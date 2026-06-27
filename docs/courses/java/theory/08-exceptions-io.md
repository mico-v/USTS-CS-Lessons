# 08 异常处理与输入输出流

## 材料来源

- `java11 异常.pdf`
- `java14 输入输出流.pdf`

## 本讲复习目标

- 理解异常是对象，掌握 `Throwable` 继承层次。
- 区分 checked 异常、运行时异常和 `Error`。
- 掌握 `try-catch-finally`、多重 `catch`、`throw`、`throws`、自定义异常。
- 掌握 Java I/O 的流概念、四大抽象父类、节点流与处理流。
- 掌握缓冲流、标准流、格式化 I/O、序列化、`RandomAccessFile` 与 `File`。

## 考点清单

- 异常处理让业务逻辑与错误处理解耦，异常沿调用栈传播。
- `Throwable` 是所有异常和错误的父类。
- `Error` 表示 JVM 严重错误，通常不由程序处理。
- `Exception` 是程序员主要处理对象。
- `RuntimeException` 是常见非受检异常。
- checked 异常编译器强制处理；unchecked 异常不强制。
- `catch` 顺序必须从子类到父类。
- `finally` 无论是否异常通常都会执行。
- 不要在 `finally` 中写 `return` 覆盖结果。
- `try-with-resources` 自动关闭实现 `AutoCloseable` 的资源。
- `throws` 写在方法签名中声明抛出；`throw` 写在方法体中实际抛出对象。
- 流按方向分输入/输出，按单位分字节/字符。
- 字节流适合二进制，字符流适合文本。
- 处理流包装节点流，提供更高效率或更多功能。

## 异常的本质

异常也是对象。

```java
ArithmeticException e = new ArithmeticException("/ by zero");
throw e;
```

核心继承层次：

```text
Throwable
├── Error
└── Exception
    └── RuntimeException
```

| 类别 | 父类 | 是否强制处理 | 典型例子 |
| --- | --- | --- | --- |
| Checked | `Exception` 非 `RuntimeException` | 强制 | `IOException`、`SQLException`、`ClassNotFoundException`、`InterruptedException` |
| Runtime | `RuntimeException` | 不强制 | `NullPointerException`、`ArithmeticException`、`ClassCastException` |
| Error | `Error` | 不建议捕获 | `OutOfMemoryError` |

异常对象常用方法：

- `getMessage()`：返回错误描述。
- `printStackTrace()`：打印调用栈。
- `getStackTrace()`：获取调用栈。

## try-catch-finally

```java
try {
    int[] nums = {10, 20, 30};
    System.out.println(nums[3]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("数组下标越界：" + e.getMessage());
} finally {
    System.out.println("处理完毕");
}
```

执行规律：

1. 正常执行完，执行 `finally`。
2. 抛出异常且被 `catch` 捕获，先执行 `catch`，再执行 `finally`。
3. 抛出异常但未捕获，仍执行 `finally`，然后异常继续向外传播。

多重 `catch` 要从具体到一般。

```java
try {
    int n = Integer.parseInt(input);
    int r = 100 / n;
    System.out.println(r);
} catch (NumberFormatException e) {
    System.out.println("输入的不是数字");
} catch (ArithmeticException e) {
    System.out.println("不能除以零");
} catch (Exception e) {
    System.out.println("其他错误");
}
```

JDK 7 起可以合并捕获多个没有继承关系的异常。

```java
catch (IOException | SQLException e) {
    e.printStackTrace();
}
```

材料强调不要在 `finally` 中写会覆盖结果的 `return`。

```java
try {
    return 1;
} finally {
    return 2; // 不推荐，最终返回2
}
```

## try-with-resources

实现 `AutoCloseable` 的资源可以放在 `try(...)` 中自动关闭。

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SumFile {
    public static void main(String[] args) {
        int sum = 0;
        try (Scanner sc = new Scanner(new File("numbers.txt"))) {
            String line = sc.nextLine();
            for (String token : line.split("\\s+")) {
                try {
                    sum += Integer.parseInt(token);
                } catch (NumberFormatException e) {
                    System.out.println("跳过非数字：" + token);
                }
            }
            System.out.println("sum=" + sum);
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } finally {
            System.out.println("处理完毕");
        }
    }
}
```

## throw 与 throws

| 对比项 | `throws` | `throw` |
| --- | --- | --- |
| 位置 | 方法签名 | 方法体 |
| 后接 | 异常类型列表 | 异常对象 |
| 数量 | 可多个，逗号分隔 | 一次一个 |
| 作用 | 声明、向上传递 | 实际抛出 |

```java
public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("age=" + age);
    }
}
```

异常沿调用栈传播，直到被某层捕获或终止程序。

```java
void layer3() throws IOException {
    throw new IOException("最底层");
}

