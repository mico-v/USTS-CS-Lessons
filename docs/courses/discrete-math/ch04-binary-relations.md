# 第 4 章 二元关系

> 关系是「两个对象之间有联系」这件事的集合化写法：把「$a$ 和 $b$ 有联系」记成一个序偶 $\langle a,b\rangle$，把所有这样的序偶收集起来，就是一个关系。本章把关系当成**特殊的集合**来处理，于是集合的并、交、差、补全都能用，另外还多了两个专属运算**复合**与**逆**。考试的重点非常明确：**关系的三种表示（集合/矩阵/图）互相转换**、**复合与幂的具体计算**、**五条性质的判定与证明**、**三种闭包的计算（含 Warshall 算法）**，这四类题几乎覆盖了整章分值。难点在于「关系既是集合又是一张表」的双重视角，以及复合运算的**方向**（$R\circ S$ 是先走 $R$ 再走 $S$）。

---

## 一、二元关系及其表示

### 1.1 序偶：给「一对」加上顺序

集合是无序的，$\{1,2\}=\{2,1\}$。但生活里大量事情是**成对且有序**的：$3<4$ 和 $4<3$ 不是一回事；「中国的首都是北京」换成「北京的首都是中国」就是错的。所以要造一个新东西。

**定义 4.1（序偶）** 由两个元素 $x,y$ 按一定次序组成的二元组称为**有序偶对**，简称**序偶**，记作 $\langle x,y\rangle$，$x$ 称为第一元素，$y$ 称为第二元素。

序偶和二元集的区别只有一条，但这条决定一切：$\langle a,b\rangle=\langle b,a\rangle \iff a=b$，而 $\{a,b\}=\{b,a\}$ 恒成立。

**定义 4.2（序偶相等）** $\langle a,b\rangle=\langle c,d\rangle \iff a=c \wedge b=d$。

也就是说，判断两个序偶相等要**两个位置分别相等**，不能像集合那样看「元素是否一样」。

> 例：求 $x,y$ 使 $\langle x+y,4\rangle$ 与 $\langle 5,2x-y\rangle$ 相等。按定义 4.2 逐位对齐：$x+y=5$，$2x-y=4$。两式相加得 $3x=9$，即 $x=3$，代回得 $y=2$。所以 $x=3,\ y=2$。

把两个元素推广到 $n$ 个，得到**$n$ 重有序组** $\langle a_1,a_2,\dots,a_n\rangle$，相等条件同样逐位比较：$\langle a_1,\dots,a_n\rangle=\langle b_1,\dots,b_n\rangle \iff a_i=b_i\ (i=1,\dots,n)$。

> **易错**：序偶里可以套序偶，$\langle a,\langle b,c\rangle\rangle$ 和 $\langle\langle a,b\rangle,c\rangle$ 是**两个不同的东西**，前者第一元素是 $a$，后者第一元素是一个序偶。写笛卡尔积的结合律时会栽在这里。

### 1.2 笛卡尔积

**定义 4.3（笛卡尔积）** 设 $A,B$ 是两个集合，称 $A\times B=\{\langle x,y\rangle \mid x\in A \wedge y\in B\}$ 为 $A$ 与 $B$ 的**笛卡尔积**。直白地说：把 $A$ 里每个元素当「第一元素」、$B$ 里每个元素当「第二元素」，两两配对，所有配对的集合就是 $A\times B$。它是**以序偶为元素的集合**——这一点是后面一切的基础。

四条必须记住的性质：

| 性质 | 内容 | 说明 |
| --- | --- | --- |
| 元素形态 | $A\times B$ 的元素都是序偶 | 不是数、不是集合 |
| 基数 | $\lvert A\times B\rvert=\lvert A\rvert\times\lvert B\rvert$（有限集） | 乘法原理 |
| 交换律 | $A\times B \neq B\times A$（一般） | 除非 $A=B$ 或有一个为空 |
| 空积 | $A\times B=\varnothing \iff A=\varnothing \vee B=\varnothing$ | 一边空，配对就配不出来 |

交换律不成立：$A=\{a\}$，$B=P(A)=\{\varnothing,\{a\}\}$，则

$$A\times B=\{\langle a,\varnothing\rangle,\langle a,\{a\}\rangle\},\qquad B\times A=\{\langle \varnothing,a\rangle,\langle \{a\},a\rangle\}$$

两个集合的元素完全不一样，当然不相等。**结合律也不成立**：同上取 $A,\ B$，再加 $D=\{0,1\}$，则

$$A\times(B\times D)=\{\langle a,\langle \varnothing,0\rangle\rangle,\ \langle a,\langle \varnothing,1\rangle\rangle,\ \langle a,\langle \{a\},0\rangle\rangle,\ \langle a,\langle \{a\},1\rangle\rangle\}$$
$$(A\times B)\times D=\{\langle\langle a,\varnothing\rangle,0\rangle,\ \langle\langle a,\varnothing\rangle,1\rangle,\ \langle\langle a,\{a\}\rangle,0\rangle,\ \langle\langle a,\{a\}\rangle,1\rangle\}$$

外观上一个「里面套」一个「外面套」，元素结构不同，故 $(A\times B)\times D\neq A\times(B\times D)$。所以写 $A\times B\times C$ 时它**不是**前两种写法中的任何一个，而是另立定义（见定义 4.4）。

> **易错**：$\lvert A\times B\rvert=\lvert B\times A\rvert$ 成立，但 $A\times B=B\times A$ 一般**不**成立。「元素个数相同」推不出「集合相等」，这是集合题最常见的失分点。

笛卡尔积对并、交满足分配律：

**定理 4.1** 设 $A,B,C$ 是任意三个集合，则

$$A\times(B\cup C)=(A\times B)\cup(A\times C),\qquad (B\cup C)\times A=(B\times A)\cup(C\times A)$$
$$A\times(B\cap C)=(A\times B)\cap(A\times C),\qquad (B\cap C)\times A=(B\times A)\cap(C\times A)$$

证明套路固定：两端都是集合，就证互相包含；而元素是序偶，就把 $\langle x,y\rangle\in$ 左边一路等价改写下去：

$$\langle x,y\rangle\in A\times(B\cup C)\iff x\in A\wedge y\in B\cup C\iff x\in A\wedge(y\in B\vee y\in C)$$
$$\iff(x\in A\wedge y\in B)\vee(x\in A\wedge y\in C)\iff \langle x,y\rangle\in(A\times B)\cup(A\times C)$$

每一步都只是「换定义」或「用分配律」，没有别的技巧。**注意这里能成立恰恰是因为 $x\in A$ 是公共因子**——换成并分配于交的位置就未必有对应规律了，不要凭感觉推广。

> **易错**：$A\times(B\cap C)=(A\times B)\cap(A\times C)$ 成立，但**不**能由此写成 $A\times(B\cap C)=(A\times B)\cap(B\times C)$。分配时公共的那个因子必须是同一个集合。

**定理 4.2** 设 $A,B,C,D$ 是四个**非空**集合，则 $A\times B\subseteq C\times D \iff A\subseteq C \wedge B\subseteq D$。「非空」这个条件不能丢：$A=\varnothing$ 时 $A\times B=\varnothing\subseteq C\times D$ 恒成立，但推不出 $B\subseteq D$。最后推广到多个集合。

**定义 4.4（$n$ 元笛卡尔积）** 设 $A_1,\dots,A_n$ 是 $n$ 个集合，

$$A_1\times A_2\times\cdots\times A_n=\{\langle a_1,\dots,a_n\rangle \mid a_i\in A_i,\ i=1,\dots,n\}$$

当 $A_1=\cdots=A_n=A$ 时简记 $A^n$。有限集时 $\lvert A_1\times\cdots\times A_n\rvert=\lvert A_1\rvert\times\cdots\times\lvert A_n\rvert$。例：$A=\{0,1\}$，$A^3$ 有 $2^3=8$ 个元素，正好是三位二进制串 $\langle 0,0,0\rangle$ 到 $\langle 1,1,1\rangle$。这个直觉很重要：**程序里的「记录」「元组」就是 $n$ 重有序组**。

### 1.3 二元关系的定义

先看一个具体问题。$A=\{1,4\}$，$B=\{a,b\}$，$A\times B=\{\langle 1,a\rangle,\langle 4,a\rangle,\langle 1,b\rangle,\langle 4,b\rangle\}$。现在取

$$R=\{\langle 1,a\rangle,\langle 1,b\rangle,\langle 4,b\rangle\}$$

显然 $R\subseteq A\times B$。也就是说，$R$ 是从所有配对里**挑出一部分**。这个「挑出来的子集」就是关系。

**定义 4.5（二元关系）** 设 $A,B$ 是两个非空集合，称 $A\times B$ 的任何子集 $R$ 为**从 $A$ 到 $B$ 的二元关系**，记作 $R:A\to B$；若 $A=B$，称 $R$ 为 **$A$ 上的二元关系**，记作 $R:A\to A$。

$\langle x,y\rangle\in R$ 记作 $xRy$，读作「$x$ 对 $y$ 有关系 $R$」；$\langle x,y\rangle\notin R$ 记作 $x\not R y$。

于是「关系」二字其实有两层意思：

- 作为**集合**：它是一堆序偶，能求并交差补、能数个数；
- 作为**表**：它描述哪两个对象之间有联系，能画图。

**判断「给定集合是不是 $A$ 到 $B$ 的关系」的标准两步**：（1）算出 $A\times B$；（2）看给定集合是不是它的子集。只要出现 $A$ 以外或 $B$ 以外的元素，就立刻不是。

