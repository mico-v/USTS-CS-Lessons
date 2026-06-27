# 07 数组、字符串、泛型与集合

## 材料来源

- `java9 数组.pdf`
- `java9-2 - 字符串.pdf`
- `java10 泛型+集合.pdf`

## 本讲复习目标

- 掌握数组初始化、遍历、复制、查找、排序和二维数组。
- 掌握 `String` 不可变、字符串池、常用方法和 `StringBuilder`。
- 理解泛型类、泛型方法、通配符和类型擦除。
- 掌握 `ArrayList`、`HashSet`、`HashMap`、`LinkedList`、`Collections`。

## 考点清单

- 数组长度固定、元素类型相同、下标从 0 开始，数组是引用类型。
- 推荐声明：`int[] scores`；`int scores[]` 合法但不推荐。
- 动态初始化有默认值；静态初始化直接给元素。
- 数组长度是属性 `length`，不是 `length()`。
- 下标范围是 `0..length-1`。
- 数组赋值复制引用，不复制内容。
- 增强 `for` 适合读，不适合按下标修改。
- 二分查找前必须排序。
- `String` 是 `final` 类，不可变；内容比较用 `equals`。
- 大量拼接用 `StringBuilder`。
- 泛型提供编译期类型检查；运行期有类型擦除。
- `Collection` 下有 `List`、`Set`、`Queue`；`Map` 不属于 `Collection`。

## 数组基础

```java
int[] scores = new int[5];       // 动态初始化，默认值为0
int[] nums = {1, 2, 3, 4, 5};    // 静态初始化

System.out.println(nums.length); // 5
System.out.println(nums[0]);     // 1
```

常见默认值：

| 元素类型 | 默认值 |
| --- | --- |
| 整数 | `0` |
| 浮点 | `0.0` |
| `char` | `\u0000` |
| `boolean` | `false` |
| 引用类型 | `null` |

## 遍历与统计

```java
int[] scores = {90, 85, 78, 92, 88};
int sum = 0;
int max = scores[0];
int min = scores[0];

for (int i = 0; i < scores.length; i++) {
    sum += scores[i];
    if (scores[i] > max) max = scores[i];
    if (scores[i] < min) min = scores[i];
}

double avg = (double) sum / scores.length;
System.out.printf("平均分：%.2f 最高分：%d 最低分：%d%n", avg, max, min);
```

最大值、最小值建议从 `arr[0]` 初始化，不要随便写 `0`，否则全负数数组会出错。

## 数组复制

数组变量赋值只复制引用：

```java
int[] a = {1, 2, 3};
int[] b = a;
b[0] = 99;
System.out.println(a[0]); // 99
```

复制数组内容：

```java
int[] a = {1, 2, 3};

int[] b = new int[a.length];
for (int i = 0; i < a.length; i++) {
    b[i] = a[i];
}

int[] c = java.util.Arrays.copyOf(a, a.length);
int[] d = a.clone();
```

二维数组的 `clone()` 是浅拷贝，内部每一行数组仍可能共享。

## 查找、排序与反转

线性查找：

```java
static int indexOf(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) return i;
    }
    return -1;
}
```

二分查找要求数组已有序：

```java
int[] arr = {1, 3, 5, 7, 9};
int index = java.util.Arrays.binarySearch(arr, 7);
```

反转数组：

```java
static void reverse(int[] arr) {
    int left = 0;
    int right = arr.length - 1;
    while (left < right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
        left++;
        right--;
    }
}
```

冒泡排序：

```java
static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                int tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
            }
        }
    }
}
```

## 二维数组与可变参数

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};

for (int r = 0; r < matrix.length; r++) {
    for (int c = 0; c < matrix[r].length; c++) {
        System.out.print(matrix[r][c] + " ");
    }
    System.out.println();
}
```

`matrix.length` 是行数，`matrix[r].length` 是第 `r` 行列数。Java 支持不规则二维数组。

```java
static int sum(int... nums) {
    int total = 0;
    for (int n : nums) total += n;
    return total;
}
```

`int... nums` 在方法内部按数组使用。

## String

`String` 是引用类型，也是 `final` 类。字符串对象不可变，修改类方法返回新字符串。

```java
String s = "hello";
s.toUpperCase();
System.out.println(s); // hello

s = s.toUpperCase();
System.out.println(s); // HELLO
```

字符串池与比较：

```java
String a = "java";
String b = "java";
String c = new String("java");

System.out.println(a == b);      // true
System.out.println(a == c);      // false
System.out.println(a.equals(c)); // true
```

对象引用是否相同用 `==`，内容是否相同用 `equals`。

## String 常用方法

| 方法 | 作用 |
| --- | --- |
| `length()` | 字符串长度 |
| `charAt(i)` | 取第 `i` 个字符 |
| `indexOf(s)` | 查找子串位置 |
| `contains(s)` | 是否包含 |
| `startsWith(s)` / `endsWith(s)` | 是否以某内容开头/结尾 |
| `isEmpty()` | 是否空串 |
| `substring(b, e)` | 截取 `[b, e)` |
| `trim()` / `strip()` | 去除首尾空白 |
| `replace(a, b)` | 替换 |
| `toUpperCase()` / `toLowerCase()` | 大小写转换 |
| `split(regex)` | 按正则拆分 |
| `toCharArray()` | 转字符数组 |

`split` 参数是正则表达式，特殊字符要转义。

```java
String ip = "192.168.1.1";
String[] parts = ip.split("\\.");

