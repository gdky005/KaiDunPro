package com.kaidun.pro.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.MsgDetailBean;
import com.kaidun.pro.managers.KDAccountManager;
import com.kaidun.pro.utils.ImgUtils;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * Created by lmj on 2018/1/23.
 */

public class MsgDetailAdapter extends ZKAdapter<MsgDetailBean.ResultBean,ZKViewHolder>{

    public MsgDetailAdapter(@Nullable List<MsgDetailBean.ResultBean> data) {
        super(data);
    }

    @Override
    protected void convert(ZKViewHolder helper, MsgDetailBean.ResultBean item) {
        if (item != null ){

            SimpleDraweeView mQrAvatar = (SimpleDraweeView) helper.getView(R.id.iv_parents_avatar);
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
            roundingParams.setRoundAsCircle(true);
            mQrAvatar.getHierarchy().setRoundingParams(roundingParams);


            helper.setText(R.id.tv_parents_name, TextUtils.isEmpty(item.getKmdRole()) ? "Alice(英语老师)" : item.getKmdRole());
            helper.setText(R.id.tv_recommended_date, item.getKmdMsgTime());
            helper.setText(R.id.tv_recommended_content, item.getKmdMsgText());

            if (TextUtils.isEmpty(item.getKmdRole())){
                mQrAvatar.setImageURI(ImgUtils.teacher_head);
            }else{
                mQrAvatar.setImageURI(KDAccountManager.getInstance().getUserInfoBean().getStuHeadImg());
            }
        }
    }
}