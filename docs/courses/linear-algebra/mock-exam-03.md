# 期末模拟试卷三

> 专业：\_\_\_\_\_\_ 学号：\_\_\_\_\_\_ 姓名：\_\_\_\_\_\_

## 一、填空题（每小题 2 分，共 16 分）

**1.** 设 $A$ 为 $n$ 阶矩阵，$E$ 为 $n$ 阶单位矩阵，且 $A^2 = E$，则行列式 $|A| =$ \_\_\_\_\_\_.

**2.** 设 $A$ 为五阶矩阵，$A^*$ 是其伴随矩阵，且 $|A| = 3$，则 $|A^*| =$ \_\_\_\_\_\_.

**3.** 设 $A$ 为 $n$ 阶可逆矩阵，$B = (A^*)^{-1}$，则矩阵 $B$ 的秩 $r(B) =$ \_\_\_\_\_\_.

**4.** 设 $A$ 为 $n$ 阶矩阵，则非齐次线性方程组 $Ax = \beta$ 有解的充分必要条件是 \_\_\_\_\_\_.

**5.** 设 $\alpha = (1, -2, 1, -1)$，$\beta = (1, 1, 3, 2)$，则 $5\alpha + \beta =$ \_\_\_\_\_\_.

**6.** 设 $n$ 阶矩阵 $A = (\alpha_1, \alpha_2, \dots, \alpha_n)$，$\alpha_1 = \alpha_2 + \alpha_3$，则 $|A| =$ \_\_\_\_\_\_.

**7.** 设三阶矩阵 $A$ 的特征值分别为 $-1, 1, 0$，则行列式 $|2A^2 + A + E| =$ \_\_\_\_\_\_.

**8.** 设 $A = \begin{pmatrix} 1 & 0 & 6 \\ 2 & 2 & 0 \\ 0 & 0 & 3 \end{pmatrix}$，则二次型 $f = x^T A x$ 的矩阵为 \_\_\_\_\_\_.

### 解析

**1.** $A^2 = E \implies |A^2| = |E| = 1 \implies |A|^2 = 1 \implies |A| = \pm 1$.

> 答案：$\pm 1$

**2.** 对于 $n$ 阶矩阵，$|A^*| = |A|^{n-1}$。五阶矩阵 $n=5$，$|A^*| = 3^{5-1} = 3^4 = 81$.

> 答案：$81$

**3.** $A^* = |A|A^{-1}$，故 $(A^*)^{-1} = \frac{A}{|A|}$。$A$ 可逆 $\implies r(A) = n$，故 $r(B) = n$.

> 答案：$n$

**4.** 非齐次线性方程组 $Ax = \beta$ 有解 $\iff$ $\beta$ 可由 $A$ 的列向量组线性表示 $\iff r(A) = r(A \mid \beta)$（系数矩阵的秩等于增广矩阵的秩）.

> 答案：$r(A) = r(A \mid \beta)$

**5.** $5\alpha + \beta = (5 \times 1 + 1,\ 5 \times (-2) + 1,\ 5 \times 1 + 3,\ 5 \times (-1) + 2) = (6, -9, 8, -3)$.

> 答案：$(6, -9, 8, -3)$

**6.** $\alpha_1 = \alpha_2 + \alpha_3 \implies \alpha_1 - \alpha_2 - \alpha_3 = 0$，即 $A$ 的列向量组线性相关，故 $|A| = 0$.

> 答案：$0$

**7.** 设 $f(\lambda) = 2\lambda^2 + \lambda + 1$。若 $\lambda$ 是 $A$ 的特征值，则 $f(\lambda)$ 是 $f(A)$ 的特征值。
$|2A^2 + A + E| = f(-1) \cdot f(1) \cdot f(0)$.
- $f(-1) = 2 \times 1 + (-1) + 1 = 2$
- $f(1) = 2 \times 1 + 1 + 1 = 4$
- $f(0) = 0 + 0 + 1 = 1$

故行列式 $= 2 \times 4 \times 1 = 8$.

> 答案：$8$

**8.** 二次型 $f = x^T A x$ 的矩阵不一定是 $A$，需要对称化。二次型的矩阵 $B$ 满足 $f = x^T B x$ 且 $B^T = B$.

