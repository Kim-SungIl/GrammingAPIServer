package com.gramming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gramming.service.db.DbService;

@RestController
public class MainController {
	@Autowired
	DbService dbService;
	
	@RequestMapping("/")	
	public String root_test() throws Exception {
		String test = dbService.getDbDual();
		return "Hello Root(/)" + test;
	}
}
