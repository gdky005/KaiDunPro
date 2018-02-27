package com.kaidun.pro.notebook.bean;

import java.util.List;

/**
 * @author Yunr
 * @date 2018/01/23 15:35
 */
public class FamContent {


    /**
     * message : 成功
     * result : {"slideCode":1,"FamilyContactList":[{"ccId":"63E6C5E6A6A3BD38E0530C01000A8339","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 11.Review1-2","csUrl":"","kwcmSendTime":"2018-01-29","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":5,"text":"\u2026\u2026\u2026是吧","writingRate":"0%"},{"ccId":"6267F9D72CD20203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 18.UNIT14","csUrl":"","kwcmSendTime":"2018-01-29","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":4,"text":"If you wish to proceed to the MA,be sure to inform the university of your choice","writingRate":"0%"},{"ccId":"6267F9D72CC30203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 3.UNIT3","csUrl":"","kwcmSendTime":"2018-01-27","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":4,"text":"Six months later, donations allow a bridge to be built between the two mountains and Wawa finally gets to walk to school","writingRate":"0%"},{"ccId":"6267F9D72CC20203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 2.UNIT2","csUrl":"","kwcmSendTime":"2018-01-16","limit":0,"listingRate":"0%","option":"A","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":5,"text":"WaWa with a big mouth meets many animals","writingRate":"0%"},{"ccId":"6267F9D72CD00203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 16.Review3","csUrl":"","kwcmSendTime":"2018-01-11","limit":0,"listingRate":"0%","option":"A-","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":3,"text":"When a teacher, Nie, visits Wawa's home with a pair of red rain boots for Naxiang, Wawa's secret is revealed","writingRate":"0%"}]}
     * statusCode : 100
     * ver : 1
     */

    private String message;
    private ResultBean result;
    private int statusCode;
    private int ver;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public static class ResultBean {
        /**
         * slideCode : 1
         * FamilyContactList : [{"ccId":"63E6C5E6A6A3BD38E0530C01000A8339","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 11.Review1-2","csUrl":"","kwcmSendTime":"2018-01-29","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":5,"text":"\u2026\u2026\u2026是吧","writingRate":"0%"},{"ccId":"6267F9D72CD20203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 18.UNIT14","csUrl":"","kwcmSendTime":"2018-01-29","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":4,"text":"If you wish to proceed to the MA,be sure to inform the university of your choice","writingRate":"0%"},{"ccId":"6267F9D72CC30203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 3.UNIT3","csUrl":"","kwcmSendTime":"2018-01-27","limit":0,"listingRate":"0%","option":"A+","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":4,"text":"Six months later, donations allow a bridge to be built between the two mountains and Wawa finally gets to walk to school","writingRate":"0%"},{"ccId":"6267F9D72CC20203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 2.UNIT2","csUrl":"","kwcmSendTime":"2018-01-16","limit":0,"listingRate":"0%","option":"A","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":5,"text":"WaWa with a big mouth meets many animals","writingRate":"0%"},{"ccId":"6267F9D72CD00203E0530C01000A0B8F","classId":"c1ca6da9-670b-4b5e-8610-42289a4fb8cf","className":"ABC1000-1","courseSortName":"ABC 16.Review3","csUrl":"","kwcmSendTime":"2018-01-11","limit":0,"listingRate":"0%","option":"A-","readingRate":"0%","slideCode":0,"speakingRate":"0%","start":3,"text":"When a teacher, Nie, visits Wawa's home with a pair of red rain boots for Naxiang, Wawa's secret is revealed","writingRate":"0%"}]
         */

        private int slideCode;
        private List<FamilyContactListBean> FamilyContactList;

        public int getSlideCode() {
            return slideCode;
        }

        public void setSlideCode(int slideCode) {
            this.slideCode = slideCode;
        }

        public List<FamilyContactListBean> getFamilyContactList() {
            return FamilyContactList;
        }

        public void setFamilyContactList(List<FamilyContactListBean> FamilyContactList) {
            this.FamilyContactList = FamilyContactList;
        }

        public static class FamilyContactListBean {
            /**
             * ccId : 63E6C5E6A6A3BD38E0530C01000A8339
             * classId : c1ca6da9-670b-4b5e-8610-42289a4fb8cf
             * className : ABC1000-1
             * courseSortName : ABC 11.Review1-2
             * csUrl :
             * kwcmSendTime : 2018-01-29
             * limit : 0
             * listingRate : 0%
             * option : A+
             * readingRate : 0%
             * slideCode : 0
             * speakingRate : 0%
             * start : 5
             * text : ………是吧
             * writingRate : 0%
             * kwcmId
             */

            private String ccId;
            private String classId;
            private String className;
            private String courseSortName;
            private String csUrl;
            private String kwcmSendTime;
            private int limit;
            private String listingRate;
            private String option;
            private String readingRate;
            private int slideCode;
            private String speakingRate;
            private int start;
            private int hourNum;
            private String processId;
            private String text;
            private String writingRate;
            private String kwcmId;
            private int flowerStatus;//0没送过，1送过了

            public String getKwcmId() {
                return kwcmId;
            }

            public void setKwcmId(String kwcmId) {
                this.kwcmId = kwcmId;
            }

            public int getFlowerStatus() {
                return flowerStatus;
            }

            public void setFlowerStatus(int flowerStatus) {
                this.flowerStatus = flowerStatus;
            }

            public int getHourNum() {
                return hourNum;
            }

            public void setHourNum(int hourNum) {
                this.hourNum = hourNum;
            }

            public String getProcessId() {
                return processId;
            }

            public void setProcessId(String processId) {
                this.processId = processId;
            }

            public String getCcId() {
                return ccId;
            }

            public void setCcId(String ccId) {
                this.ccId = ccId;
            }

            public String getClassId() {
                return classId;
            }

            public void setClassId(String classId) {
                this.classId = classId;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public String getCourseSortName() {
                return courseSortName;
            }

            public void setCourseSortName(String courseSortName) {
                this.courseSortName = courseSortName;
            }

            public String getCsUrl() {
                return csUrl;
            }

            public void setCsUrl(String csUrl) {
                this.csUrl = csUrl;
            }

            public String getKwcmSendTime() {
                return kwcmSendTime;
            }

            public void setKwcmSendTime(String kwcmSendTime) {
                this.kwcmSendTime = kwcmSendTime;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public String getListingRate() {
                return listingRate;
            }

            public void setListingRate(String listingRate) {
                this.listingRate = listingRate;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }

            public String getReadingRate() {
                return readingRate;
            }

            public void setReadingRate(String readingRate) {
                this.readingRate = readingRate;
            }

            public int getSlideCode() {
                return slideCode;
            }

            public void setSlideCode(int slideCode) {
                this.slideCode = slideCode;
            }

            public String getSpeakingRate() {
                return speakingRate;
            }

            public void setSpeakingRate(String speakingRate) {
                this.speakingRate = speakingRate;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getWritingRate() {
                return writingRate;
            }

            public void setWritingRate(String writingRate) {
                this.writingRate = writingRate;
            }
        }
    }
}
