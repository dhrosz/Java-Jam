/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.7.1/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    java
    eclipse
    idea
    id("org.springframework.boot") version "2.4.0"
    // org.springframework.boot
    // io.spring.dependency-management
}

apply(plugin = "io.spring.dependency-management")

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("com.google.guava:guava:29.0-jre")
    implementation("org.json:json:20201115");
    implementation("com.google.code.gson:gson:2.8.6")
}

application {
    // Define the main class for the application.
    mainClass.set("JavaJam.App")
}
