package com.cqgy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharFrameUtil {
               public static boolean IspictureinMeme(String sendstr) {
            		//�ж��Ƿ�Ϊ��������ͼƬ
       			if (sendstr.contains("ͼƬ")) {
       				if (sendstr.length()>"ͼƬ".length()) {
       					String resultpostion=sendstr.substring("ͼƬ".length());
       					if (new Integer(resultpostion)<10) {
       						return true;
       					}
       				}
       			}
       			
       			return false;
               }
               
               public static boolean isFilelink(String path) {
            	   //������ʽ������֤����window�Ƿ��ַ����ļ�·�����Լ����ж��������/�����������·��������֮����ĩβ�����С�/����
				//^[a-zA-Z]:(//[^///:"<>/|]+)+$
            	  if (path.trim().isEmpty()) {
					return false;
				  }
            	  
          	    Pattern pattern = Pattern.compile("^[A-z]:\\\\\\S+$");
          	    Matcher matcher = pattern.matcher(path);
          	    return matcher.find();
			   }
               
               
               public static void main(String[] args) {
				 System.out.println(isFilelink("C:\\User\\Administrator\\Desktop\\������ʵѵ\\Image\\20200608151612.gif"));
			  }
}
