package com.douzone.dbConnectionPractice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.dbConnectionPractice.model.ApiResult;
import com.douzone.dbConnectionPractice.model.TestVo;
import com.douzone.dbConnectionPractice.service.TestService;


@RestController
@RequestMapping("/test")
public class TestRestController {
	 
	@Autowired
	private TestService testService;
	
    @PostMapping("/getTestList")
    public ApiResult getTestList(@RequestBody TestVo test) {
    	ApiResult result = new ApiResult();
        result.setData(testService.getTestList(test));
        return result;
    }
    
}