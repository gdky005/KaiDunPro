package com.kaidun.pro.chooserole.bean;

import com.kaidun.pro.bean.FamilyRoleBean;

/**
 * Created by Doraemon on 2018/1/24.
 */

public class ChooseRoleBean {
    private boolean isSelected=false;
    private FamilyRoleBean familyRoleBean;

    public ChooseRoleBean(FamilyRoleBean familyRoleBean) {
        this.familyRoleBean = familyRoleBean;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public FamilyRoleBean getFamilyRoleBean() {
        return familyRoleBean;
    }

    public void setFamilyRoleBean(FamilyRoleBean familyRoleBean) {
        this.familyRoleBean = familyRoleBean;
    }
}
