# 期末模拟试卷一

> 专业：\_\_\_\_\_\_ 学号：\_\_\_\_\_\_ 姓名：\_\_\_\_\_\_

## 一、填空题（每小题 2 分，共 16 分）

**1.** 设 $A$ 是 $n$ 阶矩阵，且 $|A| = -1$，则 $|2A^T| =$ \_\_\_\_\_\_.

**2.** 已知矩阵 $A$ 满足 $A^2 + 2A - 3E = O$，则 $A^{-1} =$ \_\_\_\_\_\_.

**3.** 当 $k$ \_\_\_\_\_\_ 时，矩阵 $A = \begin{pmatrix} -1 & 0 & 0 \\ 0 & k & 0 \\ 1 & -1 & 4 \end{pmatrix}$ 可逆.

**4.** 设 $n$ 维向量组 $\alpha_1, \alpha_2, \dots, \alpha_s, \alpha_{s+1}\ (s < n)$ 线性无关，则向量组 $\alpha_1, \alpha_2, \dots, \alpha_s$ 的秩为 \_\_\_\_\_\_.

**5.** 设 $A$ 是 $m \times n$ 矩阵，$A$ 的秩为 $r\ (r < n)$，则齐次线性方程组 $Ax = 0$ 的一个基础解系中含有解向量的个数为 \_\_\_\_\_\_.

**6.** 向量 $u = (2, 0, 0)$ 在基 $\alpha_1 = (1, 1, 0)$, $\alpha_2 = (1, 0, 1)$, $\alpha_3 = (0, 1, 1)$ 下的坐标为 \_\_\_\_\_\_.

**7.** 设 $\alpha = \begin{pmatrix} 1 \\ 1 \end{pmatrix}$ 是 $A = \begin{pmatrix} a & 2 \\ 0 & b \end{pmatrix}$ 的属于 $\lambda = 3$ 的特征向量，则 $a =$ \_\_\_\_\_\_；$b =$ \_\_\_\_\_\_.

**8.** 设二次型 $f(x_1, x_2, \dots, x_n) = x_1^2 + x_2^2 + \cdots + x_r^2$，则当 $r =$ \_\_\_\_\_\_ 时 $f$ 正定.

### 解析

**1.** 由行列式性质：$|2A^T| = 2^n |A^T| = 2^n |A| = 2^n \cdot (-1) = -2^n$.

> 答案：$-2^n$

**2.** 由 $A^2 + 2A - 3E = O$，得 $A^2 + 2A = 3E$，即 $A(A + 2E) = 3E$，
故 $A \cdot \frac{A + 2E}{3} = E$，所以 $A^{-1} = \frac{A + 2E}{3}$.

> 答案：$\frac{A + 2E}{3}$

**3.** $A$ 可逆 $\iff |A| \neq 0$。$A$ 为下三角矩阵（近似），$|A| = (-1) \cdot k \cdot 4 = -4k \neq 0$，故 $k \neq 0$.

> 答案：$k \neq 0$

**4.** 若 $\alpha_1, \alpha_2, \dots, \alpha_s, \alpha_{s+1}$ 线性无关，则其部分组 $\alpha_1, \alpha_2, \dots, \alpha_s$ 也线性无关，故秩为 $s$.

> 答案：$s$

**5.** 齐次线性方程组 $Ax = 0$ 的基础解系所含向量个数 = 未知数个数 $-$ 系数矩阵的秩 = $n - r$.

> 答案：$n - r$

**6.** 设 $u = x_1\alpha_1 + x_2\alpha_2 + x_3\alpha_3$，即：
$$\begin{cases} x_1 + x_2 = 2 \\ x_1 + x_3 = 0 \\ x_2 + x_3 = 0 \end{cases}$$
解得 $x_1 = 1$, $x_2 = 1$, $x_3 = -1$，坐标为 $(1, 1, -1)$.

> 答案：$(1, 1, -1)$

