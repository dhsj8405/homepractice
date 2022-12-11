package com.douzone.dbConnectionPractice.service;

import java.util.List;

import com.douzone.dbConnectionPractice.model.TestVo;

public interface TestService {
	public List<TestVo> getTestList(TestVo testvo);
}