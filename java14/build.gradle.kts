plugins {
	java
}

repositories {
    maven("https://maven.aliyun.com/nexus/content/groups/public/")
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation("junit:junit:4.13")
}

tasks {
    compileJava {
        options.compilerArgs.add("--enable-preview")
    }
    compileTestJava {
        options.compilerArgs.add("--enable-preview")
    }
    test {
        jvmArgs("--enable-preview")
    }
}
