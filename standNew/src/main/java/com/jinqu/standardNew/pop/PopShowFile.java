package com.jinqu.standardNew.pop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgJp;
import com.jinqu.standardNew.frg.FrgLuyin;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;


public class PopShowFile implements OnClickListener {

    public Context context;
    public TextView mTextView_sp;
    public TextView mTextView_yp;
    public TextView mTextView_wz;
    public TextView mTextView_more;
    public TextView mTextView_pic;
    private View view;
    private PopupWindow popwindow;
    private View popview;

    public PopShowFile(Context context, View view) {
        super();
        this.view = view;
        this.context = context;
        LayoutInflater flater = LayoutInflater.from(context);
        popview = flater.inflate(R.layout.item_fujian_list_pop, null);
        popwindow = new PopupWindow(popview, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mTextView_sp = (TextView) popview
                .findViewById(R.id.mTextView_sp);
        mTextView_yp = (TextView) popview
                .findViewById(R.id.mTextView_yp);
        mTextView_wz = (TextView) popview
                .findViewById(R.id.mTextView_wz);
        mTextView_more = (TextView) popview
                .findViewById(R.id.mTextView_more);
        mTextView_pic = (TextView) popview
                .findViewById(R.id.mTextView_pic);
        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);

        mTextView_sp.setOnClickListener(this);
        mTextView_yp.setOnClickListener(this);
        mTextView_wz.setOnClickListener(this);
        mTextView_more.setOnClickListener(this);
        mTextView_pic.setOnClickListener(this);
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
        if (arg0.getId() == R.id.mTextView_sp) {
            Frame.HANDLES.sentAll("FrgFujianListEdit", 111, null);
        } else if (arg0.getId() == R.id.mTextView_yp) {
            Helper.startActivity(context, FrgLuyin.class, TitleAct.class);
        } else if (arg0.getId() == R.id.mTextView_wz) {

            ActivityCompat.requestPermissions((MActivityActionbar) context,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            Helper.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionRequest() {
                public void onGrant(String[] permissions, int[] grantResults) {
                    Helper.startActivity(context, FrgJp.class, TitleAct.class);
                }
            });
        } else if (arg0.getId() == R.id.mTextView_more) {
            Frame.HANDLES.sentAll("FrgFujianListEdit", 110, null);
        } else if (arg0.getId() == R.id.mTextView_pic) {
            Frame.HANDLES.sentAll("FrgFujianListEdit", 114, null);
        }
    }

}
