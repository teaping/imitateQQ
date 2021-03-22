package com.cqgy.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
 * ��ȡ�ļ��ַ�����
 */
public class ReaderFileString {
	//��ȡ�˺ź�����
		public static ArrayList<String>  readerFilestr(String path) throws IOException{
			BufferedReader  reader = null;
			try {
				if (new File(path).exists()) {
					ArrayList<String> userinfo=new ArrayList<>();
					  //ʵ����һ��BufferedReader�������
			    	  reader=new BufferedReader(new FileReader(path));
			    	 //��ȡ����
			    	  String line=null;
			    	  while((line=reader.readLine())!=null){
			    		  userinfo.add(line);
			    	  }
			    	  reader.close();
			    	  return userinfo;
				}
				
				return null;
		    	  
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				  if (reader!=null) {
					  reader.close();
					}
				WriteErrorLog.SaveError("��public ArrayList<String> readerFilestr(String path)�г����쳣,�쳣��Ϣ:"+e.toString());
				return null;
			}
		}
}
