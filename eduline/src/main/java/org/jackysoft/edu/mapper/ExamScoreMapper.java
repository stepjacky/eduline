package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jackysoft.edu.entity.Course;
import org.jackysoft.edu.entity.ExamScore;
import org.jackysoft.edu.formbean.InYearScoreDetail;
import org.jackysoft.edu.formbean.ScoreInfo;
import org.jackysoft.edu.formbean.SortedTotalScore;

public interface ExamScoreMapper extends AbstractMapper<String, ExamScore> {
    List<ExamScore> reportCard(@Param("user")String user);
    List<Course> reportCourse(@Param("jors")int jors,@Param("user") String user);
    
    List<ExamScore> findScoredStudent(@Param("es")ExamScore es);
    
    /**
     * 更新分数排名,需要较长时间大概10分钟
     **/    
    void updateScoreState(@Param("year")int year,@Param("grade")int grade,@Param("semester")int semester,@Param("monthly")int monthly);
    /**
     * 获取某学生参加的所有考试信息情况
     * */
    List<ExamScore> findStudentExamInfo(@Param("user")String user);
    
    /**
     * 获取某届学生参加过的所有考试
     * */
    @Select("select * from jq_examscore  where inyear=#{inyear} group by inyear desc,grade desc,semester desc ,monthly desc")
    List<ExamScore>  findGraduatesExamInfo(@Param("inyear")int inyear);
    
    @Select("select * from jq_examscore  where inyear=#{inyear} and grade=#{grade} and semester=#{semester} and monthly=#{monthly} and student=#{student} limit 1")
    ExamScore findSingleExamScore(
    		@Param("inyear")int inyear,
    		@Param("grade")int grade,
    		@Param("semester")int semester,
    		@Param("monthly")int monthly,
    		@Param("student")String student);
    /**
     * 获取某次考试某学生所有成绩情况
     * */
    List<ExamScore> findExamDetail(@Param("es") ExamScore es);
    
    /**
     * 获取某次考试某学生所有成绩单详情
     * */
    List<ScoreInfo> findScoreInfo(@Param("es") ExamScore es);
    /**
     * 获取某学生某次考试总分排名情况
     * */
    SortedTotalScore findExamTotalDetail(@Param("es") ExamScore es); 
    
    
    /**
     * 获取某界学生参加的所有考试信息
     * */
    ExamScore findInYearExamInfo(@Param("inyear") int inyear,@Param("page") int page);
    long  findInYearExamInfoCount(@Param("inyear") int inyear);
    
       
    
    /**
     * 获取全年级学生各科成绩排名
     * */
    List<InYearScoreDetail> findInYearDetails(@Param("es") ExamScore es);
    
    /**
     * 获取全年级学生各科均分分布
     * */
    List<SortedTotalScore> findGradedSeries(@Param("es") ExamScore es);
    
    
    @Update(" update jq_examscore ${sets} where id=#{id}; ")
    void updatePartial(@Param("id")String s,@Param("sets")String sets);
    
    @Insert("${sqls}")
    void importScore(@Param("sqls")String sqls);
    
}