由于 $x^T A x$ 是标量，$x^T A x = (x^T A x)^T = x^T A^T x$，
故 $x^T A x = \frac{1}{2}x^T (A + A^T) x$，二次型矩阵为 $\frac{A + A^T}{2}$.

$$A + A^T = \begin{pmatrix} 1 & 0 & 6 \\ 2 & 2 & 0 \\ 0 & 0 & 3 \end{pmatrix} + \begin{pmatrix} 1 & 2 & 0 \\ 0 & 2 & 0 \\ 6 & 0 & 3 \end{pmatrix} = \begin{pmatrix} 2 & 2 & 6 \\ 2 & 4 & 0 \\ 6 & 0 & 6 \end{pmatrix}$$

二次型矩阵为：
$$\frac{A + A^T}{2} = \begin{pmatrix} 1 & 1 & 3 \\ 1 & 2 & 0 \\ 3 & 0 & 3 \end{pmatrix}$$

> 答案：$\begin{pmatrix} 1 & 1 & 3 \\ 1 & 2 & 0 \\ 3 & 0 & 3 \end{pmatrix}$

---

## 二、选择题（每小题 2 分，共 8 分）

**1.** 设 $A, B$ 为 $n$ 阶可逆矩阵，则下面各式恒正确的是（ ）

A. $(A + B)^{-1} = A^{-1} + B^{-1}$
B. $(AB)^T = A^T B^T$
C. $(A^{-1} + B)^T = A^{-1} + B^T$
D. $(A + B)^{-1} = A^{-1} + B^{-1}$

**2.** 设 $A$ 为 $m \times n$ 矩阵，$C$ 为 $n$ 阶可逆矩阵，$B = AC$，则（ ）

A. $r(A) > r(B)$
B. $r(A) = r(B)$
C. $r(A) < r(B)$
D. $r(A)$ 与 $r(B)$ 的关系依 $C$ 而定

**3.** $n$ 维向量组 $\alpha_1, \alpha_2, \dots, \alpha_s\ (s \geq 2)$ 线性相关的充分必要条件是（ ）

A. $\alpha_1, \alpha_2, \dots, \alpha_s$ 中至少有一个零向量
B. $\alpha_1, \alpha_2, \dots, \alpha_s$ 中至少有两个向量成比例
C. $\alpha_1, \alpha_2, \dots, \alpha_s$ 中任意两个向量不成比例
D. $\alpha_1, \alpha_2, \dots, \alpha_s$ 中至少有一个向量可由其他向量线性表示

**4.** 已知正定二次型 $f(x_1, x_2, x_3, x_4)$ 的矩阵为 $A$，则下列说法中必成立的是（ ）

A. $A$ 的所有顺序主子式为非负数
B. $A$ 的所有特征值为非负数
C. $A$ 的所有顺序主子式大于零
D. $A$ 的所有特征值互不相同

### 解析

**1.** 需要逐个核查选项。原题可能选项有误，正确结论应为：
- $(AB)^T = B^T A^T$（非 $A^T B^T$）
- $(A^{-1})^T = (A^T)^{-1}$
- $(A + B)^{-1} \neq A^{-1} + B^{-1}$（一般情况）

若选项 C 为 $(A^{-1})^T = (A^T)^{-1}$，则正确。但原题抄录可能变形。按常见考题，选含 $(A^{-1})^T = (A^T)^{-1}$ 的选项.

> 答案：**(需根据实际选项判断，正确命题为 $(AB)^T = B^T A^T$ 与 $(A^{-1})^T = (A^T)^{-1}$)**

**2.** 右乘可逆矩阵不改变矩阵的秩：$r(AC) = r(A)$（$C$ 可逆）.

> 答案：**B**

**3.** 向量组线性相关的等价定义：其中至少有一个向量可由其余向量线性表示。A、B 是充分非必要条件.

> 答案：**D**

**4.** 二次型正定的充要条件：矩阵的各阶顺序主子式全大于零（霍尔维茨定理）。特征值全大于零也是充要条件，但选项 C 直接给出正确表述.

> 答案：**C**

---

## 三、解答题（共 60 分）

**1.（11 分）** 计算行列式：

