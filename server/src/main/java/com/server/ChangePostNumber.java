package com.server;


import java.util.List;
import java.util.Map;

import com.server.repository.Return;


public class ChangePostNumber {

	public static void changePostNumber(Return r, String searchWord) {
		
		List<Map<String, Object>> postData = MysqlRegistry.getPostData();

		for (Map<String, Object> data : postData) {
			String addr = data.get("full_addr_name").toString();
			if (searchWord.contains(addr) || searchWord.replace("字", "").replace("大字", "").contains(addr)) {
				String resultPostNum = data.get("post").toString();
				String resultFullNameAddr = addr;
//				String resultPostName = data.get("post_addr_name").toString();
				String lat = data.get("lat").toString();
				String lon = data.get("lon").toString();
				
				r.setPost(resultPostNum);
				r.setSearchWord(searchWord);
				r.setResultFullNameAddr(resultFullNameAddr);
				r.setLat(lat);
				r.setLon(lon);		
			}
				
//			if (searchWord.replace("字", "").replace("大字", "").contains(addr)) {	
//				String resultPostNum = data.get("post").toString();
//				String resultPlace = addr;
//				r.setPost(resultPostNum);
//				r.setSearchWord(searchWord);
//				r.setResultWord(resultPlace);
//			}
			
		}
	}
}
