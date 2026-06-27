# 10 线程同步、死锁、线程池与 JUC

## 材料来源

- `课时3_线程同步与通信.pdf`
- `课时4_死锁线程池与综合实战.pdf`

## 本讲复习目标

- 理解竞争条件、临界区、原子操作和互斥。
- 掌握 `synchronized` 方法和代码块。
- 掌握 `wait()`、`notify()`、`notifyAll()` 与生产者-消费者模式。
- 理解 `volatile` 的适用范围。
- 掌握死锁四个必要条件和预防策略。
- 掌握 `ReentrantLock`、线程池、`AtomicInteger` 和 JUC 基础工具。

## 考点清单

- 竞争条件：多个线程访问共享资源，至少一个线程写入，最终结果依赖执行顺序。
- `count++` 不是原子操作，包含读、加、写。
- 临界区必须互斥，同一时刻只允许一个线程进入。
- `synchronized` 每个对象有隐式 Monitor 锁。
- `synchronized` 实例方法锁是 `this`；静态方法锁是 `Class` 对象。
- `wait()` 会释放锁并进入等待状态。
- `wait()`、`notify()`、`notifyAll()` 属于 `Object`，必须在同步块或同步方法中调用。
- 条件等待用 `while`，不用 `if`。
- 生产者-消费者使用共享缓冲区解耦生产和消费。
- `volatile` 保证可见性和有序性，不保证复合操作原子性。
- 死锁四条件：互斥、占有并等待、非抢占、循环等待。
- `ReentrantLock` 必须在 `finally` 中 `unlock()`。
- 线程池复用线程、控制并发、管理任务队列。
- `AtomicInteger` 基于 CAS，适合简单数值原子操作。

## 竞争条件

银行账户并发取款是材料中的典型问题：两个线程同时读到余额 1000，各自判断可以取 800，然后都写回，结果余额错误。

```java
class BankAccount {
    private int balance = 1000;

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("取款成功，余额：" + balance);
        } else {
            System.out.println("余额不足");
        }
    }
}
```

问题根源：

- 共享资源：多个线程访问同一个对象或变量。
- 操作非原子：`读-改-写` 被拆成多步。
- 执行交叉：线程调度导致操作穿插。
- 结果不确定：每次运行可能不同。

相关概念：

| 概念 | 含义 |
| --- | --- |
| 临界区 | 访问共享资源的代码段 |
| 原子操作 | 要么完整执行，要么完全不执行 |
| 互斥 | 同一时刻只允许一个线程进入临界区 |

解决核心：让临界区串行访问。

## synchronized 方法

```java
class BankAccount {
    private int balance = 1000;

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName()
                + " 取款成功，余额：" + balance);
        } else {
            System.out.println(Thread.currentThread().getName()
                + " 余额不足，当前余额：" + balance);
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}
```

同一个对象的所有 `synchronized` 实例方法共用同一把 `this` 锁，因此两个线程不能同时进入同一对象的两个同步实例方法。

## synchronized 代码块

代码块可以指定任意锁对象，粒度更细。

```java
class ShoppingCart {
    private List<String> items = new ArrayList<>();
    private int totalPrice = 0;
    private final Object itemLock = new Object();
    private final Object priceLock = new Object();

    public void addItem(String item) {
        synchronized (itemLock) {
            items.add(item);
        }
    }

    public void updatePrice(int delta) {
        synchronized (priceLock) {
            totalPrice += delta;
        }
    }
}
```

不同锁对象保护不同数据，可以提升并发性能。锁对象通常使用 `private final Object` 字段。

| 对比项 | 同步方法 | 同步代码块 |
| --- | --- | --- |
| 锁对象 | `this` 或 `Class` | 任意指定对象 |
| 范围 | 整个方法体 | 指定代码片段 |
| 粒度 | 粗 | 细 |
| 代码 | 简单 | 更灵活 |

## wait / notify / notifyAll

仅靠加锁不能表达“等待某个条件满足后再继续”。线程通信使用 `wait()`、`notify()`、`notifyAll()`。

