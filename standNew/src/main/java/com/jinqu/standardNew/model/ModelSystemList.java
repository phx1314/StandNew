package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelSystemList {


    /**
     * total : 14
     * rows : [{"Id":256,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1477646187193)/","MessReadIsDeleted":false,"MessTitle":"项目信息备案[222]222222流程审批通过！","MessLinkTitle":"项目信息备案","MessLinkUrl":"bussiness/BussProjInfoRecords/edit?id=157&flowNodeID=0&flowMultiSignID=0","MessIsDeleted":false,"MessEmpId":1,"MessDate":"/Date(1477645978353)/","MessEmpName":"管理员","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":96,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473492539407)/","MessReadIsDeleted":false,"MessTitle":"798","MessLinkTitle":"798","MessLinkUrl":"design/DesEvent/edit?id=34&isRead=1","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1473413549603)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":88,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473467495347)/","MessReadIsDeleted":false,"MessTitle":"AAA","MessLinkTitle":"AAA","MessLinkUrl":"design/DesEvent/edit?id=28&isRead=1","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1473338390787)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":87,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473813758193)/","MessReadIsDeleted":false,"MessTitle":"789","MessLinkTitle":"789","MessLinkUrl":"design/DesEvent/edit?id=27&isRead=1","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1473337048940)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":86,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473780310197)/","MessReadIsDeleted":false,"MessTitle":"345","MessLinkTitle":"345","MessLinkUrl":"design/DesEvent/edit?id=26&isRead=1","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1473329204797)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":54,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473061860490)/","MessReadIsDeleted":false,"MessTitle":"[周军--索资]","MessLinkTitle":"[周军--索资]","MessLinkUrl":"Design/DesExch/AddExchPlan","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1472649110207)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":51,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473061867757)/","MessReadIsDeleted":false,"MessTitle":"[周军--锁资]","MessLinkTitle":"[周军--锁资]","MessLinkUrl":"Design/DesExch/AddExchPlan","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1472375948600)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":50,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473061865543)/","MessReadIsDeleted":false,"MessTitle":"[周军--锁资]","MessLinkTitle":"[周军--锁资]","MessLinkUrl":"Design/DesExch/AddExchPlan","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1472375929403)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":49,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473061873247)/","MessReadIsDeleted":false,"MessTitle":"[周军--锁资]","MessLinkTitle":"[周军--锁资]","MessLinkUrl":"Design/DesExch/AddExchPlan","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1472283977377)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600},{"Id":48,"MessReadEmpId":1,"MessReadEmpName":"管理员","MessReadDate":"/Date(1473061863477)/","MessReadIsDeleted":false,"MessTitle":"[周军--锁资]","MessLinkTitle":"[周军--锁资]","MessLinkUrl":"Design/DesExch/AddExchPlan","MessIsDeleted":false,"MessEmpId":496,"MessDate":"/Date(1472270276817)/","MessEmpName":"周军","MessIsSystem":true,"MessDialogWidth":800,"MessDialogHeight":600}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 256
         * MessReadEmpId : 1
         * MessReadEmpName : 管理员
         * MessReadDate : /Date(1477646187193)/
         * MessReadIsDeleted : false
         * MessTitle : 项目信息备案[222]222222流程审批通过！
         * MessLinkTitle : 项目信息备案
         * MessLinkUrl : bussiness/BussProjInfoRecords/edit?id=157&flowNodeID=0&flowMultiSignID=0
         * MessIsDeleted : false
         * MessEmpId : 1
         * MessDate : /Date(1477645978353)/
         * MessEmpName : 管理员
         * MessIsSystem : true
         * MessDialogWidth : 800
         * MessDialogHeight : 600
         */

        public int Id;
        public int MessReadEmpId;
        public String MessReadEmpName;
        public String MessReadDate;
        public boolean MessReadIsDeleted;
        public String MessTitle;
        public String MessLinkTitle;
        public String MessLinkUrl;
        public boolean MessIsDeleted;
        public int MessEmpId;
        public String MessDate;
        public String MessEmpName;
        public boolean MessIsSystem;
        public int MessDialogWidth;
        public int MessDialogHeight;


        public boolean isChecked;
    }
}
