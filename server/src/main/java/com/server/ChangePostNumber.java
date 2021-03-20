package com.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ChangePostNumber {

    private static ArrayList<LinkedHashMap<String,String>> postDataList;
    static String resultPostNum = "";
	public static void changePostNumber(Return r ,String csvPath, String searchWord, String fileEncode) {

		postDataList = postCsvData(csvPath, fileEncode);

		postDataList.forEach(postList -> {
			if (searchWord.contains(postList.get("full_addr_name"))) {
				resultPostNum = postList.get("post");
				r.setPost(resultPostNum);
				r.setSearchWord(searchWord);
			}
		});
	}

	// postデータを読み取りそれぞれを配列に格納
	public static ArrayList<LinkedHashMap<String,String>> postCsvData(String csvPath, String fileEncode) {
		String fileName = csvPath;
		String encode = fileEncode;
		ArrayList<LinkedHashMap<String,String>> data = new ArrayList<>();
		Resource resource = new ClassPathResource(fileName);

		try (InputStream in = resource.getInputStream();BufferedReader br = new BufferedReader(new InputStreamReader(in,StandardCharsets.UTF_8));){
			String line;
			String[] lineSplit;

			while ((line = br.readLine()) != null) {
				lineSplit = line.split(",");

				LinkedHashMap<String,String> address = new LinkedHashMap<>();
				address.put("post", lineSplit[2].replace("\"", "").trim());

				address.put("capital", lineSplit[6].replace("\"", "").trim());
				address.put("refecture", lineSplit[7].replace("\"", "").trim());
				address.put("municipalities", lineSplit[8].replace("\"", "").trim());
				address.put("full_addr_name", (lineSplit[6] + lineSplit[7] + lineSplit[8]).replace("\"", "").trim());

				address.put("capital_kana", lineSplit[3].replace("\"", "").trim());
				address.put("refecture_kana", lineSplit[4].replace("\"", "").trim());
				address.put("municipalities_kana", lineSplit[5].replace("\"", "").trim());
				address.put("full_addr_name_kana", (lineSplit[3] + lineSplit[4] + lineSplit[5]).replace("\"", "").trim());

				data.add(address);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
	  	}
		return data;
	}
}
