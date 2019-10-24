package com.framewidget.view;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framewidget.R;
import com.framewidget.item.ImgShow;
import com.mdx.framework.R.style;
import com.mdx.framework.prompt.MDialog;

import java.util.ArrayList;
import java.util.List;


public class PhotoShowDf extends MDialog {
    public ImageView mImageView_back;
    public TextView mTextView_more;
    private ViewPager mPager;
    public MyViewPagerAdapter mMyViewPagerAdapter;
    public List<String> list;
    public String sind;

    public PhotoShowDf(Context context, List<String> list, String sind) {
        super(context, style.full_dialog);
        this.list = list;
        this.sind = sind;
    }


    protected void create(Bundle savedInstanceState) {
        this.setContentView(R.layout.photoshow_dialog);
        findVMethod();
        updata();
    }

    public void updata() {
        if (list == null) {
            list = new ArrayList<>();
            list.add(sind);
        }
        List<View> mViews = new ArrayList<View>();
        for (int i = 0; i < list.size(); i++) {
            View view = ImgShow.getView(getContext(), null);
            mViews.add(view);
            ((ImgShow) view.getTag()).set(list.get(i));
        }
        mMyViewPagerAdapter = new MyViewPagerAdapter(mViews);
        mPager.setAdapter(mMyViewPagerAdapter);
        mPager.setCurrentItem(list.indexOf(sind));
        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTextView_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShowShare mPopShowShare = new PopShowShare(getContext(), mTextView_more,list.get(mPager.getCurrentItem()));
                mPopShowShare.show();
            }
        });
    }

    private void findVMethod() {
        mImageView_back = (ImageView) this.findViewById(R.id.mImageView_back);
        mTextView_more = (TextView) this.findViewById(R.id.mTextView_more);
        mPager = (com.mdx.framework.widget.MViewPager) this.findViewById(R.id.mViewPager);
    }
}
