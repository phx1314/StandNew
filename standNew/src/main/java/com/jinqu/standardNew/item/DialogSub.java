//
//  DialogSub
//
//  Created by DELL on 2017-05-11 15:58:18
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ab.http.HttpUtil;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgXzRenyuan;
import com.jinqu.standardNew.model.ModelAddPub;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.model.ModelJDInfo;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.handle.MHandler;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_FLOWWIDGET;
import static com.jinqu.standardNew.F.json2Model;


public class DialogSub extends BaseItem {
    public Spinner mSpinner_1;
    public Spinner mSpinner_2;
    public EditText mEditText;
    public TextView mTextView_sure;
    public TextView mTextView_cancel;
    public TextView mTextView_title;
    public Dialog item;
    public ModelJDInfo mModelJDInfo;
    public ModelBd.RowsBean mModelPostData;
    public String from;
    public TextView mTextView_2;
    public String ids = "";

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_dialog_sub, null);
        convertView.setTag(new DialogSub(convertView));
        return convertView;
    }

    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                List<ModelEmploee.RowsBean> mBaseEmployeeBeans = (List<ModelEmploee.RowsBean>) obj;
                String string = "";
                ids = "";
                for (ModelEmploee.RowsBean mBaseEmployeeBean : mBaseEmployeeBeans) {
                    string += mBaseEmployeeBean.EmpName + ",";
                    ids += mBaseEmployeeBean.EmpID + ",";
                }
                string = string.substring(0, string.length() - 1);
                ids = ids.substring(0, ids.length() - 1);
                mTextView_2.setText(string);
                break;
        }
    }

    public DialogSub(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.handler = new MHandler();
        String className = this.getClass().getSimpleName();
        this.handler.setId(className);
        this.handler.setMsglisnener(new MHandler.HandleMsgLisnener() {
            public void onMessage(Message msg) {
                switch (msg.what) {
                    case 201:
                        disposeMsg(msg.arg1, msg.obj);
                }

            }
        });
        Frame.HANDLES.add(this.handler);
        findVMethod();
    }

    private void findVMethod() {
        mSpinner_1 = (Spinner) contentview.findViewById(R.id.mSpinner_1);
        mSpinner_2 = (Spinner) contentview.findViewById(R.id.mSpinner_2);
        mEditText = (EditText) contentview.findViewById(R.id.mEditText);
        mTextView_sure = (TextView) contentview.findViewById(R.id.mTextView_sure);
        mTextView_cancel = (TextView) contentview.findViewById(R.id.mTextView_cancel);
        mTextView_title = (TextView) findViewById(R.id.mTextView_title);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);

        mTextView_sure.setOnClickListener(this);
        mTextView_cancel.setOnClickListener(this);
        mTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgXzRenyuan.class, TitleAct.class, "id", ids, "from", "DialogSub");
            }
        });
        mSpinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mModelJDInfo != null && mModelJDInfo.NextSteps.size() > 0) {
                    List<String> list = new ArrayList<String>();
                    if (mModelJDInfo.NextSteps.get(i).NodeName.contains("会签")) {
                        mSpinner_2.setVisibility(View.GONE);
                        mTextView_2.setVisibility(View.VISIBLE);
                        if (!TextUtils.isEmpty(mModelJDInfo.NextSteps.get(i).ChoosedUsers)) {
                            mTextView_2.setText(mModelJDInfo.NextSteps.get(i).ChoosedUserNames);
                            ids = mModelJDInfo.NextSteps.get(i).ChoosedUsers;
                        }
                    } else {
                        mSpinner_2.setVisibility(View.VISIBLE);
                        mTextView_2.setVisibility(View.GONE);
                        if (mModelJDInfo.NextSteps.get(i).Users != null)
                            for (ModelJDInfo.NextStepsBean.UsersBean mUsers : mModelJDInfo.NextSteps.get(i).Users) {
                                list.add(mUsers.Name);
                            }
                        if (list.size() <= 0) {
                            list.add("无可选择人员");
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinner_2.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_FLOWWIDGET)) {
            mModelJDInfo = (ModelJDInfo) json2Model(content, ModelJDInfo.class);
            mEditText.setText(mModelJDInfo.DefaultNote);
            if (mModelJDInfo.NextSteps.size() > 0) {
                List<String> list = new ArrayList<String>();
                for (ModelJDInfo.NextStepsBean mNextStepsBean : mModelJDInfo.NextSteps) {
                    list.add(mNextStepsBean.NodeName);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_1.setAdapter(adapter);
                mSpinner_1.setSelection(0, true);
            } else {
                List<String> list = new ArrayList<String>();
                list.add("流程结束");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_1.setAdapter(adapter);
                List<String> list2 = new ArrayList<String>();
                list2.add("不需要选择");
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, R.layout.spinner, list2);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_2.setAdapter(adapter2);
            }
        }
    }

    public void set(Dialog item, String title, ModelBd.RowsBean mModelPostData, String from) {
        this.item = item;
        this.from = from;
        this.mModelPostData = mModelPostData;
        mTextView_title.setText(title);
        if (mModelPostData.IsNew) {
            mModelPostData.FlowNodeID = 0;
        }
        HttpUtil.loadUrlPost(context, this, METHOD_FLOWWIDGET, "_refID", mModelPostData.Id, "_refTable", mModelPostData.FlowRefTable, "_flowNodeID", mModelPostData.FlowNodeID, "_action", mModelPostData._action, "_flowMultiSignID", mModelPostData.FlowMultiSignID);
    }

    public void setNoLoad(Dialog item, String title, ModelBd.RowsBean mModelPostData, String from) {
        this.item = item;
        this.from = from;
        this.mModelPostData = mModelPostData;
        mTextView_title.setText(title);
        List<String> list = new ArrayList<String>();
        list.add(title);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner_1.setAdapter(adapter);
        List<String> list2 = new ArrayList<String>();
        list2.add("不需要选择");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, R.layout.spinner, list2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner_2.setAdapter(adapter2);
    }

    @Override
    public void onClick(View v) {
        item.dismiss();
        switch (v.getId()) {
            case R.id.mTextView_sure:
                if (mSpinner_2.getSelectedItem() instanceof String && String.valueOf(mSpinner_2.getSelectedItem()).contains("无可选择人员")) {
                    return;
                }
                ModelAddPub mModelAddPub = new ModelAddPub();
                mModelAddPub._refID = mModelPostData.Id;
                mModelAddPub._refTable = mModelPostData.FlowRefTable;
                mModelAddPub._flowNodeID = mModelPostData.FlowNodeID;
                mModelAddPub._action = mModelPostData._action.split("_")[1];
                mModelAddPub._flowMultiSignID = mModelPostData.FlowMultiSignID;
                mModelAddPub._note = mEditText.getText().toString().trim();
                if (mModelJDInfo != null && mModelJDInfo.NextSteps.size() > 0) {
                    mModelAddPub._nextNodeID = mModelJDInfo.NextSteps.get(mSpinner_1.getSelectedItemPosition()).NodeID;
                    if (mTextView_2.getVisibility() == View.VISIBLE) {
                        if (!TextUtils.isEmpty(ids)) {
                            mModelAddPub._nextEmpIDs = ids;
                        } else {
                            Helper.toast("请选择会签人员", context);
                            return;
                        }
                    } else {
                        mModelAddPub._nextEmpIDs = mModelJDInfo.NextSteps.get(mSpinner_1.getSelectedItemPosition()).Users.size() > 0 ? mModelJDInfo.NextSteps.get(mSpinner_1.getSelectedItemPosition()).Users.get(mSpinner_2.getSelectedItemPosition()).ID + "" : "0";
                    }
                } else {
                    mModelAddPub._nextNodeID = 0;
                    mModelAddPub._nextEmpIDs = "0";
                }
                Frame.HANDLES.sentAll(from, 300, mModelAddPub);
                break;
            case R.id.mTextView_cancel:
                break;
        }
    }
}