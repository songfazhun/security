package cn.duyunzhi.security.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
* @Author: Ezreal-d
* @Description: 时间工具类
* @Date: 2019/9/3 14:07
*/

public class DateUtil {

	public static String getNowDateTime(){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dateTimeFormatter.format(now);
	}
}
