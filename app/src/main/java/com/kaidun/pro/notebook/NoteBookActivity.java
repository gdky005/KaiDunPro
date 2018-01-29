package com.kaidun.pro.notebook;

import android.content.Intent;

import com.ajguan.library.EasyRefreshLayout;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.adapter.FamContentAdapter;
import com.kaidun.pro.notebook.bean.FamContact;
import com.kaidun.pro.notebook.bean.FamContent;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import java.util.List;

import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * @author Yunr
 * @date 2018/01/23 15:26
 */
public class NoteBookActivity extends BaseActivity implements EasyRefreshLayout.EasyEvent {

    ZKRecycleView mNoteBookList;

    private FamContact famContact;
    private FamContentAdapter adapter;
    private EasyRefreshLayout mNoteBookRefresh;
    private List<FamContent> data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_book;
    }

    @Override
    protected void initViews() {
        mNoteBookList = findViewById(R.id.note_book_list);
        mNoteBookRefresh = findViewById(R.id.note_book_refresh);
        mTitle.setText("家联本内");
        mNoteBookRefresh.setEnablePullToRefresh(true);
        mNoteBookRefresh.addEasyEvent(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        famContact = (FamContact) intent.getSerializableExtra("book");
        selectFamContContext();
    }

    private void selectFamContContext() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseSortId", famContact.getCourseSortId());
            jsonObject.put("slideCode", 0);
            KDConnectionManager.getInstance().getZHApi()
                    .selectFamContContext(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new KDCallback<List<FamContent>>() {

                        @Override
                        public void onResponse(KDBaseBean<List<FamContent>> baseBean, List<FamContent> result) {
                            data = result;
                            page = 0;
                            adapter = new FamContentAdapter(R.layout.item_fam_content, result, famContact);
                            mNoteBookList.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int page = 0;

    @Override
    public void onLoadMore() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseSortId", famContact.getCourseSortId());
            jsonObject.put("slideCode", page++);
            KDConnectionManager.getInstance().getZHApi()
                    .selectFamContContext(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new KDCallback<List<FamContent>>() {

                        @Override
                        public void onResponse(KDBaseBean<List<FamContent>> baseBean, List<FamContent> result) {
                            adapter.getData().addAll(result);
                            adapter.notifyDataSetChanged();
                            mNoteBookRefresh.loadMoreComplete();
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefreshing() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseSortId", famContact.getCourseSortId());
            jsonObject.put("slideCode", 0);
            KDConnectionManager.getInstance().getZHApi()
                    .selectFamContContext(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new KDCallback<List<FamContent>>() {

                        @Override
                        public void onResponse(KDBaseBean<List<FamContent>> baseBean, List<FamContent> result) {
                            data = result;
                            page = 0;
                            mNoteBookRefresh.refreshComplete();
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
