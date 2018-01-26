package com.kaidun.pro.notebook.adapter;

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
        textView.setText(resultBean.getCourseSortName());

        if (resultBean.getCourseSortName().equals("暑托班")) {
            imageView.setImageResource(R.drawable.sc_notebook);
        } else if (resultBean.getCourseSortName().equals("ABC")) {
            imageView.setImageResource(R.drawable.abc_notebook);
        } else if (resultBean.getCourseSortName().equals("LA")) {
            imageView.setImageResource(R.drawable.la_notebook);
        } else {
            imageView.setImageResource(R.drawable.other_notebook);
        }
    }
}
