package com.kaidun.pro.notebook;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaidun.pro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.base.BaseActivity;

/**
 * @author Yunr
 * @date 2018/01/24 15:03
 */
public class BookDetailActivity extends BaseActivity {

    @BindView(R.id.detail_book_img)
    ImageView mDetailBookImg;
    @BindView(R.id.detail_book_name)
    TextView mDetailBookName;
    @BindView(R.id.detail_unit)
    TextView mDetailUnit;
    @BindView(R.id.detail_msg_group)
    LinearLayout mDetailMsgGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        //TODO:拿来数据
        for (int i = 0; i < 10; i++) {
            View msgView = getLayoutInflater().inflate(R.layout.item_book_detail_msg,
                    mDetailMsgGroup, false);
            TextView titleView = msgView.findViewById(R.id.book_detail_msg_title);
            TextView descView = msgView.findViewById(R.id.book_detail_msg_desc);
            View lineView = msgView.findViewById(R.id.book_detail_msg_line);

            titleView.setText("标题标题标题" + i);
            descView.setText("1. 内容内容内容，可使用换行符\n2. 内容内容");
            if (i == 10 - 1) {
                lineView.setVisibility(View.GONE);
            }

            mDetailMsgGroup.addView(msgView);
        }
    }
}
