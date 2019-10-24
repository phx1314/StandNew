package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelRw implements Serializable {


    /**
     * total : 3
     * rows : [{"ItemPath1":"[{\"rownum\":1,\"value\":355,\"text\":\"[Test111]Test111\",\"active\":\"Project\"},{\"rownum\":2,\"value\":356,\"text\":\"前期投标文件\",\"active\":\"ProjPhase\"}]","ItemPath2":"[]","ItemName":"前期投标文件","ItemEmpName":"管理员","ItemStatus":3,"DatePlanStart":"2016-09-01T00:00:00","DatePlanFinish":"2016-09-30T00:00:00","DateActualFinish":"1900-01-01T00:00:00","ItemType":1,"ItemAction":"项目策划","ProjId":1271,"TaskGroupId":356,"TaskSpecId":0,"TaskId":0,"TabId":0,"ProjTypeID":0,"row_number":1},{"ItemPath1":"[{\"rownum\":1,\"value\":349,\"text\":\"[Test-01]Test-01\",\"active\":\"Project\"},{\"rownum\":2,\"value\":350,\"text\":\"施工图\",\"active\":\"ProjPhase\"}]","ItemPath2":"[]","ItemName":"施工图","ItemEmpName":"管理员","ItemStatus":3,"DatePlanStart":"2016-09-01T00:00:00","DatePlanFinish":"2016-09-30T00:00:00","DateActualFinish":"1900-01-01T00:00:00","ItemType":1,"ItemAction":"项目策划","ProjId":1241,"TaskGroupId":350,"TaskSpecId":0,"TaskId":0,"TabId":0,"ProjTypeID":408,"row_number":2},{"ItemPath1":"[{\"rownum\":1,\"value\":323,\"text\":\"[1111]1111\",\"active\":\"Project\"},{\"rownum\":2,\"value\":324,\"text\":\"前期投标文件\",\"active\":\"ProjPhase\"}]","ItemPath2":"[{\"rownum\":1,\"value\":263,\"text\":\"商务部分\",\"active\":\"TaskSpec\"}]","ItemName":"商务部分","ItemEmpName":"管理员","ItemStatus":3,"DatePlanStart":"1900-01-01T00:00:00","DatePlanFinish":"1900-01-01T00:00:00","DateActualFinish":"1900-01-01T00:00:00","ItemType":2,"ItemAction":"专业策划","ProjId":1215,"TaskGroupId":324,"TaskSpecId":1470,"TaskId":263,"TabId":3,"ProjTypeID":0,"row_number":3}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean implements Serializable {
        /**
         * ItemPath1 : [{"rownum":1,"value":355,"text":"[Test111]Test111","active":"Project"},{"rownum":2,"value":356,"text":"前期投标文件","active":"ProjPhase"}]
         * ItemPath2 : []
         * ItemName : 前期投标文件
         * ItemEmpName : 管理员
         * ItemStatus : 3
         * DatePlanStart : 2016-09-01T00:00:00
         * DatePlanFinish : 2016-09-30T00:00:00
         * DateActualFinish : 1900-01-01T00:00:00
         * ItemType : 1
         * ItemAction : 项目策划
         * ProjId : 1271
         * TaskGroupId : 356
         * TaskSpecId : 0
         * TaskId : 0
         * TabId : 0
         * ProjTypeID : 0
         * row_number : 1
         */

        public String ItemPath1;
        public String ItemPath2;
        public String ItemName;
        public String ItemEmpName;
        public int ItemStatus;
        public int ItemEmpId;
        public String DatePlanStart;
        public String DatePlanFinish;
        public String DateActualFinish;
        public int ItemType;
        public String ItemAction;
        public int ProjId;
        public int TaskGroupId;
        public int TaskSpecId;
        public int TaskId;
        public int TabId;
        public int ProjTypeID;
        public int row_number;
        public String state;
    }
}
