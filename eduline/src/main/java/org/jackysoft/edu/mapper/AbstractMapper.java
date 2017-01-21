package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jackysoft.query.QueryBuilder;

public interface AbstractMapper<S, T> {
	
	
	List<T> findAll();

	List<T> findList(@Param("qc") QueryBuilder qc);
	
	List<T> findByQuery(@Param("qc") QueryBuilder qc,@Param("start")int start,@Param("offset")int offset);
	
	List<T> search(@Param("qc") QueryBuilder qc);

	List<T> findPageList(@Param("start")int start,@Param("offset") int offset);
	
	T findById(@Param("id")S id);
	
	long counts();
	
	long countByQuery(@Param("qc")QueryBuilder qc);
	
	void insert(T t);

	void delete(S id);
	
	void deleteByQuery(@Param("qc")QueryBuilder qc);

	void update(T t);
	
	
	void updatePartial(@Param("id")S s,@Param("sets")String sets);
	
	void updateByQuery(@Param("qc")QueryBuilder qc);
}
