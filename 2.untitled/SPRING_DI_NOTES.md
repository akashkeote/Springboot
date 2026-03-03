# Spring Dependency Injection - Method 1: XML with Setter Injection

## 📝 Assignment Question 1 - Part 1

**Question:** UserService needs to be injected into UserController using Spring DI with XML configuration and Setter Injection.

---

## ✅ Completed Implementation

### Files Created/Modified:
1. ✅ `UserService.java` - Service class (no annotations)
2. ✅ `UserController.java` - Controller with setter method
3. ✅ `config.xml` - Spring XML configuration
4. ✅ `Main.java` - Application entry point

---

## 🔑 Important Concepts & Doubts Cleared

### 1️⃣ **XML ke sath Annotations kyun nahi lagate?**

**Answer:**
- Spring mein 2 approaches hain:
  - **XML-based:** Sab configuration XML file mein (Purana tareeka)
  - **Annotation-based:** @Service, @Component, @Autowired use karo (Naya tareeka)
- **Dono ek sath use nahi karte** - confusion hota hai
- Is assignment mein XML use kiya to annotations nahi chahiye!

---

### 2️⃣ **Property Name "userService" kyun hai?**

**Answer:**
- **JavaBeans Convention** follow hota hai:

```
Setter Method:  setUserService()
                ^^^-----------
                Remove "set" ↓
                UserService
                Lowercase first ↓
Property Name:  userService  ← This goes in XML!
```

**Rule:** Property name = setter method name minus "set" with lowercase first letter

**Examples:**
| Setter Method | XML Property Name |
|---------------|-------------------|
| `setUserService()` | `userService` |
| `setMyService()` | `myService` |
| `setAbc()` | `abc` |

---

### 3️⃣ **Import automatic kyun nahi huye?**

**Answer:**
- Spring dependency download hone tak IntelliJ ko pata nahi tha ki classes exist karti hain
- **Solution:**
  1. `pom.xml` mein `spring-context` dependency add karo
  2. Maven reload karo (Right-click pom.xml → Maven → Reload)
  3. **Alt+Enter** shortcut use karo automatic imports ke liye

---

### 4️⃣ **ApplicationContext kya hai? "Container" kyun?**

**Answer:**
- `ApplicationContext` = **Spring IoC Container**
- **Container ka kaam:**
  - XML/Config file read karna
  - Beans create karna
  - Dependencies inject karna
  - Bean lifecycle manage karna
- Variable naam kuch bhi ho sakta:
  - `container` (clear naam - recommended)
  - `context`
  - `ctx`
  - `factory`

---

### 5️⃣ **UserController.class kyun likha?**

**Answer:**
- **Type Safety** ke liye!

**Without .class (Old way):**
```java
UserController c = (UserController) container.getBean("controller");
// Manual casting - error prone!
```

**With .class (Modern way):**
```java
UserController c = container.getBean("controller", UserController.class);
// Automatic casting - type safe!
```

`.class` = Java reflection object, Spring ko exact type pata chal jata hai

---

## 🎯 How It Works - Step by Step

### Step 1: Spring Container XML Read Karta Hai
```xml
<bean id="service" class="Project1.UserService">
<bean id="controller" class="Project1.UserController">
    <property name="userService" ref="service"/>
```

### Step 2: Spring Beans Create Karta Hai
```java
UserService service = new UserService();
UserController controller = new UserController();
```

### Step 3: Spring Setter Method Call Karta Hai (Injection!)
```java
controller.setUserService(service);  // Dependency inject ho gayi!
```

### Step 4: Bean Ready Hai - Use Karo!
```java
controller.getUserName();  // service.getUserName() internally call hoga
```

---

## 📌 Key Takeaways

| Concept | Explanation |
|---------|-------------|
| **XML Configuration** | Beans XML file mein define karte hain |
| **No Annotations** | XML approach mein @Service, @Component nahi chahiye |
| **Setter Injection** | `<property>` tag use karke setter method call hota hai |
| **Property Naming** | Setter method name se derive hota hai |
| **IoC Container** | ApplicationContext beans manage karta hai |
| **Type Safety** | `.class` use karke safe bean retrieval |

---

## 🚀 Next Steps

- ✅ Method 1: XML with Setter Injection (COMPLETED!)
- ⏭️ Method 2: Constructor Injection
- ⏭️ Method 3: @Autowired Field Injection (Annotation-based)
- ⏭️ Question 2: NotificationService with Email/SMS implementations

---

## 💡 Pro Tips

1. **Alt+Enter** press karo automatic imports ke liye
2. Setter method naam meaningful rakho (setUserService, not setService)
3. XML property name exactly setter se match hona chahiye
4. pom.xml mein `spring-context` dependency zaroori hai (spring-core enough nahi)
5. Maven reload karna mat bhoolna!

---

**Status:** Assignment Question 1 - Method 1 ✅ COMPLETE & WORKING!

**Last Run Output:**
```
User name is: John Doe
User saved successfully
```

---

# Spring Dependency Injection - Method 2: XML with Constructor Injection

