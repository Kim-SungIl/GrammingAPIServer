package com.gramming.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gramming.mapper.TestDBMapper;

@Service
public class DbService {
	@Autowired
	TestDBMapper testDbMapper;
	
	public String getDbDual() throws Exception {
		return testDbMapper.getDbDual();
	}
}
