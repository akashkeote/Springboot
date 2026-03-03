# Spring DI Practice Project (2.untitled)

Ye project Spring Dependency Injection ko beginner level se interview level tak samajhne ke liye banaya gaya hai.

Isme tumhe same concept ke alag-alag practical versions milenge:
- XML Setter Injection
- XML Constructor Injection
- Annotation-based Spring Boot Injection
- Interface + Multiple Implementations + Runtime Selection

---

## 1) Dependency Injection kya hota hai?

Simple language:
- Jab ek class ko doosri class ki help chahiye hoti hai, to object khud `new` karke banane ke bajay bahar se diya jata hai.
- Is process ko **Dependency Injection (DI)** bolte hain.

Example idea:
- `Controller` ko `Service` chahiye
- Tight coupling: `Controller` khud `new Service()` kare
- Loose coupling: Spring container `Service` banakar `Controller` me inject kare

DI ke benefits:
- Code loosely coupled hota hai
- Testing easy hoti hai
- Replace/extend karna easy hota hai
- Clean architecture follow hoti hai

---

## 2) Setter Injection vs Constructor Injection

### Setter Injection
- Dependency setter method se inject hoti hai (`setService(...)`)
- Optional dependency ke liye useful
- Object ban sakta hai even without dependency (danger: null risk)

### Constructor Injection
- Dependency constructor se inject hoti hai
- Mandatory dependency ke liye best practice
- Object incomplete state me create nahi hota
- Interview me generally recommended approach

Quick interview line:
> "Mandatory dependencies ke liye constructor injection, optional ke liye setter injection."

---

## 3) XML Injection kya hai?

XML Injection ka matlab:
- Beans aur dependency wiring Java code ke bahar XML file me define hoti hai.
- Spring container XML padhta hai aur objects create + inject karta hai.

Is project me:
- `config.xml` => Setter Injection demo
- `config1.xml` => Constructor Injection demo

---

## 4) Project Flow (Main logic explained)

## A) `Main.java` (Project1) - XML + Setter Injection

Flow:
1. `ApplicationContext container = new ClassPathXmlApplicationContext("config.xml")`
    - Spring container start hota hai
    - `config.xml` load hoti hai
2. `controller` bean get hoti hai
    - Bean id XML me defined hoti hai
3. `controller.getUserName()` aur `controller.saveUser()` call
    - Controller ke andar service pe method call hota hai
    - Dependency already setter se inject ho chuki hoti hai

Samajhne wala point:
- Controller khud service create nahi kar raha
- Spring ne service inject ki

## B) `Main1.java` (Project2) - XML + Constructor Injection

Flow:
1. `ClassPathXmlApplicationContext("config1.xml")` se container start
2. Bean retrieval hoti hai
3. Constructor ke through dependency inject hoti hai

Samajhne wala point:
- Object create hote hi dependency available hoti hai
- Null dependency ka risk kam

## C) `Main3.java` (Project3) - Spring Boot + `@ComponentScan`

Flow:
1. `SpringApplication.run(Main3.class, args)`
    - Spring Boot app start hota hai
    - Container create hota hai
2. `@ComponentScan("Project3")`
    - Spring `Project3` package me components scan karta hai
3. `container.getBean(UserController3.class)`
    - Type-based bean retrieval

Samajhne wala point:
- XML wiring ki jagah annotation-based auto wiring use ho rahi hai

## D) `Project4.Main4` - Interface Injection + Multiple Implementations

Flow:
1. Spring Boot app start
2. `NotificationController` bean get
3. Controller `NotificationService` interface ko call karta hai
4. Actual implementation property/config ke base par choose hoti hai

Samajhne wala point:
- Controller implementation pe dependent nahi hai, interface pe dependent hai
- Ye design scalable aur interview-friendly hai

---

## 5) Interview Preparation Section

### Q1) DI aur IoC me difference?
- **IoC** = Control framework ke paas chala gaya
- **DI** = IoC achieve karne ka practical way (dependency inject karke)

### Q2) Constructor injection better kyun?
- Mandatory dependencies ensure hoti hain
- Immutable design possible
- Test-friendly hai

### Q3) `ApplicationContext` kya karta hai?
- Beans create karta hai
- Dependencies inject karta hai
- Bean lifecycle manage karta hai

### Q4) `@SpringBootApplication` me kya hota hai?
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

### Q5) Type-based `getBean(Class)` kyun use kare?
- Safer than string id
- Compile-time clarity better

---

## 6) Practice Tasks (Self Practice)

1. Setter injection example me ek new method add karo (`deleteUser`)
2. Constructor injection me extra dependency add karke inject karo
3. Project4 me ek aur implementation banao (e.g. PushNotificationService)
4. Property change karke runtime behavior switch karke dekho
5. Try writing same wiring with annotations only (no XML)

---

## 7) Run Commands

From project root (`2.untitled`):

```bash
mvn clean compile
```

Run individual mains (if exec plugin available):

```bash
mvn -DskipTests exec:java -Dexec.mainClass=Main
mvn -DskipTests exec:java -Dexec.mainClass=Main1
mvn -DskipTests exec:java -Dexec.mainClass=Main3
mvn -DskipTests exec:java -Dexec.mainClass=Project4.Main4
```

If Maven installed nahi hai, IDE se directly main classes run kar sakte ho.

---

## 8) Quick Revision (1 minute)

- Tight coupling avoid karo
- Spring container se dependencies inject karvao
- Setter = optional dependencies
- Constructor = mandatory dependencies
- XML config old but important for fundamentals
- Annotation + interface based design modern interview expectation hai

---

Best use: Is README ko practice ke time checklist ki tarah use karo, aur interview se pehle Q&A section revise karo.
