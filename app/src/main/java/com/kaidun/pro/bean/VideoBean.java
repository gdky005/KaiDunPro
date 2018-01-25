package com.kaidun.pro.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by fatchao
 * 日期  2018-01-24.
 * 邮箱  fat_chao@163.com
 */

public class VideoBean implements Parcelable {

    /**
     * courseSortId : 40051078-ce55-45ce-95a3-67aaca1796aa
     * courseSortName : ABC
     * dvdList : [{"bookCode":"1","bookUrl":"http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png","courseSortId":"40051078-ce55-45ce-95a3-67aaca1796aa","courseSortName":"ABC"}]
     */

    private String courseSortId;
    private String courseSortName;
    private List<DvdListBean> dvdList;

    protected VideoBean(Parcel in) {
        courseSortId = in.readString();
        courseSortName = in.readString();
        dvdList = in.createTypedArrayList(DvdListBean.CREATOR);
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel in) {
            return new VideoBean(in);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };

    public String getCourseSortId() {
        return courseSortId;
    }

    public void setCourseSortId(String courseSortId) {
        this.courseSortId = courseSortId;
    }

    public String getCourseSortName() {
        return courseSortName;
    }

    public void setCourseSortName(String courseSortName) {
        this.courseSortName = courseSortName;
    }

    public List<DvdListBean> getDvdList() {
        return dvdList;
    }

    public void setDvdList(List<DvdListBean> dvdList) {
        this.dvdList = dvdList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(courseSortId);
        parcel.writeString(courseSortName);
        parcel.writeTypedList(dvdList);
    }

    public static class DvdListBean implements Parcelable {
        /**
         * bookCode : 1
         * bookUrl : http://211.152.60.252:8088/baseImg/book/fun-book-01@2x.png
         * courseSortId : 40051078-ce55-45ce-95a3-67aaca1796aa
         * courseSortName : ABC
         */
        private String bookCode;
        private String bookUrl;
        private String courseSortId;
        private String courseSortName;

        protected DvdListBean(Parcel in) {
            bookCode = in.readString();
            bookUrl = in.readString();
            courseSortId = in.readString();
            courseSortName = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(bookCode);
            dest.writeString(bookUrl);
            dest.writeString(courseSortId);
            dest.writeString(courseSortName);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DvdListBean> CREATOR = new Creator<DvdListBean>() {
            @Override
            public DvdListBean createFromParcel(Parcel in) {
                return new DvdListBean(in);
            }

            @Override
            public DvdListBean[] newArray(int size) {
                return new DvdListBean[size];
            }
        };

        public String getBookCode() {
            return bookCode;
        }

        public void setBookCode(String bookCode) {
            this.bookCode = bookCode;
        }

        public String getBookUrl() {
            return bookUrl;
        }

        public void setBookUrl(String bookUrl) {
            this.bookUrl = bookUrl;
        }

        public String getCourseSortId() {
            return courseSortId;
        }

        public void setCourseSortId(String courseSortId) {
            this.courseSortId = courseSortId;
        }

        public String getCourseSortName() {
            return courseSortName;
        }

        public void setCourseSortName(String courseSortName) {
            this.courseSortName = courseSortName;
        }
    }
}
