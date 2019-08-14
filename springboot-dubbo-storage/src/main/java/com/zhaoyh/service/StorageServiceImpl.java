package com.zhaoyh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhaoyh.entity.Storage;
import com.zhaoyh.mapper.IStorageMapper;
import com.zhaoyh.model.StorageDTO;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyh on 2019-08-08
 *
 * @author zhaoyh
 */
@Slf4j
@Service(interfaceClass = IStorageService.class)
@Component
public class StorageServiceImpl implements IStorageService {

    @Autowired
    private IStorageMapper storageMapper;

    @Override
    public int decreaseStorage(StorageDTO storageDTO) {
        log.info("全局事务XID："+ RootContext.getXID());
        Storage storage = new Storage();
        BeanUtils.copyProperties(storageDTO, storage);
        return storageMapper.decreaseStorage(storage);
    }

}
