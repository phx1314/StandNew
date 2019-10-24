package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelKhdw implements Serializable {


    /**
     * total : 4
     * rows : [{"Id":1008,"CustTypeId":1345,"CustTypeName":"政府机关","CustName":"哦我","CustAddress":"","CustRegion":"市内","CustPost":"","CustDate":"","LinkManCount":0,"BussCustomerEvaluateCount":0,"RecordCount":0,"CreatorEmpId":496},{"Id":1007,"CustTypeId":1345,"CustTypeName":"政府机关","CustName":"某某公司","CustAddress":"常州","CustRegion":"市内","CustPost":"211000","CustDate":"2018-08-01","LinkManCount":0,"BussCustomerEvaluateCount":0,"RecordCount":0,"CreatorEmpId":496},{"Id":4,"CustTypeId":1346,"CustTypeName":"民营企业","CustName":"勘察","CustAddress":"常州","CustRegion":"省内","CustPost":"","CustDate":"","LinkManCount":1,"BussCustomerEvaluateCount":1,"RecordCount":1,"CreatorEmpId":496},{"Id":3,"CustTypeId":0,"CustTypeName":"","CustName":"无","CustAddress":"","CustRegion":"","CustPost":"","CustDate":"","LinkManCount":1,"BussCustomerEvaluateCount":0,"RecordCount":0,"CreatorEmpId":496}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 1008
         * CustTypeId : 1345
         * CustTypeName : 政府机关
         * CustName : 哦我
         * CustAddress :
         * CustRegion : 市内
         * CustPost :
         * CustDate :
         * LinkManCount : 0
         * BussCustomerEvaluateCount : 0
         * RecordCount : 0
         * CreatorEmpId : 496
         */

        public int Id;
        public int CustTypeId;
        public String CustTypeName;
        public String CustName;
        public String CustAddress;
        public String CustRegion;
        public String CustPost;
        public String CustDate;
        public int LinkManCount;
        public int BussCustomerEvaluateCount;
        public int RecordCount;
        public int CreatorEmpId;

        public String TypeName;
        public String CustQualiGradeName;
        public String CustBusinessCreateDate;
        public String FlowRefTable;
        public int FlowRefID;
        public String MenuNameEng;
    }
}
