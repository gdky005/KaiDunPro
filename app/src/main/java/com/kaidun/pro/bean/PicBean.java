package com.kaidun.pro.bean;

import java.util.List;

/**
 * PicBean
 * Created by WangQing on 2018/1/24.
 */

public class PicBean {

    /**
     * pictureUrlMap : [{"kflId":"c398ebed-2cd5-41e0-accb-88b0ef1fc54d","sendSmallUrl":"https://teachervideo-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018130112646_101.jpg","teacSendUrl":"https://teachervideo-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018130112646.mp4"},{"kflId":"524a0637-536b-4c79-a14d-7e0d79119122","sendSmallUrl":"https://teachervideo-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_201813011261_101.jpg","teacSendUrl":"https://teachervideo-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_201813011262.mp4"},{"kflId":"29ba6fa6-490d-4211-9acb-8fd92ead3567","sendSmallUrl":"http://211.152.60.252:8088/A.png","teacSendUrl":"http://211.152.60.252:8088/WeChat_20180127154725.mp4"}]
     * stuId : 19d0d795-a939-4d66-b4b6-c11ec25e0d3a
     * uploadTime : 2018-01-30
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
         * kflId : c398ebed-2cd5-41e0-accb-88b0ef1fc54d
         * sendSmallUrl : https://teachervideo-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018130112646_101.jpg
         * teacSendUrl : https://teachervideo-1255920593.cos.ap-shanghai.myqcloud.com/kd2405_2018130112646.mp4
         */

        private String kflId;
        private String sendSmallUrl;
        private String teacSendUrl;

        public String getKflId() {
            return kflId;
        }

        public void setKflId(String kflId) {
            this.kflId = kflId;
        }

        public String getSendSmallUrl() {
            return sendSmallUrl;
        }

        public void setSendSmallUrl(String sendSmallUrl) {
            this.sendSmallUrl = sendSmallUrl;
        }

        public String getTeacSendUrl() {
            return teacSendUrl;
        }

        public void setTeacSendUrl(String teacSendUrl) {
            this.teacSendUrl = teacSendUrl;
        }
    }
}
