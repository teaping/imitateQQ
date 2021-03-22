package com.cqgy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharFrameUtil {
               public static boolean IspictureinMeme(String sendstr) {
            		//判断是否为表情包里的图片
       			if (sendstr.contains("图片")) {
       				if (sendstr.length()>"图片".length()) {
       					String resultpostion=sendstr.substring("图片".length());
       					if (new Integer(resultpostion)<10) {
       						return true;
       					}
       				}
       			}
       			
       			return false;
               }
               
               public static boolean isFilelink(String path) {
            	   //这个表达式可以验证带有window非法字符的文件路径，以及带有多个连续“/”的无意义的路径。不足之处是末尾不能有“/”。
				//^[a-zA-Z]:(//[^///:"<>/|]+)+$
            	  if (path.trim().isEmpty()) {
					return false;
				  }
            	  
          	    Pattern pattern = Pattern.compile("^[A-z]:\\\\\\S+$");
          	    Matcher matcher = pattern.matcher(path);
          	    return matcher.find();
			   }
               
               
               public static void main(String[] args) {
				 System.out.println(isFilelink("C:\\User\\Administrator\\Desktop\\网络编程实训\\Image\\20200608151612.gif"));
			  }
}
