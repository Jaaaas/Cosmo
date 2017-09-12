# Cosmo

> **Java Annotation Processor that helps query creation inside java files.**

## Overview

Often having to write sql queries in java file turns out to be annoying. 
To avoid this, ORMs are used for automatic query mapping. Through "meta-languages", the query string is constructed and then executed. 

During this process even for the simplest queries you will have to wait for time creation(short time) before it is executed.
In case the queries become more complicated, most of the time they should be written directly into sql language for reasons of efficiency or simply because the ORM does not offer the ability to build too complex queries.

Here comes Cosmo. But Cosmo will not build the query string for you.
Cosmo is an abstract processor that through one or more configuration files will create at compilation time functions for your queries.
