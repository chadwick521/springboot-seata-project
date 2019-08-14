package com.zhaoyh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zhaoyh.model.OrderDTO;
import com.zhaoyh.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyh on 2019-08-13
 *
 * @author zhaoyh
 */
@Slf4j
@RestController
public class MainController {

    @Reference
    private IOrderService orderService;

    @PostMapping(value = "/addOrder")
    public JSONObject addOrder(@RequestBody OrderDTO orderDTO) {
        JSONObject json = new JSONObject();
        orderService.createOrder(orderDTO);
        json.put("result", true);
        return json;
    }
}
