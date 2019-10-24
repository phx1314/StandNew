package com.jinqu.standardNew.util;


import android.text.TextUtils;

import com.framewidget.F;
import com.jinqu.standardNew.model.ModelEmploee;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaanming
 */
public class PinyinComparator implements Comparator<ModelEmploee.RowsBean> {
    public int type;

    public PinyinComparator(int type) {
        this.type = type;
    }

    public int compare(ModelEmploee.RowsBean o1, ModelEmploee.RowsBean o2) {

        if (type == 0) {
            Pattern p = Pattern.compile("[0-9]*");
            Matcher m = p.matcher(o2.EmpName.charAt(0) + "");
            Matcher m2 = p.matcher(o1.EmpName.charAt(0) + "");
            if (m.matches()) {
                return -1;
            }
            if (m2.matches()) {
                return 1;
            }
            if (F.toPinYin(o2.EmpName.charAt(0)).equals("#")) {
                return -1;
            } else if (F.toPinYin(o1.EmpName.charAt(0)).equals("#")) {
                return 1;
            } else {
                return F.toPinYin(o1.EmpName.charAt(0)).compareTo(F.toPinYin(o2.EmpName.charAt(0)));
            }
        } else {
            if (F.toPinYin((TextUtils.isEmpty(o1.EmpDepName) ? "#" : o1.EmpDepName).charAt(0)).compareTo(F.toPinYin((TextUtils.isEmpty(o2.EmpDepName) ? "#" : o2.EmpDepName).charAt(0))) == 0) {
                return (TextUtils.isEmpty(o1.EmpDepName) ? "#" : o1.EmpDepName).compareTo(TextUtils.isEmpty(o2.EmpDepName) ? "#" : o2.EmpDepName);
            } else {
                return F.toPinYin((TextUtils.isEmpty(o1.EmpDepName) ? "#" : o1.EmpDepName).charAt(0)).compareTo(F.toPinYin((TextUtils.isEmpty(o2.EmpDepName) ? "#" : o2.EmpDepName).charAt(0)));
            }
        }

    }
}
