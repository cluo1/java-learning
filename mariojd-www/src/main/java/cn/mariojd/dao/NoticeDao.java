package cn.mariojd.dao;

import cn.mariojd.entity.Notice;

import java.util.List;

/**
 * Created by Mario
 */
public interface NoticeDao {

    /**
     * 更新visit
     *
     * @param nid
     */
    void updateVisit(Integer nid);

    /**
     * 获取最新的前五条公告信息
     *
     * @return List<Notice>
     */
    List<Notice> getTop5Notices();
}
