plugins {
	java
}

repositories {
	maven("https://maven.aliyun.com/nexus/content/groups/public/")
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("ch.qos.logback:logback-classic:1.2.3")

	testImplementation("junit:junit:4.12")
}
