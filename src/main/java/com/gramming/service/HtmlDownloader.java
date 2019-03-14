package com.gramming.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HtmlDownloader {
	@Value("${local.Poltfolio.StoragePath}")
	String localStoragePath;
	/***
	 * 
	 * @param webpageUrl
	 * @param userId
	 * @throws Exception
	 */
	public String downloadByUrl(String webpageUrl, String userId) throws Exception {
		try {
			// Create URL object
			URL url = new URL(webpageUrl);
			BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));			
			
			String currentTime   = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			String fullPath = localStoragePath + userId + "/";
			
			// 새로운 사용자일 경우 directory가 생성되어 있지 않으므로 생성해준다
			File userDirectory = new File(fullPath);
			if (!userDirectory.exists()) {
				userDirectory.mkdir();
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath + currentTime + ".html"));

			String line;
			while ((line = readr.readLine()) != null) {
				writer.write(line);
			}

			readr.close();
			writer.close();
			System.out.println("Successfully Downloaded.");
			// 생성 시간 return
			return currentTime;
		}

		// Exceptions
		catch (MalformedURLException mue) {
			System.out.println("Malformed URL Exception raised");
			throw new MalformedURLException();
		} catch (IOException ie) {
			System.out.println("IOException raised");
			throw new IOException();
		}		
	}
}