package com.cqgy.method;

import java.util.Date;

import org.bson.Document;

import com.cqgy.db.Cteatadb;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class CteataDao {

	/***
	 * �������
	 * @param userId  �û�id
	 * @param sendstr  ����
	 * @param date  ʱ��
	 * @return
	 */
	public boolean InsertSeeion(String userName,String cteraNames,String userId, String sendstr, Date date) {
		// TODO Auto-generated method stub
		return Cteatadb.InsertSeeion(userName,cteraNames,userId,sendstr,date);
	}

	/**
	 * ȡ������
	 * @param cteraNames Ⱥ��
	 * @return
	 */
	public MongoCursor<Document> SelectCtreata(String cteraNames) {
		// TODO Auto-generated method stub
		return  Cteatadb.SelectSeeion(cteraNames);
	}

	
	//˽�����ݳ־û�
	public boolean InsertFinds(String userName,String findsId,String userId, String sendstr, Date date) {
		// TODO Auto-generated method stub
		return Cteatadb.InsertFinds(userName,findsId,userId,sendstr,date);
	}

}
