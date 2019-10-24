package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelDtetail {


    /**
     * total : 1
     * rows : [{"Id":116,"CreationTime":"/Date(1493685786000)/","CreatorEmpId":518,"CreatorEmpName":"关杰","CreatorDepID":1309,"CreatorDepName":"输变电室","LastModificationTime":"/Date(1493685786000)/","LastModifierEmpId":518,"LastModifierEmpName":"关杰","DeleterEmpId":0,"DeleterEmpName":"","DeletionTime":"/Date(-2209017600000)/","AgenCreatorEmpId":0,"AgenCreatorEmpName":"","AgenLastModifierEmpId":0,"AgenLastModifierEmpName":"","AgenDeleterEmpId":0,"AgenDeleterEmpName":"","TalkId":33,"ReplyContent":"雨就好好"}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 116
         * CreationTime : /Date(1493685786000)/
         * CreatorEmpId : 518
         * CreatorEmpName : 关杰
         * CreatorDepID : 1309
         * CreatorDepName : 输变电室
         * LastModificationTime : /Date(1493685786000)/
         * LastModifierEmpId : 518
         * LastModifierEmpName : 关杰
         * DeleterEmpId : 0
         * DeleterEmpName :
         * DeletionTime : /Date(-2209017600000)/
         * AgenCreatorEmpId : 0
         * AgenCreatorEmpName :
         * AgenLastModifierEmpId : 0
         * AgenLastModifierEmpName :
         * AgenDeleterEmpId : 0
         * AgenDeleterEmpName :
         * TalkId : 33
         * ReplyContent : 雨就好好
         */

        public int Id;
        public String CreationTime;
        public int CreatorEmpId;
        public String CreatorEmpName;
        public int CreatorDepID;
        public String CreatorDepName;
        public String LastModificationTime;
        public int LastModifierEmpId;
        public String LastModifierEmpName;
        public int DeleterEmpId;
        public String DeleterEmpName;
        public String DeletionTime;
        public int AgenCreatorEmpId;
        public String AgenCreatorEmpName;
        public int AgenLastModifierEmpId;
        public String AgenLastModifierEmpName;
        public int AgenDeleterEmpId;
        public String AgenDeleterEmpName;
        public int TalkId;
        public String ReplyContent;
    }
}