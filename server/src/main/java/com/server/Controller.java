package com.server;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.server.repository.Return;

@RestController
public class Controller {

	@GetMapping("/change_post")
	public ResponseEntity<?> Api(@RequestParam(value = "addr", defaultValue = "東京都千代田区丸の内１丁目") String addr){
		Return r = new Return();
		// 検索したい住所の入力（一部ではなく全て記入）
		String searchWord = Formatter.toHalfWidth(addr);

		// 住所から郵便番号の取得
		ChangePostNumber.changePostNumber(r, searchWord);
		
		String post = r.getPost();
		String fullNameAddr = r.getResultFullNameAddr();
		String lat = r.getLat();
		String lon = r.getLon();
		
		System.out.println(post);
		System.out.println(lat);
		System.out.println(lon);
		System.out.println(fullNameAddr);
		
		LinkedHashMap<String, String> keyValues = new LinkedHashMap<>();
		
		keyValues.put("post", post);
		keyValues.put("full_name_addr", fullNameAddr);
		keyValues.put("lat", lat);
		keyValues.put("lon", lon);
		keyValues.put("search_word", searchWord);
		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
		return new ResponseEntity<>(keyValues, headers, HttpStatus.OK);
	}
	
	@GetMapping("/update_mysql_database")
	public ResponseEntity<?> updateMysqlDatabase() {
		String csvPath = "csv/lat_result.csv";
		ArrayList<LinkedHashMap<String,String>> postDataList;
		
		postDataList = GetCsvData.readPostCsvData(csvPath);

		MysqlRegistry.insertPostData(postDataList);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
	    
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}
	
}

