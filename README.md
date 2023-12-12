# CustomerSystemSpringBootRepo
 <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>




import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "passWord", required = false) String passWord,
            @ModelAttribute("errorModel") Model model, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login");

        // Kiểm tra và thêm lỗi vào BindingResult
        if (userId == null || userId.isEmpty()) {
            bindingResult.rejectValue("userId", "error.userId", "Chưa nhập userId");
        }
        if (passWord == null || passWord.isEmpty()) {
            bindingResult.rejectValue("passWord", "error.passWord", "Chưa nhập password");
        }

        // Nếu có lỗi, trả về trang Login với thông báo lỗi
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorModel", model);
            return modelAndView;
        }

        // Tiếp tục xử lý nếu không có lỗi
        MstUser result = loginRepository.findByUserIdAndPasswordAndDeleteYmdIsNull(userId, passWord);

        if (result != null) {
            modelAndView.setViewName("Search");
        }
        
        return modelAndView;
    }
}















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
