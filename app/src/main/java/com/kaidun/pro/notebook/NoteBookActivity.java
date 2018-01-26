package com.kaidun.pro.notebook;

import android.content.Intent;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * @author Yunr
 * @date 2018/01/23 15:26
 */
public class NoteBookActivity extends BaseActivity {

    ZKRecycleView mNoteBookList;

    private FamContact famContact;
    private FamContentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_book;
    }

    @Override
    protected void initViews() {
        mNoteBookList = findViewById(R.id.note_book_list);
        mTitle.setText("家联本内");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        famContact = (FamContact) intent.getSerializableExtra("book");
//
//        ArrayList<FamContent> list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            FamContent famContent = new FamContent();
//            famContent.setBookName("ABC" + i);
//            famContent.setStart(3);
//            famContent.setText("我给你讲！写不完作业就别想回家！！！");
//            famContent.setUnitCode(4);
//            famContent.setListeningRate(0.72);
//            famContent.setSpeakingRate(0.34);
//            famContent.setReadingRate(0.67);
//            famContent.setWritingRate(0.12);
//            famContent.setPractiseTime("2018-1-" + i);
//            list.add(famContent);
//        }

        //TODO:请求数据
        selectFamContContext();

    }

    private void selectFamContContext() {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("courseSortId", famContact.getCourseSortId());
            KDConnectionManager.getInstance().getZHApi().selectFamContContext(KDRequestUtils.getRequestBody(jsonObject))
                    .enqueue(new KDCallback<List<FamContent>>() {

                        @Override
                        public void onResponse(KDBaseBean<List<FamContent>> baseBean, List<FamContent> result) {
                            adapter = new FamContentAdapter(R.layout.item_fam_content, result);
                            mNoteBookList.setAdapter(adapter);
                            adapter.setUpFetchEnable(true);
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
