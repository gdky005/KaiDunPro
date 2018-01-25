package com.kaidun.pro.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaidun.pro.R;
import com.kaidun.pro.managers.KDConnectionManager;
import com.kaidun.pro.notebook.adapter.NoteBookAdapter;
import com.kaidun.pro.notebook.bean.FamilyContact;
import com.kaidun.pro.utils.KDRequestUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        ArrayList<FamilyContact.ResultBean> list = new ArrayList<>();
        FamilyContact.ResultBean resultBean = new FamilyContact.ResultBean();
        resultBean.setBookName("ABC");
        list.add(resultBean);

        FamilyContact.ResultBean resultBean2 = new FamilyContact.ResultBean();
        resultBean2.setBookName("暑托班");
        list.add(resultBean2);

        FamilyContact.ResultBean resultBean3 = new FamilyContact.ResultBean();
        resultBean3.setBookName("LA");
        list.add(resultBean3);

        FamilyContact.ResultBean resultBean4 = new FamilyContact.ResultBean();
        resultBean4.setBookName("敬请期待...");
        list.add(resultBean4);


        //TODO:请求数据
        // selectFamilyContact();

        showList(list);
    }

    private void showList(ArrayList<FamilyContact.ResultBean> list) {
        adapter = new NoteBookAdapter(R.layout.item_note_book, list);
        mNoteBookGrid.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FamilyContact.ResultBean resultBean = (FamilyContact.ResultBean) adapter.getItem(position);
                //TODO:跳转到家联本详情页
                Intent intent = new Intent(getContext(), NoteBookActivity.class);
                intent.putExtra("book", resultBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void selectFamilyContact() {
        try {
            KDConnectionManager.getInstance().getZHApi().selectFamilyContact(KDRequestUtils.getRequestBody())
                    .enqueue(new Callback<FamilyContact>() {
                        @Override
                        public void onResponse(Call<FamilyContact> call, Response<FamilyContact> response) {
                            //showList(list);
                        }

                        @Override
                        public void onFailure(Call<FamilyContact> call, Throwable t) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
