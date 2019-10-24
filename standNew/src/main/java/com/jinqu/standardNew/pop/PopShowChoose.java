package com.jinqu.standardNew.pop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaChoosePopSon;

import java.util.List;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;


public class PopShowChoose implements OnClickListener {

    private ListView mListView;
    private ImageView mImageView_kb;
    private View view;
    private PopupWindow popwindow;
    private View popview;

    public PopShowChoose(Context context, View view, List data, String choosed, String from) {
        super();
        this.view = view;
        LayoutInflater flater = LayoutInflater.from(context);
        popview = flater.inflate(R.layout.item_choose_pop, null);
        popwindow = new PopupWindow(popview, view.getWidth(),
                LayoutParams.MATCH_PARENT);
        mListView = (ListView) popview
                .findViewById(R.id.mListView);
        mImageView_kb = (ImageView) popview
                .findViewById(R.id.mImageView_kb);
        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);

        mImageView_kb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                popwindow.dismiss();
            }
        });
        mListView.setAdapter(new AdaChoosePopSon(context, data, choosed, from, this));
    }


    @SuppressLint("NewApi")
    public void show() {
        popwindow.showAsDropDown(view, 0, 0);
    }

    public void hide() {
        popwindow.dismiss();
    }

    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onClick(View arg0) {
        hide();
    }
}
