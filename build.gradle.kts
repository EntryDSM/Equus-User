import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    id("org.jlleitschuh.gradle.ktlint") version PluginVersions.KLINT_VERSION
    kotlin("jvm") version PluginVersions.JVM_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
}

dependencyManagement {
    imports {
        mavenBom(Dependencies.SPRING_CLOUD)
    }
}

group = "hs.kr.equus"
version = "0.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {

    // Database
    implementation(Dependencies.SPRING_DATA_JPA)
    implementation(Dependencies.SPRING_REDIS)
    runtimeOnly(Dependencies.MYSQL_CONNECTOR)

    // Web
    implementation(Dependencies.SPRING_WEB)

    // Security
    implementation(Dependencies.SPRING_SECURITY)

    // Kotlin
    implementation(Dependencies.JACKSON)
    implementation(Dependencies.KOTLIN_REFLECT)

    // Test
    testImplementation(Dependencies.SPRING_TEST)

    // Logging
    implementation(Dependencies.SENTRY)

    // Valid
    implementation(Dependencies.SPRING_VALIDATION)

    // Gson
    implementation(Dependencies.JSON)

    // OkCert
    implementation(files("$projectDir/${Dependencies.OKCERT_PATH}"))

    // Jwt
    implementation(Dependencies.JWT)

    // Kafka
    implementation(Dependencies.KAFKA)

    // Spring Config
    implementation(Dependencies.CLOUD_CONFIG)

    // Actuator
    implementation(Dependencies.ACTUATOR)

    // Open API
    implementation(Dependencies.OPEN_API)

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
