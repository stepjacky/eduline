package org.jackysoft.utils;

import java.time.LocalDate;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
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

	public static Integer getInyear(String username){
		if(Strings.isNullOrEmpty(username)) return null;
			String iy = username.substring(2, 4);
			Integer niy = Ints.tryParse(iy);
			return niy+2000;

	}

	public static int getGrade(String username){
		if(Strings.isNullOrEmpty(username) || username.length()<4) return 0;
		LocalDate date = LocalDate.now();
		boolean downed = date.getMonthValue()>=8;
		int cy = date.getYear();
		String iy = username.substring(2, 4);
		if(!StringUtils.isInteger(iy))return 0;
		int niy = Integer.parseInt(iy);
		return cy-(niy+2000)+(downed?0:-1)+7;
	}
	
}
