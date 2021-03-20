package com.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static final String template = "Hello, %s!";

	@GetMapping("/change_post")
	public String Api(@RequestParam(value = "addr", defaultValue = "東京都千代田区丸の内１丁目") String name){
		Return r = new Return();

		String fileEncode = "SJIS";
		// csvファイルのパス
		// 今回利用したpostデータファイル：https://www.post.japanpost.jp/zipcode/dl/oogaki-zip.html
		String csvPath = "csv/KEN_ALL.CSV";
		// 検索したい住所の入力（一部ではなく全て記入）
		String searchWord = "宮城県気仙沼市廻舘181";

		// 住所から郵便番号の取得
		ChangePostNumber.changePostNumber(r, fileEncode, csvPath, searchWord);

		String post = r.getPost();
		return post;
	}


}

