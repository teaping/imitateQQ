package com.cqgy.common;

import java.text.SimpleDateFormat;
import java.util.Date;
public class WriteErrorLog {
	    //��д������־
		public static void SaveError(String errorStr){
			 SimpleDateFormat sdf = new SimpleDateFormat();// ��ʽ��ʱ�� 
		        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// aΪam/pm�ı��  
		        Date date = new Date();// ��ȡ��ǰʱ�� 
			String error=sdf.format(date)+":"+errorStr;
			SaveFileString.SaveFileStr(error,"","SoftwareError.txt",true);
		}
}
