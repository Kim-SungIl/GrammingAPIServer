package com.gramming.vo;

import java.util.HashMap;
import java.util.Map;

/***
 * API 서버의 기본적인 응답 값 VO
 * 필요시 해당 Class를 상속받아서 사용하도록 한다.
 * @author SungIl
 *
 */
public class ResponseVO {
	private int responseCode;
	private String responseMessage;
	private Map<String, Object> responseMap;
	
	/***
	 * StatusCode만 정의하면 자동으로 Return Code와 메시지가 설정되도록 한다.
	 * @param code
	 */
	public ResponseVO (StatusCode code) {
		responseCode = code.getStatusCode();
		responseMessage = code.getStatusMessage();
		responseMap = new HashMap<String, Object> ();
	}
	
	/***
	 * pre-define 되지 않은 Exception이 발생했을 때 사용되는 응답 메시지
	 * @param e
	 */
	public ResponseVO (Exception e) {
		responseCode = StatusCode.UNKNOWN_ERROR.getStatusCode();
		responseMessage = e.getMessage();
		responseMap = new HashMap<String, Object> ();
		responseMap.put("detail", e.toString());
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public Map<String, Object> getResponseMap() {
		return responseMap;
	}
	public void putResponseMap (String key, Object value) {
		responseMap.put(key, value);
	}
}
