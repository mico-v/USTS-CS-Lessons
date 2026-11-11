# 第 2 章 线性表

> 全书代码量最大的一章。考法固定为三种：**写插入/删除算法（含异常判断）**、**算平均移动次数**、**画链表指针图**。顺序表和单链表的代码要能默写，双向链表要能写对四条指针语句的顺序。

---

## 一、线性表的基本概念

线性表是由 $n\ (n \ge 0)$ 个数据元素（结点）构成的**有限序列**，逻辑表示为

$$(a_0, a_1, \dots, a_{n-1})$$

- $n$ 为线性表的长度，$n=0$ 时称为**空表**；
- $i$ 是 $a_i$ 在线性表中的**位序号**（注意课件从 0 开始编号）。

逻辑结构三个特点：

1. 由 $n$ 个**同类型**的元素组成；
2. 有且仅有一个第一个元素（开始结点）和最后一个元素（终端结点）；
3. 除首尾外，每个元素有且仅有一个前驱和一个后继。

### 1.1 抽象数据类型（Java 接口）

课件给了 8 个基本操作，考试写算法时直接引用这些名字：

| 操作 | 含义 |
| --- | --- |
| `clear()` | 置空表 |
| `isEmpty()` | 判空 |
| `length()` | 求长度 |
| `get(i)` | 取第 $i$ 个元素 |
| `insert(i, x)` | 在第 $i$ 个元素**之前**插入 `x` |
| `remove(i)` | 删除第 $i$ 个元素 |
| `indexOf(x)` | 查找 `x` 首次出现的位置，找不到返回 `-1` |
| `display()` | 输出全部元素 |

```java
public interface IList {
    public void clear();
    public boolean isEmpty();
    public int length();
    public Object get(int i) throws Exception;
    public void insert(int i, Object x) throws Exception;
    public void remove(int i) throws Exception;
    public int indexOf(Object x);
    public void display();
}
```

> 记住 `insert(i, x)` 是在第 $i$ 个元素**之前**插，$i$ 的合法范围是 $0 \le i \le n$（可以插在表尾后面）；`remove(i)` 的合法范围是 $0 \le i \le n-1$。所有算法题的第一分都是这两个边界判断。

## 二、顺序存储：顺序表

### 2.1 概念与特点

用一组**地址连续**的存储单元依次存放线性表的元素，称为顺序表。第一个元素的地址称为**基地址**。

特点（四条，简答题原话）：

1. 逻辑上相邻的元素存储位置也相邻；
2. **存储密度高**——$\text{存储密度} d = \dfrac{\text{数据元素的值所需空间}}{\text{该元素实际所需空间}}$（顺序表为 1，链表小于 1）；
3. **可随机存取**，`get(i)` 是 $O(1)$；
4. 不便于插入、删除（会引起大量结点移动）。

### 2.2 类的描述

```java
public class SqList implements IList {
    private Object[] listElem;   // 线性表存储空间
    private int curLen;          // 当前长度

    public SqList(int maxSize) {         // 构造容量为 maxSize 的空表
        curLen = 0;
        listElem = new Object[maxSize];
    }

    public void clear() { curLen = 0; }
    public boolean isEmpty() { return curLen == 0; }
    public int length() { return curLen; }

    public Object get(int i) throws Exception {
        if (i < 0 || i > curLen - 1)
            throw new Exception("第" + i + "个元素不存在");
        return listElem[i];
    }
}
```

### 2.3 插入（算法 2.1）

要求：在第 $i$ 个元素 $a_i$ 之前插入值为 `x` 的元素。

算法步骤：

1. 若 `curLen >= listElem.length`，空间已满，抛异常；
2. 若 `i < 0 || i > curLen`，插入位置不合法，抛异常；
3. 从表尾开始，把第 $i$ 个位置及之后的元素**依次后移一位**；
4. 把 `x` 放入 `listElem[i]`，表长加 1。

```java
public void insert(int i, Object x) throws Exception {
    if (curLen == listElem.length)
        throw new Exception("顺序表已满");
    if (i < 0 || i > curLen)
        throw new Exception("插入位置不合法");
    for (int j = curLen; j > i; j--)      // 后移
        listElem[j] = listElem[j - 1];
    listElem[i] = x;                      // 插入
    curLen++;                             // 表长加 1
}
```

**性能分析**（考试必算）：在第 $i\ (0 \le i \le n)$ 个元素之前插入，需要后移 $n - i$ 个元素。等概率 $P_i = \frac{1}{n+1}$ 时，平均移动次数

