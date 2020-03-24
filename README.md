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
[INFO]  -> module: cayenneDI ( /cayenneDI_modules/cayenne-di/target/cayenne-di-1.0-SNAPSHOT.jar )
[INFO]  -> module: mainModule ( /cayenneDI_modules/mainModule/target/mainModule-1.0-SNAPSHOT.jar )
[INFO]  -> module: firstModule ( /cayenneDI_modules/firstModule/target/firstModule-1.0-SNAPSHOT.jar )
[INFO]  -> module: secondModule ( /cayenneDI_modules/secondModule/target/secondModule-1.0-SNAPSHOT.jar )
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
*path to bin folder from archive*/java --module mainModule/org.example.apache.cayenne.Main
```
In output you can see 
```
First Module work!!!
Second Module work!!!
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
module moduleName {
    requires otherModule;

    provides otherModule.org.example.ModuleProvider with moduleName.ModuleProvider;
}
```
```
module otherModule {
...
    uses org.example.ModuleProvider;
}
```
* If you get next error:
```
java.lang.reflect.InaccessibleObjectException: Unable to make public org.example.SomeClass() accessible: module someModule does not "exports org.example" to module otherModule
```
You need to 

- open this module to other module(s) by using:
```
 "open" keyword in module-info file
```
or

- use command line argument 
```
--add-opens <module>/<package>=<target-module>(,<target-module>)*
```
For example 
```
--add-opens java.management/sun.management=ALL-UNNAMED
```