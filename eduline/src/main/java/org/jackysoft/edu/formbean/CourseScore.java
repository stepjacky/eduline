package org.jackysoft.edu.formbean;

public class CourseScore {
	private int style;
	private String courseName;
	private String groupNamea;
	private String groupNameb;
	private String groupNamec;
	
	private float a;
	private float b;
	private float c;
	private float d;
	private float e;
	private float f;
		
	
	public CourseScore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseScore(int style) {
		this();
		this.style = style;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setA(float a) {
		this.a = a;
	}

	public String getA() {
		return rank(style,a);
	}

	public void setB(float b) {
		this.b = b;
	}

	public String getB() {
		return rank(style,b);
	}

	public void setC(float c) {
		this.c = c;
	}

	public String getC() {
		return rank(style,c);
	}

	public void setD(float d) {
		this.d = d;
	}

	public String getD() {
		return rank(style,d);
	}

	public void setE(float e) {
		this.e = e;
	}

	public String getE() {
		return rank(style,e);
	}

	public void setF(float f) {
		this.f = f;
	}

	public String getF() {
		return rank(style,f);
	}

	public String rank(int style,float score) {
		int iscore = Math.round(score);
		
		String grade = styledScoreString(style,iscore);

		String rank = alphaRank(iscore);
		if(rank==null) return "-";
		return grade+"/"+rank;
		
		
	}
		
	public float gpaScore(int iscore){
		
		// A*=90-100% (GPA 4.0); A=80-89% (GPA 3.5); B=70-79% (GPA3.0);
		// C=60-69% (GPA 2.5); D=50-59% (GPA 2.0); E=40-49% (GPA 1.5)
		if (iscore >= 90) {
			return 4.0f;
		}
		if (iscore >= 80 && iscore < 90) {
			return 3.5f;
		}
		if (iscore >= 70 && iscore < 80) {
			return  3.0f;
		}
		if (iscore >= 60 && iscore < 70) {
			return  2.5f;
		}
		if (iscore >= 50 && iscore < 60) {
			return  2.0f;
		}
		if (iscore >= 40 && iscore < 50) {
			return  1.5f;
		}
			
		return 0;
	}
	
	public float styledScore(int style,int iscore){
		return style==0?iscore:gpaScore(iscore);
	}
	
	public String styledScoreString(int style,int iscore){
		return styledScore(style,iscore)+"";
	}
	public String gpa(int iscore){
		
		return  " (GPA "+gpaScore(iscore)+") ";
		
	}

	public String getGroupNamea() {
		return groupNamea;
	}

	public void setGroupNamea(String groupNamea) {
		this.groupNamea = groupNamea;
	}

	public String getGroupNameb() {
		return groupNameb;
	}

	public void setGroupNameb(String groupNameb) {
		this.groupNameb = groupNameb;
	}

	public String getGroupNamec() {
		return groupNamec;
	}

	public void setGroupNamec(String groupNamec) {
		this.groupNamec = groupNamec;
	}

	public boolean hasAny() {
		return a != 0f || b != 0f || c != 0f || d != 0f || e != 0f || f != 0f;

	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	
	public String alphaRank(int iscore){
		if (iscore >= 90) {
			return "A+";
		}
		if (iscore >= 80 && iscore < 90) {
			return "A";
		}
		if (iscore >= 70 && iscore < 80) {
			return "B";
		}
		if (iscore >= 60 && iscore < 70) {
			return "C";
		}
		if (iscore >= 50 && iscore < 60) {
			return "D";
		}
		if (iscore >= 40 && iscore < 50) {
			return "E";
		}
		if (iscore < 40){
			return "F";
		}
		return null;
	}
}
