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

//不需要在gradle配置文件中标注，直接在IDE中配置即可。
java {
    sourceCompatibility = JavaVersion.VERSION_13
    targetCompatibility = JavaVersion.VERSION_13
}

//switch和text block特性需要开启预览
//三者缺一不可
//需要在IDEA中手动配置模块的语言级别，否则IDEA会报语法错误
tasks {
    compileJava {
        options.compilerArgs.add("--enable-preview")
        options.compilerArgs.add("-Xlint:preview")
        //println(this.options.compilerArgs)
    }
    compileTestJava {
        options.compilerArgs.add("--enable-preview")
        options.compilerArgs.add("-Xlint:preview")
        //println(this.options.compilerArgs)
    }

    test {
        jvmArgs("--enable-preview")
    }
}
