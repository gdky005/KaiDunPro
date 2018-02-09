package com.kaidun.pro.notebook.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.Constant;
import com.kaidun.pro.R;
import com.kaidun.pro.WriteMsgActivity;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.BookDetailActivity;
import com.kaidun.pro.notebook.JustifyTextView;
import com.kaidun.pro.notebook.OptionUtil;
import com.kaidun.pro.notebook.PicActivity;
import com.kaidun.pro.notebook.bean.FamContact;
import com.kaidun.pro.notebook.bean.FamContent;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;
import com.kaidun.pro.utils.KDUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * @author Yunr
 * @date 2018/01/23 15:36
 */
public class FamContentHolder extends ZKViewHolder {

    @BindView(R.id.fam_flower)
    TextView mFamFlower;
    @BindView(R.id.fam_message)
    TextView mFamMessage;
    @BindView(R.id.fam_icon)
    SimpleDraweeView mFamIcon;
    @BindView(R.id.fam_name)
    TextView mFamName;
    @BindView(R.id.fam_kcmb)
    ImageView mFamKcmb;
    @BindView(R.id.fam_unit)
    JustifyTextView mFamUnit;
    @BindView(R.id.fam_option)
    LinearLayout mFamOption;
    @BindView(R.id.fam_text)
    TextView mFamText;
    @BindView(R.id.hour_num)
    TextView mHourNumText;
    @BindView(R.id.fam_unit_rate)
    LinearLayout mFamUnitRate;
    @BindView(R.id.fl_listen_schedule)
    FrameLayout mFlListenSchedule;
    @BindView(R.id.fl_listen)
    FrameLayout mFlListen;
    @BindView(R.id.tv_listen_percentage)
    TextView mTvListenPercentage;
    @BindView(R.id.fl_speak_schedule)
    FrameLayout mFlSpeakSchedule;
    @BindView(R.id.tv_speak_percentage)
    TextView mTvSpeakPercentage;
    @BindView(R.id.fl_read_schedule)
    FrameLayout mFlReadSchedule;
    @BindView(R.id.tv_read_percentage)
    TextView mTvReadPercentage;
    @BindView(R.id.fl_write_schedule)
    FrameLayout mFlWriteSchedule;
    @BindView(R.id.tv_write_percentage)
    TextView mTvWritePercentage;
    @BindView(R.id.fam_date)
    TextView mFamDate;

    @BindView(R.id.fam_first_gone)
    View famFirstGone;

    private static double sScheduleLength = 0;
    private FamContent.ResultBean.FamilyContactListBean famContent;
    private String name;//学员名称
    private String headImg;//学院头像地址
    private FamContact famBookData;

    public FamContentHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        LoginBean.DataBean userBean = KDAccountManager.getInstance().getUserInfoBean();
        if (!TextUtils.isEmpty(userBean.getStuName())) {
            name = userBean.getStuName();
        } else {
            name = "";
        }

