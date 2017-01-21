package org.jackysoft.edu.annotation;

public enum IdStrategy {
    //uuid 生成
	UUID,
	//随机字符串
    RANDOMSTR,
    //数据库自增
    AUTOINC,
    
    //安全角色
    SECURITY_ROLE,
    
    //手工赋值
    MANUAL
}
