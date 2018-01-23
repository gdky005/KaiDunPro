package com.kaidun.pro.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kaidun.pro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


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
    }

    @Override
    protected int getDialogLayoutId() {
        return R.layout.fragment_recommended_dialog;
    }
}