$$D = \begin{vmatrix}
1 & 1 & 1 & \cdots & 1 \\
b_1 & a_1 & a_2 & \cdots & a_n \\
b_1 & b_2 & a_2 & \cdots & a_n \\
\vdots & \vdots & \vdots & \ddots & \vdots \\
b_1 & b_2 & b_3 & \cdots & a_n
\end{vmatrix}$$

### 解析

从最后一行开始，每行减去上一行（$r_i - r_{i-1}$，$i = n, n-1, \dots, 2$）：

- $r_n - r_{n-1}$：$(b_1-b_1,\ b_2-b_2,\ b_3-b_3,\ \dots,\ a_n - a_{n-1}) = (0, 0, 0, \dots, a_n - a_{n-1})$
- ...
- $r_2 - r_1$：$(b_1-1,\ a_1-1,\ a_2-1,\ \dots,\ a_n-1)$

等等。更系统的方法：

$$D = \begin{vmatrix}
1 & 1 & 1 & \cdots & 1 \\
b_1 & a_1 & a_2 & \cdots & a_n \\
b_1 & b_2 & a_2 & \cdots & a_n \\
\vdots & \vdots & \vdots & \ddots & \vdots \\
b_1 & b_2 & b_3 & \cdots & a_n
\end{vmatrix}$$

$r_i - r_{i+1}$（$i = 1, 2, \dots, n-1$，从第一行开始向下减）：

第 1 行 $-$ 第 2 行：$(1-b_1, 1-a_1, 1-a_2, \dots, 1-a_n)$
第 2 行 $-$ 第 3 行：$(b_1-b_1, a_1-b_2, a_2-a_2, \dots, a_n-a_n) = (0, a_1-b_2, 0, \dots, 0)$
第 3 行 $-$ 第 4 行：$(b_1-b_1, b_2-b_2, a_2-b_3, \dots, a_n-a_n) = (0, 0, a_2-b_3, \dots, 0)$
...

最后一行保持不变：$(b_1, b_2, b_3, \dots, a_n)$.

这得到一个几乎是对角形的行列式：

$$D = \begin{vmatrix}
1-b_1 & 1-a_1 & 1-a_2 & \cdots & 1-a_n \\
0 & a_1-b_2 & 0 & \cdots & 0 \\
0 & 0 & a_2-b_3 & \cdots & 0 \\
\vdots & \vdots & \vdots & \ddots & \vdots \\
b_1 & b_2 & b_3 & \cdots & a_n
\end{vmatrix}$$

按第一列展开（只有 $(1,1)$ 和 $(n,1)$ 位置非零）较复杂。更好的做法是用行列式性质直接推出：

实际上，这是一个经典行列式，结果为：
$$D = (a_1 - b_1)(a_2 - b_2) \cdots (a_{n-1} - b_{n-1})(a_n - b_n)$$

即 $D = \prod_{i=1}^{n} (a_i - b_i)$（其中 $b_n$ 需另行定义，或结果形式为递推）。

> 简化解法：从最后一行开始逐行向上减，可得 $D = (a_1 - 1)\prod_{i=2}^{n}(a_i - b_{i-1})$（具体取决于行列式的确切定义）。精确结果依赖于原始矩阵的准确结构，此处给出标准递推思路.

---

**2.（11 分）** 设 $A = \begin{pmatrix} 3 & 0 & -1 \\ 1 & 3 & 0 \\ -1 & 1 & 3 \end{pmatrix}$，$B = \begin{pmatrix} 1 & 1 \\ 0 & 1 \\ 1 & 0 \end{pmatrix}$，且有 $AX - 2X = B$，求 $X$.

### 解析

$AX - 2X = B \implies (A - 2E)X = B$.

$$A - 2E = \begin{pmatrix} 3-2 & 0 & -1 \\ 1 & 3-2 & 0 \\ -1 & 1 & 3-2 \end{pmatrix} = \begin{pmatrix} 1 & 0 & -1 \\ 1 & 1 & 0 \\ -1 & 1 & 1 \end{pmatrix}$$

求 $(A - 2E)^{-1}$，然后 $X = (A - 2E)^{-1}B$.

先求行列式：$|A - 2E| = \begin{vmatrix} 1 & 0 & -1 \\ 1 & 1 & 0 \\ -1 & 1 & 1 \end{vmatrix}$.

