package org.jackysoft.edu.mapper;

import org.apache.ibatis.annotations.Param;
import org.jackysoft.edu.entity.Course;

public interface CourseMapper extends AbstractMapper<Integer, Course> {

	void setType(@Param("id")String id,@Param("type")int type);
	
}
