plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    runtimeOnly("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")


    implementation(kotlin("stdlib-jdk8"))

}
kotlin {
    jvmToolchain(17)
}