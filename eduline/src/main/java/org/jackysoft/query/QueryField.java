package org.jackysoft.query;

import java.io.Serializable;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;


/**
 * 代表查询条件
 **/
public class QueryField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3380674943538301028L;
	private String name;
	private InnerRelation inOpr = InnerRelation.EQ;
	private String value;
	private String type;
	private OuterRelation outOpr = OuterRelation.AND;
	private boolean noOutOpr = false;
	
	public QueryField() {
		super();
		this.type="string";
	}

	/**
	 * 带参构造
	 * 
	 * @param name
	 *            名
	 * @param inOpr
	 *            内关系
	 * @param value
	 *            值
	 * @param outOpr
	 *            外关系
	 * @param noOutOpr
	 *            是否有外关系
	 * 
	 **/

	public QueryField(String name, String value) {
		this();
		this.name = name;
		this.value = value;
		this.noOutOpr = true;

	}
	
	public QueryField(String name, String value,String type) {
		
		this(name,value);
		this.type = type;
		this.noOutOpr = true;		
	}

	public QueryField(String name, InnerRelation inOpr, String value){
		this(name,value);
		this.noOutOpr=true;
		this.inOpr = inOpr;
	}
	
	
	/**
	 * 带参构造
	 * 
	 * @param name
	 *            名
	 * @param inOpr
	 *            内关系
	 * @param value
	 *            值
	 * @param outOpr
	 *            外关系
	 * @param noOutOpr
	 *            是否有外关系
	 * 
	 **/

	public QueryField(String name, InnerRelation inOpr, String value,
			OuterRelation outOpr, boolean noOutOpr) {

		this(name, value);
		this.inOpr = inOpr;

		this.outOpr = outOpr;
		this.noOutOpr = noOutOpr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InnerRelation getInOpr() {
		return inOpr;
	}

	public void setInOpr(InnerRelation inOpr) {
		this.inOpr = inOpr;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = Strings.nullToEmpty(value);
	}

	public OuterRelation getOutOpr() {
		return outOpr;
	}

	public void setOutOpr(OuterRelation outOpr) {
		this.outOpr = outOpr;
	}

	public boolean isNoOutOpr() {
		return noOutOpr;
	}

	public void setNoOutOpr(boolean noOutOpr) {
		this.noOutOpr = noOutOpr;
	}

	@Override
	public String toString() {
		if (this.inOpr == null)
			this.inOpr = InnerRelation.EQ;
		if (InnerRelation.LIKE.equals(this.inOpr))
			return " " + this.name + " " + this.inOpr + " '%" + this.value
					+ "%' " + (this.noOutOpr ? "" : this.outOpr);
		
		if (InnerRelation.IN.equals(this.inOpr)){
			Splitter sp = Splitter.on(';');
			Iterable<String> vals =sp.split(value);
			Joiner jn = Joiner.on("','");
			this.value = String.format("('%s')", jn.join(vals));
			return " " + this.name + " " + this.inOpr + "  " + this.value
					+ " " + (this.noOutOpr ? "" : this.outOpr);
		}
			

		if ("false".equalsIgnoreCase(this.value)
				|| "true".equalsIgnoreCase(this.value))
			return " " + this.name + " is  " + this.value + "  "
					+ (this.noOutOpr ? "" : this.outOpr);

		return " " + this.name + " " + this.inOpr + " '" + this.value + "' "
				+ (this.noOutOpr ? "" : this.outOpr);
	}
	
	/**
	 * @return
	 *    返回语义化的http query 字符串
	 * 
	 * **/
	public String toSemanticString(){
		return String.format("%s=%s",name,value);
	}

	public String toSetz() {
		return String.format("%s=%s",name,value);
	}
	
	
	public String toOriginal() {
		return String.format("%s`%s`%s`%s", name,this.inOpr.name(),value,this.outOpr);
	}
	
	public String getType() {
		return type;
	}
	
	/**
	 * for nosql query ,at that then,parameter need its type
	 * */
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isEmpty(){
		return Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(value);
	}

	@Override
	public int hashCode() {
		return Strings.isNullOrEmpty(name)?super.hashCode():name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null || !(obj instanceof QueryField)) return false;
		QueryField that = (QueryField) obj;
		return Strings.isNullOrEmpty(name)?super.equals(obj):name.equalsIgnoreCase(that.name);
	}
	
	
}
