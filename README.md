# spring-boot-autoremote
[![Build Status](https://travis-ci.org/martinfoersterling/spring-boot-autoremote.svg?branch=master)](https://travis-ci.org/martinfoersterling/spring-boot-autoremote) [![codecov](https://codecov.io/gh/martinfoersterling/spring-boot-autoremote/branch/master/graph/badge.svg)](https://codecov.io/gh/martinfoersterling/spring-boot-autoremote) [![](https://jitpack.io/v/martinfoersterling/spring-boot-autoremote.svg)](https://jitpack.io/#martinfoersterling/spring-boot-autoremote) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Dependency Status](https://www.versioneye.com/user/projects/5a4782490fb24f00107a1de9/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5a4782490fb24f00107a1de9)

Spring Boot starter for automatic configuration of HTTP Invoker in server and client

## What it does

This project contains two spring-boot starters to simplify the use of Spring HTTP invoker.

### Server-side (expose services)

`spring-boot-autoremote-server-starter` consists of a `BeanDefinitionRegistryPostProcessor` that adds `HttpInvokerServiceExporter`s for each service annotated with `ExposedService`.

This _exposes_ the services on the server as endpoints, so that any client can access them.

You need to add

```xml
<dependency>
    <groupId>com.github.martinfoersterling.spring-boot-autoremote</groupId>
    <artifactId>spring-boot-autoremote-server-starter</artifactId>
    <version>1.1</version>
</dependency>
```

to your pom.xml and define a bean derived from `ExposedServiceConfiguration` to

`ExposedServiceConfiguration` also offers a way to customize the `RemoteInvocationExecutor` to be used. Per default, a simple executor will be used that transforms all exceptions into `RuntimeException`s to avoid `ClassNotFoundException`s in the client.

The `ExceptionHandler` is also configurable in `ExposedServiceConfiguration`.

### Client-side (consume services)

`spring-boot-autoremote-client-starter` also consists of a `BeanDefinitionRegistryPostProcessor`, here adding `HttpInvokerProxyFactoryBean` instances for a number of service interfaces.

This _consumes_ the service endpoints on the server via a proxy bean, so that the client can call service methods as if they were local.

You need to add

```xml
<dependency>
    <groupId>com.github.martinfoersterling.spring-boot-autoremote</groupId>
    <artifactId>spring-boot-autoremote-client-starter</artifactId>
    <version>1.1</version>
</dependency>
```

to your pom.xml and define a bean derived from `ConsumeServiceConfiguration` listing a number of service interfaces and configuring the URL of the server.

### Dependencies

In order for your build to find the JARs, you need to add [jitpack](https://jitpack.io/) as repository.

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

## Example

My project [spring-boot](https://github.com/martinfoersterling/spring-boot) uses this library.