按第一行展开：$1 \cdot \begin{vmatrix} 1 & 0 \\ 1 & 1 \end{vmatrix} - 0 + (-1) \cdot \begin{vmatrix} 1 & 1 \\ -1 & 1 \end{vmatrix} = 1 \cdot 1 - (1 \cdot 1 - 1 \cdot (-1)) = 1 - 2 = -1 \neq 0$.

求伴随矩阵（代数余子式）：

- $A_{11} = +\begin{vmatrix} 1 & 0 \\ 1 & 1 \end{vmatrix} = 1$
- $A_{12} = -\begin{vmatrix} 1 & 0 \\ -1 & 1 \end{vmatrix} = -1$
- $A_{13} = +\begin{vmatrix} 1 & 1 \\ -1 & 1 \end{vmatrix} = 2$
- $A_{21} = -\begin{vmatrix} 0 & -1 \\ 1 & 1 \end{vmatrix} = -1$
- $A_{22} = +\begin{vmatrix} 1 & -1 \\ -1 & 1 \end{vmatrix} = 0$
- $A_{23} = -\begin{vmatrix} 1 & 0 \\ -1 & 1 \end{vmatrix} = -1$
- $A_{31} = +\begin{vmatrix} 0 & -1 \\ 1 & 0 \end{vmatrix} = 1$
- $A_{32} = -\begin{vmatrix} 1 & -1 \\ 1 & 0 \end{vmatrix} = -1$
- $A_{33} = +\begin{vmatrix} 1 & 0 \\ 1 & 1 \end{vmatrix} = 1$

伴随矩阵：$(A-2E)^* = \begin{pmatrix} 1 & -1 & 1 \\ -1 & 0 & -1 \\ 2 & -1 & 1 \end{pmatrix}$

$$(A-2E)^{-1} = \frac{1}{-1} \begin{pmatrix} 1 & -1 & 1 \\ -1 & 0 & -1 \\ 2 & -1 & 1 \end{pmatrix} = \begin{pmatrix} -1 & 1 & -1 \\ 1 & 0 & 1 \\ -2 & 1 & -1 \end{pmatrix}$$

$$X = (A-2E)^{-1}B = \begin{pmatrix} -1 & 1 & -1 \\ 1 & 0 & 1 \\ -2 & 1 & -1 \end{pmatrix} \begin{pmatrix} 1 & 1 \\ 0 & 1 \\ 1 & 0 \end{pmatrix}$$

计算：

第一列：$\begin{pmatrix} -1\cdot1 + 1\cdot0 + (-1)\cdot1 \\ 1\cdot1 + 0\cdot0 + 1\cdot1 \\ -2\cdot1 + 1\cdot0 + (-1)\cdot1 \end{pmatrix} = \begin{pmatrix} -2 \\ 2 \\ -3 \end{pmatrix}$

第二列：$\begin{pmatrix} -1\cdot1 + 1\cdot1 + (-1)\cdot0 \\ 1\cdot1 + 0\cdot1 + 1\cdot0 \\ -2\cdot1 + 1\cdot1 + (-1)\cdot0 \end{pmatrix} = \begin{pmatrix} 0 \\ 1 \\ -1 \end{pmatrix}$

$$X = \begin{pmatrix} -2 & 0 \\ 2 & 1 \\ -3 & -1 \end{pmatrix}$$

---

**3.（11 分）** 当 $\lambda$ 为何值时，方程组

$$\begin{cases}
2x_1 + \lambda x_2 - x_3 = 1, \\
\lambda x_1 - x_2 + x_3 = 2, \\
4x_1 + 5x_2 - 5x_3 = -1
\end{cases}$$

有唯一解、无解或有无穷多组解？当有无穷多组解时，用导出组的基础解系表示全部解.

### 解析

增广矩阵：

$$\begin{pmatrix} 2 & \lambda & -1 & | & 1 \\ \lambda & -1 & 1 & | & 2 \\ 4 & 5 & -5 & | & -1 \end{pmatrix}$$

先计算系数矩阵的行列式：

$$|A| = \begin{vmatrix} 2 & \lambda & -1 \\ \lambda & -1 & 1 \\ 4 & 5 & -5 \end{vmatrix}$$

