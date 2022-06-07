Unzip sonarqube
go to bin>windows
run batch file
localhost:9090 will host dashboard
initial use name pwd is admin/admin
change it

localhost:9000 > account > security > genrate token

add plugins to project
<plugin>
<groupId>org.sonarsource.scanner.maven</groupId>
<artifactId>sonar-maven-plugin</artifactId>
<version>3.4.0.905</version>
</plugin>

mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=the_generated_token