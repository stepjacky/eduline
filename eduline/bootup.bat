@set JAVA_HOME=d:\java\jdk8
@set PATH=%PATH%;d:\maven\bin
@mvn clean compile install spring-boot:repackage -Dmaven.test.skip=true