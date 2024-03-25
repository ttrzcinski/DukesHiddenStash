# DukesHiddenStash
Set of all java utils developed in other projects.

How 'green-field' is the project:
- Uses Java 15 locally and Java 16 in docker
- Uses Maven
- Uses JUnit 5
- Uses SureFire Reports
- Uses Pitest for mutation testing
- Uses in docker openjdk:16-alpine

WIP:
- Provide choose-menu in console.
  - Add fast constructor of(HashMsp<String, String>) - after choosing option it returns corresponding value
- Create AAA Unit Test from method's name, params and result
- Provide call to process in runtime with or without output.
- Contains Faker classes for test data generation.

Features:

- Add BlockChain as a Collection/Structure type with Generics T
- Provide a template for FaaS functions, REST API and WebSockets Thin Client-Server
- Provide some basic security with external Oauth2 or JWT key
- Slice those functionalities into small modules: separate arguments on entry,
  separate AAA Class generator, separate method mapper, separate JSON generator
- In the name of annotation usage as this code is already above JDK9 add annotations to: LimitValueWithGap,
  ToJSON serialiser,
- Adds util classes to limit boiler-plate of Java code.
- Processes passed parameters in more consistent way.
- Generate package-info.java in every package from first line of JavaDoc.
- Generate all combinations of variants based on a passed parameter.
- Generate all combinations of variants based on a passed parameter's set.
- Cover every class with given pattern of JavaDoc.
- Mechanism of zip'n'send to multi-stJWTream-output.
- Use system's notifications - check, how their API operates.
- Provide mock recorder of REST JSON calls to url:port.
