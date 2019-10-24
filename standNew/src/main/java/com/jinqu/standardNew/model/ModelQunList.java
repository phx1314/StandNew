package com.jinqu.standardNew.model;

import java.util.List;

import io.rong.imlib.model.Conversation;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelQunList {


    /**
     * total : 13
     * rows : [{"GroupID":22,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:57:01.833","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":1},{"GroupID":21,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:56:50.857","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":2},{"GroupID":20,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:56:21.913","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":3},{"GroupID":19,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:56:08.237","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":4},{"GroupID":18,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:55:19.54","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":5},{"GroupID":17,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:54:58.567","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":6},{"GroupID":16,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:44:18.287","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":7},{"GroupID":15,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:43:39.143","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":8},{"GroupID":13,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:42:18.997","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":9},{"GroupID":14,"GroupName":"话剧","CreateEmp":1,"CreateDate":"2017-04-27T08:42:18.997","IsPublic":true,"PortraitUri":"","GroupNote":"话剧","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":"","HeadImage":null,"row_number":10}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * GroupID : 22
         * GroupName : 话剧
         * CreateEmp : 1
         * CreateDate : 2017-04-27T08:57:01.833
         * IsPublic : true
         * PortraitUri :
         * GroupNote : 话剧
         * RefTable :
         * RefID : 0
         * IsDelete : 0
         * HXGroupID :
         * HeadImage : Str
         * row_number : 1
         */

        public int GroupID;
        public String GroupName;
        public int CreateEmp;
        public String CreateDate;
        public boolean IsPublic;
        public String PortraitUri;
        public String GroupNote;
        public String RefTable;
        public int RefID;
        public int IsDelete;
        public String HXGroupID;
        public String HeadImage;
        public int row_number;

        public Conversation mConversation;
    }
}
