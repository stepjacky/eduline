package org.jackysoft.query;

public enum InnerRelation {
	EQ("=")
	, NEQ("!=")
	, LT("<")
	, GTEQ(">=")
	, GT(">")
	, LTEQ("<=")
	, LIKE("like")
	//不同值用分号分割 a;b;c;d;e
	, IN("in");

	private String qs = "=";

	InnerRelation(String qs) {
		this.qs = qs;

	}

	@Override
	public String toString() {
		return this.qs;
	}

	public String getName() {
		return this.name();
	}

	public String getValue() {
		return this.qs;
	}

	public static String getOptions() {
		String opts = "";
		for (InnerRelation c : InnerRelation.values()) {
			opts += String.format("<option value='%s'>%s</option>", c.name(),
					c.name());

		}
		return opts;

	}
}
