package com.douzone.chatRedis.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.chatRedis.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<UserVo> findAll() {
		return sqlSession.selectList("user.findAllId");	
	}

	public UserVo findOne(UserVo userVo) {
		return sqlSession.selectOne("user.findOneInfo", userVo);
	}

}
