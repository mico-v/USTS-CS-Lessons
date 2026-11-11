# 考前速查与易错点

离散数学的公式不多，但每一条都会在考场被反复调用。这一页把第 1–7 章必须背下来的结论、判别流程和易错点压到一起，最后一晚只看这一页。

## 一、集合论速查

| 结论 | 公式 / 说法 |
| --- | --- |
| 子集个数 | $n$ 元集合的子集共 $2^n$ 个（含 $\varnothing$ 与自身），真子集 $2^n-1$ 个 |
| 幂集元素数 | $\lvert P(A) \rvert = 2^{\lvert A \rvert}$，且 $P(\varnothing)=\{\varnothing\}$ |
| 空集 | $\varnothing$ 是**任何**集合的子集，但 $\varnothing \notin \varnothing$ |
| 德摩根律 | $\overline{A\cup B}=\overline{A}\cap\overline{B}$，$\overline{A\cap B}=\overline{A}\cup\overline{B}$ |
| 对称差 | $A\oplus B=(A-B)\cup(B-A)=(A\cup B)-(A\cap B)$ |
| 容斥（两集） | $\lvert A\cup B\rvert=\lvert A\rvert+\lvert B\rvert-\lvert A\cap B\rvert$ |
| 容斥（三集） | $\lvert A\cup B\cup C\rvert=\sum\lvert A\rvert-\sum\lvert A\cap B\rvert+\lvert A\cap B\cap C\rvert$ |
| 可数集 | $\mathbb{N}$、$\mathbb{Z}$、$\mathbb{Q}$、$\mathbb{N}\times\mathbb{N}$ 可数；任意可数集的可数并仍可数 |
| 不可数 | $\mathbb{R}$、$[0,1]$、$P(\mathbb{N})$ 不可数（康托尔对角线法） |

判两个集合相等：**互相包含**（$A\subseteq B$ 且 $B\subseteq A$）或**成员表法**（列出每种元素归属的 0/1 组合，逐列比）。

## 二、命题逻辑速查

| 结论 | 公式 / 说法 |
| --- | --- |
| 蕴涵去箭头 | $G\to H \Leftrightarrow \neg G\vee H$ |
| 等价去箭头 | $G\leftrightarrow H \Leftrightarrow (G\to H)\wedge(H\to G)$ |
| 逆否 | $G\to H \Leftrightarrow \neg H\to\neg G$ |
| 输出律 | $(G\wedge H)\to S \Leftrightarrow G\to(H\to S)$ |
| 归谬 | $(G\to H)\Leftrightarrow (G\wedge\neg H\to \mathbf{F})$ |
| 分配律 | $G\vee(H\wedge S)\Leftrightarrow(G\vee H)\wedge(G\vee S)$，另一侧对偶 |
| 吸收律 | $G\vee(G\wedge H)\Leftrightarrow G$，$G\wedge(G\vee H)\Leftrightarrow G$ |
| 极小项编号 | 变元按 $P,Q,R$ 顺序取 0/1，$m_i$ 对应二进制值 $i$（1 表正文字，0 表否定） |
| 由主析取写主合取 | 取**没有出现**的编号 $M_j$，逐个合取 |
| 完备集 | $\{\neg,\wedge\}$、$\{\neg,\vee\}$、$\{\uparrow\}$、$\{\downarrow\}$ 都是完备集，前两个不是极小完备集 |

范式求法固定四步：去 $\to,\leftrightarrow$ → 否定内移（德摩根 + 双重否定）→ 分配 → 合并同类项。求主范式时可以直接写真值表，1 的行取极小项析取，0 的行取极大项合取。

推理三种方法：真值表（前提合取 $\to$ 结论是重言式）、等价演算、演绎法。演绎法四件套：`P`（引用前提）、`T`（套用等价式/推理定律）、`CP`（把结论的前件当附加前提）、反证（把结论的否定当前提，推出矛盾）。

## 三、谓词逻辑速查

