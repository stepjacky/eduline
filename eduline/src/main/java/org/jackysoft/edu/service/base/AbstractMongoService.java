package org.jackysoft.edu.service.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;

import com.google.common.base.Strings;
import com.mongodb.AggregationOptions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.jackysoft.edu.entity.NoEntity;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.query.QueryField;
import org.jackysoft.query.QueryParser;
import org.jackysoft.utils.ClassMetaUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.WriteResult;

public abstract class AbstractMongoService<E extends NoEntity> extends AbstractService<String, E> {

	static final Log logger = LogFactory.getLog(AbstractMongoService.class);

	protected Class<E> type;
	
	private Map<String,Field> fields = new HashMap<>();

	protected AggregationOptions aggregationOptions  = AggregationOptions.builder()
			.outputMode(AggregationOptions.OutputMode.CURSOR)

						.build();


	@PostConstruct
	private void initialize(){
		Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != AbstractMongoService.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        this.type = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
		
        if(this.type!=null) {
			Field[] fds = ClassMetaUtils.getFields(this.type);
			if(fds!=null) {
				for(Field f :fds) {
					fields.put(f.getName(), f);
				}
			}
		}
	}
	
	
	@Autowired
	protected Datastore dataStore;
	
	public ActionResult save(E e){
		ActionResult result = new ActionResult();
		dataStore.save(e);
		result.put("bean",e);
		result.setFlag(true);
		return result;
	}
	
	
	
	public WriteResult remove(E e){
		WriteResult rst = dataStore.delete(e);
		return rst;
	}
	
	public List<E> findAll(){
		List<E> list = dataStore.createQuery(type).asList();
		return list;
	}
	
	public Pager<E> findPager(int page){
		long count = dataStore.getCount(dataStore.createQuery(type));
		List<E> dataList = dataStore.createQuery(type).asList();
		Pager<E>  pager = Pager.build(page, Pager.DEFAULT_OFFSET, count, dataList,false);
		return pager;
	}
	
	public Pager<E> findQuery(int page,String express){
		QueryParser parser = new QueryParser(express);
		List<QueryField> list= parser.parseExpression(express);
	    Query<E> query =  dataStore.createQuery(type);
	    list.forEach(f->{
	    	query.field(f.getName()).equal(f.getValue());
	    	
	    });
	    List<E> dataList = query.limit(Pager.DEFAULT_OFFSET).offset(Pager.DEFAULT_OFFSET*(page<0?0:page)).asList();
	    long count = dataStore.getCount(query);
	    Pager<E> pager =  Pager.build(page, Pager.DEFAULT_OFFSET, count, dataList,false);
	    return pager;
	 
	}
	
	public void updateOnly(String id,String key,String value){
		UpdateOperations<E> update = dataStore.createUpdateOperations(type).add(key, value);
		
		dataStore.update(queryById(id), update);
	}

	@Override
	public List<E> findAll(int page, int offset) {
		List<E> list = dataStore.find(type).offset(page*offset).limit(offset).asList();
		return list;
	}

	@Override
	public List<E> findAll(QueryBuilder qc) {
		Query<E> query = dataStore.createQuery(type);
		qc.getFieldMap().forEach((k,v)->{query.field(k).equal(v);});
		return query.asList();
	}

	@Override
	public List<E> search(String query) {
		
		return null;
	}

	@Override
	public List<E> findAll(QueryBuilder qc, int page, int offset) {
		Query<E> query = dataStore.createQuery(type);
		qc.getFieldMap().forEach((k,v)->{query.field(k).equal(v);});
		List<E> list = query.limit(offset).offset(page*offset).asList();
		return list;
	}

	@Override
	public Pager<E> findByPager(int page, int offset, boolean ajax) {
		Query<E> query = dataStore.createQuery(type);
		long count = query.countAll();
		List<E> dataList = findAll(page,offset);
		Pager<E> pager = Pager.build(page, count, dataList);
		return pager;
	}

	@Override
	public Pager<E> findPager(QueryBuilder qc, int page, int offset) {
		Query<E> query = dataStore.createQuery(type);
		long count = query.countAll();
		List<E> dataList = findAll(qc);
		Pager<E> pager = Pager.build(page, count, dataList);
		return pager;
	}

	@Override
	public E findById(String s) {
		if(Strings.isNullOrEmpty(s)) return null;
		Query<E> query = dataStore.createQuery(type);
		E bean= queryById(s).get();
		return bean;
	}

	@Override
	public List<ActionResult> saveAll(List<E> list) {
		if(list==null) return null;
		dataStore.save(list);
		return null;
		
	}

	@Override
	public ActionResult update(E t) {
		Query<E> query = queryById(t.getId());
		t.setId(null);
		dataStore.updateFirst(query,t,false);
		return ActionResult.SUCCESS;
	}

	@Override
	public void updatePartial(String id, String props) {
		Map<String,Object> fields = new QueryParser().parsePartial(props);
		UpdateOperations<E> operations =  dataStore.createUpdateOperations(type);
		fields.forEach((k,v)->operations.set(k, v));
		dataStore.update(queryById(id),  operations,true);
	}

	@Override
	public void updateSimple(String id, String key, String value) {
		dataStore.update(queryById(id),
				 dataStore.createUpdateOperations(type).set(key, value),true);
		
	}

	@Override
	public void update(QueryBuilder qc) {
				
	}

	@Override
	public void remove(QueryBuilder qc) {
				
	}

	@Override
	public ActionResult removeById(String s) {
		dataStore.delete(queryById(s));
		return ActionResult.SUCCESS;
		
	}

	@Override
	public void upload(E bean, Part part) {

		
	}

	protected Query<E> query(){
		return this.dataStore.createQuery(type);
	}
	protected Query<E> queryById(String s){
		if(Strings.isNullOrEmpty(s) || !ObjectId.isValid(s)) return query();
		return query().field(Mapper.ID_KEY).equal(new ObjectId(s));
	}
	protected <T> Query<T> query(Class<T> cls){
		return this.dataStore.createQuery(cls);
	}


	protected UpdateOperations<E> updates(){
		return this.dataStore.createUpdateOperations(type);
	}

	protected <T> UpdateOperations<T> updates(Class<T> cls){
		return this.dataStore.createUpdateOperations(cls);
	}

	@Override
	public void sort(String s, int sort) {
		Query<E> query = queryById(s);
		UpdateOperations<E> updates  =this.dataStore.createUpdateOperations(type).set("sort",sort);
		this.dataStore.update(query,updates);
	}

	@Override
	public List<ZtreeNode> ztree(String parent) {
		List<ZtreeNode> nodes = new ArrayList<>();

		if(Strings.isNullOrEmpty(parent)) {
			return nodes;
		}
		List<E> list = dataStore.createQuery(type)
				.field("parent").equal(parent)
				.order("sort")
				.asList();

		nodes.addAll(list.stream().map(E::toZtreeNode).collect(Collectors.toList()));
		return nodes;
	}
	
}