**7.** 由 $A\alpha = \lambda\alpha$：$\begin{pmatrix} a & 2 \\ 0 & b \end{pmatrix}\begin{pmatrix} 1 \\ 1 \end{pmatrix} = 3\begin{pmatrix} 1 \\ 1 \end{pmatrix}$，
即 $\begin{pmatrix} a+2 \\ b \end{pmatrix} = \begin{pmatrix} 3 \\ 3 \end{pmatrix}$，解得 $a = 1$, $b = 3$.

> 答案：$a = 1$；$b = 3$

**8.** 二次型 $f = x_1^2 + x_2^2 + \cdots + x_r^2$ 正定 $\iff$ 对任意非零 $x$，$f > 0$，这要求 $r = n$（所有变量平方项都存在，且系数为正）.

> 答案：$r = n$

---

## 二、选择题（每小题 3 分，共 15 分）

**1.** 设 $A, B$ 是 $n$ 阶矩阵，则下列等式正确的是（ ）

A. $AB = BA$
B. $(A + B)^T = A^T + B^T$
C. $(A + B)^{-1} = A^{-1} + B^{-1}$
D. $A^2 - B^2 = (A + B)(A - B)$

**2.** 设 $A$ 为 $n$ 阶满秩矩阵，则下列选项中正确的是（ ）

A. $A$ 的列向量组线性相关
B. $|A| \neq 0$
C. $A$ 的行向量组线性相关
D. $r(A) < n$

**3.** 设 $A$ 为 $m \times n$ 矩阵，$r(A) = r$，则方程组 $Ax = 0$ 有非零解的充分必要条件是（ ）

A. $m < n$
B. $r = m$
C. $r < m$
D. $A$ 的列向量组线性相关

**4.** 以下结论正确的是（ ）

A. $n$ 阶矩阵 $A$ 必能对角化
B. 等价矩阵必有相同的特征值
C. 实对称矩阵的对应于不同特征值的特征向量必两两正交
D. $A$ 的对应于特征值 $\lambda$ 的特征向量为特征方程组 $(A - \lambda E)x = 0$ 的全部解

**5.** 设 $\lambda = 2$ 是非奇异矩阵 $A$ 的一个特征值，则矩阵 $\frac{1}{3}A^2$ 有一个特征值等于（ ）

A. $\frac{4}{3}$
B. $\frac{3}{4}$
C. $\frac{1}{2}$
D. $\frac{1}{4}$

### 解析

**1.** 矩阵加法满足转置的分配律：$(A + B)^T = A^T + B^T$。A 错（矩阵乘法不满足交换律），C 错（和的逆不等于逆的和），D 错（$(A+B)(A-B) = A^2 - AB + BA - B^2 \neq A^2 - B^2$ 除非 $AB = BA$）.
> 答案：**B**

**2.** 满秩即 $r(A) = n$，等价于 $|A| \neq 0$（非奇异），等价于行/列向量组线性无关.
> 答案：**B**

**3.** $Ax = 0$ 有非零解 $\iff$ 系数矩阵的秩小于未知数个数 $\iff r(A) < n \iff A$ 的列向量组线性相关.
> 答案：**D**

**4.** 实对称矩阵属于不同特征值的特征向量必定正交，这是实对称矩阵的关键性质。A 错（一般矩阵不一定可对角化），B 错（等价矩阵特征值不一定相同），D 错（特征向量是 $(A-\lambda E)x=0$ 的**非零**解）.
> 答案：**C**

**5.** 若 $\lambda$ 是 $A$ 的特征值，则 $\frac{1}{3}\lambda^2$ 是 $\frac{1}{3}A^2$ 的特征值。代入 $\lambda = 2$：$\frac{1}{3} \times 2^2 = \frac{4}{3}$.
> 答案：**A**

---

## 三、计算题（每小题 10 分，共 30 分）

**1.** 设 $A = \begin{pmatrix} 1 & 2 & 0 \\ 3 & 4 & 0 \\ -1 & 2 & 1 \end{pmatrix}$，$B = \begin{pmatrix} 2 & 3 & -1 \\ -2 & 4 & 0 \end{pmatrix}$.
求：（1）$AB^T$；（2）$4A$.

### 解析

**(1) 求 $AB^T$**

$$B^T = \begin{pmatrix} 2 & -2 \\ 3 & 4 \\ -1 & 0 \end{pmatrix}$$

