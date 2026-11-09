# 第 3 章 CSS3 基础

> 这章两条主线：**怎么把样式放进页面（引入方式 + 优先级）** 和 **怎么选中元素（选择器 + 权重）**。把这两条弄清，剩下的字体/文本/背景/列表属性就是查表。

---

## 一、CSS 是什么

- 全称 **Cascading Style Sheet**，层叠样式表。
- 概念两层含义：一是对网页中元素（字体、段落、图像、列表等）属性的整体概括，即描述所有网页对象的显示形式；二是当多个 CSS 文件引用时样式冲突，按层次先后处理（这就是“层叠”）。
- W3C 创建 CSS 标准的目的是取代表格格式布局、帧等表现型语言，实现外观与结构分离。

CSS 的优势：内容与样式分离、样式复用提高效率、样式丰富布局灵活、减少代码量提升浏览速度、有利于搜索引擎收录。

CSS 的两大用途：**页面内容（元素）修饰** 与 **页面布局**。

## 二、语法结构

```css
h1 {
  font-size: 12px;
  color: #f00;
}
```

`选择器 { 声明1; 声明2; }`，每条声明是「属性: 值」。最后一条声明的 `;` 可不写，但建议写上。

## 三、三种引入方式

| 方式 | 写法 | 位置 | 优点 | 缺点 |
| --- | --- | --- | --- | --- |
| 行内样式 | `<h1 style="color:red;">` | 标签内 | 单一元素微调 | 不分离、难维护 |
| 内部样式表 | `<style> h1{...} </style>` | `<head>` 内 | 同页面方便修改 | 多页面不能共享 |
| 外部样式表 | `<link href="style.css" rel="stylesheet" type="text/css">` | `<head>` 内 | 可复用、彻底分离 | 多一次请求 |

外部样式表有两种引法：

```html
<!-- 链入式 -->
<link href="style.css" rel="stylesheet" type="text/css">
<!-- 导入式 -->
<style>@import url("css/common.css");</style>
```

链入式 vs 导入式的三点区别（简答高频）：

1. `<link>` 属于 XHTML 标签，`@import` 是 CSS2.1 的东西。
2. `<link>` 链接的 CSS 先加载再编译显示；`@import` 是先显示 HTML 结构，再把 CSS 加载进来。
3. `@import` 对不兼容 CSS2.1 的浏览器无效。

### 优先级

- 三种引入方式冲突时：**行内样式 > 内部样式表 > 外部样式表**（就近原则）。
- 同一来源多个规则冲突时：**权重**大者胜；权重相同则**后写的赢**（层叠）。

| 选择器 | 权重 |
| --- | --- |
| 行内 `style` | 1000 |
| ID 选择器 `#id` | 100 |
| 类 / 伪类 / 属性选择器 | 10 |
| 标签 / 伪元素 | 1 |
| 通配 `*` | 0 |

## 四、基本选择器

| 选择器 | 写法 | 说明 |
| --- | --- | --- |
| 标签选择器 | `p { font-size:16px; }` | 直接应用于 HTML 标签 |
| 类选择器 | `.blue { color:blue; }` | 定义时带点号，`class="blue"` 应用时不带点号；可多次使用 |
| ID 选择器 | `#nav_id { width:300px; }` | `id="nav_id"`；同一页面只能使用一次 |

优先级：**ID > 类 > 标签**。

## 五、高级选择器

| 选择器类型 | 代码 | 实例 | 说明 |
| --- | --- | --- | --- |
| 通用选择器 | `*` | `* { margin:0; }` | 选所有元素 |
| 属性选择器 | `[条件]` | `[href]`、`[attr="val"]` | 根据属性选 |
| 并集选择器 | `选择器, 选择器` | `em, strong {}` | 取并集 |
| 后代选择器 | `E F`（空格） | `.asideNav li {}` | 祖先内所有后代，必须以空格隔开 |
| 子代选择器 | `E > F` | `ul > li {}` | 仅直接子元素 |
| 相邻兄弟 | `E + F` | `p + a {}` | 紧跟在 E 后的 F |
| 通用兄弟 | `E ~ F` | `h2 ~ p {}` | E 之后所有同辈 F |
| 伪类 / 伪元素 | `:` / `::` | `a:hover{}`、`p::first-line{}` | 增加特殊效果 |

### 结构伪类选择器

| 选择器 | 含义 |
| --- | --- |
| `E:first-child` | 作为父元素第一个子元素的 E |
| `E:last-child` | 作为父元素最后一个子元素的 E | `n` 可为数字、`even`、`odd` |
| `E F:nth-child(n)` | 父级里从第一个元素数起，**不分类型**的第 n 个 F |
| `E:first-of-type` / `E:last-of-type` | 父元素内指定类型的第一个 / 最后一个 |
| `E F:nth-of-type(n)` | 父元素内**先看类型再看位置**的第 n 个 F |

> 一句话记：`nth-child` 不分类型数位置，`nth-of-type` 先分类型再数位置。这是选择题最爱的对比。

### 属性选择器

| 选择器 | 含义 |
| --- | --- |
| `E[attr]` | 具有 attr 属性的 E |
| `E[attr=val]` | 属性值完全等于 val（区分大小写） |
| `E[attr^=val]` | 以 val **开头** |
| `E[attr$=val]` | 以 val **结尾** |
| `E[attr*=val]` | **包含** val（任意位置） |

