package com.project.covid.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class UserProfileService {

	public HashMap<String, String> getAccessToken(String authorize_code) {

		HashMap<String,String> userInfo= new HashMap<>();
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=c8f72fe8e065caa66728ff40de53d9fd");
			sb.append("&redirect_uri=http://115.21.52.248:8080/kakao/login");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_token);
			System.out.println("refresh_token : " + refresh_token);
			
			userInfo.put("access_token", access_token);
			userInfo.put("refresh_token", refresh_token);
			
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userInfo;
	}
	
	public HashMap<String, String> getHostAccessToken(String authorize_code) {

		HashMap<String,String> userInfo= new HashMap<>();
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=c8f72fe8e065caa66728ff40de53d9fd");
			sb.append("&redirect_uri=http://115.21.52.248:8080/host/login");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_token);
			System.out.println("refresh_token : " + refresh_token);
			
			userInfo.put("access_token", access_token);
			userInfo.put("refresh_token", refresh_token);
			
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userInfo;
	}
	
	public String access(String access_token) {
		System.out.println("access");
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		int responseCode=0;
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_token);

			 responseCode= conn.getResponseCode();
			System.out.println("access response : "+responseCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Integer.toString(responseCode);	
	}
	
	public HashMap<String,String> updateToken(String refresh_token) {
		String reqURL = "https://kauth.kakao.com/oauth/token";
		String access_token="";
		HashMap<String,String> userInfo=new HashMap<>();
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=refresh_token");
			sb.append("&client_id=c8f72fe8e065caa66728ff40de53d9fd");
			sb.append("&refresh_token="+refresh_token);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("refresh response : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_token = element.getAsJsonObject().get("access_token").getAsString();
			if(element.getAsJsonObject().has("refresh_token")) 
				refresh_token= element.getAsJsonObject().get("refresh_token").getAsString();
			
			userInfo.put("access_token", access_token);
			userInfo.put("refresh_token", refresh_token);
			System.out.println("access_token : " + access_token);
			System.out.println("refresh_token : " + refresh_token);
			
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userInfo;
	}
	
	public String getUserInfo(String access_Token) {
		String id="";
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			id = element.getAsJsonObject().get("id").getAsString();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	public void logout(String access_token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        System.out.println(access_token);
	    	URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("logout responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public void sendMessage(String access_token) {
		// 마찬가지로 access_Token값을 가져와 access_Token값을 통해 로그인되어있는 사용자를 확인합니다.
				String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
				try {
					URL url = new URL(reqURL);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
					conn.setRequestProperty("Authorization", "Bearer " + access_token);

					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
					StringBuilder sb = new StringBuilder();


					//json 형식으로 전송 데이터 셋팅
					JsonObject json = new JsonObject();
					json.addProperty("object_type", "feed");
					JsonObject content = new JsonObject();
					content.addProperty("title", "COVID-GPS");
					content.addProperty("description", "코로나 확진자와 접촉이 의심 됩니다. 가까운 보건소나 병원에서 코로나 검사 받길 권장드립니다.");
					content.addProperty("image_url", "https://ifh.cc/g/HzwGzq.png"); 
					
					// 만약, button_title을 넣지 않으면 버튼명이 디폴트 값으로 "자세히 보기"로 나옵니다.
					JsonObject link = new JsonObject();
					link.addProperty("web_url", "http://ncov.mohw.go.kr/"); // 카카오개발자사이트 앱>앱설정>플랫폼>Web>사이트도메인에 등록한 도메인 입력
					link.addProperty("mobile_web_url", "http://ncov.mohw.go.kr/"); // 카카오개발자사이트 앱>앱설정>플랫폼>Web>사이트도메인에 등록한 도메인 입력
																		//만약, 카카오개발자사이트에 도메인을 등록하지 않았다면 링크버튼 자체가 나오지 않습니다.

					content.add("link", link.getAsJsonObject());
					json.add("content", content.getAsJsonObject());

					sb.append("template_object=" + json);

					System.out.println(sb.toString());

					bw.write(sb.toString());
					bw.flush();

					// 결과 코드가 200이라면 성공
					int responseCode = conn.getResponseCode();
					System.out.println("responseCode : " + responseCode);

					// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line = "";
					String result = "";

					while ((line = br.readLine()) != null) {
						result += line;
					}
					// response body : 0이면 성공
					System.out.println("response body : " + result);

					bw.close();
					br.close();

					if (responseCode == 200) {
	
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		}
	public void sendTimeMessage(String access_token,String it,String ot) {
		// 마찬가지로 access_Token값을 가져와 access_Token값을 통해 로그인되어있는 사용자를 확인합니다.
				String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
				try {
					URL url = new URL(reqURL);
					String msg="코로나 확진자와"+it +"~"+ot+"에 접촉이 의심 됩니다. 가까운 보건소나 병원에서 코로나 검사 받기를 권장드립니다.";
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
					conn.setRequestProperty("Authorization", "Bearer " + access_token);

					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
					StringBuilder sb = new StringBuilder();


					//json 형식으로 전송 데이터 셋팅
					JsonObject json = new JsonObject();
					json.addProperty("object_type", "feed");
					JsonObject content = new JsonObject();
					content.addProperty("title", "COVID-GPS");
					content.addProperty("description", msg);
					content.addProperty("image_url", "https://ifh.cc/g/HzwGzq.png"); 
					
					// 만약, button_title을 넣지 않으면 버튼명이 디폴트 값으로 "자세히 보기"로 나옵니다.
					JsonObject link = new JsonObject();
					link.addProperty("web_url", "http://ncov.mohw.go.kr/"); // 카카오개발자사이트 앱>앱설정>플랫폼>Web>사이트도메인에 등록한 도메인 입력
					link.addProperty("mobile_web_url", "http://ncov.mohw.go.kr/"); // 카카오개발자사이트 앱>앱설정>플랫폼>Web>사이트도메인에 등록한 도메인 입력
																		//만약, 카카오개발자사이트에 도메인을 등록하지 않았다면 링크버튼 자체가 나오지 않습니다.

					content.add("link", link.getAsJsonObject());
					json.add("content", content.getAsJsonObject());

					sb.append("template_object=" + json);

					System.out.println(sb.toString());

					bw.write(sb.toString());
					bw.flush();

					// 결과 코드가 200이라면 성공
					int responseCode = conn.getResponseCode();
					System.out.println("responseCode : " + responseCode);

					// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line = "";
					String result = "";

					while ((line = br.readLine()) != null) {
						result += line;
					}
					// response body : 0이면 성공
					System.out.println("response body : " + result);

					bw.close();
					br.close();

					if (responseCode == 200) {
	
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		}
	
}
