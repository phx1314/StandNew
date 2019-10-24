package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelHt implements Serializable {


    /**
     * total : 2
     * rows : [{"Id":3,"ConSubNumber":"001","ConSubName":"海外购","ConSubDate":"1900-01-01T00:00:00","ConSubFee":3,"CustName":"常州","ArchNumber":"","CreatorEmpId":1,"AlreadySumFeeMoney":0,"AlreadySumInvoiceMoney":0,"UnPay":3,"ConSubTypeName":"其它外包","ConSubCategoryName":"业扩咨询","ConSubStatusName":"未签订","FlowID":1080,"FlowName":"付款合同审批单","FlowStatusID":3,"FlowStatusName":"审批结束","FlowTurnedEmpIDs":"496","FlowNodeOrder":0,"FlowFinishControls":"ConSubDate;ArchNumber","FlowNodeTypeID":0,"FlowMultiSignStatus":0,"row_number":1},{"Id":2,"ConSubNumber":"1","ConSubName":"搜索","ConSubDate":"1900-01-01T00:00:00","ConSubFee":0,"CustName":null,"ArchNumber":"","CreatorEmpId":496,"AlreadySumFeeMoney":0,"AlreadySumInvoiceMoney":0,"UnPay":0,"ConSubTypeName":"设计外包","ConSubCategoryName":"业扩咨询","ConSubStatusName":"已签订","FlowID":1041,"FlowName":"付款合同审批单","FlowStatusID":3,"FlowStatusName":"审批结束","FlowTurnedEmpIDs":"496","FlowNodeOrder":0,"FlowFinishControls":"ConSubDate;ArchNumber","FlowNodeTypeID":0,"FlowMultiSignStatus":0,"row_number":2}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 3
         * ConSubNumber : 001
         * ConSubName : 海外购
         * ConSubDate : 1900-01-01T00:00:00
         * ConSubFee : 3.0
         * CustName : 常州
         * ArchNumber :
         * CreatorEmpId : 1
         * AlreadySumFeeMoney : 0.0
         * AlreadySumInvoiceMoney : 0.0
         * UnPay : 3.0
         * ConSubTypeName : 其它外包
         * ConSubCategoryName : 业扩咨询
         * ConSubStatusName : 未签订
         * FlowID : 1080
         * FlowName : 付款合同审批单
         * FlowStatusID : 3
         * FlowStatusName : 审批结束
         * FlowTurnedEmpIDs : 496
         * FlowNodeOrder : 0
         * FlowFinishControls : ConSubDate;ArchNumber
         * FlowNodeTypeID : 0
         * FlowMultiSignStatus : 0
         * row_number : 1
         */

        public String ConSubNumber="";
        public String ConSubName="";
        public String ConSubDate="";
        public double ConSubFee;
        public String ArchNumber="";
        public double AlreadySumFeeMoney;
        public double AlreadySumInvoiceMoney;
        public double UnPay;
        public String ConSubTypeName="";
        public String ConSubCategoryName="";
        public String ConSubStatusName="";
        public int FlowID;

        public int Keyid;
        public int FatherID;
        public String ConDate="";
        public double FeeFact;
        public double FeeInvoice;
        public int ConFulfilType;
        public String ConFulfilTypeName="";
        public double ConBalanceFee;
        public double SumConFee;
        public double NoFee;
        public int FlowIDD;
        public String FlowName="";
        public int FlowStatusID;
        public String FlowStatusName="";
        public String FlowTurnedEmpIDs="";
        public int FlowNodeOrder;
        public String FlowFinishControls="";
        public int FlowNodeTypeID;
        public int FlowMultiSignStatus;
        public int CreateEmpId;
        public String state="";
        public List<?> children;
        public String FlowRefTable="";
        public int FlowRefID;
        public String MenuNameEng="";

        public int Id;
        public String ConNumber="";
        public String ConrName="";
        public double ConFee;
        public double ConrBalanceFee;
        public String ConIsFeeFinished;
        public int ConOtherFulfilType;
        public int CreatorEmpId;
        public String ConIsFeeFinishedName="";
        public double ConFactFee;
        public double SumFeeMoney;
        public String CustName="";
        public double SumInvoiceMoney;
        public double NoFeeMoney;
        public int row_number;


        public String ConName="";
    }


}