| 结论 | 公式 / 说法 |
| --- | --- |
| 量词否定 | $\neg\forall xP(x)\Leftrightarrow\exists x\neg P(x)$，$\neg\exists xP(x)\Leftrightarrow\forall x\neg P(x)$ |
| 可分配 | $\forall x(P(x)\wedge Q(x))\Leftrightarrow\forall xP(x)\wedge\forall xQ(x)$，$\exists$ 对 $\vee$ 同理 |
| 不可分配 | $\forall x(P(x)\vee Q(x))\not\Leftrightarrow\forall xP(x)\vee\forall xQ(x)$（只能 $\Leftarrow$ 方向） |
| 辖域扩张 | $S\vee\forall xP(x)\Leftrightarrow\forall x(S\vee P(x))$（$S$ 不含 $x$） |
| 量词顺序 | $\forall x\exists yP(x,y)\Leftarrow\exists y\forall xP(x,y)$，反向不成立 |
| 四条规则 | US：$\forall xP(x)\Rightarrow P(c)$；UG：$P(x)$ 对任意 $x$ 成立 $\Rightarrow\forall xP(x)$；ES：$\exists xP(x)\Rightarrow P(c)$（$c$ 必须是**新**常量）；EG：$P(c)\Rightarrow\exists xP(x)$ |

符号化三种模板必须背：

- 「所有 $A$ 都是 $B$」：$\forall x(A(x)\to B(x))$——**不能**写成 $\forall x(A(x)\wedge B(x))$
- 「有的 $A$ 是 $B$」：$\exists x(A(x)\wedge B(x))$——**不能**写成 $\exists x(A(x)\to B(x))$
- 「没有 $A$ 是 $B$」：$\neg\exists x(A(x)\wedge B(x))$，等价于 $\forall x(A(x)\to\neg B(x))$

前束范式三步：换名（避免同名变元冲突）→ 否定内移 → 量词逐个左移。Skolem 范式再把 $\exists$ 消掉：左边全是 $\forall$ 就用 Skolem 函数，否则用 Skolem 常量。

## 四、二元关系速查

| 性质 | 定义（$\forall a,b\in A$） | 关系矩阵 | 关系图 |
| --- | --- | --- | --- |
| 自反 | $\forall x,\ xRx$ | 主对角线全 1 | 每点有自环 |
| 反自反 | $\forall x,\ \neg xRx$ | 主对角线全 0 | 无自环 |
| 对称 | $xRy\Rightarrow yRx$ | 矩阵关于主对角线对称 | 有边必双向 |
| 反对称 | $xRy\wedge yRx\Rightarrow x=y$ | $M$ 与 $M^{\mathsf T}$ 的 1 不重合（对角线除外） | 无双向边 |
| 传递 | $xRy\wedge yRz\Rightarrow xRz$ | $M^2\le M$（逐元素「或」） | 有两条边就有捷径边 |

| 结论 | 公式 / 说法 |
| --- | --- |
| 复合 | $R\circ S=\{\langle x,z\rangle\mid\exists y(xSy\wedge yRz)\}$，矩阵用**布尔乘法**（先乘后「或」） |
| 逆 | $(R\circ S)^{-1}=S^{-1}\circ R^{-1}$，矩阵转置 |
| 幂 | $R^0=I_A$，$R^m\circ R^n=R^{m+n}$ |
| 自反闭包 | $r(R)=R\cup I_A$ |
| 对称闭包 | $s(R)=R\cup R^{-1}$ |
| 传递闭包 | $t(R)=R\cup R^2\cup\dots\cup R^n$（$n$ 为元素个数），Warshall 是 $O(n^3)$ 求法 |

性质保守性：$R,S$ 都有某性质 → $R\cap S$、$R^{-1}$ 也有；$R\cup S$ **不一定**（对称、自反、反自反、传递里，只有对称与反自反对并封闭要小心）。复合不保对称、不保自反。

## 五、特殊关系速查

