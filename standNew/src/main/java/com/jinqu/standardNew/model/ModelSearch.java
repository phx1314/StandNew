package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class ModelSearch implements Serializable {


    /**
     * isGroup : false
     * list : [{"Key":"NewsTitle,NewsContent","Contract":"like","Value":"uu"}]
     */

    public boolean isGroup;
    public List<ListBean> list = new ArrayList<>();

    public static class ListBean {
        /**
         * Key : NewsTitle,NewsContent
         * Contract : like
         * Value : uu
         */

        public String Key;
        public String Contract = "like";
        public String Value="";
        public String filedType = "Int";
    }
}
