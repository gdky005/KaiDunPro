package com.kaidun.pro.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kaidun.pro.MesDetailActivity;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.SwipeBean;

import java.util.List;


/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：zhangxutong@imcoming.com
 * 时间： 2016/9/12.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.FullDelDemoVH> {
    private Context mContext;
    private LayoutInflater mInfalter;
    private List<SwipeBean> mDatas;
    private int FLAG;
    public static final int READ = 0;
    public static final int UNREAD = 1;

    public MessageAdapter(Context context, List<SwipeBean> mDatas,int FLAG) {
        mContext = context;
        mInfalter = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.FLAG = FLAG;
    }

    @Override
    public FullDelDemoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId = FLAG == UNREAD ? R.layout.item_msg_unread : R.layout.item_msg_read;
        return new FullDelDemoVH(mInfalter.inflate(resId, parent, false));
    }

    @Override
    public void onBindViewHolder(final FullDelDemoVH holder, final int position) {

       // holder.content.setText(mDatas.get(position).name + (position % 2 == 0 ? "我右白虎" : "我左青龙"));

        //验证长按
       /* holder.content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "longclig", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onLongClick() called with: v = [" + v + "]");
                return false;
            }
        });*/

//        holder.btnUnRead.setVisibility(position % 3 == 0 ? View.GONE : View.VISIBLE);
        SwipeBean bean = mDatas.get(position);
        holder.date.setText(bean.date);
        holder.reply.setText(bean.replyContent);
        holder.name.setText(bean.name);
        holder.mainContent.setText(bean.content);

        if (holder.btnDelete != null){
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnSwipeListener) {
                        //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                        //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                        //((CstSwipeDelMenu) holder.itemView).quickClose();
                        mOnSwipeListener.onDel(holder.getAdapterPosition());
                    }
                }
            });
        }
        //注意事项，设置item点击，不能对整个holder.itemView设置咯，只能对第一个子View，即原来的content设置，这算是局限性吧。
        (holder.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,MesDetailActivity.class));
            }
        });
       /* //置顶：
        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnSwipeListener){
                    mOnSwipeListener.onTop(holder.getAdapterPosition());
                }

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    /**
     * 和Activity通信的接口
     */
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

    class FullDelDemoVH extends RecyclerView.ViewHolder {
        TextView reply;
        TextView mainContent;
        TextView date;
        TextView name;
        View content;
        Button btnDelete;
        Button btnUnRead;
        Button btnTop;

        public FullDelDemoVH(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            content.findViewById(R.id.iv_parents_avatar);
            name = ((TextView) content.findViewById(R.id.tv_parents_name));
            reply = ((TextView) content.findViewById(R.id.replay_latest));
            mainContent = ((TextView) content.findViewById(R.id.tv_recommended_content));
            date = ((TextView) content.findViewById(R.id.tv_recommended_date));
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
//            btnUnRead = (Button) itemView.findViewById(R.id.btnUnRead);
//            btnTop = (Button) itemView.findViewById(R.id.btnTop);
        }
    }
}

