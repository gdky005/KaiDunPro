package com.kaidun.pro.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.kaidun.pro.Constant;
import com.kaidun.pro.MesDetailActivity;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.ReadAndUnReadBean;
import com.kaidun.pro.managers.KDAccountManager;

import java.util.List;

import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;


public class MessageAdapter extends ZKAdapter<ReadAndUnReadBean.ResultBean, ZKViewHolder> {


    private List<ReadAndUnReadBean.ResultBean> mDatas;

    public MessageAdapter(List<ReadAndUnReadBean.ResultBean> mDatas, int resId) {
        super(resId, mDatas);
        // int resId = FLAG == UNREAD ? R.layout.item_msg_unread : R.layout.item_msg_read;
        this.mDatas = mDatas;
    }

    public interface onSwipeListener {
        void onDel(int pos);

        void onTop(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    private String getData(String time){
        if (!TextUtils.isEmpty(time)){
            String[] split = time.split(" ");
            if (split.length > 0){
                return split[0];
            }
        }
        return "";
    }

    @Override
    protected void convert(ZKViewHolder helper, ReadAndUnReadBean.ResultBean item) {

       if (item != null ) {
           helper.setText(R.id.tv_parents_name, item.getKfmMsgTitle());
           helper.setText(R.id.tv_recommended_date, getData(item.getKfmMsgTime()));
           helper.setText(R.id.replay_latest, "回复："+ KDAccountManager.getInstance().getUserInfoBean().getStuName());
           helper.setText(R.id.tv_recommended_content, item.getKfmMsgText());


           View btnDelete = helper.getView(R.id.btnDelete);
           if (btnDelete != null) {
               btnDelete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if (null != mOnSwipeListener) {
                           mOnSwipeListener.onDel(helper.getAdapterPosition());
                       }
                   }
               });
           }

           (helper.getView(R.id.content)).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(mContext, MesDetailActivity.class);
                   intent.putExtra(Constant.KEY_ID, item.getKfmId());
                   mContext.startActivity(intent);
               }
           });

           if (mLayoutResId == R.layout.item_msg_unread) {
               helper.getView(R.id.xxtx_msg).setVisibility(View.VISIBLE);
           } else {
               helper.getView(R.id.xxtx_msg).setVisibility(View.INVISIBLE);
           }

           int adapterPosition = helper.getAdapterPosition();
           if ("N".equals(item.getIsRemove())){
               helper.getView(R.id.btnDelete).setVisibility(View.GONE);
           }else {
               helper.getView(R.id.btnDelete).setVisibility(View.VISIBLE);
           }
       }

    }
}