> 例：$A=\{1,4\}$，$B=\{a,b\}$，判断 $S_1=\{\langle 3,b\rangle\}$、$S_2=\{\langle 1,a\rangle,\langle 4,a\rangle,\langle 1,b\rangle,\langle 4,b\rangle\}$ 是否为 $A$ 到 $B$ 的关系。
>
> $A\times B=\{\langle 1,a\rangle,\langle 4,a\rangle,\langle 1,b\rangle,\langle 4,b\rangle\}$。$S_1$ 含 $3\notin A$，不是子集，故 $S_1$ **不是**。$S_2$ 与 $A\times B$ 完全相同，是子集，故 $S_2$ **是**，而且它恰好是全域关系。

**定义 4.6（$n$ 元关系）** 设 $A_1,\dots,A_n$ 为 $n$ 个非空集合，则 $A_1\times\cdots\times A_n$ 的子集 $R$ 称为以 $A_1\times\cdots\times A_n$ 为**基**的 **$n$ 元关系**。

例：$R=\{\langle 张红,离散数学\rangle,\langle 李明,组合数学\rangle\}$ 是 $2$ 元关系；选课记录 $\langle 学号,课号,成绩\rangle$ 是 $3$ 元关系，也就是数据库里的一行。

### 1.4 三个特殊关系与关系的个数

在 $A$ 上的关系中，有三个「极端」的必须张口就答：

| 名称 | 记号 | 定义 | 例子（$A=\{1,2\}$） |
| --- | --- | --- | --- |
| 空关系 | $\varnothing$ | 不含任何序偶 | $\varnothing$ |
| 全域关系 | $E_A$ | $E_A=A\times A$ | $\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,1\rangle,\langle 2,2\rangle\}$ |
| 恒等关系 | $I_A$ | $I_A=\{\langle x,x\rangle \mid x\in A\}$ | $\{\langle 1,1\rangle,\langle 2,2\rangle\}$ |

三者的关系矩阵：空关系是**全 0 矩阵**，$E_A$ 是**全 1 矩阵**，$I_A$ 是**单位矩阵**（主对角线 1，其余 0）。这三句话在 4.3 节判断性质时会反复用到。

**关系一共有多少个？** $A\times B$ 有 $\lvert A\rvert\cdot\lvert B\rvert$ 个元素，每个元素「选或不选」构成一个子集，所以

$$\text{从 } A \text{ 到 } B \text{ 的关系总数}=2^{\lvert A\rvert\cdot\lvert B\rvert}$$

例：$A=\{b\}$，$B=\{c,d\}$，$A\times B=\{\langle b,c\rangle,\langle b,d\rangle\}$，全部关系是 $\varnothing,\{\langle b,c\rangle\},\{\langle b,d\rangle\},\{\langle b,c\rangle,\langle b,d\rangle\}$，正好 $2^2=4$ 个。

> **易错**：$A$ 上关系个数是 $2^{n^2}$，不是 $2^n$。$n=3$ 时是 $2^9=512$ 个，指数是「序偶个数」而不是「元素个数」。

### 1.5 前域、值域与域

关系是序偶的集合，序偶有两个位置，于是可以分别收集：

$$\mathrm{dom}\,R=\{x \mid \exists y\,(\langle x,y\rangle\in R)\},\qquad \mathrm{ran}\,R=\{y \mid \exists x\,(\langle x,y\rangle\in R)\},\qquad \mathrm{fld}\,R=\mathrm{dom}\,R\cup\mathrm{ran}\,R$$

注意区分**前域/后域**和**定义域/值域**：若 $R:A\to B$，则 $A$ 是前域、$B$ 是后域（这是「允许的取值范围」）；而 $\mathrm{dom}\,R\subseteq A$、$\mathrm{ran}\,R\subseteq B$（这是「实际用到的值」）。两者常常不相等——这正是最容易搞混的一对概念。

> 例：$A=\{1,2,4,8\}$，$R$ 是 $A$ 上的小于关系。
>
> $R=\{\langle 1,2\rangle,\langle 1,4\rangle,\langle 1,8\rangle,\langle 2,4\rangle,\langle 2,8\rangle,\langle 4,8\rangle\}$，共 $6$ 个序偶（$4$ 个元素两两配对 $\binom{4}{2}=6$）。
>
> $\mathrm{dom}\,R=\{1,2,4\}$（$8$ 没有比它大的），$\mathrm{ran}\,R=\{2,4,8\}$（$1$ 没有比它小的），$\mathrm{fld}\,R=\{1,2,4,8\}$。
>
> 前域是 $A=\{1,2,4,8\}$，但 $\mathrm{dom}\,R$ 少了 $8$——这就是二者的差别。

> **易错**：$\mathrm{dom}\,R$ 不等于前域，$\mathrm{ran}\,R$ 不等于后域。只有 $A$ 中每个元素都当过第一元素时才有 $\mathrm{dom}\,R=A$。

### 1.6 关系矩阵

要把关系交给计算机，最自然的办法是**打表**。设 $A=\{a_1,\dots,a_n\}$，$B=\{b_1,\dots,b_m\}$，规定

$$m_{ij}=\begin{cases}1, & \langle a_i,b_j\rangle\in R\\ 0, & \langle a_i,b_j\rangle\notin R\end{cases}$$

得到的 $n\times m$ 矩阵 $M_R$ 就是 $R$ 的**关系矩阵**（也叫邻接矩阵）。它是 **0-1 矩阵**，也就是**布尔矩阵**。

要点三条：

1. **必须先给两个集合的元素排序**——行下标对应 $A$ 的序号，列下标对应 $B$ 的序号。排序方式不同，矩阵就不同（但它们描述同一个关系，考查时题目会指定顺序）；
2. 矩阵中 **1 的个数 = 序偶的个数**；
3. $A$ 上的关系，矩阵是 $n\times n$ 方阵。

