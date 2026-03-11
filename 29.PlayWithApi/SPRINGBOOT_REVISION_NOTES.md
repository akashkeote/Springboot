# Spring Boot Revision Notes - 29.PlayWithApi

Ye notes beginner friendly revision ke liye banaye gaye hain.
Target: jo concepts humne implement aur fix kiye, unko simple language me samajhna.

## 1) Project ka high-level goal

Is module me hum User APIs bana rahe hain:
- User create karna
- User list lana
- User by id lana
- Pagination aur sorting ke sath list lana
- Proper exception handling dena

Tech stack:
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Hibernate

## 2) Layered Architecture ka simple flow

Request flow:
Controller -> Service -> Repository -> Database

Response flow:
Database -> Repository -> Service -> Controller -> Client

Kyun zaroori hai:
- Controller: sirf request/response handle kare
- Service: business logic handle kare
- Repository: DB operations handle kare
- DTO: API contract handle kare (Entity directly expose na ho)

## 3) DTO vs Entity concept (important)

Entity:
- DB table mapping ke liye hoti hai
- JPA annotations use karti hai

DTO:
- API request/response payload ke liye hota hai
- External client ko safe aur controlled data dene ke liye

Is project me key rule:
- Service methods me User entity ko UserDto me convert karke return kiya gaya

## 4) Files aur unka role

1. src/main/java/com/substring/foodie/controller/UserController.java
- REST endpoints define karta hai
- URL base mapping: /api/version1/users

2. src/main/java/com/substring/foodie/service/UserService.java
- Service contract/interface define karta hai

3. src/main/java/com/substring/foodie/service/impl/UserServiceImpl.java
- Actual business logic implementation
- DTO <-> Entity conversion
- Duplicate email pre-check
- Paginated getAllUsers(Pageable)

4. src/main/java/com/substring/foodie/repository/UserRepo.java
- JPA repository methods
- Custom methods: findByEmail, findByName, searchByKeyword

5. src/main/java/com/substring/foodie/exception/GlobalExceptionHandler.java
- Centralized exception handling
- 404, 409, 400, 500 responses standard format me dena

6. src/main/java/com/substring/foodie/exception/ResourceNotFoundException.java
- Custom runtime exception for not-found case

## 5) APIs jo currently relevant hain

Base URL:
http://localhost:8080/api/version1/users

A) Create User
- Method: POST
- Path: /
- Body example:
{
  "name": "Akash Keote",
  "email": "akashkeote_new@gmail.com",
  "address": "123 Main St",
  "phoneNumber": "123-456-7890"
}

Expected:
- 201 Created
- Created user DTO response

B) Get All Users with Pagination
- Method: GET
- Path: /
- Query params:
  - page (default 0)
  - size (default 10)
  - sortBy (default createdDate)
  - sortDir (default desc)

Example:
http://localhost:8080/api/version1/users?page=0&size=10&sortBy=createdDate&sortDir=desc

Expected:
- 200 OK
- Page<UserDto> response

C) Get User by Id
- Method: GET
- Path: /{userId}

Expected:
- 200 OK agar user mila
- 404 agar user nahi mila

## 6) Important fixes jo humne kiye

### Fix 1: Endpoint typo issue
Problem:
- URL me vserion1 likh diya tha (typo)
- Result: 404 Not Found

Fix:
- Correct path use: /api/version1/users

### Fix 2: MySQL connection failure
Problem:
- Communications link failure
- App startup fail ho rahi thi

Reason:
- MySQL service down thi ya DB inaccessible tha

Fix:
- MySQL service start
- DB URL, username, password validate

### Fix 3: Duplicate email SQL error
Problem:
- SQL Error 1062
- Duplicate entry for unique email key

Reason:
- Same email se multiple POST requests

Fixes:
1. Service level pre-check add kiya:
   if email exists -> IllegalArgumentException
2. Global exception handler me 409 Conflict response add kiya

### Fix 4: Wrong HTTP method
Problem:
- Request method GET is not supported

Reason:
- POST endpoint ko GET se hit kiya

Fix:
- Create endpoint ke liye POST hi use karo

### Fix 5: Pagination imports galat the
Problem:
- Controller me SpringDataWebProperties.Pageable, Sort aur PageRequestDto use ho raha tha

Fix:
- Correct imports:
  - org.springframework.data.domain.Pageable
  - org.springframework.data.domain.Sort
  - org.springframework.data.domain.PageRequest
- PageRequest.of(page, size, sort) use kiya

### Fix 6: Global exception handler wiring issues
Problems:
- GlobalExceptionHandler me unnecessary AuthController injection tha
- ResourceNotFoundException runtime exception nahi tha

Fixes:
- Unnecessary controller injection remove
- ResourceNotFoundException ko RuntimeException extend karaya
- Exception handlers clean kiye

## 7) Exception handling map (revision shortcut)

1. ResourceNotFoundException -> 404 Not Found
2. ResourceNotFound (service package wala) -> 404 Not Found
3. IllegalArgumentException -> 409 Conflict
4. DataIntegrityViolationException -> 409 Conflict
5. MethodArgumentNotValidException -> 400 Bad Request
6. NullPointerException -> 500 Internal Server Error

## 8) Pagination concept beginner explanation

Pageable object me 3 cheeze hoti hain:
1. page number (0-based)
2. page size
3. sort configuration

Example thought process:
- page=0 size=10 means first 10 records
- page=1 size=10 means next 10 records
- sortBy=createdDate sortDir=desc means latest first

Service side:
- userRepo.findAll(pageable) Page<User> deta hai
- map karke Page<UserDto> return kiya gaya

## 9) DTO conversion concept

Method 1: convertUserDtoToUser
- incoming request DTO ko entity me convert karta hai

Method 2: convertUserToUserDto
- DB se aayi entity ko response DTO me convert karta hai

Benefit:
- API response controlled rehta hai
- internal fields safely hide kiye ja sakte hain

## 10) Postman testing checklist

1. App run karo
2. POST with unique email bhejo
3. Same email dobara bhejo -> 409 aana chahiye
4. GET paginated list check karo
5. GET by userId check karo
6. Non-existing userId check karo -> 404 aana chahiye

## 11) Common mistakes and quick diagnosis

1. 404 on users API
- Path typo check karo
- version1 spelling check karo

2. App startup fail with JDBC errors
- MySQL service check karo
- port 3306 check karo
- credentials check karo

3. SQL 1062 duplicate
- email already present hai
- unique email use karo

4. Method not supported
- POST endpoint ko GET se mat call karo

## 12) Beginner revision questions

1. Controller ka kaam kya hai?
2. Service interface kyun banate hain?
3. DTO aur Entity me difference kya hai?
4. Pagination me page aur size ka exact meaning kya hai?
5. 404, 409, 400, 500 kab aate hain?
6. Duplicate email ko service level pe check karna kyun useful hai?

## 13) Recommended next improvements

1. ResourceNotFound aur ResourceNotFoundException me se sirf ek standard exception rakho
2. User create ke request fields par bean validation annotations add karo
3. API responses ko ek common response wrapper me standardize karo
4. Unit tests and integration tests add karo
5. Swagger/OpenAPI documentation add karo

## 14) One-line summary

Is module me humne Spring Boot REST APIs ko production style ke close le jane ke liye layered design, DTO mapping, pagination, validation/error handling, database issue diagnosis, aur duplicate data handling implement kiya.
