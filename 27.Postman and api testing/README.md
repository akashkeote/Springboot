# Spring Boot Revision Notes (Beginner to Deep)

Yeh notes is folder ke project ke liye banaye gaye hain taaki beginner bhi Spring Boot ko fast revise kare aur depth me samajh sake.

Project location:
- `27.Postman and api testing/project`

---

## 1) Spring Boot Kya Hai?

Spring Boot, Spring framework ka opinionated version hai jo:
- Boilerplate config kam karta hai
- Embedded server deta hai (Tomcat by default)
- Auto-configuration provide karta hai
- Production-ready features deta hai (Actuator, metrics, health)

Simple line: "Configuration kam, development fast."

---

## 2) Core Building Blocks

### 2.1 `@SpringBootApplication`
Is annotation ke 3 parts hote hain:
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

Matlab: app ko config class bhi banata hai, auto setup bhi karta hai, aur components scan bhi karta hai.

### 2.2 Beans aur IoC
- Bean: object managed by Spring container
- IoC: object creation/control Spring ke paas hota hai
- DI: dependencies inject hoti hain (`@Autowired`, constructor injection)

Best practice:
- Field injection avoid karo
- Constructor injection use karo

---

## 3) Typical API Flow (End to End)

Request flow mostly aisa hota hai:
1. Client (Postman) request bhejta hai
2. `@RestController` endpoint hit hota hai
3. Controller service ko call karta hai
4. Service business logic chalata hai
5. Repository DB access karti hai (agar DB use ho)
6. Response JSON me return hota hai

Layered architecture:
- Controller: HTTP handling
- Service: business rules
- Repository: data access
- Model/Entity/DTO: data structure

---

## 4) Important Annotations (Must Revise)

### Controller Side
- `@RestController`: REST API controller
- `@RequestMapping`: base URL
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- `@PathVariable`: URL se variable
- `@RequestParam`: query params
- `@RequestBody`: JSON to Java object

### Service/Repository Side
- `@Service`: business layer bean
- `@Repository`: persistence layer bean
- `@Component`: generic bean

### Validation and Error
- `@Valid`
- `@NotNull`, `@NotBlank`, `@Size`, `@Email`
- `@ControllerAdvice`, `@ExceptionHandler`

---

## 5) HTTP Basics for Postman Practice

### Common Methods
- GET: data read
- POST: new data create
- PUT: full update
- PATCH: partial update
- DELETE: remove data

### Common Status Codes
- `200 OK` -> success read/update
- `201 Created` -> resource created
- `204 No Content` -> success but no body
- `400 Bad Request` -> invalid input
- `404 Not Found` -> resource missing
- `500 Internal Server Error` -> server side issue

---

## 6) JSON and Jackson

Spring Boot JSON mapping Jackson se karta hai.
- Request JSON -> Java object
- Java object -> Response JSON

Useful annotations:
- `@JsonProperty`
- `@JsonIgnore`
- `@JsonFormat`

Tip:
- Model me default constructor + getters/setters rakho (ya Lombok use karo).

---

## 7) Postman Quick Workflow (Daily Revision)

1. API run karo
2. Postman me collection banao
3. Har endpoint ke liye request save karo
4. Body me valid/invalid dono payload test karo
5. Status code aur response structure verify karo
6. Environment variables use karo (base URL etc.)

Example base URL:
- `http://localhost:8080`

Example endpoints:
- `GET /users`
- `GET /users/{id}`
- `POST /users`
- `PUT /users/{id}`
- `DELETE /users/{id}`

---

## 8) Clean Code Rules (Beginner se Pro)

- Controller me business logic mat rakho
- Service me validation + business decisions rakho
- Standard response structure follow karo
- Exception handling centralize karo
- Naming clear rakho (`createUser`, `getUserById`)
- Magic strings avoid karo

---

## 9) Common Mistakes and Fix

1. `404` on endpoint
- URL mismatch check karo
- Controller base path check karo
- Method mapping check karo

2. `415 Unsupported Media Type`
- Header me `Content-Type: application/json` bhejo

3. `400 Bad Request`
- JSON field names class fields se match hone chahiye
- Validation fail ho sakti hai

4. App start nahi ho rahi
- Port conflict (`8080` busy)
- Dependency conflict
- Syntax/bean creation error

---

## 10) Revision Roadmap (7 Days)

### Day 1
- Spring Boot basics, project structure, annotations

### Day 2
- REST controller and request mapping

### Day 3
- Service layer, response handling

### Day 4
- Validation + global exception handling

### Day 5
- Repository/JPA basics (if DB enabled)

### Day 6
- Postman advanced: environments, tests, collections

### Day 7
- Mini CRUD project from scratch + self review

---

## 11) Interview Quick Questions

- Spring aur Spring Boot me difference?
- `@Component`, `@Service`, `@Repository` me kya difference?
- `@Autowired` ka best usage kya hai?
- `@RestController` aur `@Controller` difference?
- `@PathVariable` vs `@RequestParam`?
- Global exception handling kaise karte hain?
- `@SpringBootApplication` me kaun kaun se annotations include hote hain?

---

## 12) Fast Self-Checklist

- Kya main endpoint create kar sakta hoon bina copy paste?
- Kya main valid and invalid requests test kar pata hoon?
- Kya main status codes logically use kar raha hoon?
- Kya main exception handling properly kar raha hoon?
- Kya code layers me separated hai?

Agar inme se sabka answer "yes" hai, aap strong track par ho.

---

## 13) Next Practical Step

Is project me:
1. Ek complete `User` CRUD banao
2. Validation add karo
3. Global exception handler add karo
4. Postman collection export karo
5. README me sample request/response add karo

Yeh 5 kaam kar loge to beginner se strong intermediate level clear ho jayega.