> 例：$A=\{1,2,3,4\}$ 上的「大于等于」关系
>
> $$S=\{\langle 1,1\rangle,\langle 2,1\rangle,\langle 2,2\rangle,\langle 3,1\rangle,\langle 3,2\rangle,\langle 3,3\rangle,\langle 4,1\rangle,\langle 4,2\rangle,\langle 4,3\rangle,\langle 4,4\rangle\},\qquad M_S=\begin{pmatrix}1&0&0&0\\1&1&0&0\\1&1&1&0\\1&1&1&1\end{pmatrix}$$
>
> 下三角全 1、上三角全 0，一眼看出大小方向；$M_S$ 有 $10$ 个 1，与序偶个数一致。再看 $A=\{1,2\}$、$P(A)=\{\varnothing,\{1\},\{2\},\{1,2\}\}$ 上的包含关系 $R$ 与真包含关系 $S'$（按 $\varnothing,\{1\},\{2\},\{1,2\}$ 排序）：
>
> $$M_R=\begin{pmatrix}1&1&1&1\\0&1&0&1\\0&0&1&1\\0&0&0&1\end{pmatrix},\qquad M_{S'}=\begin{pmatrix}0&1&1&1\\0&0&0&1\\0&0&0&1\\0&0&0&0\end{pmatrix}$$
>
> 两者只差主对角线：包含允许自己包含自己（对角线全 1），真包含不允许（全 0）。这就是 4.3 节「自反 / 反自反」的矩阵判据。

### 1.7 关系图

关系图把序偶画成有向边，最直观。**$A\neq B$ 时**以 $A\cup B$ 的元素为顶点，若 $\langle a_i,b_j\rangle\in R$ 就画一条 $a_i\to b_j$ 的有向边；**$A=B$ 时**顶点只有 $A$ 的 $n$ 个元素，$i=j$ 的序偶画成**带箭头的自环**。两条画图纪律：

- 没有边相连的顶点**不能从图上删掉**（否则就不知道它属于哪个基集了）；
- 序偶与有向边**一一对应**，边数 = 序偶数。

> 例：$A=\{1,2,3,4\}$ 上的「大于等于」关系（就是上一个小节的 $S$）。图上 $1$ 有自环、无出边到别人；$2$ 有自环和 $2\to 1$；$3$ 有自环和 $3\to1,3\to2$；$4$ 有自环和指向 $1,2,3$ 的边。整个图是「完全的下三角 + 四个自环」，共 $10$ 条边。

### 1.8 三种表示互转（考法）

| 已知 | 求集合 | 求矩阵 | 求图 |
| --- | --- | --- | --- |
| 集合 | — | 排序后逐格填 0/1 | 每个序偶画一条边，注意自环 |
| 矩阵 | 第 $i$ 行第 $j$ 列为 1 就写 $\langle a_i,b_j\rangle$ | — | 每个 1 画一条边 |
| 图 | 每条边读成一个序偶 | 每个 1 的位置填 1 | — |

判断依据只有一句：**三者是同一份信息的不同外壳**，序偶 ↔ 矩阵里的 1 ↔ 图上有向边，三者严格一一对应。所以任何一题都可以先在脑子里转成自己最熟的那种形式。

> **易错**：从关系图写集合表示时，漏掉自环或漏掉孤立顶点对应的信息。孤立顶点本身不产生序偶，但**它属于基集**，求 $\mathrm{dom}$、$\mathrm{ran}$ 时要注意它可能不在里面；反过来求关系个数、判断性质时它又必须算进去。

---

## 二、关系的运算

关系首先是集合，所以集合的并、交、差、补**全部可以直接用**。设 $R,S$ 都是从 $A$ 到 $B$ 的关系：

$$R\cup S=\{\langle x,y\rangle \mid \langle x,y\rangle\in R \vee \langle x,y\rangle\in S\}$$
$$R\cap S=\{\langle x,y\rangle \mid \langle x,y\rangle\in R \wedge \langle x,y\rangle\in S\}$$
$$R-S=\{\langle x,y\rangle \mid \langle x,y\rangle\in R \wedge \langle x,y\rangle\notin S\}$$
$$R^{c}=\{\langle x,y\rangle \mid \langle x,y\rangle\in A\times B \wedge \langle x,y\rangle\notin R\}=A\times B-R$$

补集要特别注意：**补是相对于 $A\times B$ 这个全集补的**，不是相对于别的什么。于是恒有 $R\cup R^{c}=A\times B$、$R\cap R^{c}=\varnothing$。

> 例：$A=\{a,b\}$，$B=\{1,2\}$，$R=\{\langle a,1\rangle,\langle a,2\rangle\}$，$S=\{\langle a,1\rangle,\langle b,1\rangle,\langle b,2\rangle\}$。
>
> 先写 $A\times B=\{\langle a,1\rangle,\langle a,2\rangle,\langle b,1\rangle,\langle b,2\rangle\}$，然后逐项加减：
>
> - $R\cup S=\{\langle a,1\rangle,\langle a,2\rangle,\langle b,1\rangle,\langle b,2\rangle\}=A\times B$；
> - $R\cap S=\{\langle a,1\rangle\}$；
> - $R-S=\{\langle a,2\rangle\}$，$S-R=\{\langle b,1\rangle,\langle b,2\rangle\}$；
> - $R^{c}=A\times B-R=\{\langle b,1\rangle,\langle b,2\rangle\}=S-R$；
> - $S^{c}=A\times B-S=\{\langle a,2\rangle\}=R-S$。
>
> 最后一行的巧合是因为这两个关系恰好把 $A\times B$ 分完（$S=R^{c}$），一般并不成立。

本节真正的新东西只有两个专属运算：**逆**和**复合**，再加一个派生的**幂**。

### 2.1 逆运算

生活里的关系都有「反向说法」：$\langle 1,2\rangle$ 在小于关系里，$\langle 2,1\rangle$ 就在大于关系里；父子关系反过来就是父子反过来。所以把序偶两个位置对调，就得到逆关系。

**定义 4.9（逆关系）** 设 $R:A\to B$，则从 $B$ 到 $A$ 的关系 $R^{-1}=\{\langle b,a\rangle \mid \langle a,b\rangle\in R\}$ 称为 $R$ 的**逆关系**，运算「$-1$」称为**逆运算**。三条立刻可得的结论：$(R^{-1})^{-1}=R$，$\varnothing^{-1}=\varnothing$，$(A\times B)^{-1}=B\times A$。

三种表示下的操作，一句话一个：

| 表示 | 求逆怎么做 |
| --- | --- |
| 集合 | 每个序偶里两个元素**换位置** |
| 矩阵 | **转置**（$M_{R^{-1}}=M_{R}^{T}$） |
| 关系图 | 每条有向边**反向** |

由此还可得：$\mathrm{dom}\,R=\mathrm{ran}\,R^{-1}$，$\mathrm{dom}\,R^{-1}=\mathrm{ran}\,R$，$\lvert R\rvert=\lvert R^{-1}\rvert$。

> 例：$A=\{1,2,3,4\}$，$B=\{a,b,c,d\}$，$R=\{\langle 1,a\rangle,\langle 2,c\rangle,\langle 3,b\rangle,\langle 4,b\rangle,\langle 4,d\rangle\}$。对调每个序偶：$R^{-1}=\{\langle a,1\rangle,\langle c,2\rangle,\langle b,3\rangle,\langle b,4\rangle,\langle d,4\rangle\}$。
>
> 检查：$\mathrm{dom}\,R=\{1,2,3,4\}$，$\mathrm{ran}\,R^{-1}=\{1,2,3,4\}$，相等；$\mathrm{ran}\,R=\{a,b,c,d\}$，$\mathrm{dom}\,R^{-1}=\{a,b,c,d\}$，相等。

**定理 4.7** 设 $R,S:A\to B$，则

$$(R\cup S)^{-1}=R^{-1}\cup S^{-1},\quad (R\cap S)^{-1}=R^{-1}\cap S^{-1},\quad (R-S)^{-1}=R^{-1}-S^{-1}$$
$$(R^{c})^{-1}=(R^{-1})^{c},\qquad (A\times B)^{-1}=B\times A,\qquad S\subseteq R \iff S^{-1}\subseteq R^{-1}$$

也就是说，逆运算对并、交、差、补**全都可换**（可以搬进括号里），并且保持包含方向不变。这是逆运算「脾气最好」的地方。

### 2.2 复合运算：走两步

设 $R$ 表示城市间的直达航线，$S$ 表示城市间的直达公路。若 $\langle a,b\rangle\in R$ 且 $\langle b,c\rangle\in S$，则 $a$ 到 $c$ 存在「先坐飞机再坐汽车」的可达路线。把所有这样的 $\langle a,c\rangle$ 收集起来，就是一个新关系。

**定义 4.8（复合关系）** 设 $R:A\to B$，$S:B\to C$，则 $R$ 与 $S$ 的**复合关系**是从 $A$ 到 $C$ 的关系 $R\circ S=\{\langle x,z\rangle \mid x\in A \wedge z\in C \wedge \exists y\,(y\in B \wedge \langle x,y\rangle\in R \wedge \langle y,z\rangle\in S)\}$。**方向必须记牢**：$R\circ S$ 表示**先走 $R$、再走 $S$**。这与函数复合的写法习惯相反，是本章最高频的失分点。记忆口诀：**从右往左读不适用于此处，按书写顺序从左往右走**。

**手算方法**：对 $R$ 中每个序偶 $\langle x,y\rangle$，去 $S$ 里找所有第一元素为 $y$ 的序偶 $\langle y,z\rangle$，把 $x$ 和 $z$ 配成 $\langle x,z\rangle$。找不到配对就丢掉。

还要记住 $\varnothing\circ R=R\circ\varnothing=\varnothing$——中间没有桥，就走不通。

> 例：$A=\{1,2,3,4\}$ 上 $R=\{\langle 1,2\rangle,\langle 3,4\rangle\}$，$S=\{\langle 2,4\rangle,\langle 3,4\rangle,\langle 4,2\rangle\}$，$T=\{\langle 1,4\rangle,\langle 2,1\rangle,\langle 4,2\rangle\}$。
>
> **（1）$R\circ S=\{\langle 1,4\rangle,\langle 3,2\rangle\}$**：$\langle 1,2\rangle\in R$ 在 $S$ 中找以 $2$ 开头的 $\langle 2,4\rangle$，得 $\langle 1,4\rangle$；$\langle 3,4\rangle\in R$ 找以 $4$ 开头的 $\langle 4,2\rangle$，得 $\langle 3,2\rangle$。
>
> **（2）$S\circ R=\varnothing$**：$S$ 的三个序偶第二元素分别是 $4,4,2$，而 $R$ 中没有以 $4$ 或 $2$ 开头的序偶。同一个 $R$ 和 $S$，两个方向一个得两个序偶一个是空集，**复合不满足交换律**。
>
> **（3）$(R\circ S)\circ T$**：由（1）$R\circ S=\{\langle 1,4\rangle,\langle 3,2\rangle\}$。$\langle 1,4\rangle$ 接 $T$ 中的 $\langle 4,2\rangle$ 得 $\langle 1,2\rangle$；$\langle 3,2\rangle$ 接 $T$ 中的 $\langle 2,1\rangle$ 得 $\langle 3,1\rangle$。故 $(R\circ S)\circ T=\{\langle 1,2\rangle,\langle 3,1\rangle\}$。
>
> **（4）$R\circ(S\circ T)$**：先算 $S\circ T$。$\langle 2,4\rangle$ 接 $\langle 4,2\rangle$ 得 $\langle 2,2\rangle$；$\langle 3,4\rangle$ 接 $\langle 4,2\rangle$ 得 $\langle 3,2\rangle$；$\langle 4,2\rangle$ 接 $\langle 2,1\rangle$ 得 $\langle 4,1\rangle$，即 $S\circ T=\{\langle 2,2\rangle,\langle 3,2\rangle,\langle 4,1\rangle\}$。再与 $R$ 复合：$\langle 1,2\rangle$ 接 $\langle 2,2\rangle$ 得 $\langle 1,2\rangle$；$\langle 3,4\rangle$ 接 $\langle 4,1\rangle$ 得 $\langle 3,1\rangle$。故 $R\circ(S\circ T)=\{\langle 1,2\rangle,\langle 3,1\rangle\}$。
>
> 两边一样，这就是**结合律**的一次具体验证。

**定理 4.4** 设 $R:A\to B$，$S:B\to C$，$T:C\to D$，则 $(R\circ S)\circ T=R\circ(S\circ T)$，$I_A\circ R=R\circ I_B=R$。所以写三个关系的复合时**不用加括号**，直接写 $R\circ S\circ T$。证明方法就是集合相等的标准套路：任取 $\langle a,d\rangle$，把「$\in$ 左边」用定义展开成「存在 $b$、存在 $c$ 使三条序偶都在」，再把量词交换顺序收回去，即得右边。

> **易错**：结合律成立，**交换律不成立**，两者千万别混。$R\circ S$ 与 $S\circ R$ 甚至可能一个为空、一个是有限集（如上面的例），也可能一个有定义、另一个根本没有意义（当 $R$ 的值域不在 $S$ 的定义域里时）。

**与并、交的关系**（定理 4.5）：$R\circ(S_1\cup S_2)=(R\circ S_1)\cup(R\circ S_2)$、$(S_1\cup S_2)\circ T=(S_1\circ T)\cup(S_2\circ T)$ 成立，即**复合对并满足分配律**；对交不满足，只有包含关系

$$R\circ(S_1\cap S_2)\subseteq(R\circ S_1)\cap(R\circ S_2),\qquad (S_1\cap S_2)\circ T\subseteq(S_1\circ T)\cap(S_2\circ T)$$

反例（记住这一组数）：$R=\{\langle 2,2\rangle,\langle 2,1\rangle\}$，$S_1=\{\langle 1,2\rangle,\langle 2,3\rangle\}$，$S_2=\{\langle 1,3\rangle\}$。

- $S_1\cap S_2=\varnothing$，故 $R\circ(S_1\cap S_2)=\varnothing$；
- $R\circ S_1=\{\langle 2,2\rangle,\langle 2,3\rangle\}$，$R\circ S_2=\{\langle 2,3\rangle\}$，二者交为 $\{\langle 2,3\rangle\}\neq\varnothing$。

直观原因：$\langle 2,3\rangle$ 可以「经 $2$ 由 $S_1$ 到达」也可以「直接由 $S_2$ 到达」，两条路各自成立，但**没有同一个中间点同时被 $S_1$ 和 $S_2$ 覆盖**。

**复合与逆的关系**（定理 4.6）：$(R\circ S)^{-1}=S^{-1}\circ R^{-1}$，顺序要**倒过来**，就像穿脱衣服：先穿袜子再穿鞋，脱的时候先脱鞋。用上面的数据验算：由 $R\circ S=\{\langle 1,4\rangle,\langle 3,2\rangle\}$ 得 $(R\circ S)^{-1}=\{\langle 4,1\rangle,\langle 2,3\rangle\}$；而 $S^{-1}=\{\langle 4,2\rangle,\langle 4,3\rangle,\langle 2,4\rangle\}$，$R^{-1}=\{\langle 2,1\rangle,\langle 4,3\rangle\}$，先走 $S^{-1}$ 再走 $R^{-1}$：$\langle 4,2\rangle$ 接 $\langle 2,1\rangle$ 得 $\langle 4,1\rangle$；$\langle 2,4\rangle$ 接 $\langle 4,3\rangle$ 得 $\langle 2,3\rangle$，恰为 $\{\langle 4,1\rangle,\langle 2,3\rangle\}$，一致。

### 2.3 用布尔矩阵算复合

复合关系完全可以用矩阵算：把 $M_R$ 和 $M_S$ 做**布尔积**（把普通乘法的「乘」换成 $\wedge$、「加」换成 $\vee$）：

$$e_{ij}=\bigvee_{k=1}^{p}(a_{ik}\wedge b_{kj}),\qquad M_{R\circ S}=M_R\odot M_S$$

> 例：$A=\begin{pmatrix}1&0&1\\0&1&0\end{pmatrix}$，$C=\begin{pmatrix}0&1\\1&0\\1&1\end{pmatrix}$。
>
> $e_{11}=(1\wedge0)\vee(0\wedge1)\vee(1\wedge1)=1$；$e_{12}=(1\wedge1)\vee(0\wedge0)\vee(1\wedge1)=1$；
> $e_{21}=(0\wedge0)\vee(1\wedge1)\vee(0\wedge1)=1$；$e_{22}=(0\wedge1)\vee(1\wedge0)\vee(0\wedge1)=0$。
>
> 所以 $A\odot C=\begin{pmatrix}1&1\\1&0\end{pmatrix}$。注意 $e_{12}$ 若按普通乘法算是 $2$，布尔运算里「有就是 1」，仍是 $1$——这是布尔积与普通矩阵积唯一的区别。

布尔矩阵自身满足交换律（$\vee,\wedge$）、结合律、分配律（定理 4.3），但**布尔积不满足交换律**（尺寸常常都不匹配），这一点和普通矩阵一样。

### 2.4 关系的幂

当 $R$ 是 $A$ 上的关系时，可以自己和自己复合。**定义 4.10（关系的幂）** 设 $R:A\to A$，定义 $R^{0}=I_A$，$R^{1}=R$，$R^{n+1}=R^{n}\circ R=R\circ R^{n}$，并且 $R^{m}\circ R^{n}=R^{m+n}$，$(R^{m})^{n}=R^{mn}$。

**幂的含义**：$\langle x,y\rangle\in R^{n}$ 当且仅当存在一条从 $x$ 到 $y$、长度恰为 $n$ 的**路径**（$n$ 步都走 $R$ 的边）。所以 $R^{n}$ 数的是「恰好 $n$ 步能到哪」。

> 例：$A=\{1,2,3,4\}$，$R=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,4\rangle\}$，求 $R^{1}$ 到 $R^{4}$。
>
> $$R^{1}=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,4\rangle\},\quad R^{2}=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 1,3\rangle,\langle 2,4\rangle\},\quad R^{3}=R^{4}=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 1,3\rangle,\langle 1,4\rangle\}$$
>
> 逐步核对（按「两步走」理解）：$R^{2}$ 中 $\langle 1,1\rangle$ 来自 $1\to1\to1$，$\langle 1,2\rangle$ 来自 $1\to1\to2$，$\langle 1,3\rangle$ 来自 $1\to2\to3$，$\langle 2,4\rangle$ 来自 $2\to3\to4$；$R^{3}$ 中 $\langle 1,4\rangle$ 来自 $1\to2\to3\to4$，其余三个来自在自环处多绕一步。为什么 $R^{4}=R^{3}$？因为 $A$ 只有 $4$ 个元素，从 $1$ 出发的最长不重复路径是 $1\to2\to3\to4$（$3$ 条边），再走只能绕回已访问的结点，不会产生新终点。
>
> 再取 $S=\{\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,4\rangle\}$（去掉自环）：
>
> $$S^{2}=\{\langle 1,3\rangle,\langle 2,4\rangle\},\quad S^{3}=\{\langle 1,4\rangle\},\quad S^{4}=\varnothing,\quad S^{n}=\varnothing\ (n\ge4)$$
>
> 没有环就没有任意长的路径，$S^{4}$ 之后全空。

