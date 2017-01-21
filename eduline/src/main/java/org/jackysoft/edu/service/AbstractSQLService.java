package org.jackysoft.edu.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.servlet.http.Part;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.entity.SysUser;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.formbean.Ztreeable;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.utils.DateUtils;
import org.jackysoft.utils.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@CacheConfig
@Transactional("transactionManager")
public abstract class AbstractSQLService<S, T> extends AbstractService<S, T> {

	static final Log logger = LogFactory.getLog(AbstractService.class);

		
	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName")
	public List<T> findAll() {
		return this.getMapper().findAll();
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#page+#offset")
	public List<T> findAll(int page, int offset) {
		int cpage = page;
		if (cpage <= 0)
			cpage = 1;

		return this.getMapper().findPageList(cpage * offset - offset, offset);
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#qc.where")
	public List<T> findAll(QueryBuilder qc) {
		return this.getMapper().findList(qc);
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#query")
	public List<T> search(String query) {
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries(query);
		return this.getMapper().search(qc);
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#qc.where+#page+#offset")
	public List<T> findAll(QueryBuilder qc, int page, int offset) {
		int cpage = page;
		if (cpage <= 0)
			cpage = 1;
		if (qc == null)
			this.findAll();
		qc.setPage(page);
		qc.setOffset(offset);
		return this.getMapper().findList(qc);
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#qc.where+#page+#offset")
	public Pager<T> findPager(QueryBuilder qc, int page, int offset) {

		
		if (qc == null)
			return this.findByPager(page, offset, true);
		qc.setPage(page);
		qc.setOffset(offset);
		List<T> dataList = this.getMapper().findByQuery(qc, qc.getStart(), offset);
		long count = this.getMapper().countByQuery(qc);
		Pager<T> pager = Pager.build(page, offset, count, dataList);
		return pager;
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#s")
	public T findById(S s) {
		T t = getMapper().findById(s);
		
		return t; 
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#t.toString()")
	public PreResult save(T t) {

		if (t == null)
			return PreResult.FAILURE;

		BeanWrapper bw = new BeanWrapperImpl(t);
		Set<Entry<String, String>> idSet = Sets.newHashSet();
		Set<Entry<String, String>> autoSet = Sets.newHashSet();
		List<Field> list = Arrays.asList(t.getClass().getDeclaredFields());
		list.forEach(new Consumer<Field>() {

			@Override
			public void accept(Field f) {
				if (!f.isAnnotationPresent(Column.class))
					return;
				Column col = f.getAnnotation(Column.class);
				switch (col.idStrategy()) {

				case RANDOMSTR:
					idSet.add(new HashMap.SimpleEntry<String, String>(f.getName(), StringUtils.randomstring()));
					break;
				case SECURITY_ROLE:
					idSet.add(
							new HashMap.SimpleEntry<String, String>(f.getName(), "ROLE_" + StringUtils.randomstring()));
					break;
				case UUID:
					idSet.add(new HashMap.SimpleEntry<String, String>(f.getName(), UUID.randomUUID().toString()));
					break;
				default:

					break;

				}

				Authentication a = SecurityContextHolder.getContext().getAuthentication();
				SysUser u = (SysUser) a;
				switch (col.autoValue()) {
				case CURRENT_TIMEMILLIS:
					autoSet.add(new HashMap.SimpleEntry<String, String>(f.getName(), DateUtils.currentMillis() + ""));
					break;

				case CURRENT_USERNAME:

					autoSet.add(new HashMap.SimpleEntry<String, String>(f.getName(), u.getUsername()));
					break;

				case CURRENT_USERNICK:
					autoSet.add(new HashMap.SimpleEntry<String, String>(f.getName(), u.getNickname()));
					break;
				case UUID:
					autoSet.add(new HashMap.SimpleEntry<String,String>(f.getName(),UUID.randomUUID().toString()));
					break;
				case NONE:
				default:
					break;

				}

			}

		});

		autoSet.forEach(e -> {
			if (bw.isWritableProperty(e.getKey())) {
				bw.setPropertyValue(e.getKey(), e.getValue());
			}

		});
		idSet.forEach(e -> {
			if (bw.isWritableProperty(e.getKey())) {
				bw.setPropertyValue(e.getKey(), e.getValue());
			}
		});

		this.getMapper().insert(t);
		return PreResult.SUCCESS;
	}

	@Override
	public List<PreResult> saveAll(List<T> list) {
		List<PreResult> rs = new ArrayList<>();
		if (list == null || list.isEmpty())
			return rs;
		
		list.stream().filter(t -> t != null).forEach(item -> rs.add(save(item)));
		return rs;
	}

	@Override
	// @CacheEvict(value="#root.targetClass+#root.methodName+#t.toString()")
	public void update(T t) {
		this.getMapper().update(t);

	}

	@Override
	// @CacheEvict(value="#root.targetClass+#root.methodName+#qc.where")
	public void update(QueryBuilder qc) {
		this.getMapper().updateByQuery(qc);

	}

	@Override
	// @CacheEvict(value="#root.targetClass+#root.methodName+#qc.where")
	public void remove(QueryBuilder qc) {

		this.getMapper().deleteByQuery(qc);
	}

	@Override
	// @CacheEvict(value="#root.targetClass+#root.methodName+#s")
	public void removeById(S s) {

		this.getMapper().delete(s);
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#page+#offset+#ajax+'paged'")
	public Pager<T> findByPager(int page, int offset, boolean ajax) {
		long count = getMapper().counts();
		int start = page <= 0 ? 0 : page * offset;
		int oft = offset <= 0 ? 10 : offset;
		List<T> list = getMapper().findPageList(start, oft);
		Pager<T> pager = Pager.build(page, offset, count, list, ajax);
		return pager;
	}

	@Override
	// @Cacheable(value="#root.targetClass+#root.methodName+#s.toString()")
	public void updatePartial(S id, String props) {
		if (props == null || id == null)
			return;
		logger.debug(props);
		Set<String> sets = new HashSet<>();
		String[] paires = props.split(";");
		List<String> plist = Lists.newArrayList(paires);
		if (plist.isEmpty())
			return;
		plist.stream().filter(s -> s != null).forEach(s -> {
			String[] ss = s.split(":");
			if (ss != null && ss.length == 2) {
				sets.add(ss[0] + "='" + ss[1] + "'");
			}
		});
		if (sets.isEmpty())
			return;
		logger.debug("params : " + sets);
		String sparam = Joiner.on(',').join(sets);
		if (Strings.isNullOrEmpty(sparam))
			return;
		getMapper().updatePartial(id, "SET " + sparam);

	}

	
	
	
	@Override
	public void updateSimple(S id, String key, String value) {
		this.updatePartial(id, key+":"+value);
		
	}
	
	

	@Override
	public void upload(T bean, Part part) {

	}
	
	

	@Override
	public List<ZtreeNode> ztree(String parent) {
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("parent`"+parent);
		List<T> beans = this.findAll(qc);
		List<ZtreeNode> nodes = new ArrayList<>();
		for(T t : beans){
			if(!(t instanceof Ztreeable))continue;
			nodes.add(Ztreeable.class.cast(t).toZtreeNode());			
		}
		return nodes;
	}

	@Override
	public abstract AbstractMapper<S, T> getMapper();

}
