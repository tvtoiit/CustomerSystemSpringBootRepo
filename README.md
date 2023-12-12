# CustomerSystemSpringBootRepo

<dependencies>
    <!-- Spring Boot Starter Web (bao gồm Spring MVC và Tomcat) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Tomcat (để sử dụng Tomcat như server) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>

    <!-- Thư viện javax.servlet:jstl -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
    </dependency>
</dependencies>








<!-- Hibernate ORM -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.5.6.Final</version> <!-- Chọn phiên bản mới nhất -->
</dependency>

<!-- Hibernate EntityManager -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.5.6.Final</version>
</dependency>

<!-- Hibernate Validator (Nếu bạn muốn sử dụng validation) -->
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>7.0.2.Final</version>
</dependency>



# DataSource settings: set here configurations for the database connection
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password

# Specify the DBMS
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
