package com.kaidun.pro.notebook.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.WriteMsgActivity;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.BookDetailActivity;
import com.kaidun.pro.notebook.bean.FamContent;
import com.kaidun.pro.notebook.bean.MsgBean;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    ImageView mFamIcon;
    @BindView(R.id.fam_name)
    TextView mFamName;
    @BindView(R.id.fam_name2)
    TextView mFamName2;
    @BindView(R.id.fam_kcmb)
    ImageView mFamKcmb;
    @BindView(R.id.fam_unit)
    TextView mFamUnit;
    @BindView(R.id.fam_option)
    LinearLayout mFamOption;
    @BindView(R.id.fam_text)
    TextView mFamText;
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

    private FamContent data;

    public FamContentHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setData(FamContent famContent) {
        this.data = famContent;
        mFamUnit.setText(famContent.getBookName());
        addStar(famContent.getStart());
        mFamText.setText(famContent.getText());
        addUnitRate(famContent.getUnitCode());
        setPercentage(mFlListenSchedule, famContent.getListeningRate(), mTvListenPercentage);
        setPercentage(mFlSpeakSchedule, famContent.getSpeakingRate(), mTvSpeakPercentage);
        setPercentage(mFlReadSchedule, famContent.getReadingRate(), mTvReadPercentage);
        setPercentage(mFlWriteSchedule, famContent.getWritingRate(), mTvWritePercentage);
        mFamDate.setText(famContent.getPractiseTime());
    }


    /**
     * 添加小测验徽章
     *
     * @param unitCode
     */
    private void addUnitRate(int unitCode) {

        mFamUnitRate.removeAllViews();
        for (int i = 0; i < unitCode; i++) {
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

    @OnClick({R.id.fam_flower, R.id.fam_message, R.id.fam_kcmb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fam_flower:
                //TODO:送花
                sendFolwer();
                break;
            case R.id.fam_message:
                //TODO:给班级留言
                //待定
                view.getContext().startActivity(new Intent(view.getContext(), WriteMsgActivity.class));
                break;
            case R.id.fam_kcmb:
                //TODO:跳课程目标，要带参数
                view.getContext().startActivity(new Intent(view.getContext(), BookDetailActivity.class));
                break;
        }
    }

    private void sendFolwer() {
//        areaCode（地区编码）	1001
//        userCode（用户码）	10009010
//        ccId（课表id）
        String ccId = "4B93B97398216E08E0531064410ABCF4";
        int areaCode = 1001;
        int userCode = 10009010;

        try {
            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
//            jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
            jsonObject.put("userCode", userCode);
            jsonObject.put("areaCode", areaCode);
            jsonObject.put("ccId", ccId);
            KDConnectionManager.getInstance().getZHApi().sendFolwer(
                    KDRequestUtils.getHeaderMaps(),
                    KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<MsgBean>() {
                        @Override
                        public void onResponse(Call<MsgBean> call, Response<MsgBean> response) {
                            //showList(list);
                        }

                        @Override
                        public void onFailure(Call<MsgBean> call, Throwable t) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @SuppressLint("SetTextI18n")
    private void setPercentage(final FrameLayout layout, double progress, TextView percentage) {
        layout.post(() -> realSetPercentage(layout, progress));
        percentage.setText((int) (progress * 100) + "%");
    }

    private void realSetPercentage(FrameLayout layout, double progress) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout.getLayoutParams();
        layout.measure(0, 0);
        params.width = (int) (layout.getWidth() * progress);
        layout.setLayoutParams(params);
    }
}
