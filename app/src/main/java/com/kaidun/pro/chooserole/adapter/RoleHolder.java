package com.kaidun.pro.chooserole.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.FamilyRoleBean;
import com.kaidun.pro.notebook.bean.FamilyContact;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * Created by Doraemon on 2018/1/24.
 */
public class RoleHolder extends ZKViewHolder {

    @BindView(R.id.iv_role)
    ImageView roleIv;
    @BindView(R.id.tv_role_name)
    TextView nameTv;

    public RoleHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setData(FamilyRoleBean.ResultBean resultBean) {
//        nameTv.setText(resultBean.getFamilyRoleName());


    }

}
