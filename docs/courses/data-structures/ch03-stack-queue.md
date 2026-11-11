# 第 3 章 栈与队列

> 这章的特点是“概念简单、边界条件阴人”。必背：**栈空/栈满条件**、**循环队列空/满条件**、**长度公式**。大题几乎必考一道——要么是循环队列的入队出队，要么是括号匹配/表达式求值。

---

## 一、总起：为什么叫“操作受限的线性表”

栈和队列都是**限定插入和删除只能在线性表的端点进行**的线性表：

| 结构 | 插入 | 删除 | 取出顺序 |
| --- | --- | --- | --- |
| 普通线性表 | `insert(i, x)`，$0 \le i \le n$ | `remove(i)`，$0 \le i \le n-1$ | 任意 |
| 栈 | `insert(n, x)`，只能在表尾 | `remove(n-1)`，只能在表尾 | 后进先出 LIFO / 先进后出 FILO |
| 队列 | `insert(n, x)`，只能在表尾 | `remove(0)`，只能在表头 | 先进先出 FIFO / 后进后出 LILO |

## 二、栈

### 2.1 概念

栈是仅限制在**表尾**进行插入和删除的特殊线性表：允许操作的一端叫**栈顶**，另一端叫**栈底**。

### 2.2 进出栈序列问题（必考）

设有编号 1、2、3、4 的四辆车顺序进一个栈式结构（先进后出）的站台，问所有可能的出站顺序有几种？

$n$ 个元素的出栈序列数为卡特兰数 $\dfrac{1}{n+1}\binom{2n}{n}$，$n=4$ 时是 $\dfrac{1}{5}\times 70 = 14$ 种。课件把 14 种全部列了出来：

```
1234  1243  1324  1342  1432
2134  2143  2314  2341  2431
3214  3241  3421  4321
```

记法：**不可能出现的只有 4123、4132、4231、4312、2 开头但 4 在 2 前的那几类**——判定口诀是“若 3 在 4 之前出现，则 4 之后的元素必须逆序（递减）”。

### 2.3 抽象数据类型

| 操作 | 含义 |
| --- | --- |
| `clear()` | 置空栈 |
| `isEmpty()` | 判空 |
| `length()` | 求长度 |
| `peek()` | 取栈顶元素（**不删除**） |
| `push(x)` | 入栈 |
| `pop()` | 出栈并返回栈顶元素 |

```java
public interface IStack {
    public void clear();
    public boolean isEmpty();
    public int length();
    public Object peek();
    public void push(Object x) throws Exception;
    public Object pop();
}
```

### 2.4 顺序栈

用数组 `stackElem` 存元素，`top` 是栈顶指针。**本课程约定 `top` 指向“栈顶元素的下一个空位”**（和教材 P71 一致）：

| 问题 | 答案 |
| --- | --- |
| 栈空条件 | `top == 0` |
| 栈满条件 | `top == stackElem.length` |
| 栈的长度 | `top` |
| 栈顶元素 | `stackElem[top - 1]` |

```java
public class SqStack implements IStack {
    private Object[] stackElem;
    private int top;

    public SqStack(int maxSize) {
        stackElem = new Object[maxSize];
        top = 0;
    }

    public void clear() { top = 0; }
    public boolean isEmpty() { return top == 0; }
    public int length() { return top; }

    public Object peek() {
        if (!isEmpty()) return stackElem[top - 1];
        else return null;
    }

    // 算法 3.1：入栈
    public void push(Object x) throws Exception {
        if (top == stackElem.length)
            throw new Exception("栈已满");
        else
            stackElem[top++] = x;
    }

    // 算法 3.2：出栈
    public Object pop() {
        if (isEmpty()) return null;
        else return stackElem[--top];
    }

    public void display() {          // 从栈顶到栈底输出
        for (int i = top - 1; i >= 0; i--)
            System.out.print(stackElem[i] + " ");
    }
}
```

> 如果换一套约定（`top` 指向栈顶元素本身），所有条件都要跟着变：空栈 `top == -1`，满栈 `top == length - 1`，长度 `top + 1`。**答题时先写清约定**，否则整题按错处理。

### 2.5 链栈

链栈是运算受限的单链表，插入删除只在表头。**不必加头结点**，栈顶指针就是链表头指针。

