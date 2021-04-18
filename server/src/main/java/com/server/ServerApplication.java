package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ServerApplication.class, args);
		
		
//		初期にデータを挿入する場合
//		ConfigurableApplicationContext ctx = SpringApplication.run(ServerApplication.class, args);
//		ServerApplication app = ctx.getBean(ServerApplication.class);
//		app.execStartup(args);
	}
	
//	private void execStartup(String[] args) {
//
//		String csvPath = "csv/x-ken-all.csv";
//		ArrayList<LinkedHashMap<String,String>> postDataList;
//		
//		postDataList = GetCsvData.readPostCsvData(csvPath);
//
//		MysqlRegistry.insertPostData(postDataList);
//	}

}
