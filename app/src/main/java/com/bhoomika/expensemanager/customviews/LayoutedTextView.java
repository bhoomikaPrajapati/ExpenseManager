package com.bhoomika.expensemanager.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by imobdev on 9/1/17.
 */

public class LayoutedTextView extends android.support.v7.widget.AppCompatTextView {

    public LayoutedTextView(Context context) {
        super(context);
    }

    public LayoutedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface OnLayoutListener {
        void onLayouted(TextView view);
    }

    private OnLayoutListener mOnLayoutListener;

    public void setOnLayoutListener(OnLayoutListener listener) {
        mOnLayoutListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (mOnLayoutListener != null) {
            mOnLayoutListener.onLayouted(this);
        }
    }

}
