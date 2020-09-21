# Geektrust in War


Geektrust in war is the solution of the programming problem statement "WAR" given by geektrust as a september hiring challenge.


### Building the code

Geektrust in war requires [Java](https://www.java.com/) v1.8+ and [gradle](https://gradle.org/) to run.

Run the gradle build in the project directory.

```sh
gradle build
```

### How to use

To use this java application, you must run the java jar file created in build/libs/ folder of the project structure which gets updated when you run gradle build.

The application requires an extra parameter which contains the location of input File and therefore is required to be passed as command line arguments.

To run the jar file use
```sh
java -jar geektrust.jar <input-file-location>
```

# Tests
There are currently 6 tests written for checking if the input is providing the correct output and to check if it can handle IOException when the file location is incorrect.