        headImg = userBean.getStuHeadImg();
    }

    public void setData(FamContent.ResultBean.FamilyContactListBean famContent) {
        this.famContent = famContent;
        if (getLayoutPosition() == 0) {
            famFirstGone.setBackgroundColor(Color.WHITE);
        } else {
            famFirstGone.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

        mFamName.setText(name);

        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        mFamIcon.getHierarchy().setRoundingParams(roundingParams);
        if (!TextUtils.isEmpty(headImg)) {
            mFamIcon.setImageURI(KDAccountManager.getInstance().getUserInfoBean().getStuHeadImg());
        } else {
            mFamIcon.setImageURI(KDAccountManager.getInstance().getUserInfoBean().getStuHeadImg());
        }

        mHourNumText.setText(famContent.getHourNum() + "");//课时
        mFamUnit.setText(famContent.getCourseSortName());//课程进度
        addStar(famContent.getStart());//学员表现
        mFamText.setText(famContent.getText());//老师评语
        addUnitRate(famContent.getOption());//小测验
        setPercentage(mFlListenSchedule, calculatePercentage(famContent.getListingRate()), mTvListenPercentage);
        setPercentage(mFlSpeakSchedule, calculatePercentage(famContent.getSpeakingRate()), mTvSpeakPercentage);
        setPercentage(mFlReadSchedule, calculatePercentage(famContent.getReadingRate()), mTvReadPercentage);
        setPercentage(mFlWriteSchedule, calculatePercentage(famContent.getWritingRate()), mTvWritePercentage);
        mFamDate.setText(famContent.getKwcmSendTime());//时间
    }

    /**
     * 添加小测验徽章
     */
    private void addUnitRate(String option) {
        mFamUnitRate.removeAllViews();
        int count = OptionUtil.check(option);
        for (int i = 0; i < count; i++) {
            View view = LayoutInflater.from(mFamUnitRate.getContext()).inflate(R.layout.item_unit_rate, mFamUnitRate, false);
            mFamUnitRate.addView(view);
        }
    }

    /**
     * 添加星星数
     *
     * @param start
     */
    private void addStar(int start) {
        mFamOption.removeAllViews();
        for (int i = 0; i < start; i++) {
            View view = LayoutInflater.from(mFamOption.getContext()).inflate(R.layout.item_star, mFamOption, false);
            mFamOption.addView(view);
        }
    }

    @OnClick({R.id.fam_flower, R.id.fam_message, R.id.fam_kcmb, R.id.fam_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fam_flower:
                sendFolwer();
                break;
            case R.id.fam_message:
                Intent intent = new Intent(view.getContext(), WriteMsgActivity.class);
                intent.putExtra(Constant.CLASS_ID, famContent.getClassId());
                intent.putExtra(Constant.CLASS_Name, famContent.getClassName());
                view.getContext().startActivity(intent);
                break;
            case R.id.fam_kcmb:
                if (famContent != null) {
                    Intent toDetailIntent = new Intent(view.getContext(), BookDetailActivity.class);
                    toDetailIntent.putExtra("ccId", famContent.getCcId());
                    toDetailIntent.putExtra("courseSortId", famBookData.getCourseSortId());
                    //测试数据
//                    toDetailIntent.putExtra("ccId", "52F4DE182D9DF774E0530B01000A1F5A");
//                    toDetailIntent.putExtra("courseSortId", "40051078-ce55-45ce-95a3-67aaca1796aa");
                    toDetailIntent.putExtra("bookName", famContent.getCourseSortName());
                    toDetailIntent.putExtra("bookImg", famBookData.getCsUrl());
                    view.getContext().startActivity(toDetailIntent);
                }
                break;
            case R.id.fam_pic:
                Intent picIntent = new Intent(view.getContext(), PicActivity.class);
                picIntent.putExtra("ccId", famContent.getCcId());
                picIntent.putExtra("classId", famContent.getClassId());
                view.getContext().startActivity(picIntent);
                break;
        }
    }

    private void sendFolwer() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ccId", famContent.getCcId());
            KDConnectionManager.getInstance().getZHApi().sendFolwer(
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new KDCallback<String>() {

                        @Override
                        public void onResponse(KDBaseBean<String> baseBean, String result) {
                            if (baseBean.getStatusCode() == 100)
                                ToastUtils.showShort("送花成功");
                            else
                                KDUtils.showErrorToast();
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            ToastUtils.showShort("服务器异常");
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calculatePercentage(String rate) {
        String[] num = rate.split("%");
        if (num == null || num.length == 0) {
            return 0.00;
        }

        double percentage = Double.valueOf(num[0]);
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
        //防止数据超过100%时出现进度条超过布局宽度的问题
        if (progress > 1) {
            progress = 1;
        }

        params.width = (int) (sScheduleLength * progress);
        layout.setLayoutParams(params);
    }

    public void setFamBookData(FamContact famBookData) {
        this.famBookData = famBookData;
    }
}
