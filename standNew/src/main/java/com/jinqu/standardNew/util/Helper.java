package com.jinqu.standardNew.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.google.protobuf.GeneratedMessage;
import com.mdx.framework.log.MLog;

import java.io.Serializable;

/**
 * Created by DELL on 2018/7/19.
 */

public class Helper {
    public static void startActivity(Context context, Class<?> fragment, Class<?> activity, Bundle b, Object... params) {
        startActivity(context, 0, b, (String) fragment.getName(), activity, (Object[]) params);
    }


    public static void startActivity(Context context, int flag, Bundle b, String fragment, Class<?> activity, Object... params) {
        Intent i = new Intent(context, activity);
        i.setFlags(flag);
        i.putExtra("className", fragment);
        if (params != null) {
            for (int ind = 0; ind < params.length; ++ind) {
                String key = params[ind].toString();
                if (params.length > ind + 1) {
                    Object obj = params[ind + 1];
                    if (obj instanceof Boolean) {
                        i.putExtra(key, (Boolean) obj);
                    } else if (obj instanceof Integer) {
                        i.putExtra(key, (Integer) obj);
                    } else if (obj instanceof Float) {
                        i.putExtra(key, (Float) obj);
                    } else if (obj instanceof Double) {
                        i.putExtra(key, (Double) obj);
                    } else if (obj instanceof Long) {
                        i.putExtra(key, (Long) obj);
                    } else if (obj instanceof String) {
                        i.putExtra(key, (String) obj);
                    } else if (obj instanceof Serializable) {
                        i.putExtra(key, (Serializable) obj);
                    } else if (obj instanceof Byte[]) {
                        i.putExtra(key, (Byte[]) ((Byte[]) obj));
                    } else if (obj instanceof String[]) {
                        i.putExtra(key, (String[]) ((String[]) obj));
                    } else if (obj instanceof Parcelable) {
                        i.putExtra(key, (Parcelable) obj);
                    } else if (obj instanceof GeneratedMessage.Builder) {
                        try {
                            GeneratedMessage e = (GeneratedMessage) ((GeneratedMessage.Builder) obj).build();
                            i.putExtra(key, e);
                        } catch (Exception var11) {
                            var11.printStackTrace();
                        }
                    } else {
                        MLog.D(obj.getClass().getName() + " unsuppt class type");
                    }
                }

                ++ind;
            }
        }

        context.startActivity(i, b);
    }

}
