# 11. Database in Spring Boot - Beginner's Guide

## 🎯 What is This Project?

Ye project sikhata hai **Spring Boot + MySQL database ko connect kaise karte hain**.

---

## 📋 Simple Setup

### 1. Database Create Karo
```sql
CREATE DATABASE springbootdb;
```

### 2. Dependencies Add Karo (pom.xml)
```xml
<!-- Web Server -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Database Connection -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<!-- MySQL Driver -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

### 3. Database Credentials Dalo (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb
spring.datasource.username=root
spring.datasource.password=171712
```

### 4. Run Karo
```bash
mvn spring-boot:run
```

---

## 🏗️ Project Structure - 5 Files Samjhne Hain

```
1. Main.java          ← Application start hota hai yahan
2. Category.java      ← Data structure (Entity)
3. CategoryDao.java   ← Database operations
4. AppConfig.java     ← Database configuration
5. application.properties ← Database login info
```

---

## 💡 Key Concepts (Interview Mein Puche Jayenge)

### Q1: Spring Boot kya hota hai?
**A:** Framework jisseh web applications banana aasan ho jaata hai. Automatically configuration karti hai.

### Q2: JDBC Template kya hota hai?
**A:** Database operations aasan karne ke liye Spring provide karta hai. Manual queries likha nahi padta.

### Q3: @Autowired annotation kya karta hai?
**A:** Spring automatically objects inject karta hai. Manual se `new` likhne se bachta hai.

**Example:**
```java
@Autowired
JdbcTemplate jt;  // Automatically inject hota hai
```

### Q4: @PostConstruct kya hota hai?
**A:** Ye method application start hone ke turant baad automatically run hota hai.

**Example:**
```java
@PostConstruct
public void init() {
    // Table create karne ke liye perfect
}
```

### Q5: CategoryDao kya hai?
**A:** Database se data leane aur save karne ke liye class. CRUD operations yahan hote hain.

---

## 📝 Main 5 Database Operations

### 1️⃣ CREATE (Insert) - Data Save Karna

```java
public Category set(Category c) {
    String q = "insert into category (title, description) values(?, ?)";
    jt.update(q, c.getTitle(), c.getDescription());
    return c;
}
```

**How to use:**
```java
Category c = new Category();
c.setTitle("Electronics");
c.setDescription("Electronic items");
dao.set(c);  // Database mein save hoga
```

---

### 2️⃣ READ - Data Lena

#### Single Record Lena:
```java
public Category get(int id) {
    String q = "select * from category where id = ?";
    return jt.queryForObject(q, (rs, rowNum) -> {
        Category c = new Category();
        c.setId(rs.getInt("id"));
        c.setTitle(rs.getString("title"));
        c.setDescription(rs.getString("description"));
        return c;
    }, id);
}
```

#### Sab Records Lena:
```java
public List<Category> getAll() {
    String q = "select * from category";
    return jt.query(q, (rs, rowNum) -> {
        Category c = new Category();
        c.setId(rs.getInt("id"));
        c.setTitle(rs.getString("title"));
        c.setDescription(rs.getString("description"));
        return c;
    });
}
```

---

### 3️⃣ UPDATE - Data Change Karna

```java
public Category update(int id, Category nw) {
    String q = "update category set title=?, description=? where id=?";
    jt.update(q, nw.getTitle(), nw.getDescription(), id);
    return nw;
}
```

---

### 4️⃣ DELETE - Data Remove Karna

```java
public void delete(int id) {
    String q = "delete from category where id = ?";
    jt.update(q, id);
}
```

---

### 5️⃣ CREATE TABLE - Table Banaana

```java
@PostConstruct
public void init() {
    String q = "create table if not exists category(" +
               "id int primary key auto_increment, " +
               "title varchar(100), " +
               "description varchar(100))";
    jt.execute(q);
}
```

---

## ❓ Common Questions & Answers

### Q: Kya ho agar database exist nahi karta?
**A:** Error aega `Unknown database 'springbootdb'`. Database manually create karna padega.

```sql
CREATE DATABASE springbootdb;
```

### Q: Kya application turant band kyu hoti thi?
**A:** `spring-boot-starter-web` dependency nahi thi. Web server (Tomcat) nahi tha. Ab add kar diya.

### Q: id (primary key) ko insert karte time pass nahi karte?
**A:** Nahi, kyunki `auto_increment` hai. Database automatically unique ID assign karega.

### Q: `?` symbols kya hote hain SQL mein?
**A:** Placeholders hote hain. Security se data insert hota hai (SQL Injection se bachne ke liye).

