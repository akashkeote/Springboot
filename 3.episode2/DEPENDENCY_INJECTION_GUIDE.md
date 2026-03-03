# Spring Dependency Injection - Complete Guide

## Three Types of Dependency Injection

### 1️⃣ SETTER INJECTION

**What it is:** Dependency injected through a setter method

**Code Example:**
```java
@Component("carSetter")
public class CarSetterInjection {
    private Engine engine;  // NOT final

    public CarSetterInjection() { }

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;  // Injected here
    }
}
```

**Pros:**
- ✅ Optional dependencies
- ✅ Flexible - can change after creation
- ✅ Can use @Qualifier for specific beans
- ✅ Backward compatible

**Cons:**
- ❌ No immutability
- ❌ Object may not have dependency after creation (NPE risk)
- ❌ Order of initialization unclear
- ❌ Can't enforce required dependencies

**When to use:** Optional dependencies, when you need flexibility

---

### 2️⃣ FIELD INJECTION

**What it is:** Dependency injected directly into a field

**Code Example:**
```java
@Component("carField")
public class CarFieldInjection {
    
    @Autowired
    private EngineFieldInjection engine;  // Injected here directly

    public CarFieldInjection() { }
}
```

**Pros:**
- ✅ Concise code - minimal boilerplate
- ✅ Easy to read
- ✅ Supports optional dependencies with `@Autowired(required = false)`

**Cons:**
- ❌ Hard to test (needs reflection or PowerMock)
- ❌ NullPointerException risk if Spring doesn't initialize
- ❌ No immutability
- ❌ Hidden dependencies (not visible in constructor)
- ❌ Circular dependency issues
- ❌ Can't be used with `final` keyword

**When to use:** NOT RECOMMENDED FOR PRODUCTION - Only for simple cases

---

### 3️⃣ CONSTRUCTOR INJECTION ⭐ BEST PRACTICE

**What it is:** Dependency injected through constructor parameter

**Code Example:**
```java
@Component("carConstructor")
public class CarConstructorInjection {
    
    private final EngineConstructorInjection engine;  // Can be final!

    @Autowired
    public CarConstructorInjection(EngineConstructorInjection engine) {
        this.engine = engine;  // Injected here
    }
}
```

**Pros:**
- ✅ **Immutability possible** (use `final` keyword)
- ✅ **Fail-fast** - exception thrown if dependency missing
- ✅ **Easy to test** - pass mock object to constructor
- ✅ Clear visibility of dependencies
- ✅ Detects circular dependencies early
- ✅ No NullPointerException risk
- ✅ Spring best practice & recommended by most frameworks

**Cons:**
- ❌ More verbose code
- ❌ Complex constructors for many dependencies

**When to use:** DEFAULT CHOICE - Always use this for production code

---

## Comparison Table

| Feature | Setter | Field | Constructor |
|---------|--------|-------|-------------|
| Easy to Test | Medium | ❌ Hard | ✅ Easy |
| Immutability | ❌ No | ❌ No | ✅ Yes |
| Code Clarity | Good | Better | ✅ Best |
| Optional Dependencies | ✅ Yes | ✅ Yes | No |
| Boilerplate | Some | Minimal | More |
| Circular Dependencies | Possible | Possible | ✅ Detected |
| NullPointerException Risk | ⚠️ Yes | ⚠️ Yes | ❌ No |
| Performance | Good | ✅ Best | Good |

---

## Real World Example

```java
// ❌ BAD - Field Injection
@Component
public class UserService {
    @Autowired
    private UserRepository userRepo;  // Hard to test!
    
    public User getUser(Long id) {
        return userRepo.findById(id);
    }
}

// ⚠️ OK - Setter Injection
@Component
public class UserService {
    private UserRepository userRepo;
    
    @Autowired
    public void setUserRepository(UserRepository repo) {
        this.userRepo = repo;
    }
    
    public User getUser(Long id) {
        return userRepo.findById(id);
    }
}

// ✅ BEST - Constructor Injection
@Component
public class UserService {
    private final UserRepository userRepo;
    
    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    public User getUser(Long id) {
        return userRepo.findById(id);
    }
}
```

---

## Testing Example

```java
// How Constructor Injection makes testing easy
@Test
public void testGetUser() {
    // Easy - just pass mock to constructor
    UserRepository mockRepo = mock(UserRepository.class);
    UserService service = new UserService(mockRepo);
    
    when(mockRepo.findById(1L)).thenReturn(new User(1L, "John"));
    User user = service.getUser(1L);
    
    assertEquals("John", user.getName());
}

// vs Field Injection - requires reflection/PowerMock
// Much harder! ❌
```

---

## Best Practices

### ✅ DO:

1. **Use Constructor Injection by default**
   ```java
   public MyClass(Dependency dep) {
       this.dep = dep;
   }
   ```

2. **Make fields final for immutability**
   ```java
   private final Dependency dep;
   ```

3. **Inject interfaces, not concrete classes**
   ```java
   private final UserRepository repo;  // Interface ✅
   // NOT private final UserRepositoryImpl repo;  ❌
   ```

4. **Fail fast with required dependencies**
   - Constructor injection ensures this automatically

### ❌ DON'T:

1. **Don't use field injection in production**
2. **Don't mix injection types** - be consistent
3. **Don't inject too many dependencies** (sign of SRP violation)
4. **Don't use @Autowired(required = false) carelessly** - can cause NPE

---

## When Each Type Is Used

| Scenario | Type | Reason |
|----------|------|--------|
| Required dependency | Constructor | Fail-fast, immutability |
| Optional dependency | Setter | Can be skipped |
| Simple prototype/tutorial | Field | Quick & easy |
| Framework internal | Field/Setter | Flexibility needed |
| Production code | Constructor | Best practice |

---

## How to Run Examples

```bash
# Compile
mvn clean compile

# Run all injection examples
mvn exec:java -Dexec.mainClass="com.episode.second.episode2.MainAllInjectionExamples"

# Or run from IDE - right-click MainAllInjectionExamples.java → Run
```

---

## Summary

🏆 **Constructor Injection** is the clear winner for production code:
- Makes dependencies explicit
- Enables immutability
- Easy to unit test
- Prevents NullPointerException
- Recommended by Spring team & best practices

Use **Setter Injection** only for optional dependencies.

Avoid **Field Injection** in production code.

---

*Created for Episode 2 - Spring Dependency Injection Deep Dive*
