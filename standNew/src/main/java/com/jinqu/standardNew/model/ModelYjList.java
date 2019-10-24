package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelYjList {


    /**
     * total : 5
     * rows : [{"Id":21,"MailID":21,"MailIsRead":true,"MailFlag":"fa fa-envelope-o","MailDate":"2018-07-05T15:08:51.583","MailTitle":"test2","MailNote":"test3","MailEmpName":"管理员"},{"Id":20,"MailID":20,"MailIsRead":true,"MailFlag":"fa fa-envelope-o","MailDate":"2018-07-05T11:59:28.96","MailTitle":"测试","MailNote":"测试1","MailEmpName":"管理员"},{"Id":18,"MailID":18,"MailIsRead":true,"MailFlag":"fa fa-envelope-o","MailDate":"2018-06-25T10:25:46.923","MailTitle":"阿莫","MailNote":"得","MailEmpName":"周军"},{"Id":17,"MailID":17,"MailIsRead":true,"MailFlag":"fa fa-envelope-o","MailDate":"2018-06-25T10:12:56.893","MailTitle":"【回复】sdcard","MailNote":"打算Xxxx","MailEmpName":"周军"},{"Id":13,"MailID":13,"MailIsRead":true,"MailFlag":"fa fa-envelope-o","MailDate":"2018-06-25T08:29:00.35","MailTitle":"sdcard","MailNote":"&nbsp;打算","MailEmpName":"周军"}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 21
         * MailID : 21
         * MailIsRead : true
         * MailFlag : fa fa-envelope-o
         * MailDate : 2018-07-05T15:08:51.583
         * MailTitle : test2
         * MailNote : test3
         * MailEmpName : 管理员
         */

        public int Id;
        public int MailID;
        public int HasAttachs;
        public boolean MailIsRead;
        public String MailFlag;
        public String MailDate;
        public String MailTitle;
        public String MailNote;
        public String MailEmpName;
        public boolean isChecked;
    }
}
