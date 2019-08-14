package com.zhaoyh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhaoyh on 2019-08-08
 *
 * @author zhaoyh
 */
@Data
public class Storage implements Serializable {

    private Integer id;
    private String commodityCode;
    private String name;
    private Integer count;

}
