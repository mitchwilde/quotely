import org.gradle.jvm.tasks.Jar

group = "com.quotely"
version = "1.0.0"

plugins {
    application
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
}

application {
    mainClass.set("com.quotely.Quotely")
}

tasks {
    val run by existing(JavaExec::class)

    register("runEng") {
        doFirst {
            run.configure {
                args = listOf("English")
            }
        }
        finalizedBy(run)
    }

    register("runRus") {
        doFirst {
            run.configure {
                args = listOf("Russian")
            }
        }
        finalizedBy(run)
    }
}

distributions {
    main {
        contents {
            from("README.md")
        }
    }
}

tasks.register<Jar>("uberJar") {
    archiveClassifier = "uber"
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter {
            it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to "Quotely",
            "Implementation-Version" to archiveVersion,
            "Main-Class" to "com.quotely.Quotely",
            "Class-Path" to "jackson-annotations-2.17.1.jar jackson-core-2.17.1.jar jackson-databind-2.17.1.jar"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}