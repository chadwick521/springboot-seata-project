package com.zhaoyh.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhaoyh on 2019-08-08
 *
 * @author zhaoyh
 */
@Data
public class OrderDTO implements Serializable {

    private Integer id;
    private String orderNo;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Double amount;

}
