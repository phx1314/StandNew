package com.jinqu.standardNew.model;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelFjList implements Serializable {


    /**
     * total : 1
     * rows : [{"ID":500978,"Name":"SalteeGannets_ZH-CN12304087974_1920x1080.jpg","Size":335669,"LastModifyDate":"/Date(1472723528000)/","UploadDate":"/Date(1473385765537)/","EmpName":"周军","Version":1,"Type":"attach"}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean implements Serializable {
        /**
         * ID : 500978
         * Name : SalteeGannets_ZH-CN12304087974_1920x1080.jpg
         * Size : 335669
         * LastModifyDate : /Date(1472723528000)/
         * UploadDate : /Date(1473385765537)/
         * EmpName : 周军
         * Version : 1
         * Type : attach
         */
        public String IDD;
        public int ID;
        public int AttachVersionID;
        public int AttachID;
        public String Name;
        public int Size;
        public String LastModifyDate;
        public String UploadDate;
        public String EmpName;
        public int Version;
        public String Type;
        public String Dir;
        public File file;
        public String fileGuid;
    }
}
