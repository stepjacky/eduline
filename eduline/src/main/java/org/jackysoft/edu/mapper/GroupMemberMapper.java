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
    
    
    @Select("select * from jq_groupmember g where g.groupId=#{groupId} group by groupId,student")
    List<GroupMember> findMembers(@Param("groupId")String groupId);
    
    @Select("select * from jq_groupmember g where g.teacher=#{teacher} group by teacher,groupId order by grade desc")
    List<GroupMember> findGroupOfTeacher(@Param("teacher")String teacher);
    
}
