package com.tb.util;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoDBconnectUtil {
	public static DB connect() {
		List<ServerAddress> adds = new ArrayList<>();
		//ServerAddress()���������ֱ�Ϊ ��������ַ �� �˿�
		ServerAddress serverAddress = new ServerAddress("39.106.170.208", 27017);
		adds.add(serverAddress);
		 
		List<MongoCredential> credentials = new ArrayList<>();
		//MongoCredential.createScramSha1Credential()���������ֱ�Ϊ �û��� ���ݿ����� ����
		MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("root", "admin", "root".toCharArray());
		credentials.add(mongoCredential);
		 
		//ͨ��������֤��ȡMongoDB����
		MongoClient mongoClient = new MongoClient(adds, credentials);
		
		//���ӵ����ݿ�
		DB mongoDatabase = mongoClient.getDB("timebank");

		return mongoDatabase;
	}
}
