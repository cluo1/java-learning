package cn.mariojd.util;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author Mario on 2017-03-02
 *
 */
public class UUIDUtil {

	/**
	 * 返回随机生成的32位字符串
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
