package com.kaidun.pro.notebook;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.bean.BookDetail;
import com.kaidun.pro.utils.KDRequestUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Yunr
 * @date 2018/01/24 15:03
 */
public class BookDetailActivity extends KDBaseActivity {

    @BindView(R.id.detail_book_img)
    ImageView mDetailBookImg;
    @BindView(R.id.detail_book_name)
    TextView mDetailBookName;
    @BindView(R.id.detail_unit)
    TextView mDetailUnit;
    @BindView(R.id.detail_msg_group)
    LinearLayout mDetailMsgGroup;
    @BindView(R.id.book_detail_objective)
    TextView mBookDetailObjective;
    @BindView(R.id.book_detail_sentence)
    TextView mBookDetailSentence;
    @BindView(R.id.book_detail_vocabulary)
    TextView mBookDetailVocabulary;
    @BindView(R.id.book_detail_phonice)
    TextView mBookDetailPhonice;
    private String ccId;
    private String courseSortId;
    private String bookName;
    //private String unitName;
    private String bookImg;

    private String[] titles = {"课程目标", "Sentence", "Vocabulary", "Phonice"};

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
        //unitName = toDetailIntent.getStringExtra("unitName");
        bookImg = toDetailIntent.getStringExtra("bookImg");

        Picasso.with(mContext).load(bookImg).into(mDetailBookImg);
        mDetailBookName.setText(bookName);
        //mDetailUnit.setText(unitName);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ccId", ccId);
            jsonObject.put("courseSortId", courseSortId);
            KDConnectionManager.getInstance().getZHApi()
                    .selectCourseObject(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<BookDetail>() {

                        @Override
                        public void onResponse(Call<BookDetail> call, Response<BookDetail> response) {
                            if (response.isSuccessful() && response.body().getStatusCode() == 100) {
                                showData(response.body().getResult());
                            }
                        }

                        @Override
                        public void onFailure(Call<BookDetail> call, Throwable t) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showData(BookDetail.ResultBean bookDetail) {
        mBookDetailObjective.setText(bookDetail.getCourseObject());

        StringBuilder sentence = new StringBuilder();
        StringBuilder vocabulary = new StringBuilder();
        StringBuilder phonice = new StringBuilder();

        for (int i = 0; i < bookDetail.getCourseObjectList().size(); i++) {
            BookDetail.ResultBean.CourseObjectListBean listBean = bookDetail.getCourseObjectList().get(i);
            sentence.append(listBean.getCourseSentencePattern()).append("\n");
            vocabulary.append(listBean.getCourseVocabulary()).append(",");
            phonice.append(listBean.getCourserPhonice()).append(",");
        }

        if (sentence.length() > 0) {
            sentence.deleteCharAt(sentence.length() - 1);
            mBookDetailSentence.setText(sentence);
        }

        if (vocabulary.length() > 0) {
            vocabulary.deleteCharAt(vocabulary.length() - 1);
            mBookDetailVocabulary.setText(vocabulary);
        }

        if (phonice.length() > 0) {
            phonice.deleteCharAt(phonice.length() - 1);
            mBookDetailPhonice.setText(phonice);
        }
    }

//    private void showData(BookDetail result) {
//        for (int i = 0; i < titles.length; i++) {
//            View msgView = getLayoutInflater().inflate(R.layout.item_book_detail_msg,
//                    mDetailMsgGroup, false);
//            TextView titleView = msgView.findViewById(R.id.book_detail_msg_title);
//            TextView descView = msgView.findViewById(R.id.book_detail_msg_desc);
//            View lineView = msgView.findViewById(R.id.book_detail_msg_line);
//
//            titleView.setText(titles[i]);
//            descView.setText(desc[i]);
//            if (i == 10 - 1) {
//                lineView.setVisibility(View.GONE);
//            }
//
//            mDetailMsgGroup.addView(msgView);
//        }
//    }

}
