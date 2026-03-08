# Spring MVC Modern Way - Detailed Revision Notes

This note is written for fast and easy revision.
Module path: `26_Spring MVC_Modern_way/practice`

## 1. What This Module Covers

This project mixes multiple Spring concepts in one place:
- Spring MVC controllers and REST endpoints
- JSON response generation using `@RestController`
- Spring Data JPA with MySQL
- JPA relationships (`OneToMany`, `ManyToMany`)
- Basic dummy object APIs (student, department, subject)

## 2. Project Snapshot

- Spring Boot version: `4.0.3`
- Java version: `25`
- Build tool: Maven (`mvnw`, `mvnw.cmd`)
- Main class: `practice/src/main/java/com/substring/foodie/SubstringFoodieApplication.java`

## 3. Important Dependencies (`practice/pom.xml`)

- `spring-boot-starter-webmvc`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-data-jdbc`
- `spring-boot-starter-thymeleaf`
- `mysql-connector-j`
- `lombok`

Revision note:
- Because both JDBC and JPA starters are present, startup logs show repository scanning messages for multiple Spring Data modules. That is expected here.

## 4. Database Configuration (`application.properties`)

File: `practice/src/main/resources/application.properties`

Configured values:
- DB URL: `jdbc:mysql://localhost:3306/jb2404`
- Username: `root`
- Password: `171712`
- Driver: `com.mysql.cj.jdbc.Driver`
- JPA DDL mode: `update`
- SQL logs enabled

Revision note:
- `spring.jpa.properties.hibernate.dialect` is set manually, but modern Hibernate can auto-detect MySQL dialect.

## 5. Package-Wise Quick Guide

### Controllers

Path: `practice/src/main/java/com/substring/foodie/controller`

- `UserController`
- `StudentController`
- `RestaurantController` (placeholder)
- `OrderController` (placeholder)

### Service Layer

Path: `practice/src/main/java/com/substring/foodie/service`

- `UserService` interface
- `impl/UserServiceImpl`

### Persistence

Path: `practice/src/main/java/com/substring/foodie/repository`

- `UserRepo extends JpaRepository<User, String>`

### Entities

Path: `practice/src/main/java/com/substring/foodie/entity`

- `User`
- `Restaurant`
- `RoleEntity`
- `Role` (enum)

### Dummy Object Model (non-DB)

Path: `practice/src/main/java/com/substring/foodie/bunch_of_object`

- `Student`, `Department`, `Subject`, `DummyDataGenerator`

## 6. Endpoint Revision Table

Base URL: `http://localhost:8080`

### User endpoints

Controller: `UserController` with base mapping `/user`

1. `GET /user/`
- Returns string: `"user_list"`

2. `GET /user/1`
- Returns integer: `7`

3. `GET /user/players-list`
- Returns list of players

4. `GET /user/details`
- Returns one `User` object with random UUID

### Student endpoints

Controller: `StudentController` with base mapping `/student`

1. `GET /student/stu_details`
- Returns one student with department and subjects

2. `GET /student/all`
- Returns list of dummy students generated from `DummyDataGenerator`

## 7. JPA Entity Relationship Revision

### `User` entity

Table: `foodie_users`

Fields include:
- `id`, `name`, `email`, `password`, `address`, `phoneNumber`
- `role` (`EnumType.STRING`)
- `isAvailable`

Relationships:
- `@OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)` with `Restaurant`
- `@ManyToMany(cascade = ALL)` with `RoleEntity` via join table `user_role`

### `Restaurant` entity

Table: `foodie_restaurant`

Relationship:
- `@ManyToOne` -> `User`

### `RoleEntity`

- `id` (auto generated)
- `name`
- inverse side of `@ManyToMany(mappedBy = "roleEntities")`

## 8. Service Layer Logic

File: `practice/src/main/java/com/substring/foodie/service/impl/UserServiceImpl.java`

Methods:
1. `saveUser(User user)`
- Assigns random UUID
- Saves using `userRepo.save(...)`

2. `updateUser(User user, String userId)`
- Finds existing user by ID
- Updates fields (currently name shown)
- Saves updated user

3. `testUserRole()`
- Creates one user
- Creates two role entities (`ROLE_ADMIN`, `ROLE_GUEST` as names)
- Links both sides of many-to-many
- Saves user

Revision note:
- The `Role` enum values are `ADMIN`, `CUSTOMER`, `DELIVERY_BOY`, while `RoleEntity` names are custom strings (example `ROLE_ADMIN`). Keep this distinction clear.

## 9. Known Pitfalls + Fixes

1. `UnsupportedOperationException` in `/student/stu_details`
- Cause: using `List.of(...)` and then trying `add(...)`
- Correct approach: use `new ArrayList<>()` if you need to add elements

2. Endpoint still failing after code change
- Cause: old app process running old compiled classes
- Fix: stop app and run again

3. DB startup issues
- Check MySQL server status
- Verify URL/user/password in `application.properties`

4. Security concern in demo
- `password` is plain text in responses/examples
- In real project, never expose password in API response

## 10. How To Run

Git Bash:

```bash
cd "C:/Users/Akash/Desktop/New folder (2)/Springboot/26_Spring MVC_Modern_way/practice"
./mvnw spring-boot:run
```

PowerShell:

```powershell
cd "C:\Users\Akash\Desktop\New folder (2)\Springboot\26_Spring MVC_Modern_way\practice"
.\mvnw.cmd spring-boot:run
```

## 11. Fast Revision Checklist

- I can explain difference between `@Controller` and `@RestController`.
- I can map all current URLs and expected response type.
- I understand `User` <-> `Restaurant` one-to-many.
- I understand `User` <-> `RoleEntity` many-to-many.
- I can debug immutable list error in student endpoint.
- I know where DB config lives and how Hibernate DDL works.

## 12. Practice Tasks

1. Add POST endpoint to create user using `UserService.saveUser()`.
2. Add PUT endpoint for `updateUser(...)`.
3. Add DTO to hide password from API output.
4. Convert placeholder `RestaurantController` into working CRUD endpoints.
5. Add global exception handler using `@ControllerAdvice`.

---

If you revise section 6 + 7 + 9 properly, most viva/interview questions for this module become very easy.
