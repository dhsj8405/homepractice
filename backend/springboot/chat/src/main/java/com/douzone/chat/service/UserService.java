package com.douzone.chat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.chat.repository.UserRepository;
import com.douzone.chat.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public  Map<String, Object> getUser() {

		List<UserVo> list = userRepository.findAll();
//		for(UserVo userVo: list) {
//			System.out.println(userVo.getId());
//		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "list", list );
		return map;
	}



}
