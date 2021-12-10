plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    testImplementation("org.assertj:assertj-core:3.11.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}