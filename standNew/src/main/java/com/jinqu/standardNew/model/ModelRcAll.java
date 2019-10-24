package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelRcAll {


    /**
     * total : 13
     * rows : [{"ID":14,"Content":"新建日程","StartTime":"2018-08-07T00:00:00","EndTime":"2018-08-07T00:00:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":true,"JoinEmpNames":"周军,","row_number":1},{"ID":13,"Content":"新建日程","StartTime":"2018-08-06T00:00:00","EndTime":"2018-08-06T00:00:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":true,"JoinEmpNames":"周军,","row_number":2},{"ID":12,"Content":"大会议室-测试","StartTime":"2018-06-25T09:00:00","EndTime":"2018-06-28T10:00:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,曹瑛静,","row_number":3},{"ID":7,"Content":"大会议室-啊","StartTime":"2018-04-20T16:29:00","EndTime":"2018-04-20T17:29:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,范丹,","row_number":4},{"ID":8,"Content":"大会议室-上班","StartTime":"2018-04-20T16:20:00","EndTime":"2018-04-20T17:20:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,陈嘉平,","row_number":5},{"ID":10,"Content":"大会议室-就","StartTime":"2018-04-20T14:56:00","EndTime":"2018-04-20T14:56:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,赖茹婉,","row_number":6},{"ID":11,"Content":"大会议室-就","StartTime":"2018-04-20T14:56:00","EndTime":"2018-04-20T14:56:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,赖茹婉,","row_number":7},{"ID":9,"Content":"大会议室-开会","StartTime":"2018-04-20T14:49:00","EndTime":"2018-04-20T14:49:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,吴四,","row_number":8},{"ID":6,"Content":"大会议室-开会","StartTime":"2018-04-20T14:48:18.107","EndTime":"2018-04-20T14:48:18.107","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,叶丹,","row_number":9},{"ID":5,"Content":"大会议室-zzz","StartTime":"2018-04-20T14:13:28.81","EndTime":"2018-04-20T14:13:28.81","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,","row_number":10},{"ID":4,"Content":"大会议室-开会","StartTime":"2018-04-20T13:41:12.16","EndTime":"2018-04-20T13:41:12.16","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,吴四,","row_number":11},{"ID":3,"Content":"大会议室-开会","StartTime":"2018-04-20T13:16:52.42","EndTime":"2018-04-20T13:16:52.42","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,122,","row_number":12},{"ID":2,"Content":"大会议室-信息","StartTime":"2018-04-19T00:00:00","EndTime":"2018-04-19T00:00:00","EmpName":"周军","DepartmentName":"院部","IsFullDay":false,"JoinEmpNames":"周军,","row_number":13}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * ID : 14
         * Content : 新建日程
         * StartTime : 2018-08-07T00:00:00
         * EndTime : 2018-08-07T00:00:00
         * EmpName : 周军
         * DepartmentName : 院部
         * IsFullDay : true
         * JoinEmpNames : 周军,
         * row_number : 1
         */

        public int ID;
        public String Content;
        public String StartTime;
        public String EndTime;
        public String EmpName;
        public String DepartmentName;
        public boolean IsFullDay;
        public String JoinEmpNames;
        public int row_number;
    }
}
