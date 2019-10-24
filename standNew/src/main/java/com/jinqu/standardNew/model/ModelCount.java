package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelCount {


    /**
     * Result : true
     * Total : 1
     * Datas : [{"Id":13,"MailReadEmpId":712,"MailReadEmpName":"戴飞","MailReadIsDelete":0,"MailIsBBC":0,"CreationTime":"/Date(1540271833531)/","MailTitle":"掌上金曲 5.0 正式发布","CreatorEmpName":"黄一鸣"}]
     */

    public boolean Result;
    public int Total;
    public List<DatasBean> Datas;

    public static class DatasBean {
        /**
         * Id : 13
         * MailReadEmpId : 712
         * MailReadEmpName : 戴飞
         * MailReadIsDelete : 0
         * MailIsBBC : 0
         * CreationTime : /Date(1540271833531)/
         * MailTitle : 掌上金曲 5.0 正式发布
         * CreatorEmpName : 黄一鸣
         */

        public int Id;
        public int MailReadEmpId;
        public String MailReadEmpName;
        public int MailReadIsDelete;
        public int MailIsBBC;
        public String CreationTime;
        public String MailTitle;
        public String CreatorEmpName;
    }
}
