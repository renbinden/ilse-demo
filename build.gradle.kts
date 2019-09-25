plugins {
    id("org.jetbrains.kotlin.js") version "1.3.50"
}

group = "ilse-test"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/alyphen/ilse")
        credentials {
            username = "alyphen"
            password = file("$rootDir/github_token.txt").readLines()[0]
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
    implementation(group = "uk.co.renbinden", name = "ilse", version = "1.0.0")
    testCompile(group = "junit", name = "junit", version = "4.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
}

kotlin.target.browser { }