| 结论 | 公式 / 说法 |
| --- | --- |
| 等价关系 | 自反 + 对称 + 传递；等价类 $[a]=\{x\mid xRa\}$ |
| 等价类三条 | $[a]\ne\varnothing$；$a\in[a]$；$aRb\Leftrightarrow[a]=[b]$，$[a]\ne[b]\Rightarrow[a]\cap[b]=\varnothing$ |
| 商集 | $A/R=\{[a]\mid a\in A\}$，是 $A$ 的一个划分 |
| 划分 ↔ 等价关系 | 一个划分定一个等价关系，反之亦然（一一对应） |
| 偏序 | 自反 + 反对称 + 传递；哈斯图去掉自环与传递边，上大下小 |
| 特殊元 | 最大元必唯一且是上界；极大元可多个；上确界 = 最小上界 |
| 函数个数 | 从 $m$ 元集到 $n$ 元集共 $n^m$ 个；双射 $m=n$ 时 $m!$ 个 |
| 单/满/双 | 有限集上：单射 $\Leftrightarrow$ 满射 $\Leftrightarrow$ 双射（$\lvert A\rvert=\lvert B\rvert$ 时） |
| 逆函数 | 只有双射才有逆，且 $(f^{-1})^{-1}=f$，$(g\circ f)^{-1}=f^{-1}\circ g^{-1}$ |

哈斯图画法：先画「覆盖」关系（$a\lessdot b$ 表示 $a<b$ 且中间没有元素），只画覆盖边，箭头省略默认向上。

## 六、图速查

| 结论 | 公式 / 说法 |
| --- | --- |
| 握手定理 | $\sum_{v}\deg v=2\lvert E\rvert$；有向图 $\sum \deg^+=\sum\deg^-=\lvert E\rvert$ |
| 奇度点 | 度数为奇数的结点个数必为**偶数** |
| 完全图 | $K_n$ 有 $\dfrac{n(n-1)}{2}$ 条边，每点度数 $n-1$ |
| 通路计数 | 邻接矩阵 $A$ 的 $A^m$ 中第 $(i,j)$ 元 = 从 $v_i$ 到 $v_j$ 长度为 $m$ 的通路数 |
| 可达性 | $P=A\vee A^2\vee\dots\vee A^{n}$（布尔运算），$P_{ij}=1$ 表示可达 |
| 距离 | 最短通路长度；不连通记 $\infty$ |
| Dijkstra | 单源最短路，每轮选未标记点中距离最小者，松弛其邻边，$O(n^2)$ |
| Floyd | 多源最短路，$d_{ij}\leftarrow\min(d_{ij},d_{ik}+d_{kj})$，$k$ 从 1 到 $n$ 做外层 |
| 连通度 | 点连通度 $\kappa(G)$、边连通度 $\lambda(G)$，有 $\kappa\le\lambda\le\delta$ |

判断连通：无向图看连通分支数；有向图分三种——**强**连通（任意两点互相可达）、**单向**连通（任意两点至少一个方向可达）、**弱**连通（忽略方向后连通）。

## 七、特殊图速查

