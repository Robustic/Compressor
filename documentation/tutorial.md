# Tutorial

## Maven command line commands

### Create JavaDoc documentation

`mvn javadoc:javadoc`

JavaDoc documentation is generated to the folder:

`/Compressor/target/site/apidocs/overview-summary.html`

### Run JUnit tests and generate Jacoco test report

`mvn test`

Jacoco report is generated to the folder:

`/Compressor/target/site/jacoco/index.html`

### Generate Checkstyle report

`mvn checkstyle:checkstyle`

Checkstyle report is generated to the folder:

`/Compressor/target/site/checkstyle.html`

### Suoritettavan jar tiedoston generointi

`mvn package`

Jar ohjelma voidaan suorittaa komennolla 

`java -jar Compressor.jar`

siinä kansiossa, missä jar-paketti sijaitsee.