| 方法 | 作用 |
| --- | --- |
| `wait()` | 当前线程释放锁，进入等待状态 |
| `notify()` | 随机唤醒一个等待该对象的线程 |
| `notifyAll()` | 唤醒所有等待该对象的线程 |

三者都属于 `Object`，必须在 `synchronized` 内调用，否则抛 `IllegalMonitorStateException`。被唤醒后不会立即执行，还要重新竞争锁。

```java
class MessageBox {
    private String message;
    private boolean hasMessage = false;

    public synchronized String receive() throws InterruptedException {
        while (!hasMessage) {
            wait();
        }
        hasMessage = false;
        notifyAll();
        return message;
    }

    public synchronized void send(String msg) throws InterruptedException {
        while (hasMessage) {
            wait();
        }
        this.message = msg;
        hasMessage = true;
        notifyAll();
    }
}
```

条件检查用 `while` 而不是 `if`，防止虚假唤醒或条件被其他线程改变。

## 生产者-消费者模式

生产者负责产生数据，消费者负责处理数据，共享缓冲区用于解耦。缓冲区满时生产者等待，缓冲区空时消费者等待。

```java
class BoundedBuffer {
    private final int[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;
    private final int capacity;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
    }

    public synchronized void put(int item) throws InterruptedException {
        while (count == capacity) {
            System.out.println("缓冲区已满，生产者等待...");
            wait();
        }
        buffer[in] = item;
        in = (in + 1) % capacity;
        count++;
        System.out.println("生产：" + item + " 数量：" + count);
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (count == 0) {
            System.out.println("缓冲区为空，消费者等待...");
            wait();
        }
        int item = buffer[out];
        out = (out + 1) % capacity;
        count--;
        System.out.println("消费：" + item + " 数量：" + count);
        notifyAll();
        return item;
    }
}
```

```java
class Producer implements Runnable {
    private BoundedBuffer buffer;

    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                buffer.put(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.take();
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
```

## volatile

`volatile` 是比 `synchronized` 更轻量的同步机制，适合状态标志。

```java
class Worker {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            // work
        }
    }

    public void stop() {
        running = false;
    }
}
```

对比：

| 能力 | `volatile` | `synchronized` |
| --- | --- | --- |
| 可见性 | 有 | 有 |
| 有序性 | 有 | 有 |
| 原子性 | 不保证复合操作 | 保证临界区 |
| 互斥性 | 无 | 有 |
| 适用 | 状态标志 | 复合操作 |

`volatile` 不能让 `count++` 线程安全。

## 死锁

死锁定义：两个或多个线程相互等待对方释放锁，且没有任何线程能继续执行。

四个必要条件：

1. 互斥：资源同一时刻只能被一个线程占用。
2. 占有并等待：线程已持有资源，还等待更多资源。
3. 非抢占：已获得资源不能被强制剥夺。
4. 循环等待：线程之间形成环形等待链。

示例：

