# CustomerSystemSpringBootRepo
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MstCustomerRepository extends JpaRepository<MstCustomer, Long> {

    @Query("SELECT c FROM MstCustomer c WHERE c.deleteYmd IS NULL " +
           "AND (:name IS NULL OR LOWER(c.customerName) LIKE LOWER(CONCAT('%', :name, '%'))) " +
           "AND (:sex IS NULL OR c.sex = :sex) " +
           "AND (:birthdayFrom IS NULL OR c.birthday >= :birthdayFrom) " +
           "AND (:birthdayTo IS NULL OR c.birthday <= :birthdayTo) " +
           "ORDER BY c.customerId")
    Page<MstCustomer> findCustomers(
            @Param("name") String name,
            @Param("sex") Integer sex,
            @Param("birthdayFrom") LocalDate birthdayFrom,
            @Param("birthdayTo") LocalDate birthdayTo,
            Pageable pageable
    );
}






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MstCustomerService {

    @Autowired
    private MstCustomerRepository customerRepository;

    public Page<MstCustomer> findCustomers(String name, Integer sex, LocalDate birthdayFrom, LocalDate birthdayTo, Pageable pageable) {
        return customerRepository.findCustomers(name, sex, birthdayFrom, birthdayTo, pageable);
    }
}




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @Autowired
    private MstCustomerService customerService;

    @GetMapping("/search")
    public String searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer sex,
            @RequestParam(required = false) LocalDate birthdayFrom,
            @RequestParam(required = false) LocalDate birthdayTo,
            Pageable pageable,
            Model model
    ) {
        Page<MstCustomer> customers = customerService.findCustomers(name, sex, birthdayFrom, birthdayTo, pageable);
        model.addAttribute("customers", customers);
        return "customerList";
    }
}




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h2>Customer List</h2>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Sex</th>
                <th>Birthday</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customers.content}">
                <tr>
                    <td>${customer.customerId}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.sex == 0 ? 'Male' : 'Female'}</td>
                    <td>${customer.birthday}</td>
                    <td>${customer.address}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div>
        <c:if test="${customers.totalPages > 1}">
            <p>Page ${customers.number + 1} of ${customers.totalPages}</p>
        </c:if>
    </div>

    <div>
        <a href="<c:url value='/search'/>?page=1">First Page</a>
        <c:if test="${!customers.first}">
            <a href="<c:url value='/search'/>?page=${customers.previousPageable.pageNumber + 1}">Previous Page</a>
        </c:if>
        <c:if test="${!customers.last}">
            <a href="<c:url value='/search'/>?page=${customers.nextPageable.pageNumber + 1}">Next Page</a>
        </c:if>
        <a href="<c:url value='/search'/>?page=${customers.totalPages}">Last Page</a>
    </div>
</body>
</html>

























$.ajax({
    type: "POST",
    url: "/login",
    data: JSON.stringify({ userId: "yourUserId", password: "yourPassword" }),
    contentType: "application/json",
    success: function(response) {
        // Xử lý khi đăng nhập thành công
        console.log(response);
        // Chuyển hướng trang hoặc thực hiện các hành động khác
    },
    error: function(xhr, status, error) {
        // Xử lý khi đăng nhập không thành công
        var errorMessage = JSON.parse(xhr.responseText);
        console.log(errorMessage);

        // Hiển thị thông báo lỗi trên thẻ div có id là "errorContainer"
        $("#errorContainer").text(errorMessage);

        // Hoặc nếu bạn muốn thêm class để tùy chỉnh giao diện
        // $("#errorContainer").addClass("error-style");
        
        // Hoặc có thể hiển thị thông báo lỗi trong một cửa sổ thông báo
        // alert(errorMessage);
        
        // Thực hiện các hành động khác tùy vào yêu cầu của bạn
    }
});



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Kiểm tra nếu userId hoặc password rỗng
        if (loginRequest.getUserId().isEmpty()) {
            return ResponseEntity.badRequest().body("Chưa nhập User ID");
        }

        if (loginRequest.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Chưa nhập Password");
        }

        // Thực hiện kiểm tra đăng nhập
        boolean loginSuccess = // logic kiểm tra đăng nhập;

        if (loginSuccess) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(401).body("Đăng nhập không thành công");
        }
    }
}



