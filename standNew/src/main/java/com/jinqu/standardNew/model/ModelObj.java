package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelObj implements Serializable {


    /**
     * total : 1
     * rows : [{"Id":1031,"ProjNumber":"20180813","ProjName":"厂房修建","ParentId":0,"DateCreate":"2018-08-13T00:00:00","DatePlanStart":"2018-08-13T00:00:00","DatePlanFinish":"2018-09-14T00:00:00","ColAttType5":0,"BridgeFact":0,"CustName":"勘察","CustLinkMan":"张三","ProjTaskContent":"厂房改造","ProjFeeSource":"政府出资","DatePlanDeliver":"2018-09-14T00:00:00","ProjDemand":"无","ProjNoteOther":"无","ProjVoltID":164,"state":"closed","ProjEmpName":"周军","PhaseNames":"选址选线,可研,初设,施工图,","ProjTypeName":"输变电","ProjPropertyName":"改造","ProjVoltName":"10kV","row_number":1}]
     */

    public int total;
    public List<Object> rows;


}
