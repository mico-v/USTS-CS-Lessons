# 02 Java 基础语法

## 材料来源

- `java2.pdf`

## 本讲复习目标

- 掌握 Java 源文件、类与 `main` 方法规则。
- 熟悉标识符、关键字、基本类型、引用类型。
- 掌握变量、运算符、类型转换和控制流程。
- 能阅读基础程序并判断输出。

## 考点清单

- 一个 Java 程序由类组成。
- 一个 `.java` 文件最多有一个 `public class`，文件名必须与该类同名。
- `main` 方法固定入口：`public static void main(String[] args)`。
- Java 大小写敏感。
- 基本数据类型：`byte`、`short`、`int`、`long`、`float`、`double`、`char`、`boolean`。
- 引用类型：类、接口、数组、枚举、`String`。
- `boolean` 不是整数。
- 运算符：算术、关系、逻辑、位、赋值、三目、`instanceof`。
- 自动类型转换与强制类型转换。
- 分支、循环、`break`、`continue`、`return`。

## 源文件与 main 方法

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

复习时要能解释这行输出语句：

- `System` 是类。
- `out` 是标准输出对象。
- `println` 是方法。
- `.` 表示成员访问。

常见分隔符：

- `()` 用于参数列表、表达式分组。
- `{}` 用于类体、方法体、代码块。
- `[]` 用于数组。
- `;` 表示语句结束。
- `,` 分隔多个变量或参数。
- `.` 访问对象或类的成员。

## 标识符与命名

标识符要求：

- 不能是关键字。
- 大小写敏感。
- 推荐类名用大驼峰，如 `StudentScore`。
- 变量名、方法名用小驼峰，如 `studentName`、`getScore`。

## 数据类型

| 分类 | 类型 |
| --- | --- |
| 整数 | `byte`、`short`、`int`、`long` |
| 浮点 | `float`、`double` |
| 字符 | `char` |
| 布尔 | `boolean` |
| 引用 | 类、接口、数组、枚举、`String` |

```java
int age = 20;
double score = 89.5;
char grade = 'A';
boolean passed = true;
String name = "张三";
```

## 运算符

### 自增自减

```java
int a = 5;
int b = a++; // b=5, a=6

int x = 5;
int y = ++x; // x=6, y=6
```

`i++` 先用后加，`++i` 先加后用。

### 逻辑运算

| 运算符 | 含义 | 是否短路 |
| --- | --- | --- |
| `!` | 非 | - |
| `&` | 与 | 不短路 |
| `|` | 或 | 不短路 |
| `^` | 异或 | 不短路 |
| `&&` | 短路与 | 短路 |
| `||` | 短路或 | 短路 |

```java
int n = 0;
if (n != 0 && 10 / n > 1) {
    System.out.println("safe");
}
```

这里必须用 `&&`，因为左边为 `false` 后右边不会计算，避免除零。

### 三目运算符

```java
int score = 75;
String result = score >= 60 ? "及格" : "不及格";
```

## 类型转换

自动转换方向：

```text
byte / short / char -> int -> long -> float -> double
```

```java
int a = 10;
double b = a;       // 自动转换

double x = 3.14;
int y = (int) x;    // 强制转换，y=3
```

向下转型对象时先用 `instanceof` 判断，避免 `ClassCastException`。

```java
Object obj = "hello";
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}
```

## 控制流程

### if

```java
if (score >= 90) {
    System.out.println("优秀");
} else if (score >= 60) {
    System.out.println("及格");
} else {
    System.out.println("不及格");
}
```

条件表达式必须是 `boolean`。

### switch

```java
int day = 3;
switch (day) {
    case 1:
        System.out.println("周一");
        break;
    case 2:
        System.out.println("周二");
        break;
    default:
        System.out.println("其他");
}
```

### 循环

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}

int n = 5;
while (n > 0) {
    System.out.println(n);
    n--;
}

do {
    System.out.println("至少执行一次");
} while (false);
```

`break` 结束循环，`continue` 跳过本次循环剩余语句，`return` 结束方法。

## 示例：成绩统计

```java
public class ScoreDemo {
    public static void main(String[] args) {
        int[] scores = {90, 85, 78, 92, 88};
        int sum = 0;

        for (int score : scores) {
            sum += score;
        }

        double avg = (double) sum / scores.length;
        System.out.printf("平均分：%.2f%n", avg);
    }
}
```

关键点：`sum / scores.length` 如果都是整数会做整数除法，所以要把其中一个转换为 `double`。

## 易错点

- 忘记 `main` 方法中的 `static`。
- 把 `boolean` 当 `0/1` 使用。
- `if (a = 3)` 在 Java 中不是合法条件，因为结果不是布尔值。
- 忘记 `switch` 中的 `break` 导致穿透。
- `i++` 与 `++i` 出现在表达式中时混淆。
- 强制类型转换可能丢失精度。

## 自测题

1. 一个 `.java` 文件中能有几个 `public class`？文件名有什么要求？
2. `int b = a++;` 和 `int b = ++a;` 区别是什么？
3. `&&` 和 `&` 有什么区别？
4. 为什么 `double avg = sum / count;` 可能算错平均值？
5. `break`、`continue`、`return` 分别结束什么？

