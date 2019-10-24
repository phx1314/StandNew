package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelShPjdList {


    /**
     * total : 2
     * rows : [{"Id":31,"FlowID":13,"FlowNodeID":29,"ExeActionID":47,"ExeActionDate":"2017-05-09T14:39:35.27","ExeArgEmpId":0,"ExeEmpId":1,"ExeNote":"同意","ExeEmpName":"管理员","ExeArgEmpName":"","NewExeActionDate":"2017-05-09 14:39:35","ActionName":"完成","FlowNodeName":"发起审批","row_number":1},{"Id":32,"FlowID":13,"FlowNodeID":30,"ExeActionID":39,"ExeActionDate":"2017-05-09T14:39:35.28","ExeArgEmpId":0,"ExeEmpId":496,"ExeNote":"","ExeEmpName":"周军","ExeArgEmpName":"","NewExeActionDate":"2017-05-09 14:39:35","ActionName":"轮到","FlowNodeName":"车辆管理员","row_number":2}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 31
         * FlowID : 13
         * FlowNodeID : 29
         * ExeActionID : 47
         * ExeActionDate : 2017-05-09T14:39:35.27
         * ExeArgEmpId : 0
         * ExeEmpId : 1
         * ExeNote : 同意
         * ExeEmpName : 管理员
         * ExeArgEmpName :
         * NewExeActionDate : 2017-05-09 14:39:35
         * ActionName : 完成
         * FlowNodeName : 发起审批
         * row_number : 1
         */

        public int Id;
        public int FlowID;
        public int FlowNodeID;
        public int ExeActionID;
        public String ExeActionDate;
        public int ExeArgEmpId;
        public int ExeEmpId;
        public String ExeNote;
        public String ExeEmpName;
        public String ExeArgEmpName;
        public String NewExeActionDate;
        public String ActionName;
        public String FlowNodeName;
        public int row_number;
    }
}
