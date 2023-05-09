plugins {
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.21"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.integration:spring-integration-mail")

    implementation("org.springframework.boot:spring-boot-starter-artemis")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
