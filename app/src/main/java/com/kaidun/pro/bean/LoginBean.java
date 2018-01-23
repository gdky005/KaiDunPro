package com.kaidun.pro.bean;

/**
 * LoginBean
 * Created by WangQing on 2018/1/23.
 */

public class LoginBean {

    /**
     * data : {"areaCode":"1001","stuCode":"10009010","stuHeadImg":"https://headicon-1255920593.cos.ap-shanghai.myqcloud.com/43465f0d-253f-4dd8-9afc-ec1e24116203_HeadIcon.png","stuId":"43465f0d-253f-4dd8-9afc-ec1e24116203","stuName":"李悅轩/Eric","userCode":"10009010"}
     * reminder : It is good that children are good.
     * token : eyJhbGciOiJIUzI1NiJ9.eyJtYWNoaW5lQ29kZSI6ImFzZGFzMjM0MiIsInRpbWUiOiIxNTE2NjkwNjkwNTM0In0.sc0mFPxpt3dgCjcpghVNXUcEPvU_gpu_GN3A5pcrhVw
     */

    private DataBean data;
    private String reminder;
    private String token;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * areaCode : 1001
         * stuCode : 10009010
         * stuHeadImg : https://headicon-1255920593.cos.ap-shanghai.myqcloud.com/43465f0d-253f-4dd8-9afc-ec1e24116203_HeadIcon.png
         * stuId : 43465f0d-253f-4dd8-9afc-ec1e24116203
         * stuName : 李悅轩/Eric
         * userCode : 10009010
         */

        private String areaCode;
        private String stuCode;
        private String stuHeadImg;
        private String stuId;
        private String stuName;
        private String userCode;

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getStuCode() {
            return stuCode;
        }

        public void setStuCode(String stuCode) {
            this.stuCode = stuCode;
        }

        public String getStuHeadImg() {
            return stuHeadImg;
        }

        public void setStuHeadImg(String stuHeadImg) {
            this.stuHeadImg = stuHeadImg;
        }

        public String getStuId() {
            return stuId;
        }

        public void setStuId(String stuId) {
            this.stuId = stuId;
        }

        public String getStuName() {
            return stuName;
        }

        public void setStuName(String stuName) {
            this.stuName = stuName;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        @Override
        public String toString() {
            return "{" +
                    "areaCode:'" + areaCode + '\'' +
                    ", stuCode:'" + stuCode + '\'' +
                    ", stuHeadImg:'" + stuHeadImg + '\'' +
                    ", stuId:'" + stuId + '\'' +
                    ", stuName:'" + stuName + '\'' +
                    ", userCode:'" + userCode + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "data:" + data +
                ", reminder:'" + reminder + '\'' +
                ", token:'" + token + '\'' +
                '}';
    }
}
