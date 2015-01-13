# LinkCutter.
=================
## Features of LinkCutter:
* convert long links to short links (through rest service)
* convert short links to long links (through rest service)

## Technologies of LinkCutter:
1. guice as DI
2. hibernate + hsqldb as storage container
3. jersey as REST framework
4. junit + mockito + atunit (I don't find this lib in maven repository, so a put it in libs folder) as test framework

## How to run:
1. from IDE - run EmulationServer
2. go to http://localhost:8080/
or
1. mvn clean install
2. mvn tomcat6:run
3. go to http://localhost:8080/




