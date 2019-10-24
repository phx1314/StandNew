package com.jinqu.standardNew.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/3/24.
 */

public class EntitySearch implements Serializable {
    public int type;//0时间1单选2多选3关键字
    public String title = "";
    public String value = "";
    public List<String> src = new ArrayList<>();
}
