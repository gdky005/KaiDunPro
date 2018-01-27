package com.kaidun.pro.notebook;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.bean.BookDetail;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

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
    private String ccId;
    private String courseSortId;
    private String bookName;
    private String unitName;
    private String bookImg;

    private String[] titles = {"课程目标", "学习常用句型", "Phonice", "Vocabulary", "Sentence"};
    private String[] desc = new String[titles.length];

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        mTitle.setText("课程目标");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent toDetailIntent = getIntent();
        ccId = toDetailIntent.getStringExtra("ccId");
        courseSortId = toDetailIntent.getStringExtra("courseSortId");
        bookName = toDetailIntent.getStringExtra("bookName");
        unitName = toDetailIntent.getStringExtra("unitName");
        bookImg = toDetailIntent.getStringExtra("bookImg");

        Picasso.with(mContext).load(bookImg).into(mDetailBookImg);
        mDetailBookName.setText(bookName);
        mDetailUnit.setText(unitName);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ccId", ccId);
            jsonObject.put("courseSortId", courseSortId);
            KDConnectionManager.getInstance().getZHApi()
                    .selectCourseObject(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new KDCallback<BookDetail>() {
                        @Override
                        public void onResponse(KDBaseBean<BookDetail> baseBean, BookDetail result) {
                            showData(result);
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showData(BookDetail result) {
        desc[0] = result.getCourseObjective();
        desc[1] = result.getCourseSentencePattern();
        desc[2] = result.getCourserPhonice();
        desc[3] = result.getCourseVocabulary();
        desc[4] = result.getCourseSentence();

        for (int i = 0; i < titles.length; i++) {
            View msgView = getLayoutInflater().inflate(R.layout.item_book_detail_msg,
                    mDetailMsgGroup, false);
            TextView titleView = msgView.findViewById(R.id.book_detail_msg_title);
            TextView descView = msgView.findViewById(R.id.book_detail_msg_desc);
            View lineView = msgView.findViewById(R.id.book_detail_msg_line);

            titleView.setText(titles[i]);
            descView.setText(desc[i]);
            if (i == 10 - 1) {
                lineView.setVisibility(View.GONE);
            }

            mDetailMsgGroup.addView(msgView);
        }
    }
}
