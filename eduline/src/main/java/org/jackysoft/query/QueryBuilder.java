package org.jackysoft.query;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * <dd>通用查询类 <dd>代表一次查询收集上来的条件
 **/
public class QueryBuilder implements Cloneable {

	private static final Log logger = LogFactory.getLog(QueryBuilder.class);

	protected static final QueryTypeAliasRegistry TYPES = new QueryTypeAliasRegistry();

	protected GroupBy groupBy;

	protected int page = 1;

	protected int offset = 10;

	private String rtxexpr = null;

	private String orderexpr = "";

	QueryParser expbuilder = new QueryParser();

	public QueryBuilder() {
		 this(new QueryParser());
	}
	
	public QueryBuilder(Map<String,String> map) {
		this(new QueryParser(map));
	}
	
	public QueryBuilder(QueryParser parser) {
		this.expbuilder = parser;		
	}
	
	/**
	 * 组合的最终SQL查询字符串
	 **/
	public String getWhere() {

		List<QueryField> fields = Lists.newArrayList(expbuilder.parseQueryFields(this.rtxexpr));

		String qs = "";

		if (fields.isEmpty())
			qs = " 1=1 ";
		else {

			ListIterator<QueryField> litr = fields
					.stream()
					.filter(f -> f != null
							&& !Strings.isNullOrEmpty(f.getValue()))
					.collect(Collectors.toList()).listIterator();

			int size = fields.size();
			for (; litr.hasNext();) {
				QueryField qf = litr.next();
				if (qf == null)
					continue;
				int nidx = litr.nextIndex();
				qf.setNoOutOpr(nidx == size);
				qs += qf;
			}
		}
		
		if (this.groupBy != null
				&& !Strings.isNullOrEmpty(this.groupBy.toGroupBy())) {
			qs += this.groupBy.toGroupBy();
		}

		if (!Strings.isNullOrEmpty(this.orderexpr)) {
			qs += " ORDER BY " + this.orderexpr;

		}
		String qstring = Strings.isNullOrEmpty(qs) ? " 1=1 " : qs;

		logger.debug("查询字符串:" + qstring);

		return qstring;
	}

	public Map<String,Object> getFieldMap(){
		Map<String,Object> maps = new HashMap<>();
		List<QueryField> fields = Lists.newArrayList(expbuilder.parseQueryFields(this.rtxexpr));
		fields.forEach(e->maps.put(e.getName(), e.getValue()));
		return maps;
	}
	public Map<String,Object> getFieldMap(String query){
		Map<String,Object> maps = new HashMap<>();
		List<QueryField> fields = Lists.newArrayList(expbuilder.parseQueryFields(query));
		fields.forEach(e->maps.put(e.getName(), e.getValue()));
		return maps;
	}
	
	public String getSetz() {

		List<QueryField> sets = Lists.newArrayList(expbuilder.parseQueryFields(this.rtxexpr));
		if (CollectionUtils.isEmpty(sets))
			return null;

		return String.join(
				",",
				sets.stream()
						.filter(f -> f != null
								&& !Strings.isNullOrEmpty(f.getName())
								&& !Strings.isNullOrEmpty(f.getValue()))
						.map(f -> f.toSetz()).collect(Collectors.toSet()));
	}

	public String semanticHttpQuery() {
		List<QueryField> fields = Lists.newArrayList(expbuilder.parseQueryFields(this.rtxexpr));
		if (fields == null)
			return "";
		return String.join(
				"&",
				fields.stream().filter(f -> f != null)
						.map(f -> f.toSemanticString())
						.collect(Collectors.toSet()));

	}

	public String getHttpQuery() {
		List<QueryField> fields = Lists.newArrayList(expbuilder.parseQueryFields(this.rtxexpr));
		if (fields.isEmpty())
			return "";
		return String.join(
				"&",
				fields.stream()
						.filter(f -> Strings.isNullOrEmpty(f.getValue()))
						.map(f -> f.toOriginal()).collect(Collectors.toList()));

	}

	public void setPage(int page) {
		this.page = page <= 0 ? 0 : page;
	}

	public int getStart() {
		return (page + 1) * offset - offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public boolean isPaged() {
		return page > 0;
	}



	/**
	 *@param query synax is: queryName`[EQ|LT|...[queryValue[`[AND|OR]]]] 
	 **/
	public void setQueries(String query) {
		this.rtxexpr = query;
	}

	public void setGroupBy(String group) {

		this.groupBy = new GroupBy(group);
	}

	public void setSets(String setzexpr) {
	}

	public void setOrders(String orderexpr) {

		this.orderexpr = orderexpr;
	}

	@Override
	public QueryBuilder clone() {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(this);
			out.close();

			ByteArrayInputStream bin = new ByteArrayInputStream(
					bout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object ret = in.readObject();
			return (QueryBuilder) ret;

		} catch (Exception e) {
			return null;
		}
	}
}
