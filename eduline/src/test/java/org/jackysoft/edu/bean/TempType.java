package org.jackysoft.edu.bean;

public enum TempType {
   XmlSource(".xml"),JavaSource(".java"),Jetx(".jetx"),Css(".css"),Script(".js"),Other(""),ALL("");
   private String postfix="";
   TempType(String postfix){
	   this.postfix = postfix;
   }
   String getPostfix() {return postfix;}
   
}
