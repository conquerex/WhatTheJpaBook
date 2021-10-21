plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"

}

allprojects {
    group = "what.the.jpa.book"
    version = "1.0-SNAPSHOT"

    repositories {
        // 요게 없으면 Cannot resolve external dependency org.jetbrains.kotlin:kotlin-compiler-embeddable:1.3.21 because no repositories are defined. 발생
        mavenCentral() // mavenCentral 인건 상관없네.
    }

    apply(plugin = "java")
    apply(plugin = "kotlin")

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.hibernate:hibernate-entitymanager:6.0.0.Alpha7")
        runtimeOnly("com.h2database:h2:1.4.199")

        //JUnit 추가
        testImplementation("org.junit.vintage:junit-vintage-engine:5.8.1") {
            exclude(group = "org.hamcrest", module = "hamcrest-core")
        }

        //querydsl 추가
        implementation("com.querydsl:querydsl-jpa:5.0.0")

        //querydsl 추가
        implementation("com.querydsl:querydsl-apt:5.0.0")

    }
}
