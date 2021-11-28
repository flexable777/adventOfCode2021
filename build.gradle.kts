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
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}