$$E_{\text{ins}} = \sum_{i=0}^{n} \frac{1}{n+1}(n-i) = \frac{1}{n+1}\cdot\frac{n(n+1)}{2} = \frac{n}{2}$$

时间复杂度 $O(n)$。最好情况在表尾插入（移动 0 次），最坏在表头插入（移动 $n$ 次）。

### 2.4 删除（算法 2.2）

```java
public void remove(int i) throws Exception {
    if (i < 0 || i > curLen - 1)
        throw new Exception("删除位置不合法");
    for (int j = i; j < curLen - 1; j++)   // 前移
        listElem[j] = listElem[j + 1];
    curLen--;                              // 表长减 1
}
```

删除第 $i\ (0 \le i \le n-1)$ 个元素需前移 $n - i - 1$ 个元素，等概率下平均移动

$$E_{\text{del}} = \frac{1}{n}\sum_{i=0}^{n-1}(n-i-1) = \frac{n-1}{2}$$

时间复杂度 $O(n)$。

> 两个平均值必须记死：**插入平均移动 $n/2$ 次，删除平均移动 $(n-1)/2$ 次**。题目如果问“在表头”“在表尾”，就对号入座用 $n-i$ / $n-i-1$。

### 2.5 查找（算法 2.3）

```java
public int indexOf(Object x) {
    int j = 0;
    while (j < curLen && !listElem[j].equals(x))
        j++;
    if (j < curLen) return j;
    else return -1;
}
```

若 `x` 在第 $i$ 个位置，需比较 $i+1$ 次；等概率下平均比较次数 $\frac{n+1}{2}$，时间复杂度 $O(n)$。

> Java 里比较内容必须用 `.equals()`，用 `==` 比的是引用，这是改错题的高发点。

## 三、链式存储：单链表

### 3.1 结点与头结点

用一组**地址任意**的存储单元存放元素，每个结点 = 数据域 + 指针域（指示后继位置），用“结点的序列”表示线性表，称为链表。

- **头指针**：指向链表第一个结点的指针，代表整个链表。
- **头结点**：在首结点之前虚加的一个结点，其数据域通常不用，指针域指向首结点。**带头结点可以让插入、删除算法统一，不必特殊处理表头**。
- 空表时头结点的指针域为空。

```java
public class Node {
    public Object data;    // 结点值
    public Node next;      // 后继结点的引用

    public Node() { this(null, null); }
    public Node(Object data) { this(data, null); }
    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }
}
```

### 3.2 单链表类

```java
public class LinkList implements IList {
    public Node head;                  // 头指针

    public LinkList() { head = new Node(); }   // 初始化头结点

    public LinkList(int n, boolean Order) throws Exception {
        this();
        if (Order) create1(n);         // 尾插法，顺序
        else create2(n);               // 头插法，逆序
    }

    public void clear() { head.data = null; head.next = null; }
    public boolean isEmpty() { return head.next == null; }
}
```

### 3.3 求长度

```java
public int length() {
    Node p = head.next;
    int length = 0;
    while (p != null) {
        p = p.next;
        ++length;
    }
    return length;
}
```

### 3.4 按位序号查找 get(i)（算法 2.4）

单链表是**顺序存取**结构：为取第 $i$ 个元素，必须先找到第 $i-1$ 个元素，从头“点数”。

```java
public Object get(int i) throws Exception {
    Node p = head.next;
    int j = 0;
    while (p != null && j < i) {
        p = p.next;
        ++j;
    }
    if (j > i || p == null)
        throw new Exception("第" + i + "个元素不存在");
    return p.data;
}
```

> 课件在这里问：能否把初始化改成 `p = head; j = -1;`？可以——把**头结点当成第 -1 个结点**，后面的循环条件和判断都不用改。这种“计数器基准问题”是常见的改错题。

### 3.5 按值查找 indexOf(x)（算法 2.5）

```java
public int indexOf(Object x) {
    Node p = head.next;
    int j = 0;
    while (p != null && !p.data.equals(x)) {
        p = p.next;
        ++j;
    }
    if (p != null) return j;
    else return -1;
}
```

时间复杂度 $O(n)$。

### 3.6 插入 insert(i, x)（算法 2.6）

在带头结点的单链表中第 $i$ 个结点之前插入 `x`，$0 \le i \le n$。

三步：找到第 $i-1$ 个结点 `p` → 生成新结点 `s` → `s.next = p.next; p.next = s;`（顺序不能反，反了会断链）。

