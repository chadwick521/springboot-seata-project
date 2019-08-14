package com.zhaoyh.mapper;

import com.zhaoyh.entity.Storage;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhaoyh on 2019-08-08
 *
 * @author zhaoyh
 */
@Mapper
public interface IStorageMapper {

    int decreaseStorage(Storage storage);

}
