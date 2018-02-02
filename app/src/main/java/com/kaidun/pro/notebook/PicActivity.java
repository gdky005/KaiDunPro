package com.kaidun.pro.notebook;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.adapter.PicAdapter;
import com.kaidun.pro.bean.KDListBaseBean;
import com.kaidun.pro.bean.PicBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.retrofit2.KDListCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.component.ZKRecycleView;

import static com.kaidun.pro.Constant.CC_ID;
import static com.kaidun.pro.Constant.CLASS_ID;

/**
 * @author Yunr
 * @date 2018/02/02 15:03
 */
public class PicActivity extends KDBaseActivity {

    @BindView(R.id.pic_recycle_view)
    ZKRecycleView picRecycleView;

    private List<PicBean> list;
    private PicAdapter picAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        mTitle.setText("照片");
        initRecyclerView();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();

//        for (int i = 0; i < 60; i++) {
//            PicBean itemBean = new PicBean();
//            itemBean.setUploadTime("2018年01月24日 " + i);
//            list.add(itemBean);
//        }
        picAdapter = new PicAdapter(list);

        picRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        picRecycleView.setAdapter(picAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {
        String ccId = getIntent().getStringExtra(CC_ID);
        String classId = getIntent().getStringExtra(CLASS_ID);
        JSONObject jsonObject = new JSONObject();
        if (!TextUtils.isEmpty(ccId) && TextUtils.isEmpty(classId)) {
            try {
                jsonObject.put("ccId", ccId);
                jsonObject.put("classId", classId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        KDConnectionManager.getInstance().getZHApi().selectFamilyPicture(
                KDRequestUtils.getRequestBody(jsonObject)).enqueue(new KDListCallback<PicBean>() {
            @Override
            public void onResponse(KDListBaseBean<PicBean> baseBean, List<PicBean> result) {
                picAdapter.setNewData(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                ToastUtils.showShort(throwable.getMessage());
            }
        });

    }

}
