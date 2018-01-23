package com.kaidun.pro.notebook.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Yunr
 * @date 2018/01/23 13:37
 */
public class FamilyContact {

    /**
     * message : 成功
     * result : [{"bookName":"暑托班","courseSortId":"101b3642-8a7e-4380-bbf2-228af6107d22"},{"bookName":"ABC","courseSortId":"fd23aa51-dd03-4896-98cf-254864f646a9"},{"bookName":"LA","courseSortId":"b66b6d55-8685-461e-851f-a98196348852"}]
     * statusCode : 100
     * ver : 1
     */

    private String message;
    private int statusCode;
    private int ver;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean  implements Serializable{
        /**
         * bookName : 暑托班
         * courseSortId : 101b3642-8a7e-4380-bbf2-228af6107d22
         */

        private String bookName;
        private String courseSortId;

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getCourseSortId() {
            return courseSortId;
        }

        public void setCourseSortId(String courseSortId) {
            this.courseSortId = courseSortId;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "bookName='" + bookName + '\'' +
                    ", courseSortId='" + courseSortId + '\'' +
                    '}';
        }
    }
}