> **易错**：$R^{0}=I_A$ 而不是 $\varnothing$，也不是 $R$。很多人把 $R^{2}$ 直接写成 $\{\langle x,z\rangle\}$ 的两步走法却漏了自环贡献，比如上例中 $R^{2}$ 的 $\langle 1,1\rangle$ 就来自 $1\to1\to1$；**自环会让幂不缩小**。

**定理 4.8** 设 $\lvert A\rvert=n$，$R$ 是 $A$ 上的关系，则对任意 $k>n$，$R^{k}$ 都被前 $n$ 个幂「吸收」，即 $\bigcup_{k=1}^{\infty}R^{k}=\bigcup_{k=1}^{n}R^{k}$。证明用的是鸽笼原理：$\langle a,b\rangle\in R^{k}$ 说明有一条长度 $k$ 的路径，路径上有 $k+1$ 个结点，而 $A$ 只有 $n$ 个元素，所以必有两个结点相同（$a_i=a_j$），把中间那段「兜圈子」删掉，就得到一条更短的路径，反复删到长度不超过 $n$ 为止。

**这个定理的意义**：无穷并只需要算前 $n$ 项，这是后面求传递闭包能用有限步算完的根本原因。

---

## 三、关系的性质

### 3.1 前提：性质必须挂在「同一个集合」上

本节讨论的关系一律是 **$A$ 上的关系**（$A$ 非空），因为性质的定义里要用到同一个集合里的 $x,y,z$ 做比较。前域和后域不同的关系，谈不上这些性质。这一点经常被考到：$R=\{\langle 1,1\rangle,\langle 2,2\rangle\}$ 在 $A=\{1,2\}$ 上**是自反的**，同一个 $R$ 在 $B=\{1,2,3\}$ 上**不是自反的**（缺 $\langle 3,3\rangle$）。所以**绝对不能脱离基集谈性质**。

### 3.2 自反与反自反

**定义 4.11** 设 $R$ 是非空集合 $A$ 上的关系。

（1）若 $\forall x\,(x\in A \to \langle x,x\rangle\in R)=1$，称 $R$ 在 $A$ 上是**自反的**；

（2）若 $\forall x\,(x\in A \to \langle x,x\rangle\notin R)=1$，称 $R$ 在 $A$ 上是**反自反的**。大白话：**自反 = 每个人都跟自己有关系；反自反 = 每个人都不跟自己有关系**。同姓关系是自反的（自己跟自己同姓），父子关系是反自反的（没人是他自己的父亲）。

注意这两条**不是互补的**。$A=\{1,2,3\}$ 上：

| 关系 | 自反？ | 反自反？ | 原因 |
| --- | --- | --- | --- |
| $R_1=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,2\rangle,\langle 3,3\rangle\}$ | 是 | 否 | 三个自环齐全 |
| $R_2=\{\langle 1,1\rangle,\langle 2,3\rangle\}$ | 否 | 否 | 有 $\langle 1,1\rangle$ 但缺 $\langle 2,2\rangle,\langle 3,3\rangle$ |
| $R_3=\{\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,1\rangle\}$ | 否 | 是 | 一个自环都没有 |
| $R_4=I_A$ | 是 | 否 | 自环恰好三个 |

判断的三个判据（三者等价，挑最顺手的用）：

| 视角 | 自反 | 反自反 | 两者都不是 |
| --- | --- | --- | --- |
| 集合 | $\forall x\in A,\ \langle x,x\rangle\in R$ | $\forall x\in A,\ \langle x,x\rangle\notin R$ | 存在自环，也缺自环 |
| 矩阵 | 主对角线**全 1** | 主对角线**全 0** | 对角线上既有 1 又有 0 |
| 关系图 | 每个结点都有自环 | 每个结点都无自环 | 有自环的结点、无自环的结点并存 |

**定理 4.9（1）（2）** 用恒等关系可以写得非常简洁：$R$ 自反 $\iff I_A\subseteq R$，$R$ 反自反 $\iff R\cap I_A=\varnothing$。

> **易错**：「不是自反」推不出「反自反」，「不是反自反」也推不出「自反」。$R_2$ 两个都不是，就是反例。这是选择题最爱设的坑。

