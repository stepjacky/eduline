package org.jackysoft.edu.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jackysoft.edu.entity.SysUser;

public interface SysUserMapper extends AbstractMapper<String, SysUser> {
   
	@Select("select username,nickname,grade from jq_sysuser where nickname=#{nickname} limit 1")
	SysUser findByNickname(@Param("nickname")String nickname);
}
