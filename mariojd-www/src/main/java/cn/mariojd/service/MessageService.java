package cn.mariojd.service;

import cn.mariojd.dto.PageResult;
import cn.mariojd.entity.Message;

import java.util.List;

public interface MessageService {

	/**
	 * 获取Message List
	 * 
	 * @return List<Message>
	 */
	List<Message> getList(PageResult page);

	/**
	 * 保存Message
	 * 
	 * @param uid
	 * @param content
	 */
	void save(Integer uid, String content);

}
