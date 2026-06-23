# 09 线程基础、创建方式与生命周期

## 材料来源

- `Lesson1_线程与并发基础.pdf`
- `Lesson2_线程实现与生命周期.pdf`

## 本讲复习目标

- 区分进程与线程、并发与并行。
- 理解 Java 程序自带 `main` 线程。
- 掌握 `start()` 与 `run()` 区别。
- 掌握三种创建线程方式：`Thread`、`Runnable`、`Callable`。
- 掌握线程基本状态、`sleep`、`join`、`yield`、`interrupt`、优先级和守护线程。

## 考点清单

- 进程是程序的一次执行过程，是操作系统资源分配最小单位。
- 线程是进程内部的一条执行流，是 CPU 调度最小单位。
- 同一进程内线程共享堆内存、全局变量、文件、网络等资源。
- 线程拥有独立程序计数器、寄存器和栈。
- 并发是看起来同时，单核可通过时间片轮转实现。
- 并行是真正同一时刻执行，依赖多核。
- JVM 启动时自动创建名为 `main` 的线程执行 `main()`。
- `start()` 才会启动新线程；直接调用 `run()` 是普通方法调用。
- 现代 Java 常用 `Runnable + Lambda`；需要返回值时用 `Callable + FutureTask`。
- `sleep()` 让当前线程睡眠，不释放锁。
- `join()` 等待另一个线程结束。
- `interrupt()` 是请求中断，不是强制停止。
- `setDaemon(true)` 必须在 `start()` 前调用。

## 进程 vs 线程

| 对比维度 | 进程 Process | 线程 Thread |
| --- | --- | --- |
| 本质 | 程序的一次执行过程 | 进程内部的一条执行流 |
| 调度/资源 | 资源分配单位 | CPU 调度单位 |
| 内存 | 独立内存空间 | 同进程线程共享堆 |
| 创建开销 | 大 | 小 |
| 切换开销 | 大，需要切换地址空间 | 小，同一地址空间 |
| 通信 | IPC：管道、共享内存、Socket | 直接读写共享变量 |
| 独立性 | 崩溃通常不影响其他进程 | 一个线程出问题可能拖垮进程 |

一句话：进程是资源容器，线程是执行流。

## 并发 vs 并行

| 概念 | 含义 | 关键词 |
| --- | --- | --- |
| 并发 Concurrency | 看起来同时，实际可能交替执行 | 交替、处理多个任务 |
| 并行 Parallelism | 真正同一时刻多个任务执行 | 同时、多核 |

单核 CPU 可以并发但不能真正并行；多核 CPU 可以支持并行。

## main 线程

每个 Java 程序启动时，JVM 自动创建 `main` 线程执行 `main()` 方法。

```java
public class HelloMain {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("线程名：" + t.getName());
        System.out.println("线程ID：" + t.getId());
        System.out.println("线程优先级：" + t.getPriority());
        System.out.println("是否守护线程：" + t.isDaemon());
    }
}
```

`Thread.currentThread()` 返回当前正在执行的线程对象。

## 第一个多线程程序

```java
public class HelloThreads {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("  [子线程] hello " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "WorkerThread");

        t.start();

        for (int i = 1; i <= 5; i++) {
            System.out.println("[main线程] hi " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
```

输出顺序每次可能不同，因为线程由操作系统调度，执行顺序不可预测。不要依赖线程输出顺序；需要顺序时使用同步机制。

## start() 与 run()

```java
Thread t = new Thread(() -> {
    System.out.println(Thread.currentThread().getName());
});

t.run();   // 输出 main，没有启动新线程
t.start(); // 输出 Thread-0 或指定线程名，启动新线程
```

`run()` 是任务代码体，直接调用只是普通方法调用。`start()` 才通知 JVM/OS 创建新线程，并由新线程执行 `run()`。

## 创建线程的三种方式

### 继承 Thread

```java
class CountThread extends Thread {
    private final String label;

    public CountThread(String label) {
        this.label = label;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(label + ": " + i);
        }
    }
}

new CountThread("A").start();
new CountThread("B").start();
```

缺点：Java 单继承，继承 `Thread` 后不能再继承其他类。

### 实现 Runnable

```java
class CountTask implements Runnable {
    private final String label;

    public CountTask(String label) {
        this.label = label;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(label + ": " + i);
        }
    }
}

Runnable taskA = new CountTask("A");
Thread tA = new Thread(taskA, "Worker-A");
tA.start();
```

推荐理由：任务和线程解耦、避免单继承限制、同一个任务对象可被多个线程共用。

Lambda 写法：

```java
Thread t = new Thread(() -> {
    System.out.println("hello");
});
t.start();
```

### Callable + FutureTask

`Runnable` 无返回值且不能抛受检异常；`Callable<V>` 有返回值，`call()` 可抛异常。

```java
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

Callable<Integer> task = () -> {
    int sum = 0;
    for (int i = 1; i <= 100; i++) {
        sum += i;
    }
    return sum;
};

FutureTask<Integer> ft = new FutureTask<>(task);
new Thread(ft).start();

Integer result = ft.get(); // 阻塞等待结果
System.out.println(result); // 5050
```

