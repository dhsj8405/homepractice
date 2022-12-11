package com.douzone.dbConnectionPractice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.dbConnectionPractice.mapper.TestMapper;
import com.douzone.dbConnectionPractice.model.TestVo;
import com.douzone.dbConnectionPractice.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestMapper testMapper;
	
	@Override 
	public List<TestVo> getTestList(TestVo testvo){
		return testMapper.getTestList(testvo);
	};
}