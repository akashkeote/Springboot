# Episode 2 - Spring Dependency Injection Complete Guide

## 🎯 Overview

This project demonstrates all **3 types of Spring Dependency Injection** with separate examples, each with their own:
- ✅ Main class
- ✅ Config file
- ✅ Package structure

Perfect for learning DI patterns!

---

## 📚 Three Injection Types

### 1️⃣ **Constructor Injection** ✅ BEST PRACTICE

**File:** `Main1ConstructorInjection.java` + `config1.xml`

**Package:** `com.episode.second.episode2.constructor`

**Run:**
```bash
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main1ConstructorInjection"
```

**How it works:**
```java
@Component("carConstructor")
public class CarConstructorInjection {
    private final EngineConstructorInjection engine;

    @Autowired
    public CarConstructorInjection(EngineConstructorInjection engine) {
        this.engine = engine;  // ✅ Injected via constructor
    }
}
```

**Pros:**
- ✅ Immutability (use `final` keyword)
- ✅ Fail-fast if dependency missing
- ✅ Easy to test (pass mock in constructor)
- ✅ Clear dependency visibility

**Cons:**
- More verbose code

**Output:**
```
═══ CONSTRUCTOR INJECTION (Best Practice) ═══
✅ Engine Type: V8 Turbo
✅ Engine HP: 500
✨ BEST PRACTICE: Use constructor for required deps!
```

---

### 2️⃣ **Setter Injection** ⚠️ FOR OPTIONAL DEPS

**File:** `Main2SetterInjection.java` + `config2.xml`

**Package:** `com.episode.second.episode2.setter`

**Run:**
```bash
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main2SetterInjection"
```

**How it works:**
```java
@Component("carSetter")
public class CarSetterInjection {
    private EngineSetterInjection engine;

    @Autowired
    public void setEngine(EngineSetterInjection engine) {
        this.engine = engine;  // ⚠️ Injected via setter
    }
}
```

**Pros:**
- Optional dependencies
- Flexible - can change after creation
- Can use @Qualifier for specific beans

**Cons:**
- ❌ No immutability
- ❌ NullPointerException risk
- ❌ No guarantee of dependency presence

**Output:**
```
═══ SETTER INJECTION (Optional Dependencies) ═══
✅ Engine Type: V8
✅ Engine HP: 300
⚠️  Use setter for OPTIONAL dependencies only!
```

---

### 3️⃣ **Field Injection** ❌ AVOID IN PRODUCTION

**File:** `Main3FieldInjection.java` + `config3.xml`

**Package:** `com.episode.second.episode2.field`

**Run:**
```bash
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main3FieldInjection"
```

**How it works:**
```java
@Component("carField")
public class CarFieldInjection {
    
    @Autowired
    private EngineFieldInjection engine;  // ❌ Direct field injection
}
```

**Pros:**
- Concise code
- Minimal boilerplate

**Cons:**
- ❌ Hard to test (needs reflection)
- ❌ Hidden dependencies
- ❌ NullPointerException risk
- ❌ No immutability

**Output:**
```
═══ FIELD INJECTION (Avoid in Production!) ═══
✅ Engine Type: V6
✅ Engine HP: 300
⛔ AVOID: Hard to test, use Constructor instead!
```

---

## 📁 Project Structure

```
3.episode2/
├── src/main/
│   ├── java/com/episode/second/episode2/
│   │   ├── Main1ConstructorInjection.java
│   │   ├── Main2SetterInjection.java
│   │   ├── Main3FieldInjection.java
│   │   │
│   │   ├── constructor/
│   │   │   ├── CarConstructorInjection.java
│   │   │   └── EngineConstructorInjection.java
│   │   │
│   │   ├── setter/
│   │   │   ├── CarSetterInjection.java
│   │   │   └── EngineSetterInjection.java
│   │   │
│   │   └── field/
│   │       ├── CarFieldInjection.java
│   │       └── EngineFieldInjection.java
│   │
│   └── resources/
│       ├── config1.xml    (Constructor config)
│       ├── config2.xml    (Setter config)
│       ├── config3.xml    (Field config)
│       └── application.properties
│
└── pom.xml
```

---

## 🔧 Config Files

Each injection type has its own config file with separate component scanning:

**config1.xml** - Constructor Injection:
```xml
<context:component-scan base-package="com.episode.second.episode2.constructor" />
```

**config2.xml** - Setter Injection:
```xml
<context:component-scan base-package="com.episode.second.episode2.setter" />
```

**config3.xml** - Field Injection:
```xml
<context:component-scan base-package="com.episode.second.episode2.field" />
```

---

## 🚀 Quick Start

Run any example from IDE:
1. Right-click `Main1ConstructorInjection.java` → Run
2. Right-click `Main2SetterInjection.java` → Run
3. Right-click `Main3FieldInjection.java` → Run

Or from terminal:
```bash
mvn clean compile

# Constructor
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main1ConstructorInjection"

# Setter
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main2SetterInjection"

# Field
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main3FieldInjection"
```

---

## 📊 Comparison Table

| Feature | Constructor ✅ | Setter ⚠️ | Field ❌ |
|---------|---|---|---|
| Immutability | ✅ Yes (final) | ❌ No | ❌ No |
| Testability | ✅ Easy | ⚠️ Medium | ❌ Hard |
| Clear Dependencies | ✅ Visible | ⚠️ Setter | ❌ Hidden |
| Required Dependencies | ✅ Enforced | ❌ Optional | ❌ Optional |
| Circular Dependency Detection | ✅ Early | ⚠️ Late | ❌ Possible issue |
| NullPointerException Risk | ❌ None | ⚠️ Yes | ⚠️ Yes |
| Production Use | ✅ YES | ⚠️ Sometimes | ❌ NO |

---

## 🎓 Learning Points

✅ How Spring manages bean lifecycle  
✅ XML-based component scanning  
✅ Three different DI approaches  
✅ When to use each injection type  
✅ Comparing DI patterns  
✅ Best practices for dependency injection  

---

## 💡 Key Takeaway

**Always use Constructor Injection by default!**

- Makes dependencies explicit
- Enables immutability
- Easy to unit test
- Recommended by Spring team
- Exception thrown immediately if dependency missing

Use Setter Injection only for optional dependencies.

Avoid Field Injection in production code.

---

## 🔗 Additional Resources

- See `SIMPLE_GUIDE.md` for quick reference
- See `DEPENDENCY_INJECTION_GUIDE.md` for detailed guide
- Run the examples to see DI in action!

---

## 📝 Technologies

- **Java 25** - Latest Java version
- **Spring Boot 4.0.3** - Latest Spring Boot
- **Spring Framework** - XML-based DI
- **Maven** - Build & dependency management

---

*Episode 2 - Spring Dependency Injection Deep Dive Tutorial*
