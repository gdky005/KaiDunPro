package com.kaidun.pro.notebook.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaidun.pro.R;
import com.kaidun.pro.notebook.bean.FamContact;

import butterknife.BindView;
import butterknife.ButterKnife;
import team.zhuoke.sdk.component.ZKViewHolder;

/**
 * @author Yunr
 * @date 2018/01/23 13:20
 */
public class NoteBookHolder extends ZKViewHolder {

    @BindView(R.id.note_book_item_img)
    ImageView imageView;
    @BindView(R.id.note_book_item_text)
    TextView textView;

    public NoteBookHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setData(FamContact resultBean) {
        if (resultBean != null) {
            String name = resultBean.getCourseSortName();
            if (!TextUtils.isEmpty(name)) {
                textView.setText(name);
                if (name.equals("暑托班")) {
                    imageView.setImageResource(R.drawable.sc_notebook);
                } else if (name.equals("ABC")) {
                    imageView.setImageResource(R.drawable.abc_notebook);
                } else if (name.equals("LA")) {
                    imageView.setImageResource(R.drawable.la_notebook);
                } else {
                    imageView.setImageResource(R.drawable.other_notebook);
                }
            }
        }
    }
}
