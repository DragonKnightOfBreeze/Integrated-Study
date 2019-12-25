plugins {
	java
}

repositories {
	maven("maven.aliyun.com/nexus/content/groups/public/")
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("cglib:cglib:3.3.0")
	testImplementation("junit:junit:4.12")
}
