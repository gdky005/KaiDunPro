package com.kaidun.pro.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.R;
import com.kaidun.pro.home.bean.CourseInfo;
import com.kaidun.pro.home.bean.Home;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/23.
 */

public class HomeBodyHolder extends HomeHolder {
    @BindView(R.id.iv_course_photo)
    SimpleDraweeView mCoursePhoto;
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
    private Context mContext;
    private CourseInfo.ResultBean.ClassCourseInfoBean mCourseInfo;
    private static double sScheduleLength = 0;

    public HomeBodyHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
        mContext = view.getContext();
    }

    public void setData(Home home) {
        if (home instanceof CourseInfo.ResultBean.ClassCourseInfoBean) {
            CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean
                    = (CourseInfo.ResultBean.ClassCourseInfoBean) home;
            mCourseInfo = courseInfoBean;
            mCourseName.setText(courseInfoBean.getClassName());
            mCoursePhoto.setImageURI(courseInfoBean.getBookModels().get(0).getBookUrl());

            setCoursePercentage(courseInfoBean, 0);
            setCourseSpinner(courseInfoBean);
            mCourseSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setCoursePercentage(courseInfoBean, position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        mTeacherEvaluationContent.setText(home.teacherEvaluation);
        mTeacherEvaluationDate.setText(home.teacherEvaluationDate);
    }

    private void setCourseSpinner(CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean) {
        String[] bookCodes = getBookCodeList(courseInfoBean);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_dropdown_item, bookCodes);
        mCourseSelect.setAdapter(adapter);
    }

    private String[] getBookCodeList(CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean) {
        String[] bookCodes = new String[courseInfoBean.getBookModels().size()];
        for (int i = 0; i < courseInfoBean.getBookModels().size(); i++) {
            bookCodes[i] = courseInfoBean.getBookModels().get(i).getBookCode();
        }
        return bookCodes;
    }

    private void setCoursePercentage(CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean, int index) {
        setPercentage(mListenSchedule,
                calculatePercentage(courseInfoBean.getRateList().get(index).getListingRate()),
                mListenPercentage);
        setPercentage(mSpeakSchedule,
                calculatePercentage(courseInfoBean.getRateList().get(index).getSpeakingRate()),
                mSpeakPercentage);
        setPercentage(mReadSchedule,
                calculatePercentage(courseInfoBean.getRateList().get(index).getReadingRate()),
                mReadPercentage);
        setPercentage(mWriteSchedule,
                calculatePercentage(courseInfoBean.getRateList().get(index).getWritingRate()),
                mWritePercentage);
    }

    private double calculatePercentage(String rate) {
        double percentage = Double.valueOf(rate.split("%")[0]);
        return (percentage / 100);
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
        if (sScheduleLength == 0) {
            sScheduleLength = layout.getWidth();
        }
        params.width = (int) (sScheduleLength * progress);
        layout.setLayoutParams(params);
    }
}
