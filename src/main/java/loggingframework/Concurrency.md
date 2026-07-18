# Logger Framework - Concurrency Improvements

## 1. Thread-safe LogManager
Use `ConcurrentHashMap` instead of `HashMap` to safely create and retrieve loggers concurrently.

```java
private final ConcurrentHashMap<String, Logger> logs = new ConcurrentHashMap<>();
```

---

## 2. Thread-safe Appenders List
Use `CopyOnWriteArrayList` instead of `ArrayList` to avoid `ConcurrentModificationException` while iterating and modifying appenders concurrently.

```java
private final List<LogAppender> appenders = new CopyOnWriteArrayList<>();
```

---

## 3. Synchronize FileAppender
Synchronize the `append()` method (or use a `ReentrantLock`) to prevent multiple threads from corrupting the log file while writing.

```java
@Override
public synchronized void append(LogMessage message) {
    ...
}
```

---

## 4. Make Logger Level Visible Across Threads
Declare the logger's log level as `volatile` so that changes made by one thread are immediately visible to others.

```java
private volatile LogLevel level;
```