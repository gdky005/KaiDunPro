package com.kaidun.pro.home.bean;

/**
 * Created by Administrator on 2018/1/23.
 */

public class Home {
    public String courseUrl;
    public String courseName;
    public double listenProgress;
    public double speakProgress;
    public double readProgress;
    public double writeProgress;
    public String teacherEvaluation;
    public String teacherEvaluationDate;

    public Home(String courseUrl, String courseName, double listenProgress, double speakProgress,
                double readProgress, double writeProgress, String teacherEvaluation,
                String teacherEvaluationDate) {
        this.courseUrl = courseUrl;
        this.courseName = courseName;
        this.listenProgress = listenProgress;
        this.speakProgress = speakProgress;
        this.readProgress = readProgress;
        this.writeProgress = writeProgress;
        this.teacherEvaluation = teacherEvaluation;
        this.teacherEvaluationDate = teacherEvaluationDate;
    }
}
