package com.kaidun.pro.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.R;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.home.adapter.HomeAdapter;
import com.kaidun.pro.home.bean.CourseInfo;
import com.kaidun.pro.home.bean.Home;
import com.kaidun.pro.home.bean.SchoolNotification;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/22.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.iv_parents_avatar)
    SimpleDraweeView mParentsAvatar;
    @BindView(R.id.tv_parents_name)
    TextView mParentsName;
    @BindView(R.id.tv_parents_nick)
    TextView mParentsNick;
    @BindView(R.id.ll_show_stand_inside_letter)
    LinearLayout mShowStandInsideLetter;
    @BindView(R.id.ll_show_recommended)
    LinearLayout mShowRecommended;
    @BindView(R.id.ll_show_qr)
    LinearLayout mShowQr;
    private SchoolNotification mSchoolNotification;

    @BindView(R.id.rl_home_layout)
    RecyclerView mHomeLayout;

    private HomeAdapter mAdapter;
    private ArrayList<Home> mHomes = new ArrayList<>();
    private KDApi mKdApi = KDConnectionManager.getInstance().getZHApi();

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        mHomeLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeAdapter(R.layout.item_home, mHomes);
        mHomeLayout.setAdapter(mAdapter);
    }

    @Override
    public void initData(Bundle bundle) {
        if (KDAccountManager.getInstance().getUserInfoBean() != null
                && KDAccountManager.getInstance().getUserInfoBean().getStuName() != null) {
            String stuName =  KDAccountManager.getInstance().getUserInfoBean().getStuName();
            if (stuName != null) {
                String[] name = stuName.split("/");
                mParentsName.setText(name[0]);
                if (name.length > 1) {
                    mParentsNick.setText(name[1]);
                }
            }
        } else {
            mParentsName.setText("暂无");
        }

        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        mParentsAvatar.getHierarchy().setRoundingParams(roundingParams);
        mParentsAvatar.setImageURI(KDAccountManager.getInstance().getUserInfoBean().getStuHeadImg());
        try {
            getFamilyInfo();
            getCourseInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getCourseInfo() throws JSONException {
        mKdApi.selectClassCourseInfo(KDRequestUtils.getRequestBody()).enqueue(new Callback<CourseInfo>() {
            @Override
            public void onResponse(Call<CourseInfo> call, Response<CourseInfo> response) {
                if (response.body() != null && response.body().getStatusCode() == 100) {
                    CourseInfo courseInfo = response.body();
                    if (courseInfo.getResult() != null
                            && courseInfo.getResult().getClassCourseInfo() != null) {
                        List<CourseInfo.ResultBean.ClassCourseInfoBean> classCourseInfos
                                = courseInfo.getResult().getClassCourseInfo();
                        mHomes.clear();
                        if (mSchoolNotification != null) {
                            mHomes.add(0, mSchoolNotification);
                        }
                        mHomes.addAll(classCourseInfos);
                        Log.e("TAG", "selectClassCourseInfoFinish");
                        mAdapter.notifyDataSetChanged();
                    }
                } else if (response.body() != null && response.body().getMessage() != null) {
                    ToastUtils.showShort(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CourseInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getFamilyInfo() throws Exception {
        mKdApi.selectFamilyInfo(KDRequestUtils.getRequestBody()).enqueue(new Callback<SchoolNotification>() {
            @Override
            public void onResponse(Call<SchoolNotification> call, Response<SchoolNotification> response) {
                String msg = "请求错误";
                mSchoolNotification = response.body();

                if (mSchoolNotification != null) {
                    if ( mSchoolNotification.getStatusCode() == 100) {
                        SchoolNotification.ResultBean resultBean = mSchoolNotification.getResult();

                        if (resultBean != null) {
                            if (resultBean.getComment() != null) {
                                CourseInfo.ResultBean.ClassCourseInfoBean.comment = resultBean.getComment().getComment();
                                CourseInfo.ResultBean.ClassCourseInfoBean.teacher = resultBean.getComment().getTeacher();
                                CourseInfo.ResultBean.ClassCourseInfoBean.publishTime = resultBean.getComment().getPublishTime();
                            } else {
                                CourseInfo.ResultBean.ClassCourseInfoBean.comment = null;
                                CourseInfo.ResultBean.ClassCourseInfoBean.teacher = null;
                                CourseInfo.ResultBean.ClassCourseInfoBean.publishTime = null;
                            }
                            mHomes.add(0, mSchoolNotification);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                    } else {
                        msg = mSchoolNotification.getMessage();
                    }
                }

                ToastUtils.showShort(msg);
            }

            @Override
            public void onFailure(Call<SchoolNotification> call, Throwable t) {
                t.printStackTrace();
                ToastUtils.showShort(t.getMessage());
            }
        });
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.ll_show_stand_inside_letter, R.id.ll_show_recommended, R.id.ll_show_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_show_stand_inside_letter:
                startActivity(new Intent(getActivity(), StandInsideLetterActivity.class));
                break;
            case R.id.ll_show_recommended:
                RecommendedDialogFragment dialogFragment = RecommendedDialogFragment.newInstance();
                dialogFragment.show(getChildFragmentManager(), "");
                break;
            case R.id.ll_show_qr:
                startActivity(new Intent(getActivity(), QRActivity.class));
                break;
        }
    }
}
