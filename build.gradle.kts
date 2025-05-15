import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.sonarqube") version "5.0.0.4638"
    id("org.springframework.boot") version "3.3.0"
    id("de.undercouch.download") version "5.6.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("com.github.ben-manes.versions") version "0.51.0"
//    id("io.kotest") version "0.4.11"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    jacoco
}

extra["log4j2.version"] = "2.17.1"
extra["newrelicVersion"] = "7.5.0"

group = "com.talkdesk.hello"
java.sourceCompatibility = JavaVersion.VERSION_21

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.sonarqube")

    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://hub.talkdeskapp.com:8443/repository/talkdesk-maven/") }
    }

    sonarqube {
        properties {
            property("sonar.host.url", System.getenv("SONAR_HOST_URL"))
            property("sonar.login", System.getenv("SONAR_AUTH_TOKEN"))
            property("sonar.externalIssuesReportPaths", "./horusec_analysis.json")
            property("sonar.projectKey", System.getenv("APP_NAME"))
            property("sonar.projectName", System.getenv("APP_NAME"))
            property("sonar.branch.name", System.getenv("GIT_BRANCH"))
            property("sonar.projectVersion", System.getenv("RELEASE_TAG"))
        }
    }
}

dependencies {

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Talkdesk Starter
    implementation("com.talkdesk:talkdesk-spring-boot-starter:5.0.0")
    implementation("com.talkdesk:talkdesk-spring-boot-starter-web:5.0.0")

    implementation("io.micrometer:micrometer-bom:1.12.3")
    implementation("io.micrometer:micrometer-tracing:1.2.3")
    implementation("io.micrometer:micrometer-tracing-bridge-otel:1.2.3")
    implementation("io.opentelemetry:opentelemetry-api:1.31.0")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.0")
    testImplementation("io.kotest:kotest-assertions-core:5.9.0")
    testImplementation("io.kotest:kotest-property:5.9.0")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("io.kotest:kotest-extensions-junitxml:5.9.0")
    testImplementation("io.kotest:kotest-extensions-htmlreporter:5.9.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
    }
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.apache.logging.log4j") {
            useVersion("2.23.1")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        showStandardStreams = true
    }
    reports {
        html.required.set(false)
        junitXml.required.set(false)
    }
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required = false
        html.required = true
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
    classDirectories.setFrom(
        files(
            classDirectories.files.map {
                fileTree(it) {
                    setExcludes(
                        listOf(
                            "**/com/talkdesk/janus/Application*",
                        ),
                    )
                }
            },
        ),
    )

    doLast {
        File("build").walkTopDown().forEach { println(it) }
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