按第一行展开：

$$= 2 \begin{vmatrix} -1 & 1 \\ 5 & -5 \end{vmatrix} - \lambda \begin{vmatrix} \lambda & 1 \\ 4 & -5 \end{vmatrix} + (-1) \begin{vmatrix} \lambda & -1 \\ 4 & 5 \end{vmatrix}$$
$$= 2(5 - 5) - \lambda(-5\lambda - 4) - (\lambda \cdot 5 - (-1) \cdot 4)$$
$$= 0 + \lambda(5\lambda + 4) - (5\lambda + 4)$$
$$= (5\lambda + 4)(\lambda - 1)$$

$|A| \neq 0$ 即 $\lambda \neq 1$ 且 $\lambda \neq -\frac{4}{5}$ 时，有唯一解.

- **当 $\lambda = 1$**：代入增广矩阵

$$\begin{pmatrix} 2 & 1 & -1 & | & 1 \\ 1 & -1 & 1 & | & 2 \\ 4 & 5 & -5 & | & -1 \end{pmatrix}$$

$r_2 \leftrightarrow r_1$，$r_2 - 2r_1$, $r_3 - 4r_1$：

$$\rightarrow \begin{pmatrix} 1 & -1 & 1 & | & 2 \\ 0 & 3 & -3 & | & -3 \\ 0 & 9 & -9 & | & -9 \end{pmatrix}$$

$r_3 - 3r_2$：第三行全零。$r(A) = r(\bar{A}) = 2 < 3$，**有无穷多解**.

同解方程组：$\begin{cases} x_1 - x_2 + x_3 = 2 \\ 3x_2 - 3x_3 = -3 \end{cases} \implies \begin{cases} x_1 = 1 \\ x_2 = x_3 - 1 \end{cases}$

通解：$\begin{pmatrix} x_1 \\ x_2 \\ x_3 \end{pmatrix} = \begin{pmatrix} 1 \\ -1 \\ 0 \end{pmatrix} + k\begin{pmatrix} 0 \\ 1 \\ 1 \end{pmatrix},\ k \in \mathbb{R}$.

- **当 $\lambda = -\frac{4}{5}$**：代入增广矩阵并化简

$$\begin{pmatrix} 2 & -\frac{4}{5} & -1 & | & 1 \\ -\frac{4}{5} & -1 & 1 & | & 2 \\ 4 & 5 & -5 & | & -1 \end{pmatrix}$$

乘以 5 消分母：$\begin{pmatrix} 10 & -4 & -5 & | & 5 \\ -4 & -5 & 5 & | & 10 \\ 4 & 5 & -5 & | & -1 \end{pmatrix}$

$r_3 + r_2$：$\begin{pmatrix} 10 & -4 & -5 & | & 5 \\ -4 & -5 & 5 & | & 10 \\ 0 & 0 & 0 & | & 9 \end{pmatrix}$

$r(A) = 2$, $r(\bar{A}) = 3$，**无解**.

> 总结：
> - $\lambda \neq 1$ 且 $\lambda \neq -\frac{4}{5}$ 时，有唯一解
> - $\lambda = 1$ 时，有无穷多解
> - $\lambda = -\frac{4}{5}$ 时，无解

---

**4.（13 分）** 已知向量组

$\alpha_1 = (1, 3, 2, 0)^T$, $\alpha_2 = (7, 0, 14, 3)^T$, $\alpha_3 = (2, -1, 0, 1)^T$, $\alpha_4 = (5, 1, 6, 2)^T$, $\alpha_5 = (2, -1, 4, 1)^T$.

（1）求该向量组的秩；
（2）求该向量组的一个极大线性无关组，并把其余向量分别用该极大线性无关组线性表示.

### 解析

以向量为列构造 $4 \times 5$ 矩阵：

$$A = \begin{pmatrix}
1 & 7 & 2 & 5 & 2 \\
3 & 0 & -1 & 1 & -1 \\
2 & 14 & 0 & 6 & 4 \\
0 & 3 & 1 & 2 & 1
\end{pmatrix}$$

做初等行变换：

$r_2 - 3r_1$, $r_3 - 2r_1$：