```java
public class DeadlockDemo {
    static Object lockA = new Object();
    static Object lockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("T1: 持有A，等待B");
                sleep(100);
                synchronized (lockB) {
                    System.out.println("T1: 同时持有A和B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("T2: 持有B，等待A");
                sleep(100);
                synchronized (lockA) {
                    System.out.println("T2: 同时持有A和B");
                }
            }
        });

        t1.start();
        t2.start();
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

材料给出的检测方式：运行后可用 `jstack <pid>` 查看 `DEADLOCK` 标记。

预防策略：

- 固定加锁顺序，打破循环等待。
- 使用 `tryLock()` 超时，打破无限等待。
- 缩小锁持有范围，减少占有并等待。

## ReentrantLock

`Lock` 接口比 `synchronized` 更灵活，常用实现是 `ReentrantLock`。

| 特性 | `synchronized` | `ReentrantLock` |
| --- | --- | --- |
| 可中断等待 | 不支持 | `lockInterruptibly()` |
| 超时尝试 | 不支持 | `tryLock(time, unit)` |
| 公平锁 | 不支持 | `new ReentrantLock(true)` |
| 多条件变量 | 一个对象等待队列 | `newCondition()` |
| 锁释放 | 自动释放 | 必须手动 `unlock()` |

```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class SafeBankAccount {
    private int balance = 1000;
    private final ReentrantLock lock = new ReentrantLock();

    public boolean withdraw(int amount, long timeout) throws InterruptedException {
        if (!lock.tryLock(timeout, TimeUnit.MILLISECONDS)) {
            System.out.println("获取锁超时，放弃操作");
            return false;
        }
        try {
            if (balance < amount) return false;
            balance -= amount;
            System.out.println(Thread.currentThread().getName()
                + " 取款成功，余额：" + balance);
            return true;
        } finally {
            lock.unlock();
        }
    }
}
```

使用 `ReentrantLock` 必须在 `finally` 中释放锁。

## 线程池 ExecutorService

不使用线程池的问题：

- 每次 `new Thread()` 都有创建开销。
- 线程数量失控可能耗尽资源。
- 难以统一管理、限流、关闭和监控。

线程池优势：

- 复用线程。
- 控制并发数量。
- 任务队列排队。
- 统一管理任务生命周期。

四种常用线程池：

| 方法 | 特点 | 适用 |
| --- | --- | --- |
| `newFixedThreadPool(n)` | 固定线程数，超出任务排队 | CPU 密集型、已知并发量 |
| `newCachedThreadPool()` | 弹性线程池，线程数可增减但无上限 | 大量短期异步任务 |
| `newSingleThreadExecutor()` | 单工作线程，保证任务顺序 | 顺序任务队列 |
| `newScheduledThreadPool(n)` | 定时或周期任务 | 心跳、定时任务 |

材料提示：`Executors` 工厂方法在生产环境有风险，例如 `CachedThreadPool` 无上限。进阶场景推荐直接配置 `ThreadPoolExecutor`。

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName()
                    + " 执行任务 #" + taskId);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown();
        if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
            pool.shutdownNow();
        }
        System.out.println("所有任务完成！");
    }
}
```

`submit()` 可以提交 `Callable` 并获得 `Future<T>`；`execute()` 提交 `Runnable` 无返回值。

`ThreadPoolExecutor` 核心参数：

- `corePoolSize`：核心线程数。
- `maximumPoolSize`：最大线程数。
- `keepAliveTime` 与 `unit`：非核心线程空闲超时时间。
- `workQueue`：任务等待队列。
- `threadFactory`：线程工厂。
- `handler`：拒绝策略。

## AtomicInteger 与 CAS

`java.util.concurrent.atomic` 包提供原子操作类，无需加锁即可保证简单操作线程安全。

常用原子类：

- `AtomicInteger`
- `AtomicLong`
- `AtomicBoolean`
- `AtomicReference<T>`
- `AtomicIntegerArray`
- `LongAdder`

CAS 参数：

- `V`：内存当前值。
- `E`：期望值。
- `N`：新值。

逻辑：如果 `V == E`，修改为 `N`；否则不修改并重试。特点是无锁、依赖硬件原语；缺点包括 ABA 问题。

```java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int get() {
        return count.get();
    }
}
```

`AtomicInteger` 适合简单数值操作，但不能替代所有锁。复合操作如“先检查再更新”仍可能需要锁。

## 综合案例：并发任务处理器

材料中的综合案例使用：

- `BlockingQueue<Task>`：线程安全任务队列。
- `ExecutorService`：工作线程池。
- `AtomicInteger`：统计成功/失败数量。

