# 01 Java 环境与面向对象入门

## 材料来源

- `JAVA1.pdf`

## 本讲复习目标

- 理解 Java 程序从源码到运行的流程。
- 区分 `JDK`、`JRE`、`JVM`，知道 JVM 的作用。
- 建立面向对象基本概念：类、对象、属性、行为、消息。
- 掌握类的基本定义、对象创建、封装、构造方法、访问权限、`static` 与 `this`。

## 考点清单

- Java 跨平台机制：字节码运行在 JVM 上。
- `JDK ⊃ JRE ⊃ JVM`。
- `.java -> javac -> .class/bytecode -> JVM -> 机器指令`。
- 面向对象从“怎么做”转向“谁来做”。
- 类是对象共同属性和行为的抽象。
- 对象之间通过消息通信：接收者、方法名、参数。
- 面向对象三大特征：封装、继承、多态。
- 类定义语法、字段、方法、构造方法。
- 对象引用与 `new`。
- `private` 字段配合 `public` getter/setter。
- `static` 成员属于类，实例成员属于对象。
- `this` 表示当前对象。

## 记忆点

- JVM 做四件关键事：执行字节码、内存管理、线程调度、JIT 优化。
- 类是模板，对象是实例。
- 对象 = 状态 + 行为。
- 构造方法：同名、无返回类型、`new` 时自动调用。
- 没写构造方法时，编译器提供默认无参构造；一旦自己写了构造方法，默认无参构造不再自动提供。
- 静态方法没有 `this`，只能直接访问静态成员。

## Java 运行机制

Java 源文件先由编译器编译成字节码，字节码不是某一种 CPU 的机器码，而是 JVM 能识别的中间代码。不同平台安装对应 JVM 后，同一份 `.class` 字节码即可运行。

```text
Hello.java --javac--> Hello.class --JVM--> 当前平台机器指令
```

开发环境常见配置：

- `JAVA_HOME` 指向 JDK 安装目录。
- `PATH` 中加入 JDK 的 `bin` 目录，便于使用 `javac`、`java`。

## 面向对象基本思想

过程式思路关心“步骤怎么做”，面向对象思路关心“由谁来做”。例如矩形求面积，不是把长宽和公式散落在主程序中，而是定义 `Rectangle` 类，让矩形对象自己保存长宽并提供求面积行为。

```java
class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}

public class Demo {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(3.0, 4.0);
        System.out.println(r.area());
    }
}
```

## 类与对象

类的基本结构：

```java
[修饰符] class 类名 [extends 父类] [implements 接口列表] {
    字段;
    构造方法;
    方法;
}
```

对象创建分两步：声明引用变量，再用 `new` 创建对象。

```java
Student stu;
stu = new Student("张三", 20);

stu.name = "张三";      // 访问字段，若字段可见
stu.study();            // 调用方法
```

材料强调对象参数传递引用。更准确地说，Java 参数传递都是值传递；当传入引用变量时，复制的是引用值，所以方法内可以通过这个引用修改同一个对象的状态。

```java
class Student {
    String name;
}

public class RefDemo {
    static void rename(Student s) {
        s.name = "李四";
    }

    public static void main(String[] args) {
        Student stu = new Student();
        stu.name = "张三";
        rename(stu);
        System.out.println(stu.name); // 李四
    }
}
```

## 封装

封装的典型写法是字段私有化，对外提供方法。这样可以在 setter 中加入校验，避免对象进入非法状态。

```java
class Student {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 150) {
            System.out.println("年龄非法");
            return;
        }
        this.age = age;
    }
}
```

## 构造方法

构造方法用于初始化对象。

```java
class Account {
    private String owner;
    private double balance;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }
}
```

构造方法特点：

- 名字必须与类名完全相同。
- 没有返回类型，不能写 `void`。
- 使用 `new` 创建对象时自动调用。
- 可以重载。

## 访问控制

| 修饰符 | 本类 | 同包 | 子类 | 其他包 |
| --- | --- | --- | --- | --- |
| `private` | 可以 | 不可以 | 不可以 | 不可以 |
| 默认不写 | 可以 | 可以 | 不可以 | 不可以 |
| `protected` | 可以 | 可以 | 可以 | 不可以 |
| `public` | 可以 | 可以 | 可以 | 可以 |

默认权限不是 `default` 关键字，而是什么都不写。

## static 与 this

`static` 修饰的字段是类变量，所有对象共享一份；静态方法属于类，常用类名调用。

```java
class Student {
    private String name;
    private static int count;

    public Student(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }
}
```

`this` 表示当前对象，常见用途：

- 区分同名字段和参数。
- 把当前对象作为参数传给其他方法。
- 调用本类其他构造方法：`this(...)`，且必须是构造方法第一行。

```java
class Point {
    private int x;
    private int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

## 易错点

- 把构造方法写成 `public void Student()`，这会变成普通方法。
- 在静态方法中直接访问实例字段，编译错误。
- 定义有参构造后忘记补无参构造，导致 `new Student()` 编译失败。
- 把引用赋值当成对象复制，`b = a` 后两个引用指向同一个对象。

## 自测题

1. `JDK`、`JRE`、`JVM` 谁包含谁？
2. 为什么 Java 能跨平台？
3. 构造方法能不能有返回值？能不能重载？
4. `static` 方法中为什么不能直接使用 `this`？
5. `Student a = new Student(); Student b = a;` 后修改 `b` 的字段，`a` 会变化吗？为什么？

