package com.cqgy.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Cteatadb {

	public static boolean InsertSeeion(String userName,String cteraNames,String userId, String sendstr, Date date) {
		// TODO Auto-generated method stub
		 try{   
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Seeionmessage");  
        System.out.println("Connect to database successfully");
        
        MongoCollection<Document> collection = mongoDatabase.getCollection(cteraNames);
        System.out.println("集合 test 选择成功");
        //插入文档  
        /** 
        * 1. 创建文档 org.bson.Document 参数为key-value的格式 
        * 2. 创建文档集合List<Document> 
        * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
        * */
        Document document = new Document("title", "seeion").  
        append("userId", userId).  
        append("username",userName).
        append("meesage", sendstr).  
        append("date", date);  
        List<Document> documents = new ArrayList<Document>();  
        documents.add(document);  
        collection.insertMany(documents);  
        System.out.println("文档插入成功");  
        mongoClient.close();
        mongoClient=null;//一定要写这句话，不然系统不会回收，只是关闭了，连接存在。
        
     }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }
		return true;
	}

	public static MongoCursor<Document> SelectSeeion(String cteraNames) {
		// TODO Auto-generated method stub
		MongoCursor<Document> mongoCursor=null;
		 try{   
	         // 连接到 mongodb 服务
	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	         
	         // 连接到数据库
	         MongoDatabase mongoDatabase = mongoClient.getDatabase("Seeionmessage");
	         
	         MongoCollection<Document> collection = mongoDatabase.getCollection(cteraNames);
	         System.out.println("集合 test 选择成功");
	         
	         //检索所有文档  
	         /** 
	         * 1. 获取迭代器FindIterable<Document> 
	         * 2. 获取游标MongoCursor<Document> 
	         * 3. 通过游标遍历检索出的文档集合 
	         * */  
	         FindIterable<Document> findIterable = collection.find();  
	         mongoCursor = findIterable.iterator();
	         mongoClient.close();
	         mongoClient=null;//一定要写这句话，不然系统不会回收，只是关闭了，连接存在。
	      
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
		return mongoCursor;
	}

	public static boolean InsertFinds(String userName,String findsId,String userId, String sendstr, Date date) {
		// TODO Auto-generated method stub
		 try{   
		        // 连接到 mongodb 服务
		        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		        
		        // 连接到数据库
		        MongoDatabase mongoDatabase = mongoClient.getDatabase("Seeionmessage");  
		        System.out.println("Connect to database successfully");
		        int i = Integer.parseInt(userId);
		        int y = Integer.parseInt(findsId);
		        String zhaas=String.valueOf(i+y);
		        MongoCollection<Document> collection = mongoDatabase.getCollection(zhaas);
		        System.out.println("集合 test 选择成功");
		        //插入文档  
		        /** 
		        * 1. 创建文档 org.bson.Document 参数为key-value的格式 
		        * 2. 创建文档集合List<Document> 
		        * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
		        * */
		        Document document = new Document("title", "seeion").  
		        append("userId", userId).  
		        append("username",userName).
		        append("meesage", sendstr).  
		        append("date", date);  
		        List<Document> documents = new ArrayList<Document>();  
		        documents.add(document);  
		        collection.insertMany(documents);  
		        System.out.println("文档插入成功");  
		        mongoClient.close();
		        mongoClient=null;//一定要写这句话，不然系统不会回收，只是关闭了，连接存在。
		        
		     }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
				return true;
	}

}
