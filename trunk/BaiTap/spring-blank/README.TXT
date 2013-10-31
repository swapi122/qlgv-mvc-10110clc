This is a blank Eclipse project that can be copied 
and renamed when building Spring projects. 
It was built as follows:
- Made new Java project in Eclipse
- Added Spring-aware features by R-clicking and
  selecting "Spring Tools --> Add Spring Project Nature"
  This assumes you have the Spring plugin from SpringIDE.org.
  However, this project will work without the plugin: you
  just won't have the cool Spring features.
- Made a new project directory called "lib"
- Copied <spring-install>/dist/spring.jar and
  <spring-install>/lib/jakarta-commons/commons-logging.jar
  to the lib folder.
- Added the two JAR files to the project build path by
  R-clicking on the project, selecting Properties, 
  then Libraries, then Add JARs, then pointing at 
  lib/spring.jar and lib/commons-logging.jar
- Made an empty applicationContext.xml file in the src folder

To use this as a starting point for your projects, R-click
on the project, select Copy, then R-click in the Project
Explorer, and choose Paste (with a new name). The procedure
for using Spring in Web apps is different: make a new Dynamic
Web Project, add the Spring Project Nature, and put the JAR
files in WebContent/WEB-INF/lib.

For more details, see http://courses.coreservlets.com/Course-Materials/spring.html