$$AB^T = \begin{pmatrix} 1 & 2 & 0 \\ 3 & 4 & 0 \\ -1 & 2 & 1 \end{pmatrix} \begin{pmatrix} 2 & -2 \\ 3 & 4 \\ -1 & 0 \end{pmatrix}$$

- 第1行第1列：$1 \times 2 + 2 \times 3 + 0 \times (-1) = 8$
- 第1行第2列：$1 \times (-2) + 2 \times 4 + 0 \times 0 = 6$
- 第2行第1列：$3 \times 2 + 4 \times 3 + 0 \times (-1) = 18$
- 第2行第2列：$3 \times (-2) + 4 \times 4 + 0 \times 0 = 10$
- 第3行第1列：$(-1) \times 2 + 2 \times 3 + 1 \times (-1) = 3$
- 第3行第2列：$(-1) \times (-2) + 2 \times 4 + 1 \times 0 = 10$

$$AB^T = \begin{pmatrix} 8 & 6 \\ 18 & 10 \\ 3 & 10 \end{pmatrix}$$

**(2) $4A = 4 \times \begin{pmatrix} 1 & 2 & 0 \\ 3 & 4 & 0 \\ -1 & 2 & 1 \end{pmatrix} = \begin{pmatrix} 4 & 8 & 0 \\ 12 & 16 & 0 \\ -4 & 8 & 4 \end{pmatrix}$**

---

**2.** 解矩阵方程 $Ax = -A + 3x$，其中 $A = \begin{pmatrix} -2 & 0 & 1 \\ -1 & 4 & 0 \\ 0 & 1 & -1 \end{pmatrix}$.

### 解析

由 $Ax = -A + 3x$ 得 $Ax - 3x = -A$，即 $(A - 3E)x = -A$.

$$A - 3E = \begin{pmatrix} -2-3 & 0 & 1 \\ -1 & 4-3 & 0 \\ 0 & 1 & -1-3 \end{pmatrix} = \begin{pmatrix} -5 & 0 & 1 \\ -1 & 1 & 0 \\ 0 & 1 & -4 \end{pmatrix}$$

求解 $(A - 3E)x = -A$. 先求 $|A - 3E|$：

$$|A - 3E| = \begin{vmatrix} -5 & 0 & 1 \\ -1 & 1 & 0 \\ 0 & 1 & -4 \end{vmatrix}$$

按第一行展开：
$$= (-5) \cdot \begin{vmatrix} 1 & 0 \\ 1 & -4 \end{vmatrix} - 0 + 1 \cdot \begin{vmatrix} -1 & 1 \\ 0 & 1 \end{vmatrix}$$
$$= (-5) \cdot (-4) + 1 \cdot (-1) = 20 - 1 = 19 \neq 0$$

故 $A - 3E$ 可逆。$x = -(A - 3E)^{-1}A$.

先求 $(A - 3E)^{-1}$，用伴随矩阵法：

各元素的代数余子式：
- $A_{11} = \begin{vmatrix} 1 & 0 \\ 1 & -4 \end{vmatrix} = -4$
- $A_{12} = -\begin{vmatrix} -1 & 0 \\ 0 & -4 \end{vmatrix} = -4$
- $A_{13} = \begin{vmatrix} -1 & 1 \\ 0 & 1 \end{vmatrix} = -1$
- $A_{21} = -\begin{vmatrix} 0 & 1 \\ 1 & -4 \end{vmatrix} = 1$
- $A_{22} = \begin{vmatrix} -5 & 1 \\ 0 & -4 \end{vmatrix} = 20$
- $A_{23} = -\begin{vmatrix} -5 & 0 \\ 0 & 1 \end{vmatrix} = 5$
- $A_{31} = \begin{vmatrix} 0 & 1 \\ 1 & 0 \end{vmatrix} = -1$
- $A_{32} = -\begin{vmatrix} -5 & 1 \\ -1 & 0 \end{vmatrix} = -1$
- $A_{33} = \begin{vmatrix} -5 & 0 \\ -1 & 1 \end{vmatrix} = -5$

