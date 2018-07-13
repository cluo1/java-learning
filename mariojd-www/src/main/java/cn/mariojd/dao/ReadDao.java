package cn.mariojd.dao;

import cn.mariojd.entity.Read;

import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
public interface ReadDao {

    /**
     * 获取Read List
     *
     * @return List<Read>
     */
    List<Read> getList(Map<String, Object> params);

    /**
     * 获取Read总条数
     *
     * @return 查询总条数
     */
    int getCount();
}
