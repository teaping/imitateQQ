package com.cqgy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * ����������֤
 */
public class ValidateUtil {

	//�Ƿ�Ϊ����
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	//�Ƿ�Ϊ��ĸ
	public static boolean isString(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	//�Ƿ�Ϊ����
	public static boolean isChinese(String str) {
		// 编码
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
		 * ��֤����
		 */
		System.out.println(ValidateUtil.isString("javajpo"));
		System.out.println(ValidateUtil.isNumber("1111111"));
		System.out.println(ValidateUtil.isChinese("���"));
	}
	
}
