package com.gramming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@RequestMapping("/")	
	public String root_test() throws Exception {
		return "Hello Root(/)";
	}
}
