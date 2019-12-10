plugins {
	java
}

repositories {
	maven("maven.aliyun.com/nexus/content/groups/public/")
	mavenCentral()
	jcenter()
}

dependencies {
	testImplementation("junit:junit:4.12")
}
