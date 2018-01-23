package com.kaidun.pro.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.home.bean.Home;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HomeHolder extends ZKViewHolder {
    @BindView(R.id.iv_course_photo)
    ImageView mCoursePhoto;
    @BindView(R.id.iv_course_name)
    TextView mCourseName;
    @BindView(R.id.sp_course_select)
    Spinner mCourseSelect;
    @BindView(R.id.fl_listen_schedule)
    FrameLayout mListenSchedule;
    @BindView(R.id.fl_listen)
    FrameLayout mListen;
    @BindView(R.id.tv_listen_percentage)
    TextView mListenPercentage;
    @BindView(R.id.fl_speak_schedule)
    FrameLayout mSpeakSchedule;
    @BindView(R.id.tv_speak_percentage)
    TextView mSpeakPercentage;
    @BindView(R.id.fl_read_schedule)
    FrameLayout mReadSchedule;
    @BindView(R.id.tv_read_percentage)
    TextView mReadPercentage;
    @BindView(R.id.fl_write_schedule)
    FrameLayout mWriteSchedule;
    @BindView(R.id.tv_write_percentage)
    TextView mWritePercentage;
    @BindView(R.id.tv_teacher_evaluation_content)
    TextView mTeacherEvaluationContent;
    @BindView(R.id.tv_teacher_evaluation_date)
    TextView mTeacherEvaluationDate;

    public HomeHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Home home) {
        mCourseName.setText(home.courseName);
        setPercentage(mListenSchedule, home.listenProgress, mListenPercentage);
        setPercentage(mSpeakSchedule, home.speakProgress, mSpeakPercentage);
        setPercentage(mReadSchedule, home.readProgress, mReadPercentage);
        setPercentage(mWriteSchedule, home.writeProgress, mWritePercentage);
        mTeacherEvaluationContent.setText(home.teacherEvaluation);
        mTeacherEvaluationDate.setText(home.teacherEvaluationDate);
    }

    @SuppressLint("SetTextI18n")
    private void setPercentage(final FrameLayout layout, double progress, TextView percentage) {
        layout.post(() -> realSetPercentage(layout, progress));
        percentage.setText((int) (progress * 100) + "%");
    }

    private void realSetPercentage(FrameLayout layout, double progress) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout.getLayoutParams();
        //  measure方法的参数值都设为0即可
        layout.measure(0, 0);
        Log.e("TAG", "getMeasuredWidth = " + layout.getMeasuredWidth());
        Log.e("TAG", "getWidth = " + layout.getWidth());
        Log.e("TAG", "getMeasuredWidthAndState = " + layout.getMeasuredWidthAndState());
        Log.e("TAG", "params.width = " + params.width);
        params.width = (int) (layout.getWidth() * progress);
        layout.setLayoutParams(params);
    }
}
