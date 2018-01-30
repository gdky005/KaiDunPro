package com.kaidun.pro;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.bean.ClassBean;
import com.kaidun.pro.notebook.bean.MsgBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * Created by lmj on 2018/1/24.
 */

public class WriteMsgActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mToolbarTitle;

    @BindView(R.id.cancel_btn)
    TextView cancel;

    @BindView(R.id.send_btn)
    TextView send;

    @BindView(R.id.spinner_class)
    Spinner spinnerClass;

    @BindView(R.id.edit_content)
    EditText mEditContent;

    @BindView(R.id.edit_theme)
    EditText mEditTheme;

    @BindView(R.id.class_icon)
    ImageView mClassIcon;
    @BindView(R.id.class_tag)
    TextView mClassTag;

    private KdNetWorkClient httpUtils;
    private HashMap<String, String> classInfos = new HashMap<String, String>();  //默认大小应该足够了
    private boolean isGetClassInfoSuccess = false;
    private ArrayList<String> className = new ArrayList<>();
    private String classId;
    private ArrayAdapter<String> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.write_msg_layout;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        httpUtils = new KdNetWorkClient();
        cancel.setOnClickListener(this);
        send.setOnClickListener(this);
        mToolbarTitle.setText(R.string.write_msg);

        adapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, R.id.class_name, className);
        adapter.setDropDownViewResource(R.layout.item_class_select);
        spinnerClass.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null && (classId = intent.getStringExtra(Constant.CLASS_ID)) != null) {
            classId = intent.getStringExtra(Constant.CLASS_ID);
            className.add(intent.getStringExtra(Constant.CLASS_Name));
            className.add(intent.getStringExtra(Constant.CLASS_Name));

//            //家联本内容跳转过来无班级名称，隐藏吧
//            mClassTag.setVisibility(View.GONE);
//            mClassIcon.setVisibility(View.GONE);
//            spinnerClass.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        } else {  //默认界面跳过来需要先请求班级
            getClasses();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == send) {
            sendClassMsg();
        } else if (view == cancel) {
            finish();
        } else if (view == spinnerClass) {
            if (isGetClassInfoSuccess) {
                return;
            }
            getClasses();
        }
    }


    private void getClasses() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<ClassBean>() {
            @Override
            public void getSuccessDataCallBack(ClassBean data) {
                if (data != null) {
                    isGetClassInfoSuccess = true;
                    if (data.getResult() != null && data.getResult().size() > 0) {
                        tranfToArray(data.getResult());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    ToastUtils.showShort("无法获取到班级信息");
                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {

            }
        });
        httpUtils.selectClassInfo();
    }

    private void tranfToArray(List<ClassBean.ResultBean> result) {
        className.clear();
        //  className = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ClassBean.ResultBean bean = result.get(i);
            classInfos.put(bean.getClassName(), bean.getClassId());
//            className[i] = bean.getClassName();
            className.add(bean.getClassName());
        }
    }

    private Handler mHandler = new Handler();

    private void sendClassMsg() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {
            @Override
            public void getSuccessDataCallBack(MsgBean data) {
                if (data != null && data.getStatusCode() == 100) {
                    ToastUtils.showLong("留言成功");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);

                }
            }

            @Override
            public void getFailDataCallBack(int failIndex) {

            }
        });
//        if (spinnerClass.getSelectedItem() != null) {
        String className = spinnerClass.getSelectedItem().toString();
        String tempclassId = classId != null ? classId : classInfos.get(className);
//        } else {//从家联本跳转过来的没有班级名称
//            tempclassId = classId;
//        }

        String content = mEditContent.getText().toString();
        String theme = mEditTheme.getText().toString();
        httpUtils.leaveMsg(content, theme, tempclassId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpUtils.setmCallBack(null);
        httpUtils = null;
    }

    //-------------------
}