```java
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Task {
    private final int id;
    private final int difficulty;

    public Task(int id, int difficulty) {
        this.id = id;
        this.difficulty = difficulty;
    }

    public void execute() throws InterruptedException {
        Thread.sleep(difficulty * 100L);
        if (difficulty == 3 && Math.random() < 0.3) {
            throw new RuntimeException("任务#" + id + " 执行失败！");
        }
        System.out.printf("[%s] 完成任务#%d (难度:%d)%n",
            Thread.currentThread().getName(), id, difficulty);
    }
}

class Statistics {
    static final AtomicInteger success = new AtomicInteger(0);
    static final AtomicInteger failure = new AtomicInteger(0);
}

class Worker implements Runnable {
    private final BlockingQueue<Task> queue;
    private final int workerId;

    public Worker(BlockingQueue<Task> queue, int workerId) {
        this.queue = queue;
        this.workerId = workerId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = queue.poll(1, TimeUnit.SECONDS);
                if (task == null) break;
                task.execute();
                Statistics.success.incrementAndGet();
            } catch (RuntimeException e) {
                Statistics.failure.incrementAndGet();
                System.out.println("Worker-" + workerId + ": " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

public class ConcurrentTaskSystem {
    public static void main(String[] args) throws Exception {
        int taskCount = 50;
        int workerCount = 4;
        BlockingQueue<Task> queue = new ArrayBlockingQueue<>(20);
        ExecutorService pool = Executors.newFixedThreadPool(workerCount);

        for (int i = 1; i <= workerCount; i++) {
            pool.submit(new Worker(queue, i));
        }

        for (int i = 1; i <= taskCount; i++) {
            int difficulty = (i % 3) + 1;
            queue.put(new Task(i, difficulty));
        }

        pool.shutdown();
        pool.awaitTermination(60, TimeUnit.SECONDS);

        System.out.printf("成功: %d 失败: %d 总计: %d%n",
            Statistics.success.get(), Statistics.failure.get(), taskCount);
    }
}
```

`BlockingQueue.poll(timeout)` 同时兼顾等待任务和检测退出，是材料强调的常用技巧。

## JUC 工具总览

| 类别 | 例子 |
| --- | --- |
| 锁 | `ReentrantLock`、`ReadWriteLock`、`StampedLock` |
| 线程池 | `ExecutorService`、`ForkJoinPool`、`CompletableFuture` |
| 原子类 | `AtomicInteger`、`AtomicReference`、`LongAdder` |
| 同步工具 | `CountDownLatch`、`CyclicBarrier`、`Semaphore` |
| 并发集合 | `ConcurrentHashMap`、`CopyOnWriteArrayList`、`LinkedBlockingQueue` |
| Future | `Future<T>`、`CompletableFuture`、`FutureTask` |

`CountDownLatch` 用于让一个或多个线程等待其他线程完成某件事，计数到 0 后不可重置。

## 并发工具选型

| 需求 | 推荐工具 |
| --- | --- |
| 简单线程安全 | `synchronized` 方法/块 |
| 需要超时、可中断、多条件 | `ReentrantLock` |
| 简单整数或引用原子操作 | `AtomicInteger` / `AtomicReference` |
| 管理多个并发任务 | `ExecutorService` |
| 多线程等待某个事件 | `CountDownLatch` / `CyclicBarrier` |
| 线程安全 Map/List | `ConcurrentHashMap` / `CopyOnWriteArrayList` |

## 易错点

- 以为 `count++` 是原子操作。
- 在 `synchronized` 外调用 `wait()` 或 `notify()`。
- 条件等待用 `if`，被唤醒后不重新检查条件。
- 认为 `notifyAll()` 后所有线程立即运行。
- 认为 `volatile` 可替代所有锁。
- 加锁顺序不一致导致死锁。
- 使用 `ReentrantLock` 忘记 `finally unlock()`。
- 认为 `CachedThreadPool` 适合所有高并发场景。
- 认为 `AtomicInteger` 可替代所有 `synchronized`。
- 认为 `shutdown()` 会立刻停止所有正在执行的线程。

## 自测题

1. 什么是竞争条件？为什么 `count++` 不安全？
2. `synchronized` 实例方法和静态方法分别锁什么？
3. `wait()` 与 `sleep()` 最大区别是什么？
4. 死锁四个必要条件是什么？如何打破循环等待？
5. `shutdown()` 和 `shutdownNow()` 有什么区别？

