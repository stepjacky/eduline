package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jackysoft.edu.entity.LectureNotes;

public interface LectureNotesMapper extends AbstractMapper<String, LectureNotes> {
	
	
	@Select("select * from jq_lecturenotes where owner=#{owner} and course=#{course} limit ${page*10},10")
	List<LectureNotes> findForTeacher(
			@Param("page")int page,
			@Param("owner")String owner,
			@Param("course")int course);
  
	@Select("select count(*) from jq_lecturenotes where owner=#{owner} and course=#{course}")
	long countForTeacher(@Param("owner")String owner,@Param("course")int course);
	
	@Select("select * from jq_lecturenotes where id in ('${idz}') limit ${page*10},10")
	List<LectureNotes> findByIdz(@Param("idz")String idz,@Param("page")int page);
	
	@Select("select count(*) from jq_lecturenotes where id in ('${idz}')")
	long countByIdz(@Param("idz")String idz);

}