```java
public void insert(int i, Object x) throws Exception {
    Node p = head;
    int j = -1;
    while (p != null && j < i - 1) {
        p = p.next;
        ++j;
    }
    if (j > i - 1 || p == null)
        throw new Exception("插入位置不合法");
    Node s = new Node(x);
    s.next = p.next;
    p.next = s;
}
```

### 3.7 删除 remove(i)

```java
public void remove(int i) throws Exception {
    Node p = head;
    int j = -1;
    while (p.next != null && j < i - 1) {
        p = p.next;
        ++j;
    }
    if (j > i - 1 || p.next == null)
        throw new Exception("删除位置不合法");
    if (i == length() - 1) p.next = null;        // 删尾结点
    else p.next = p.next.next;                   // 常规删除
}
```

> 课件在删除这里留了两个思考题，都是以“取前驱”为核心的变体：
> - 不带表头结点的单链表怎么写插入？（表头要单独判断，这正是头结点存在的意义）
> - 删除值为 `x` 的结点怎么写？（改成 `while (p.next != null && !p.next.data.equals(x))`，边找边删）

### 3.8 建立单链表：头插法与尾插法

链表是动态结构，建表过程就是结点“逐个插入”。

```java
// 头插法（逆位序法，算法 2.9）：每次插到表头，读入顺序与链表顺序相反
public void create2(int n) throws Exception {
    Scanner sc = new Scanner(System.in);
    for (int j = 0; j < n; j++)
        insert(0, sc.next());
}

// 尾插法（顺序法，算法 2.10）：每次插到表尾，顺序一致
public void create1(int n) throws Exception {
    Scanner sc = new Scanner(System.in);
    for (int j = 0; j < n; j++)
        insert(length(), sc.next());
}
```

| 方法 | 结果 | 时间复杂度 |
| --- | --- | --- |
| 头插法 `create2` | 逆序 | $O(n)$ |
| 尾插法 `create1` | 顺序 | 上面这种写法是 $O(n^2)$（每次 `length()` 都要遍历） |

> 考试如果问“尾插法建表的最优复杂度”，答 $O(n)$——用尾指针 `rear` 保存表尾即可，不必每次求长度。

## 四、其它链表

### 4.1 循环链表

最后一个结点的指针指回头结点。

- 判空条件：`head.next == head`；
- 与单链表的**唯一差别**：判到表尾的条件不是“后继为空”，而是“后继是否为头结点”；
- 特点：从任一结点出发都能访问全部结点；用**头指针**找开始结点 $O(1)$、找终端结点 $O(n)$；用**尾指针**找开始结点和终端结点都是 $O(1)$。

课件的经典题：把 (a₀,…,aₙ₋₁) 和 (b₀,…,bₘ₋₁) 合成 (a₀,…,aₙ₋₁,b₀,…,bₘ₋₁)，用尾指针 `taila`、`tailb` 只需两句：

```java
Node p = tailb.next;          // 暂存 B 表的头结点
tailb.next = taila.next;      // ① B 的表尾接 A 的头结点（A 的首结点）
taila.next = p;               // ② A 的表尾接 B 的首结点
```

### 4.2 双向链表

```java
public class DuLNode {
    public Object data;
    public DuLNode prior;   // 前驱
    public DuLNode next;    // 后继
}
```

双向循环链表判空条件：`head.next == head`（课件写作 `head.getNext() == head`）。

**查询和单链表相同，插入删除要同时改两个方向**。

在 `p` 所指结点**之后**插入新结点 `s`，四句顺序不能乱（1、2 必须在 3、4 之前，否则 `p.next` 被覆盖）：

```java
s.next = p.next;      // ① s 的后继
p.next = s;           // ② p 的后继改成 s
s.next.prior = s;     // ③ 原后继的前驱改成 s
s.prior = p;          // ④ s 的前驱
```

删除 `p` 之后的结点：

```java
p.next = p.next.next;   // ① 跨过被删结点
p.next.prior = p;       // ② 新后继的前驱指回 p
```

> 课件留了两个思考题，都是把上面四句/两句对着写：
> - 在 `p` **之前**插入 `s`：`s.prior = p.prior; p.prior.next = s; s.next = p; p.prior = s;`
> - 删除 `p` 所指结点：`p.prior.next = p.next; p.next.prior = p.prior;`

## 五、顺序表与链表的比较（简答必考）

