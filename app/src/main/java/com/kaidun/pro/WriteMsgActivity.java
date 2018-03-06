package com.kaidun.pro;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.bean.ClassBean;
import com.kaidun.pro.notebook.bean.MsgBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lmj on 2018/1/24.
 */

public class WriteMsgActivity extends KDBaseActivity implements View.OnClickListener {

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

    @BindView(R.id.pb_loading)
    ProgressBar mProgress;

    private KdNetWorkClient httpUtils;
    private HashMap<String, String> classInfos = new HashMap<String, String>();  
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
            adapter.notifyDataSetChanged();
        } else {  
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
        for (int i = 0; i < result.size(); i++) {
            ClassBean.ResultBean bean = result.get(i);
            classInfos.put(bean.getClassName(), bean.getClassId());
            className.add(bean.getClassName());
        }
    }

    private Handler mHandler = new Handler();

    private void sendClassMsg() {
        String className = spinnerClass.getSelectedItem().toString();
        String tempclassId = classId != null ? classId : classInfos.get(className);
        String content = mEditContent.getText().toString();
        String theme = mEditTheme.getText().toString();

        if (TextUtils.isEmpty(className)){
            ToastUtils.showShort("请选择班级");
            return;
        }

        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(theme)){
            ToastUtils.showShort("请输入主题和内容");
            return;
        }


        mProgress.setVisibility(View.VISIBLE);
        send.setClickable(false);
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {
            @Override
            public void getSuccessDataCallBack(MsgBean data) {
                if (data != null && data.getStatusCode() == 100) {
                    ToastUtils.showLong("留言成功");
                    clear();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 200);

                }
                send.setClickable(true);
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void getFailDataCallBack(int failIndex) {
                send.setClickable(true);
                mProgress.setVisibility(View.GONE);
            }
        });

        httpUtils.leaveMsg(content, theme, tempclassId);
    }

    private void clear() {
        mEditTheme.setText("");
        mEditContent.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpUtils.setmCallBack(null);
        httpUtils = null;
    }

}