| 问题 | 答案 |
| --- | --- |
| 栈空条件 | `top == null` |
| 栈顶元素 | `top.data` |
| 栈的长度 | 从 `top` 沿 `next` 数结点，$O(n)$ |

```java
public class LinkStack implements IStack {
    private Node top;

    public void clear() { top = null; }
    public boolean isEmpty() { return top == null; }

    public int length() {              // 算法 3.3
        Node p = top;
        int length = 0;
        while (p != null) { p = p.next; ++length; }
        return length;
    }

    public Object peek() {
        if (!isEmpty()) return top.data;
        else return null;
    }

    public void push(Object x) {       // 算法 3.4
        Node p = new Node(x);
        p.next = top;
        top = p;
    }

    public Object pop() {              // 算法 3.5
        if (isEmpty()) return null;
        else {
            Node p = top;
            top = top.next;
            return p.data;
        }
    }
}
```

链栈**没有栈满问题**，空间可扩充，适合多栈操作。

### 2.6 栈的应用（四个，全在课件里）

**（1）分隔符匹配（课件例 1，教材 P77-78）**

Java 里的分隔符有圆括号、方括号、花括号，以及注释符 `/*` 和 `*/`。三类错误：

1. 到来的右分隔符和栈顶左分隔符不匹配（`［（ ］）`）；
2. 栈里没有左分隔符却来了右分隔符（“不速之客”）；
3. 整个串读完，栈里还剩左分隔符（“左括弧有余”）。

算法：左括号进栈；遇到右括号先看栈是否空，空则该右括号多余，非空则与栈顶比较，匹配就弹出、不匹配就报错；结束时栈必须为空。

```java
if (LEFT == verifyFlag(t))
    S.push(t);
else if (RIGHT == verifyFlag(t))
    if (S.isEmpty() || !matches(S.pop().toString(), t))
        throw new Exception("错误：Java语句不合法！");
// 扫描结束后
if (!S.isEmpty())
    throw new Exception("错误：Java语句不合法！");
```

> 另外要注意 `/*` 和 `*/` 是**两个字符**的分隔符，扫描时要多看一位并 `++i` 跳过一个字符，这是这道题最容易漏的细节。

**（2）大数加法（课件例 2，教材 P76 图 3.10）**

超过整数上限的数，按数字字符串把每一位压进两个栈 `sA`、`sB`（高位先压，栈顶是低位），逐位弹出相加：

1. 两个栈都非空：弹栈顶相加存到 `partialSum`，有进位则和的个位压入 `sum` 栈、进位数加到下一位；
2. 一个栈空：把另一个栈的剩余位依次与进位相加压栈，最高位若还有进位则压 1 进 `sum`；
3. 两个都空：`sum` 栈即结果，**栈顶是结果的最高位**。

**（3）表达式求值（课件例 3）**

三种表示法（设 $\text{Exp} = S_1 + OP + S_2$）：

| 表示法 | 形式 | 例子（$a \times b + (c - d / e) \times f$） |
| --- | --- | --- |
| 前缀（波兰式） | $OP\ S_1\ S_2$ | `+ * a b * - c / d e f` |
| 中缀 | $S_1\ OP\ S_2$ | `a * b + c - d / e * f` |
| 后缀（逆波兰式） | $S_1\ S_2\ OP$ | `a b * c d e / - f * +` |

结论四条：操作数相对次序不变；运算符相对次序不同；中缀丢了括号，次序不确定；后缀式中运算符出现的顺序恰是运算顺序，每个运算符和它前面紧靠的两个操作数构成一个最小表达式。

运算符优先级：`(` 为 0，`+ -` 为 1，`* / %` 为 2，`^` 为 3。

**中缀转后缀的步骤**（用运算符栈）：

1. 遇操作数 → 直接输出；
2. 遇运算符 → 栈空或优先级高于栈顶则进栈，否则弹出栈顶输出（再重复比较）；
3. 遇 `(` → 进栈；
4. 遇 `)` → 不断弹出并输出，直到弹出 `(` 为止（`(` 丢弃）；
5. 读完 → 把栈里剩下的运算符全部弹出输出。

课件例子 $3*(7-2)$ → `372-*` 的过程：

