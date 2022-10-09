### Pre-requisite:
- JAVA 8
- MAVEN

### Description
- This project implements a Markdown to HTML converter. `pom.xml` file has the list of dependencies used
- Formatter class will accept an input file path, parse the file and will create a HTML file under "src/main/resources/output.html"
- The input file should be having the values separated by empty lines
- TestFormatter is the test class implemented by using TestNG. This class has all the test coverage for markdown to HTML converter
- The class is configured as part of maven pom.xml
- Resources folder has files related to input and output test data


### Steps to run the project:
- To run the project use
  - `mvn clean install exec:java` in the terminal 
  - For sample input file path use: `src/main/resources/data.txt` or `src/main/resources/data2.txt`
  - Once the input file path is given, the output html file path is displayed in the terminal. Copy the html path and paste it in the browser to view the converted html page. Output path of the html is going be in this form: `file:///**/**/**/MarkDownApp/src/main/resources/output.html`
- Tests inside the TestFormatter class can be run individually or as a group using supported IDE or by command line.
