package org.jackysoft.utils;

import java.time.LocalDate;

import org.jackysoft.edu.formbean.Semester;

public class SchoolUtils {

	/**
	 * @param month 0 present current month
	 * */
	public static Semester getSemester(int month) {
		
		Semester s = null;
		int mt = month==0?LocalDate.now().getMonthValue():month;
			
		
		if(mt>=9 || mt<2) {
			
			 return new Semester(0);
			
		}else if(mt>=2 && mt<=8) {
				
			 return new Semester(1);
		}
			
	
		return s;
	}
	
}
