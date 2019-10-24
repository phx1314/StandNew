package com.jinqu.standardNew.util;


import com.jinqu.standardNew.model.ModelQunList;

import java.util.Comparator;

/**
 * @author xiaanming
 */
public class QzxxComparator implements Comparator<ModelQunList.RowsBean> {
    public int type;


    public int compare(ModelQunList.RowsBean o1, ModelQunList.RowsBean o2) {

        if (o1.mConversation == null) {
            return 1;
        } else if (o2.mConversation == null) {
            return 1;
        } else if (o1.mConversation.getSentTime() > o2.mConversation.getSentTime()) {
            return -1;
        } else if (o1.mConversation.getSentTime() < o2.mConversation.getSentTime()) {
            return 1;
        } else {
            return 0;
        }

    }
}