| 步骤 | 后缀表达式 | 运算符栈 | 说明 |
| --- | --- | --- | --- |
| 1 | | | 读 `3`，输出 |
| 2 | `3` | | 读 `*`，栈空，进栈 |
| 3 | `3` | `*` | 读 `(`，进栈 |
| 4 | `3` | `*(` | 读 `7`，输出 |
| 5 | `37` | `*(` | 读 `-`，优先级高于 `(`，进栈 |
| 6 | `37` | `*(-` | 读 `2`，输出 |
| 7 | `372` | `*(-` | 遇 `)`，弹到 `(` 为止 → 输出 `-` |
| 8 | `372-` | `*` | |
| 9 | `372-` | `*` | 读完，弹出 `*` |
| 10 | `372-*` | | 结束 |

**后缀式求值**：设操作数栈，遇操作数压栈，遇运算符弹出两个操作数运算后把结果压回，最后栈里剩的就是值。

**（4）栈与递归（课件例 4）**

高级语言中函数调用与被调用之间的链接和信息交换**由编译程序通过栈实现**，内存管理是“栈式管理”——后调用先返回。

调用前要做三件事：① 传递实在参数、返回地址等信息给被调函数保存；② 为被调函数的局部变量分配存储区；③ 把控制转到被调函数入口。

返回前要做三件事：① 保存被调函数的计算结果；② 释放被调函数的数据区；③ 按保存的返回地址把控制转回调用函数。

四个名词：**递归工作栈**（递归过程占用的数据区）、**递归工作记录**（每层的递归参数合成一个记录）、**当前活动记录**（栈顶记录）、**当前环境指针**（递归工作栈的栈顶指针）。

汉诺塔（$n$ 个盘从 x 借助 y 移到 z）：

```java
public void hanoi(int n, char x, char y, char z) {
    if (n == 1)
        move(x, 1, z);              // 只剩一个盘，直接搬
    else {
        hanoi(n - 1, x, z, y);      // 上面 n-1 个先搬到 y
        move(x, n, z);              // 第 n 个搬到 z
        hanoi(n - 1, y, x, z);      // 再把 y 上的 n-1 个搬到 z
    }
}
```

移动次数 $T(n) = 2T(n-1) + 1 = 2^n - 1$，时间复杂度 $O(2^n)$，递归深度 $n$，空间复杂度 $O(n)$。

## 三、队列

### 3.1 概念

只允许在表的一端插入（**队尾**）、另一端删除（**队首**）的特殊线性表，先进先出。

| 操作 | 含义 |
| --- | --- |
| `clear()` / `isEmpty()` / `length()` | 同前 |
| `peek()` | 取队首元素（不删除） |
| `offer(x)` | 入队（插到队尾） |
| `poll()` | 出队（删队首并返回） |

```java
public interface IQueue {
    public void clear();
    public boolean isEmpty();
    public int length();
    public Object peek();
    public void offer(Object x) throws Exception;
    public Object poll();
}
```

### 3.2 顺序队列与“假溢出”

普通顺序队列用 `front`、`rear` 两个指针：队空 `front == rear`，队满 `rear == queueElem.length`；入队 `queueElem[rear++] = x`，出队 `return queueElem[front++]`。

**假溢出**：随着反复入队出队，`front` 不断增大，数组前面空出一大片，但 `rear` 已经顶到数组尾，明明还有空位却报“队满”。解决办法是把数组看成**首尾相接的循环队列**。

### 3.3 循环队列（重点）

用“少用一个元素空间”的方案（教材 P91 的 `CircleSqQueue`）：`front` 指向队首元素，`rear` 指向**队尾元素的下一个位置**。

| 问题 | 公式 |
| --- | --- |
| 队空 | `front == rear` |
| 队满 | `(rear + 1) % queueElem.length == front` |
| 队列长度 | `(rear - front + queueElem.length) % queueElem.length` |
| 队首元素 | `queueElem[front]` |
| 队尾元素 | `queueElem[(rear - 1 + len) % len]` |

课件还给了另外两种判定方案（三种都要知道）：

1. **设标志位 `flag`**，初值 0，入队成功后置 1，出队成功后置 0：队空 `front == rear && flag == 0`，队满 `front == rear && flag == 1`。
2. **设计数变量 `num`**，初值 0，入队 +1、出队 −1：队空 `num == 0`（或 `front == rear && num == 0`），队满 `num == queueElem.length`（或 `front == rear && num > 0`）。
3. **少用一个元素空间**（最常用，也是本课程代码采用的）。

