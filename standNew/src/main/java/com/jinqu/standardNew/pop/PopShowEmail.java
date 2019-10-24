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
import com.jinqu.standardNew.frg.FrgSendEmail;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;


public class PopShowEmail implements OnClickListener {

    public TextView mTextView_search;
    public TextView mTextView_add;
    public TextView mTextView_del;
    public Context context;
    private View view;
    private PopupWindow popwindow;
    private View popview;

    public PopShowEmail(Context context, View view) {
        super();
        this.view = view;
        this.context = context;
        LayoutInflater flater = LayoutInflater.from(context);
        popview = flater.inflate(R.layout.item_email_pop, null);
        popwindow = new PopupWindow(popview, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mTextView_search = (TextView) popview
                .findViewById(R.id.mTextView_search);
        mTextView_add = (TextView) popview
                .findViewById(R.id.mTextView_add);
        mTextView_del = (TextView) popview
                .findViewById(R.id.mTextView_del);
        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);

        mTextView_del.setOnClickListener(this);
        mTextView_add.setOnClickListener(this);
        mTextView_search.setOnClickListener(this);
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
    public void onClick(View arg0) {setBgClickClor(arg0);
        hide();
        if (arg0.getId() == R.id.mTextView_del) {
            Frame.HANDLES.sentAll("FrgEmailList", 120, null);
        } else if (arg0.getId() == R.id.mTextView_add) {
            Helper.startActivity(context, FrgSendEmail.class, TitleAct.class);
        }
    }

}