## 六、层叠性、继承性、重要性

- **层叠性**：权重相同时，后写的样式生效。
- **继承性**：可继承的样式会应用到某标签及其后代（如 `p { color:pink; }` 会让 `p` 内的 `span` 也变粉色）。
- **重要性**：`!important` 优先级最高，会覆盖其他声明。

```css
p { color:red !important; }
p { color:green; }   /* 结果是红 */
```

## 七、属性单位

长度单位：

- 相对单位：`em`、`ex`、`ch`、`rem`、`%`，以及可视区百分比 `vw`、`vh`、`vmin`、`vmax`。
- 绝对单位：`cm`、`mm`、`in`、`pt`、`px`。
- 未指定字体大小时，普通文本默认 16px（16px = 1em）。

颜色单位：

- 十六进制：`#RRGGBB`，如 `#A983D8`，前两位红、中间两位绿、最后两位蓝。
- `rgb(r,g,b)`：0～255。
- `rgba(r,g,b,a)`：a 为 0～1，0 全透明、1 不透明。

## 八、字体与文本属性

字体：

| 属性 | 作用 | 例 |
| --- | --- | --- |
| `font-family` | 字体类型 | `font-family: "隶书", Arial;` |
| `font-size` | 字号 | `font-size: 14px;` |
| `font-style` | 风格（normal/italic/oblique） | `font-style: italic;` |
| `font-weight` | 粗细（normal/bold/bolder/lighter/100–900） | `font-weight: bold;`（400=normal，700=bold） |
| `font` | 简写 | `font: italic bold 36px "宋体";` |

`font` 简写顺序（必背）：**字体风格 → 字体粗细 → 字体大小 → 字体类型**。

文本：

| 属性 | 作用 | 常用值 |
| --- | --- | --- |
| `color` | 文字颜色 | `#00C` |
| `text-align` | 水平对齐 | left / right / center / justify |
| `text-indent` | 首行缩进 | `20px` |
| `line-height` | 行高 | `25px` |
| `text-decoration` | 装饰线 | none / underline / overline / line-through |
| `text-shadow` | 文本阴影 | `color h-shadow v-shadow blur-radius` |

## 九、背景属性

| 属性 | 作用 | 取值 |
| --- | --- | --- |
| `background-color` | 背景色 | 颜色值 |
| `background-image` | 背景图 | `url(图片路径)` |
| `background-repeat` | 平铺方式 | repeat / repeat-x / repeat-y / no-repeat |
| `background-attachment` | 背景是否随内容滚动 | scroll / fixed |
| `background-position` | 背景位置 | 像素、百分比、关键词（left/center/right + top/center/bottom） |
| `background-size` | 背景尺寸 | auto / 百分比 / cover（放大填满） / contain（等比例适应） |
| `background` | 复合属性 | `background: url(x.jpg) no-repeat;` |

`cover` 与 `contain` 的区别常考：cover 可能裁切但一定填满，contain 一定完整显示但可能留白。

## 十、列表与超链接伪类

`list-style` 系列：

| 值 | 效果 |
| --- | --- |
| `none` | 无标记（导航最常用） |
| `disc` | 实心圆（默认） |
| `circle` | 空心圆 |
| `square` | 实心正方形 |
| `decimal` | 数字 |

简写：`list-style: square inside url('/i/eg_arrow.gif');`

超链接四个伪类的**书写顺序固定**（必须按此顺序，否则不生效）：

```css
a:link    { color: #9ef5f9; }  /* 未访问，主样式 */
a:visited { color: #333;    }  /* 已访问 */
a:hover   { color: #ff7300; }  /* 鼠标悬停，最常用 */
a:active  { color: #999;    }  /* 鼠标按下未释放 */
```

口诀：**LoVe HAte**（link → visited → hover → active）。

## 十一、CSS3 渐变

```css
/* 线性渐变：方向、颜色1、颜色2 ... */
#grad1 {
  height: 200px;
  background-color: red;                                 /* 回退 */
  background-image: linear-gradient(to right, red, yellow);
}

/* 径向渐变：形状 大小 at 位置、颜色序列 */
#grad2 {
  background-image: radial-gradient(circle at center, red, yellow);
}
```

线性渐变颜色沿一条直线过渡；径向渐变从一点向四周发散，是圆形或椭圆形。

---

## 考点自测

1. 三种引入 CSS 的方式及优先级顺序？
2. `<link>` 与 `@import` 的三点区别？
3. 基本选择器的优先级？属性选择器 `^=`、`$=`、`*=` 分别匹配什么？
4. `nth-child(n)` 与 `nth-of-type(n)` 的区别？
5. 超链接伪类的书写顺序？
6. `font` 简写顺序？
7. `background-size: cover` 与 `contain` 的区别？

> 参考：1. 行内、内部、外部；行内 > 内部 > 外部（就近），同权重时后写的胜。2. link 属 XHTML，@import 属 CSS2.1；link 先加载 CSS 再显示，@import 先显示结构再加载；@import 对不兼容 CSS2.1 的浏览器无效。3. ID > 类 > 标签；`^=` 开头、`$=` 结尾、`*=` 包含。4. 前者父级里不分类型数位置，后者先按类型筛选再数位置。5. link → visited → hover → active。6. 风格 → 粗细 → 大小 → 类型。7. cover 放大填满（可能裁切），contain 等比例适应（可能留白）。
