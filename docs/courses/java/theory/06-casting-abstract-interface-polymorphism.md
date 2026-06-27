# 06 类型转换、抽象类、接口与多态

## 材料来源

- `Java7_类型转换与多态.pdf`
- `java8 多态复习.pdf`

## 本讲复习目标

- 掌握向上转型、向下转型和 `instanceof`。
- 掌握抽象类和接口的语法与适用场景。
- 理解多态成立条件和“编译看左边，运行看右边”。
- 能用多态设计简单扩展型程序。

## 考点清单

- 子类对象可以自动向上转型为父类引用。
- 父类引用向下转型为子类引用必须强制转换。
- 向下转型前用 `instanceof` 判断，避免 `ClassCastException`。
- `null instanceof AnyClass` 结果为 `false`。
- 抽象类用 `abstract class`，不能实例化。
- 抽象方法没有方法体，子类必须实现，否则子类仍是抽象类。
- 抽象方法不能是 `private` 或 `final`。
- 接口方法默认 `public abstract`，字段默认 `public static final`。
- 一个类可以实现多个接口。
- `extends` 写在 `implements` 前。
- 接口可以作为引用类型。
- Java 8 接口可以有 `default` 方法和 `static` 方法。
- 多态条件：继承或实现、方法重写、父类或接口引用指向子类对象。
- 成员变量和静态方法不参与多态。

## 类型转换

### 向上转型

```java
class Animal {
    public void speak() {
        System.out.println("animal");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("dog");
    }

    public void guard() {
        System.out.println("guard");
    }
}

public class Demo {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.speak();       // dog
        // a.guard();    // 编译错误：Animal 类型中没有 guard
    }
}
```

向上转型安全，但只能调用父类中声明的方法；如果方法被子类重写，运行时执行子类版本。

### 向下转型

```java
Animal a = new Dog();

if (a instanceof Dog) {
    Dog d = (Dog) a;
    d.guard();
}
```

错误示例：

```java
Animal a = new Animal();
Dog d = (Dog) a; // 运行时 ClassCastException
```

## 抽象类

抽象类表达“部分实现 + 部分规范”。它可以有字段、构造方法、普通方法、抽象方法。

```java
abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double area();
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
```

抽象类不能 `new`：

```java
// Shape s = new Shape("red"); // 编译错误
Shape s = new Circle("red", 2.0);
```

## 模板方法模式

材料中强调抽象类可用于模板方法：父类固定流程，子类实现变化步骤。

```java
abstract class ExamProcess {
    public final void takeExam() {
        login();
        answer();
        submit();
    }

    private void login() {
        System.out.println("登录考试系统");
    }

    protected abstract void answer();

    private void submit() {
        System.out.println("提交试卷");
    }
}

class JavaExam extends ExamProcess {
    @Override
    protected void answer() {
        System.out.println("作答 Java 题目");
    }
}
```

`takeExam()` 用 `final` 防止子类改变整体流程。

## 接口

接口表达行为契约，强调“能做什么”。

```java
interface PaymentMethod {
    void pay(double amount);
}

class AliPay implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("支付宝支付：" + amount);
    }
}

class WeChatPay implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("微信支付：" + amount);
    }
}
```

实现接口的方法必须是 `public`，因为接口方法默认是 `public abstract`。

接口字段默认是常量：

```java
interface Config {
    int MAX_SIZE = 100; // public static final
}
```

一个类可以实现多个接口：

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck extends Animal implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("fly");
    }

    @Override
    public void swim() {
        System.out.println("swim");
    }
}
```

## 抽象类 vs 接口

| 对比项 | 抽象类 | 接口 |
| --- | --- | --- |
| 关系 | `is-a`，属于某类 | `can-do`，具备某能力 |
| 构造方法 | 可以有 | 没有构造方法 |
| 实例字段 | 可以有 | 字段默认常量 |
| 普通方法 | 可以有 | Java 8 起可有默认方法、静态方法 |
| 继承数量 | 类只能继承一个抽象类 | 类可实现多个接口 |
| 适用 | 提供共同状态和代码 | 定义能力契约 |

## 多态

多态成立条件：

1. 有继承关系或接口实现关系。
2. 子类重写父类方法，或实现类实现接口方法。
3. 父类或接口引用指向子类/实现类对象。

口诀：编译看左边，运行看右边。

- 编译看左边：引用类型决定能调用哪些方法。
- 运行看右边：实际对象决定重写方法执行哪个版本。

```java
class Cashier {
    public void checkout(PaymentMethod method, double amount) {
        method.pay(amount);
    }
}

public class Demo {
    public static void main(String[] args) {
        Cashier cashier = new Cashier();
        cashier.checkout(new AliPay(), 88.0);
        cashier.checkout(new WeChatPay(), 66.0);
    }
}
```

这种写法符合材料中的开放封闭思想：新增支付方式时新增类，不修改 `Cashier` 的核心逻辑。

## 多态数组示例

```java
abstract class Shape {
    public abstract double area();
}

class Rectangle extends Shape {
    private double width;
    private double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

public class Demo {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Rectangle(3, 4),
            new Circle(2)
        };

        for (Shape shape : shapes) {
            System.out.println(shape.area());
        }
    }
}
```

## 不参与多态的成员

字段不多态，静态方法也不多态。

```java
class Parent {
    String name = "Parent";

    static void show() {
        System.out.println("Parent static");
    }
}

class Child extends Parent {
    String name = "Child";

    static void show() {
        System.out.println("Child static");
    }
}

public class Demo {
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.name); // Parent
        p.show();                   // Parent static，不推荐用对象调静态方法
    }
}
```

## 易错点

- 向上转型后不能调用子类独有方法。
- 向下转型不检查实际类型，运行时抛 `ClassCastException`。
- `null instanceof String` 是 `false`。
- 抽象类可以有构造方法，但不能直接实例化。
- 接口实现方法忘记写 `public`。
- 接口字段不是实例变量，而是常量。
- 以为字段和静态方法也遵循“运行看右边”。

## 自测题

1. `Animal a = new Dog();` 后，`a` 能否调用 `Dog` 独有方法？为什么？
2. 向下转型前为什么要用 `instanceof`？
3. 抽象类和接口分别适合什么场景？
4. 多态成立的三个条件是什么？
5. “编译看左边，运行看右边”对字段适用吗？

