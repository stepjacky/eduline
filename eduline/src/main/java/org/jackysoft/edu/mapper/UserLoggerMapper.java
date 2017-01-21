package org.jackysoft.edu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jackysoft.edu.entity.UserLogger;

public interface UserLoggerMapper extends AbstractMapper<String, UserLogger> {
	
	@Insert("insert into jq_userlogger(id,username,nickname,firetime,action,ipaddr) "
			+ "values(#{bean.id},#{bean.username},#{bean.nickname},#{bean.firetime},#{bean.action},#{bean.ipaddr}) "
			+ " ON DUPLICATE KEY UPDATE ipaddr=VALUES(ipaddr)")
	void insert(@Param("bean")UserLogger t);
}
