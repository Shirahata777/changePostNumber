package com.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.server.repository.PostDataRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class MysqlRegistry {
	private static final Logger LOG = LoggerFactory.getLogger(MysqlRegistry.class);
	private static JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MysqlRegistry(JdbcTemplate jdbcTemplate) {
		MysqlRegistry.jdbcTemplate = jdbcTemplate;
	}
	

	public static void insertPostData(ArrayList<LinkedHashMap<String,String>> postData) {
		
		postData.forEach(map -> {
			PostDataRepository p = new PostDataRepository();
			p.setPost(map.get("post"));
			p.setLat(map.get("lat"));
			p.setLon(map.get("lon"));
			p.setPostName(map.get("post_name"));
			p.setFullAddrName(map.get("full_addr_name"));
			
			LOG.info(map.get("post"));
			LOG.info(map.get("lat"));
			LOG.info(map.get("lon"));
			LOG.info(map.get("post_name"));
			LOG.info(map.get("full_addr_name"));
			
			jdbcTemplate.update("INSERT INTO post_data_list(post, lat, lon, post_addr_name, full_addr_name) Values(?,?,?,?,?)",p.getPost(),p.getLat(),p.getLon(),p.getPostName(), p.getFullAddrName());
		});
	}
	
	public static List<Map<String, Object>> getPostData() {
		return jdbcTemplate.queryForList("SELECT * FROM post_data_list");	
	}
	
}
 
	
	

