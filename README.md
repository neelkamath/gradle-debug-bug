# Gradle Debug Bug

This project demonstrates a bug in Gradle which causes it to be unable to debug Java 9+ projects.

From the [docs](https://docs.gradle.org/current/userguide/build_environment.html):

> **`org.gradle.debug=(true,false)`**
> 
> When set to `true`, Gradle will run the build with remote debugging enabled, listening on port 5005. Note that this is the equivalent of adding `-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005` to the JVM command line and will suspend the virtual machine until a debugger is attached. Default is `false`.

Gradle allows for remote debugging by running `gradle --debug-jvm`. The `--debug-jvm` flag instructs Gradle to supply the JVM with `-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005`. From Java 9 onwards, the argument is supposed to be `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005` ([reference](https://dev-aux.com/java/java-local-and-remote-jvm-debugging-jdk-8-and-later)). This is why remote debugging Java 9+ projects with Gradle's `--debug-jvm` doesn't work.

## Installation

Install [Docker](https://hub.docker.com/search/?type=edition&offering=community) so that we can easily simulate the required environments.

## Usage

1. We'll first run the Java 9+ project to see that the debugger doesn't work.
    1. [Start the debugger](debug.md).
    1. It will fail with the error `java.io.IOException: handshake failed - connection prematurally closed`.
1. Now we'll modify the project to show that it works in Java 8.
    1. Change line 10 of `build.gradle.kts` from `sourceCompatibility = JavaVersion.VERSION_13 ` to `sourceCompatibility = JavaVersion.VERSION_1_8`  
    1. Change line 4 of `docker-compose.yml` from `image: gradle:6.4.0-jdk14` to `image: gradle:6.4.0-jdk8`.
    1. [Start the debugger](debug.md).
    1. The debugging session starts successfully.
    
## License

This project is under the [MIT License](LICENSE).