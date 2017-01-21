package org.jackysoft.edu.annotation;

public enum InputType {
   INPUT_TEXT("<input type='text' id='%s' name='%s' class='form-control' >"),
   INPUT_PASSWORD("<input type='password' id='%s' name='%s' class='form-control' >"),
   INPUT_CHECKBOX("<input type='checkbox' id='%s' name='%s'  >"),
   INPUT_RADIO("<input type='radio' id='%s' name='%s'  >"),
   INPUT_HIDDEN("<input type='hidden' id='%s' name='%s' >"),
   INPUT_DATE("<input type='text' class='datepicker form-control' ><input type='hidden' id='%s' name='%s'>"),
   SELECT("<select id='%s' name='%s' class='form-control' >%s</select><input type='hidden' id='%sName' name='%sName'>"),
   TEXTAREA("<textarea id='%s' name='%s' class='form-control' ></textarea>");
   
   private String html;
   InputType(String html){
	  this.html = html;
   }
   
   @Override
   public String toString() {
	   return html;
   }
   
}
