package com.example.mvvmapplication.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.example.mvvmapplication.R;

/**
 * 类说明
 *
 * @author renjialiang
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 */
public class VerticalDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    private Paint mPaint;

    public VerticalDecoration(Context context) {
        mSpacing = context.getResources().getDimensionPixelSize(R.dimen.vertical_spacing);
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context, R.color.color_divider));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.save();
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final int bottom = parent.getChildAt(i).getBottom();
            final int top = bottom - mSpacing;
            c.drawRect(left, top, right, bottom, mPaint);
        }
        c.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.set(0, 0, 0, mSpacing);
    }
}
