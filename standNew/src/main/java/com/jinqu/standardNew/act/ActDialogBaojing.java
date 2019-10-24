//
//  ActDialogBaojing
//
//  Created by df on 2016-08-02 09:07:47
//  Copyright (c) df All rights reserved.


/**

 */

package com.jinqu.standardNew.act;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgCreateRcDetail;
import com.jinqu.standardNew.model.ModelBell;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

public class ActDialogBaojing extends BaseAct {


    public TextView mTextView_content;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public ModelBell mModelBell;

    @Override
    protected void create(Bundle savedInstanceState) {
        mModelBell = (ModelBell) getActivity().getIntent().getSerializableExtra("data");
        setContentView(R.layout.act_dialog_baojing);
        initView();
        loaddata();
    }


    private void initView() {
        findVMethod();
    }

    private void findVMethod() {


        mTextView_content = (TextView) findViewById(R.id.mTextView_content);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);

        mTextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActDialogBaojing.this.finish();
            }
        });
        mTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActDialogBaojing.this.finish();
                Helper.startActivity(getContext(), FrgCreateRcDetail.class, TitleAct.class, "id", mModelBell.ID + "","isRemind",true);
            }
        });
    }


    public void loaddata() {
        mTextView_content.setText(mModelBell.Content);
    }

}
