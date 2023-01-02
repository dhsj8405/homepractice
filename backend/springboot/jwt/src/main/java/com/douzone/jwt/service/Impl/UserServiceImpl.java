package com.douzone.jwt.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jwt.mapper.UserMapper;
import com.douzone.jwt.service.UserService;
import com.douzone.jwt.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override 
	public List<UserVo> getTestList(UserVo userVo){
		return userMapper.getUserList(userVo);
	};
}