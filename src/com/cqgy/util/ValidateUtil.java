package com.cqgy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * 正则表达是验证
 */
public class ValidateUtil {

	//是否为数字
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	//是否为字母
	public static boolean isString(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	//是否为中文
	public static boolean isChinese(String str) {
		// 缂栫爜
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	public static boolean isIPLegal(String ipStr){
	    if(ipStr.trim().isEmpty())
	    {
	    	 return false;
	    }
	    //Pattern pattern = Pattern.compile("^((http|https)://)((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$");
	    Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$");
	    Matcher matcher = pattern.matcher(ipStr);
	    return matcher.find();
	}
	
	
	public static void main(String[] args) {
		/*
		 * 验证部分
		 */
		System.out.println(ValidateUtil.isString("javajpo"));
		System.out.println(ValidateUtil.isNumber("1111111"));
		System.out.println(ValidateUtil.isChinese("你好"));
	}
	
}
