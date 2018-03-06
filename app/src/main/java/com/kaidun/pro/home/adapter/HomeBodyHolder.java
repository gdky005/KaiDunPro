package com.kaidun.pro.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.R;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.CourseSchedule;
import com.kaidun.pro.home.bean.CourseInfo;
import com.kaidun.pro.home.bean.Home;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.rl_teacher_evaluation)
    RelativeLayout mTeacherEvaluationLayout;
    @Nullable
    @BindView(R.id.ll_course_schedule)
    LinearLayout mCourseScheduleLayout;
    @BindView(R.id.pb_loading)
    ProgressBar mLoading;
    private Context mContext;
    private CourseInfo.ResultBean.ClassCourseInfoBean mCourseInfo;
    private static double sScheduleLength = 0;
    private View mItemView;

    public HomeBodyHolder(View view) {
        super(view);
        mItemView = itemView;
        mContext = view.getContext();
    }

    @Override
    public void setEmptyData(int count) {
        mTeacherEvaluationContent.setText("暂无老师评价");
        if (getAdapterPosition() != (count - 1)) {
            mTeacherEvaluationLayout.setVisibility(View.GONE);
        } else {
            mTeacherEvaluationLayout.setVisibility(View.VISIBLE);
        }
        showCourseSchedule();
        if (TextUtils.isEmpty(mCourseName.getText().toString())) {
            mCourseName.setText("暂无");
        }
        if (mCourseInfo != null && mCourseInfo.getCourseUrl() != null) {
            mCoursePhoto.setImageURI(mCourseInfo.getCourseUrl());
        }
        setCoursePercentageWithEmpty();
        setCourseSpinnerWithEmpty();
    }

    private void showCourseSchedule() {
        mLoading.setVisibility(View.GONE);
        if (mCourseScheduleLayout != null) {
            mCourseScheduleLayout.setVisibility(View.VISIBLE);
        }
    }

    private void hideCourseSchedule() {
        mLoading.setVisibility(View.VISIBLE);
        if (mCourseScheduleLayout != null) {
            mCourseScheduleLayout.setVisibility(View.INVISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    public void setData(Home home, int count) throws JSONException {
        if (home instanceof CourseInfo.ResultBean.ClassCourseInfoBean) {
            hideCourseSchedule();
            CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean
                    = (CourseInfo.ResultBean.ClassCourseInfoBean) home;
            if (courseInfoBean.getCourseUrl() != null) {
                mCoursePhoto.setImageURI(courseInfoBean.getCourseUrl());
            }
            mCourseInfo = courseInfoBean;
            changeShowBook(courseInfoBean, 0);
            String bookCode = courseInfoBean.getBookModels().get(0).getBookCode();
            String courseSortId = courseInfoBean.getCourseSortId();
            requestCourseSchedule(bookCode, courseSortId);
            setCourseSpinner(courseInfoBean);
            mCourseSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        hideCourseSchedule();
                        String bookCode = courseInfoBean.getBookModels().get(position).getBookCode();
                        String courseSortId = courseInfoBean.getCourseSortId();
                        requestCourseSchedule(bookCode, courseSortId);
                        changeShowBook(courseInfoBean, position);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            teacherEvaluation(count);
        }
    }

    private void requestCourseSchedule(String bookCode, String courseSortId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseSortId", courseSortId);
        jsonObject.put("bookCode", bookCode);
        KDApi kdApi = KDConnectionManager.getInstance().getZHApi();
        kdApi.selectBookFinishRate(KDRequestUtils.getRequestBody(jsonObject)).enqueue(new Callback<CourseSchedule>() {
            @Override
            public void onResponse(Call<CourseSchedule> call, Response<CourseSchedule> response) {
                try {
                    if (response.body() != null) {
                        if (!(response.body().getStatusCode() == 100)) {
                            showToast(response.body().getMessage());
                        } else {
                            setCoursePercentage(response.body().getResult().get(0));
                        }
                    }
                    showCourseSchedule();
                } catch (Exception e) {
                    setCoursePercentageWithEmpty();
                    showCourseSchedule();
                }
            }

            @Override
            public void onFailure(Call<CourseSchedule> call, Throwable t) {
                showCourseSchedule();
            }
        });
    }

    private void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    private void changeShowBook(CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean, int position) {
        if (TextUtils.isEmpty(courseInfoBean.getClassName())) {
            mCourseName.setText("暂无");
            LogUtils.e("TextUtils.isEmpty(ClassName)");
        } else {
            mCourseName.setText(courseInfoBean.getClassName());
        }
        mCoursePhoto.setImageURI(courseInfoBean.getBookModels().get(position).getBookUrl());
    }

    public void teacherEvaluation(int count) {
        if (getAdapterPosition() == (count - 1)) {
            Log.e("T", getAdapterPosition() + "");
            mTeacherEvaluationLayout.setVisibility(View.VISIBLE);
            loadTeacherEvaluation();
        } else {
            Log.e("T", getAdapterPosition() + "");
            mTeacherEvaluationLayout.setVisibility(View.GONE);
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadTeacherEvaluation() {
        if (!TextUtils.isEmpty(CourseInfo.ResultBean.ClassCourseInfoBean.comment)) {
            mTeacherEvaluationContent.setText(CourseInfo.ResultBean.ClassCourseInfoBean.comment);
        } else {
            mTeacherEvaluationContent.setVisibility(View.GONE);
            mTeacherEvaluationContent.postDelayed(() -> {
                if (!TextUtils.isEmpty(CourseInfo.ResultBean.ClassCourseInfoBean.comment)) {
                    mTeacherEvaluationContent.setText(CourseInfo.ResultBean.ClassCourseInfoBean.comment);
                } else {
                    mTeacherEvaluationContent.setText("暂无老师评价");
                }
                mTeacherEvaluationContent.setVisibility(View.VISIBLE);
            }, 500);
        }

        if (!TextUtils.isEmpty(CourseInfo.ResultBean.ClassCourseInfoBean.publishTime)
                && !TextUtils.isEmpty(CourseInfo.ResultBean.ClassCourseInfoBean.teacher)) {
            mTeacherEvaluationDate.setText(CourseInfo.ResultBean.ClassCourseInfoBean.publishTime
                    + " by " + CourseInfo.ResultBean.ClassCourseInfoBean.teacher);
        } else {
            mTeacherEvaluationDate.setText("");
        }
    }

    private void setCourseSpinner(CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean) {
        String[] bookCodes = getBookCodeList(courseInfoBean);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_dropdown_item, bookCodes);
        mCourseSelect.setAdapter(adapter);
    }

    private void setCourseSpinnerWithEmpty() {
        String[] bookCodes = new String[] {"暂无"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_dropdown_item, bookCodes);
        mCourseSelect.setAdapter(adapter);
    }

    private String[] getBookCodeList(CourseInfo.ResultBean.ClassCourseInfoBean courseInfoBean) {
        String[] bookCodes = new String[courseInfoBean.getBookModels().size()];
        Log.e("Tag", "size " + courseInfoBean.getBookModels().size());
        for (int i = 0; i < courseInfoBean.getBookModels().size(); i++) {
            bookCodes[i] = courseInfoBean.getBookModels().get(i).getBookCode();
        }
        return bookCodes;
    }

    private void setCoursePercentage(CourseSchedule.ResultBean resultBean) {
        setPercentage(mListenSchedule,
                calculatePercentage(resultBean.getListingRate()),
                mListenPercentage);
        setPercentage(mSpeakSchedule,
                calculatePercentage(resultBean.getSpeakingRate()),
                mSpeakPercentage);
        setPercentage(mReadSchedule,
                calculatePercentage(resultBean.getReadingRate()),
                mReadPercentage);
        setPercentage(mWriteSchedule,
                calculatePercentage(resultBean.getWritingRate()),
                mWritePercentage);
    }

    private void setCoursePercentageWithEmpty() {
        setPercentage(mListenSchedule,
                calculatePercentage("0"),
                mListenPercentage);
        setPercentage(mSpeakSchedule,
                calculatePercentage("0"),
                mSpeakPercentage);
        setPercentage(mReadSchedule,
                calculatePercentage("0"),
                mReadPercentage);
        setPercentage(mWriteSchedule,
                calculatePercentage("0"),
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
        layout.measure(0, 0);
        if (sScheduleLength == 0) {
            sScheduleLength = layout.getWidth();
        }
        Log.e("TAG", "sScheduleLength = " + sScheduleLength);
        if (progress > 1) {
            progress = 1;
        }

        params.width = (int) (sScheduleLength * progress);
        layout.setLayoutParams(params);
    }
}
