package org.jackysoft.edu.service;

import java.util.List;
import java.util.Set;

import org.jackysoft.edu.entity.Ebook;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.edu.mapper.EbookMapper;
import org.jackysoft.edu.service.base.AbstractSQLService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.springframework.web.servlet.ModelAndView;


@Service
@Transactional("transactionManager")
public class EbookService extends AbstractSQLService<String, Ebook> {

	static final Logger logger = LoggerFactory.getLogger(EbookService.class); 
	@Autowired
	private EbookMapper mapper;

	
	
		
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public ActionResult update(Ebook t) {
		return super.update(t);
	}




	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public void update(QueryBuilder qc) {
		super.update(qc);
	}




	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public void remove(QueryBuilder qc) {
		super.remove(qc);
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public ActionResult removeById(String s) {

		return super.removeById(s);

	}




	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	public void updatePartial(String id, String props) {
		// TODO Auto-generated method stub
		super.updatePartial(id, props);
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#qc.where+#page+#offset")
	public Pager<Ebook> findPager(QueryBuilder qc, int page, int offset) {

		if (qc == null)
			return this.findByPager(page, offset, false);
		qc.setPage(page);
		qc.setOffset(offset);
		List<Ebook> dataList = this.getMapper().findByQuery(qc, qc.getStart(), offset);
		long count = this.getMapper().countByQuery(qc);
		Pager<Ebook> pager = Pager.build(page, offset, count, dataList);
		return pager;
	}
	
	public Set<String> findTags(){
		List<String> raws  = mapper.retriveTags();
		Set<String> tags = Sets.newHashSet();
		if(raws==null||raws.isEmpty())return tags;
		raws.forEach(r->{
			if(r!=null){
				List<String> rst = Splitter.on(',').splitToList(r);
				if(rst.size()==1){
					rst = Splitter.on('，').splitToList(r);
				}
				tags.addAll(rst);
			}
			
		});
		return tags;
	}

	@Override
	public void beforeInput(ModelAndView mav) {
		mav.addObject("tags", findTags());
	}

	@Override
	public AbstractMapper<String, Ebook> getMapper() {
		return mapper;
	}

    public void incrementClicked(String id) {
		mapper.updateClick(id);		
	}

}
