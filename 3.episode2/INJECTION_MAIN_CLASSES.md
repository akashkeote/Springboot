# Episode 2 - Dependency Injection Examples

## Three Main Classes to Run

### 1. Main1ConstructorInjection ✅ BEST PRACTICE
```bash
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main1ConstructorInjection"
```
**What it shows:**
- Dependency injected through constructor parameter
- Field is immutable (final)
- Fail-fast if dependency missing
- Easy to test

**Output:**
```
╔════════════════════════════════════════════╗
║  CONSTRUCTOR INJECTION EXAMPLE             ║
║  (Best Practice - Use This!)              ║
╚════════════════════════════════════════════╝

📦 Retrieving CarConstructorInjection bean...

✅ Bean Retrieved Successfully!
   Car: CarConstructorInjection [engine=...]
   Engine Type: V8 Turbo
   Engine HP: 500
```

---

### 2. Main2SetterInjection ⚠️ USE FOR OPTIONAL DEPS
```bash
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main2SetterInjection"
```
**What it shows:**
- Dependency injected through setter method
- Can be changed after object creation
- Good for optional dependencies
- More flexible than constructor

**Output:**
```
╔════════════════════════════════════════════╗
║  SETTER INJECTION EXAMPLE                  ║
║  (Good for Optional Dependencies)         ║
╚════════════════════════════════════════════╝

📦 Retrieving CarSetterInjection bean...

✅ Bean Retrieved Successfully!
   Car: CarSetterInjection [engine=...]
   Engine Type: V8
```

---

### 3. Main3FieldInjection ⛔ AVOID IN PRODUCTION
```bash
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main3FieldInjection"
```
**What it shows:**
- Dependency injected directly to field
- Minimal boilerplate
- Hard to test
- Hidden dependencies

**Output:**
```
╔════════════════════════════════════════════╗
║  FIELD INJECTION EXAMPLE                   ║
║  (Avoid in Production!)                   ║
╚════════════════════════════════════════════╝

📦 Retrieving CarFieldInjection bean...

✅ Bean Retrieved Successfully!
   Car: CarFieldInjection [engine=...]
   Engine Type: V6
```

---

## Quick Comparison

| Feature | Constructor ✅ | Setter ⚠️ | Field ❌ |
|---------|---|---|---|
| **Immutability** | ✅ Yes (final) | ❌ No | ❌ No |
| **Testability** | ✅ Easy | ⚠️ Medium | ❌ Hard |
| **Clear Deps** | ✅ Visible | ⚠️ Hidden | ❌ Hidden |
| **Required Deps** | ✅ Enforced | ❌ No | ❌ No |
| **Code Length** | ⚠️ More | ✅ Less | ✅ Minimal |
| **Production Use** | ✅ YES | ⚠️ Sometimes | ❌ NO |

---

## How to Run All Three

**Option 1: Run from IDE**
- Right-click `Main1ConstructorInjection.java` → Run
- Right-click `Main2SetterInjection.java` → Run
- Right-click `Main3FieldInjection.java` → Run

**Option 2: Terminal**
```bash
cd c:\Users\AkashK\IdeaProjects
mvn clean compile

# Run Constructor Injection
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main1ConstructorInjection"

# Run Setter Injection
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main2SetterInjection"

# Run Field Injection
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.Main3FieldInjection"
```

---

## Key Takeaways

### 🏆 Constructor Injection is BEST
Use this for **all required dependencies** in production code.

```java
@Component
public class UserService {
    private final UserRepository repo;
    
    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }
}
```

### ⚠️ Setter Injection is OK for Optional
Use only when dependency **is optional**.

```java
@Component
public class UserService {
    private UserRepository repo;
    
    @Autowired(required = false)
    public void setRepository(UserRepository repo) {
        this.repo = repo;
    }
}
```

### ❌ Field Injection - AVOID
Only in tutorials/learning projects.

```java
// Don't do this in production!
@Component
public class UserService {
    @Autowired
    private UserRepository repo;
}
```

---

*Created for Spring Framework Tutorial - Episode 2*
