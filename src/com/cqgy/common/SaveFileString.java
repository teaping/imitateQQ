package com.cqgy.common;

import java.awt.Panel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*
 *�����ļ��ַ�����
 */
public class SaveFileString {
	   //�����ļ��ַ�����
		public static void SaveFileStr(String str1, String str2,String path,boolean apend) {
			try {
				if (!new File(path).exists()) {
					new File(path).createNewFile();
				}
				//�����ļ�д����
				FileWriter objwrite=new FileWriter(path,apend);
				if (str1.trim().isEmpty()||str2.trim().isEmpty()) {
					if (!str1.trim().isEmpty()) {
						objwrite.write(str1);	
						objwrite.write("\r\n");
					}
					if (!str2.trim().isEmpty()) {
						objwrite.write(str2);
						objwrite.write("\r\n");
					}
				}else if (!str1.trim().isEmpty()&&!str2.trim().isEmpty()) {
					objwrite.write(str1);
					objwrite.write("\r\n");
					objwrite.write(str2);
				}
				
				objwrite.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			   WriteErrorLog.SaveError("��public void SaveFileStr(String str1, String str2,String path,boolean apend)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			}
		}
}
