package com.kaidun.pro.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaidun.pro.R;
import com.kaidun.pro.bean.KDBaseBean;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.adapter.NoteBookAdapter;
import com.kaidun.pro.notebook.bean.FamContact;
import com.kaidun.pro.retrofit2.KDCallback;
import com.kaidun.pro.utils.KDRequestUtils;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import team.zhuoke.sdk.base.BaseFragment;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * 家联本
 * Created by Yunr
 */
public class NoteBookFragment extends BaseFragment {

    public static final String KEY = "key";

    @BindView(R.id.note_book_grid)
    ZKRecycleView mNoteBookGrid;
    Unbinder unbinder;
    private NoteBookAdapter adapter;

    public static NoteBookFragment newInstance(int index) {
        NoteBookFragment fragment = new NoteBookFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_note_book;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void initData(Bundle bundle1) {
//
//        ArrayList<FamContact> list = new ArrayList<>();
//        FamContact resultBean = new FamContact.ResultBean();
//        resultBean.setBookName("ABC");
//        list.add(resultBean);
//
//        FamContact.ResultBean resultBean2 = new FamContact.ResultBean();
//        resultBean2.setBookName("暑托班");
//        list.add(resultBean2);
//
//        FamContact.ResultBean resultBean3 = new FamContact.ResultBean();
//        resultBean3.setBookName("LA");
//        list.add(resultBean3);
//
//        FamContact.ResultBean resultBean4 = new FamContact.ResultBean();
//        resultBean4.setBookName("敬请期待...");
//        list.add(resultBean4);


        //TODO:请求数据
        selectFamilyContact();
    }

    private void selectFamilyContact() {
        try {
            JSONObject jsonObject = new JSONObject();
            KDConnectionManager.getInstance().getZHApi()
                    .selectFamContact(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new KDCallback<List<FamContact>>() {

                        @Override
                        public void onResponse(KDBaseBean<List<FamContact>> baseBean, List<FamContact> result) {
                            showList(result);
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showList(List<FamContact> list) {
        adapter = new NoteBookAdapter(R.layout.item_note_book, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FamContact famContact = (FamContact) adapter.getItem(position);
                Intent intent = new Intent(getContext(), NoteBookActivity.class);
                intent.putExtra("book", famContact);
                startActivity(intent);
            }
        });
        if (adapter != null && mNoteBookGrid != null) {
            mNoteBookGrid.setAdapter(adapter);
        }
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
