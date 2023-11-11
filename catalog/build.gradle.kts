apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")


    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.2.0")
    runtimeOnly("com.h2database:h2:2.2.224")

    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.persistence:jakarta.persistence-api")
    kapt("jakarta.annotation:jakarta.annotation-api")
}