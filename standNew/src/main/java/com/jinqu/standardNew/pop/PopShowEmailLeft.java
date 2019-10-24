package com.jinqu.standardNew.pop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.mdx.framework.Frame;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;


public class PopShowEmailLeft implements OnClickListener {

    public Context context;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public TextView mTextView_4;
    private View view;
    private PopupWindow popwindow;
    private View popview;

    public PopShowEmailLeft(Context context, View view) {
        super();
        this.view = view;
        this.context = context;
        LayoutInflater flater = LayoutInflater.from(context);
        popview = flater.inflate(R.layout.item_email_pop_left, null);
        popwindow = new PopupWindow(popview, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mTextView_1 = (TextView) popview
                .findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) popview
                .findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) popview
                .findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) popview
                .findViewById(R.id.mTextView_4);
        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);

        mTextView_1.setOnClickListener(this);
        mTextView_2.setOnClickListener(this);
        mTextView_3.setOnClickListener(this);
        mTextView_4.setOnClickListener(this);
    }


    @SuppressLint("NewApi")
    public void show() {
        popwindow.showAsDropDown(view, 0, -25);
    }

    public void hide() {
        popwindow.dismiss();
    }

    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onClick(View arg0) {
        setBgClickClor(arg0);
        hide();
        if (arg0.getId() == R.id.mTextView_1) {
            Frame.HANDLES.sentAll("FrgEmailList", 1, null);
        } else if (arg0.getId() == R.id.mTextView_2) {
            Frame.HANDLES.sentAll("FrgEmailList", 2, null);
        } else if (arg0.getId() == R.id.mTextView_3) {
            Frame.HANDLES.sentAll("FrgEmailList", 3, null);
        } else if (arg0.getId() == R.id.mTextView_4) {
            Frame.HANDLES.sentAll("FrgEmailList", 4, null);
        }
    }

}