void layer2() throws IOException {
    layer3();
}

void layer1() {
    try {
        layer2();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

覆盖父类方法时，子类方法抛出的受检异常不能比父类更宽。可以抛出相同类型、其子类，或父类异常列表的子集。

## 自定义异常、日志与断言

```java
class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(String message) {
        super(message);
    }
}

class Student {
    private String name;
    private int score;

    public Student(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        if (score < 0 || score > 100) {
            throw new InvalidScoreException("成绩必须在0~100之间，但收到了：" + score);
        }
        this.score = score;
    }
}
```

日志比直接 `print` 更适合记录异常，因为日志有等级、时间、输出目标等信息。

```java
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo {
    private static final Logger logger = Logger.getLogger(Demo.class.getName());

    public static void main(String[] args) {
        logger.info("程序开始运行");
        try {
            int r = 10 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "计算时出错了", e);
        }
        logger.info("程序结束");
    }
}
```

日志等级材料列出：`SEVERE`、`WARNING`、`INFO`、`CONFIG`、`FINE`。

断言与异常：

- `assert` 表示“这是 bug，不该发生”，调试用。
- `Exception` 表示“这是错误，需要处理”，生产用。
- 断言默认关闭，需要 `java -ea Demo` 启用。
- 不要用断言检查用户输入。

## 流的概念

流是由一组有序字节或字符组成的、有起点和终点的数据序列。除 `RandomAccessFile` 外，流通常按顺序读写。

| 方向 | 字节流基类 | 字符流基类 |
| --- | --- | --- |
| 输入：数据源到程序 | `InputStream` | `Reader` |
| 输出：程序到目标 | `OutputStream` | `Writer` |

字节流处理二进制数据，如图片、音频；字符流处理文本，能处理字符编码。

使用流的一般步骤：

1. 创建流对象。
2. 调用 `read()` 或 `write()`。
3. 关闭流，推荐 try-with-resources。

```java
try (FileReader fr = new FileReader("data.txt")) {
    int ch;
    while ((ch = fr.read()) != -1) {
        System.out.print((char) ch);
    }
}
```

## 节点流与处理流

节点流直接连接数据源或目标，处理流包装节点流扩展功能。

```java
BufferedReader br = new BufferedReader(
    new InputStreamReader(
        new FileInputStream("data.txt"), "UTF-8"
    )
);
```

常用读写方法：

| 类 | 方法 | 说明 |
| --- | --- | --- |
| `InputStream` | `int read()` | 读一个字节，末尾返回 `-1` |
| `InputStream` | `int read(byte[] b)` | 读若干字节，返回实际读取数 |
| `OutputStream` | `void write(int b)` | 写一个字节 |
| `OutputStream` | `void write(byte[] b)` | 写字节数组 |
| `Reader` | `int read()` | 读一个字符，末尾返回 `-1` |
| `Reader` | `int read(char[] c)` | 读若干字符 |
| `Writer` | `void write(String s)` | 写字符串 |
| `Writer` | `void flush()` | 刷新缓冲区 |

`FileOutputStream.write(65)` 写入的是字节值 65，也就是字符 `A`，不是字符串 `"65"`。

## 缓冲流

缓冲流在内存中维护缓冲区，批量读写，减少系统调用次数。

```java
try (
    BufferedReader br = new BufferedReader(new FileReader("input.txt"));
    PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))
) {
    String line;
    while ((line = br.readLine()) != null) {
        pw.println(line.toUpperCase());
    }
    pw.flush();
}
```

四种缓冲流：

| 缓冲流 | 包装对象 | 数据类型 | 核心能力 |
| --- | --- | --- | --- |
| `BufferedInputStream` | `InputStream` | 字节 | 加速二进制读取 |
| `BufferedOutputStream` | `OutputStream` | 字节 | 加速二进制写出，`flush()` |
| `BufferedReader` | `Reader` | 字符 | `readLine()` |
| `BufferedWriter` | `Writer` | 字符 | `newLine()`、`flush()` |

`close()` 内含 `flush()`，但程序异常退出前可能来不及 `close()`，关键数据写出后仍应关注刷新。

## 命令行 I/O 与格式化

标准流：

- `System.in`：标准输入流，类型为 `InputStream`。
- `System.out`：标准输出流，类型为 `PrintStream`。
- `System.err`：标准错误输出，类型为 `PrintStream`。

```java
InputStreamReader isr = new InputStreamReader(System.in);
BufferedReader br = new BufferedReader(isr);

System.out.print("请输入您的姓名：");
String name = br.readLine();
System.out.println("你好，" + name + "！");
```

`Console` 支持安全密码输入，但在 IDE 中 `System.console()` 可能返回 `null`。

```java
Console console = System.console();
if (console == null) {
    System.err.println("当前环境不支持 Console");
    return;
}
char[] password = console.readPassword("密码：");
java.util.Arrays.fill(password, ' ');
```

`Scanner` 常用方法：

- `nextLine()`：读一整行。
- `next()`：读一个 token。
- `nextInt()`、`nextDouble()`：读数字。
- `hasNext()`、`hasNextLine()`：判断是否还有数据。
- `useDelimiter()`：设置分隔符。

格式化输出：

| 格式符 | 含义 | 示例 |
| --- | --- | --- |
| `%d` | 整数 | `printf("%5d", 42)` |
| `%f` | 浮点数 | `printf("%.2f", 3.14159)` |
| `%s` | 字符串 | `printf("%-10s", "Hi")` |
| `%c` | 字符 | `printf("%c", 65)` |
| `%n` | 换行 | `printf("a%nb")` |

```java
double r = Math.pow(2.3, 4.0);
System.out.format("format：%8.2f%n", r);
System.out.printf("printf：%-8.2f结束%n", r);
```

## 对象序列化

序列化是对象到字节序列，反序列化是字节序列恢复对象。类需要实现 `Serializable`，这是标记接口。

```java
import java.io.Serializable;

class Employee implements Serializable {
    private String name;
    private int id;
    private transient String password;
}
```

```java
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emp.ser"));
oos.writeObject(new Employee("张三", 1001));
oos.close();

ObjectInputStream ois = new ObjectInputStream(new FileInputStream("emp.ser"));
Employee e = (Employee) ois.readObject();
ois.close();
```

`transient` 修饰字段不参与序列化，反序列化后对象字段为 `null`，基本类型为默认值。常用于密码、缓存、线程对象、数据库连接等。

## RandomAccessFile

`RandomAccessFile` 支持任意位置读写，内部有文件指针。

| 方法 | 说明 |
| --- | --- |
| `getFilePointer()` | 返回当前文件指针位置 |
| `seek(long pos)` | 移动指针 |
| `length()` | 文件总字节数 |
| `readXXX()` / `writeXXX()` | 读写基本类型 |

```java
RandomAccessFile raf = new RandomAccessFile("students.dat", "rw");
raf.seek(0);
raf.writeInt(1001);
raf.writeUTF("张三");
raf.seek(50);
raf.writeInt(1002);
raf.writeUTF("李四");
raf.close();
```

## File 类

`File` 描述文件或目录的属性，不负责读写文件内容。读写内容要用流。

| 分类 | 方法 |
| --- | --- |
| 构造 | `new File(String path)`、`new File(File parent, String child)` |
| 判断 | `exists()`、`isFile()`、`isDirectory()` |
| 获取 | `getName()`、`getAbsolutePath()`、`length()`、`lastModified()` |
| 创建 | `createNewFile()`、`mkdir()`、`mkdirs()` |
| 删除 | `delete()` |
| 列目录 | `list()`、`listFiles()` |

```java
File file = new File("D:\\demo\\test.txt");
System.out.println(file.exists());
System.out.println(file.getAbsolutePath());

new File("D:\\demo").mkdirs();
boolean created = file.createNewFile();
System.out.println("已创建：" + created);

File dir = new File("D:\\demo");
File[] files = dir.listFiles();
for (File f : files) {
    System.out.printf("[%s] %s (%d bytes)%n",
        f.isDirectory() ? "DIR" : "FILE",
        f.getName(), f.length());
}
```

## 易错点

- 多重 `catch` 把父类异常放在子类前。
- `finally` 中写 `return`。
- `throws` 和 `throw` 混淆。
- checked 异常不处理导致编译失败。
- `read()` 到末尾返回 `-1`，`BufferedReader.readLine()` 到末尾返回 `null`。
- 字节流写数字 65 得到字节值，对应 ASCII `A`。
- 忘记关闭流或刷新缓冲。
- 把 `File` 当成读写内容的类。
- 反序列化后忘记强制类型转换。

## 自测题

1. `FileNotFoundException`、`ArithmeticException`、`OutOfMemoryError` 分别属于哪类？
2. 为什么多重 `catch` 要从子类到父类？
3. `throw` 和 `throws` 的区别是什么？
4. 字节流和字符流分别适合什么数据？
5. `File` 类能不能直接读取文件内容？为什么？

