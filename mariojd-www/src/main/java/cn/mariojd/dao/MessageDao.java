package cn.mariojd.dao;

import cn.mariojd.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Mario
 */
public interface MessageDao {

    /**
     * 获取Message List
     *
     * @return List<Message>
     */
    List<Message> getList(Map<String, Object> params);

    /**
     * 获取Message总条数
     *
     * @return 查询总条数
     */
    int getCount();

    /**
     * 保存Message
     *
     * @param uid
     * @param content
     * @param postTime
     */
    void save(@Param("uid") Integer uid, @Param("content") String content, @Param("postTime") String postTime);

}
