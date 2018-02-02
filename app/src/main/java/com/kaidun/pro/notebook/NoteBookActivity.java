package com.kaidun.pro.notebook;

import android.content.Intent;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaidun.pro.R;
import com.kaidun.pro.activity.KDBaseActivity;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.adapter.FamContentAdapter;
import com.kaidun.pro.notebook.bean.FamContact;
import com.kaidun.pro.notebook.bean.FamContent;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * @author Yunr
 * @date 2018/01/23 15:26
 */
public class NoteBookActivity extends KDBaseActivity implements EasyRefreshLayout.EasyEvent, BaseQuickAdapter.RequestLoadMoreListener {

    ZKRecycleView mNoteBookList;

    private FamContact famContact;
    private FamContentAdapter adapter;
    private EasyRefreshLayout mNoteBookRefresh;
    private List<FamContent.ResultBean.FamilyContactListBean> data;

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
        mNoteBookRefresh.setLoadMoreModel(LoadModel.NONE);
        mNoteBookRefresh.setHideLoadViewAnimatorDuration(1000);
        mNoteBookRefresh.setHideLoadViewAnimatorDuration(1000);

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
                    .enqueue(new Callback<FamContent>() {

                        @Override
                        public void onResponse(Call<FamContent> call, Response<FamContent> response) {
                            if (response.isSuccessful() && response.body().getStatusCode() == 100) {
                                data = response.body().getResult().getFamilyContactList();
                                nextPage = response.body().getResult().getSlideCode();
                                adapter = new FamContentAdapter(R.layout.item_fam_content, data, famContact);
                                mNoteBookList.setAdapter(adapter);
                                adapter.setEnableLoadMore(true);
                                adapter.setOnLoadMoreListener(NoteBookActivity.this, mNoteBookList);
                            }
                        }

                        @Override
                        public void onFailure(Call<FamContent> call, Throwable t) {

                        }
                    });

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    int nextPage = 0;

    @Override
    public void onRefreshing() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseSortId", famContact.getCourseSortId());
            jsonObject.put("slideCode", 0);
            KDConnectionManager.getInstance().getZHApi()
                    .selectFamContContext(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<FamContent>() {
                        @Override
                        public void onResponse(Call<FamContent> call, Response<FamContent> response) {
                            if (response.isSuccessful() && response.body().getStatusCode() == 100) {
                                data = response.body().getResult().getFamilyContactList();
                                nextPage = response.body().getResult().getSlideCode();
                                mNoteBookRefresh.refreshComplete();
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<FamContent> call, Throwable t) {
                            mNoteBookRefresh.refreshComplete();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseSortId", famContact.getCourseSortId());
            jsonObject.put("slideCode", nextPage);

            KDConnectionManager.getInstance().getZHApi()
                    .selectFamContContext(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new Callback<FamContent>() {
                        @Override
                        public void onResponse(Call<FamContent> call, Response<FamContent> response) {
                            if (response.isSuccessful() && response.body().getStatusCode() == 100) {
                                List<FamContent.ResultBean.FamilyContactListBean> list =
                                        response.body().getResult().getFamilyContactList();
                                if (list == null || list.size() == 0) {
                                    adapter.loadMoreEnd();
                                    return;
                                }
                                nextPage = response.body().getResult().getSlideCode();
                                adapter.getData().addAll(list);
                                // adapter.notifyDataSetChanged();
                                adapter.loadMoreComplete();
                            }
                        }

                        @Override
                        public void onFailure(Call<FamContent> call, Throwable t) {
                            adapter.loadMoreEnd();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadMore() {

    }
}
