package org.jackysoft.query;

public enum OuterRelation {
	AND("and"), OR("or");

	public static OuterRelation parse(String str) {
		return Enum.valueOf(OuterRelation.class, str);

	}

	private String value;

	private OuterRelation(String label) {
		this.value = label;

	}

	@Override
	public String toString() {
		return this.value.toUpperCase();
	}

	public static String getOptions() {
		String opts = "";
		for (OuterRelation c : OuterRelation.values()) {
			opts += String.format("<option value='%s'>%s</option>", c.name(),
					c.name());

		}
		return opts;

	}
}
