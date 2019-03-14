package com.gramming.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class HtmlToPdfConverter {
	@Value("${local.Poltfolio.StoragePath}")
	String localStoragePath;
	
	/***
	 * html 파일을 pdf로 변환하는 모듈
	 * @param userId
	 * @param downloadTime
	 * @return 생성된 pdf의 경로
	 * @throws Exception
	 */
	public String makePdf(String userId, String downloadTime) throws Exception {
		try {
			String fullPath = localStoragePath + userId + "/" + downloadTime;
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(fullPath + ".pdf"));
			document.open();
			XMLWorkerHelper.getInstance().parseXHtml(writer, document,
					new FileInputStream(fullPath + ".html"));
			document.close();
			System.out.println("Successfully convert html to pdf");
			return fullPath + ".pdf";
		} catch (DocumentException e) {
			System.out.println("DocumentException URL Exception raised");
			throw new DocumentException();
		} catch (IOException e) {
			System.out.println("IOException URL Exception raised");
			throw new IOException();
		}
	}
}
