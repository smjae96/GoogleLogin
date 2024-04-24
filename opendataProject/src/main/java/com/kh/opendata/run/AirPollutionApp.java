package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

public class AirPollutionApp {
	
	// 서비스키(발급받은 키)
	public static final String SERVICEKEY = "MtqB3TcQKjP%2FCAFENvmat65LBSeFHOQcvvc7s9jYhobgmB4BryQx%2FiJ%2F6CvUvSdpZTRqgkkCvZegedX3pfJJew%3D%3D";

	public static void main(String[] args) throws IOException {
		
		// Open API 요청 주소
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		
		// 요청 시 전달 데이터 (Request Parameter)
		url += "?serviceKey="+SERVICEKEY;		// => 서비스키를 잘못 입력한 경우 (요청한 경우) "SERVICE_KEY_IS_NOT_REGISTERED_ERROR" 에러메시지가 확인됨.
		url += "&returnType=json";	// json or xml로!!
		// url += "&&sidoName=서울";	// => 한글이 포함되어 UTF-8 인코딩 처리
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
		
		// System.out.println(url);
		
		// * HttpURLConnection 객체를 사용하여 요청
		// 1) java.net.URL 객체 생성 (요청 url)
		URL requestUrl = new URL(url);
	
		// 2) java.net.HttpURLConnection 객체 생성 (URL 객체)
		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();		// 메소드가 URLConnection 타입을 리턴하기 때문에 다운캐스팅해줌.
		
		// 3) 요청 시 필요한 Header 설정
		urlConn.setRequestMethod("GET");
		
		// 4) API 서버로 요청 보낸 후 입력 스트림을 통해 응답 데이터 읽기
		//		* BufferedReader : 보조스트림/ 한 줄씩 읽어옴
		//		* InputStream (1byte단위) / Reader (2byte단위)
		//		* InputStreamReader : "바이트 스트림" ---> "문자 스트림" 바꿔주는 스트림.
		BufferedReader buf = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		String responseText = "";
		String line;
		while((line = buf.readLine()) != null) {
//			System.out.println(line);
			responseText += line;
		}
		
		// 5) 스트림 반납
		buf.close();
		urlConn.disconnect();
		
//		System.out.println(responseText);
		// 응답데이터 ---> VO 객체 파싱 작업 (JsonObject, JsonArray, JsonElement 객체 이용 파싱) -> gson 라이브러리가 제공하는 객체들임. 
															//* JSONObject/JSONArray/JSONElement 객체들과 헷갈리지 말 것.
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();	// Json 형태로 파싱.
		JsonObject responseObj = totalObj.getAsJsonObject("response");
		// => 응답 데이터 중 "response" 키값에 해당되는 데이터 추출
//		System.out.println(responseObj);
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		// => "body" 키값에 해당되는 데이터 추출
//		System.out.println(bodyObj);
		
		JsonArray items = bodyObj.getAsJsonArray("items");
		// => "items"라는 키값에 해당되는 데이터 추출
		int totalCount = bodyObj.get("totalCount").getAsInt();	// get이라는 메소드로 먼저 추출하고, getAsInt로 int 타입으로 형변환함.
		// => "totalCount" 키값에 해당되는 데이터 추출
		
//		System.out.println(totalCount);
//		System.out.println(items);
		ArrayList<AirVO> list = new ArrayList<AirVO>();
		for(int i=0; i<items.size(); i++) {
			
			JsonObject item = items.get(i).getAsJsonObject();
//			System.out.println(item);
			
			AirVO air = new AirVO();
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air);
		}
//		System.out.println(list);
		for(AirVO air : list) {
			System.out.println(air);
		}
	}
}

