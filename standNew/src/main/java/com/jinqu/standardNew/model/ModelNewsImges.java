package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelNewsImges {


    /**
     * rows : [{"Id":3,"NewsTypeID":0,"NewsTitle":"开会","NewsDate":"/Date(-2209017600000)/","NewsContent":"","NewsOrNotice":0,"LastModificationTime":"/Date(-2209017600000)/","LastModifierEmpId":0,"LastModifierEmpName":"","CreationTime":"/Date(-2209017600000)/","CreatorEmpId":0,"CreatorEmpName":"","FlowID":0,"FlowTime":"/Date(-2209017600000)/","AgenEmpId":0,"AgenEmpName":"","CreatorDepId":0,"CreatorDepName":"","DeleterEmpId":0,"DeleterEmpName":"","NewsImage":"NewsImage\\2018\\04\\28\\23abe790-4e17-42fc-899d-8847eddd6fbb.jpg","NewsDescription":"","FK_OaNew_NewsTypeID":null,"FK_OaNewsRead_Id":[]},{"Id":2,"NewsTypeID":0,"NewsTitle":"明天到大后天放假","NewsDate":"/Date(-2209017600000)/","NewsContent":"","NewsOrNotice":0,"LastModificationTime":"/Date(-2209017600000)/","LastModifierEmpId":0,"LastModifierEmpName":"","CreationTime":"/Date(-2209017600000)/","CreatorEmpId":0,"CreatorEmpName":"","FlowID":0,"FlowTime":"/Date(-2209017600000)/","AgenEmpId":0,"AgenEmpName":"","CreatorDepId":0,"CreatorDepName":"","DeleterEmpId":0,"DeleterEmpName":"","NewsImage":"NewsImage\\2018\\04\\28\\f0b380fa-a98b-4a1d-adcf-e8325c7e5960.jpg","NewsDescription":"","FK_OaNew_NewsTypeID":null,"FK_OaNewsRead_Id":[]}]
     * total : 2
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 3
         * NewsTypeID : 0
         * NewsTitle : 开会
         * NewsDate : /Date(-2209017600000)/
         * NewsContent :
         * NewsOrNotice : 0
         * LastModificationTime : /Date(-2209017600000)/
         * LastModifierEmpId : 0
         * LastModifierEmpName :
         * CreationTime : /Date(-2209017600000)/
         * CreatorEmpId : 0
         * CreatorEmpName :
         * FlowID : 0
         * FlowTime : /Date(-2209017600000)/
         * AgenEmpId : 0
         * AgenEmpName :
         * CreatorDepId : 0
         * CreatorDepName :
         * DeleterEmpId : 0
         * DeleterEmpName :
         * NewsImage : NewsImage\2018\04\28\23abe790-4e17-42fc-899d-8847eddd6fbb.jpg
         * NewsDescription :
         * FK_OaNew_NewsTypeID : null
         * FK_OaNewsRead_Id : []
         */

        public int Id;
        public int NewsTypeID;
        public String NewsTitle;
        public String NewsDate;
        public String NewsContent;
        public int NewsOrNotice;
        public String LastModificationTime;
        public int LastModifierEmpId;
        public String LastModifierEmpName;
        public String CreationTime;
        public int CreatorEmpId;
        public String CreatorEmpName;
        public int FlowID;
        public String FlowTime;
        public int AgenEmpId;
        public String AgenEmpName;
        public int CreatorDepId;
        public String CreatorDepName;
        public int DeleterEmpId;
        public String DeleterEmpName;
        public String NewsImage;
        public String NewsDescription;
        public Object FK_OaNew_NewsTypeID;
        public List<?> FK_OaNewsRead_Id;
    }
}