伴随矩阵：
$$(A - 3E)^* = \begin{pmatrix} -4 & 1 & -1 \\ -4 & 20 & -1 \\ -1 & 5 & -5 \end{pmatrix}$$

$$(A - 3E)^{-1} = \frac{1}{19} \begin{pmatrix} -4 & 1 & -1 \\ -4 & 20 & -1 \\ -1 & 5 & -5 \end{pmatrix}$$

$$x = -(A - 3E)^{-1}A = -\frac{1}{19} \begin{pmatrix} -4 & 1 & -1 \\ -4 & 20 & -1 \\ -1 & 5 & -5 \end{pmatrix} \begin{pmatrix} -2 & 0 & 1 \\ -1 & 4 & 0 \\ 0 & 1 & -1 \end{pmatrix}$$

计算矩阵乘积：

第一行：$(-4)(-2)+1\cdot(-1)+(-1)\cdot0 = 7$；$(-4)\cdot0+1\cdot4+(-1)\cdot1 = 3$；$(-4)\cdot1+1\cdot0+(-1)(-1) = -3$

第二行：$(-4)(-2)+20(-1)+(-1)\cdot0 = -12$；$(-4)\cdot0+20\cdot4+(-1)\cdot1 = 79$；$(-4)\cdot1+20\cdot0+(-1)(-1) = -3$

第三行：$(-1)(-2)+5(-1)+(-5)\cdot0 = -3$；$(-1)\cdot0+5\cdot4+(-5)\cdot1 = 15$；$(-1)\cdot1+5\cdot0+(-5)(-1) = 4$

$$x = -\frac{1}{19} \begin{pmatrix} 7 & 3 & -3 \\ -12 & 79 & -3 \\ -3 & 15 & 4 \end{pmatrix} = \frac{1}{19} \begin{pmatrix} -7 & -3 & 3 \\ 12 & -79 & 3 \\ 3 & -15 & -4 \end{pmatrix}$$

> 注：此处 $x$ 为 $3 \times 3$ 矩阵。若原题 $x$ 为列向量则方程需重新审视。

---

**3.** 设向量组
$\alpha_1 = \begin{pmatrix} 0 \\ 1 \\ -2 \\ 3 \end{pmatrix}$,
$\alpha_2 = \begin{pmatrix} 3 \\ -2 \\ 4 \\ 3 \end{pmatrix}$,
$\alpha_3 = \begin{pmatrix} 2 \\ 0 \\ 6 \\ 3 \end{pmatrix}$,
$\alpha_4 = \begin{pmatrix} -1 \\ 2 \\ -6 \\ 4 \end{pmatrix}$.

求：（1）向量组 $\alpha_1, \alpha_2, \alpha_3, \alpha_4$ 的秩和一个极大线性无关组；
（2）将其余向量用该极大线性无关组线性表示.

### 解析

以 $\alpha_1, \alpha_2, \alpha_3, \alpha_4$ 为列构造矩阵并做初等行变换：

$$A = \begin{pmatrix} 0 & 3 & 2 & -1 \\ 1 & -2 & 0 & 2 \\ -2 & 4 & 6 & -6 \\ 3 & 3 & 3 & 4 \end{pmatrix}$$

交换第1行和第2行（使左上角不为0）：

$$\xrightarrow{r_1 \leftrightarrow r_2} \begin{pmatrix} 1 & -2 & 0 & 2 \\ 0 & 3 & 2 & -1 \\ -2 & 4 & 6 & -6 \\ 3 & 3 & 3 & 4 \end{pmatrix}$$

$r_3 + 2r_1$, $r_4 - 3r_1$：

$$\rightarrow \begin{pmatrix} 1 & -2 & 0 & 2 \\ 0 & 3 & 2 & -1 \\ 0 & 0 & 6 & -2 \\ 0 & 9 & 3 & -2 \end{pmatrix}$$

$r_4 - 3r_2$：

$$\rightarrow \begin{pmatrix} 1 & -2 & 0 & 2 \\ 0 & 3 & 2 & -1 \\ 0 & 0 & 6 & -2 \\ 0 & 0 & -3 & 1 \end{pmatrix}$$

$r_4 + \frac{1}{2}r_3$：

