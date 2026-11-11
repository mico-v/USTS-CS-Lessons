# 考前速查与易错点

> 最后一晚只看这一页。前半是公式与结论速查，后半是代码模板和历年最容易踩的坑。

---

## 一、公式速查

### 1.1 复杂度

常见阶从小到大：

$$c < \log_2 n < n < n\log_2 n < n^2 < n^3 < 2^n < n! < n^n$$

| 操作 | 复杂度 |
| --- | --- |
| 顺序表按序号存取 `get(i)` | $O(1)$ |
| 顺序表插入 / 删除 | $O(n)$，平均移动 $n/2$ / $(n-1)/2$ 次 |
| 顺序表按值查找 | $O(n)$，平均比较 $(n+1)/2$ 次 |
| 链表按序号取、按值找 | $O(n)$（顺序存取） |
| 链表插入 / 删除（已知前驱） | $O(1)$（改指针） |
| 链表求长度 | $O(n)$ |
| 顺序栈 / 链栈 push、pop | $O(1)$ |
| 循环队列 offer、poll | $O(1)$ |
| 二叉树遍历（四种） | $O(n)$，空间 $O(h)$ |
| 二叉排序树查找（平均 / 最坏） | $O(\log n)$ / $O(n)$ |
| BF 模式匹配（最好 / 最坏） | $O(m)$ / $O(n \times m)$ |
| 稀疏矩阵普通转置 / 快速转置 | $O(n \times t)$ / $O(n + t)$ |
| 冒泡排序空间复杂度 | $O(1)$ |

### 1.2 顺序表与循环队列条件

| 结构 | 空 | 满 | 长度 |
| --- | --- | --- | --- |
| 顺序表 `SqList` | `curLen == 0` | `curLen == listElem.length` | `curLen` |
| 顺序栈（`top` 指栈顶下一个空位） | `top == 0` | `top == stackElem.length` | `top` |
| 顺序栈（`top` 指栈顶元素） | `top == -1` | `top == length - 1` | `top + 1` |
| 链栈（不带头结点） | `top == null` | 无 | 数结点 |
| 顺序队列 | `front == rear` | `rear == length`（有假溢出） | `rear - front` |
| **循环队列**（少用一个元素） | `front == rear` | `(rear+1) % length == front` | `(rear-front+length) % length` |

> 循环队列的数组长度是 `maxSize`，但最多只能存 `maxSize - 1` 个元素。要存 100 个就开 101。

### 1.3 二叉树性质

| 性质 | 结论 |
| --- | --- |
| 第 $i$ 层结点数 | 至多 $2^i$（$i \ge 0$） |
| 深度 $h$ 的结点总数 | 至多 $2^h - 1$ |
| 叶结点与度为 2 的结点 | $n_0 = n_2 + 1$ |
| 完全二叉树深度 | $\lfloor \log_2 n \rfloor + 1$ |
| $n$ 个结点的完全二叉树高度 | $\lceil \log_2(n+1) \rceil$ |
| 度为 $m$ 的树 | $n_0 = 1 + n_2 + 2n_3 + \dots + (m-1)n_m$ |
| 哈夫曼树（$n$ 个叶子） | 共 $2n - 1$ 个结点，无度为 1 的结点 |
| 二叉链表空指针数 | $n + 1$ |

完全二叉树编号（0 起）：双亲 $\lfloor (i-1)/2 \rfloor$、左 $2i+1$、右 $2i+2$；（1 起）：双亲 $\lfloor i/2 \rfloor$、左 $2i$、右 $2i+1$。

### 1.4 数组与矩阵

| 场景 | 公式 |
| --- | --- |
| 行主序（1 起） | $\text{Loc}(a_{ij}) = \text{Loc}(a_{11}) + [(i-1)n + (j-1)]\,l$ |
| 列主序（1 起） | $\text{Loc}(a_{ij}) = \text{Loc}(a_{11}) + [(j-1)m + (i-1)]\,l$ |
| 对称矩阵（下三角，行主序） | $k = \dfrac{i(i+1)}{2} + j$，共 $\dfrac{n(n+1)}{2}$ 个 |
| 三角矩阵 | 同上；$i<j$ 时 $k = \dfrac{n(n+1)}{2}$（存常数） |
| 三对角矩阵 | $i>j$：$3i-1$；$i=j$：$3i$；$i<j$：$3i+1$，共 $3n-2$ 个 |
| 稀疏矩阵转置 `cpot` | `cpot[0]=0; cpot[col]=cpot[col-1]+num[col-1]` |

### 1.5 遍历对应关系

| 树 / 森林 | 二叉树 |
| --- | --- |
| 先根遍历 | 先序遍历 |
| 后根遍历 | **中序遍历** |
| 层次遍历 | —（无对应） |

## 二、代码模板

### 2.1 顺序表插入 / 删除

