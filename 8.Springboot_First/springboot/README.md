# Springboot_First (Project 8) - Simple + Detailed Concept Guide

Ye project Spring Boot ka **starter level practical demo** hai jisme aap ek hi jagah par REST endpoint aur MVC page rendering dono samajh sakte ho.

Is README ka goal hai ki beginner ko bina confusion ke yeh clear ho:
- Spring Boot app start kaise hota hai
- `@RestController` aur `@Controller` me real difference kya hai
- Browser request backend tak kaise pahunchti hai
- Template page kaise render hota hai
- Project ko run/build/test kaise karein

---

## 1) Project Snapshot

- Project folder: `8.Springboot_First/springboot`
- Java version (configured): `25`
- Spring Boot parent: `4.0.3`
- Build tool: Maven Wrapper (`mvnw`, `mvnw.cmd`)
- App name: `springboot`
- Running port: `1717`

Base URL:

```text
http://localhost:1717
```

---

## 2) Is Project Se Kya Seekhne Ko Milega

1. Spring Boot bootstrapping (`SpringApplication.run`)
2. `@RequestMapping` ke through URL mapping
3. REST response return karna (plain text)
4. View/template return karna (HTML page)
5. `application.properties` se server config control

---

## 3) Folder Structure With Meaning

```text
springboot/
├─ pom.xml
├─ src/main/java/com/firstboot/project/springboot/
│  ├─ App.java
│  └─ controller/
│     ├─ HomeController.java
│     └─ PageController.java
├─ src/main/resources/
│  ├─ application.properties
│  └─ templates/
│     ├─ about.html
│     └─ service.html
└─ mvnw / mvnw.cmd
```

### Quick meaning
- `App.java` -> app start point
- `controller/` -> incoming request handle
- `templates/` -> HTML views
- `application.properties` -> app settings

---

## 4) Important Classes Explained (Simple Language)

## 4.1 `App.java`

Role: Pure project ka entry point.

```java
SpringApplication.run(App.class, args);
```

Is line ka simple meaning:
- Spring container start karo
- beans load karo
- embedded server start karo
- incoming requests accept karo

### Annotation concept
- `@SpringBootApplication` = 3 major cheezein ek sath:
  - `@Configuration`
  - `@EnableAutoConfiguration`
  - `@ComponentScan`

Matlab minimal code me Spring setup ready.

---

## 4.2 `HomeController.java` (`@RestController`)

Ye class **API/text response** ke liye hai.

Routes:
- `GET /magic` -> `This is magic`
- `GET /another` -> `This is actually magic`

### Concept
- `@RestController` ka matlab method jo return karega, wo direct response body me jayega.
- Yahan koi HTML template resolve nahi hota.

---

## 4.3 `PageController.java` (`@Controller`)

Ye class **HTML page rendering** ke liye hai.

Routes:
- `GET /about` -> returns `about` (template name)
- `GET /service` -> returns `service` (template name)

Spring in names ko map karta hai:
- `about` -> `src/main/resources/templates/about.html`
- `service` -> `src/main/resources/templates/service.html`

### Console logs
- `rendering about page`
- `rendering service page`

Ye logs debugging me help karte hain.

---

## 4.4 `application.properties`

Current values:

```properties
spring.application.name=springboot
server.port=1717
```

Simple meaning:
- app ka logical name `springboot`
- server default `8080` ke instead `1717` pe run karega

---

## 5) End-to-End Request Flow (Easy Diagram)

### A) REST flow (`/magic`)

```text
Browser -> HomeController -> String response -> Browser
```

### B) MVC flow (`/about`)

```text
Browser -> PageController -> view name (about)
        -> template resolver -> about.html -> Browser
```

---

## 6) Run Project Step-by-Step

Terminal ko `8.Springboot_First/springboot` folder me open karein.

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### macOS/Linux

```bash
./mvnw spring-boot:run
```

Start hone ke baad open:

```text
http://localhost:1717
```

---

## 7) Test All URLs

### REST URLs
- `http://localhost:1717/magic`
- `http://localhost:1717/another`

### MVC URLs
- `http://localhost:1717/about`
- `http://localhost:1717/service`

### Optional cURL check

```bash
curl http://localhost:1717/magic
curl http://localhost:1717/another
```

---

## 8) Build and Run JAR

### Build command

```powershell
.\mvnw.cmd clean package
```

### Run generated jar

```powershell
java -jar target\springboot-0.0.1-SNAPSHOT.jar
```

---

## 9) `@RestController` vs `@Controller` (Most Important Concept)

| Point | `@RestController` | `@Controller` |
|---|---|---|
| Output type | Raw data/text/JSON | View name (HTML page) |
| Template engine needed? | No | Yes |
| Current example | `/magic`, `/another` | `/about`, `/service` |

Agar aapko API banana hai -> `@RestController`

Agar aapko webpage render karna hai -> `@Controller`

---

## 10) Common Errors and Quick Fixes

## 10.1 Java version mismatch

Issue: `Unsupported class file version` ya `release version not supported`

Reason: local JDK and `pom.xml` mismatch

Fix:
- JDK 25 install karo, ya
- `pom.xml` me Java version lower set karo (e.g. `21`)

## 10.2 Port already in use

Issue: app start nahi hota, port conflict

Fix in `application.properties`:

```properties
server.port=8080
```

## 10.3 Template not found

Issue: `/about` or `/service` open karte time error

Checklist:
1. file path exactly `src/main/resources/templates/` me hai?
2. return name aur file name same hai?
3. dependency issue ho to Thymeleaf starter add karein

---

## 11) Practice Tasks (Concept Strong Karne Ke Liye)

1. `/hello` REST endpoint add karo jo `Hello from Spring Boot` return kare
2. `home.html` template add karo aur `/` route create karo
3. `@RequestMapping` ko `@GetMapping` me convert karo
4. `application.properties` se port change karke verify karo
5. `about.html` me dynamic data bhejne ke liye `Model` use karo

---

## 12) Quick Command Cheat Sheet

```powershell
# Run application
.\mvnw.cmd spring-boot:run

# Clean + build
.\mvnw.cmd clean package

# Run packaged jar
java -jar target\springboot-0.0.1-SNAPSHOT.jar
```

---

## 13) Final One-Line Summary

Ye project Spring Boot ke core foundation concepts (`bootstrapping + REST + MVC + config`) ko simple aur practical form me samjhane ke liye perfect starter hai.
