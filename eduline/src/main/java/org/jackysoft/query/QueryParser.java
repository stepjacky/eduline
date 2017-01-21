package org.jackysoft.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class QueryParser {
	
	protected static final Log logger = LogFactory.getLog(QueryParser.class);
	protected static final char inspt = '`';
	protected static final char outspt = ';';
    
	protected Collection<QueryField> qfields = Lists.newArrayList();
	private Map<String,String> extMap = Maps.newHashMap();
	
	private String express;
	public QueryParser() {
		super();
		
	}

	public QueryParser(Map<String,String> maps) {
		if(maps==null) return;
		extMap.putAll(maps);		
	}
	
	
	public QueryParser(String expr) {
		this();		
		this.express = expr;
		parseQueryFields(expr);
	}

	Collection<QueryField> parseQueryFields(String expr) {
		List<QueryField> fields = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(expr)) {
			parseExpress(fields,expr);		
		}
		if(!Strings.isNullOrEmpty(this.express)){
			parseExpress(fields,this.express);		
		}
		qfields.clear();	
		this.extMap.entrySet().stream().forEach(e->fields.add(new QueryField(e.getKey(),e.getValue())));
		this.qfields.addAll(fields);	
		return fields;
	}
	
	public final List<QueryField> parseExpression(final String express){
		List<QueryField> qfs = new ArrayList<>();
		parseExpress(qfs,express);
		return qfs;
	}
	
	private void parseExpress(final List<QueryField> fields,final String express){
		List<String> fieldstr = Splitter.on(outspt).splitToList(express);
		for (String fs : fieldstr) {
			List<String> ps = Splitter.on(inspt).splitToList(fs);
			if (ps == null || ps.size() < 2)
				continue;
			QueryField qf = new QueryField();
			if(ps.size()==2) {
				String ts = ps.get(0);
				if (Strings.isNullOrEmpty(ts))
					continue;
				qf.setName(ts);
				ts = ps.get(1);
				if (Strings.isNullOrEmpty(ts))
					continue;
				qf.setValue(ts);
			}else if(ps.size()==3) {
				String ts = ps.get(0);
				if (Strings.isNullOrEmpty(ts))
					continue;
				qf.setName(ts);
				ts = ps.get(1);
				if (Strings.isNullOrEmpty(ts))
					continue;
				qf.setInOpr(InnerRelation.valueOf(ts));
				ts = ps.get(2);
				if (Strings.isNullOrEmpty(ts))
					continue;
				qf.setValue(ts);
			}else if(ps.size()>3) {
				String ts = ps.get(0);
				if (Strings.isNullOrEmpty(ts))
					continue;
				qf.setName(ts);
				ts = ps.get(1);
				if (Strings.isNullOrEmpty(ts)) {
					qf.setInOpr(InnerRelation.EQ);
				} else {
					qf.setInOpr(InnerRelation.valueOf(ts));
				}
				ts = ps.get(2);
				if (Strings.isNullOrEmpty(ts))
					continue;
				qf.setValue(ts);
				
				ts = ps.get(3);
				if (Strings.isNullOrEmpty(ts)) {
					qf.setOutOpr(OuterRelation.AND);
				} else {
					qf.setOutOpr(OuterRelation.valueOf(ts));
				}
			}				
			fields.add(qf);
		}
	}
	
	/**
	 *@see void org.jackysoft.edu.service.ServiceProvider.updatePartial(S id, String props)
	 **/
	public Map<String,Object> parsePartial(String partial ){
		Map<String,Object> maps = new HashMap<>();
		Splitter.on(';').split(partial).forEach(f->{
			List<String> ita = Splitter.on(':').splitToList(f);
			if(ita.size()<2) return;
			maps.put(ita.get(0), ita.get(1));
		});
		return maps;
	}
		
	public String findValue(final String fieldName) {
		for(QueryField f:this.qfields){
			if(f.getName().equalsIgnoreCase(fieldName)){
				return f.getValue();
			}
		}
		
		return null;
	}
}
