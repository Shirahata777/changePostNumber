package com.server;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.atilika.kuromoji.ipadic.Tokenizer.Builder;

public class Formatter {
	
	public static String toHalfWidth(String s) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (0xFF10 <= c && c <= 0xFF19) {
				sb.setCharAt(i, (char) (c - 0xFEE0));
			}
		}
		return sb.toString().replace("　", "").replace(" ", "").replace("\"", "").trim();
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
	
	public static String sjisToUtf8(String value) throws UnsupportedEncodingException {
        byte[] srcStream = value.getBytes("SJIS");
        byte[] destStream = (new String(srcStream, "SJIS")).getBytes("UTF-8");
        value = new String(destStream, "UTF-8");
        value = convert(value, "SJIS", "UTF-8");
        return value;
    }
	
	private static String convert(String value, String src, String dest) throws UnsupportedEncodingException {
        Map<String, String> conversion = createConversionMap(src, dest);
        char oldChar;
        char newChar;
        String key;
        for (Iterator<String> itr = conversion.keySet().iterator() ; itr.hasNext() ;) {
            key = itr.next();
            oldChar = toChar(key);
            newChar = toChar(conversion.get(key));
            value = value.replace(oldChar, newChar);
        }
        return value;
    }
	
	private static Map<String, String> createConversionMap(String src, String dest) throws UnsupportedEncodingException {
        Map<String, String> conversion = new HashMap<String, String>();
        if ((src.equals("UTF-8")) && (dest.equals("SJIS"))) {
            // －（全角マイナス）
           conversion.put("U+FF0D", "U+2212");
            // ～（全角チルダ）
           conversion.put("U+FF5E", "U+301C");
            // ￠（セント）
           conversion.put("U+FFE0", "U+00A2");
            // ￡（ポンド）
           conversion.put("U+FFE1", "U+00A3");
            // ￢（ノット）
           conversion.put("U+FFE2", "U+00AC");
            // ―（全角マイナスより少し幅のある文字）
           conversion.put("U+2015", "U+2014");
            // ∥（半角パイプが2つ並んだような文字）
           conversion.put("U+2225", "U+2016");

        } else if ((src.equals("SJIS")) && (dest.equals("UTF-8"))) {
            // －（全角マイナス）
           conversion.put("U+2212", "U+FF0D");
            // ～（全角チルダ）
           conversion.put("U+301C", "U+FF5E");
            // ￠（セント）
           conversion.put("U+00A2", "U+FFE0");
            // ￡（ポンド）
           conversion.put("U+00A3", "U+FFE1");
            // ￢（ノット）
           conversion.put("U+00AC", "U+FFE2");
            // ―（全角マイナスより少し幅のある文字）
           conversion.put("U+2014", "U+2015");
            // ∥（半角パイプが2つ並んだような文字）
           conversion.put("U+2016", "U+2225");

        } else {
            throw new UnsupportedEncodingException("この文字コードはサポートしていません。\n・src=" + src + ",dest=" + dest);
        }
        return conversion;
    }

    /**
     * 16進表記の文字を取得する。
    * 
     * @param value 変換対象の文字列
    * @return 16進表記の文字
    */
    private static char toChar(String value) {
        return (char)Integer.parseInt(value.trim().substring("U+".length()), 16);
    }

}