```java
String q = "insert into category (title) values(?)";
jt.update(q, c.getTitle());  // c.getTitle() ki jagah ? fill hota hai
```

### Q: Exception handling karna chahiye?
**A:** Haan! Production mein try-catch use karna chahiye:

```java
try {
    jt.update(q, params);
} catch (DataAccessException e) {
    System.out.println("Database error: " + e.getMessage());
}
```

---

## 🔧 Important Classes Explained

### Category.java (Entity)
```java
public class Category {
    private int id;
    private String title;
    private String description;
    
    // Getters aur Setters
}
```
**Purpose:** Database table ke columns ko Java objects mein represent karna.

---

### CategoryDao.java (DAO = Data Access Object)
```java
@Component
@Repository
public class CategoryDao {
    @Autowired
    JdbcTemplate jt;
    
    // CRUD operations yahan likhe hote hain
}
```
**Purpose:** Database operations ke liye dedicated class.

---

### AppConfig.java (Configuration)
```java
@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        // Database connection setup
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
```
**Purpose:** JdbcTemplate ko Spring bean banana aur inject karna.

---

### Main.java (Entry Point)
```java
@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```
**Purpose:** Application start point.

---

## 📊 Database Table Structure

```
TABLE: category
┌────┬──────────────┬──────────────────────┐
│ id │    title     │    description       │
├────┼──────────────┼──────────────────────┤
│ 1  │ Electronics  │ Electronic items     │
│ 2  │ Books        │ All types of books   │
│ 3  │ Clothing     │ Men & Women clothes  │
└────┴──────────────┴──────────────────────┘
```

---

## 🚀 Annotations Samjho

| Annotation | Purpose | Example |
|-----------|---------|---------|
| `@SpringBootApplication` | Application start point | `public class Main` |
| `@Component` | Spring bean banao | `public class CategoryDao` |
| `@Repository` | Data layer ke liye | `public class CategoryDao` |
| `@Autowired` | Object automatically inject | `@Autowired JdbcTemplate jt;` |
| `@PostConstruct` | Startup mein run | `public void init()` |
| `@Configuration` | Configuration class | `public class AppConfig` |
| `@Bean` | Object create karo | `public JdbcTemplate...()` |

---

## 📦 JdbcTemplate Methods

```java
jt.execute(query)           // DDL queries (CREATE, DROP)
jt.update(query, params)    // DML queries (INSERT, UPDATE, DELETE)
jt.queryForObject(...)      // Single row get karna
jt.query(...)               // Multiple rows get karna
```

---

## ✅ Testing Checklist

```
☐ MySQL service running hai?
☐ Database springbootdb created hai?
☐ application.properties mein credentials sahi hain?
☐ pom.xml mein sab dependencies add hain?
☐ Compilation errors nahi hain?
☐ Application port 8080 par run ho raha hai?
```

---

## 🎓 Interview Tips

**Q: Spring Boot ke fayde?**
- Configuration aasan hai
- Web server (Tomcat) built-in hai
- Development fast hota hai

**Q: JDBC vs JdbcTemplate?**
- JDBC: Manual queries likne padti hain
- JdbcTemplate: Framework handle karta hai

**Q: @Autowired se kya benefit?**
- Manual `new` keyword nahi chahiye
- Dependency Injection hota hai
- Code clean rahta hai

**Q: Database operations mein error handling important?**
- Haan, real-world mein zaroori hai
- Try-catch use karo
- Proper error messages dedo

---

## 📚 Code Example - Complete Flow

```java
// 1. Object banao
Category c = new Category();
c.setTitle("Laptops");
c.setDescription("Portable computers");

// 2. Database mein save karo
dao.set(c);

// 3. Database se lao
Category retrieved = dao.get(1);

// 4. Update karo
retrieved.setTitle("Updated Laptops");
dao.update(1, retrieved);

// 5. Sab dekho
List<Category> all = dao.getAll();

// 6. Delete karo
dao.delete(1);
```

---

## 🎯 Key Takeaways

1. **Spring Boot = Easy Web Development**
2. **@Autowired = Automatic Object Injection**
3. **JdbcTemplate = Easy Database Operations**
4. **DAO Pattern = Clean Code Organization**
5. **@PostConstruct = Initialization Management**

---

## 🔗 Related Concepts to Learn

- Spring Data JPA (isse JDBC likha nahi padta)
- REST APIs with Spring
- Exception Handling
- Transaction Management
- Connection Pooling

---

**Last Updated:** March 4, 2026  
**Difficulty Level:** ⭐⭐ (Beginner to Intermediate)  
**Time to Learn:** 2-3 hours
