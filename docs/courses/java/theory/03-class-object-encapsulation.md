# 03 类、对象、封装与 static

## 材料来源

- `java3.pdf`

## 本讲复习目标

- 深入掌握类的组成：字段、方法、构造方法。
- 理解对象创建、引用赋值、参数传递和作用域。
- 掌握封装、包、导入、访问权限。
- 掌握方法重载、`this`、`static`、静态初始化块。

## 考点清单

- 面向对象设计步骤：收集需求、设计类和关系、创建对象、启动对象。
- 类包含状态、行为、构造方法。
- `new Constructor(args)` 会在堆中创建对象并返回引用。
- 基本类型参数传值；引用变量传入方法后可修改对象状态。
- 成员变量、局部变量、参数的作用域区别。
- 封装：`private` 字段 + `public` getter/setter。
- `package` 必须是源文件第一条语句。
- `java.lang` 自动导入。
- 类要跨包访问，类本身通常需要 `public`。
- 重载：同名，不同参数列表；返回值不能单独区分重载。
- `static` 成员只分配一次，推荐用类名访问。
- `static final` 常用于常量。

## 类的组成

```java
class Student {
    private String name;      // 状态：字段
    private int score;

    public Student(String name, int score) { // 构造方法
        this.name = name;
        this.score = score;
    }

    public void printInfo() { // 行为：方法
        System.out.println(name + ": " + score);
    }
}
```

每个类至少有一个构造方法。如果没有显式定义，编译器提供默认无参构造；如果已经定义了任意构造方法，默认无参构造消失。

```java
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }
}

// new User(); // 编译错误：没有无参构造
```

## 对象创建与引用赋值

```java
Student a = new Student("张三", 90);
Student b = a;
```

`a` 和 `b` 保存同一个对象的引用，不是复制出第二个对象。通过 `b` 修改对象，`a` 看到的也是修改后的状态。

```java
class Point {
    int x;
}

public class RefAssignDemo {
    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 10;

        Point p2 = p1;
        p2.x = 20;

        System.out.println(p1.x); // 20
    }
}
```

## 参数传递

基本类型传入方法，方法拿到的是值的副本。

```java
static void change(int x) {
    x = 100;
}

public static void main(String[] args) {
    int a = 10;
    change(a);
    System.out.println(a); // 10
}
```

引用类型传入方法，方法拿到的是引用值的副本，可以通过它访问同一个对象。

```java
class Box {
    int value;
}

static void change(Box box) {
    box.value = 100;
}

public static void main(String[] args) {
    Box b = new Box();
    b.value = 10;
    change(b);
    System.out.println(b.value); // 100
}
```

## 作用域

| 名称 | 定义位置 | 生命周期/可见性 |
| --- | --- | --- |
| 成员变量 | 类中、方法外 | 随对象存在，可被本类方法访问 |
| 局部变量 | 方法或代码块中 | 只在定义它的块中可见 |
| 参数 | 方法参数列表 | 只在方法内部可见 |

局部变量使用前必须赋值；成员变量有默认值。

## 封装与访问权限

封装让外部只依赖稳定方法，不直接依赖内部字段。材料中的典型思想是：即使内部表示变化，只要 `getAge()` 等方法保持不变，外部代码无需修改。

```java
class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("存款金额非法");
            return;
        }
        balance += amount;
    }
}
```

访问权限复习：

| 修饰符 | 范围 |
| --- | --- |
| `private` | 本类内部 |
| 默认不写 | 同一个包 |
| `protected` | 同包和子类 |
| `public` | 所有位置 |

## 包与导入

`package` 是命名边界，必须写在源文件第一条语句。

```java
package edu.usts.oop;

public class Student {
}
```

命名建议使用小写和反向域名风格。

使用其他包中的类：

```java
import java.util.Scanner;
import java.util.*;
```

`java.lang` 包自动导入，例如 `String`、`System` 不需要手动 `import`。

## 方法重载

同一个类中，方法名相同但参数列表不同，称为重载。

```java
class Printer {
    public void print(String text) {
        System.out.println(text);
    }

    public void print(int number) {
        System.out.println(number);
    }

    public void print(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(text);
        }
    }
}
```

参数列表不同包括：参数个数不同、类型不同、顺序不同。只有返回类型不同不能构成重载。

## this

```java
class Student {
    private String name;
    private int score;

    public Student() {
        this("未命名", 0);
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
```

`this(...)` 调用本类其他构造方法，必须是第一条语句，不能形成循环调用。

## static 与类成员

```java
class ParkingLot {
    private static int totalCars;
    private String carNo;

    public ParkingLot(String carNo) {
        this.carNo = carNo;
        totalCars++;
    }

    public static int getTotalCars() {
        return totalCars;
    }
}
```

静态成员特点：

- 属于类，不属于某个对象。
- 只分配一份，所有对象共享。
- 推荐使用 `类名.静态成员` 访问。
- 静态方法只能直接访问静态成员。
- 实例方法可以访问静态成员和实例成员。
- `main` 是静态方法，因为程序开始时还没有当前类的对象。

`static final` 常量：

```java
class MathConfig {
    public static final double PI = 3.1415926;
}
```

静态初始化块在类加载时执行一次。

```java
class Config {
    static {
        System.out.println("类加载时执行一次");
    }
}
```

## 易错点

- `package` 前面不能有普通语句。
- 只有返回值不同不是重载。
- 静态方法不能直接访问实例字段。
- `this()` 必须写在构造方法第一行。
- `b = a` 不是对象复制。
- 类本身不是 `public` 时，跨包访问会受限。

## 自测题

1. 定义有参构造后，为什么 `new 类名()` 可能编译失败？
2. `private` 字段为什么仍然可以通过 getter/setter 暴露访问？
3. 哪些情况可以构成方法重载？
4. `static` 字段和实例字段在内存和访问方式上有什么区别？
5. `this()` 和 `super()` 为什么不能同时出现在同一个构造方法中？

