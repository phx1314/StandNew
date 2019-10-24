//
//  BaseFrg
//
//  Created by DELL on 2018-05-03 08:33:37
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ab.http.AbRequestParams;
import com.ab.http.HttpUtil;
import com.ab.util.HttpResponseListener;
import com.ab.util.HttpResponseListenerSon;
import com.framewidget.view.Headlayout;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.item.MimaDialog;
import com.jinqu.standardNew.model.ModelBanBen;
import com.mdx.framework.activity.MFragment;
import com.mdx.framework.widget.ActionBar;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import static com.jinqu.standardNew.F.GET;
import static com.jinqu.standardNew.F.METHOD_UPDATE;
import static com.jinqu.standardNew.F.POST;
import static com.jinqu.standardNew.F.getJson;
import static com.jinqu.standardNew.F.json2Model;

public abstract class BaseFrg extends MFragment implements View.OnClickListener, HttpResponseListenerSon {
    public Headlayout mHeadlayout;
    public static boolean isShowDialog = false;
    public boolean isNeedYzh = true;
    public Dialog mDialog;

    @Override
    public void onClick(View v) {
        setBgClickClor(v);
    }

    public static void setBgClickClor(View v) {
        TypedValue typedValue = new TypedValue();
        int[] attribute = new int[]{android.R.attr.selectableItemBackground};
        TypedArray typedArray = v.getContext().getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
        v.setBackgroundDrawable(typedArray.getDrawable(0));
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onFailure(int statusCode, String content, Throwable error) {

    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_UPDATE)) {
            ModelBanBen modelBanBen = (ModelBanBen) json2Model(content, ModelBanBen.class);
            if (modelBanBen.stateValue != null) {
                if (modelBanBen.stateValue.VersionsCode > F.getversioncode(getContext())) {
                    HttpUtil.updateApk(getActivity(), modelBanBen.stateValue.UpdatePath);
                } else {
//                    Helper.toast("已是最新版本", getContext());
                }
            } else {
//                Helper.toast("已是最新版本", getContext());
            }
        }
    }

    public static void showDialog(Context context) {
        if (getJson("toggle2").equals("true")) {
            final View view = MimaDialog.getView(context, null);
            Dialog mDialog = new Dialog(context, com.framewidget.R.style.dialog_1);
            mDialog.setContentView(view, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            WindowManager windowManager = ((FragmentActivity) context)
                    .getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
            lp.width = (display.getWidth()); // 设置宽度
            lp.height = (int) (display.getHeight()); // 高度设置为屏幕的0.6
            lp.gravity = Gravity.CENTER;
            mDialog.getWindow().setAttributes(lp);
            mDialog.show();
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.setCancelable(false);
            ((MimaDialog) view.getTag()).set(mDialog);
        }
    }

    @Override
    protected void initcreate(Bundle savedInstanceState) {
        super.initcreate(savedInstanceState);
        setDarkStatusIcon(getActivity(), true);
//        setWindowStatusBarColor(getActivity(), Color.parseColor(F.COLOR));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        mHeadlayout = new Headlayout(context);
        mHeadlayout.setLeftBackground(R.drawable.arrows_left);
        mHeadlayout.setGoBack(getActivity());
        actionBar.addView(mHeadlayout);
    }

    public void loadUrlGet(String methodName, Object... mparams) {
        HttpUtil.loadUrl(getContext(), GET, methodName, new HttpResponseListener(getContext(), this, methodName, true), mparams);
    }

    public void loadUrlGetNoshow(String methodName, Object... mparams) {
        HttpUtil.loadUrl(getContext(), GET, methodName, new HttpResponseListener(getContext(), this, methodName, false), mparams);
    }

    public void loadUrlPost(String methodName, Object... mparams) {
        HttpUtil.loadUrl(getContext(), POST, methodName, new HttpResponseListener(getContext(), this, methodName, true), mparams);
    }

    public void loadNoCookie(String methodName, boolean noCookie, Object... mparams) {
        HttpUtil.loadNoCookie(getContext(), POST, methodName, new HttpResponseListener(getContext(), this, methodName, true), noCookie, mparams);
    }

    public void loadPost(String methodName, String methodNameBiaoShi, Object... mparams) {
        HttpUtil.loadUrl(getContext(), POST, methodName, new HttpResponseListener(getContext(), this, methodNameBiaoShi, true), mparams);
    }

    public void loadPostNoShow(String methodName, String methodNameBiaoShi, Object... mparams) {
        HttpUtil.loadUrl(getContext(), POST, methodName, new HttpResponseListener(getContext(), this, methodNameBiaoShi, false), mparams);
    }

    public void loadUrlPostNoShow(String methodName, Object... mparams) {
        HttpUtil.loadUrl(getContext(), POST, methodName, new HttpResponseListener(getContext(), this, methodName, false), mparams);
    }

    public void loadUrlPostBase(String methodName, String json, Object obj) {
        HttpUtil.loadUrl(getContext(), POST, methodName, new HttpResponseListener(getContext(), this, methodName, true), readClassAttr(json, obj));
    }

    /**
     * 用来遍历对象属性和属性值
     */
    public static AbRequestParams readClassAttr(String json, Object tb) {
        AbRequestParams map = new AbRequestParams();
        try {
            if (tb != null) {
                Field[] fields = tb.getClass().getDeclaredFields();
                System.out.println(fields.length);
                for (Field field : fields) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(tb) == null ? "" : field.get(tb).toString());
                }
            }
            if (!TextUtils.isEmpty(json)) {
                JSONObject jsonObject = new JSONObject(json);
                Iterator iterator = jsonObject.keys();                       // joData是JSONObject对象
                while (iterator.hasNext()) {
                    String key = iterator.next() + "";
                    map.put(key, jsonObject.optString(key));
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public void setWindowStatusBarColor(int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colorResId);

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 说明：Android 6.0+ 状态栏图标原生反色操作
     */
    public static void setDarkStatusIcon(Activity act, boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = act.getWindow().getDecorView();
            if (decorView == null) return;

            int vis = decorView.getSystemUiVisibility();
            if (dark) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(vis);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isNeedYzh && isShowDialog && F.getJson("toggle1").equals("true")) {
            if (mDialog != null && mDialog.isShowing()) {
                return;
            }
            final View view = MimaDialog.getView(getContext(), null);
            mDialog = new Dialog(getContext(), com.framewidget.R.style.dialog_1);
            mDialog.setContentView(view, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            WindowManager windowManager = ((FragmentActivity) getContext())
                    .getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
            lp.width = (display.getWidth()); // 设置宽度
            lp.height = (int) (display.getHeight()); // 高度设置为屏幕的0.6
            lp.gravity = Gravity.CENTER;
            mDialog.getWindow().setAttributes(lp);
            mDialog.show();
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.setCancelable(false);
            ((MimaDialog) view.getTag()).set(mDialog);
            isShowDialog = false;
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (!isAppOnForeground()) {//由前台切换到后台
            isShowDialog = true;
        }
    }

    /**
     * 判断app是否处于前台
     *
     * @return
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getActivity()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getActivity().getPackageName();
        /**
         * 获取Android设备中所有正在运行的App
         */
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        com.framewidget.F.closeSoftKey(getActivity());
    }

}
