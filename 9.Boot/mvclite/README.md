# 9.Boot - mvclite (Detailed Guide)

`mvclite` ek beginner-to-intermediate Spring Boot demo project hai jo MVC + REST + layered architecture ko practical tareeke se samjhata hai.

Is project ka main objective hai:
- Spring Boot bootstrapping samajhna
- `@RestController` vs `@Controller` difference clear karna
- Controller → Service → Repository layering ka flow dekhna
- Config classes ko modular way me organize karna

---

## 1) Quick Snapshot

- Project Name: `mvclite`
- Group: `com.springboot.dive`
- Spring Boot Parent: `4.0.3`
- Java Version (configured): `25`
- Packaging: JAR
- Default Port: `8080` (because `application.properties` me `server.port` set nahi hai)

---

## 2) What You Learn From This Project

1. Spring Boot app ka startup lifecycle
2. REST endpoints banana (`/magic`, `/another`)
3. MVC page routes banana (`/page/about`, `/page/service`)
4. Layered login flow (`LoginController -> LoginService -> LoginRepository`)
5. Configuration classes ka use aur structure
6. Component scanning and bean registration (`pack.Student`)

---

## 3) Tech Stack & Dependencies

### Runtime
- Java `25`
- Spring Boot `4.0.3`

### Maven Dependencies (current)
- `spring-boot-starter-webmvc`
- `spring-boot-starter-webservices`

### Test Dependencies
- `spring-boot-starter-webmvc-test`
- `spring-boot-starter-webservices-test`

### Important Note (Template Rendering)
Project templates return kar raha hai, lekin `pom.xml` me explicit Thymeleaf dependency nahi dikh rahi. Agar runtime par template resolver issue aaye, ye dependency add karein:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

---

## 4) Folder Structure (with purpose)

```text
mvclite/
├─ pom.xml                              # Maven config + dependencies + Java version
├─ src/main/java/
│  ├─ springboot/
│  │  ├─ FirstBootProjectApplication.java   # Main entry point
│  │  ├─ controller/
│  │  │  ├─ HomeController.java             # REST endpoints
│  │  │  ├─ PageController.java             # MVC page routes
│  │  │  ├─ LoginController.java            # Login flow trigger
│  │  │  └─ SignupController.java           # Placeholder controller
│  │  ├─ services/
│  │  │  └─ LoginService.java               # Business logic layer
│  │  ├─ repositories/
│  │  │  └─ LoginRepository.java            # Data access placeholder
│  │  └─ config/
│  │     ├─ AppConfig.java                  # Bean + component scan config
│  │     ├─ DbConfig.java                   # DB config placeholder
│  │     ├─ EmailConfig.java                # Email config placeholder
│  │     └─ SecurityConfig.java             # Security config placeholder
│  └─ pack/
│     └─ Student.java                       # Sample component bean
└─ src/main/resources/
  ├─ application.properties                # App properties
  └─ templates/
    ├─ about.html
    ├─ service.html
    ├─ login.html
    └─ success_login.html
```

---

## 5) Class-by-Class Explanation

### 5.1 `FirstBootProjectApplication`
- `@SpringBootApplication` se auto configuration + component scanning + configuration enable hoti hai.
- App start hota hai:

```java
SpringApplication.run(FirstBootProjectApplication.class, args);
```

### 5.2 `HomeController` (`@RestController`)
- Plain text response return karta hai.
- `@Autowired Student student` injected hai (learning/demo purpose).

Routes:
- `GET /magic` -> `This is magic`
- `GET /another` -> `This is actually magic`

### 5.3 `PageController` (`@Controller` + class mapping `/page`)
- MVC style view names return karta hai.
- Console logs print karta hai for route trace.

Routes:
- `GET /page/about` -> `about` template
- `GET /page/service` -> `service` template

### 5.4 `LoginController`
- Base route: `/login`
- `GET /login/` call par service ko invoke karta hai.

Behavior:
1. `LoginService.doLogin()` call hota hai
2. Service repository call karta hai
3. Service currently hardcoded `true` return karta hai
4. View `success_login` return hoti hai

### 5.5 `LoginService`
- Business layer abstraction.
- `LoginRepository.getUser()` call karta hai.
- Future me credentials validation yahin add karni chahiye.

### 5.6 `LoginRepository`
- Data access layer placeholder.
- Currently sirf console print.
- Future me DB query yahin implement hogi.

### 5.7 Config Classes
- `AppConfig`: custom bean config + `@ComponentScan("pack")`
- `DbConfig`, `EmailConfig`, `SecurityConfig`: placeholder modules (good separation for future scaling)

