package com.cqgy.util;

import java.util.Arrays;
import java.util.List;

public class regularSeeion {
	String zc = "";

	// 正则判断
	public static int RegularFinds(String s,String a,String b) {
		String str = s;
		String[] strs = str.split(",");
		List<String> list = Arrays.asList(strs);// 将String数组转化为list集合
		int i=Integer.parseInt(a);
		int y=Integer.parseInt(b);
		if(list.contains(a)&&list.contains(b)) return i+y;
		
		return -1;
	}
	
//	public static void main(String[] args) {
//			System.out.println(RegularFinds("aaa,bbb","aa","bbb"));
//	}

}
