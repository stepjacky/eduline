package org.jackysoft.edu.entity;

import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.formbean.Ztreeable;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public abstract class NoEntity implements Ztreeable {

	@Id
	protected String id;

	@Property
	protected long count;

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

	@Override
	public ZtreeNode toZtreeNode() {
		ZtreeNode node = new ZtreeNode();
		node.setId(id);
		return node;
	}

}
