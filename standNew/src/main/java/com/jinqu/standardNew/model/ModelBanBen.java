package com.jinqu.standardNew.model;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelBanBen {


    /**
     * stateType : 0
     * stateValue : {"ID":1,"AppName":"掌上金曲","DeviceModel":"Android","VersionsName":"4.0.1","VersionsCode":1,"UpdateInfo":"","UpdatePath":"http://www.jinqu.cn/GoldFile/AppDown/GoldPM9_jqpm.apk","CreateDate":"/Date(1503936000000)/","isDelete":0,"FileSize":0}
     * stateMsg : 操作成功
     * url :
     * validationResults : null
     */

    public int stateType;
    public StateValueBean stateValue;
    public String stateMsg;
    public String url;
    public Object validationResults;

    public static class StateValueBean {
        /**
         * ID : 1
         * AppName : 掌上金曲
         * DeviceModel : Android
         * VersionsName : 4.0.1
         * VersionsCode : 1
         * UpdateInfo :
         * UpdatePath : http://www.jinqu.cn/GoldFile/AppDown/GoldPM9_jqpm.apk
         * CreateDate : /Date(1503936000000)/
         * isDelete : 0
         * FileSize : 0
         */

        public int ID;
        public String AppName;
        public String DeviceModel;
        public String VersionsName;
        public int VersionsCode;
        public String UpdateInfo;
        public String UpdatePath;
        public String CreateDate;
        public int isDelete;
        public int FileSize;
    }
}
