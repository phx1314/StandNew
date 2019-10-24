package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelQunZiLiao {


    /**
     * stateType : 0
     * stateValue : {"Model":{"GroupID":31,"GroupName":"噢噢","CreateEmp":586,"CreateDate":"2017-04-27T10:25:48.267","IsPublic":true,"PortraitUri":"","GroupNote":"根本","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":""},"UserArr":[{"RoleType":1,"EmpID":586,"EmpGUId":"","EmpName":"卜伟","EmpLogin":"卜伟","EmpPassword":"1A1DC91C907325C69271DDF0C944BC72","SalaryPassword":"1A1DC91C907325C69271DDF0C944BC72","EmpDepID":1312,"EmpDepName":"配电室","DepOrder":0,"EmpOrder":16,"EmpBirthDate":"1900-01-01T00:00:00","EmpTitle":"","EmpTel":"13428759852","EmpComputer":"","EmpIPAddress":"","EmpDisk":"D","EmpIsDeleted":false,"EmpPageSize":0,"EmpThemesName":"blue","EmpMenuType":"accordion","EmpIsAgent":false,"EmpSignUrl":"","EmpIsBind":false,"EmpMacAddress":"","EmpTelNX":"","EmpTelWX":"","EmpFJNum":"","EmpOaMail":"","EmpComMail":"","EmpZWAddress":"","EmpWGAddress":"","EmpNote":"叫姐姐斤斤计较","EmpPort":"","EmpHead":"781d43480f1b4a9b898f3df2c6597d96","EmpIsSub":0,"DepartmentID":1312,"DepartmentName":"配电室","DepartmentOrder":"002_008","RoleName":"员级","PayManageCoeff":1,"PaySkillCoeff":0}]}
     * stateMsg : 无法找到群组内容！
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
         * Model : {"GroupID":31,"GroupName":"噢噢","CreateEmp":586,"CreateDate":"2017-04-27T10:25:48.267","IsPublic":true,"PortraitUri":"","GroupNote":"根本","RefTable":"","RefID":0,"IsDelete":0,"HXGroupID":""}
         * UserArr : [{"RoleType":1,"EmpID":586,"EmpGUId":"","EmpName":"卜伟","EmpLogin":"卜伟","EmpPassword":"1A1DC91C907325C69271DDF0C944BC72","SalaryPassword":"1A1DC91C907325C69271DDF0C944BC72","EmpDepID":1312,"EmpDepName":"配电室","DepOrder":0,"EmpOrder":16,"EmpBirthDate":"1900-01-01T00:00:00","EmpTitle":"","EmpTel":"13428759852","EmpComputer":"","EmpIPAddress":"","EmpDisk":"D","EmpIsDeleted":false,"EmpPageSize":0,"EmpThemesName":"blue","EmpMenuType":"accordion","EmpIsAgent":false,"EmpSignUrl":"","EmpIsBind":false,"EmpMacAddress":"","EmpTelNX":"","EmpTelWX":"","EmpFJNum":"","EmpOaMail":"","EmpComMail":"","EmpZWAddress":"","EmpWGAddress":"","EmpNote":"叫姐姐斤斤计较","EmpPort":"","EmpHead":"781d43480f1b4a9b898f3df2c6597d96","EmpIsSub":0,"DepartmentID":1312,"DepartmentName":"配电室","DepartmentOrder":"002_008","RoleName":"员级","PayManageCoeff":1,"PaySkillCoeff":0}]
         */

        public ModelBean Model;
        public List<ModelEmploee.RowsBean> UserArr;

        public static class ModelBean {
            /**
             * GroupID : 31
             * GroupName : 噢噢
             * CreateEmp : 586
             * CreateDate : 2017-04-27T10:25:48.267
             * IsPublic : true
             * PortraitUri :
             * GroupNote : 根本
             * RefTable :
             * RefID : 0
             * IsDelete : 0
             * HXGroupID :
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
        }

        public static class UserArrBean {
            /**
             * RoleType : 1
             * EmpID : 586
             * EmpGUId :
             * EmpName : 卜伟
             * EmpLogin : 卜伟
             * EmpPassword : 1A1DC91C907325C69271DDF0C944BC72
             * SalaryPassword : 1A1DC91C907325C69271DDF0C944BC72
             * EmpDepID : 1312
             * EmpDepName : 配电室
             * DepOrder : 0
             * EmpOrder : 16
             * EmpBirthDate : 1900-01-01T00:00:00
             * EmpTitle :
             * EmpTel : 13428759852
             * EmpComputer :
             * EmpIPAddress :
             * EmpDisk : D
             * EmpIsDeleted : false
             * EmpPageSize : 0
             * EmpThemesName : blue
             * EmpMenuType : accordion
             * EmpIsAgent : false
             * EmpSignUrl :
             * EmpIsBind : false
             * EmpMacAddress :
             * EmpTelNX :
             * EmpTelWX :
             * EmpFJNum :
             * EmpOaMail :
             * EmpComMail :
             * EmpZWAddress :
             * EmpWGAddress :
             * EmpNote : 叫姐姐斤斤计较
             * EmpPort :
             * EmpHead : 781d43480f1b4a9b898f3df2c6597d96
             * EmpIsSub : 0
             * DepartmentID : 1312
             * DepartmentName : 配电室
             * DepartmentOrder : 002_008
             * RoleName : 员级
             * PayManageCoeff : 1.0
             * PaySkillCoeff : 0.0
             */

            public int RoleType;
            public int EmpID;
            public String EmpGUId;
            public String EmpName;
            public String EmpLogin;
            public String EmpPassword;
            public String SalaryPassword;
            public int EmpDepID;
            public String EmpDepName;
            public int DepOrder;
            public int EmpOrder;
            public String EmpBirthDate;
            public String EmpTitle;
            public String EmpTel;
            public String EmpComputer;
            public String EmpIPAddress;
            public String EmpDisk;
            public boolean EmpIsDeleted;
            public int EmpPageSize;
            public String EmpThemesName;
            public String EmpMenuType;
            public boolean EmpIsAgent;
            public String EmpSignUrl;
            public boolean EmpIsBind;
            public String EmpMacAddress;
            public String EmpTelNX;
            public String EmpTelWX;
            public String EmpFJNum;
            public String EmpOaMail;
            public String EmpComMail;
            public String EmpZWAddress;
            public String EmpWGAddress;
            public String EmpNote;
            public String EmpPort;
            public String EmpHead;
            public int EmpIsSub;
            public int DepartmentID;
            public String DepartmentName;
            public String DepartmentOrder;
            public String RoleName;
            public double PayManageCoeff;
            public double PaySkillCoeff;
        }
    }
}