**计数题**：$\lvert A\rvert=n$，则 $A$ 上的关系共 $2^{n^{2}}$ 个；反自反的有 $2^{n^{2}-n}$ 个（对角线必须全 0，其余 $n^{2}-n$ 格随意）；自反的也是 $2^{n^{2}-n}$ 个（对角线必须全 1）。所以既不自反也不反自反的有

$$2^{n^{2}}-2\cdot 2^{n^{2}-n}$$

个（$n=3$ 时是 $512-2\cdot 64=384$ 个）。

### 3.3 对称与反对称

**定义 4.12** 设 $R$ 是非空集合 $A$ 上的关系。

（1）若 $\forall x\forall y\,(x\in A\wedge y\in A\wedge \langle x,y\rangle\in R \to \langle y,x\rangle\in R)=1$，称 $R$ 是**对称的**；

（2）若 $\forall x\forall y\,(x\in A\wedge y\in A\wedge(\langle x,y\rangle\in R\wedge\langle y,x\rangle\in R)\to x=y)=1$，称 $R$ 是**反对称的**。大白话：**对称 = 有来必有回，且可以是不同的人**（同姓关系：$a$ 与 $b$ 同姓则 $b$ 与 $a$ 同姓）；**反对称 = 一旦互有往来，那只能是同一个人**（父子关系、$\le$、$\subseteq$：$a\le b$ 且 $b\le a$ 只能 $a=b$）。

> **易错**：反对称**不是**「不对称」。$\le$ 是反对称的，但它允许 $a\le a$；恒等关系 $I_A$ 既是对称的又是反对称的。名字里的「反对称」只表示「双向边只能出现在自环上」，完全没有「处处不对称」的意思。

$A=\{1,2,3,4\}$ 上的四个例子：

| 关系 | 对称？ | 反对称？ | 判定依据 |
| --- | --- | --- | --- |
| $R=\{\langle 1,1\rangle,\langle 1,3\rangle,\langle 3,1\rangle\}$ | 是 | 否 | $\langle 1,3\rangle,\langle 3,1\rangle$ 成对出现但 $1\neq3$ |
| $S=\{\langle 1,1\rangle,\langle 2,3\rangle,\langle 2,4\rangle\}$ | 否 | 是 | 没有成对的反向边 |
| $T=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 1,3\rangle,\langle 3,1\rangle\}$ | 否 | 否 | 有 $\langle 1,2\rangle$ 无 $\langle 2,1\rangle$；有 $\langle 1,3\rangle,\langle 3,1\rangle$ 但 $1\neq3$ |
| $V=I_A$ | 是 | 是 | 只有自环，两个条件都满足 |

判断三视角：

| 视角 | 对称 | 反对称 |
| --- | --- | --- |
| 集合 | $\langle x,y\rangle\in R \Rightarrow \langle y,x\rangle\in R$ | $\langle x,y\rangle,\langle y,x\rangle\in R \Rightarrow x=y$ |
| 矩阵 | $M_R=M_R^{T}$（关于主对角线对称） | 任取 $i\neq j$，$m_{ij}$ 与 $m_{ji}$ **不同时为 1** |
| 关系图 | 任两结点间要么有两条反向边，要么无边 | 任两结点间**至多一条**边 |
| 逆运算 | $R=R^{-1}$ | $R\cap R^{-1}\subseteq I_A$ |

最后一行就是定理 4.9 的（3）（4），写成公式最省事：$R$ 对称 $\iff R=R^{-1}$，$R$ 反对称 $\iff R\cap R^{-1}\subseteq I_A$。

> 例：判断 $A=\{1,2,3,4\}$ 上 $R=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,1\rangle,\langle 3,4\rangle\}$ 的性质。
>
> - 自反？缺 $\langle 2,2\rangle,\langle 3,3\rangle,\langle 4,4\rangle$，**否**；
> - 反自反？有 $\langle 1,1\rangle$，**否**；
> - 对称？有 $\langle 3,4\rangle$ 却无 $\langle 4,3\rangle$，**否**；
> - 反对称？有 $\langle 1,2\rangle,\langle 2,1\rangle$ 而 $1\neq2$，**否**；
> - 传递？$\langle 2,1\rangle,\langle 1,2\rangle\in R$ 要求 $\langle 2,2\rangle\in R$，但不在，**否**。
>
> 结论：五条性质**一条都不具备**。这种「全不具备」的题很常见，逐条找反例即可，不需要证明成立。

### 3.4 传递性

**定义 4.13** 若 $\forall x\forall y\forall z\,(x,y,z\in A \wedge \langle x,y\rangle\in R \wedge \langle y,z\rangle\in R \to \langle x,z\rangle\in R)=1$，称 $R$ 是**传递的**。

大白话：**能连成两段就能连成一段**。同姓关系传递（$a$ 与 $b$ 同姓、$b$ 与 $c$ 同姓，则 $a$ 与 $c$ 同姓）；父子关系不传递（我父亲和我儿子之间不是父子）。

$A=\{1,2,3\}$ 上的例子：

| 关系 | 传递？ | 说明 |
| --- | --- | --- |
| $R=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 1,3\rangle,\langle 2,3\rangle\}$ | 是 | 所有两段链的缺口都已补齐 |
| $S=\{\langle 1,2\rangle\}$ | 是 | 压根没有两段可接的链，条件真值为真 |
| $T=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,3\rangle\}$ | 否 | 有 $\langle 1,2\rangle,\langle 2,3\rangle$ 但缺 $\langle 1,3\rangle$ |
| $V=\{\langle 1,2\rangle,\langle 2,3\rangle,\langle 1,3\rangle,\langle 2,1\rangle\}$ | 否 | 有 $\langle 2,1\rangle,\langle 1,2\rangle$ 但缺 $\langle 2,2\rangle$ |

> **易错**：传递性要求对**所有**三元组成立。只要找到**一个**反例三元组就足以判定「不传递」；但要判定「传递」则必须确认没有任何反例（或者用定理 $R\circ R\subseteq R$）。上面 $V$ 容易被误判为传递——只盯着 $1\to2\to3$ 这一条链，忘了 $2\to1\to2$ 也要补 $\langle 2,2\rangle$。

三个判据：

| 视角 | 传递的充要条件 |
| --- | --- |
| 集合 | 若 $\langle x,y\rangle,\langle y,z\rangle\in R$ 则 $\langle x,z\rangle\in R$ |
| 矩阵 | 对所有 $i,j,k$：$m_{ij}\wedge m_{jk}=1 \Rightarrow m_{ik}=1$ |
| 关系图 | 任两不同结点间若存在路径，则必存在直接边（一步可达） |
| 复合 | $R\circ R\subseteq R$（定理 4.9 之（5）） |

矩阵判据的实操办法：把 $M_R\odot M_R$ 与 $M_R$ 逐格比较，**布尔积中凡是 1 而 $M_R$ 中为 0 的格子，都是「缺边」**，只要有一个「缺边」就不传递。这等于在算 $R^{2}\subseteq R$。

### 3.5 判断流程与结论速查

拿到一个关系判性质，照这个顺序走，最省时间：

```text
1. 先确认基集 A（性质只相对于 A 而言）
2. 看对角线：全 1 → 自反；全 0 → 反自反；混合 → 两个都不是
3. 看反对称性：找有没有 i≠j 使 m_ij = m_ji = 1（有则不反对称）
   （注意「不反对称」不等于「对称」）
4. 看对称性：找有没有 m_ij = 1 而 m_ji = 0（有则不对称）
5. 看传递性：算 M⊙M，检查是否有 1 落到原矩阵的 0 上
6. 若题目要求「证明」，改用定义：任取元素，由前提推结论
```

**三个特殊关系的性质，必须背下来**：

| 关系 | 自反 | 反自反 | 对称 | 反对称 | 传递 |
| --- | --- | --- | --- | --- | --- |
| 空关系 $\varnothing$ | 否 | 是 | 是 | 是 | 是 |
| 全域关系 $E_A$ | 是 | 否 | 是 | 否（$n\ge2$） | 是 |
| 恒等关系 $I_A$ | 是 | 否 | 是 | 是 | 是 |

这份表怎么记：空关系「什么都没有」，所以凡是「不需要存在」的性质（反自反、对称、反对称、传递）它都满足，凡是「要求所有元素都有」的性质（自反）它都不满足。恒等关系是「最小」的自反关系，也是最典型的既对称又反对称的例子。

> 例（证明题模板）：证明 $A$ 上的恒等关系 $I_A$ 具有自反性、对称性、反对称性、传递性。由 $I_A=\{\langle x,x\rangle \mid x\in A\}$。
>
> （1）对任意 $x\in A$，$\langle x,x\rangle\in I_A$，故自反；
> （2）若 $\langle x,y\rangle\in I_A$，则 $x=y$，于是 $\langle y,x\rangle=\langle x,x\rangle\in I_A$，故对称；
> （3）若 $\langle x,y\rangle\in I_A$ 且 $\langle y,x\rangle\in I_A$，由定义直接得 $x=y$，故反对称；
> （4）若 $\langle x,y\rangle\in I_A$ 且 $\langle y,z\rangle\in I_A$，则 $x=y$ 且 $y=z$，故 $x=z$，$\langle x,z\rangle=\langle x,x\rangle\in I_A$，故传递。四问都用「由定义得 $x=y$」这一招，因为 $I_A$ 里的序偶两个位置必然相同。

### 3.6 性质的保守性：运算之后还保得住吗

把两个「反自反」的关系复合，结果还反自反吗？这就是**保守性**问题。

反例：$A=\{1,2,3\}$，$R=\{\langle 1,3\rangle,\langle 2,3\rangle\}$，$S=\{\langle 2,3\rangle,\langle 3,1\rangle\}$。两者显然都反自反。但

- $R-S=\{\langle 1,3\rangle\}$，仍然反自反；
- $R\circ S=\{\langle 1,1\rangle,\langle 2,1\rangle\}$，出现了 $\langle 1,1\rangle$，**不再反自反**。

