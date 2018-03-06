package com.kaidun.pro.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
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


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.line_bottom)
    View lineBottom;
    @BindView(R.id.detail_book_img)
    ImageView detailBookImg;
    @BindView(R.id.detail_book_name)
    TextView detailBookName;
    @BindView(R.id.detail_unit)
    TextView detailUnit;
    @BindView(R.id.book_detail_msg_title)
    TextView bookDetailMsgTitle;
    @BindView(R.id.book_detail_objective)
    TextView bookDetailObjective;
    @BindView(R.id.book_detail_study_sentence_title)
    TextView bookDetailStudySentenceTitle;
    @BindView(R.id.book_detail_study_sentence)
    TextView bookDetailStudySentence;
    @BindView(R.id.book_detail_phonics_title)
    TextView bookDetailPhonicsTitle;
    @BindView(R.id.book_detail_phonics)
    TextView bookDetailPhonics;
    @BindView(R.id.book_detail_vocabulary_title)
    TextView bookDetailVocabularyTitle;
    @BindView(R.id.book_detail_vocabulary)
    TextView bookDetailVocabulary;
    @BindView(R.id.book_detail_sentence_title)
    TextView bookDetailSentenceTitle;
    @BindView(R.id.book_detail_sentence)
    TextView bookDetailSentence;
    @BindView(R.id.detail_msg_group)
    LinearLayout detailMsgGroup;
    private String processId;
    private String courseSortId;
    private String bookName;
    private String unitName;
    private String bookImg;

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
        processId = toDetailIntent.getStringExtra("processId");
        courseSortId = toDetailIntent.getStringExtra("courseSortId");
        bookName = toDetailIntent.getStringExtra("bookName");
        unitName = toDetailIntent.getStringExtra("unitName");
        bookImg = toDetailIntent.getStringExtra("bookImg");

        Picasso.with(mContext).load(bookImg).into(detailBookImg);
        detailBookName.setText(bookName);
        detailUnit.setText(unitName);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("processId", processId);
            jsonObject.put("courseSortId", courseSortId);
            KDConnectionManager.getInstance().getZHApi()
                    .selectCourseObject(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<BookDetail>() {

                        @Override
                        public void onResponse(Call<BookDetail> call, Response<BookDetail> response) {
                            if (response.isSuccessful() && response.body().getStatusCode() == 100) {
                                showData(response.body().getResult());
                            } else {
                                onFailure(call, new Throwable("服务器异常"));
                            }
                        }

                        @Override
                        public void onFailure(Call<BookDetail> call, Throwable t) {
                            ToastUtils.showShort("服务器异常");
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showData(BookDetail.ResultBean bookDetail) {
        bookDetailObjective.setText(bookDetail.getCourseObject());

        StringBuilder sentence = new StringBuilder();
        StringBuilder vocabulary = new StringBuilder();
        StringBuilder phonice = new StringBuilder();


        BookDetail.ResultBean.CourseObjectListBean courseObjectListBean = bookDetail.getCourseObjectList();

        if (courseObjectListBean != null) {
            for (String sentenceStr : courseObjectListBean.getSentenceList()) {
                sentence.append(sentenceStr).append("\n");
            }

            for (String vocabularyStr : courseObjectListBean.getVocabularyList()) {
                vocabulary.append(vocabularyStr).append(",");
            }

            for (String phoniceStr : courseObjectListBean.getPhoniceList()) {
                phonice.append(phoniceStr).append(",");
            }
        }

        if (sentence.length() > 0) {
            sentence.deleteCharAt(sentence.length() - 1);
            bookDetailSentence.setText(sentence);
            bookDetailStudySentence.setText(sentence);
        }

        if (vocabulary.length() > 0) {
            vocabulary.deleteCharAt(vocabulary.length() - 1);
            bookDetailVocabulary.setText(vocabulary);
        }

        if (phonice.length() > 0) {
            phonice.deleteCharAt(phonice.length() - 1);
            bookDetailPhonics.setText(phonice);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

}
