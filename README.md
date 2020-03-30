# cayenneDI with javaModules and jlink
Demo Cayenne Dependency Injection with java 9+ modules and jlink packager.

# Prerequisites
* Java 1.8 or newer.
* Apache Maven.

# Build the Demo
Here is how to build it:
```
git clone https://github.com/alex-byi/cayenne-di-java-modules-jlink.git
cd cayenne-di-java-modules-jlink/
mvn package
```
In command line you can see:
```
...
[INFO] The following dependencies will be linked into the runtime image:
[INFO]  -> module: org.example.apache.cayenne.module.main ( /cayenneDI_modules/main-module/target/main-module-1.0-SNAPSHOT.jar )
[INFO]  -> module: org.apache.cayenne.di ( /cayenneDI_modules/cayenne-di/target/cayenne-di-1.0-SNAPSHOT.jar )
[INFO]  -> module: org.example.apache.cayenne.module.first ( /cayenneDI_modules/first-module/target/first-module-1.0-SNAPSHOT.jar )
[INFO]  -> module: org.example.apache.cayenne.module.second ( /cayenneDI_modules/second-module/target/second-module-1.0-SNAPSHOT.jar )
[INFO] Building zip: /cayenneDI_modules/jlink/target/jlink-1.0-SNAPSHOT.zip


...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
...
```
After that  the maven-jlink-plugin package all java modules, all required dependencies
into zip archive. 
After unpacking archive you can run the Main class of the application using the command:
```
*path to bin folder from archive*/java --module org.example.apache.cayenne.module.main/org.example.apache.cayenne.Main
```
In output you can see 
```
First module message
Second module message
```

# Important
* If your project has circular dependencies you cannot build it using Java modules and jlink
* If any module uses tests that use Java Reflection to access the packages of another module,
 then you will have to make the module completely open by adding the "open" keyword to the module-info file. e.g.:
 ```
open module org.example.some.module {
...
}
```
or configure the maven plugin to open a specific package for another module e.g.:
```
<plugin>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>${surefire-version}</version>
    <configuration>
    <argLine>--add-opens org.example.some.module/org.example.module.testpackage=org.example.another.module</argLine>
    </configuration>
</plugin>
```
* Using the interfaces of one module to implement them in another module do not forget to use
"provides … with" and "uses". e.g.:
```
module org.example.module.first {
    requires org.example.module.other;

    provides org.example.module.other.package.ModuleProvider with org.example.module.first.package.ModuleProvider;
}
```
```
module org.example.module.other {
...
    uses org.example.module.other.package.ModuleProvider;
}
```
* If you get next error:
```
java.lang.reflect.InaccessibleObjectException: Unable to make public org.example.SomeClass() accessible: module org.example.module does not "exports package.example" to module org.example.module.other
```
You need to 

- open this module to other module(s) by using:
```
 "open" keyword in module-info file
```
or

- use command line argument 
```
java --add-opens <module>/<package>=<target-module>(,<target-module>)*
```
For example 
```
--add-opens java.management/sun.management=ALL-UNNAMED
```
or configure maven plugin(s)
* Maven JLink Plugin cannot be used with automatic modules

* If you get next error
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-jlink-plugin:3.0.0-alpha-1:jlink (default-jlink) on project : Execution default-jlink of goal org.apache.maven.plugins:maven-jlink-plugin:3.0.0-alpha-1:jlink failed.: NullPointerException
```
You can wait for the release of the new version of the plugin, or use the snapshot version

* If you get next error
```
Error: automatic module cannot be used with jlink:
```
This means that you need to create a modular jar. file. First, you can use jdeps for create module-info.java:
```
jdeps --generate-module-info <output-location> <path-to-jar>
```
Next, you need to compile module-info.java:
```
javac module-info.java
```
Next, you need to add module-info.class to non-modular .jar:
```
jar --update --file <path-to-jar> --main-class <path.to.Main> --module-version <version> -C <DIR> <path-to-module-info.class>
```
Next, install jar into local maven repository:
```
mvn install:install-file -Dfile=<path-to-jar> -DgroupId=<group.id> -DartifactId=<artifact.id> -Dversion=<version> -Dpackaging=jar
```
Next, add dependency in pom.xml:
```
<dependency>
    <groupId>org.example</groupId>
    <artifactId>demo</artifactId>
    <version>1.0</version>
</dependency>
```
Next, add configuration in maven-compiler-plugin:
```
<configuration>
    <compilerArgs>
        <arg>--add-exports</arg> <arg>org.example/example.package=<module.that.needs.dependencies></arg>
    </compilerArgs>
</configuration>
```
And after that maven-jlink-plugin compile .zip archive with all modules include .jar

