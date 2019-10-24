package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelgzdtList {


    /**
     * total : 1
     * rows : [{"Id":33,"TalkTitle":"哈哈","ReplyCount":1,"ReadCount":0,"HY":"1/0","CreatorEmpName":"关杰","CreationTime":"2017-05-02T08:42:58","TalkContent":"放寒假","CreatorEmpId":518}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean implements Serializable {
        /**
         * Id : 33
         * TalkTitle : 哈哈
         * ReplyCount : 1
         * ReadCount : 0
         * HY : 1/0
         * CreatorEmpName : 关杰
         * CreationTime : 2017-05-02T08:42:58
         * TalkContent : 放寒假
         * CreatorEmpId : 518
         */

        public int Id;
        public String TalkTitle;
        public int ReplyCount;
        public int ReadCount;
        public String HY;
        public String CreatorEmpName;
        public String CreationTime;
        public String TalkContent;
        public int CreatorEmpId;
    }
}
