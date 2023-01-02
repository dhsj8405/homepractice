package com.douzone.jwt.service;

import java.util.List;

import com.douzone.jwt.vo.UserVo;

public interface UserService {
	public List<UserVo> getTestList(UserVo userVo);
}
