package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jackysoft.edu.entity.UserEvent;

public interface UserEventMapper extends AbstractMapper<String, UserEvent> {

	
	@Select("select * from jq_userevent where starttime>=#{from} and endtime<=#{to} and ${extWhere}")
	List<UserEvent> findMyEvents(@Param("from")long from,@Param("to")long to,@Param("extWhere")String extWhere);
	
}
