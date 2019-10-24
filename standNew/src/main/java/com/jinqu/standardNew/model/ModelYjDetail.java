package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelYjDetail {


    /**
     * divid : null
     * iframeID : null
     * loadingType : null
     * dgID : null
     * sitePath : /JQWebMVC/
     * model : {"Id":120,"MailDate":"2017-06-05T13:47:02","MailTitle":"国际机场而","MailNote":"如举行牛角尖春公积金","MailFlag":0,"MailIsDelete":false,"MailIsBBC":0,"DeletionTime":"1900-01-01T00:00:00","LastModificationTime":"2017-06-05T13:46:28","LastModifierEmpId":662,"LastModifierEmpName":"xy","CreationTime":"2017-06-05T13:46:28","CreatorEmpId":662,"CreatorEmpName":"xy","AgenEmpId":0,"AgenEmpName":"","CreatorDepId":1305,"CreatorDepName":"院部","DeleterEmpId":0,"DeleterEmpName":"","FK_OaMailRead_Id":[]}
     * SendEmp : 周军,钟强,徐静,谢华明,蔡梅香,钟智宇,钟俊年,冼晓敏,xy
     * SendEmpID : 496,552,556,563,578,588,609,611,662
     * ReceiveFlag : 0
     * AttachData : [{"FileName":"00aef8c3-7b37-4f0e-9365-fa51d3bdda7e","RealName":"csimages490e15aea50f4549b46f313ee475887c_cstempcrop.jpgtemp","Size":"623940","LastModifiedTime":"2017-06-05 13:46:28","UploadTime":"2017-06-05 13:46:29.0000","EmpID":"662","EmpName":"xy"},{"FileName":"a09f9f7d-914f-442d-ab05-3b3eb657fbb4","RealName":"3.jpg","Size":"141825","LastModifiedTime":"2017-06-05 13:46:28","UploadTime":"2017-06-05 13:46:29.0170","EmpID":"662","EmpName":"xy"},{"FileName":"706199f3-a3d5-4d4a-bc8d-6f324541bcc8","RealName":"1.jpg","Size":"450069","LastModifiedTime":"2017-06-05 13:47:02","UploadTime":"2017-06-05 13:47:03.0370","EmpID":"662","EmpName":"xy"}]
     */

    public Object divid;
    public Object iframeID;
    public Object loadingType;
    public Object dgID;
    public String sitePath;
    public ModelBean model;
    public String SendEmp;
    public String SendEmpID;
    public int ReceiveFlag;
    public List<AttachDataBean> AttachData;

    public static class ModelBean {
        /**
         * Id : 120
         * MailDate : 2017-06-05T13:47:02
         * MailTitle : 国际机场而
         * MailNote : 如举行牛角尖春公积金
         * MailFlag : 0
         * MailIsDelete : false
         * MailIsBBC : 0
         * DeletionTime : 1900-01-01T00:00:00
         * LastModificationTime : 2017-06-05T13:46:28
         * LastModifierEmpId : 662
         * LastModifierEmpName : xy
         * CreationTime : 2017-06-05T13:46:28
         * CreatorEmpId : 662
         * CreatorEmpName : xy
         * AgenEmpId : 0
         * AgenEmpName :
         * CreatorDepId : 1305
         * CreatorDepName : 院部
         * DeleterEmpId : 0
         * DeleterEmpName :
         * FK_OaMailRead_Id : []
         */

        public int Id;
        public String MailDate;
        public String MailTitle;
        public String MailNote;
        public int MailFlag;
        public boolean MailIsDelete;
        public int MailIsBBC;
        public String DeletionTime;
        public String LastModificationTime;
        public int LastModifierEmpId;
        public String LastModifierEmpName;
        public String CreationTime;
        public int CreatorEmpId;
        public String CreatorEmpName;
        public int AgenEmpId;
        public String AgenEmpName;
        public int CreatorDepId;
        public String CreatorDepName;
        public int DeleterEmpId;
        public String DeleterEmpName;
        public List<?> FK_OaMailRead_Id;
    }

    public static class AttachDataBean {
        /**
         * FileName : 00aef8c3-7b37-4f0e-9365-fa51d3bdda7e
         * RealName : csimages490e15aea50f4549b46f313ee475887c_cstempcrop.jpgtemp
         * Size : 623940
         * LastModifiedTime : 2017-06-05 13:46:28
         * UploadTime : 2017-06-05 13:46:29.0000
         * EmpID : 662
         * EmpName : xy
         */

        public String FileName;
        public String RealName;
        public String Size;
        public String LastModifiedTime;
        public String UploadTime;
        public String EmpID;
        public String EmpName;
    }
}
