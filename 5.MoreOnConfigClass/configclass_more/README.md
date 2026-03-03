# Project 5: More On Config Class

## 📚 Learning Objectives

This project demonstrates advanced Spring configuration concepts:
- **@Configuration** class as Java-based config (replaces XML)
- **@Bean** annotation for manual bean creation
- **@ComponentScan** for auto-detecting components
- **Setter Injection** with @Autowired

---

## 🎯 Core Concepts Explained

### 1. @Configuration - What is it?

```java
@Configuration
@ComponentScan(basePackages = {"package1", "package2"})
public class ConfigClass {
    // This class tells Spring where to find beans
}
```

**What it means:**
- `@Configuration` marks this class as Spring's configuration center
- `@ComponentScan` tells Spring where to look for `@Component` classes
- It's the Java replacement for `<beans>` in XML

---

### 2. @Bean vs @Component

#### Using @Component (Auto-detection)
```java
@Component
public class Student {
    // Spring automatically creates a bean for this class
}

@Component("teacher")
public class Teacher {
    // Bean name: "teacher" (custom)
}
```

#### Using @Bean (Manual creation)
```java
@Configuration
public class ConfigClass {
    @Bean(name = "teacher")
    public Teacher getTeacher() {
        System.out.println("Creating Teacher manually");
        return new Teacher();  // We control the creation!
    }
}
```

**When to use which?**
- **@Component**: Simple beans, auto-detection needed
- **@Bean**: Need control over initialization, third-party classes

---

### 3. Dependency Injection with @Autowired

**The Problem:**
```java
public class Car {
    Engine engine;
    
    public Car() {
        this.engine = new Engine();  // ❌ Tight Coupling!
    }
}
```

**The Solution (Setter Injection):**
```java
public class Car {
    Engine engine;  // Declared but not initialized
    
    @Autowired  // "Spring, inject an Engine here"
    public void setEngine(Engine engine) {
        this.engine = engine;  // ✅ Loose Coupling!
    }
}
```

Spring finds the Engine bean and calls this method automatically.

---

## 📂 Project Structure

```
5.MoreOnConfigClass/
├── config/
│   └── ConfigClass.java          ← Main configuration with @Configuration
├── student/
│   ├── Student.java              ← @Component (auto-detected)
│   └── Teacher.java              ← @Component + @Bean (bean priority)
├── concepts/
│   ├── Car.java                  ← Has @Autowired setter for Engine
│   └── Engine.java               ← Injected into Car
└── App.java                       ← Main entry point
```

---

## 🔍 Detailed Code Walkthrough

### ConfigClass.java
```java
@Configuration
@ComponentScan(basePackages = { 
    "com.fifth.more_on_config.configclass_more.student",
    "com.fifth.more_on_config.configclass_more.concepts"
})
public class ConfigClass {
    
    @Bean(name = "teacher")
    public Teacher getTeacher() {
        System.out.println("new bean manual in config class");
        return new Teacher();
    }
}
```

**Key Points:**
- `@ComponentScan` finds all `@Component` in those packages
- `@Bean` creates Teacher manually (overrides the @Component one)
- @Bean has higher priority than @Component

---

### Student.java
```java
@Component
public class Student {
    public Student() {
        System.out.println("Creating Student Object");
    }
    
    public void show() {
        System.out.println("I am student");
    }
}
```

Simple component that gets auto-detected and bean created.

---

### Teacher.java
```java
@Component("teacher")  // Bean name: "teacher"
public class Teacher {
    public Teacher() {
        System.out.println("Creating Teacher Object");
    }
    
    public void show() {
        System.out.println("I am teacher");
    }
}
```

**Important:** 
- @ComponentScan finds this
- But @Bean in ConfigClass overrides it (same name "teacher")
- So you get the @Bean version!

---

### Car.java (The interesting one!)
```java
@Component("car")
public class Car {
    Engine engine;  // ← Will be injected
    
    public Car() {
        System.out.println("creating car object");
    }
    
    // Setter Injection
    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
        System.out.println("setting engine: setter injection");
    }
    
    public void start() {
        engine.startEngine();
        System.out.println("car started");
    }
}
```

