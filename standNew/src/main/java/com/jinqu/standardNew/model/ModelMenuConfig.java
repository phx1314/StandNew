package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelMenuConfig implements Serializable {


    /**
     * grid : {"url":["project/Project/jsonNew?category=0","project/Project/jsonNew?category=1"],"queryParams":[{"fields[]":"ColAttType5, ProjName, ProjEmpName, PhaseNames, ProjTypeName, ProjPropertyName, ProjVoltName, DateCreate, DatePlanFinish, FlowProgress, opt","id":"#{Id}"},{"id":"#{Id}","fields[]":"ColAttType5, ProjName, ProjEmpName, PhaseNames, ProjTypeName, ProjPropertyName, ProjVoltName, DateCreate, DatePlanFinish, FlowProgress, opt"}],"rows":20,"addUrl":"","editUrl":"project/projectmobile/viewMajor?id=#{Id}","delUrl":"project/project/delMajor","listPage":"ProjectList"}
     * flow : {"isShowSave":true,"processor":"Project,Project.FlowProcessor.ProjectMajorProcessor","refTable":"Project"}
     * uploaders : [{"uploaderName":"UploadFile1","refTable":"Project"}]
     * search : [{"type":"text","baseorder":"","text":"请输入项目编号、项目名称","multiselect":"0","sqlstring":"{\"isGroup\":false,\"list\":[{\"Key\":\"txtLike\",\"Contract\":\"like\",\"Value\":\"#{value}\"}]}"},{"type":"time_ymd","baseorder":"","text":"请选择立项开始时间","multiselect":"0","sqlstring":"{\"isGroup\":false,\"list\":[{\"Key\":\"DateCreateS\",\"Contract\":\">=\",\"filedType\":\"Date\",\"Value\":\"#{value}\"}]}"},{"type":"time_ymd","baseorder":"","text":"请选择立项结束时间","multiselect":"0","sqlstring":"{\"isGroup\":false,\"list\":[{\"Key\":\"DateCreateE\",\"Contract\":\"<=\",\"filedType\":\"Date\",\"Value\":\"#{value}\"}]}"},{"type":"time_ymd","baseorder":"","text":"请选择计划完成开始时间","multiselect":"0","sqlstring":"{\"isGroup\":false,\"list\":[{\"Key\":\"DatePlanFinishS\",\"Contract\":\">=\",\"filedType\":\"Date\",\"Value\":\"#{value}\"}]}"},{"type":"time_ymd","baseorder":"","text":"请选择计划完成结束时间","multiselect":"0","sqlstring":"{\"isGroup\":false,\"list\":[{\"Key\":\"DatePlanFinishE\",\"Contract\":\"<=\",\"filedType\":\"Date\",\"Value\":\"#{value}\"}]}"},{"type":"basedata","baseorder":"003_001_","text":"请选择项目性质","multiselect":"1","sqlstring":"{\"isGroup\":false,\"list\":[{\"Key\":\"ProjPropertyID\",\"Contract\":\"in\",\"filedType\":\"Int\",\"Value\":\"#{value}\"}]}"},{"type":"basedata","baseorder":"003_003_","text":"请选择电压等级","multiselect":"1","sqlstring":"{\"isGroup\":false,\"list\":[{\"Key\":\"ProjVoltID\",\"Contract\":\"in\",\"filedType\":\"Int\",\"Value\":\"#{value}\"}]}"}]
     */

    public GridBean grid;
    public FlowBean flow;
    public List<UploadersBean> uploaders;
    public List<SearchBean> search;

    public static class GridBean implements Serializable{
        /**
         * url : ["project/Project/jsonNew?category=0","project/Project/jsonNew?category=1"]
         * queryParams : [{"fields[]":"ColAttType5, ProjName, ProjEmpName, PhaseNames, ProjTypeName, ProjPropertyName, ProjVoltName, DateCreate, DatePlanFinish, FlowProgress, opt"},{"id":"#{Id}","fields[]":"ColAttType5, ProjName, ProjEmpName, PhaseNames, ProjTypeName, ProjPropertyName, ProjVoltName, DateCreate, DatePlanFinish, FlowProgress, opt"}]
         * rows : 20
         * addUrl :
         * editUrl : project/projectmobile/viewMajor?id=#{Id}
         * delUrl : project/project/delMajor
         * listPage : ProjectList
         */

        public int rows;
        public String   addUrl;
        public String editUrl;
        public String delUrl;
        public String listPage;
        public List<String> url;
        public Object[] queryParams;

    }

    public static class FlowBean implements Serializable{
        /**
         * isShowSave : true
         * processor : Project,Project.FlowProcessor.ProjectMajorProcessor
         * refTable : Project
         */

        public boolean isShowSave;
        public String processor;
        public String refTable;
    }

    public static class UploadersBean implements Serializable{
        /**
         * uploaderName : UploadFile1
         * refTable : Project
         */

        public String uploaderName;
        public String refTable;
    }

    public static class SearchBean implements Serializable{
        /**
         * type : text
         * baseorder :
         * text : 请输入项目编号、项目名称
         * multiselect : 0
         * sqlstring : {"isGroup":false,"list":[{"Key":"txtLike","Contract":"like","Value":"#{value}"}]}
         */

        public String type;
        public String baseorder;
        public String text;
        public String multiselect;
        public String sqlstring;
        public String value;
        public String ids;
    }
}
