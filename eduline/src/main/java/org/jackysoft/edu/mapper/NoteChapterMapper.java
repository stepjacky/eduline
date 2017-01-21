package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jackysoft.edu.entity.NoteChapter;

public interface NoteChapterMapper extends AbstractMapper<String, NoteChapter> {

	
	@Select("select * from jq_notechapter where parent=#{parent} order by sort")
	List<NoteChapter> findByParent(@Param("parent")String parent);
	
	@Select("select * from jq_notechapter where noteId=#{noteId}")
	List<NoteChapter> findByLecture(@Param("noteId")String noteId);
	
}
