package com.bit;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class Ex01 {

	public static void main(String[] args) {
		
		ServerAddress addr=new ServerAddress("127.0.0.1",27017);
		MongoClient client=new com.mongodb.MongoClient(addr);
		List<String> names=client.getDatabaseNames();
		for(int i=0; i<names.size(); i++) {
			System.out.println(names.get(i));
		}
		System.out.println("----------------------");
		DB db=client.getDB("testDB");
		Set<String> set=db.getCollectionNames();
		Iterator<String> ite=set.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
		System.out.println("----------------------");
		DBCollection collection=db.getCollection("sales");
		BasicDBObject query=new BasicDBObject();
		BasicDBObject sub1=new BasicDBObject();
		sub1.append("$lt", 10);
		query.append("price", sub1);
		DBCursor cursor=collection.find(query);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
