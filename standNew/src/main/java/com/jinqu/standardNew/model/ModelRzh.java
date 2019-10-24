package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelRzh {


    /**
     * total : 347
     * rows : [{"BaseLogID":1435,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-10T09:26:30.53","BaseLogIP":"192.168.0.196","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1434,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-10T09:24:54.803","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1433,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-10T09:14:54.167","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1432,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-10T09:11:32.01","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1431,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-09T17:26:15.897","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1430,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-09T17:24:45.857","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1429,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-09T17:20:55.437","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1428,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-09T17:19:44.727","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1427,"BaseLogEmpID":496,"EmpName":"周军","BaseLogDate":"2018-07-09T17:19:19.59","BaseLogIP":"192.168.1.244","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":496,"BaseLogRefHTML":"登录成功！","LogGrade":0},{"BaseLogID":1426,"BaseLogEmpID":1,"EmpName":"管理员","BaseLogDate":"2018-07-09T17:18:33.313","BaseLogIP":"192.168.1.245","BaseLogTypeID":0,"BaseLogRefTable":"login","BaseLogRefID":1,"BaseLogRefHTML":"登录成功！","LogGrade":0}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * BaseLogID : 1435
         * BaseLogEmpID : 1
         * EmpName : 管理员
         * BaseLogDate : 2018-07-10T09:26:30.53
         * BaseLogIP : 192.168.0.196
         * BaseLogTypeID : 0
         * BaseLogRefTable : login
         * BaseLogRefID : 1
         * BaseLogRefHTML : 登录成功！
         * LogGrade : 0
         */

        public int BaseLogID;
        public int BaseLogEmpID;
        public String EmpName;
        public String BaseLogDate;
        public String BaseLogIP;
        public int BaseLogTypeID;
        public String BaseLogRefTable;
        public int BaseLogRefID;
        public String BaseLogRefHTML;
        public int LogGrade;
    }
}
