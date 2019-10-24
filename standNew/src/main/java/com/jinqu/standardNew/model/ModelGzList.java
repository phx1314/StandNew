package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelGzList implements Serializable {


    public List<RowsBean> rows;

    public static class RowsBean implements Serializable {
        /**
         * id : 1740
         * parentID : 0
         * orderCode : 02
         * isMust : false
         * text : 经营管理
         * MenuNameEng : Business
         * attributes :
         * isSecond : false
         * iconCls : fa fa-folder-open-o
         * MenuImageUrl : fa fa-folder-open-o
         * children : [{"id":1749,"parentID":1740,"orderCode":"02_04","isMust":false,"text":"立项管理","MenuNameEng":"ProjectRegister","attributes":"","isSecond":false,"iconCls":"fa fa-cube","MenuImageUrl":"fa fa-cube","children":[{"id":1750,"parentID":1749,"orderCode":"02_04_01","isMust":true,"text":"项目信息登记","MenuNameEng":"ProjInfoRegister","attributes":"Project/project/MajorList","isSecond":false,"iconCls":"fa fa-cube","MenuImageUrl":"fa fa-cube","children":null}]},{"id":1755,"parentID":0,"orderCode":"02_05","isMust":false,"text":"项目合同","MenuNameEng":"Contract","attributes":" ","isSecond":false,"iconCls":"fa fa-suitcase","MenuImageUrl":"fa fa-suitcase","children":[{"id":1756,"parentID":0,"orderCode":"02_05_01","isMust":true,"text":"收款合同登记","MenuNameEng":"ContractInfo","attributes":"Bussiness/busscontract/list","isSecond":false,"iconCls":"fa fa-suitcase","MenuImageUrl":"fa fa-suitcase","children":null},{"id":1758,"parentID":0,"orderCode":"02_05_03","isMust":true,"text":"付款合同登记","MenuNameEng":"ContractSubInfo","attributes":"Bussiness/busscontractsub/list","isSecond":false,"iconCls":"fa fa-suitcase","MenuImageUrl":"fa fa-suitcase","children":null}]},{"id":1760,"parentID":0,"orderCode":"02_06","isMust":false,"text":"其它合同","MenuNameEng":"OtherContract","attributes":" ","isSecond":false,"iconCls":"fa fa-suitcase","MenuImageUrl":"fa fa-suitcase","children":[{"id":1761,"parentID":0,"orderCode":"02_06_01","isMust":true,"text":"收款合同登记","MenuNameEng":"OtherContractInfo","attributes":"Bussiness/busscontractother/list","isSecond":false,"iconCls":"fa fa-suitcase","MenuImageUrl":"fa fa-suitcase","children":null},{"id":1945,"parentID":0,"orderCode":"02_06_04","isMust":true,"text":"付款合同登记","MenuNameEng":"OtherContracSubtInfo","attributes":"Bussiness/busscontractother/listconothersub","isSecond":false,"iconCls":"fa fa-suitcase","MenuImageUrl":"fa fa-suitcase","children":null}]},{"id":1764,"parentID":0,"orderCode":"02_08","isMust":false,"text":"客户管理","MenuNameEng":"Customer","attributes":"","isSecond":false,"iconCls":"fa fa-users","MenuImageUrl":"fa fa-users","children":[{"id":1765,"parentID":0,"orderCode":"02_08_01","isMust":true,"text":"客户单位信息","MenuNameEng":"CustomerInfo","attributes":"Bussiness/busscustomer/list","isSecond":false,"iconCls":"fa fa-group","MenuImageUrl":"fa fa-group","children":null},{"id":1767,"parentID":0,"orderCode":"02_08_03","isMust":true,"text":"外委单位信息","MenuNameEng":"CustomerSubInfo","attributes":"Bussiness/busscustomer/sublist","isSecond":false,"iconCls":"fa fa-group","MenuImageUrl":"fa fa-group","children":null},{"id":1769,"parentID":0,"orderCode":"02_08_05","isMust":true,"text":"其它单位信息","MenuNameEng":"OtherCustomerInfo","attributes":"Bussiness/busscustomer/otherlist","isSecond":false,"iconCls":"fa fa-users","MenuImageUrl":"fa fa-users","children":null}]}]
         */

        public int id;
        public int parentID;
        public String orderCode;
        public boolean isMust;
        public String text;
        public ModelMenuConfig mModelMenuConfig;
        public String MenuNameEng;
        public String attributes;
        public boolean isSecond;
        public boolean HasChild;
        public String iconCls;
        public String MenuImageUrl;
        public List<RowsBean> children;
        public String MenuMobileUrl;
        public String RefTable;
        public String FileRefTable;
        public String MenuMobileConfig;
    }


}
