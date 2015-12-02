set M2_HOME=S:\apache-maven-3.3.3
%M2_HOME%\bin\mvn -f %2\deploy-40-pom.xml -P %1 antrun:run