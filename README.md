# cayenneDI with javaModules and jlink
Demo Cayenne Dependency Injection with java 9+ modules and jlink packager.

# Prerequisites
* Java 1.8 or newer.
* Apache Maven.

# Build the Demo
Here is how to build it:
```
git clone **************************
cd *********************************
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
open module someModuleName {
...
}
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