package com.kaidun.pro.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.R;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.LoginBean;
import com.kaidun.pro.home.adapter.HomeAdapter;
import com.kaidun.pro.home.bean.Home;
import com.kaidun.pro.home.bean.SchoolNotification;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

public class HomeFragment extends BaseFragment implements KDAccountManager.LoginFinish {
    @BindView(R.id.tv_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
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

    @BindView(R.id.rl_home_layout)
    RecyclerView mHomeLayout;

    private HomeAdapter mAdapter;
    private ArrayList<Home> mHomes = new ArrayList<>();

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
        mToolbarTitle.setText("主页");
        mParentsName.setText("Durian_");
        mParentsNick.setText("Jaaaelu");
        mHomeLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeAdapter(R.layout.item_home, mHomes);
        mHomeLayout.setAdapter(mAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bg_line));
        mHomeLayout.addItemDecoration(divider);
    }

    @Override
    public void initData(Bundle bundle) {
        Home homeHeader = new Home("          下次一ABC课程，我们进行考试，及成果展示，请各位家长带好xxx相关的东西，并准时参加成果展示课程。",
                "2017年9月6日 by Zola");

        Home home = new Home(null, "ABC 1-9", 1,
                0.8, 0.5, 0.3,
                "          下次一ABC课程，我们进行考试，及成果展示，请各位家长带好xxx相关的东西，并准时参加成果展示课程。",
                "2017年9月6日 by Zola");
        mHomes.clear();
        mHomes.add(homeHeader);
        mHomes.add(home);
        mHomes.add(home);
        mHomes.add(home);
        mHomes.add(home);
        mAdapter.notifyDataSetChanged();

        KDAccountManager.getInstance().setLoginFinish(this);
        KDAccountManager.getInstance().login("10007027", "10007027", "1001", "003");
    }

    private void getFamilyInfo() throws Exception {
        KDApi kdApi = KDConnectionManager.getInstance().getZHApi();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userCode", "10007027");
        jsonObject.put("areaCode", "1001");
        kdApi.selectFamilyInfo(KDRequestUtils.getHeaderMaps(),
                KDRequestUtils.getRequestBody(jsonObject)).enqueue(new Callback<SchoolNotification>() {
            @Override
            public void onResponse(Call<SchoolNotification> call, Response<SchoolNotification> response) {
                if (response.errorBody() != null) {
                    try {
                        ToastUtils.showShort(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("TAG", "");
            }

            @Override
            public void onFailure(Call<SchoolNotification> call, Throwable t) {
                t.printStackTrace();
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

    @Override
    public void loginFinish(LoginBean login) {
        KDAccountManager.getInstance().setUserInfoBean(login.getData());
        try {
            getFamilyInfo();
            mParentsName.setText(KDAccountManager.getInstance().getUserInfoBean().getStuName());
            mParentsNick.setText(KDAccountManager.getInstance().getUserInfoBean().getStuName());
            mParentsAvatar.setImageURI(KDAccountManager.getInstance().getUserInfoBean().getStuHeadImg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
