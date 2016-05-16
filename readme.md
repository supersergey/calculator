# Online Calculator Test Assignment #

This application is a simple online calculator that evaluates arithmetic expressions.
It only supports +, -, /, =. Other operations are not implemented.
So don't try to calculate sin(x) on it.
                                       
## Installing and running the application ##

1. Download and install Java 8 from [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
  1. Run `java -version` to make sure that Java is intalled correctly.
  2. Should report `java version "1.8.xx`. If `java 1.7` or earlier is reported:
    1. Uninstall old java from your computer.
    2. Open `My Computer - Properties - Additional Properties - System Variables`, and specify Java 8 installation folder as `JAVA_HOME`, e.g. `JAVA_HOME = C:\Program Files\Java\jdk1.8.0_91\`
2. Download and install Maven from [https://maven.apache.org/]. 
  1. Open `My Computer - Properties - Additional Properties - System Variables`, and specify Maven installation folder as `M2_HOME` and as `MAVEN_HOME`, e.g. `MAVEN_HOME = C:\Program Files\Maven`
  2. Add path to `mvn.cmd` (e.g. `C:\Program Files\Maven\bin`) to your path.
  3. Run `mvn -version` to make sure that Maven is intalled correctly.
3. Clone this repository to your local folder, by running `git clone https://github.com/supersergey/calculator.git`
4. Enter the local folder and type `mvn exec:java`
5. Open `localhost:4567` in your web-browser.
6. You can register or login as a the pre-defined test user. login : `admin`, password: `admin`

## Technologies used

Spring Ioc, SparkJava, Sql2o, Freemaker, Bootstrap, JUnit

## Contact

[My LinkedIn profile](https://www.linkedin.com/in/sergey-tolokunsky-103a2b1)
                                                         
Enjoy ;-)