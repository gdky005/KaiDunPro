package com.kaidun.pro.notebook.bean;

/**
 * @author Yunr
 * @date 2018/01/23 15:35
 */
public class FamContent {

    private String bookName;//(书本名称)
    private String ccId;//(课表id)
    private String classId;//(班级id)
    private String courseSortId;//(课程类别id)

    private int unitCode;//(单元完成率)

    private double listeningRate;//(听完成率)
    private double speakingRate;//(说完成率)
    private double readingRate;//(读完成率)
    private double writingRate;//(写完成率)
    private String practiseTime;//（评测时间）
    private String option;//（学员表现）

    private String text;//（评语内容）

    private int start;//（星星数）

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getCourseSortId() {
        return courseSortId;
    }

    public void setCourseSortId(String courseSortId) {
        this.courseSortId = courseSortId;
    }

    public int getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(int unitCode) {
        this.unitCode = unitCode;
    }

    public double getListeningRate() {
        return listeningRate;
    }

    public void setListeningRate(double listeningRate) {
        this.listeningRate = listeningRate;
    }

    public double getSpeakingRate() {
        return speakingRate;
    }

    public void setSpeakingRate(double speakingRate) {
        this.speakingRate = speakingRate;
    }

    public double getReadingRate() {
        return readingRate;
    }

    public void setReadingRate(double readingRate) {
        this.readingRate = readingRate;
    }

    public double getWritingRate() {
        return writingRate;
    }

    public void setWritingRate(double writingRate) {
        this.writingRate = writingRate;
    }

    public String getPractiseTime() {
        return practiseTime;
    }

    public void setPractiseTime(String practiseTime) {
        this.practiseTime = practiseTime;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