### 5.8 `Student`
- `@Component` annotated sample class.
- Bean lifecycle/injection practice ke liye useful.

---

## 6) Endpoint Reference Table

| Method | URL | Type | Returned Value |
|---|---|---|---|
| GET | `/magic` | REST | `This is magic` |
| GET | `/another` | REST | `This is actually magic` |
| GET | `/page/about` | MVC | `about.html` |
| GET | `/page/service` | MVC | `service.html` |
| GET | `/login/` | MVC + Service Flow | `success_login.html` (current logic) |

---

## 7) Request Flow Diagrams

### 7.1 REST Route Flow

```text
Browser/Client -> HomeController -> String Response
```

### 7.2 MVC Route Flow

```text
Browser -> PageController -> View Name -> Template Resolver -> HTML Response
```

### 7.3 Login Layered Flow

```text
GET /login/
  ↓
LoginController
  ↓
LoginService.doLogin()
  ↓
LoginRepository.getUser()
  ↓
boolean result (currently true)
  ↓
success_login view
```

---

## 8) Prerequisites

Before run, ensure:
- JDK installed (preferably Java 25; otherwise update `pom.xml`)
- Internet available for first-time Maven dependency download
- Port `8080` free

Verify Java:

```powershell
java -version
```

---

## 9) Run Instructions

Open terminal inside `9.Boot/mvclite`.

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### macOS/Linux

```bash
./mvnw spring-boot:run
```

Expected base URL:

```text
http://localhost:8080
```

---

## 10) API / URL Testing Commands

### Browser URLs
- `http://localhost:8080/magic`
- `http://localhost:8080/another`
- `http://localhost:8080/page/about`
- `http://localhost:8080/page/service`
- `http://localhost:8080/login/`

### cURL (REST checks)

```bash
curl http://localhost:8080/magic
curl http://localhost:8080/another
```

---

## 11) Build, Package, Run JAR

### Build

```powershell
.\mvnw.cmd clean package
```

### Run packaged JAR

```powershell
java -jar target\mvclite-0.0.1-SNAPSHOT.jar
```

---

## 12) Dev Tips (Useful While Learning)

1. Console logs dekhkar route trace karo (`rendering about page`, `getting user`, etc.)
2. `@RestController` and `@Controller` output compare karo (text vs HTML)
3. `/login/` flow me hardcoded `true` ko condition se replace karke practice karo
4. `application.properties` me `server.port=1717` set karke custom port test karo

---

## 13) Common Errors + Fixes

### Error A: Java release/version not supported
Reason: Local JDK and `pom.xml` Java version mismatch.

Fix:
- JDK 25 install karo, ya
- `pom.xml` me:

```xml
<java.version>21</java.version>
```

### Error B: Template not found
Reason: Template dependency missing or wrong view name.

Fix checklist:
1. `src/main/resources/templates` me file names exactly same hain?
2. View return value and file name match karte hain?
3. Thymeleaf starter add kiya hai (agar required)?

### Error C: 404 on page routes
Reason: Class-level mapping ignore ho gaya.

Correct routes:
- `/page/about`
- `/page/service`

### Error D: Port already in use
Fix in `application.properties`:

```properties
server.port=1717
```

---

## 14) Current Limitations (By Design)

- No real DB integration yet
- No real authentication/authorization
- HTML templates are minimal placeholders
- Signup controller not implemented yet
- No automated test classes yet

Ye sab intentionally blank areas hain so that you can extend project step by step.

---

## 15) Suggested Upgrade Path (Next 7 Steps)

1. `@RequestMapping` ko `@GetMapping` se replace karo
2. Login page par form submit flow add karo (`POST /login`)
3. DTO/model class add karo for login request
4. Repository me real DB connection (H2/MySQL) integrate karo
5. Spring Security basics add karo
6. Thymeleaf templates ko proper UI content do
7. Unit tests + integration tests add karo

---

## 16) Quick Command Cheat Sheet

```powershell
# Run in dev mode
.\mvnw.cmd spring-boot:run

# Clean + package
.\mvnw.cmd clean package

# Run packaged jar
java -jar target\mvclite-0.0.1-SNAPSHOT.jar

# Run tests (when tests are added)
.\mvnw.cmd test
```

---

## 17) Final Summary

`mvclite` ek clean starter codebase hai jisme:
- REST + MVC dono patterns dikhte hain
- Layered architecture ka seed version hai
- Future-ready config classes already split hain

Agar aap Spring Boot seekh rahe ho, to ye project incremental practice ke liye perfect base hai.