$$\rightarrow \begin{pmatrix} 1 & -2 & 0 & 2 \\ 0 & 3 & 2 & -1 \\ 0 & 0 & 6 & -2 \\ 0 & 0 & 0 & 0 \end{pmatrix}$$

化为行阶梯形，有 3 个非零行.

**(1)** 向量组的秩 $r = 3$，一个极大线性无关组为 $\alpha_1, \alpha_2, \alpha_3$.

**(2)** 继续化为行最简形。$r_3 \div 6$：

$$\rightarrow \begin{pmatrix} 1 & -2 & 0 & 2 \\ 0 & 3 & 2 & -1 \\ 0 & 0 & 1 & -\frac{1}{3} \\ 0 & 0 & 0 & 0 \end{pmatrix}$$

$r_2 - 2r_3$, $r_2 \div 3$：

$$\rightarrow \begin{pmatrix} 1 & -2 & 0 & 2 \\ 0 & 1 & 0 & -\frac{1}{9} \\ 0 & 0 & 1 & -\frac{1}{3} \\ 0 & 0 & 0 & 0 \end{pmatrix}$$

$r_1 + 2r_2$：

$$\rightarrow \begin{pmatrix} 1 & 0 & 0 & \frac{16}{9} \\ 0 & 1 & 0 & -\frac{1}{9} \\ 0 & 0 & 1 & -\frac{1}{3} \\ 0 & 0 & 0 & 0 \end{pmatrix}$$

由行最简形可得：$\alpha_4 = \frac{16}{9}\alpha_1 - \frac{1}{9}\alpha_2 - \frac{1}{3}\alpha_3$.

---

## 四、解答题（共 27 分）

**1.（13 分）** 已知方程组
$$\begin{cases} x_1 + x_2 + ax_3 = -1, \\ x_1 - x_2 + 2x_3 = -1, \\ -x_1 + ax_2 + x_3 = a^2 \end{cases}$$
有无穷多解，求 $a$ 的值以及方程组的通解.

### 解析

写出增广矩阵：

$$\begin{pmatrix} 1 & 1 & a & | & -1 \\ 1 & -1 & 2 & | & -1 \\ -1 & a & 1 & | & a^2 \end{pmatrix}$$

$r_2 - r_1$, $r_3 + r_1$：

$$\rightarrow \begin{pmatrix} 1 & 1 & a & | & -1 \\ 0 & -2 & 2-a & | & 0 \\ 0 & a+1 & a+1 & | & a^2-1 \end{pmatrix}$$

当 $a \neq 2$ 时继续消元。$r_2 \div (-2)$：

$$\rightarrow \begin{pmatrix} 1 & 1 & a & | & -1 \\ 0 & 1 & \frac{a-2}{2} & | & 0 \\ 0 & a+1 & a+1 & | & a^2-1 \end{pmatrix}$$

$r_3 - (a+1)r_2$：

$$\rightarrow \begin{pmatrix} 1 & 1 & a & | & -1 \\ 0 & 1 & \frac{a-2}{2} & | & 0 \\ 0 & 0 & a+1 - \frac{(a+1)(a-2)}{2} & | & a^2-1 \end{pmatrix}$$

化简右下角：$a+1 - \frac{(a+1)(a-2)}{2} = \frac{2(a+1) - (a+1)(a-2)}{2} = \frac{(a+1)(4-a)}{2}$

方程组有无穷多解 $\iff r(A) = r(\bar{A}) < 3$（未知数个数）。

当 $a = -1$ 时，$r_3 = 0$（系数矩阵第三行全零），增广矩阵第三行右端 $a^2 - 1 = 0$，故 $r(A) = r(\bar{A}) = 2 < 3$，有无穷多解。

当 $a = 4$ 时，$a+1 - \frac{(a+1)(a-2)}{2} = 0$，但增广矩阵右端 $a^2-1 = 15 \neq 0$，无解。

当 $a = 2$ 时需单独讨论。代入原增广矩阵：

$$\begin{pmatrix} 1 & 1 & 2 & | & -1 \\ 1 & -1 & 2 & | & -1 \\ -1 & 2 & 1 & | & 4 \end{pmatrix}$$