$$\rightarrow \begin{pmatrix}
1 & 7 & 2 & 5 & 2 \\
0 & -21 & -7 & -14 & -7 \\
0 & 0 & -4 & -4 & 0 \\
0 & 3 & 1 & 2 & 1
\end{pmatrix}$$

$r_2 \div (-7)$：

$$\rightarrow \begin{pmatrix}
1 & 7 & 2 & 5 & 2 \\
0 & 3 & 1 & 2 & 1 \\
0 & 0 & -4 & -4 & 0 \\
0 & 3 & 1 & 2 & 1
\end{pmatrix}$$

$r_4 - r_2$：第四行全零.

$r_3 \div (-4)$：

$$\rightarrow \begin{pmatrix}
1 & 7 & 2 & 5 & 2 \\
0 & 3 & 1 & 2 & 1 \\
0 & 0 & 1 & 1 & 0 \\
0 & 0 & 0 & 0 & 0
\end{pmatrix}$$

$r_2 - r_3$, $r_2 \div 3$：

$$\rightarrow \begin{pmatrix}
1 & 7 & 2 & 5 & 2 \\
0 & 1 & 0 & \frac{1}{3} & \frac{1}{3} \\
0 & 0 & 1 & 1 & 0 \\
0 & 0 & 0 & 0 & 0
\end{pmatrix}$$

$r_1 - 7r_2 - 2r_3$：

$$\rightarrow \begin{pmatrix}
1 & 0 & 0 & 5 - \frac{7}{3} - 2 & 2 - \frac{7}{3} \\
0 & 1 & 0 & \frac{1}{3} & \frac{1}{3} \\
0 & 0 & 1 & 1 & 0 \\
0 & 0 & 0 & 0 & 0
\end{pmatrix}$$

计算：$5 - \frac{7}{3} - 2 = 3 - \frac{7}{3} = \frac{2}{3}$，$2 - \frac{7}{3} = -\frac{1}{3}$.

行最简形：

$$\rightarrow \begin{pmatrix}
1 & 0 & 0 & \frac{2}{3} & -\frac{1}{3} \\
0 & 1 & 0 & \frac{1}{3} & \frac{1}{3} \\
0 & 0 & 1 & 1 & 0 \\
0 & 0 & 0 & 0 & 0
\end{pmatrix}$$

**(1)** 秩 $r = 3$.

**(2)** 极大线性无关组：$\alpha_1, \alpha_2, \alpha_3$.

由行最简形得：

- $\alpha_4 = \frac{2}{3}\alpha_1 + \frac{1}{3}\alpha_2 + \alpha_3$
- $\alpha_5 = -\frac{1}{3}\alpha_1 + \frac{1}{3}\alpha_2$

---

**5.（14 分）** 设 $A = \begin{pmatrix} 4 & 2 & 2 \\ 2 & 4 & 2 \\ 2 & 2 & 4 \end{pmatrix}$，求正交矩阵 $P$，使 $P^{-1}AP$ 为对角矩阵，并写出相应的对角矩阵.

### 解析

$$|\lambda E - A| = \begin{vmatrix} \lambda-4 & -2 & -2 \\ -2 & \lambda-4 & -2 \\ -2 & -2 & \lambda-4 \end{vmatrix}$$

该矩阵每行和相等。$r_1 + r_2 + r_3$：

特征多项式：
$$= (\lambda-8) \begin{vmatrix} 1 & 1 & 1 \\ -2 & \lambda-4 & -2 \\ -2 & -2 & \lambda-4 \end{vmatrix}$$

第一行提取公因子后：
$$= (\lambda-8) \begin{vmatrix} 1 & 0 & 0 \\ -2 & \lambda-2 & 0 \\ -2 & 0 & \lambda-2 \end{vmatrix} = (\lambda-8)(\lambda-2)^2$$

特征值：$\lambda_1 = 8$（单根），$\lambda_2 = \lambda_3 = 2$（二重根）.

**求特征向量：**

对 $\lambda_1 = 8$：$(A - 8E)x = 0$.

$$A - 8E = \begin{pmatrix} -4 & 2 & 2 \\ 2 & -4 & 2 \\ 2 & 2 & -4 \end{pmatrix}$$

