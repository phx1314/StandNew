package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelBd implements Serializable {


    /**
     * total : 11
     * rows : [{"FlowID":1075,"FlowRefID":5,"FlowMultiSignID":0,"FlowRefTable":"OaLeave","FlowName":"请假申请单","FlowNodeID":1190,"FlowNodeName":"部门主任审批","FlowStartDate":"/Date(1532486198400)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"OA/OaLeave/edit?id=5&flowNodeID=1190&flowMultiSignID=0","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1532486198773)/","FlowTitle":"请病假2.0小时","FlowSummary":"[{\"Key\":\"请假类型\",\"Value\":\"病假\"},{\"Key\":\"请假时长\",\"Value\":\"2.0小时\"},{\"Key\":\"请假原因\",\"Value\":\"hi\"},{\"Key\":\"开始时间\",\"Value\":\"2018-07-25\"}]","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1074,"FlowRefID":4,"FlowMultiSignID":0,"FlowRefTable":"OaStampUse","FlowName":"印章申请-管理员审批","FlowNodeID":1188,"FlowNodeName":"印章管理员审批","FlowStartDate":"/Date(1532484840053)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"oa/OaStampUse/edit?id=4&flowNodeID=1188&flowMultiSignID=0","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1532484841770)/","FlowTitle":"用章审批","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1066,"FlowRefID":3,"FlowMultiSignID":5,"FlowRefTable":"OaGoing","FlowName":"外出申请单","FlowNodeID":1157,"FlowNodeName":"人事审批","FlowStartDate":"/Date(1529904367430)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"-1","FlowNodeStatusID":0,"FlowNodeStatusName":"轮到","FlowNodeUrl":"OA/OaGoing/edit?id=3&flowNodeID=1157&flowMultiSignID=5","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529904392133)/","FlowTitle":"常州会议0.5天","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1065,"FlowRefID":2,"FlowMultiSignID":6,"FlowRefTable":"OaGoing","FlowName":"外出申请单","FlowNodeID":1154,"FlowNodeName":"人事审批","FlowStartDate":"/Date(1529904334367)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"-1","FlowNodeStatusID":0,"FlowNodeStatusName":"轮到","FlowNodeUrl":"OA/OaGoing/edit?id=2&flowNodeID=1154&flowMultiSignID=6","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529904414450)/","FlowTitle":"常州出差0.5天","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1064,"FlowRefID":4,"FlowMultiSignID":0,"FlowRefTable":"OaLeave","FlowName":"请假申请单","FlowNodeID":1150,"FlowNodeName":"部门主任审批","FlowStartDate":"/Date(1529898482140)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"OA/OaLeave/edit?id=4&flowNodeID=1150&flowMultiSignID=0","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529898482217)/","FlowTitle":"请事假0.0小时","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1063,"FlowRefID":3,"FlowMultiSignID":0,"FlowRefTable":"OaStampUse","FlowName":"印章申请-管理员审批","FlowNodeID":1148,"FlowNodeName":"印章管理员审批","FlowStartDate":"/Date(1529659469453)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"oa/OaStampUse/edit?id=3&flowNodeID=1148&flowMultiSignID=0","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529659469473)/","FlowTitle":"用章审批","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1061,"FlowRefID":3,"FlowMultiSignID":0,"FlowRefTable":"OaEquipGetFlow","FlowName":"设备采购申请单","FlowNodeID":1141,"FlowNodeName":"部门审批","FlowStartDate":"/Date(1529634333987)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"Oa/OaEquipGet/edit?id=3&flowNodeID=1141","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529634334017)/","FlowTitle":"[2018-06-22]周军的设备采购申请单","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1058,"FlowRefID":2,"FlowMultiSignID":0,"FlowRefTable":"OaEquipUseOfficeFlow","FlowName":"办公用品领用申请单","FlowNodeID":1132,"FlowNodeName":"批准","FlowStartDate":"/Date(1529630177863)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"Oa/OaEquipUse/edit?id=2&flowNodeID=1132","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529630177983)/","FlowTitle":"周军的办公用品领用申请单","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1057,"FlowRefID":8,"FlowMultiSignID":0,"FlowRefTable":"CarUse","FlowName":"车辆申请","FlowNodeID":1130,"FlowNodeName":"车辆管理员","FlowStartDate":"/Date(1529565020663)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"Oa/OaCarUse/edit?id=8&flowNodeID=1130","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529565020683)/","FlowTitle":"[外景]:出车申请","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1054,"FlowRefID":3,"FlowMultiSignID":0,"FlowRefTable":"OaFileSend","FlowName":"文件发送单","FlowNodeID":1124,"FlowNodeName":"批准人","FlowStartDate":"/Date(1529559660890)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"管理员","CreatorEmpId":1,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"Oa/OaFileSend/edit?id=3&flowNodeID=1124","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529559660933)/","FlowTitle":"发文部门[行政人事部],文件名称:额","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0},{"FlowID":1052,"FlowRefID":2,"FlowMultiSignID":0,"FlowRefTable":"OaFileSend","FlowName":"文件发送单","FlowNodeID":1120,"FlowNodeName":"批准人","FlowStartDate":"/Date(1529385447153)/","FlowFinishDate":"/Date(-2209017600000)/","CreatorEmpName":"周军","CreatorEmpId":496,"FlowStatusID":"2","FlowStatusName":"轮到（周军）","FlowModular":2,"FlowModularName":"办公表单","FlowNodeEmpId":0,"FlowNodeEmpName":"周军","FlowNodeTypeID":"0","FlowNodeStatusID":29,"FlowNodeStatusName":"轮到","FlowNodeUrl":"Oa/OaFileSend/edit?id=2&flowNodeID=1120","FlowNodeDate":"/Date(-2209017600000)/","FlowNodeFromDateTime":"/Date(1529385447197)/","FlowTitle":"发文部门[院部],文件名称:嗯","FlowSummary":"","DialogWidth":800,"DialogHeight":600,"FlowType":0,"FormTemplateID":0}]
     */

    public int total;
    public List<RowsBean> rows;


    public static class RowsBean implements Serializable {
        /**
         * FlowID : 1075
         * FlowRefID : 5
         * FlowMultiSignID : 0
         * FlowRefTable : OaLeave
         * FlowName : 请假申请单
         * FlowNodeID : 1190
         * FlowNodeName : 部门主任审批
         * FlowStartDate : /Date(1532486198400)/
         * FlowFinishDate : /Date(-2209017600000)/
         * CreatorEmpName : 周军
         * CreatorEmpId : 496
         * FlowStatusID : 2
         * FlowStatusName : 轮到（周军）
         * FlowModular : 2
         * FlowModularName : 办公表单
         * FlowNodeEmpId : 0
         * FlowNodeEmpName : 周军
         * FlowNodeTypeID : 0
         * FlowNodeStatusID : 29
         * FlowNodeStatusName : 轮到
         * FlowNodeUrl : OA/OaLeave/edit?id=5&flowNodeID=1190&flowMultiSignID=0
         * FlowNodeDate : /Date(-2209017600000)/
         * FlowNodeFromDateTime : /Date(1532486198773)/
         * FlowTitle : 请病假2.0小时
         * FlowSummary : [{"Key":"请假类型","Value":"病假"},{"Key":"请假时长","Value":"2.0小时"},{"Key":"请假原因","Value":"hi"},{"Key":"开始时间","Value":"2018-07-25"}]
         * DialogWidth : 800
         * DialogHeight : 600
         * FlowType : 0
         * FormTemplateID : 0
         */
        public Object obj;
        public String MenuNameEng;
        public String text;
        public ModelMenuConfig mModelMenuConfig;
        public String _processor;
        public String Title;
        public String ApplyBeginDate;
        public String RefTable_file;
        public Object FlowID;
//        public int FlowRefID;
        public int FlowMultiSignID;
        public String FlowRefTable;
        public String FlowName;
        public int FlowNodeID;
        public String FlowNodeName;
        public String FlowStartDate;
        public String FlowFinishDate;
        public String CreatorEmpName;
        public int CreatorEmpId;
        public String FlowStatusID;
        public String FlowStatusName;
        public int FlowModular;
        public String FlowModularName;
        public int FlowNodeEmpId;
        public String FlowNodeEmpName;
        public String FlowNodeTypeID;
        public int FlowNodeStatusID;
        public String FlowNodeStatusName;
        public String FlowNodeUrl;
        public String FlowNodeDate;
        public String FlowNodeFromDateTime;
        public String FlowTitle;
        public String FlowSummary;
        public int DialogWidth;
        public int DialogHeight;
        public int FlowType;
        public int FormTemplateID;
        public String _action;
        public boolean IsNew;
        public int Id;
        public String LeaveTypeName;
        public String CreationTime;
        public String TimeSpanDay;
        public String TimeSpanHour;
        public String ApplyNote;
        public String GoingTypeName;
        public int TimeSpanAm;
        public String ApplyEndDate;
        public int TimeSpanPm;
        public String ApplyTotalDayNum;
        public String ApplyTarget;
        public String WorkOverHour;
        public boolean isNeedSp;
        public String _summary;
        public List<RowsBean> rows;
        public boolean IsSave;
        public String MenuMobileConfig;
    }

    public static class FlowSummary {

        /**
         * Key : 请假类型
         * Value : 病假
         */

        public String Key;
        public String Value;
    }

}