| 结论 | 公式 / 说法 |
| --- | --- |
| 树的等价定义 | 连通无回路；$m=n-1$；任两点唯一通路；删任一边不连通；加任一边得唯一回路 |
| 叶结点 | $n\ge2$ 的树至少两片叶；$k$ 元完全树：$(k-1)i=t-1$（$i$ 分支点，$t$ 叶） |
| 生成树 | 破圈法（每次删回路上一 条边）、避圈法（每次加不成圈的边） |
| 最小生成树 | Kruskal 按权升序选边不成圈；Prim 从一个点长出去，每次取连到树上的最小边 |
| 哈夫曼 | 每次取两个最小权合并，新权入队；$\mathrm{WPL}=\sum w_i l_i$；左 0 右 1，前缀码 |
| 欧拉回路 | 无向：连通且所有点度数为偶；有向：弱连通且每点入度 = 出度 |
| 欧拉通路 | 无向：连通且恰有 0 或 2 个奇度点（2 个时它们是端点） |
| 哈密顿 | 无简单充要条件。必要：删 $k$ 点后分支数 $\le k$；充分：Dirac（$\deg\ge n/2$）、Ore（不相邻 $u,v$ 有 $\deg u+\deg v\ge n$） |
| 偶图判定 | $G$ 是偶图 $\Leftrightarrow$ 无奇数长度的回路 $\Leftrightarrow$ 可用两色染色 |
| 霍尔定理 | 存在饱和 $X$ 的匹配 $\Leftrightarrow$ 对任意 $S\subseteq X$，$\lvert N(S)\rvert\ge\lvert S\rvert$ |
| 欧拉公式 | 连通平面图 $n-m+r=2$（$r$ 含外部面） |
| 平面图边数 | $n\ge3$ 简单连通平面图：$m\le 3n-6$；无三角形时 $m\le 2n-4$ |
| 非平面 | $K_5$（$m=10>3\cdot5-6=9$）与 $K_{3,3}$（$m=9>2\cdot6-4=8$）都非平面 |
| 库拉托夫斯基 | 平面图 $\Leftrightarrow$ 不含 $K_5$ 或 $K_{3,3}$ 的细分 |

## 八、18 条易错点

1. 空集是任何集合的子集，但 $\varnothing\notin\varnothing$；$P(\varnothing)=\{\varnothing\}$ 有 **1** 个元素，不是 0 个。
2. 集合的元素不能重复、无序：$\{1,1,2\}=\{1,2\}$，$\{1,2\}=\{2,1\}$，但 $\langle1,2\rangle\ne\langle2,1\rangle$。
3. 「$A\subseteq B$」与「$A\in B$」是两回事，$\in$ 左边必须是元素。
4. 真值表行数是 $2^n$，$n$ 是**不同**命题变元的个数，重复出现的算一个。
5. 「或」在离散数学里默认**可兼或**（$P\vee Q$ 在两者都真时为真），除非明确说「不可兼或」。
6. 「如果 $P$ 则 $Q$」在 $P$ 为假时整体为**真**；「除非 $A$ 否则 $B$」符号化为 $\neg A\to B$，等价 $A\vee B$。
7. 主析取范式里极小项必须**每个变元出现一次**；漏变元的合取项只是析取范式。
8. 由真值表求主析取范式看真值为 **1** 的行，求主合取范式看真值为 **0** 的行，两种编号互补。
9. $\neg\forall xP(x)$ 与 $\forall x\neg P(x)$ 完全不同，否定要**穿过**量词并换量词。
10. $\forall x(P(x)\vee Q(x))$ 推不出 $\forall xP(x)\vee\forall xQ(x)$；量词不能随意分配进 $\vee$ 或 $\wedge$。
11. 使用 ES 规则时，取出的常量必须是**前面没出现过的新常量**，否则会推出假结论。
12. 关系的**反对称**不是「不对称」：$xRy$ 且 $yRx$ 时必须 $x=y$，允许自环。
13. 关系复合 $R\circ S$ 的次序：先走 $S$ 再走 $R$；矩阵乘法是 $M_R\cdot M_S$（布尔乘），别写反。
14. 自反闭包加的是 $I_A$，不是「每点加自环」之外的东西；传递闭包不一定等于 $R^2$，要取并到 $R^n$。
15. 等价类的并是整个 $A$，商集是**集合的集合**，写的时候外层花括号别丢。
16. 哈斯图求最大元：最大元必须与**所有**元素可比；极大元只要求没人比它大，可以有好几个。
17. 图的度数把环算 **2**；有向图握手定理分别对入度、出度成立，别把两者相加当边数。
18. 欧拉图看**边**（一次走遍所有边），哈密顿图看**点**（一次走遍所有点）；「连通 + 有奇度点」判的是欧拉通路不是欧拉回路。
