plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"

}

allprojects {
    repositories {
        // 요게 없으면 Cannot resolve external dependency org.jetbrains.kotlin:kotlin-compiler-embeddable:1.3.21 because no repositories are defined. 발생
        mavenCentral() // mavenCentral 인건 상관없네.
    }
}

subprojects {
    group = "what.the.jpa.book"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}