原因是 $1\to3\to1$ 绕回来了，复合运算会产生自环和双向边。

**定理 4.10（保守性）** 设 $R,S$ 是 $A$ 上的二元关系，则

| 若 $R,S$ 都具有 | 则下列运算结果一定保持该性质 | 不一定保持 |
| --- | --- | --- |
| 自反性 | $R^{-1}$、$R\cup S$、$R\cap S$、$R\circ S$ | — |
| 反自反性 | $R^{-1}$、$R\cup S$、$R\cap S$ | $R-S$、$R\circ S$ |
| 对称性 | $R^{-1}$、$R\cup S$、$R\cap S$ | $R-S$、$R\circ S$ |
| 反对称性 | $R^{-1}$、$R\cap S$ | $R\cup S$、$R-S$、$R\circ S$ |
| 传递性 | $R^{-1}$、$R\cap S$ | $R\cup S$、$R-S$、$R\circ S$ |

一句话总结：**逆运算和交运算最老实，并、差、复合最容易翻车**。

> **易错**：反自反性连复合都保不住，但**自反性保得住复合**（$R,S$ 自反则 $R\circ S$ 也自反，因为 $\langle x,x\rangle\in R$ 和 $\langle x,x\rangle\in S$ 直接接成 $\langle x,x\rangle\in R\circ S$）。这个不对称容易记反。

两个经典反例值得单独记（都取 $A=\{1,2,3\}$）：

1. **传递 + 传递，并起来不传递**：$R=\{\langle 1,2\rangle\}$，$S=\{\langle 2,3\rangle\}$ 都传递，但 $R\cup S$ 缺 $\langle 1,3\rangle$。2. **对称 + 对称，复合不对称**：$R=\{\langle 1,2\rangle,\langle 2,1\rangle\}$，$S=\{\langle 2,3\rangle,\langle 3,2\rangle\}$ 都对称，而 $R\circ S=\{\langle 1,3\rangle\}$ 不对称。举反例的通用套路：先想好**要得到什么坏结果**，再反推 $R,S$ 该长什么样，最后把基集补到能容下这些元素的最小规模。

---

## 四、关系的闭包

### 4.1 闭包就是「补最少的边，让它满足性质」

有些关系天生缺一点东西。$A=\{1,2,3\}$ 上 $R=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,1\rangle\}$ 不是自反的（缺 $\langle 2,2\rangle,\langle 3,3\rangle$），也不是传递的（$\langle 2,1\rangle,\langle 1,2\rangle$ 要求 $\langle 2,2\rangle$，也不满足）。想要它满足，就往里塞序偶。可塞法很多，我们只想要**塞得最少**的那一个。

**定义 4.14（闭包）** 设 $R$ 是 $A$ 上的关系，若存在 $A$ 上的关系 $R'$ 满足：

（1）$R\subseteq R'$，且 $R'$ 是自反的（对称的、传递的）；

（2）对任何自反的（对称的、传递的）关系 $R''$，若 $R\subseteq R''$，则 $R'\subseteq R''$；

则称 $R'$ 为 $R$ 的**自反闭包**（对称闭包、传递闭包），分别记作 $r(R)$、$s(R)$、$t(R)$。条件（1）说「不能把原有的边弄丢」，条件（2）说「新加的边已经不能再少」——**最小性**就是这句话。所以闭包是「包含 $R$ 且具有该性质的**最小**关系」，它唯一存在。

求闭包的两步操作：先看 $R$ 本身是否已经有该性质，有就直接取 $R$；没有就**只补必需的**元素。

> 例：$A=\{1,2\}$，$R=\{\langle 1,1\rangle,\langle 1,2\rangle\}$。
>
> - $R$ 不是自反的（缺 $\langle 2,2\rangle$），补上后 $r(R)=\{\langle 1,1\rangle,\langle 2,2\rangle,\langle 1,2\rangle\}$；
> - $R$ 不是对称的（有 $\langle 1,2\rangle$ 无 $\langle 2,1\rangle$），补上后 $s(R)=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,1\rangle\}$；
> - $R$ 是传递的（没有可接的两段链），所以 $t(R)=R$。

注意 $r(R)$ 里**不能再多**加 $\langle 2,1\rangle$——加了也自反，但不是最小的。这就是闭包与「任取一个满足性质的关系」的区别。

### 4.2 三个闭包的计算公式

**定理 4.11（闭包公式）** 设 $R$ 是 $A$ 上的二元关系，则

$$r(R)=R\cup I_A,\qquad s(R)=R\cup R^{-1},\qquad t(R)=R\cup R^{2}\cup\cdots\cup R^{n}=\bigcup_{i=1}^{n}R^{i}\quad(\lvert A\rvert=n)$$

三个公式各自在说什么，必须讲清楚，否则背下来也不会用：

| 闭包 | 公式 | 一句话解释 |
| --- | --- | --- |
| $r(R)$ | $R\cup I_A$ | 把缺的自环全补上，已有的一个不动 |
| $s(R)$ | $R\cup R^{-1}$ | 把每条单向边补一条反向边（双向边不重复） |
| $t(R)$ | $R\cup R^{2}\cup\cdots\cup R^{n}$ | 把「能走两步、三步……的起点终点」全部连成一条边 |

传递闭包的公式是**路径解释**：$\langle x,y\rangle\in R^{i}$ 表示存在一条从 $x$ 到 $y$、长 $i$ 的路径。所以 $t(R)$ 就是「只要**有路径**（不论多长）就从 $x$ 直接连到 $y$」的关系。而由定理 4.8，$n$ 步以上的路径都可以截短到不超过 $n$ 步，所以并到 $R^{n}$ 就够，不必无穷并。

> **易错**：$t(R)$ 的并里包含 $R$ 本身（$i=1$），所以原有的边不会丢。另外**不能**只并到 $R^{n-1}$ 就停：$A=\{1,2,3,4\}$（$n=4$）上 $\{\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,4\rangle\}$ 的 $R^{3}=\{\langle 1,4\rangle\}$ 就是新东西，直接并到 $R^{n}$ 最保险。

### 4.3 用关系图求闭包

闭包最直观的求法是画图：

1. **$r(R)$**：在没有自环的结点处**加自环**；
2. **$s(R)$**：把每条单向边改成**双向边**；
3. **$t(R)$**：对每个结点，找出它出发的**所有**可达结点（无论几步），把缺失的边补上。

> 例（$A=\{a,b,c,d\}$，$R=\{\langle a,b\rangle,\langle b,a\rangle,\langle b,c\rangle,\langle c,d\rangle\}$）
>
> $$r(R)=R\cup I_A=\{\langle a,b\rangle,\langle b,a\rangle,\langle b,c\rangle,\langle c,d\rangle,\langle a,a\rangle,\langle b,b\rangle,\langle c,c\rangle,\langle d,d\rangle\},\quad s(R)=R\cup R^{-1}=\{\langle a,b\rangle,\langle b,a\rangle,\langle b,c\rangle,\langle c,b\rangle,\langle c,d\rangle,\langle d,c\rangle\}$$
>
> $t(R)$ 需要算幂：$R^{2}=\{\langle a,a\rangle,\langle a,c\rangle,\langle b,b\rangle,\langle b,d\rangle\}$，$R^{3}=\{\langle a,b\rangle,\langle a,d\rangle,\langle b,a\rangle,\langle b,c\rangle\}$，$R^{4}=R^{2}$。因为 $n=4$，并到 $R^{4}$ 即可（其实 $R^{2},R^{3}$ 就闭合了）：$R^{2}=\{\langle a,a\rangle,\langle a,c\rangle,\langle b,b\rangle,\langle b,d\rangle\}$，$R^{3}=\{\langle a,b\rangle,\langle a,d\rangle,\langle b,a\rangle,\langle b,c\rangle\}$，$R^{4}=R^{2}$。因为 $n=4$，并到 $R^{4}$ 即可（其实 $R^{2},R^{3}$ 就闭合了）：
>
> $$t(R)=\{\langle a,a\rangle,\langle a,b\rangle,\langle a,c\rangle,\langle a,d\rangle,\langle b,a\rangle,\langle b,b\rangle,\langle b,c\rangle,\langle b,d\rangle,\langle c,d\rangle\}$$
>
> 校验：从 $a$ 出发可到 $a,b,c,d$ 四个点；从 $b$ 出发可回到 $a$，故 $b$ 也能到全部；从 $c$ 出发只到 $d$；$d$ 出不去。与结果中「$a,b$ 行全 1，$c$ 行只有 $d$，$d$ 行全 0」完全一致。

### 4.4 Warshall 算法：机械地算传递闭包

当结点多起来，算幂会报，于是用 **Warshall 算法**。它的想法只有一句：**逐个把结点当作「中转站」，看能不能借助它接通别的边**。

```text
输入：A = {1,...,n} 上的关系 R 的矩阵 M
输出：t(R) 的矩阵 M
for k = 1 to n:
    for i = 1 to n:
        if M[i][k] == 1:
            for j = 1 to n:
                M[i][j] = M[i][j] ∨ M[k][j]      // 第 i 行整体与第 k 行做布尔并
```

手算时可以偷懒：**第 $k$ 轮只看「第 $k$ 列中有 1 的行」，把这些行分别与第 $k$ 行做布尔并**，其它行不动。

用上一个小节的关系 $R=\{\langle a,b\rangle,\langle b,a\rangle,\langle b,c\rangle,\langle c,d\rangle\}$，把 $a,b,c,d$ 记为 $1,2,3,4$，初始矩阵与逐轮演算如下（矩阵按行写出，行号对应 $1,2,3,4$）：

$$M^{(0)}=\begin{pmatrix}0&1&0&0\\1&0&1&0\\0&0&0&1\\0&0&0&0\end{pmatrix}$$

