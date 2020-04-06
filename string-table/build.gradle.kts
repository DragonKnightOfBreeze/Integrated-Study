plugins {
    java
}

repositories {
	maven("https://maven.aliyun.com/nexus/content/groups/public/")
	mavenCentral()
	jcenter()
}

dependencies {
	testImplementation("junit:junit:4.12")
}

tasks.compileJava {
	this.options.compilerArgs.add("--add-exports=java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED")
}