```java
public void insert(int i, Object x) throws Exception {
    if (curLen == listElem.length) throw new Exception("顺序表已满");
    if (i < 0 || i > curLen) throw new Exception("插入位置不合法");
    for (int j = curLen; j > i; j--)      // 从后往前
        listElem[j] = listElem[j - 1];
    listElem[i] = x;
    curLen++;
}

public void remove(int i) throws Exception {
    if (i < 0 || i > curLen - 1) throw new Exception("删除位置不合法");
    for (int j = i; j < curLen - 1; j++)  // 从前往后
        listElem[j] = listElem[j + 1];
    curLen--;
}
```

### 2.2 单链表插入 / 删除（带头结点）

```java
public void insert(int i, Object x) throws Exception {
    Node p = head; int j = -1;
    while (p != null && j < i - 1) { p = p.next; ++j; }
    if (j > i - 1 || p == null) throw new Exception("插入位置不合法");
    Node s = new Node(x);
    s.next = p.next;      // 先接后
    p.next = s;           // 再断前
}

public void remove(int i) throws Exception {
    Node p = head; int j = -1;
    while (p.next != null && j < i - 1) { p = p.next; ++j; }
    if (j > i - 1 || p.next == null) throw new Exception("删除位置不合法");
    p.next = p.next.next;
}
```

### 2.3 顺序栈 / 循环队列

```java
// 顺序栈
public void push(Object x) throws Exception {
    if (top == stackElem.length) throw new Exception("栈已满");
    else stackElem[top++] = x;
}
public Object pop() {
    if (isEmpty()) return null;
    else return stackElem[--top];
}

// 循环队列（少用一个元素空间）
public void offer(Object x) throws Exception {
    if ((rear + 1) % queueElem.length == front) throw new Exception("队列已满");
    queueElem[rear] = x;
    rear = (rear + 1) % queueElem.length;
}
public Object poll() {
    if (front == rear) return null;
    Object t = queueElem[front];
    front = (front + 1) % queueElem.length;
    return t;
}
```

### 2.4 二叉树三种递归遍历 + 层次

```java
void pre(BiTreeNode T)  { if (T!=null) { visit(T); pre(T.lchild);  pre(T.rchild);  } }
void in(BiTreeNode T)   { if (T!=null) { in(T.lchild);  visit(T);  in(T.rchild);   } }
void post(BiTreeNode T) { if (T!=null) { post(T.lchild); post(T.rchild); visit(T); } }

void level(BiTreeNode T) {
    if (T == null) return;
    LinkQueue Q = new LinkQueue();
    Q.offer(T);
    while (!Q.isEmpty()) {
        T = (BiTreeNode) Q.poll();
        visit(T);
        if (T.lchild != null) Q.offer(T.lchild);
        if (T.rchild != null) Q.offer(T.rchild);
    }
}
```

求深度：`return 1 + max(depth(l), depth(r))`；求结点数：左 + 1 + 右。

### 2.5 哈夫曼构造思路（答题写这四步即可）

1. $n$ 个权值各自成树，入森林；
2. 取最小、次小两棵，合并成新树（权和）；
3. 删两棵、加新树；
4. 重复到只剩一棵。$n$ 个叶子共 $2n-1$ 个结点，编码长度 = 叶子路径长度。

## 三、高频易错点

1. **下标起点**：题目给 1 起还是 0 起，公式和编号全变，先确认。
2. **栈顶指针约定**：先说约定再写条件，否则整题按错。
3. **循环队列满条件**是 `(rear+1)%len==front`，不是 `rear+1==front`；忘取模就错。
4. **平均值**：插入平均移动 $n/2$，删除平均移动 $(n-1)/2$，两个别交换。
5. **链表改指针顺序**：`s.next = p.next` 必须在 `p.next = s` 之前。
6. **$n_0 = n_2 + 1$** 只适用于二叉树；多叉树用 $n_0 = 1 + \sum (i-1)n_i$。
7. **树的深度** = 最大层次 + 1（根层次为 0）。
8. **前序 + 后序不唯一**，必须有中序才能还原。
9. **树后根 ↔ 二叉树中序**，别写后序。
10. **哈夫曼编码不唯一但 WPL 唯一**；WPL 可用“非叶结点权和”验算。
11. **空串长度 0、空白串长度 1**；子串必须连续。
12. **BF 最坏 $O(nm)$**，KMP 才是 $O(n+m)$。
13. **Java 比较内容用 `.equals()`**，`==` 比引用，改错题高发点。
14. **递归算法空间复杂度 = 递归深度**，不是 $O(1)$。

## 四、答题三段式（算法设计题）

1. **算法步骤**：分 a、b、c 列清（判断合法 → 核心操作 → 修正状态），课件每道算法都这么写。
2. **算法代码**：类 Java，含异常判断，变量名和课件一致（`p`、`j`、`curLen`）。
3. **复杂度分析**：时间 + 空间，顺序表说明移动次数，链表说明遍历次数。

按这三段写，即使代码有小瑕疵也能拿到大部分步骤分。
