package com.kaidun.pro.views;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/8/17.
 */

public class RecDividerItemDecoration extends RecyclerView.ItemDecoration {

    private final int mDivideHeight;
    private Paint mPaint;
    //是否需要顶部分割线 Doraemon 2018/2/10
    private boolean isNeedTopDivider = true;

    public RecDividerItemDecoration(int paintColor, int divideHeight) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(paintColor);
        this.mDivideHeight = divideHeight;
    }

    public RecDividerItemDecoration(int paintColor, int divideHeight, boolean isNeedTopDivider) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(paintColor);
        this.mDivideHeight = divideHeight;
        this.isNeedTopDivider = isNeedTopDivider;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // if (parent.getChildAdapterPosition(view)!=0){
        outRect.top = mDivideHeight;
//        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            if (i == 0 && !isNeedTopDivider) continue;

            View child = parent.getChildAt(i);
            int Top = child.getTop() - mDivideHeight;
            int bottom = child.getTop();
            int left = parent.getPaddingLeft() + ((RecyclerView.LayoutParams) child.getLayoutParams()).leftMargin;
            int right = parent.getMeasuredWidth() - parent.getPaddingRight() - ((RecyclerView.LayoutParams) child.getLayoutParams()).rightMargin;
            c.drawRect(left, Top, right, bottom, mPaint);

        }
    }
}
