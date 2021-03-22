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
        // ���ӵ� mongodb ����
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
        // ���ӵ����ݿ�
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Seeionmessage");  
        System.out.println("Connect to database successfully");
        
        MongoCollection<Document> collection = mongoDatabase.getCollection(cteraNames);
        System.out.println("���� test ѡ��ɹ�");
        //�����ĵ�  
        /** 
        * 1. �����ĵ� org.bson.Document ����Ϊkey-value�ĸ�ʽ 
        * 2. �����ĵ�����List<Document> 
        * 3. ���ĵ����ϲ������ݿ⼯���� mongoCollection.insertMany(List<Document>) ���뵥���ĵ������� mongoCollection.insertOne(Document) 
        * */
        Document document = new Document("title", "seeion").  
        append("userId", userId).  
        append("username",userName).
        append("meesage", sendstr).  
        append("date", date);  
        List<Document> documents = new ArrayList<Document>();  
        documents.add(document);  
        collection.insertMany(documents);  
        System.out.println("�ĵ�����ɹ�");  
        mongoClient.close();
        mongoClient=null;//һ��Ҫд��仰����Ȼϵͳ������գ�ֻ�ǹر��ˣ����Ӵ��ڡ�
        
     }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }
		return true;
	}

	public static MongoCursor<Document> SelectSeeion(String cteraNames) {
		// TODO Auto-generated method stub
		MongoCursor<Document> mongoCursor=null;
		 try{   
	         // ���ӵ� mongodb ����
	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	         
	         // ���ӵ����ݿ�
	         MongoDatabase mongoDatabase = mongoClient.getDatabase("Seeionmessage");
	         
	         MongoCollection<Document> collection = mongoDatabase.getCollection(cteraNames);
	         System.out.println("���� test ѡ��ɹ�");
	         
	         //���������ĵ�  
	         /** 
	         * 1. ��ȡ������FindIterable<Document> 
	         * 2. ��ȡ�α�MongoCursor<Document> 
	         * 3. ͨ���α�������������ĵ����� 
	         * */  
	         FindIterable<Document> findIterable = collection.find();  
	         mongoCursor = findIterable.iterator();
	         mongoClient.close();
	         mongoClient=null;//һ��Ҫд��仰����Ȼϵͳ������գ�ֻ�ǹر��ˣ����Ӵ��ڡ�
	      
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
		return mongoCursor;
	}

	public static boolean InsertFinds(String userName,String findsId,String userId, String sendstr, Date date) {
		// TODO Auto-generated method stub
		 try{   
		        // ���ӵ� mongodb ����
		        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		        
		        // ���ӵ����ݿ�
		        MongoDatabase mongoDatabase = mongoClient.getDatabase("Seeionmessage");  
		        System.out.println("Connect to database successfully");
		        int i = Integer.parseInt(userId);
		        int y = Integer.parseInt(findsId);
		        String zhaas=String.valueOf(i+y);
		        MongoCollection<Document> collection = mongoDatabase.getCollection(zhaas);
		        System.out.println("���� test ѡ��ɹ�");
		        //�����ĵ�  
		        /** 
		        * 1. �����ĵ� org.bson.Document ����Ϊkey-value�ĸ�ʽ 
		        * 2. �����ĵ�����List<Document> 
		        * 3. ���ĵ����ϲ������ݿ⼯���� mongoCollection.insertMany(List<Document>) ���뵥���ĵ������� mongoCollection.insertOne(Document) 
		        * */
		        Document document = new Document("title", "seeion").  
		        append("userId", userId).  
		        append("username",userName).
		        append("meesage", sendstr).  
		        append("date", date);  
		        List<Document> documents = new ArrayList<Document>();  
		        documents.add(document);  
		        collection.insertMany(documents);  
		        System.out.println("�ĵ�����ɹ�");  
		        mongoClient.close();
		        mongoClient=null;//һ��Ҫд��仰����Ȼϵͳ������գ�ֻ�ǹر��ˣ����Ӵ��ڡ�
		        
		     }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
				return true;
	}

}