@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    // Thực hiện xác thực người dùng
    // ...

    if (isUserAuthenticated) {
        // Đăng nhập thành công
        return ResponseEntity.ok("success");
    } else {
        // Đăng nhập thất bại
        return ResponseEntity.badRequest().build();
    }
}





package com.example.CustomerSystemSpringboot.entitys;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// Entity annotation indicates that this class is a JPA entity, representing a database table
@Entity
// Table annotation specifies the name of the database table corresponding to this entity
@Table(name = "MSTUSER")
public class MstUser {
    // Id annotation indicates the primary key of the entity
    @Id
    // Column annotation specifies the mapping to the corresponding database column
    @Column(name = "PSN_CD")
    // GeneratedValue annotation indicates that the value for this field will be generated by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int psnCd;

    @Column(name = "USERID", length = 8)
    private String userId;

    @Column(name = "PASSWORD", length = 8)
    private String password;

    @Column(name = "USERNAME", length = 40)
    private String username;

    @Column(name = "DELETE_YMD")
    // Temporal annotation specifies the type of temporal data for this field
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deleteYmd;

    @Column(name = "INSERT_YMD", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp insertYmd;

    @Column(name = "INSERT_PSN_CD", columnDefinition = "numeric(5,0)")
    private BigDecimal insertPsnCd;

    @Column(name = "UPDATE_YMD", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateYmd;

    @Column(name = "UPDATE_PSN_CD", columnDefinition = "numeric(5,0)")
    private BigDecimal updatePsnCd;

    // Default constructor required by JPA
    public MstUser() {
        super();
    }

    // Parameterized constructor for creating instances with initial values
    public MstUser(int psnCd, String userId, String password, String username, Timestamp deleteYmd, Timestamp insertYmd,
            BigDecimal insertPsnCd, Timestamp updateYmd, BigDecimal updatePsnCd) {
        super();
        this.psnCd = psnCd;
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.deleteYmd = deleteYmd;
        this.insertYmd = insertYmd;
        this.insertPsnCd = insertPsnCd;
        this.updateYmd = updateYmd;
        this.updatePsnCd = updatePsnCd;
    }

    // Getter and Setter methods for accessing and modifying private fields

    // ...
}






// ... (existing code above)

    // Getter method for retrieving the value of psnCd
    public int getPsnCd() {
        return psnCd;
    }

    // Setter method for updating the value of psnCd
    public void setPsnCd(int psnCd) {
        this.psnCd = psnCd;
    }

    // Getter method for retrieving the value of userId
    public String getUserId() {
        return userId;
    }

    // Setter method for updating the value of userId
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter method for retrieving the value of password
    public String getPassword() {
        return password;
    }

    // Setter method for updating the value of password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter method for retrieving the value of username
    public String getUsername() {
        return username;
    }

    // Setter method for updating the value of username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for retrieving the value of deleteYmd
    public Timestamp getDeleteYmd() {
        return deleteYmd;
    }

    // Setter method for updating the value of deleteYmd
    public void setDeleteYmd(Timestamp deleteYmd) {
        this.deleteYmd = deleteYmd;
    }

    // Getter method for retrieving the value of insertYmd
    public Timestamp getInsertYmd() {
        return insertYmd;
    }

    // Setter method for updating the value of insertYmd
    public void setInsertYmd(Timestamp insertYmd) {
        this.insertYmd = insertYmd;
    }

    // Getter method for retrieving the value of insertPsnCd
    public BigDecimal getInsertPsnCd() {
        return insertPsnCd;
    }

    // Setter method for updating the value of insertPsnCd
    public void setInsertPsnCd(BigDecimal insertPsnCd) {
        this.insertPsnCd = insertPsnCd;
    }

    // Getter method for retrieving the value of updateYmd
    public Timestamp getUpdateYmd() {
        return updateYmd;
    }

    // Setter method for updating the value of updateYmd
    public void setUpdateYmd(Timestamp updateYmd) {
        this.updateYmd = updateYmd;
    }

    // Getter method for retrieving the value of updatePsnCd
    public BigDecimal getUpdatePsnCd() {
        return updatePsnCd;
    }

    // Setter method for updating the value of updatePsnCd
    public void setUpdatePsnCd(BigDecimal updatePsnCd) {
        this.updatePsnCd = updatePsnCd;
    }

// ... (existing code below)







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
