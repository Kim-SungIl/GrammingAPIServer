package com.gramming.vo;

/***
 * 응답 값에 대한 코드를 정의한다.
 * Front-end와 협의하여 Error Code를 정의하고, 해당 Error Code에 해당하는 문구를 Front에서 출력할 수 있도록 한다. 
 * @author SungIl
 *
 */
public enum StatusCode {
	SUCCESS(1), FAIL(0), UNKNOWN_ERROR(-1);
	private int statusCode;
	private String statusMessage;

	private StatusCode(int value) {
		this.statusCode = value;
		switch (value) {
		case 1:
			statusMessage = "Request success!";
			break;
		case 2:
			statusMessage = "Request Fail!";
			break;
		}
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
}
