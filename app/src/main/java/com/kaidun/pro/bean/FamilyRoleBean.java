package com.kaidun.pro.bean;

/**
 * FamilyRoleBean
 * Created by WangQing on 2018/1/23.
 */

public class FamilyRoleBean {

    /**
     * message : 成功
     * result : [{"familyRoleCode":"001","familyRoleName":"妈妈"},{"familyRoleCode":"002","familyRoleName":"爸爸"},{"familyRoleCode":"003","familyRoleName":"奶奶"},{"familyRoleCode":"004","familyRoleName":"爷爷"},{"familyRoleCode":"005","familyRoleName":"外婆"},{"familyRoleCode":"006","familyRoleName":"外公"},{"familyRoleCode":"007","familyRoleName":"其他"}]
     * statusCode : 100
     * ver : 1
     */

    private String familyRoleCode;
    private String familyRoleName;

    public String getFamilyRoleCode() {
        return familyRoleCode;
    }

    public void setFamilyRoleCode(String familyRoleCode) {
        this.familyRoleCode = familyRoleCode;
    }

    public String getFamilyRoleName() {
        return familyRoleName;
    }

    public void setFamilyRoleName(String familyRoleName) {
        this.familyRoleName = familyRoleName;
    }

    @Override
    public String toString() {
        return "{" +
                "familyRoleCode:'" + familyRoleCode + '\'' +
                ", familyRoleName:'" + familyRoleName + '\'' +
                '}';
    }
}
