package com.kaidun.pro.notebook;

import android.content.Intent;

import com.kaidun.pro.R;
import com.kaidun.pro.notebook.adapter.FamContentAdapter;
import com.kaidun.pro.notebook.bean.FamContent;
import com.kaidun.pro.notebook.bean.FamilyContact;

import java.util.ArrayList;

import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * @author Yunr
 * @date 2018/01/23 15:26
 */
public class NoteBookActivity extends BaseActivity {

    ZKRecycleView mNoteBookList;

    private FamilyContact.ResultBean resultBean;
    private FamContentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_book;
    }

    @Override
    protected void initViews() {
        mNoteBookList = findViewById(R.id.note_book_list);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        resultBean = (FamilyContact.ResultBean) intent.getSerializableExtra("book");
        mTitle.setText(resultBean.getBookName() + "家联本内");


        ArrayList<FamContent> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new FamContent());
        }

        //TODO:请求数据
        adapter = new FamContentAdapter(R.layout.item_fam_content, list);
        mNoteBookList.setAdapter(adapter);
        adapter.setUpFetchEnable(true);
    }
}