| 轮次 $k$ | 第 $k$ 列中为 1 的行 $i$ | 执行的行变换（布尔并） | 本轮新增的序偶 | 本轮结束后的矩阵 |
| --- | --- | --- | --- | --- |
| $k=1$ | $i=2$ | $r_2\leftarrow r_2\vee r_1=(1,0,1,0)\vee(0,1,0,0)=(1,1,1,0)$ | $\langle 2,2\rangle$ | 第 1 行 $(0,1,0,0)$；第 2 行 $(1,1,1,0)$；第 3、4 行不变 |
| $k=2$ | $i=1,\ i=2$ | $r_1\leftarrow(0,1,0,0)\vee(1,1,1,0)=(1,1,1,0)$；$r_2$ 自并仍为 $(1,1,1,0)$ | $\langle 1,1\rangle,\langle 1,3\rangle$ | 第 1、2 行均为 $(1,1,1,0)$；第 3 行 $(0,0,0,1)$；第 4 行 $(0,0,0,0)$ |
| $k=3$ | $i=1,\ i=2$ | $r_1\leftarrow(1,1,1,0)\vee(0,0,0,1)=(1,1,1,1)$；$r_2$ 同样得 $(1,1,1,1)$ | $\langle 1,4\rangle,\langle 2,4\rangle$ | 第 1、2 行 $(1,1,1,1)$；第 3 行 $(0,0,0,1)$；第 4 行 $(0,0,0,0)$ |
| $k=4$ | $i=1,2,3$（但第 4 行为全 0） | $r_3\leftarrow(0,0,0,1)\vee(0,0,0,0)=(0,0,0,1)$，其余行同理无变化 | 无 | 同上行 |

最终 $M^{(4)}=\begin{pmatrix}1&1&1&1\\1&1&1&1\\0&0&0&1\\0&0&0&0\end{pmatrix}$，矩阵中共 $9$ 个 1，与前面算出的 $t(R)$ 的 $9$ 个序偶一一对应（$\langle 1,1\rangle\to\langle a,a\rangle$ 等等）。复杂度 $O(n^{3})$，比算幂的矩阵乘法便宜。

> **易错**：Warshall 里第 $k$ 轮的「中转」是**可以重复使用前几轮已经打通的中转**的（因为 $M$ 是边算边改的）。所以 $k$ 必须从 $1$ 到 $n$ **按顺序跑完**，不能挑着用。手算时若只做一遍 $k=1..n$ 但中途重算矩阵，容易把上一轮的结果覆盖掉——就按上表一行一行改，每轮只改变少数几行。

---

## 五、关系的应用

### 5.1 关系数据库：一张表就是一个关系

关系数据库的全部理论基础就是本章这一句：**一张表 = 一个关系**。

- 表的**列**是属性，相当于 $n$ 元关系的「第几个位置」；
- 表的**行**是一条记录，相当于一个 $n$ 重有序组 $\langle a_1,\dots,a_n\rangle$；
- 表名是关系名，全体行是那个关系的序偶（元组）集合。

| 学号 | 姓名 | 课程 | 成绩 |
| --- | --- | --- | --- |
| $A_1$ | $B_1$ | $C_1$ | $D_2$ |
| $A_2$ | $B_2$ | $C_2$ | $D_1$ |

这个表就是 $A\times B\times C\times D$ 的子集，也就是一个 $4$ 元关系，数据库里的基本操作恰好对上了关系的运算：

| 数据库操作 | 对应的关系运算 |
| --- | --- |
| 插入 / 删除行 | 并（$R\cup S$）/ 差（$R-S$） |
| 两表取公共元组 | 交（$R\cap S$） |
| 多表按公共属性拼接 | 复合（$R\circ S$） |
| 相对全集的补充 | 补（$R^{c}$，相对于 $A\times B\times\cdots$） |

选课关系 $R$（学生 → 课程）与课程先修关系 $S$（课程 → 课程）一复合，得到的就是「这个学生间接满足的先修条件」——这就是复合在数据库里的实际用途。用两个关系解释集合运算的意义（$A$ 为晚上各时刻，$B$ 为频道）：若 $R_1$ 是音乐节目表、$R_2$ 是戏曲节目表，则 $R_1\cup R_2$ 是「音乐或戏曲」的播出表，$R_1\cap R_2$ 是「同时播音乐和戏曲」的表（通常为空），$R_1-R_2$ 是「播音乐但不播戏曲」的表。

### 5.2 可达性判断：矩阵做到底

图论里的「$i$ 能否到 $j$」本质上就是关系的**传递闭包**：把图的邻接矩阵当关系矩阵，求 $t(R)$，得到的矩阵叫**可达性矩阵**。其中：

- $M_{R^{k}}$ 中第 $(i,j)$ 格为 $m$，表示从 $i$ 到 $j$ **长度恰为 $k$ 的路径有 $m$ 条**（用普通矩阵乘法时数条数，用布尔乘法时只记「有没有」）；
- 把 $M^{(4)}$ 这类结果里对角线改为 1（或本就含自环）后，逐行读 1 的位置就是「从该点能到达的所有点」。

> 例：矩阵 $A=\begin{pmatrix}0&1&0\\0&0&1\\1&0&0\end{pmatrix}$ 表示 $1\to2\to3\to1$ 的三边环。用普通矩阵乘法算 $A^{2}=\begin{pmatrix}0&0&1\\1&0&0\\0&1&0\end{pmatrix}$，$A$ 中有 $3$ 个 1，说明长度 $2$ 的路径有 $3$ 条（$1\to2\to3$、$2\to3\to1$、$3\to1\to2$）；算 $A\odot(A\vee A^{2}\vee A^{3})$ 得全 1 矩阵，即每个点都能到达所有点（环上可以无限绕）。

所以判断可达性的通用流程：**邻接矩阵 → 布尔幂并起来（或跑 Warshall） → 读 1 的位置**。这也是网络路由、程序调用图、状态机可达性分析的共同做法。

---

## 本章小结

| 概念 | 一句话 | 常考形式 |
| --- | --- | --- |
| 序偶 | 有顺序的一对数，$\langle a,b\rangle=\langle c,d\rangle\iff a=c\wedge b=d$ | 解方程组求 $x,y$ |
| 笛卡尔积 | 两两配对得到的序偶集合 | 求元素、求 $\lvert A\times B\rvert$、判交换/结合律 |
| 二元关系 | $A\times B$ 的子集 | 判断给定集合是否是关系 |
| 关系个数 | $2^{\lvert A\rvert\lvert B\rvert}$ | 填空题 |
| $\mathrm{dom}/\mathrm{ran}/\mathrm{fld}$ | 第一元素集、第二元素集、两者之并 | 由集合或图读出 |
| 关系矩阵 / 关系图 | 0-1 矩阵 / 有向图 | 三表示互换 |
| 逆 $R^{-1}$ | 序偶对调，矩阵转置，图反边 | 求逆、用 $R=R^{-1}$ 判对称 |
| 复合 $R\circ S$ | 先走 $R$ 再走 $S$；布尔积 | 具体计算、（不）交换 |
| 幂 $R^{n}$ | $n$ 步路径；$R^{0}=I_A$ | 逐步算到稳定 |
| 五条性质 | 自反、反自反、对称、反对称、传递 | 判定 + 证明 + 计数 |
| 闭包 | 补最少边使其满足性质 | $r,s,t$ 的计算 |
| Warshall | 逐个结点当中转，算传递闭包 | 给矩阵演算 |

**必须背下来的结论**

1. $R$ 自反 $\iff I_A\subseteq R$；反自反 $\iff R\cap I_A=\varnothing$；对称 $\iff R=R^{-1}$；反对称 $\iff R\cap R^{-1}\subseteq I_A$；传递 $\iff R\circ R\subseteq R$。
2. $(R\circ S)^{-1}=S^{-1}\circ R^{-1}$，顺序必须颠倒；复合满足结合律但**不**满足交换律；对并满足分配律，对交只有包含关系。
3. $r(R)=R\cup I_A$，$s(R)=R\cup R^{-1}$，$t(R)=R\cup R^{2}\cup\cdots\cup R^{n}$（$\lvert A\rvert=n$）。
4. 逆运算和交运算保持所有五条性质；并、差、复合**不保证**保持（尤其反自反与反对称）。
5. 性质离不开基集：同一个关系换个基集，性质可能完全变样。

---

## 自测题

**1.** 设 $A=\{1,2\}$，$B=\{a,b,c\}$。（1）求 $A\times B$ 与 $\lvert A\times B\rvert$。（2）判断 $S_1=\{\langle 1,a\rangle,\langle 2,b\rangle\}$、$S_2=\{\langle 1,c\rangle,\langle 3,a\rangle\}$ 是否为 $A$ 到 $B$ 的关系。（3）从 $A$ 到 $B$ 共有多少个不同的关系？

**2.** 设 $A=\{1,2,3,4\}$，$R=\{\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,4\rangle\}$，$S=\{\langle 1,3\rangle,\langle 4,1\rangle\}$。计算 $R\circ S$、$S\circ R$、$(R\circ S)^{-1}$，并说明复合是否满足交换律。

**3.** 设 $A=\{1,2,3,4\}$ 上的关系 $R=\{\langle 1,2\rangle,\langle 2,1\rangle,\langle 2,3\rangle,\langle 3,4\rangle\}$。写出 $R$ 的关系矩阵、$\mathrm{dom}\,R$、$\mathrm{ran}\,R$、$\mathrm{fld}\,R$，并求 $R^{2}$。

