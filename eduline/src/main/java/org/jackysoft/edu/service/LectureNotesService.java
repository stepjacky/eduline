package org.jackysoft.edu.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.jackysoft.edu.entity.LectureNotes;
import org.jackysoft.edu.entity.NoteChapter;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.LectureNotesMapper;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;


@Service
@Transactional("transactionManager")
public class LectureNotesService extends AbstractSQLService<String, LectureNotes> {

	
	@Autowired
	private LectureNotesMapper mapper;
	
	@Autowired
	private NoteChapterService capService;

	@Autowired
	private SysUserService userService;
	
	/**
	 *@ 
	 **/
	public void sharedLecture(String noteId,String user){
		
	    if(Strings.isNullOrEmpty(noteId) || Strings.isNullOrEmpty(user)) return;
		SysUser target = userService.findById(user);
		if(target==null) return;
		LectureNotes bean = this.findById(noteId);
		if(bean==null) return;
		String noteNewId = UUID.randomUUID().toString();
		bean.setId(noteNewId);
		bean.setFiretime(Instant.now().toEpochMilli());
		bean.setOwner(target.getName());
		bean.setOwnerName(target.getNickname());
		mapper.insert(bean);
		
		List<NoteChapter> chapters = capService.findByLecture(noteId);
		for(NoteChapter nc:chapters){
			nc.setNoteId(noteNewId);
			String newChtId =  UUID.randomUUID().toString();
			String oldChtId =  new String(nc.getId());
			nc.setId(newChtId);
			if(nc.getParent().equals(noteId)){
				nc.setParent(noteNewId);
			}
			chapters.stream().filter(p->oldChtId.equals(p.getParent())).forEach(t->t.setParent(newChtId));
			
			capService.save(nc);
		}
		
	}
	
	
	public Pager<LectureNotes> findPagerForTeacher(int page,String owner,int course){
		List<LectureNotes> dataList = mapper.findForTeacher(page, owner, course);
		long count = mapper.countForTeacher(owner, course);
		Pager<LectureNotes> pager = Pager.build(page, count, dataList);
	    return pager;
	}
	
	
	public Pager<LectureNotes> findShared(int course,int page){
		QueryBuilder query = new QueryBuilder();
		query.setQueries("shared`EQ`1`AND"+(course!=-1?";course`EQ`"+course:""));
		int offset = 10;
		List<LectureNotes> list = mapper.findByQuery(query, page*offset, offset);
	    long count =	mapper.countByQuery(query);
		Pager<LectureNotes> pager = Pager.build(page, count, list);
		return pager;
	}
	
	
	public Pager<LectureNotes> findPagerForStudent(int page,String idz){
		List<LectureNotes> dataList = mapper.findByIdz(idz, page);
		long count = mapper.countByIdz(idz);
		Pager<LectureNotes> pager = Pager.build(page, count, dataList);
		return pager;
	}
	
	
	
	@Override
	public AbstractMapper<String, LectureNotes> getMapper() {
		return mapper;
	}

	
}