| 比较项 | 顺序表 | 链表 |
| --- | --- | --- |
| 空间大小 | 相对固定（预先分配） | 可动态变化，较灵活 |
| 存储密度 | 较高（只存元素值） | 较低（还要存指针） |
| 创建 | 简单（用数组） | 较复杂（头插/尾插） |
| 存取操作 | 简单，**随机存取** $O(1)$ | 较复杂，**顺序存取** $O(n)$ |
| 插入 | 不方便（元素移动） | 方便（只改链） |
| 删除 | 不方便（元素移动） | 方便（只改链） |

记忆线索：**顺序表“查得快、改得慢”，链表“改得快、查得慢”**。如果题目说“经常按序号访问、很少插入删除”，选顺序表；反之选链表。

## 六、应用举例

### 6.1 删除单链表中的重复结点

思路：从首结点开始，每个结点与它后面的所有结点比较，相等就删。

```java
Node p = L.head.next, q;
while (p != null) {
    int order = L.indexOf(p.data);      // 后面的 remove 要用位置
    q = p.next;
    while (q != null) {
        if (p.data.equals(q.data))
            L.remove(order + 1);        // 删掉重复的那个
        else
            ++order;
        q = q.next;
    }
    p = p.next;
}
```

复杂度 $O(n^2)$。若用“双重循环 + 双指针”改写可以省掉 `indexOf` 的重复遍历。

### 6.2 合并两个有序单链表

设 LA、LB 按值非递减有序，合并到 LA。用三个指针 `pa`、`pb`、`pc`：

```java
public static LinkList mergeList_L(LinkList LA, LinkList LB) {
    Node pa = LA.head.next;
    Node pb = LB.head.next;
    Node pc = LA.head;
    int da, db;
    while (pa != null && pb != null) {
        da = Integer.valueOf(pa.data.toString());
        db = Integer.valueOf(pb.data.toString());
        if (da <= db) { pc.next = pa; pc = pa; pa = pa.next; }
        else          { pc.next = pb; pc = pb; pb = pb.next; }
    }
    pc.next = (pa != null ? pa : pb);   // 接剩余段
    return LA;
}
```

时间复杂度 $O(m+n)$，空间 $O(1)$（直接改链，不新建结点）。

### 6.3 一元多项式加法

系数 + 指数用线性表表示，稀疏多项式用链表存 `(coef, expn)`：

```java
public class PolynNode {
    public double coef;   // 系数
    public int expn;      // 指数
    public PolynNode(double coef, int expn) {
        this.coef = coef;
        this.expn = expn;
    }
}
```

加法用两路归并：比较指数，小的往后接，相等则系数相加（和为 0 要删除该项）。

```java
switch (cmp(a, b)) {          // a = qa.expn, b = qb.expn
    case -1: qa = qa.next; break;                 // LA 指数小，跳过
    case 0:                                       // 指数相等
        double sum = a.coef + b.coef;
        if (sum != 0.0) { a.coef = sum; ha.next = qa; ha = qa; qa = qa.next; qb = qb.next; }
        else            { qa = qa.next; qb = qb.next; }
        break;
    case 1: ha.next = qb; ha = qb; qb = qb.next; break;   // LB 指数小
}
```

> 为什么不能用 `(p₀, p₁, …, pₙ)` 顺序存储？因为 $S(x)=1+3x^{10000}-2x^{20000}$ 这种稀疏多项式要浪费两万个存储单元。这是“存储密度”的经典论证题。

## 七、易错点清单

1. `insert` 的 $i$ 范围是 $0 \le i \le n$，`remove` 是 $0 \le i \le n-1$，`get` 也是 $0 \le i \le n-1$。
2. 顺序表插入是**从后往前**搬（`j` 递减），删除是**从前往后**搬（`j` 递增），方向写反数据就错。
3. 链表插入的顺序：先 `s.next = p.next`，后 `p.next = s`；反了就丢链。
4. 带头结点 ≠ 带头指针。头结点是结点，头指针是指向第一个结点的引用/指针。
5. `p.next = p.next.next` 删的是 `p` 的后继，不是 `p`；删除 `p` 本身必须先找到它的前驱。
6. 循环链表判尾用 `p.next == head`，不是 `p.next == null`。
7. 双向链表插入四句的顺序：**先给 s 的两条腿，再改邻居的两条腿**。

## 八、本章作业（课件布置）

教材习题二：三、1、3、4、6（均为算法设计题，按“算法步骤 + 代码 + 复杂度”三段写）。