**How it works:**
1. Spring creates `Car()` → constructor called
2. Spring scans for @Autowired methods
3. Finds `setEngine(Engine engine)` with @Autowired
4. Finds an Engine bean (named "engine1")
5. Calls `setEngine(engine1)` automatically
6. Now Car has Engine! ✅

---

### Engine.java
```java
@Component("engine1")
public class Engine {
    public Engine() {
        System.out.println("creating engine object");
    }
    
    public void startEngine() {
        System.out.println("engine started...");
    }
}
```

---

### App.java
```java
public class App {
    public static void main(String[] args) {
        // Create Spring container using ConfigClass
        ApplicationContext container = 
            new AnnotationConfigApplicationContext(ConfigClass.class);
        
        // Get the car bean (Engine is already injected)
        Car car = container.getBean("car", Car.class);
        
        // Car's engine is ready to use!
        car.start();
    }
}
```

---

## 🎬 Execution Flow

```
1. new AnnotationConfigApplicationContext(ConfigClass.class)
   ↓
2. ConfigClass is loaded
   - @ComponentScan finds all @Component in specified packages
   ↓
3. Auto-detected beans created:
   - Student: Creating Student Object
   - Teacher: Creating Teacher Object (from @Component)
   - Car: creating car object
   - Engine: creating engine object
   ↓
4. Manual beans created by @Bean:
   - Teacher: Creating Teacher Object (from @Bean)
   - This OVERWRITES the @Component one!
   ↓
5. @Autowired injection:
   - Car's setEngine() found
   - Engine bean injected
   - "setting engine: setter injection"
   ↓
6. In main():
   - car.start() called
   - "engine started..."
   - "car started"
```

---

## ❓ Common Questions

### Q: What's the difference between @Bean and @Component?

| Aspect | @Component | @Bean |
|--------|-----------|-------|
| Usage | On class | On method in @Configuration |
| Auto-detection | Yes | No (must call method) |
| Initialization | Constructor only | Can add custom logic |
| Best for | Simple beans | Complex initialization |
| Third-party classes | No | Yes |

---

### Q: What does @ComponentScan do?

```java
@ComponentScan(basePackages = {"com.package1", "com.package2"})
```

This tells Spring:
- Look in these packages for `@Component`, `@Service`, `@Repository`, etc.
- Create beans for all of them
- Register them in the ApplicationContext

Without this, Spring won't know where your components are!

---

### Q: Why Setter Injection over Constructor Injection?

**Constructor Injection:**
```java
public Car(Engine engine) {  // Required dependency
    this.engine = engine;
}
```
✅ Immutability (can use final)  
✅ All dependencies explicit in constructor  

**Setter Injection:**
```java
@Autowired
public void setEngine(Engine engine) {  // Optional dependency
    this.engine = engine;
}
```
✅ Optional dependencies  
✅ Flexibility  
⚠️ Can have null values initially  

---

### Q: What if there are multiple beans of the same type?

This is a problem:
```java
@Component
public class CocaCola implements Drink { }

@Component
public class Pepsi implements Drink { }

@Autowired
Drink drink;  // ❌ ERROR: Which Drink should I inject?
```

**Solution:** Use **@Qualifier**!  
That's what Project 6 is all about! 👇

---

## 🚀 How to Run

```bash
cd 5.MoreOnConfigClass/configclass_more

# Compile
mvn clean compile

# Run
mvn exec:java -Dexec.mainClass="com.fifth.more_on_config.configclass_more.App"

# Or in IDE: Right-click App.java → Run
```

---

## ✅ Knowledge Checklist

- [ ] Understand what @Configuration does
- [ ] Know difference between @Component and @Bean
- [ ] Understand how @ComponentScan works
- [ ] Know how @Autowired works with setter injection
- [ ] Understand bean creation priority (@Bean > @Component)
- [ ] Can trace the execution flow manually

If all checked, you're ready for Project 6! 🎉

---

## 📌 Next: Project 6 - @Qualifier

**What you learned here:**
- Single bean per type

**What you'll learn next:**
- Multiple beans of same type
- How to choose which bean to inject
- @Qualifier vs @Primary annotations

Let's go! 🚀
