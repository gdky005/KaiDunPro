package com.kaidun.pro.notebook;

import android.content.Intent;

import com.kaidun.pro.R;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.adapter.FamContentAdapter;
import com.kaidun.pro.notebook.bean.FamContent;
import com.kaidun.pro.notebook.bean.FamilyContact;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * @author Yunr
 * @date 2018/01/23 15:26
 */
public class NoteBookActivity extends BaseActivity {

    ZKRecycleView mNoteBookList;

    private FamilyContact.ResultBean resultBean;
    private FamContentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_book;
    }

    @Override
    protected void initViews() {
        mNoteBookList = findViewById(R.id.note_book_list);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        resultBean = (FamilyContact.ResultBean) intent.getSerializableExtra("book");
        mTitle.setText("家联本内");

        ArrayList<FamContent> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FamContent famContent = new FamContent();
            famContent.setBookName("ABC" + i);
            famContent.setStart(3);
            famContent.setText("我给你讲！写不完作业就别想回家！！！");
            famContent.setUnitCode(4);
            famContent.setListeningRate(0.72);
            famContent.setSpeakingRate(0.34);
            famContent.setReadingRate(0.67);
            famContent.setWritingRate(0.12);
            famContent.setPractiseTime("2018-1-" + i);
            list.add(famContent);
        }

        //TODO:请求数据
        adapter = new FamContentAdapter(R.layout.item_fam_content, list);
        mNoteBookList.setAdapter(adapter);
        adapter.setUpFetchEnable(true);
    }


    private void selectFamContContext() {

        int unitCode = 1;
        String courseType = "fd23aa51-dd03-4896-98cf-254864f646a9";
//        areaCode（地区编码）	1001
//        userCode（用户码）	10009010
//        courseType（课程类别）	fd23aa51-dd03-4896-98cf-254864f646a9
//        unitCode(单元id)（向下拉动查询）	1

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("courseType", courseType);
            jsonObject.put("unitCode", unitCode);
            KDConnectionManager.getInstance().getZHApi().selectFamContContext(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<FamContent>() {
                        @Override
                        public void onResponse(Call<FamContent> call, Response<FamContent> response) {
                            //showList(list);
                        }

                        @Override
                        public void onFailure(Call<FamContent> call, Throwable t) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
