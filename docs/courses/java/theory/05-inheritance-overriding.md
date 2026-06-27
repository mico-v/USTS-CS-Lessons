# 05 继承、构造链与方法重写

## 材料来源

- `Java6 继承与方法重写.pdf`

## 本讲复习目标

- 掌握继承语法、继承关系和 Java 单继承限制。
- 理解子类对象创建时的构造链。
- 区分 `this` 与 `super`。
- 掌握方法重写规则、重写与重载区别。
- 掌握 `protected`、`final`、`Object` 常用方法。

## 考点清单

- `extends` 表示继承，满足 `is-a` 关系。
- Java 类只能单继承，但可以多层继承。
- 如果没有显式父类，默认继承 `Object`。
- 子类拥有父类非私有成员；父类私有成员存在于对象中，但子类不能直接访问。
- 构造方法不被继承。
- 创建子类对象时先调用父类构造，再调用子类构造。
- `super(...)` 必须是构造方法第一行；省略时默认插入 `super()`。
- 父类只有有参构造时，子类必须显式调用 `super(args)` 或父类补无参构造。
- 方法重写口诀：两同一小一大。
- 字段隐藏不参与多态，方法调用参与动态绑定。
- `final` 类不能被继承，`final` 方法不能被重写，`final` 变量不能重新赋值。

## 继承基础

```java
class Animal {
    protected String name;

    public void eat() {
        System.out.println(name + " eat");
    }
}

class Dog extends Animal {
    public void bark() {
        System.out.println(name + " bark");
    }
}
```

继承的价值：复用代码、形成层次结构、表达 `is-a` 关系。

Java 只支持类的单继承，以避免多继承中的菱形问题；多个能力通过接口表达。

## 构造链

创建子类对象时，构造调用顺序是从最顶层父类到当前子类。

```java
class Parent {
    Parent() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    Child() {
        System.out.println("Child");
    }
}

public class Demo {
    public static void main(String[] args) {
        new Child();
    }
}
```

输出：

```text
Parent
Child
```

父类没有无参构造时：

```java
class Parent {
    Parent(String name) {
        System.out.println(name);
    }
}

class Child extends Parent {
    Child() {
        super("parent");
        System.out.println("child");
    }
}
```

`this(...)` 和 `super(...)` 都要求第一行，因此不能同时直接出现在同一个构造方法中。

## super 的三种用法

```java
class Parent {
    protected int value = 10;

    Parent(int value) {
        this.value = value;
    }

    public void print() {
        System.out.println("Parent: " + value);
    }
}

class Child extends Parent {
    private int value = 20;

    Child() {
        super(15);                 // 调父类构造
    }

    public void show() {
        System.out.println(super.value); // 访问父类字段
        super.print();                  // 调父类方法
    }
}
```

`this` 表示当前对象，`super` 表示从父类部分查找成员。二者不是两个对象。

## 字段隐藏与方法动态绑定

同名字段不会多态，字段访问看引用类型；方法调用看实际对象类型。

```java
class Parent {
    String name = "Parent";

    void print() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    String name = "Child";

    @Override
    void print() {
        System.out.println("Child method");
    }
}

public class Demo {
    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println(p.name); // Parent
        p.print();                  // Child method
    }
}
```

材料建议避免子类定义与父类同名字段。

## 方法重写

方法重写是子类重新定义父类继承来的方法。

```java
class Animal {
    public void speak() {
        System.out.println("animal");
    }
}

class Cat extends Animal {
    @Override
    public void speak() {
        System.out.println("meow");
    }
}
```

重写规则口诀：两同一小一大。

- 两同：方法名相同，参数列表相同。
- 一小：返回值类型相同或是父类返回类型的子类。
- 一大：访问权限不能比父类更严格。

重写建议写 `@Override`，让编译器帮你检查。

## 重写 vs 重载

| 对比项 | 重写 Override | 重载 Overload |
| --- | --- | --- |
| 位置 | 父子类之间 | 同一类中，也可在继承关系中出现 |
| 方法名 | 相同 | 相同 |
| 参数列表 | 相同 | 必须不同 |
| 返回类型 | 相同或协变 | 不能仅靠返回类型区分 |
| 绑定时机 | 运行时动态绑定 | 编译时确定 |

## protected

`protected` 可以在本类、同包、不同包子类中访问；不同包的非子类不能访问。

```java
package a;

public class Parent {
    protected int value;
}
```

```java
package b;

import a.Parent;

public class Child extends Parent {
    public void test() {
        value = 10; // 子类中可以访问继承来的 protected 成员
    }
}
```

## final

```java
final class Utility {
}

class Parent {
    public final void cannotOverride() {
    }
}

final Student stu = new Student();
// stu = new Student(); // 不允许重新赋值
stu.setScore(90);      // 如果对象方法允许，仍可修改对象内容
```

常见 `final` 类示例：`String`、`Math`、`Integer`。

## Object 常用方法

所有类默认继承 `Object`，常见方法：

- `toString()`：返回对象字符串表示。
- `equals(Object obj)`：比较对象是否相等。
- `hashCode()`：返回哈希码。
- `getClass()`：返回运行时类信息。

重写 `toString()`：

```java
class Student {
    private String name;
    private int score;

    @Override
    public String toString() {
        return name + ": " + score;
    }
}
```

重写 `equals(Object)` 时参数必须是 `Object`，不是 `Student`。

```java
class Student {
    private String id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return id.equals(other.id);
    }
}
```

## 易错点

- 子类不会继承父类构造方法。
- 忘记父类没有无参构造，导致子类构造报错。
- `super` 不是父类对象，它只是访问父类成员的语法。
- 重写时把访问权限缩小。
- 把重载当成重写。
- 以为同名字段也会动态绑定。
- 重写 `equals(Student)` 而不是 `equals(Object)`。

## 自测题

1. Java 为什么只支持类的单继承？
2. 创建子类对象时，父类和子类构造方法谁先执行？
3. 父类只有有参构造，子类应该怎么写？
4. 方法重写的“两同一小一大”分别是什么？
5. `Parent p = new Child(); p.name` 与 `p.method()` 分别看谁？

