package cn.mariojd.service;

import cn.mariojd.dto.PageResult;
import cn.mariojd.entity.Read;

import java.util.List;

/**
 * Created by Mario
 */
public interface ReadService {

    /**
     * 获取Read List
     *
     * @return List<Read>
     */
    List<Read> getList(PageResult page);
}
