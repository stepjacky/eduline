@set JAVA_HOME=D:\java\java8
@set M2_HOME=D:\java\libs\apache-maven-3.2.2
@set PATH=%PATH%;%M2_HOME%\bin
@mvn clean compile spring-boot:run -Drun.jvmArguments="-Dhostname=localhost -Dtest=true" -Dmaven.test.skip=true 