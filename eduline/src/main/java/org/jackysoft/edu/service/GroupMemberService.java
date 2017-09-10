package org.jackysoft.edu.service;

import java.util.List;

import com.google.common.base.Joiner;
import org.jackysoft.edu.entity.GroupMember;
import org.jackysoft.edu.formbean.MemberBean;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.GroupMemberMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional("transactionManager")
public class GroupMemberService extends AbstractSQLService<String, GroupMember> {

	
	@Autowired
	private GroupMemberMapper mapper;

	public List<MemberBean> findByGroupId(String groupId){
		QueryBuilder qc = new QueryBuilder();
		Authentication author = SecurityContextHolder.getContext().getAuthentication();
		qc.setQueries("groupId`"+groupId+";teacher`"+author.getName());
		List<MemberBean> list = mapper.findMemberDetails(qc);
		return list;
	}
	
	public List<GroupMember> find4ScorePanel(String groupId){
		List<GroupMember> beans = mapper.findScorePanel(groupId);
		
		return beans;
	}

	public GroupMember findGroupParticipated(String student,int grade,int course){
        return mapper.findParticipated(student,grade,course);
	}

	public GroupMember findGroupFeature(String groupId) {
		return mapper.findGroupFeature(groupId);
	}
		
	
	public  List<GroupMember> findMembersOfGroup(String groupId){
	    List<GroupMember> list = mapper.findMembers(groupId);
	    return list;
	}
	
	
	public List<GroupMember> findTeacherGroups(String teacher){
		List<GroupMember> list = mapper.findGroupOfTeacher(teacher);
		return list;
	}
	
	
	@Override@PreAuthorize("hasRole('ROLE_TEACHER')")
	public ActionResult save(GroupMember t) {
		
		return super.save(t);
	}

	@Override@PreAuthorize("hasRole('ROLE_TEACHER')")
	public List<ActionResult> saveAll(List<GroupMember> list) {
		
		return super.saveAll(list);
	}

	@Override@PreAuthorize("hasRole('ROLE_TEACHER')")
	public ActionResult update(GroupMember t) {
		
		return super.update(t);
	}

	@Override@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void update(QueryBuilder qc) {
		
		super.update(qc);
	}

	@Override@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void remove(QueryBuilder qc) {
		
		super.remove(qc);
	}

	@Override@PreAuthorize("hasRole('ROLE_TEACHER')")
	public ActionResult removeById(String s) {
		
		return super.removeById(s);
	}

	@Override@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void updatePartial(String id, String props) {
		
		super.updatePartial(id, props);
	}

	public List<GroupMember> findByGroupIds(List<String> ids){
		List<GroupMember> list = mapper.findByGroupIds(Joiner.on("','").join(ids));
		return list;
	}
	@Override
	public AbstractMapper<String, GroupMember> getMapper() {
		return mapper;
	}

}