$r_2 - r_1$, $r_3 + r_1$：

$$\rightarrow \begin{pmatrix} 1 & 1 & 2 & | & -1 \\ 0 & -2 & 0 & | & 0 \\ 0 & 3 & 3 & | & 3 \end{pmatrix}$$

$r_3 + \frac{3}{2}r_2$：得 $r(A) = 2$, $r(\bar{A}) = 2 < 3$，也有无穷多解。

验证：$a = 2$ 时也符合。再看 $a = -1$ 时通解。

**综上，$a = -1$ 或 $a = 2$ 时有无穷多解。**

现求 $a = -1$ 时的通解。方程组为：

$$\begin{cases} x_1 + x_2 - x_3 = -1 \\ x_1 - x_2 + 2x_3 = -1 \\ -x_1 - x_2 + x_3 = 1 \end{cases}$$

行变换后：$\begin{pmatrix} 1 & 1 & -1 & | & -1 \\ 0 & 1 & -\frac{3}{2} & | & 0 \\ 0 & 0 & 0 & | & 0 \end{pmatrix}$

$r_1 - r_2$：$\begin{pmatrix} 1 & 0 & \frac{1}{2} & | & -1 \\ 0 & 1 & -\frac{3}{2} & | & 0 \\ 0 & 0 & 0 & | & 0 \end{pmatrix}$

得 $x_1 = -1 - \frac{1}{2}x_3$, $x_2 = \frac{3}{2}x_3$，$x_3$ 为自由变量。

通解：$\begin{pmatrix} x_1 \\ x_2 \\ x_3 \end{pmatrix} = \begin{pmatrix} -1 \\ 0 \\ 0 \end{pmatrix} + k\begin{pmatrix} -\frac{1}{2} \\ \frac{3}{2} \\ 1 \end{pmatrix},\ k \in \mathbb{R}$.

$a = 2$ 时类似可求.

---

**2.（14 分）** 已知二次型 $f(x_1, x_2, x_3) = 2x_1^2 + 3x_2^2 + 3x_3^2 + 2ax_2x_3\ (a > 0)$ 通过正交变换化为标准型 $f(y_1, y_2, y_3) = y_1^2 + 2y_2^2 + 5y_3^2$，求参数 $a$ 以及所用的正交变换矩阵.

### 解析

二次型的矩阵为：

$$A = \begin{pmatrix} 2 & 0 & 0 \\ 0 & 3 & a \\ 0 & a & 3 \end{pmatrix}$$

正交变换不改变矩阵的特征值。标准型 $y_1^2 + 2y_2^2 + 5y_3^2$ 表明特征值为 $\lambda_1 = 1$, $\lambda_2 = 2$, $\lambda_3 = 5$.

由矩阵 $A$ 的结构，$\lambda_1 = 2$（因为第一行第一列元素 2 独立，对应特征向量 $(1, 0, 0)^T$）。

右下角 $2 \times 2$ 子矩阵 $\begin{pmatrix} 3 & a \\ a & 3 \end{pmatrix}$ 的特征值为 $\lambda$ 满足 $(\lambda - 3)^2 - a^2 = 0$，即 $\lambda = 3 \pm a$.

这两个特征值应为 1 和 5（已有一个为 2）。故 $3 - a = 1$, $3 + a = 5$，解得 $a = 2$（满足 $a > 0$）。

现求特征向量：

- $\lambda_1 = 2$：$(A - 2E)x = 0$，$\begin{pmatrix} 0 & 0 & 0 \\ 0 & 1 & 2 \\ 0 & 2 & 1 \end{pmatrix}x = 0$，得 $x_2 = x_3 = 0$，特征向量 $p_1 = (1, 0, 0)^T$.

- $\lambda_2 = 1$：$(A - E)x = 0$，$\begin{pmatrix} 1 & 0 & 0 \\ 0 & 2 & 2 \\ 0 & 2 & 2 \end{pmatrix}x = 0$，得 $x_1 = 0$, $x_2 + x_3 = 0$，特征向量 $p_2 = (0, 1, -1)^T$.

