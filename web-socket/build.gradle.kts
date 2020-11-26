plugins {
    java
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
}

repositories {
    maven("https://maven.aliyun.com/nexus/content/groups/public/")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("javax.websocket:javax.websocket-api:1.1")
    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars:jquery:3.1.1-1")
    implementation("org.webjars:bootstrap:3.3.7")
    implementation("org.webjars:sockjs-client:1.0.2")
    implementation("org.webjars:stomp-websocket:2.3.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
