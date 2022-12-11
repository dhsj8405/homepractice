package com.douzone.dbConnectionPractice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.douzone.dbConnectionPractice.model.TestVo;

@Mapper
public interface TestMapper {
	public List<TestVo> getTestList(TestVo testvo);	
}