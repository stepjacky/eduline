package org.jackysoft.edu.formbean;

import java.util.List;

import com.google.common.collect.Lists;

public class UserEventBean {
	private int success = 1;

	private List<UserEventBody> result = Lists.newArrayList();

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public List<UserEventBody> getResult() {
		return result;
	}

	public void setResult(List<UserEventBody> result) {
		this.result = result;
	}

	
}
