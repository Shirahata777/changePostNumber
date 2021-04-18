package com.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.core.io.ClassPathResource;

public class GetCsvData {
	
	// postデータを読み取りそれぞれを配列に格納
		public static ArrayList<LinkedHashMap<String,String>> readPostCsvData(String csvPath) {
			String fileName = csvPath;
			ArrayList<LinkedHashMap<String,String>> data = new ArrayList<>();

			try (InputStream is = new ClassPathResource(fileName).getInputStream();BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"))){
				String line;
				String[] lineSplit;
				int count = 0;

				while ((line = br.readLine()) != null) {
					if (count != 0) {
						lineSplit = line.split(",");
						
						String post = Formatter.sjisToUtf8(Formatter.toHalfWidth(lineSplit[4]));
						String fullAddrName = Formatter.sjisToUtf8(Formatter.toHalfWidth((lineSplit[1])));
						String lat = Formatter.sjisToUtf8(lineSplit[2]);
						String lon = Formatter.sjisToUtf8(lineSplit[3]);
						String postName = Formatter.sjisToUtf8(lineSplit[5]);
						
						data.add(createAddrList(post, lat, lon, postName, fullAddrName));
					}
					count++;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
		  	}
			return data;
		}
		
		public static LinkedHashMap<String,String> createAddrList(String post, String lat, String lon, String postName, String fullAddrName) {
			LinkedHashMap<String,String> address = new LinkedHashMap<>();
			address.put("post", post);
			address.put("lat", lat);
			address.put("lon", lon);
			address.put("full_addr_name", fullAddrName);
			address.put("post_name", postName);
			return address;
		}

}