- $\lambda_3 = 5$：$(A - 5E)x = 0$，$\begin{pmatrix} -3 & 0 & 0 \\ 0 & -2 & 2 \\ 0 & 2 & -2 \end{pmatrix}x = 0$，得 $x_1 = 0$, $x_2 = x_3$，特征向量 $p_3 = (0, 1, 1)^T$.

三个特征向量已两两正交（不同特征值对应的特征向量自动正交，且 $p_2 \perp p_3$ 验证 $\langle p_2, p_3 \rangle = 0$）。

单位化：$\|p_1\| = 1$, $\|p_2\| = \sqrt{2}$, $\|p_3\| = \sqrt{2}$.

正交变换矩阵：

$$P = \begin{pmatrix} 1 & 0 & 0 \\ 0 & \frac{1}{\sqrt{2}} & \frac{1}{\sqrt{2}} \\ 0 & -\frac{1}{\sqrt{2}} & \frac{1}{\sqrt{2}} \end{pmatrix}$$

验证 $P^TAP = \operatorname{diag}(2, 1, 5)$，与标准型对应（需调整顺序使对角矩阵为 $\operatorname{diag}(1,2,5)$）。调整：

$$P = \begin{pmatrix} 0 & 1 & 0 \\ \frac{1}{\sqrt{2}} & 0 & \frac{1}{\sqrt{2}} \\ -\frac{1}{\sqrt{2}} & 0 & \frac{1}{\sqrt{2}} \end{pmatrix}$$

> 答案：$a = 2$，正交变换矩阵见上.

---

## 五、证明题（每小题 6 分，共 12 分）

**1.** 若 $A$ 是对称矩阵，$B$ 是反对称矩阵，则 $AB - BA$ 是否为对称矩阵？证明你的结论.

### 解析

已知 $A^T = A$, $B^T = -B$.

考虑 $(AB - BA)^T$：

$$(AB - BA)^T = (AB)^T - (BA)^T = B^T A^T - A^T B^T = (-B)A - A(-B) = -BA + AB = AB - BA$$

故 $(AB - BA)^T = AB - BA$，$AB - BA$ 是**对称矩阵**.

> 结论：$AB - BA$ 是对称矩阵.

---

**2.** 设 $\alpha_1, \alpha_2, \alpha_3$ 是齐次线性方程组 $Ax = 0$ 的基础解系，证明：$\alpha_1, \alpha_1 + \alpha_2, \alpha_1 + \alpha_2 + \alpha_3$ 也是齐次线性方程组 $Ax = 0$ 的基础解系.

### 解析

需证两点：(1) 三个向量都是 $Ax = 0$ 的解；(2) 它们线性无关。

**(1)** 由于 $\alpha_1, \alpha_2, \alpha_3$ 是 $Ax = 0$ 的解，$A\alpha_i = 0$.

- $A\alpha_1 = 0$
- $A(\alpha_1 + \alpha_2) = A\alpha_1 + A\alpha_2 = 0 + 0 = 0$
- $A(\alpha_1 + \alpha_2 + \alpha_3) = A\alpha_1 + A\alpha_2 + A\alpha_3 = 0$

故三个向量均为 $Ax = 0$ 的解.

**(2)** 设 $k_1\alpha_1 + k_2(\alpha_1 + \alpha_2) + k_3(\alpha_1 + \alpha_2 + \alpha_3) = 0$，

整理得 $(k_1 + k_2 + k_3)\alpha_1 + (k_2 + k_3)\alpha_2 + k_3\alpha_3 = 0$.

由于 $\alpha_1, \alpha_2, \alpha_3$ 线性无关，必有：

$$\begin{cases} k_1 + k_2 + k_3 = 0 \\ k_2 + k_3 = 0 \\ k_3 = 0 \end{cases}$$

递推得 $k_3 = 0$, $k_2 = 0$, $k_1 = 0$，故 $\alpha_1, \alpha_1+\alpha_2, \alpha_1+\alpha_2+\alpha_3$ 线性无关.

综上所述，三个向量个数等于基础解系所含向量个数（3 个），且线性无关、均为解，故也构成基础解系.

> 证毕.
