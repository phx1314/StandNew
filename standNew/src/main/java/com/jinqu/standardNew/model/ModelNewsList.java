package com.jinqu.standardNew.model;

import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelNewsList {


    /**
     * total : 5
     * rows : [{"Id":5,"NewsTypeID":10,"NewsTypeName":"要闻","NewsTitle":"顶顶顶顶","CreatorEmpName":"管理员","NewsDate":"2017-04-25"},{"Id":4,"NewsTypeID":11,"NewsTypeName":"其它","NewsTitle":"大","CreatorEmpName":"管理员","NewsDate":"2017-04-17"},{"Id":3,"NewsTypeID":10,"NewsTypeName":"要闻","NewsTitle":"多撒多","CreatorEmpName":"管理员","NewsDate":"2017-04-17"},{"Id":2,"NewsTypeID":8,"NewsTypeName":"通知","NewsTitle":"多撒多","CreatorEmpName":"管理员","NewsDate":"2017-04-17"},{"Id":1,"NewsTypeID":9,"NewsTypeName":"公告","NewsTitle":"多撒大所多","CreatorEmpName":"管理员","NewsDate":"2017-04-17"}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * Id : 5
         * NewsTypeID : 10
         * NewsTypeName : 要闻
         * NewsTitle : 顶顶顶顶
         * CreatorEmpName : 管理员
         * NewsDate : 2017-04-25
         */

        public int Id;
        public int NewsTypeID;
        public String NewsTypeName;
        public String NewsTitle;
        public String CreatorEmpName;
        public String NewsDate;
        public String NewsImage;
    }
}
