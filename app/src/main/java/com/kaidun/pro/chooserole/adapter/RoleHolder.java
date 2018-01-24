package com.kaidun.pro.chooserole.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.chooserole.bean.ChooseRoleBean;

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

    public void setData(ChooseRoleBean resultBean) {
        String roleName=resultBean.getFamilyRoleBean().getFamilyRoleName();
        if (("爸爸").equals(roleName)) {
            roleIv.setImageResource(R.drawable.selector_role_father);
        } else if (("妈妈").equals(roleName)) {
            roleIv.setImageResource(R.drawable.selector_role_mother);
        } else if (("爷爷").equals(roleName)) {
            roleIv.setImageResource(R.drawable.selector_role_grandpa);
        } else if (("奶奶").equals(roleName)) {
            roleIv.setImageResource(R.drawable.selector_role_grandmother);
        } else if (("外公").equals(roleName)) {
            roleIv.setImageResource(R.drawable.selector_role_grandpa2);
        } else if (("外婆").equals(roleName)) {
            roleIv.setImageResource(R.drawable.selector_role_grandmother2);
        } else {
            roleIv.setImageResource(R.drawable.selector_role_other);
        }
        roleIv.setSelected(resultBean.isSelected());
        nameTv.setSelected(resultBean.isSelected());
        nameTv.setText(resultBean.getFamilyRoleBean().getFamilyRoleName());


    }

}
