package com.kaidun.pro.bean;

import java.util.List;

/**
 * PicBean
 * Created by WangQing on 2018/1/24.
 */

public class PicBean {


    /**
     * pictureUrlMap : [{"dvdUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142435.jpg","kflId":"a3349d27-a6f7-4d68-b665-ce82435a596e"},{"dvdUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142427_0.jpg","kflId":"e188f6d5-216f-4974-a172-55e6541053e2"},{"dvdUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142415_0.jpg","kflId":"28215722-01b8-47b1-bb33-5e4ba3503f2c"},{"dvdUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123135033_0.jpg","kflId":"66eb108c-84d2-4041-8f56-246de1db4e84"},{"dvdUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142110.jpg","kflId":"f23b669d-cbf3-4d66-9d31-427a98c16035"},{"dvdUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123135033_0.jpg","kflId":"0a3fbdf9-975d-4ae5-9186-be61e7a5bc88"},{"dvdUrl":"https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123135033_0.jpg","kflId":"12868d4e-5ab4-4fb5-8d13-12c7866e0ae8"}]
     * stuId : 44460
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
         * dvdUrl : https://teacherpicture-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018123142435.jpg
         * kflId : a3349d27-a6f7-4d68-b665-ce82435a596e
         */

        private String dvdUrl;
        private String kflId;

        public String getDvdUrl() {
            return dvdUrl;
        }

        public void setDvdUrl(String dvdUrl) {
            this.dvdUrl = dvdUrl;
        }

        public String getKflId() {
            return kflId;
        }

        public void setKflId(String kflId) {
            this.kflId = kflId;
        }
    }
}
