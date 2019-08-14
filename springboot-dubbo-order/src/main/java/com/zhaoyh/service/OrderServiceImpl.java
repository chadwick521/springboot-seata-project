package com.zhaoyh.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zhaoyh.entity.Order;
import com.zhaoyh.mapper.IOrderMapper;
import com.zhaoyh.model.OrderDTO;
import com.zhaoyh.model.StorageDTO;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhaoyh on 2019-08-08
 *
 * @author zhaoyh
 */
@Slf4j
@Service(interfaceClass = IOrderService.class)
@Component
public class OrderServiceImpl implements IOrderService {

    private AtomicInteger order_id = new AtomicInteger(0);

    @Autowired
    private IOrderMapper orderMapper;

    @Reference
    private IStorageService storageService;

    /**
     * 分布式事务测试
     *
     * @param orderDTO
     */
    @Override
    //@GlobalTransactional
    public void createOrder(OrderDTO orderDTO) {
        log.info("开始全局事务。XID=" + RootContext.getXID());

        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setCount(orderDTO.getCount());
        storageDTO.setCommodityCode(orderDTO.getCommodityCode());

        //1、扣减库存
        storageService.decreaseStorage(storageDTO);

        //2、创建订单
        orderDTO.setId(order_id.incrementAndGet());
        orderDTO.setOrderNo(UUID.randomUUID().toString());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        orderMapper.createOrder(order);

        // 构造异常抛出
        if (orderDTO.getUserId().length() == 5) {
            throw new RuntimeException("分布式事务异常..." + orderDTO.getOrderNo());
        }
    }
}
