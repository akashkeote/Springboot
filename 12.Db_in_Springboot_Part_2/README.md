# DB in Spring Boot Part 2 - Detailed Revision Notes

This README is made for quick and easy revision of this module.
Project path: `12.Db_in_Springboot_Part_2/demo`

## 1. What You Are Learning Here

This module shows how to use:
- Spring Boot + Spring JDBC (`JdbcTemplate`)
- MySQL connection and schema initialization
- DAO pattern for CRUD operations
- One-to-many style relation using foreign key:
  - `category` (parent)
  - `course` (child)

## 2. Tech Stack

- Java: `25`
- Spring Boot: `4.0.3`
- Spring Data JDBC starter
- MySQL Connector/J
- Build tool: Maven

Main dependency file: `demo/pom.xml`

## 3. Important Files (Must Revise)

- App entry + runner: `demo/src/main/java/com/database/springboot_part/akash.java`
- DAO classes:
  - `demo/src/main/java/com/database/springboot_part/Dao/CategoryDao.java`
  - `demo/src/main/java/com/database/springboot_part/Dao/CourseDao.java`
- Entities:
  - `demo/src/main/java/com/database/springboot_part/entities/Category.java`
  - `demo/src/main/java/com/database/springboot_part/entities/Course.java`
- Config:
  - `demo/src/main/resources/application.properties`
  - `demo/src/main/resources/scheme.sql`

## 4. Database Configuration Flow

In `application.properties`:
- `spring.datasource.url=jdbc:mysql://127.0.0.1:3306/springbootdb`
- `spring.datasource.username=root`
- `spring.datasource.password=171712`
- `spring.sql.init.mode=always`
- `spring.sql.init.schema-locations=classpath:scheme.sql`

Meaning:
1. App connects to MySQL DB `springbootdb`.
2. On startup, Spring executes `scheme.sql`.
3. Tables are created automatically if missing.

## 5. Schema (Very Important)

Defined in `scheme.sql`:

```sql
create table if not exists category(
    id int primary key,
    title varchar(100),
    description varchar(100)
);

create table if not exists course(
    courseId int primary key,
    courseTitle varchar(100),
    courseDescription varchar(255),
    coursePrice int,
    categoryId int,
    constraint fk_course_category
        foreign key (categoryId)
        references category(id)
        on update cascade
        on delete cascade
);
```

Key revision points:
- `course.categoryId` is foreign key to `category.id`.
- `on delete cascade`: deleting a category deletes linked courses.
- Insert category first, then course with valid `categoryId`.

## 6. Application Entry and Execution

Class: `akash.java`
- Annotated with `@SpringBootApplication`
- Implements `CommandLineRunner`
- `run()` method executes when app starts.

Current behavior in `run()`:
- Calls `cD.getAllCou()`
- Prints all course IDs

So this project is currently designed as a startup runner demo (not REST API).

## 7. DAO Revision Notes

### CategoryDao

Uses `JdbcTemplate` with methods:
- `initDb()`:
  - SQL: `create database if not exists springbootdb`
  - Note: this depends on current DB connection permissions.
- `init()` (`@PostConstruct`):
  - creates `category` table if missing.
- `set(Category c)`:
  - inserts one category.
- `delete(Category c, int id)`:
  - deletes category by id.
- `get(int id)`:
  - fetches one row.
- `getAll()`:
  - fetches all categories using inline `RowMapper`.
- `update(int id, Category nw)`:
  - updates title/description by id.

### CourseDao

Methods:
- `insert(Course c)`
- `get(int id)`
- `getAllCou()`
- `update(Course c)`
- `delete(Course c, int id)`
- `getCourseByCategoryId(int catid)`

All methods map SQL result -> `Course` manually.

## 8. Entities Revision

### Category
Fields:
- `id`
- `title`
- `description`

### Course
Fields:
- `courseId`
- `courseTitle`
- `courseDescription`
- `coursePrice`
- `categoryId`

These are plain Java classes (POJOs), no JPA annotations used here.

## 9. How to Run

From terminal:

```bash
cd "C:/Users/Akash/Desktop/New folder (2)/Springboot/12.Db_in_Springboot_Part_2/demo"
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
cd "C:\Users\Akash\Desktop\New folder (2)\Springboot\12.Db_in_Springboot_Part_2\demo"
.\mvnw.cmd spring-boot:run
```

## 10. Common Errors and Fast Fixes

1. MySQL connection failure
- Check MySQL server is running.
- Verify username/password in `application.properties`.
- Ensure DB `springbootdb` exists or user has create permission.

2. Foreign key error while inserting course
- Cause: `categoryId` not present in `category` table.
- Fix: insert category first.

3. Duplicate key error
- Cause: same `id` or `courseId` already exists.
- Fix: use unique keys or run update instead of insert.

4. Nothing prints in console
- Check `run()` method content in `akash.java`.
- Ensure code lines are not commented out.

## 11. Quick Revision Checklist (Before Interview/Class)

- I can explain why `JdbcTemplate` is used.
- I know startup flow (`CommandLineRunner` + schema init).
- I can write insert/get/update/delete SQL for both tables.
- I understand FK relation and cascade behavior.
- I can debug DB connection and key constraint errors.

## 12. Practice Tasks

1. Add a method: get courses above a price.
2. Add a method: get category by title.
3. Return total course count per category.
4. Convert runner code into REST endpoints (next step learning).

---

If you revise this README section by section, this module becomes easy to remember in 10-15 minutes.
