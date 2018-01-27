package com.kaidun.pro.bean;

import java.util.List;

/**
 * PicBean
 * Created by WangQing on 2018/1/24.
 */

public class PicBean {


    /**
     * pictureUrlMap : [{"kflId":"c419d7df-12e3-4d22-8497-23dff2c0450a","teacSendUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142427_0.jpg"},{"kflId":"862e6c81-89d7-4ea7-b8be-3a408c37aa61","teacSendUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142415_0.jpg"},{"kflId":"619a468e-2dc3-4c70-be3a-2a0d4fdd795f","teacSendUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123135033_0.jpg"},{"kflId":"70247623-be1e-4f53-9b31-c0fc0005ad36","teacSendUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142110.jpg"},{"kflId":"1a0ecda1-542b-4047-a25d-2a3f7a80512b","teacSendUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123135033_0.jpg"},{"kflId":"7346cf45-e3dc-46d1-add2-7f05e7d45b12","teacSendUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123135033_0.jpg"}]
     * stuId : c8390c74-5aea-4952-aa0c-89e623c3c29a
     * uploadTime : 2018-01-23
     */

    private String stuId;
    private String uploadTime;
    private List<PictureUrlMapBean> pictureUrlMap;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<PictureUrlMapBean> getPictureUrlMap() {
        return pictureUrlMap;
    }

    public void setPictureUrlMap(List<PictureUrlMapBean> pictureUrlMap) {
        this.pictureUrlMap = pictureUrlMap;
    }

    public static class PictureUrlMapBean {
        /**
         * kflId : c419d7df-12e3-4d22-8497-23dff2c0450a
         * teacSendUrl : https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142427_0.jpg
         */

        private String kflId;
        private String teacSendUrl;

        public String getKflId() {
            return kflId;
        }

        public void setKflId(String kflId) {
            this.kflId = kflId;
        }

        public String getTeacSendUrl() {
            return teacSendUrl;
        }

        public void setTeacSendUrl(String teacSendUrl) {
            this.teacSendUrl = teacSendUrl;
        }
    }
}
