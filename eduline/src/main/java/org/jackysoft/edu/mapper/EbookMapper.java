package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jackysoft.edu.entity.Ebook;

public interface EbookMapper extends AbstractMapper<String, Ebook> {
    
	@Update("update jq_ebook set clicknum=clicknum+1 where id=#{id}")
	void updateClick(@Param("id")String id);
	
	@Select("select tags from jq_ebook")
	List<String> retriveTags();
}
