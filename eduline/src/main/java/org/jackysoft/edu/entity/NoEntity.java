package org.jackysoft.edu.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.formbean.Ztreeable;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public abstract class NoEntity implements Ztreeable {

	@Id
	protected String id;

	@Property
	protected long count;

	@Property
	protected int sort;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}


	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public ZtreeNode toZtreeNode() {
		ZtreeNode node = new ZtreeNode();
		node.setId(id);
		return node;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
