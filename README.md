# Fika
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.speedment.fika/fika/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.speedment.fika/fika)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.speedment.fika/fika/badge.svg)](http://www.javadoc.io/doc/com.speedment.fika/fika)
"Fika" is a Swedish word that means "Coffee with something to it", and that is exactly what this project is about - a collection of general purpose modules for various Java projects.

## Included modules
Each module in this project is designed to work as an independant component. Below is a list of the included modules with links to their individual project pages:

### [RestUp](https://github.com/speedment/fika/wiki/RestUp)
A simple API for connecting to an existing REST API:s from Java.
```xml
<dependency>
    <groupId>com.speedment.fika</groupId>
    <artifactId>restup</artifactId>
    <version>1.0.1</version>
</dependency>
```

### [Reactor](https://github.com/speedment/fika/wiki/Reactor)
An extension to [Speedment](https://github.com/speedment/speedment) that polls the database at a regular interval to produce a materialized object view (MOV) of a particular table.
```xml
<dependency>
    <groupId>com.speedment.fika</groupId>
    <artifactId>reactor</artifactId>
    <version>1.0.0</version>
</dependency>
```

## License
All the modules in this project are available under [the Apache 2 license](http://www.apache.org/licenses/LICENSE-2.0). 
Attribution should be done to Speedment, Inc.
