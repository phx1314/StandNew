package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelxmdtList implements Serializable {


    /**
     * total : 2
     * rows : [{"Id":2,"ProjNumber":"22dsd","ProjName":"22222asda","TaskGroupStatus":2,"TaskGroupId":2,"ProjPhaseId":1079,"ProjPhaseName":"选址选线","ProjPhaseEmpId":562,"ProjPhaseEmpName":"刘伟","DatePlanStart":"1900-01-01T00:00:00","DatePlanFinish":"1900-01-01T00:00:00","TaskFinishCount":0,"TaskTotalCount":2,"row_number":1},{"Id":2,"ProjNumber":"22dsd","ProjName":"22222asda","TaskGroupStatus":0,"TaskGroupId":3,"ProjPhaseId":61,"ProjPhaseName":"施工图","ProjPhaseEmpId":562,"ProjPhaseEmpName":"刘伟","DatePlanStart":"1900-01-01T00:00:00","DatePlanFinish":"1900-01-01T00:00:00","TaskFinishCount":0,"TaskTotalCount":0,"row_number":2}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean implements  Serializable{
        /**
         * Id : 2
         * ProjNumber : 22dsd
         * ProjName : 22222asda
         * TaskGroupStatus : 2
         * TaskGroupId : 2
         * ProjPhaseId : 1079
         * ProjPhaseName : 选址选线
         * ProjPhaseEmpId : 562
         * ProjPhaseEmpName : 刘伟
         * DatePlanStart : 1900-01-01T00:00:00
         * DatePlanFinish : 1900-01-01T00:00:00
         * TaskFinishCount : 0
         * TaskTotalCount : 2
         * row_number : 1
         */

        public int Id;
        public String ProjNumber;
        public String ProjName;
        public int TaskGroupStatus;
        public int TaskGroupId;
        public int ProjPhaseId;
        public String ProjPhaseName;
        public int ProjPhaseEmpId;
        public String ProjPhaseEmpName;
        public String DatePlanStart;
        public String DatePlanFinish;
        public int TaskFinishCount;
        public int TaskTotalCount;
        public int row_number;
    }
}
