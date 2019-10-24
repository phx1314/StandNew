package com.framewidget.newMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 *
 */
public class DfLayOut extends LinearLayout {

    public DfLayOut(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DfLayOut(Context context) {
        super(context);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
