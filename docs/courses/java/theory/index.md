# OOP 理论课复习讲义

本讲义整理面向对象程序设计理论课中的 Java 与 OOP 知识点，按材料出现顺序组织。用途是期末复习：先建立知识地图，再按专题复盘考点、记忆点、示例代码与易错点。

## 课程考核提示

- 课程材料说明期末为闭卷笔试，期末成绩占 60%，实践项目占 20%。
- 复习时不要只背概念，要能读代码、写小段代码、判断输出和解释错误原因。
- 高频题型集中在：类与对象、封装与构造、继承与重写、多态、数组与集合、异常处理、I/O、线程同步与线程池。

## 材料顺序与讲义目录

| 顺序 | 原始材料 | 本站讲义 |
| --- | --- | --- |
| 1 | `JAVA1.pdf` | [01 Java 环境与面向对象入门](./01-intro-oop.md) |
| 2 | `java2.pdf` | [02 Java 基础语法](./02-java-basics.md) |
| 3 | `java3.pdf` | [03 类、对象、封装与 static](./03-class-object-encapsulation.md) |
| 4 | `Java复习_第一课时_UML与类结构 (1).pdf`、`Java4嵌套类.pdf`、`Java5 类与对象 习题.pdf` | [04 UML、嵌套类与类对象练习](./04-uml-nested-classes-practice.md) |
| 5 | `Java6 继承与方法重写.pdf` | [05 继承、构造链与方法重写](./05-inheritance-overriding.md) |
| 6 | `Java7_类型转换与多态.pdf`、`java8 多态复习.pdf` | [06 类型转换、抽象类、接口与多态](./06-casting-abstract-interface-polymorphism.md) |
| 7 | `java9 数组.pdf`、`java9-2 - 字符串.pdf`、`java10 泛型+集合.pdf` | [07 数组、字符串、泛型与集合](./07-arrays-strings-generics-collections.md) |
| 8 | `java11 异常.pdf`、`java14 输入输出流.pdf` | [08 异常处理与输入输出流](./08-exceptions-io.md) |
| 9 | `Lesson1_线程与并发基础.pdf`、`Lesson2_线程实现与生命周期.pdf` | [09 线程基础、创建方式与生命周期](./09-threads-basics-lifecycle.md) |
| 10 | `课时3_线程同步与通信.pdf`、`课时4_死锁线程池与综合实战.pdf` | [10 线程同步、死锁、线程池与 JUC](./10-thread-sync-pools.md) |

## 总体考点地图

### 必背概念

- `JDK`、`JRE`、`JVM` 的包含关系：`JDK ⊃ JRE ⊃ JVM`。
- 面向对象三大特征：封装、继承、多态。
- 类是对象的抽象，对象由状态和行为组成，对象之间通过消息通信。
- Java 程序从 `.java` 经过 `javac` 编译为 `.class` 字节码，再由 JVM 执行。
- 类成员分为实例成员和类成员，`static` 属于类，实例成员属于对象。
- 继承是 `is-a` 关系；接口描述 `can-do` 能力。
- 多态口诀：编译看左边，运行看右边。
- 异常即对象，核心层次是 `Throwable -> Error / Exception -> RuntimeException`。
- 流是有起点和终点的有序数据序列，按方向分输入/输出，按单位分字节/字符。
- 线程是 CPU 调度单位，进程是资源分配单位。

### 高频代码题

- 写类：字段私有化、构造方法、getter/setter、`this`、`static` 计数。
- 判断构造链输出顺序：`Object -> 父类 -> 子类`。
- 判断重载和重写是否合法：参数列表、返回类型、访问权限、异常声明。
- 用父类引用指向子类对象，判断能调用哪些方法、实际执行哪个方法。
- 数组复制、排序、查找、二维数组遍历。
- `String` 的 `==` 与 `equals`，字符串不可变，`StringBuilder` 拼接。
- `try-catch-finally` 执行顺序、多重 `catch` 顺序、`throw` 与 `throws`。
- 文件读写、缓冲流、序列化、`transient`。
- `start()` 与 `run()` 区别、`sleep()` 与 `join()`、`synchronized`、`wait/notifyAll`。

### 易错点速记

| 易错点 | 正确记忆 |
| --- | --- |
| 一个 `.java` 可以有多个 `public class` | 最多一个，且文件名必须与 `public class` 同名 |
| `boolean` 可以当整数用 | Java 中 `boolean` 不是整数，条件表达式必须是布尔值 |
| 定义了有参构造后仍自动保留无参构造 | 不会保留，除非自己再写无参构造 |
| `static` 方法能直接访问实例字段 | 不能；静态方法没有 `this` |
| 子类继承父类构造方法 | 构造方法不被继承，只会被构造链调用 |
| 同名字段也多态 | 字段不多态，字段访问看引用类型 |
| 抽象类可以 `new` | 抽象类不能实例化 |
| 接口字段是普通变量 | 接口字段默认 `public static final` |
| 数组长度用 `length()` | 数组用属性 `length`，字符串才用 `length()` |
| `String` 修改原对象 | `String` 不可变，方法返回新字符串 |
| `catch(Exception)` 可以放最前 | 多重 `catch` 子类必须在父类前 |
| `run()` 会启动线程 | `start()` 才会创建新线程，`run()` 是普通方法调用 |
| `sleep()` 会释放锁 | `sleep()` 不释放锁；`wait()` 会释放锁 |
| `notifyAll()` 后线程立即运行 | 被唤醒后还要重新竞争锁 |

## 推荐复习方法

1. 先通读每页“考点清单”和“记忆点”，形成整体框架。
2. 再手写每页示例代码，特别是构造链、多态、异常、线程同步。
3. 最后做每页“自测题”，能解释“为什么错”比只给答案更重要。