## Assignment Question 1 - Part 2

**Question:** UserService needs to be injected into UserController using Spring DI with XML configuration and Constructor Injection.

---

## Completed Implementation (Constructor Injection)

### Files Used:
1. `src/main/java/Project2/UserService1.java`
2. `src/main/java/Project2/UserController1.java`
3. `src/main/resources/config1.xml`
4. `src/main/java/Main1.java`

---

## Key Differences vs Setter Injection

| Topic | Setter Injection | Constructor Injection |
|------|------------------|-----------------------|
| XML Tag | `<property>` | `<constructor-arg>` |
| Method | `setUserService(...)` | `UserController(UserService ...)` |
| When Injected | After object creation | During object creation |
| Best Practice | OK | Preferred |

---

## How It Works (Constructor Injection)

### Step 1: Spring reads XML and creates beans
```xml
<bean id="service1" class="Project2.UserService1"/>
<bean id="controller1" class="Project2.UserController1">
    <constructor-arg ref="service1"/>
</bean>
```

### Step 2: Spring calls constructor with dependency
```java
public UserController1(UserService1 service1) {
    this.service1 = service1;
}
```

### Step 3: Bean is ready to use
```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("config1.xml");
UserController1 controller1 = ctx.getBean("controller1", UserController1.class);
```

---

## Notes / Doubts Cleared (Method 2)

### 1) `<constructor-arg>` tag kyun use hota hai?
- Constructor injection mein dependency constructor ke parameter se aati hai.
- XML mein `<constructor-arg ref="..."/>` se Spring ko batate hain ki konsa bean pass karna hai.

### 2) Setter method ki zarurat kyun nahi?
- Constructor hi dependency set kar deta hai.
- Isliye `setUserService()` method optional ho jata hai (common practice: remove or avoid it).

### 3) Constructor injection ka fayda?
- Object **immutable** ban sakta hai (field `final` ho sakti hai).
- Dependency **must** be present at creation time.

### 4) `Main1.java` mein kuch change kyon nahi?
- Bean retrieval same hi hota hai.
- Constructor injection internal hota hai; `getBean()` usage same rehta hai.

---

## Optional Improvement (Not required for assignment)

- `UserController1` constructor mein service method calls na karna better practice hai.
- Better approach: constructor sirf dependency set kare, business calls method se hon.

---

**Status:** Assignment Question 1 - Method 2 ✅ COMPLETE & WORKING!

**Last Run Output:**
```
User name is: John Doe
User saved successfully
```

---

# Spring Dependency Injection - Method 3: Annotation-Based (Spring Boot)

## Assignment Question 1 - Part 3

**Question:** UserService needs to be injected into UserController using Spring DI with annotations.

---

## Completed Implementation (Annotation-Based)

### Files Used:
1. `src/main/java/Project3/UserService3.java`
2. `src/main/java/Project3/UserController3.java`
3. `src/main/java/Main3.java`
4. `pom.xml`

---

## What Was Done (Step-by-Step)

### Step 1: Service class with `@Service`
```java
@Service
public class UserService3 {
    public void getUserName() { /* prints message */ }
    public void saveUser() { /* prints message */ }
}
```

### Step 2: Controller class with `@RestController` + constructor injection
```java
@RestController
public class UserController3 {
    private final UserService3 service3;

    @Autowired
    public UserController3(UserService3 service3) {
        this.service3 = service3;
        service3.getUserName();
        service3.saveUser();
    }
}
```

### Step 3: Spring Boot main class
```java
@SpringBootApplication
@ComponentScan("Project3")
public class Main3 {
    public static void main(String[] args) {
        ApplicationContext container3 = SpringApplication.run(Main3.class, args);
        UserController3 controller3 = container3.getBean(UserController3.class);
    }
}
```

### Step 4: `pom.xml` set to Spring Boot parent
- Use `spring-boot-starter-parent` (e.g., `3.2.5`)
- Add `spring-boot-starter` and `spring-boot-starter-web`
- Boot manages Spring versions automatically to avoid conflicts

---

## Key Concepts (Method 3)

### 1) XML ki zarurat kyun nahi?
- Annotations ka use hota hai, Spring component scanning se beans mil jate hain.
- `@Service` and `@RestController` automatically Spring beans banate hain.

### 2) `@ComponentScan("Project3")` kyun?
- Spring ko batata hai ki Project3 package scan kare.
- Wahi se `UserService3` aur `UserController3` register hote hain.

### 3) `@Autowired` constructor kyun?
- Constructor injection best practice hai.
- Dependency object create hote hi inject ho jata hai.

---

## How To Run (Method 3)

1. IntelliJ me `pom.xml` reload karo
2. `Main3.java` run karo
3. Console me output check karo

---

**Status:** Assignment Question 1 - Method 3 ✅ COMPLETE & WORKING!

**Last Run Output:**
```
User name is: John Doe
User saved successfully
```

---

*Generated on: Assignment completion*
*@hmaracollege Spring DI Assignment*
