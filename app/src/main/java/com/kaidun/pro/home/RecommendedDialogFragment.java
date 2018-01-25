package com.kaidun.pro.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ToastUtils;
import com.kaidun.pro.R;
import com.kaidun.pro.api.KDApi;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by admin on 2016/11/21.
 */

public class RecommendedDialogFragment extends BaseDialogFragment {
    @BindView(R.id.et_input_number)
    EditText mInputNumber;
    @BindView(R.id.et_input_parents_name)
    EditText mInputParentsName;
    @BindView(R.id.et_input_child_name)
    EditText mInputChildName;
    @BindView(R.id.iv_confirm)
    ImageView mConfirm;
    @BindView(R.id.ll_close_dialog)
    LinearLayout mCloseDialogLayout;
    @BindView(R.id.iv_close_dialog)
    ImageView mCloseDialog;
    @BindView(R.id.pb_loading)
    ProgressBar mLoading;

    public static RecommendedDialogFragment newInstance() {

        Bundle args = new Bundle();

        RecommendedDialogFragment fragment = new RecommendedDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initView() {
        mCloseDialog.setOnClickListener(v -> dismiss());
        mCloseDialogLayout.setOnClickListener(v -> dismiss());
        mConfirm.setOnClickListener(v -> {
            mLoading.setVisibility(View.VISIBLE);
            if (dataVerification()) {
                try {
                    recommend();
                } catch (JSONException e) {
                    mLoading.setVisibility(View.GONE);
                }
            } else {
                mLoading.setVisibility(View.GONE);
            }
        });
    }

    private void recommend() throws JSONException {
        KDApi kdApi = KDConnectionManager.getInstance().getZHApi();
        kdApi.recommend(KDRequestUtils.getHeaderMaps(), recommendInfo()).enqueue(new Callback<KDBaseBean>() {
            @Override
            public void onResponse(Call<KDBaseBean> call, Response<KDBaseBean> response) {
                if (response.body() != null) {
                    if (!(response.body().getStatusCode() == 100)) {
                        showToast(response.body().getMessage());
                    } else {
                        cleanInputAndDismiss();
                        showToast("推荐成功");
                    }
                }
                mLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<KDBaseBean> call, Throwable t) {
                mLoading.setVisibility(View.GONE);
            }
        });
    }

    private void cleanInputAndDismiss() {
        mInputNumber.setText("");
        mInputParentsName.setText("");
        mInputChildName.setText("");
        dismiss();
    }

    private RequestBody recommendInfo() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userCode", KDAccountManager.getInstance().getUserCode());
        jsonObject.put("areaCode", KDAccountManager.getInstance().getAreaCode());
        jsonObject.put("cellPhoneNumber", mInputNumber.getText().toString());
        jsonObject.put("familyName", mInputParentsName.getText().toString());
        jsonObject.put("childName", mInputChildName.getText().toString());
        return KDRequestUtils.getRequestBody(jsonObject);
    }

    private boolean dataVerification() {
        return !(checkEditTextIsEmpty(mInputNumber, "请输入手机号...") ||
                checkEditTextIsEmpty(mInputParentsName, "请输入家长姓名...") ||
                checkEditTextIsEmpty(mInputChildName, "请输入学生姓名..."));
    }

    private boolean checkEditTextIsEmpty(EditText editText, String msg) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            showToast(msg);
            return true;
        }
        return false;
    }

    private void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    protected int getDialogLayoutId() {
        return R.layout.fragment_recommended_dialog;
    }
}
