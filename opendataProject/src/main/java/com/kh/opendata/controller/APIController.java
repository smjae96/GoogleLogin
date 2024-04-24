package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;
import com.kh.opendata.model.vo.ShelterVO;

@Controller
public class APIController {

	public static final String SERVICEKEY = "MtqB3TcQKjP%2FCAFENvmat65LBSeFHOQcvvc7s9jYhobgmB4BryQx%2FiJ%2F6CvUvSdpZTRqgkkCvZegedX3pfJJew%3D%3D";
//	@ResponseBody
//	@RequestMapping(value="air.do", produces="application/json;charset=UTF-8") 
//	public String selectList(String location) throws IOException {
//		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
//		
//		url += "?serviceKey="+SERVICEKEY;		// => 서비스키를 잘못 입력한 경우 (요청한 경우) "SERVICE_KEY_IS_NOT_REGISTERED_ERROR" 에러메시지가 확인됨.
//		url += "&returnType=json";	// json or xml로!!
//		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
//		url += "&numOfRows=30";	// 행 수를 30개로
//		
//		URL requestUrl = new URL(url);
//	
//
//		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();		
//		
//
//		urlConn.setRequestMethod("GET");
//		
//
//		BufferedReader buf = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
//		String responseText = "";
//		String line;
//		while((line = buf.readLine()) != null) {
//			responseText += line;
//		}
//		
//		buf.close();
//		urlConn.disconnect();
//		
//		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();	// Json 형태로 파싱.
//		JsonObject responseObj = totalObj.getAsJsonObject("response");
//		JsonObject bodyObj = responseObj.getAsJsonObject("body");
//		
//		JsonArray items = bodyObj.getAsJsonArray("items");
//		int totalCount = bodyObj.get("totalCount").getAsInt();	// get이라는 메소드로 먼저 추출하고, getAsInt로 int 타입으로 형변환함.
//		
//		ArrayList<AirVO> list = new ArrayList<>();
//		for(int i=0; i<items.size(); i++) {
//			
//			JsonObject item = items.get(i).getAsJsonObject();
//			
//			AirVO air = new AirVO();
//			air.setStationName(item.get("stationName").getAsString());
//			air.setDataTime(item.get("dataTime").getAsString());
//			air.setKhaiValue(item.get("khaiValue").getAsString());
//			air.setPm10Value(item.get("pm10Value").getAsString());
//			air.setCoValue(item.get("coValue").getAsString());
//			air.setNo2Value(item.get("no2Value").getAsString());
//			air.setSo2Value(item.get("so2Value").getAsString());
//			air.setO3Value(item.get("o3Value").getAsString());
//			
//			list.add(air);
//		}
//		
//		return new Gson().toJson(list);
//	}
	
	
	/* XML형식으로 받았을 경우*/
	@ResponseBody
	@RequestMapping(value="air.do", produces="text/xml;charset=UTF-8") 
	public String selectList(String location) throws IOException {
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		
		url += "?serviceKey="+SERVICEKEY;		// => 서비스키를 잘못 입력한 경우 (요청한 경우) "SERVICE_KEY_IS_NOT_REGISTERED_ERROR" 에러메시지가 확인됨.
		url += "&returnType=xml";	// json or xml로!!
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
		url += "&numOfRows=30";	// 행 수를 30개로
		
		URL requestUrl = new URL(url);
	

		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();		
		

		urlConn.setRequestMethod("GET");
		

		BufferedReader buf = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		String responseText = "";
		String line;
		while((line = buf.readLine()) != null) {
			responseText += line;
		}
		
		buf.close();
		urlConn.disconnect();
		
//		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();	// Json 형태로 파싱.
//		JsonObject responseObj = totalObj.getAsJsonObject("response");
//		JsonObject bodyObj = responseObj.getAsJsonObject("body");
//		
//		JsonArray items = bodyObj.getAsJsonArray("items");
//		int totalCount = bodyObj.get("totalCount").getAsInt();	// get이라는 메소드로 먼저 추출하고, getAsInt로 int 타입으로 형변환함.
//		
//		ArrayList<AirVO> list = new ArrayList<>();
//		for(int i=0; i<items.size(); i++) {
//			
//			JsonObject item = items.get(i).getAsJsonObject();
//			
//			AirVO air = new AirVO();
//			air.setStationName(item.get("stationName").getAsString());
//			air.setDataTime(item.get("dataTime").getAsString());
//			air.setKhaiValue(item.get("khaiValue").getAsString());
//			air.setPm10Value(item.get("pm10Value").getAsString());
//			air.setCoValue(item.get("coValue").getAsString());
//			air.setNo2Value(item.get("no2Value").getAsString());
//			air.setSo2Value(item.get("so2Value").getAsString());
//			air.setO3Value(item.get("o3Value").getAsString());
//			
//			list.add(air);
//		}
		
		return responseText;
	}
	
	@ResponseBody
	@RequestMapping(value="shelter.do", produces="application/json;charset=UTF-8") 
	public String selectShelterList(String pageNo) throws IOException {
		String url = "http://apis.data.go.kr/1741000/TsunamiShelter4/getTsunamiShelter4List";
		
		url += "?ServiceKey="+SERVICEKEY;		// => 서비스키를 잘못 입력한 경우 (요청한 경우) "SERVICE_KEY_IS_NOT_REGISTERED_ERROR" 에러메시지가 확인됨.
		url += "&type=json";	// json or xml로!!
		url += "&pageNo="+pageNo;
		url += "&numOfRows=30";	// 행 수를 30개로
		
		URL requestUrl = new URL(url);
	

		HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();		
		

		urlConn.setRequestMethod("GET");
		

		BufferedReader buf = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		String responseText = "";
		String line;
		while((line = buf.readLine()) != null) {
			responseText += line;
		}
		return responseText;
//		buf.close();
//		urlConn.disconnect();
//		
//		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();	// Json 형태로 파싱.
//		JsonObject TsunamiShelterObj = totalObj.getAsJsonObject("TsunamiShelter");
//		JsonArray items = TsunamiShelterObj.getAsJsonArray("row");
//		
//		
//		ArrayList<ShelterVO> list = new ArrayList<>();
//		for(int i=0; i<items.size(); i++) {
//			
//			JsonObject item = items.get(i).getAsJsonObject();
//			
//			ShelterVO shelter = new ShelterVO();
//			shelter.setSigunguName(item.get("sigungu_name").getAsString());
//			shelter.setRemarks(item.get("remarks").getAsString());
//			shelter.setShelNm(item.get("shel_nm").getAsString());
//			shelter.setNewAddress(item.get("new_address").getAsString());
//			shelter.setTel(item.get("tel").getAsString());
//			shelter.setManageGovNm(item.get("manage_gov_nm").getAsString());
//			
//			
//			list.add(shelter);
//		}
//		
//		return new Gson().toJson(list);
	}
	
	
}
