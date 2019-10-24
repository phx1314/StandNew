//
//  Txl
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framewidget.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgGrzx;
import com.jinqu.standardNew.model.ModelEmploee;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;

import java.util.List;

import static com.jinqu.standardNew.F.setImage;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;


public class Txl extends BaseItem {
    public TextView mTextView_title;
    public MImageView mImageView_touxiang;
    public TextView mTextView_name;
    public TextView mTextView_bumen;
    public ImageView mCheckBox;
    public List<ModelEmploee.RowsBean> list;
    public int type;
    public ModelEmploee.RowsBean item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_txl, null);
        convertView.setTag(new Txl(convertView));
        return convertView;
    }

    public Txl(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mImageView_touxiang = (MImageView) contentview.findViewById(R.id.mImageView_touxiang);
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_bumen = (TextView) contentview.findViewById(R.id.mTextView_bumen);
        mCheckBox = (ImageView) contentview.findViewById(R.id.mCheckBox);

        mImageView_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                Helper.startActivity(context, FrgGrzx.class, NoTitleAct.class, "id", item.EmpID + "");
            }
        });
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(char section) {
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.isEmpty(type == 0 ? list.get(i).EmpName : list.get(i).EmpDepName)) {
                continue;
            }
            String sortStr = F.toPinYin((type == 0 ? list.get(i).EmpName : list.get(i).EmpDepName).charAt(0));
            char firstChar = sortStr.charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public int getPositionForSection(String EmpDepName) {
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.isEmpty(list.get(i).EmpDepName)) {
                continue;
            }
            if (list.get(i).EmpDepName.equals(EmpDepName)) {
                return i;
            }
        }
        return -1;
    }

    public void set(ModelEmploee.RowsBean item, int position, List<ModelEmploee.RowsBean> list, int type) {
        setZhi(item, position, list, type);
    }

    public void setZhi(ModelEmploee.RowsBean item, int position, List<ModelEmploee.RowsBean> list, int type) {
        this.list = list;
        this.type = type;
        this.item = item;
        if (TextUtils.isEmpty(type == 0 ? item.EmpName : item.EmpDepName)) {
            mTextView_title.setText("#");
        } else {
            mTextView_title.setText(type == 0 ? F.toPinYin(item.EmpName.charAt(0)).toUpperCase().charAt(0) + "" : item.EmpDepName);
        }
        mTextView_name.setText(item.EmpName);
        mTextView_bumen.setText(item.EmpDepName);


        if (!TextUtils.isEmpty(item.EmpHead)) {
            mImageView_touxiang.setObj(item.EmpHead);
        } else {
            setImage(mImageView_touxiang, item.EmpName.substring(0, 1));
        }
        mImageView_touxiang.setCircle(true);
        if (TextUtils.isEmpty((type == 0 ? item.EmpName : item.EmpDepName))) {
            mTextView_title.setVisibility(View.GONE);
        } else {
            if (position == (type == 0 ? getPositionForSection(F.toPinYin(item.EmpName.charAt(0)).charAt(0)) : getPositionForSection(item.EmpDepName))) {
                mTextView_title.setVisibility(View.VISIBLE);
            } else {
                mTextView_title.setVisibility(View.GONE);
            }
        }
    }

    public void set(ModelEmploee.RowsBean item, int position, List<ModelEmploee.RowsBean> list, int type, boolean isShow) {
        mCheckBox.setVisibility(View.VISIBLE);
        mCheckBox.setBackgroundResource(item.isChecked ? R.drawable.xuanzhong : R.drawable.weixuan);
        setZhi(item, position, list, type);
    }

    public void set(ModelEmploee.RowsBean item) {
        setZhiSousuo(item);
    }

    public void set(ModelEmploee.RowsBean item, boolean isShow) {
        mCheckBox.setVisibility(View.VISIBLE);
        mCheckBox.setBackgroundResource(item.isChecked ? R.drawable.xuanzhong : R.drawable.weixuan);
        setZhiSousuo(item);
    }

    public void setZhiSousuo(ModelEmploee.RowsBean item) {
        this.item = item;
        mTextView_name.setText(item.EmpName);
        mTextView_bumen.setText(item.EmpDepName);

        if (!TextUtils.isEmpty(item.EmpHead)) {
            mImageView_touxiang.setObj(item.EmpHead);
        } else {
            setImage(mImageView_touxiang, item.EmpName.substring(0, 1));
        }

        mImageView_touxiang.setCircle(true);
        mTextView_title.setVisibility(View.GONE);
    }


}