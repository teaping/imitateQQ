package com.cqgy.method;

import java.util.Date;

import org.bson.Document;

import com.cqgy.db.Cteatadb;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class CteataDao {

	/***
	 * 添加数据
	 * @param userId  用户id
	 * @param sendstr  内容
	 * @param date  时间
	 * @return
	 */
	public boolean InsertSeeion(String userName,String cteraNames,String userId, String sendstr, Date date) {
		// TODO Auto-generated method stub
		return Cteatadb.InsertSeeion(userName,cteraNames,userId,sendstr,date);
	}

	/**
	 * 取出数据
	 * @param cteraNames 群名
	 * @return
	 */
	public MongoCursor<Document> SelectCtreata(String cteraNames) {
		// TODO Auto-generated method stub
		return  Cteatadb.SelectSeeion(cteraNames);
	}

	
	//私聊数据持久化
	public boolean InsertFinds(String userName,String findsId,String userId, String sendstr, Date date) {
		// TODO Auto-generated method stub
		return Cteatadb.InsertFinds(userName,findsId,userId,sendstr,date);
	}

}