**4.** 判断 $A=\{1,2,3\}$ 上下列关系各具有哪些性质（自反、反自反、对称、反对称、传递）：（1）$R_1=\{\langle 1,1\rangle,\langle 1,2\rangle,\langle 2,1\rangle\}$；（2）$R_2=\{\langle 1,2\rangle,\langle 2,3\rangle\}$；（3）$R_3=I_A\cup\{\langle 1,2\rangle\}$。

**5.** 证明：$R$ 在 $A$ 上是对称的 $\iff R=R^{-1}$。

**6.** 设 $A=\{1,2,3,4\}$，$R=\{\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,4\rangle\}$。求 $r(R)$、$s(R)$、$t(R)$，并用 Warshall 算法验证 $t(R)$。

**7.** 设 $A=\{a,b,c\}$，$R=\{\langle a,b\rangle\}$，$S=\{\langle b,c\rangle\}$，二者都在 $A$ 上且都传递。试判断 $R\cup S$ 是否传递，并说明「传递性对并不保守」。

**8.** 设 $\lvert A\rvert=n$，求 $A$ 上既不自反也不反自反的关系个数，并在 $n=2$ 时验证结果。

::: details 参考答案

**1.**（1）$A\times B=\{\langle 1,a\rangle,\langle 1,b\rangle,\langle 1,c\rangle,\langle 2,a\rangle,\langle 2,b\rangle,\langle 2,c\rangle\}$，共 $2\times3=6$ 个元素。（2）$S_1$ 的每个序偶第一元素在 $A$、第二元素在 $B$，是子集，故**是**关系；$S_2$ 含 $3\notin A$，不是 $A\times B$ 的子集，故**不是**。（3）$2^{\lvert A\rvert\lvert B\rvert}=2^{6}=64$ 个。

**2.** 复合按「先走前一个、再走后一个」：

- $R\circ S$：$R$ 中 $\langle 1,2\rangle$ 在 $S$ 里找不到以 $2$ 开头的；$\langle 2,3\rangle$ 找不到以 $3$ 开头的；$\langle 3,4\rangle$ 接 $\langle 4,1\rangle$ 得 $\langle 3,1\rangle$。故 $R\circ S=\{\langle 3,1\rangle\}$。
- $S\circ R$：$\langle 1,3\rangle$ 接 $R$ 中 $\langle 3,4\rangle$ 得 $\langle 1,4\rangle$；$\langle 4,1\rangle$ 接 $\langle 1,2\rangle$ 得 $\langle 4,2\rangle$。故 $S\circ R=\{\langle 1,4\rangle,\langle 4,2\rangle\}$。
- $(R\circ S)^{-1}=\{\langle 1,3\rangle\}$。用公式校验：$R^{-1}=\{\langle 2,1\rangle,\langle 3,2\rangle,\langle 4,3\rangle\}$，$S^{-1}=\{\langle 3,1\rangle,\langle 1,4\rangle\}$，按 $(R\circ S)^{-1}=S^{-1}\circ R^{-1}$ 先走 $S^{-1}$ 再走 $R^{-1}$：$\langle 3,1\rangle$ 要接以 $1$ 开头的序偶，$R^{-1}$ 中没有，丢弃；$\langle 1,4\rangle$ 接 $\langle 4,3\rangle$ 得 $\langle 1,3\rangle$。故 $S^{-1}\circ R^{-1}=\{\langle 1,3\rangle\}$，与直接对调的结果一致。
- $R\circ S=\{\langle 3,1\rangle\}$，$S\circ R=\{\langle 1,4\rangle,\langle 4,2\rangle\}$，两者不同，**复合不满足交换律**。

**3.** 按 $1,2,3,4$ 排序（各行 1 分别在列 $2$、列 $1,3$、列 $4$、无），得 $M_R=\begin{pmatrix}0&1&0&0\\1&0&1&0\\0&0&0&1\\0&0&0&0\end{pmatrix}$。

$\mathrm{dom}\,R=\{1,2,3\}$（$4$ 没当过第一元素），$\mathrm{ran}\,R=\{1,2,3,4\}$，$\mathrm{fld}\,R=\{1,2,3,4\}$。$R^{2}$ 取所有两步路径：$1\to2\to1$ 得 $\langle 1,1\rangle$，$1\to2\to3$ 得 $\langle 1,3\rangle$，$2\to1\to2$ 得 $\langle 2,2\rangle$，$2\to3\to4$ 得 $\langle 2,4\rangle$，故 $R^{2}=\{\langle 1,1\rangle,\langle 1,3\rangle,\langle 2,2\rangle,\langle 2,4\rangle\}$。

**4.**（1）$R_1$：缺 $\langle 2,2\rangle,\langle 3,3\rangle$ 故非自反；有 $\langle 1,1\rangle$ 故非反自反；有 $\langle 1,2\rangle,\langle 2,1\rangle$ 故对称；因存在 $1\neq2$ 的双向边故非反对称；$\langle 2,1\rangle,\langle 1,2\rangle$ 要求 $\langle 2,2\rangle$ 但缺，故非传递。结论：**只有对称性**。

（2）$R_2$：无自环，**反自反**；无反向边对，**反对称**；没有两段可接的链（$2\to3$ 之后无出边），**传递**；缺 $\langle 1,1\rangle$ 等，非自反；有 $\langle 1,2\rangle$ 无 $\langle 2,1\rangle$，非对称。结论：**反自反、反对称、传递**。（3）$R_3=\{\langle 1,1\rangle,\langle 2,2\rangle,\langle 3,3\rangle,\langle 1,2\rangle\}$：自环齐全，**自反**；有自环故非反自反；无 $\langle 2,1\rangle$ 故非对称；无 $i\neq j$ 的双向边，**反对称**；可接的链只有 $\langle 1,1\rangle\langle 1,2\rangle\to\langle 1,2\rangle$ 与 $\langle 1,2\rangle\langle 2,2\rangle\to\langle 1,2\rangle$ 这类，结果都在 $R_3$ 中，**传递**。结论：**自反、反对称、传递**。

**5.** 证明互相包含。（$\Rightarrow$）设 $R$ 对称。任取 $\langle x,y\rangle\in R$，由对称性得 $\langle y,x\rangle\in R$，按逆关系定义即 $\langle x,y\rangle\in R^{-1}$，故 $R\subseteq R^{-1}$；反过来任取 $\langle x,y\rangle\in R^{-1}$，则 $\langle y,x\rangle\in R$，再由对称性 $\langle x,y\rangle\in R$，故 $R^{-1}\subseteq R$。于是 $R=R^{-1}$。（$\Leftarrow$）设 $R=R^{-1}$。任取 $\langle x,y\rangle\in R$，则 $\langle x,y\rangle\in R^{-1}$，即 $\langle y,x\rangle\in R$，故 $R$ 对称。

**6.** $r(R)=R\cup I_A=\{\langle 1,1\rangle,\langle 2,2\rangle,\langle 3,3\rangle,\langle 4,4\rangle,\langle 1,2\rangle,\langle 2,3\rangle,\langle 3,4\rangle\}$；$s(R)=R\cup R^{-1}=\{\langle 1,2\rangle,\langle 2,1\rangle,\langle 2,3\rangle,\langle 3,2\rangle,\langle 3,4\rangle,\langle 4,3\rangle\}$。

$t(R)$：$R^{2}=\{\langle 1,3\rangle,\langle 2,4\rangle\}$，$R^{3}=\{\langle 1,4\rangle\}$，$R^{4}=\varnothing$，故 $t(R)=\{\langle 1,2\rangle,\langle 1,3\rangle,\langle 1,4\rangle,\langle 2,3\rangle,\langle 2,4\rangle,\langle 3,4\rangle\}$（$6$ 个序偶）。

Warshall 验证：$M^{(0)}$ 第 1 行 $(0,1,0,0)$、第 2 行 $(0,0,1,0)$、第 3 行 $(0,0,0,1)$、第 4 行 $(0,0,0,0)$。$k=1$：第 1 列无 1，不变。$k=2$：第 2 列第 1 行为 1，$r_1\leftarrow r_1\vee r_2=(0,1,0,0)\vee(0,0,1,0)=(0,1,1,0)$，新增 $\langle 1,3\rangle$。$k=3$：第 3 列中 $i=1,2$ 为 1，$r_1\vee r_3=(0,1,1,0)\vee(0,0,0,1)=(0,1,1,1)$，$r_2\vee r_3=(0,0,1,0)\vee(0,0,0,1)=(0,0,1,1)$，新增 $\langle 1,4\rangle,\langle 2,4\rangle$。$k=4$：第 4 行全 0，无变化。得 $6$ 个 1，与上面结果一致。

**7.** $R\cup S=\{\langle a,b\rangle,\langle b,c\rangle\}$。它有可接的链 $\langle a,b\rangle,\langle b,c\rangle$，但 $\langle a,c\rangle\notin R\cup S$，故 **$R\cup S$ 不传递**。而 $R$、$S$ 各自都传递（没有两段可接的链），可见「传递性在并运算下不保守」，即定理 4.10 中传递性只对 $R^{-1}$ 和 $R\cap S$ 保证成立。

**8.** 总关系数 $2^{n^{2}}$；反自反要求主对角线全 0，其余 $n^{2}-n$ 格任取，共 $2^{n^{2}-n}$ 个；自反要求主对角线全 1，其余任取，也是 $2^{n^{2}-n}$ 个。两者互不相交（对角线全 0 与全 1 不能同时），故所求为 $2^{n^{2}}-2\cdot 2^{n^{2}-n}$。$n=2$ 时：$2^{4}-2\cdot2^{2}=16-8=8$ 个。逐个验证：$A=\{1,2\}$ 上共 $16$ 个关系，对角线为 $(1,1)$ 的 $4$ 个全自反，为 $(0,0)$ 的 $4$ 个全反自反，对角线为 $(1,0)$ 或 $(0,1)$ 的各 $4$ 个才是「两者都不是」，共 $8$ 个，吻合。

:::
