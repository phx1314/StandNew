package com.jinqu.standardNew.model;

import java.io.Serializable;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelBell implements Serializable{


    /**
     * RefID : 0
     * RefTable :
     * CreationTime : /Date(1535680739687)/
     * Attributes : <Root><Notify EmpID="496" DateTime="" Status="0" /></Root>
     * RemindBefore : 1
     * RemindBeforeType : 3
     * ID : 1014
     * Content : 新建日程
     * StartTime : /Date(1535731200000)/
     * JoinEmpIDs : 496
     * CreatorEmpId : 496
     * NotifyTime : /Date(1535644800000)/
     * IsFullDay : true
     * CreatorEmpName : 周军
     * EndTime : /Date(1535731200000)/
     */

    public int RefID;
    public String RefTable;
    public String CreationTime;
    public String Attributes;
    public int RemindBefore;
    public int RemindBeforeType;
    public int ID;
    public String Content;
    public String StartTime;
    public String JoinEmpIDs;
    public int CreatorEmpId;
    public String NotifyTime;
    public boolean IsFullDay;
    public String CreatorEmpName;
    public String EndTime;
}
