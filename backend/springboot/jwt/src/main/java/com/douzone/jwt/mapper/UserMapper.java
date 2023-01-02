package com.douzone.jwt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.douzone.jwt.vo.UserVo;

@Mapper
public interface UserMapper {
	public List<UserVo> getUserList(UserVo userVo);	
}