String text = "a|b|c";
String[] tokens = text.split("\\|");
```

## StringBuilder

循环拼接字符串时使用 `StringBuilder`。

```java
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= 5; i++) {
    sb.append(i).append(",");
}
String result = sb.toString();
```

常用方法：`append`、`insert`、`delete`、`reverse`、`replace`、`toString`。`StringBuffer` 与 `StringBuilder` 类似，但同步，通常更慢。

## 泛型

泛型把类型检查提前到编译期，减少强制转换。

```java
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

Box<String> box = new Box<>();
box.set("hello");
String s = box.get();
```

泛型不能使用基本类型参数，应该使用包装类。

```java
// ArrayList<int> list = new ArrayList<>(); // 错误
ArrayList<Integer> list = new ArrayList<>();
```

泛型方法：

```java
static <T> void printArray(T[] arr) {
    for (T item : arr) {
        System.out.println(item);
    }
}
```

有界类型参数：

```java
static <T extends Number> double sum(T[] arr) {
    double total = 0;
    for (T item : arr) {
        total += item.doubleValue();
    }
    return total;
}
```

通配符：

- `<?>`：未知类型。
- `<? extends T>`：读取为主，生产者。
- `<? super T>`：写入为主，消费者。
- PECS：Producer Extends, Consumer Super。

类型擦除：泛型主要在编译期生效，运行期类型参数会被擦除。

## 集合框架

| 类型 | 特点 | 典型用途 |
| --- | --- | --- |
| `ArrayList` | 动态数组、有序、可重复 | 按下标访问、保存列表 |
| `HashSet` | 无序、不可重复 | 去重、集合判断 |
| `HashMap` | 键值对、键唯一 | 通过 key 查 value |
| `LinkedList` | 链表，实现 `List`、`Queue`、`Deque` | 队列、栈操作 |

### ArrayList

```java
import java.util.ArrayList;

ArrayList<String> names = new ArrayList<>();
names.add("张三");
names.add("李四");
names.add(1, "王五");

System.out.println(names.get(0));
names.set(0, "赵六");
names.remove("李四");
System.out.println(names.size());
```

### HashSet

```java
import java.util.HashSet;

HashSet<String> set = new HashSet<>();
set.add("Java");
set.add("Java");
set.add("OOP");

System.out.println(set.size()); // 2
```

### HashMap

```java
import java.util.HashMap;
import java.util.Map;

HashMap<String, Integer> scores = new HashMap<>();
scores.put("张三", 90);
scores.put("李四", 85);
scores.put("张三", 95); // 覆盖旧值

System.out.println(scores.get("张三"));
System.out.println(scores.getOrDefault("王五", 0));

for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

### LinkedList

```java
import java.util.LinkedList;

LinkedList<String> queue = new LinkedList<>();
queue.offer("A");
queue.offer("B");
System.out.println(queue.peek()); // A
System.out.println(queue.poll()); // A

LinkedList<String> stack = new LinkedList<>();
stack.push("A");
stack.push("B");
System.out.println(stack.pop());  // B
```

### Iterator 删除

```java
import java.util.ArrayList;
import java.util.Iterator;

ArrayList<Integer> nums = new ArrayList<>();
nums.add(1);
nums.add(2);
nums.add(3);

Iterator<Integer> it = nums.iterator();
while (it.hasNext()) {
    if (it.next() % 2 == 0) {
        it.remove();
    }
}
```

### Collections

```java
import java.util.ArrayList;
import java.util.Collections;

ArrayList<Integer> nums = new ArrayList<>();
Collections.addAll(nums, 3, 1, 2);
Collections.sort(nums);
Collections.reverse(nums);
Collections.shuffle(nums);

System.out.println(Collections.max(nums));
System.out.println(Collections.min(nums));
```

## 综合示例：成绩册

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Student {
    private String name;
    private int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

public class GradeBook {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("张三", 90));
        students.add(new Student("李四", 85));

        students.sort(Comparator.comparingInt(Student::getScore).reversed());

        HashMap<String, Integer> map = new HashMap<>();
        for (Student s : students) {
            map.put(s.getName(), s.getScore());
        }

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}
```

## 易错点

- `arr[arr.length]` 一定越界。
- 数组长度写成 `arr.length()`。
- 直接打印数组，应改用 `Arrays.toString(arr)`。
- 二分查找前忘记排序。
- 字符串内容比较用 `==`。
- `String` 方法调用后忘记接收返回值。
- `split(".")` 误把点当正则任意字符。
- 泛型类型参数写基本类型。
- `HashMap.put` 同 key 会覆盖旧值。

## 自测题

1. 数组为什么不能动态改变长度？
2. `int[] b = a;` 和 `Arrays.copyOf(a, a.length)` 有什么区别？
3. 为什么字符串循环拼接推荐 `StringBuilder`？
4. 泛型解决了旧式 `Object` 容器的什么问题？
5. `List`、`Set`、`Map` 的核心区别是什么？

