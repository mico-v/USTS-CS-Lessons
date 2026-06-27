# 04 UML、嵌套类与类对象练习

## 材料来源

- `Java复习_第一课时_UML与类结构 (1).pdf`
- `Java4嵌套类.pdf`
- `Java5 类与对象 习题.pdf`

## 本讲复习目标

- 能读懂 UML 类图中的类、属性、方法和可见性。
- 掌握继承、实现等 UML 关系箭头。
- 掌握四类嵌套类：静态嵌套类、成员内部类、局部内部类、匿名内部类。
- 通过类与对象练习巩固构造方法、`this`、`static`、引用赋值。

## 考点清单

- UML 类图三层结构：类名、属性、方法。
- 可见性符号：`+`、`-`、`#`、`~`。
- 继承箭头：实线 + 空心三角，箭头指向父类。
- 实现箭头：虚线 + 空心三角，箭头指向接口。
- 静态嵌套类使用 `Outer.StaticNested` 创建。
- 非静态内部类必须依附外部类对象：`outer.new Inner()`。
- 局部内部类定义在方法或代码块中。
- 匿名内部类适合一次性实现接口或抽象类，结尾要有分号。
- 局部变量被内部类访问时必须是 final 或 effectively final。

## UML 类图速记

口诀：上名中属下方法，`+` 公开、`-` 私有、`#` 保护。

```text
-------------------------
| Student                |
-------------------------
| - name: String         |
| - score: int           |
-------------------------
| + Student(name, score) |
| + getScore(): int      |
| + setScore(score): void|
-------------------------
```

| 符号 | 含义 | Java 对应 |
| --- | --- | --- |
| `+` | public | `public` |
| `-` | private | `private` |
| `#` | protected | `protected` |
| `~` | package | 默认不写 |

常见关系：

| 关系 | UML 表示 | 记忆 |
| --- | --- | --- |
| 继承 | 实线 + 空心三角 | 子类指向父类 |
| 实现 | 虚线 + 空心三角 | 实现类指向接口 |
| 关联/聚合/依赖 | 材料中作了解释 | 看类之间是否长期持有或临时使用 |

## 静态嵌套类

静态嵌套类用 `static class` 定义，不持有外部类对象引用，只能直接访问外部类静态成员。

```java
class Library {
    private static int totalBooks = 100;

    static class BookRecord {
        public void printTotal() {
            System.out.println(totalBooks);
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        Library.BookRecord record = new Library.BookRecord();
        record.printTotal();
    }
}
```

易错：静态嵌套类不能直接访问外部类实例字段。

## 成员内部类

成员内部类没有 `static` 修饰，依附于某个外部类对象，可以访问外部类所有成员，包括 `private` 成员。

```java
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    class Transaction {
        public void withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
            }
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        BankAccount.Transaction tx = account.new Transaction();
        tx.withdraw(200);
    }
}
```

如果内外类字段同名，可以用 `OuterClass.this.field` 指定外部类字段。

```java
class Hero {
    private String name = "外部英雄";

    class Skill {
        private String name = "内部技能";

        public void print() {
            System.out.println(name);
            System.out.println(Hero.this.name);
        }
    }
}
```

## 局部内部类

局部内部类定义在方法或代码块中，只能在该方法或代码块内使用。

```java
class Outer {
    private int base = 10;

    public void test() {
        int factor = 2;

        class LocalCalculator {
            int calc(int x) {
                return (x + base) * factor;
            }
        }

        LocalCalculator c = new LocalCalculator();
        System.out.println(c.calc(5));
    }
}
```

`factor` 被局部内部类访问后不能再被修改，因为它必须是 effectively final。

## 匿名内部类

匿名内部类适合一次性实现接口或抽象类。

```java
interface Printer {
    void print(String text);
}

public class Demo {
    public static void main(String[] args) {
        Printer printer = new Printer() {
            @Override
            public void print(String text) {
                System.out.println(text);
            }
        };

        printer.print("hello");
    }
}
```

注意 `};`：匿名内部类表达式结束后需要分号。Java 8 后，如果接口是函数式接口，可以使用 Lambda 替代。

```java
Printer printer = text -> System.out.println(text);
```

## 嵌套类对比

| 类型 | 定义位置 | 是否依赖外部对象 | 访问外部成员 | 创建方式 |
| --- | --- | --- | --- | --- |
| 静态嵌套类 | 类中，`static` | 不依赖 | 直接访问静态成员 | `new Outer.Nested()` |
| 成员内部类 | 类中，非 `static` | 依赖 | 可访问全部成员 | `outer.new Inner()` |
| 局部内部类 | 方法/块中 | 依赖所在上下文 | 可访问外部成员和 effectively final 局部变量 | 方法内部创建 |
| 匿名内部类 | 表达式中 | 依赖具体写法 | 可访问外部成员和 effectively final 局部变量 | `new 接口(){...}` |

## 典型练习：Student 静态计数

```java
class Student {
    private String name;
    private int score;
    private static int count;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
        count++;
    }

    public static int getCount() {
        return count;
    }
}

public class Demo {
    public static void main(String[] args) {
        new Student("张三", 90);
        new Student("李四", 85);
        System.out.println(Student.getCount()); // 2
    }
}
```

## 典型练习：Point 引用与拷贝

```java
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point copy() {
        return new Point(x, y);
    }
}

public class Demo {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = p1;
        Point p3 = p1.copy();

        p2.x = 9;
        System.out.println(p1.x); // 9
        System.out.println(p3.x); // 1
    }
}
```

## 易错点

- 静态嵌套类直接访问外部类实例字段，编译错误。
- 创建成员内部类时忘记外部类对象。
- 匿名内部类结尾漏写分号。
- 局部内部类访问的局部变量后续又被修改。
- `this()` 不是普通方法调用，必须在构造方法第一行。
- 外部类和内部类可以访问彼此的私有成员。

## 自测题

1. UML 中 `+`、`-`、`#`、`~` 分别表示什么？
2. 继承和实现的 UML 箭头有什么区别？箭头指向谁？
3. `new Outer.Inner()` 为什么通常不能创建非静态内部类？
4. 匿名内部类为什么常用于接口的一次性实现？
5. `Point p2 = p1;` 与 `Point p2 = p1.copy();` 有什么本质区别？

