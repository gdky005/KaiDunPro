package com.kaidun.pro;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.bean.ClassBean;
import com.kaidun.pro.notebook.bean.MsgBean;

import java.security.cert.TrustAnchor;
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

    private KdNetWorkClient httpUtils;
    private HashMap<String,String> classInfos = new HashMap<String,String>();  //默认大小应该足够了
    private boolean isGetClassInfoSuccess = false;
    private String[] className;

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
        getClasses();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View view) {
        if (view == send) {
            sendClassMsg();
        } else if (view == cancel) {
            finish();
        }else if(view == spinnerClass){
            if (isGetClassInfoSuccess){
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
                        className = new String[]{"班级A", "班级B", "班级c"};
                        ArrayAdapter adapter = new ArrayAdapter<String>(mContext,
                                R.layout.item_class_select,R.id.class_name,className);
                        spinnerClass.setAdapter(adapter);
                    }
                }else {
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
        className = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ClassBean.ResultBean bean = result.get(i);
            classInfos.put(bean.getClassName(),bean.getClassId());
            className[i] = bean.getClassName();
        }
    }

    private void sendClassMsg() {
        httpUtils.setmCallBack(new KdNetWorkClient.DataCallBack<MsgBean>() {
            @Override
            public void getSuccessDataCallBack(MsgBean data) {

            }

            @Override
            public void getFailDataCallBack(int failIndex) {

            }
        });
        String className = spinnerClass.getSelectedItem().toString();
        String classId = classInfos.get(className);
        String content = mEditContent.getText().toString();
        String theme = mEditTheme.getText().toString();
        httpUtils.leaveMsg(content,theme,classId);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpUtils.setmCallBack(null);
        httpUtils = null;
    }


    //-------------------

    public class SimpleSpinnerAdapter extends ArrayAdapter {
        public SimpleSpinnerAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }



       /* @Override
        public int getCount() {
            return className.length;
        }

        @Override
        public Object getItem(int i) {
            return className[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View mView = LayoutInflater.from(WriteMsgActivity.this).inflate(R.layout.item_class_select, viewGroup, false);
            ((TextView) mView.findViewById(R.id.class_name)).setText(className[i]);
            return mView;
        }*/
    }


}
