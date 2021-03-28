package com.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.core.io.ClassPathResource;


public class ChangePostNumber {

	public static void changePostNumber(Return r ,String csvPath, String searchWord) {
		ArrayList<LinkedHashMap<String,String>> postDataList;
		postDataList = postCsvData(csvPath);

		postDataList.forEach(postList -> {
			if (searchWord.indexOf(postList.get("full_addr_name")) >= 0) {
				System.out.println(postList.get("full_addr_name"));
				String resultPostNum = postList.get("post");
				String resultPlace = postList.get("full_addr_name");
				r.setPost(resultPostNum);
				r.setSearchWord(searchWord);
				r.setResultWord(resultPlace);
			}
		});
		
	}

	// postデータを読み取りそれぞれを配列に格納
	public static ArrayList<LinkedHashMap<String,String>> postCsvData(String csvPath) {
		String fileName = csvPath;
		ArrayList<LinkedHashMap<String,String>> data = new ArrayList<>();

		try (InputStream is = new ClassPathResource(fileName).getInputStream();BufferedReader br = new BufferedReader(new InputStreamReader(is,"Shift-JIS"))){
			String line;
			String[] lineSplit;

			while ((line = br.readLine()) != null) {
				lineSplit = line.split(",");

				LinkedHashMap<String,String> address = new LinkedHashMap<>();
				
				String fullAddrName = (lineSplit[6] + lineSplit[7] + lineSplit[8]).replace("\"", "").replace("　", "").trim();
	
				address.put("post", toHalfWidth(lineSplit[2].replace("\"", "").trim()));
				address.put("full_addr_name", toHalfWidth(fullAddrName));
				data.add(address);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
	  	}
		return data;
	}
	
	public static String toHalfWidth(String s) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (0xFF10 <= c && c <= 0xFF19) {
				sb.setCharAt(i, (char) (c - 0xFEE0));
			}
		}
		return sb.toString();
	}
	
	
}
