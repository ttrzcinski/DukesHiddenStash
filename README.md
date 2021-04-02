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

- Adds util classes to limit boiler-plate of Java code.
- Processes passed parameters in more consistent way.
- Generate package-info.java in every package from first line of JavaDoc.
- Generate all combinations of variants based on a passed parameter.
- Generate all combinations of variants based on a passed parameter's set.
- Cover every class with given pattern of JavaDoc.
- Mechanism of zip'n'send to multi-stream-output.
- Use system's notifications - check, how their API operates.
- Provide mock recorder of REST JSON calls to url:port.
