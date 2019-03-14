package com.gramming.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gramming.service.HtmlDownloader;
import com.gramming.service.HtmlToPdfConverter;
import com.gramming.vo.ResponseVO;
import com.gramming.vo.StatusCode;

@RestController
@RequestMapping("/makePoltfolio")
public class PoltfolioController {

	@Autowired
	HtmlDownloader htmlDownloader;	
	@Autowired
	HtmlToPdfConverter htmlToPdfConverter;
	
	/***
	 * Web url에 있는 포트폴리오를 Html 형태로 다운받아 device에 저장한다.
	 * 
	 * @param userId
	 * @param poltfolioUrl
	 * @return responseVO에 html이 다운로드된 시간을 return (파일명)
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadHtml", method = RequestMethod.POST)
	public ResponseVO downloadHtml(@RequestParam("userId") String userId,
			@RequestParam("poltfolioUrl") String poltfolioUrl) throws Exception {
		ResponseVO responseVo;
		String downloadTime = null;
		try {
			downloadTime = htmlDownloader.downloadByUrl(poltfolioUrl, userId);
		} catch (MalformedURLException e) {
			// Error Code 정의 후 response 처리

		} catch (IOException e) {
			// Error Code 정의 후 response 처리
		} catch (Exception e) {
			// 정의되지 않은 Error 발생시 처리, 해당 오류 발생 시 추후 Error code 정의 필요
			responseVo = new ResponseVO(e);
			return responseVo;
		}
		
		responseVo = new ResponseVO(StatusCode.SUCCESS);
		responseVo.putResponseMap("downloadTime", downloadTime);
		return responseVo;
	}
	
	/***
	 * 
	 * @param userId
	 * @param downloadTime
	 * @return
	 */
	@RequestMapping(value = "/convertHtmlToPdf", method = RequestMethod.POST)
	public ResponseVO convertHtmlToPdf(@RequestParam("userId") String userId,
			@RequestParam("downloadTime") String downloadTime) throws Exception {
		ResponseVO responseVo;
		
		try {
			htmlToPdfConverter.makePdf(userId, downloadTime);
		} catch (MalformedURLException e) {
			// Error Code 정의 후 response 처리

		} catch (IOException e) {
			// Error Code 정의 후 response 처리
		} catch (Exception e) {
			// 정의되지 않은 Error 발생시 처리, 해당 오류 발생 시 추후 Error code 정의 필요
			responseVo = new ResponseVO(e);
			return responseVo;
		}
		
		responseVo = new ResponseVO(StatusCode.SUCCESS);
		return responseVo;
	}
}
