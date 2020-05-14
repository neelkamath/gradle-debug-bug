plugins {
    application
    java
}

application.mainClassName = "Main"

repositories { mavenCentral() }

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}