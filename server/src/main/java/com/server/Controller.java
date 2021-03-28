package com.server;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atilika.kuromoji.TokenizerBase.Mode;
import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.atilika.kuromoji.ipadic.Tokenizer.Builder;

@RestController
public class Controller {

	@GetMapping("/change_post")
	public ResponseEntity<?> Api(@RequestParam(value = "addr", defaultValue = "東京都千代田区丸の内１丁目") String addr){
		Return r = new Return();
		// csvファイルのパス
		// 今回利用したpostデータファイル：https://www.post.japanpost.jp/zipcode/dl/oogaki-zip.html
		String csvPath = "csv/x-ken-all.csv";
		// 検索したい住所の入力（一部ではなく全て記入）
		String searchWord = toHalfWidth(addr).replace("　", "").replace(" ", "");

		// 住所から郵便番号の取得
		ChangePostNumber.changePostNumber(r, csvPath, searchWord);
		
		String post = r.getPost();
		
		LinkedHashMap<String, String> keyValues = new LinkedHashMap<>();
		keyValues.put("post", post);
		keyValues.put("search_word", searchWord);
//		testKuromoji(searchWord);
		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
		return new ResponseEntity<>(keyValues, headers, HttpStatus.OK);
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
	
	//jsonオブジェクトの作成
	public static JSONObject createJson(Map<String, String> keyValuesList) {
		JSONObject json = new JSONObject();
		keyValuesList.forEach((key, value) -> json.put(key, value));
		
		return json;
	}
	
	// kuromojiのテストメソッド
	public static void testKuromoji(String searchWord) {
		
		Builder builder = new Tokenizer.Builder();
//		builder.mode(Mode.SEARCH);
		Tokenizer tokenizer = builder.build();
		
        List<Token> tokens = tokenizer.tokenize(searchWord);
        for (Token token : tokens) {
            System.out.println(token.getSurface() + "\t" + token.getAllFeatures());
        }
	}


}

