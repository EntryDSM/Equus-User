import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.hidetake.gradle.swagger.generator.GenerateSwaggerUI
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.SourceTask

plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    id("org.jlleitschuh.gradle.ktlint") version PluginVersions.KLINT_VERSION
    kotlin("jvm") version PluginVersions.JVM_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
    id("com.epages.restdocs-api-spec") version "0.17.1"
    id("org.hidetake.swagger.generator") version "2.18.2"
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

val swaggerSources by tasks.registering(SourceTask::class) {
    configure<SourceTask> {
        inputs.files(file("${project.buildDir}/api-spec/openapi3.yaml"))
    }
}

val openapi3 by tasks.registering(GenerateSwaggerUI::class) {
    configure<GenerateSwaggerUI> {
        (this as ExtensionAware).extra["servers"] = listOf(
            mapOf("url" to "http://배포중인 주소"),
            mapOf("url" to "http://localhost:8080")
        )
        (this as ExtensionAware).extra["title"] = "API 문서"
        (this as ExtensionAware).extra["description"] = "RestDocsWithSwagger Docs"
        (this as ExtensionAware).extra["version"] = "0.0.1"
        (this as ExtensionAware).extra["format"] = "yaml"
    }
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

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.epages:restdocs-api-spec-mockmvc:2.18.2")
    implementation("org.webjars:swagger-ui:4.11.1")
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

tasks.withType<GenerateSwaggerUI> {
    dependsOn(tasks.getByName("generateOpenApi3"))

    doFirst {
        val swaggerUIFile = file("*/openapi3.yaml")

        val securitySchemesContent = """
            |  securitySchemes:
            |    APIKey:
            |      type: apiKey
            |      name: Authorization
            |      in: header
            |security:
            |  - APIKey: []  # Apply the security scheme here
        """.trimMargin()

        swaggerUIFile.appendText(securitySchemesContent)
    }
}



tasks.named<BootJar>("bootJar") {
    dependsOn("generateSwaggerUISample")
    from(tasks["generateSwaggerUISample"].outputs.files) {
        into("static/docs")
    }
}
