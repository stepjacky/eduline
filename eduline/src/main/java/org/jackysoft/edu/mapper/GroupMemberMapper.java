package org.jackysoft.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.formbean.MemberBean;
import org.jackysoft.query.QueryBuilder;

public interface GroupMemberMapper extends AbstractMapper<String, GroupMember> {
    GroupMember findGroupFeature(@Param("groupId")String groupId);
    List<MemberBean> findMemberDetails(@Param("qc") QueryBuilder qc);
    
    List<GroupMember> findScorePanel(@Param("groupId")String groupId);

    @Select("select * from jq_groupmember where student=#{student} and grade=${grade} and  course=${course} group by teacher,grade,course limit 1;")
    GroupMember findParticipated(@Param("student")String student,@Param("grade") int grade,@Param("course")int course);

    @Select("select * from jq_groupmember g where g.groupId=#{groupId} group by groupId,student")
    List<GroupMember> findMembers(@Param("groupId")String groupId);
    
    @Select("select * from jq_groupmember g where g.teacher=#{teacher} group by teacher,groupId order by grade desc")
    List<GroupMember> findGroupOfTeacher(@Param("teacher")String teacher);

    @Select("select * from jq_groupmember g where g.groupId in ('${idz}') group by groupId")
    List<GroupMember> findByGroupIds(@Param("idz")String idz);
    
}
