# 🌱 Spring Bean Lifecycle - Complete Guide

> **Simple & Easy Revision Notes** - सबकुछ आसान भाषा में

---

## 📋 Table of Contents
1. [Bean Lifecycle क्या है?](#bean-lifecycle-क्या-है)
2. [Bean Scopes](#bean-scopes)
3. [Bean Lifecycle Methods - 3 Ways](#bean-lifecycle-methods---3-ways)
4. [Complete Examples](#complete-examples)
5. [Quick Revision Points](#quick-revision-points)

---

## 🎯 Bean Lifecycle क्या है?

**Simple Definition:**
- Bean = Spring Container द्वारा manage किया गया object
- Lifecycle = Bean कब बनता है, कैसे initialize होता है, और कब destroy होता है

### Bean Lifecycle Flow:

```
1. Container Start होता है
2. Bean Object बनता है (Constructor Call)
3. Dependencies Inject होती हैं (Setter/Constructor Injection)
4. Initialization Methods चलते हैं
5. Bean Use के लिए Ready है
6. Container Close होता है
7. Destruction Methods चलते हैं
```

---

## 🔄 Bean Scopes

### 1. **Singleton Scope** (Default)
- **क्या है:** पूरी application में **एक ही object** बनता है
- **कब use करें:** जब सभी जगह same object चाहिए

```java
@Component  // By default Singleton
public class Samosa {
    public Samosa() {
        System.out.println("new samosa is created");
    }
}
```

**Testing:**
```java
Samosa samosa1 = container.getBean("samosa", Samosa.class);
Samosa samosa2 = container.getBean("samosa", Samosa.class);

// Output: "new samosa is created" - सिर्फ 1 बार print होगा
// samosa1 == samosa2 → true (same object)
```

---

### 2. **Prototype Scope**
- **क्या है:** हर बार **नया object** बनता है
- **कब use करें:** जब हर request पर fresh object चाहिए

```java
@Component
@Scope("prototype")
public class Samosa {
    public Samosa() {
        System.out.println("new samosa is created");
    }
}
```

**Testing:**
```java
Samosa samosa1 = container.getBean("samosa", Samosa.class);
Samosa samosa2 = container.getBean("samosa", Samosa.class);

// Output: "new samosa is created" - 2 बार print होगा
// samosa1 != samosa2 → false (different objects)
```

---

### 3. **Other Scopes** (Web Applications)

| Scope | Description | Kab Use Karein |
|-------|-------------|----------------|
| `request` | हर HTTP request पर नया bean | Web apps में |
| `session` | हर user session में एक bean | Login systems में |
| `application` | पूरी ServletContext में एक bean | Shared resources के लिए |

```java
@Component
@Scope("request")  // Web app में use करें
public class Samosa { }
```

---

## 🔧 Bean Lifecycle Methods - 3 Ways

Bean के initialization और destruction के लिए **3 तरीके** हैं:

---

### ✅ Method 1: Interfaces (InitializingBean & DisposableBean)

**कब use करें:** Old way, अब कम use होता है

```java
@Component("car")
public class Car implements InitializingBean, DisposableBean {
    
    Engine engine;
    
    public Car() {
        System.out.println("Car is instantiated");
    }
    
    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
        System.out.println("setting engine: setter injection");
    }
    
    // ✅ Initialization: Dependencies inject होने के बाद
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet: Car initialized");
        System.out.println(engine);  // engine use करने के लिए ready
    }
    
    // ✅ Destruction: Container close होने से पहले
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy: Car bean is going to destroy");
        // Cleanup: resources close करें
    }
}
```

**Execution Order:**
```
1. Constructor → "Car is instantiated"
2. Setter/Dependencies → "setting engine: setter injection"
3. afterPropertiesSet() → "afterPropertiesSet: Car initialized"
4. Bean Ready for Use ✅
5. destroy() → "destroy: Car bean is going to destroy"
```

---

### ✅ Method 2: JSR Annotations (@PostConstruct & @PreDestroy)

**कब use करें:** Modern way, सबसे ज्यादा recommended ⭐

```java
@Component
public class UserDao {
    
    String connection = null;
    Scanner sc = null;
    
    public UserDao() {
        System.out.println("instantiating user dao");
    }
    
    // ✅ Initialization: Bean बनने के तुरंत बाद
    @PostConstruct
    public void init() throws Exception {
        sc = new Scanner(System.in);
        System.out.println("Enter connection:");
        connection = sc.nextLine();
        // Database connection setup करो
    }
    
    public void saveUser() {
        System.out.println("using db connection: " + connection.length());
        System.out.println("saving user");
    }
    
    // ✅ Destruction: Bean destroy होने से पहले
    @PreDestroy
    public void des() throws Exception {
        connection = null;
        sc.close();
        System.out.println("destroying connection");
        // Resources cleanup करो
    }
}
```

**Why Best?**
- ✅ Java standard (JSR-250)
- ✅ Spring interfaces पर dependent नहीं
- ✅ Clean और readable code
- ✅ किसी भी method पर लगा सकते हैं

---

### ✅ Method 3: XML/Configuration (@Bean attributes)

**कब use करें:** XML configuration या @Bean में

```java
@Configuration
public class ConfigClass {
    
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public UserDao userDao() {
        return new UserDao();
    }
}
```

```java
public class UserDao {
    
    public void init() {
        System.out.println("Init method called");
        // Setup code
    }
    
    public void cleanup() {
        System.out.println("Cleanup method called");
        // Destruction code
    }
}
```

---

## 📊 Complete Comparison

| Feature | InitializingBean/DisposableBean | @PostConstruct/@PreDestroy | @Bean(init/destroy) |
|---------|--------------------------------|----------------------------|---------------------|
| Spring Dependency | ✅ Yes | ❌ No (JSR-250) | ✅ Yes |
| Readability | 😐 Medium | 😊 Best | 😐 Medium |
| Flexibility | ❌ Interface ही implement करो | ✅ किसी भी method पर | ✅ Config में specify करो |
| **Recommended** | ❌ Old way | ✅ **Best Practice** | 😊 Good for @Bean |

---

## 💡 Complete Examples

### Example 1: Singleton vs Prototype

```java
// Test करें - Samosa.java में
@Component
// @Scope("prototype")  // Comment/Uncomment करके देखें
public class Samosa {
    public Samosa() {
        System.out.println("new samosa is created");
    }
}
```

```java
// App.java में
Samosa s1 = container.getBean("samosa", Samosa.class);
Samosa s2 = container.getBean("samosa", Samosa.class);

System.out.println(s1);  // hashcode देखो
System.out.println(s2);  // hashcode देखो

// Singleton: same hashcode, "created" 1 बार
// Prototype: different hashcode, "created" 2 बार
```

---

### Example 2: Full Lifecycle with Initialization

```java
@Component("car")
public class Car implements InitializingBean, DisposableBean {
    
    @Autowired
    Engine engine;
    
    public Car() {
        System.out.println("1. Constructor: Car object created");
    }
    
    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
        System.out.println("2. Dependency Injection: Engine injected");
    }
    
    @Override
    public void afterPropertiesSet() {
        System.out.println("3. After Properties Set: Car ready to use");
        System.out.println("   Engine: " + engine);
    }
    
    public void start() {
        engine.startEngine();
        System.out.println("4. Business Method: Car started");
    }
    
    @Override
    public void destroy() {
        System.out.println("5. Destroy: Car cleanup");
    }
}
```

**Output Flow:**
```
1. Constructor: Car object created
2. Dependency Injection: Engine injected
3. After Properties Set: Car ready to use
   Engine: com.lifecycle.concepts.Engine@12345678
4. Business Method: Car started  ← User calls this
5. Destroy: Car cleanup  ← On container.close()
```

---

### Example 3: Database Connection Lifecycle

```java
@Component
public class UserDao {
    
    String connection = null;
    
    // 1. Object बनता है
    public UserDao() {
        System.out.println("UserDao created");
    }
    
    // 2. Connection setup (Init)
    @PostConstruct
    public void init() {
        connection = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Connection established: " + connection);
    }
    
    // 3. Business operations
    public void saveUser() {
        if(connection != null) {
            System.out.println("Saving user using: " + connection);
        }
    }
    
    // 4. Connection cleanup (Destroy)
    @PreDestroy
    public void cleanup() {
        connection = null;
        System.out.println("Connection closed");
    }
}
```

---

## ⚡ Quick Revision Points

### 🔹 Bean Scopes

```
Singleton (Default)
├── पूरी app में 1 object
├── Performance अच्छी
└── Stateless beans के लिए

Prototype
├── हर बार नया object
├── Memory ज्यादा use होती
└── Stateful beans के लिए

Request/Session/Application
└── Web apps में use करें
```

---

### 🔹 Lifecycle Methods - कब use करें?

```java
// ✅ Modern Way (Recommended)
@PostConstruct - Bean बनने के बाद init
@PreDestroy    - Bean destroy होने से पहले cleanup

// 😐 Old Way (Still works)
InitializingBean.afterPropertiesSet() - Init
DisposableBean.destroy()              - Cleanup

// 🏗️ Configuration Way
@Bean(initMethod="init", destroyMethod="cleanup")
```

---

### 🔹 Complete Bean Lifecycle Order

```
📦 Container Start
    ↓
1️⃣ Constructor Call
    ↓
2️⃣ Dependency Injection (@Autowired)
    ↓
3️⃣ @PostConstruct / afterPropertiesSet()
    ↓
4️⃣ Bean Ready for Use ✅
    ↓
    ... Application Running ...
    ↓
5️⃣ @PreDestroy / destroy()
    ↓
📦 Container Close
```

---

### 🔹 Important Points to Remember

1. **Singleton Default:**
   - `@Component` → Singleton by default
   - Memory efficient

2. **Prototype New Object:**
   - `@Scope("prototype")` → हर बार नया
   - Destroy methods नहीं चलते (Spring manage नहीं करता)

3. **Init Methods:**
   - Dependencies के बाद चलते हैं
   - Resource setup के लिए (DB, files, etc.)

4. **Destroy Methods:**
   - `container.close()` पर चलते हैं
   - Resource cleanup के लिए

5. **@PostConstruct Best:**
   - Java standard
   - Spring version से independent
   - Clean code

---

## 🎓 Practice Questions

### Q1: Output क्या होगा?

```java
@Component
public class Sample {
    public Sample() { System.out.println("A"); }
    
    @PostConstruct
    public void init() { System.out.println("B"); }
    
    @PreDestroy
    public void cleanup() { System.out.println("C"); }
}

// Main
Sample s = container.getBean(Sample.class);
container.close();
```

**Answer:** `A` → `B` → `C`

---

### Q2: Singleton vs Prototype में क्या अंतर?

```java
@Component
@Scope("singleton")  // vs @Scope("prototype")
public class Test { }

Test t1 = container.getBean(Test.class);
Test t2 = container.getBean(Test.class);
```

**Answer:**
- **Singleton:** `t1 == t2` (same object)
- **Prototype:** `t1 != t2` (different objects)

---

### Q3: सही order बताओ?

```
A) Dependency Injection
B) Constructor
C) @PostConstruct
D) destroy()
```

**Answer:** B → A → C → D

---

## 📝 Common Mistakes to Avoid

❌ **Mistake 1:** Prototype beans में @PreDestroy expect करना
```java
@Component
@Scope("prototype")
public class Test {
    @PreDestroy  // ❌ Prototype में नहीं चलेगा
    public void cleanup() { }
}
```

❌ **Mistake 2:** Init method में dependencies use करना (Constructor में)
```java
@Autowired
Engine engine;

public Car() {
    engine.start();  // ❌ Null होगा! @PostConstruct में करो
}
```

✅ **Correct:**
```java
@PostConstruct
public void init() {
    engine.start();  // ✅ Dependencies inject हो चुकी हैं
}
```

❌ **Mistake 3:** container.close() भूल जाना
```java
// ❌ Destroy methods नहीं चलेंगे
AnnotationConfigApplicationContext container = new ...;
// कुछ work करो
// Program end → Destroy methods skip!

// ✅ हमेशा close करो
container.close();
```

---

## 🚀 Best Practices

1. ✅ **@PostConstruct/@PreDestroy use करो** (Modern & Clean)
2. ✅ **Default Singleton use करो** (जब तक prototype की जरूरत न हो)
3. ✅ **Init में resource setup करो** (DB, files, connections)
4. ✅ **Destroy में cleanup करो** (Memory leaks avoid करने के लिए)
5. ✅ **हमेशा container.close() करो** (या try-with-resources)

---

## 📚 Summary - One Page Revision

```
BEAN LIFECYCLE
==============

SCOPES:
-------
Singleton (Default) → 1 object पूरी app में
Prototype           → हर call पर new object

LIFECYCLE ORDER:
---------------
1. Constructor
2. Dependency Injection
3. @PostConstruct (Init)
4. Bean Ready
5. @PreDestroy (Cleanup)
6. Container Close

INIT/DESTROY METHODS:
--------------------
@PostConstruct/@PreDestroy  ← Best ⭐
InitializingBean/Disposable ← Old way
@Bean(init/destroy)         ← Config way

USE CASES:
----------
Init    → DB connection, file open, resource setup
Destroy → Close connections, cleanup, free memory

REMEMBER:
---------
✅ Singleton = default, memory efficient
✅ @PostConstruct में dependencies ready होती हैं
✅ Prototype beans में destroy नहीं चलता
✅ container.close() करना जरूरी है
```

---

## 🎯 Final Tips for Revision

1. **Code खुद लिखो:** README पढ़ने के साथ code try करो
2. **Outputs देखो:** Singleton vs Prototype का difference console में देखो
3. **Lifecycle Flow याद रखो:** Constructor → Injection → Init → Use → Destroy
4. **@PostConstruct prefer करो:** Modern और clean
5. **Practice करो:** Different scopes try करो

---

**Happy Learning! 🎉**

*Made with ❤️ for easy revision*
