package cn.mariojd.util;

/**
 * Random随机数字工具类
 * 
 * @author Mario on 2017-03-08
 *
 */
public class RandomUtil {

	/**
	 * 返回随机生成的6位字符串
	 * 
	 * @return
	 */
	public static String getRandom() {
		String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		return code;
	}

}