| 对比项 | Thread | Runnable | Callable |
| --- | --- | --- | --- |
| 建立方式 | `extends Thread` | `implements Runnable` | `implements Callable<V>` |
| 核心方法 | `void run()` | `void run()` | `V call() throws Exception` |
| 返回值 | 无 | 无 | 有 |
| 受检异常 | 不便抛出 | 不便抛出 | 可抛出 |
| 单继承限制 | 有 | 无 | 无 |
| 典型场景 | 简单脚本 | 多数业务代码 | 需要异步结果 |

## 线程生命周期

材料使用 5 个基本状态帮助记忆：

```text
NEW -> RUNNABLE -> RUNNING -> BLOCKED -> DEAD
```

Java API 中 `Thread.State` 枚举为：

```text
NEW / RUNNABLE / BLOCKED / WAITING / TIMED_WAITING / TERMINATED
```

记忆口诀：生、就、运、阻、亡。新生需 `start()`，运行靠调度，中途常阻塞，结束便消亡。

状态含义：

- `NEW`：线程对象已创建，未调用 `start()`。
- `RUNNABLE`：已调用 `start()`，等待操作系统分配 CPU。
- `RUNNING`：拿到 CPU，正在执行 `run()`。
- `BLOCKED`：因 `sleep`、`wait`、I/O、锁等暂停。
- `DEAD`：`run()` 执行完毕，线程不可再次启动。

同一个线程对象调用 `start()` 两次会抛 `IllegalThreadStateException`。

## 常用方法

### sleep

```java
for (int i = 5; i > 0; i--) {
    System.out.println("倒计时：" + i);
    Thread.sleep(1000);
}
```

要点：

- `sleep` 是静态方法，作用于当前线程。
- 时间结束后回到可运行状态，不保证立即执行。
- 必须处理 `InterruptedException`。
- `sleep` 不释放任何已持有的锁。

### join

```java
Thread worker = new Thread(() -> {
    System.out.println("worker 干活...");
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    System.out.println("worker 完成！");
});

worker.start();
System.out.println("main 等待 worker...");
worker.join();
System.out.println("main 继续！");
```

在 A 线程中调用 `b.join()`，A 暂停，直到 b 线程结束后 A 才继续。

### yield 与 interrupt

```java
Thread.yield(); // 提示让出CPU，调度器不一定采纳
```

`interrupt()` 只是设置中断标志，请求线程自己响应，不是强制终止。

```java
Thread t = new Thread(() -> {
    while (!Thread.currentThread().isInterrupted()) {
        // 干活
    }
    System.out.println("收到中断，退出");
});

t.start();
Thread.sleep(2000);
t.interrupt();
```

`stop()`、`suspend()` 已废弃，不应使用。

## 线程属性

优先级范围是 `1..10`：

- `Thread.MIN_PRIORITY`：1
- `Thread.NORM_PRIORITY`：5
- `Thread.MAX_PRIORITY`：10

优先级只是调度建议，不能依赖它控制并发逻辑。

守护线程：

```java
Thread t = new Thread(() -> {
    while (true) {
        System.out.println("daemon...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
        }
    }
});

t.setDaemon(true); // 必须在start前
t.start();
Thread.sleep(2000);
System.out.println("main结束");
```

当所有用户线程结束时，守护线程会被立即终止。典型例子包括 GC 线程、JIT 编译线程、监控线程。

## 并行下载示例

```java
import java.util.ArrayList;
import java.util.List;

public class ParallelDownload {
    public static void main(String[] args) throws InterruptedException {
        String[] urls = {"img1", "img2", "img3"};
        List<Thread> threads = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (String url : urls) {
            Thread t = new Thread(() -> {
                System.out.println("开始: " + url);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("完成: " + url);
            }, "DL-" + url);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long cost = System.currentTimeMillis() - start;
        System.out.println("总耗时 " + cost + " ms");
    }
}
```

材料说明：串行 3 个 2 秒任务约 6 秒，并行约 2 秒多。第 4 讲会用线程池更优雅地管理。

## 易错点

- 直接调用 `run()`，没有新线程。
- 一个线程对象 `start()` 两次。
- 以为 `Thread.sleep(1000)` 会让某个 Thread 对象睡眠；它实际作用于当前线程。
- 以为 `sleep()` 会释放锁。
- 忘记 `join()` 导致主线程提前继续。
- `setDaemon(true)` 写在 `start()` 后。
- 使用已废弃的 `stop()` 强制终止线程。
- 依赖线程执行顺序。

## 自测题

1. 进程和线程分别是什么单位？
2. 并发和并行有什么区别？单核 CPU 能并行吗？
3. `start()` 和 `run()` 的区别是什么？
4. `Runnable` 与 `Callable` 的核心区别是什么？
5. `sleep()`、`join()`、`interrupt()` 分别解决什么问题？

