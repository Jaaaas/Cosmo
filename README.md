# Cosmo

> **Java Annotation Processor that helps query creation inside java files.**

## Overview

Often having to write sql queries in java file turns out to be annoying. 
To avoid this, ORMs are used for automatic query mapping. Through "meta-languages", the query string is constructed and then executed. 

During this process even for the simplest queries you will have to wait for time creation(short time) before it is executed.
In case the queries become more complicated, most of the time they should be written directly into **sql language** for reasons of efficiency or simply because the ORM does not offer the ability to build too complex queries.

Here comes Cosmo. But Cosmo will not build the query string for you.
Cosmo is an **annotation processor** that through one or more configuration files will create at compilation time functions for your queries.

## Getting Started

### Prerequisites
In order to use Cosmo you simply need to import the **JAR** (
you can download it from here [Cosmo](https://github.com/Jaaaas/Cosmo/files/1296088/Cosmo-1.0.zip) or from the releases) into the project and you can use its features.

### Usage
Cosmo is an annotation processor. The annotation that is used is **@CosmoGen**. The CosmoGen annotation has as RetentionPolicy **SOURCE**, so it is discarded by the compiler.

```java
@Retention(RetentionPolicy.SOURCE)
public @interface CosmoGen 
{
    String basePath();
    String pkgToConfig();
    String fileName();
}
```

We can see from the above code that we have to pass 3 parameters:

* **basePath**: absolute path to the src folder. It will be used in order to create next files.
* **pkgToConfig**: package where the configuration file will be created.
* **fileName**: configuration file name.

Example:

```java
public class CosmoTest
{
    @CosmoGen
    (
        basePath="absolute/path/to/thisProject/src/",
        pkgToConfig="CosmoConfig/",
        fileName="CosmoConfig"
    )
    String cosmoConfiguration;
}
```

After doing this, simply build the project and json file ,with the given *fileName*, will be created at the specified *basePath/pkgToConfig*.

The json should be the following:

```json
{
    "toCreate": 
    [
        {
            "packageToClass": "",
            "functionName": "",
            "query": ""
        }
    ]
}
```

* **packageToClass**: relative path(**including a className.java**) starting from basePath specified before.
* **functionName**: name of the function that will be created.
* **query**: query we need.

Example:

```json
"toCreate": 
    [
        {
            "packageToClass": "CosmoQueries/SomethingQueries.java",
            "functionName": "SelectSomething",
            "query": "SELECT * FROM Something"
        }
    ]
```
Now we just need to rebuild the project and a SomethingQueries.java file will be created with the specified functions.

```java
package CosmoQueries;

public class SomethingQueries
{
    public String SelectSomething()
    {
        return "SELECT * FROM Something";
    }
}
```
You can surely write multiple queries within the same configuration file.

*In order to add next queries functions, SomethingQueries.java file will be deleted and recreated.*

We highly recommend creating different configuration files based on the entity or based on the query type.
That's why we can create:

```java
@CosmoGen
(
    basePath="absolute/path/to/thisProject/src/",
    pkgToConfig="CosmoConfig/",
    fileName="CosmoSomethingQueries"
)
String cosmoConfigSomething;

@CosmoGen
(
    basePath="absolute/path/to/thisProject/src/",
    pkgToConfig="CosmoConfig/",
    fileName="CosmoSomethingElseQueries"
)
String cosmoConfigSomethingElse;
```
In this case, two creation files will be created in which we can separate the queries by entities.

### Related project
* **[Sinfonia](https://github.com/Jaaaas/Sinfonia/)**
* **[Dinamo](https://github.com/Tignaman/Dinamo)**

### Contributors
* **[suxl89](https://github.com/suxl89)**
* **[Nazzareno Di Pietro](https://github.com/Tignaman)**
* **[fpafumi](https://github.com/fpafumi)**

### Future developments

* **Testing**
* **Maven integration**
* **New options for function creation (for example the possibility to specify annotations, exceptions in creating the method)**
* **Improves performance**