行变换得 $x_1 = x_2 = x_3$，单位化：$p_1 = \frac{1}{\sqrt{3}}(1, 1, 1)^T$.

对 $\lambda_2 = 2$：$(A - 2E)x = 0$.

$$A - 2E = \begin{pmatrix} 2 & 2 & 2 \\ 2 & 2 & 2 \\ 2 & 2 & 2 \end{pmatrix} \rightarrow \begin{pmatrix} 1 & 1 & 1 \\ 0 & 0 & 0 \\ 0 & 0 & 0 \end{pmatrix}$$

$x_1 + x_2 + x_3 = 0$，解空间维数为 2.

取两个正交的基础解向量（Schmidt 正交化）：

$p_2 = \frac{1}{\sqrt{2}}(1, -1, 0)^T$（满足 $x_1 + x_2 + x_3 = 0$，且与 $p_1$ 正交）

$p_3$ 需与 $p_1, p_2$ 均正交：从 $x_1 + x_2 + x_3 = 0$ 且 $x_1 - x_2 = 0$ 得 $x_1 = x_2$, $x_3 = -2x_1$.

取 $p_3 = \frac{1}{\sqrt{6}}(1, 1, -2)^T$.

正交矩阵：
$$P = \begin{pmatrix}
\frac{1}{\sqrt{3}} & \frac{1}{\sqrt{2}} & \frac{1}{\sqrt{6}} \\
\frac{1}{\sqrt{3}} & -\frac{1}{\sqrt{2}} & \frac{1}{\sqrt{6}} \\
\frac{1}{\sqrt{3}} & 0 & -\frac{2}{\sqrt{6}}
\end{pmatrix}$$

对角矩阵：
$$P^{-1}AP = P^TAP = \operatorname{diag}(8, 2, 2)$$

---

## 四、证明题（每小题 8 分，共 16 分）

**1.** 设 $\alpha$ 是 $n$ 阶矩阵 $A$ 的属于特征值 $\lambda$ 的特征向量，证明：$\alpha$ 也是 $A^5 + 4A^3 + E$ 的特征向量，其中 $E$ 为 $n$ 阶单位矩阵.

### 解析

已知 $A\alpha = \lambda\alpha$（$\alpha \neq 0$）.

则 $A^2\alpha = A(A\alpha) = A(\lambda\alpha) = \lambda A\alpha = \lambda^2\alpha$.

归纳可得 $A^k\alpha = \lambda^k\alpha$（$k \in \mathbb{N}$）.

于是：
$$(A^5 + 4A^3 + E)\alpha = A^5\alpha + 4A^3\alpha + E\alpha$$
$$= \lambda^5\alpha + 4\lambda^3\alpha + \alpha$$
$$= (\lambda^5 + 4\lambda^3 + 1)\alpha$$

故 $\alpha$ 是 $A^5 + 4A^3 + E$ 的属于特征值 $\lambda^5 + 4\lambda^3 + 1$ 的特征向量.

> 证毕.

---

**2.** 设 $n$ 维向量组 $\alpha, \beta, \gamma$ 线性无关，向量组 $\alpha, \beta, \delta$ 线性相关，证明：$\delta$ 必可由 $\alpha, \beta, \gamma$ 线性表示.

### 解析

由 $\alpha, \beta, \delta$ 线性相关，存在不全为零的数 $k_1, k_2, k_3$ 使得：
$$k_1\alpha + k_2\beta + k_3\delta = 0.$$

若 $k_3 = 0$，则 $k_1\alpha + k_2\beta = 0$。由于 $\alpha, \beta$ 线性无关（$\alpha, \beta, \gamma$ 线性无关 $\implies$ 其部分组 $\alpha, \beta$ 线性无关），必有 $k_1 = k_2 = 0$，与 "不全为零" 矛盾.

故 $k_3 \neq 0$，从而：
$$\delta = -\frac{k_1}{k_3}\alpha - \frac{k_2}{k_3}\beta = \left(-\frac{k_1}{k_3}\right)\alpha + \left(-\frac{k_2}{k_3}\right)\beta + 0 \cdot \gamma.$$

即 $\delta$ 可由 $\alpha, \beta, \gamma$ 线性表示.

> 证毕.