```java
public class CircleSqQueue implements IQueue {
    private Object[] queueElem;
    private int front;
    private int rear;

    public CircleSqQueue(int maxSize) {
        queueElem = new Object[maxSize];
        front = rear = 0;
    }

    public void clear() { front = rear = 0; }
    public boolean isEmpty() { return front == rear; }
    public int length() {
        return (rear - front + queueElem.length) % queueElem.length;
    }

    public Object peek() {
        if (front == rear) return null;
        else return queueElem[front];
    }

    // 算法 3.6：入队
    public void offer(Object x) throws Exception {
        if ((rear + 1) % queueElem.length == front)
            throw new Exception("队列已满");
        else {
            queueElem[rear] = x;
            rear = (rear + 1) % queueElem.length;
        }
    }

    // 算法 3.7：出队
    public Object poll() {
        if (front == rear) return null;
        else {
            Object t = queueElem[front];
            front = (front + 1) % queueElem.length;
            return t;
        }
    }

    public void display() {
        if (!isEmpty()) {
            for (int i = front; i != rear; i = (i + 1) % queueElem.length)
                System.out.print(queueElem[i] + " ");
        } else System.out.println("此队列为空");
    }
}
```

> **容量坑**：这个方案下数组长度 `maxSize` 实际最多只能存 `maxSize - 1` 个元素。题目如果问“要存 100 个元素需要开多长的数组”，答案是 101。

### 3.4 链队列

队列的链式存储，本课程用**不带头结点**的单链表实现，`front` 指队首、`rear` 指队尾。入队改队尾、出队改队首，都是 $O(1)$；判空 `front == null`。

课件的补充：用循环链表也能表示队列，`rear` 指向尾巴，`rear.next` 就是队首，入队出队同样 $O(1)$。

### 3.5 优先队列

带优先级的队列，按优先级从大到小有序排列，也有顺序和链式两种存储。结点要存 `(data, priority)`：

```java
public class PriorityQData {
    public Object elem;
    public int priority;
    public PriorityQData(Object elem, int priority) {
        this.elem = elem;
        this.priority = priority;
    }
}
```

> 注意课件的一句话：**一般规定优先数越小，其优先级越大**。入队时按 `priority` 找到第一个“比新元素优先级低”的位置插入，可能要插队首、队中、队尾三种情况分别处理。

## 四、栈与队列的比较（简答）

**相同点**

1. 都是线性结构，元素之间是“一对一”关系；
2. 插入操作都限制在表尾；
3. 都可在顺序和链式两种存储结构上实现；
4. 时间代价上插入删除都是常数时间，空间代价情况也相同；
5. 多链栈和多链队列的管理模式可以相同。

**不同点**

1. **删除的位置不同**（栈删表尾，队列删表头）；
2. 应用场合不同；
3. 顺序栈可实现多栈空间共享（两栈相向生长），顺序队列做不到。

## 五、综合应用：停车场管理（课件例 3.7）

停车场是只能一个门进出的狭长通道，停满后车在门外便道排队，某车离开时它后面的车要先退出让路，再按原次序回来，按停留时间收费。

- 停车场：**顺序栈**（车位固定）；
- 便道：**链队列**（车数不定）；
- 输入每组数据三项：到达/离去、车牌号、时刻；
- 到达 → 输出它在停车场或便道的位置；离去 → 输出停留时间与费用。

这是“栈 + 队列”混合题的模板，思路写对就能拿大部分分。

## 六、易错点清单

1. 栈顶指针的约定不同，空/满条件全不同——答题先声明约定。
2. `peek()` 只读不删，`pop()` 读并删；队列的 `peek()` 和 `poll()` 同理。
3. 循环队列队满 `(rear+1)%len==front`，队空 `front==rear`，两个公式长得很像，别写混。
4. 长度公式必须有 `+len`，否则 `rear < front` 时算出负数。
5. 链栈不带头结点（`top == null` 才是空），链队列本课程也不带头结点，链表的头结点习惯不能乱套。
6. 中缀转后缀时，**优先级相等也要弹出**（左结合），这是 `a-b-c` 能正确转换的关键。
7. 递归算法的空间复杂度是递归深度，不是 $O(1)$。

## 七、本章作业（课件布置）

教材习题三：三、3、4、5。
