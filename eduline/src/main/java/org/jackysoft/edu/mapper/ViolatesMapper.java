package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jackysoft.edu.entity.Violates;
import org.jackysoft.edu.formbean.ChartDataSource;
import org.jackysoft.query.QueryBuilder;

public interface ViolatesMapper extends AbstractMapper<String, Violates> {
	
	  @Select(" SELECT sum(scoreValue)  from jq_violates where ${qc.where} ")
      Float accumulatePoints(@Param("qc")QueryBuilder qc);
      List<Violates> queryCurrentGrade(@Param("grade")int grade,@Param("eduyear")int eduyear,@Param("page")int page,@Param("offset")int offset);
      Long queryCurrentGradeCount(@Param("grade")int grade,@Param("eduyear")int eduyear);
      
      @Select("select grade,gradeName, sum(if(affirmative>0,scoreValue*1,0)) uppoint,sum(if(affirmative<0,scoreValue*-1,0)) downpoint from jq_violates" 
    		  + " where year(from_unixtime(recordtime/1000))=#{eduyear}"
    		  +	" and month(from_unixtime(recordtime/1000))=#{edumonth} group by grade")
      List<ChartDataSource> findChartDataSource(@Param("eduyear")int eduyear,@Param("edumonth")int edumonth);
      
}
