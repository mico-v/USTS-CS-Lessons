# 例题精讲：三份作业里的真题

> 下面 40 道题全部来自学习通上的实际作业（作业一 / 计算机作业二 / 作业三），按考点重新编排。答案和解析来自平台批改结果，我把重复题合并了、解析压短了。**建议先盖住答案自己写，再往下看。**

---

## 一、HTML5 基本概念（作业一）

**1.（单选）下列不是开发 HTML 网页的软件是**
A. EditPlus　B. Notepad++　C. Dreamweaver　D. Photoshop

答案 **D**。前三者都是代码/网页编辑器，Photoshop 是图像处理软件。

**2.（判断）HTML 是一种编程语言，可以通过编写逻辑代码实现程序运算功能。**

答案 **错**。HTML 是**标记语言**（Hyper Text Markup Language），只描述结构，没有变量、判断、循环，不能运算。

> 三种语言的分界（简答常考）：标记语言描述**结构**（HTML），脚本语言由解释器逐句执行、写页面行为（JavaScript），编程语言需编译或运行环境、能做通用计算（Java/C）。三者的边界是「描述 / 执行 / 计算」。

**3.（单选）HTML5 中，用于告知浏览器使用 HTML5 标准解析文档的声明写法是**
A. `<!DOCTYPE HTML5>`　B. `<!DOCTYPE html>`　C. `<DOCTYPE html>`　D. `<html5>`

答案 **B**。小写、只有 `html` 一个词，不带版本号，也不是标签（没有结束符）。

**4.（单选）下列 HTML 标签的嵌套写法，正确的是**
A. `<div><p></div></p>`　B. `<div><p></p></div>`　C. `<p><div></div></p>`　D. `<div/><p></div>`

答案 **B**。标签必须**成对嵌套、先开的后关**，交叉嵌套会导致解析异常。

**5.（判断）HTML 标签对大小写不敏感，`<html>`、`<HTML>`、`<Html>` 会被识别为同一个标签。**

答案 **对**。HTML 标签不区分大小写；但 XHTML 要求小写，写小写是通用习惯。

**6.（单选）若要在 HTML 页面中设置供搜索引擎抓取的关键字，应该使用 meta 标签的哪个属性组合**
A. `name="keywords"` + `content`　B. `http-equiv="keywords"` + `content`　C. `name="description"` + `charset`　D. `charset="keywords"`

答案 **A**。`<meta name="keywords" content="前端,HTML5,CSS">`。`http-equiv` 用于模拟 HTTP 响应头，`charset` 只声明编码。

**7.（填空）若要设置网页字符编码为 UTF-8，应在 meta 标签中使用 \_\_\_\_ 属性指定编码值。**

答案 **charset**，即 `<meta charset="UTF-8">`。要覆盖全世界字符就用 UTF-8。

**8.（单选）下列关于 `<link>` 标签的描述，正确的是**
A. 用于在页面中插入图片　B. 用于引入外部 CSS 文件，必须放在 head 中　C. 只能引入 JavaScript　D. 是行内标签，可写在 body 任意位置

答案 **B**。图片用 `<img>`，脚本用 `<script>`，样式表用 `<link rel="stylesheet" href="...">`。

## 二、表格与表单（作业一）

**9.（填空）表格的标题标记是 \_\_\_\_，表格行的标记是 \_\_\_\_，表格的表头标记是 \_\_\_\_。**

答案 `caption`、`tr`、`th`。`td` 是普通单元格，`th` 默认加粗居中。

**10.（填空）单元格跨 3 行：`<td ____="____">`；跨 5 列：`<td ____="____">`。**

答案 `rowspan="3"`、`colspan="5"`。记法：**row 管上下（行），col 管左右（列）**。

**11.（单选）设置复选框的（ ）属性时可以实现让该复选框为预选状态。**
A. `multiple`　B. `selected`　C. `checked`　D. `default`

答案 **C**。`checked` 管 input（radio/checkbox），`selected` 管 `<option>`。

**12.（单选）要使下拉列表框支持多选功能，必须设置 select 标记的（ ）属性。**
A. `multiple`　B. `checked`　C. `selected`　D. `size`

答案 **A**。`<select multiple>` 后可以按住 Ctrl 多选。

**13.（填空）一组复选框中复选框的 name 属性值必须 \_\_\_\_，value 值也必须 \_\_\_\_；而一组单选钮中每一个单选钮的 name 属性值必须 \_\_\_\_，value 属性值必须 \_\_\_\_。**

答案：复选框中 **相同 / 相同**，单选钮中 **相同 / 不同**。复选框 name 相同是为了服务端一次收到多个值；单选钮靠相同的 name 实现「互斥」，靠不同 value 区分选的是哪一个。

**14.（填空）HTML5 新增 \_\_\_\_ 表单元素可以产生数据加密；新增 \_\_\_\_ 可以产生不同类型的输出；新增 \_\_\_\_ 可以定义选项列表。**

答案 `keygen`、`output`、`datalist`。`<datalist>` 配合 `input list="id"` 用，给输入框挂候选列表。

**15.（单选）以下哪个 target 属性值表示在新空白窗口打开链接？**
A. `_self`　B. `_blank`　C. `_parent`　D. `_top`

答案 **B**。`_self` 本窗口、`_parent` 父框架、`_top` 顶层框架。

## 三、CSS3 引入方式与优先级（作业二）

**16.（单选）同时用行内、内部、外部样式表设置同一段落文字颜色且权重相同时，最终生效的是**
A. 外部　B. 内部　C. 行内　D. 浏览器默认

答案 **C**。权重相同时遵循**就近原则**，行内样式离元素最近，优先级最高。

**17.（单选）下列 CSS3 语法书写正确、符合 W3C 规范的是**
A. `h1 {font-size:12px color:#F00}`　B. `h1 {font-size:12px; color:#F00;}`　C. `{h1 font-size:12px;}`　D. `h1: {font-size=12px;}`

答案 **B**。选择器 + 大括号，声明用 `属性:值;`，冒号不能用等号，最后一条也建议带分号。

**18.（单选）内部样式表的 CSS 代码应该写在**
A. body 内任意位置　B. head 里的 `<style>` 中　C. 文档最末尾　D. `<!DOCTYPE>` 上方

答案 **B**。写在 `<head><style>…</style></head>`。

**19.（单选）下列关于内部样式表的优缺点，说法正确的是**
A. 可以多页面共享　B. 方便同页面修改，但不利于多页面复用　C. 彻底分离内容和样式　D. 必须用 link 引入

答案 **B**。内部样式表只作用于当前页；要跨页复用必需外部样式表。

**20.（单选）下列关于导入式引入外部 CSS 的语法，正确的是**
A. `<style type="text/css">@import url("style.css");</style>`　B. `<link import="style.css">`　C. `<script src="style.css">`　D. `<css src="style.css">`

答案 **A**。`@import url("…")` 必须写在 `<style>` 里，且要放在其他规则之前（否则可能不生效）。

**21.（单选）CSS 中「层叠」的含义是**
A. 能设多层阴影　B. 多个样式表冲突时按层次先后决定谁生效　C. z 轴层叠　D. 代码分层书写

答案 **B**。「层叠」= 冲突时的取舍规则（权重 + 先后）。

**22.（单选）下列不属于 CSS3 常用用途的是**
A. 元素修饰　B. 页面布局　C. 操作 DOM 节点实现交互　D. 设置字体字号颜色

答案 **C**。操作 DOM 是 JavaScript 的活；另一版本的同题把「定义网页结构与语义」作为答案，那是 HTML 的活。

## 四、盒子模型与居中（作业二）

**23.（填空）CSS 盒子模型从内到外依次由内容、\_\_\_\_、边框、外边距四部分组成。**

答案 **内边距（padding）**。顺序：content → padding → border → margin。

**24.（单选）`margin: 10px 20px 30px;` 的左外边距是**
A. 10px　B. 20px　C. 30px　D. 0

答案 **B**。三值 = 上 / 左右 / 下，左边省略时取右边的值。

**25.（单选）`margin: 5px 10px 15px 20px;` 的下外边距是**
A. 5px　B. 10px　C. 15px　D. 20px

答案 **C**。四值按**顺时针** 上 → 右 → 下 → 左 赋值，第三个是下。

**26.（单选）`margin: 8px;` 的下外边距是**
A. 0　B. 4px　C. 8px　D. 16px

答案 **C**。一个值时四个方向相同。

**27.（单选）让一个固定宽度的块级元素在父容器中水平居中，应该用**
A. `margin:0 auto`　B. `padding:0 auto`　C. `text-align:center`　D. `align:center`

答案 **A**。两个必要条件：**定宽** + **块级**；`text-align:center` 只让行内内容居中，管不了块本身。

**28.（判断）CSS 只能统一设置四个方向的边框，不能单独给某一个方向设置样式、宽度或颜色。**

答案 **错**。`border-top` / `border-right` / `border-bottom` / `border-left` 可以单独设置，还能分别给 `-width`、`-style`、`-color`。

**29.（判断）CSS3 向模块化发展，新增了圆角、阴影、动画等属性，且所有低版本浏览器都能完美支持。**

答案 **错**。新特性依赖高级浏览器，低版本存在兼容问题——考试里凡是出现「所有浏览器都支持」基本可以判错。

**30.（简答）盒子模型的四个组成部分，`margin` 缩写规则，块级元素水平居中的条件和方法。**

答题要点：① 由内到外 content、padding、border、margin；② 一个值四边相同，两个值「上下、左右」，三个值「上、左右、下」，四个值「上、右下、左」顺时针；③ 块级元素设了固定宽度后，用 `margin: 0 auto` 让左右外边距自动均分实现居中（起因：行内元素用父元素 `text-align:center`）。

## 五、JavaScript 数组（作业三）

**31.（单选）执行 `var arr = new Array(3); arr[0]="a"; arr[1]="b"; arr.push("c");` 后 `arr.length` 是**
A. 2　B. 3　C. 4　D. 5

答案 **C**。`new Array(3)` 先得到长度 3 的数组，`push` 再加一个，长度变 4。

**32.（单选）下列代码中创建了包含 4 个初始水果元素的数组的是**
A. `new Array("apple","orange","peach","banana")`　B. `new Array("apple orange peach banana")`　C. `new Array(); fruit[4]="banana"`　D. `Array(4)`

答案 **A**。传多个参数=初始元素；传单个字符串=只有一个元素；传单个数字=初始长度。

**33.（单选）`var nums=[3,1,2]; nums.sort(); nums.pop();` 返回的结果是**
A. 1　B. 2　C. 3　D. undefined

答案 **C**。`sort()` 按字符编码排成 `[1,2,3]`，`pop()` 删掉并返回最后一个元素 3。

**34.（单选）执行 `var arr=[3,1,2]; arr.sort(); var res=arr.toString();` 后 res 是**
A. `"3,1,2"`　B. `"1,2,3"`　C. `"123"`　D. `"312"`

答案 **B**。`toString()` 用逗号拼接数组元素。

**35.（单选）`var arr=["北京","上海","广州"]; var str=arr.join("-");` 后 str 是**
A. `"北京,上海,广州"`　B. `"北京-上海-广州"`　C. `"北京上海广州"`　D. `"北京|上海|广州"`

答案 **B**。`join(分隔符)` 自定义分隔符，不传参时等同逗号。

**36.（单选）能把两个或多个数组合并并返回新数组的方法是**
A. `join()`　B. `concat()`　C. `push()`　D. `toString()`

答案 **B**。`concat()` 返回新数组、不改变原数组。

**37.（填空）数组元素数目属性是 \_\_\_\_；合并数组返回新数组的方法是 \_\_\_\_；删除并返回最后一个元素的方法是 \_\_\_\_；末尾添加元素并返回新长度的方法是 \_\_\_\_。**

答案 `length`、`concat()`、`pop()`、`push()`。配对记：**push/pop 管尾巴，join 拼字符串，concat 合并，sort 排序，toString 转逗号串**。

## 六、DOM 与 BOM（作业三）

**38.（单选）文档对象模型 DOM 的作用是**
A. 只操作窗口大小　B. 作为 HTML/XML 文档的编程接口，允许程序访问和修改文档内容　C. 描述 JS 语法　D. 管理浏览历史

答案 **B**。DOM 是 W3C 推荐的处理 HTML/XML 的标准编程接口。

**39.（单选）在 BOM 层次中，作为其他浏览器对象顶层容器的是**
A. document　B. window　C. location　D. history

答案 **B**。`window` 是顶层，也是全局对象。

**40.（单选）下列关于 document 对象的描述，正确的是**
A. document 是 BOM 顶层　B. 代表整个 HTML 文档，是访问所有元素的入口　C. 管理历史记录　D. 独立于 window 存在

答案 **B**。`document` 是 `window` 的子对象，不是顶层。

**41.（单选）能提供浏览器过去访问过的 URL 信息、实现前进后退的对象是**
A. history　B. document　C. location　D. window

答案 **A**。`history.back()` / `history.forward()` / `history.go(n)`。

**42.（单选）执行 `window.location = "http://www.example.cn";` 的功能是**
A. 弹新窗口　B. 当前窗口跳转到该地址　C. 后退　D. 刷新

答案 **B**。给 `location`（或 `location.href`）赋值即跳转；刷新用 `location.reload()`。

**43.（填空）BOM 的核心对象是 \_\_\_\_；用于访问所有 HTML 元素的入口对象是 \_\_\_\_；提供历史操作的是 \_\_\_\_；提供当前 URL 的是 \_\_\_\_。**

答案 `window`、`document`、`history`、`location`（还有 `navigator` 存浏览器信息、`screen` 存屏幕信息）。

**44.（判断）每个文档的根节点是 documentElement，`<html>` 是文档元素。**

严格说：根节点是**文档节点**（`document`），它下面通常只有一个子节点 `<html>`，即**文档元素**（`document.documentElement`）。作业原题问「每个文档的根节点是 \_\_\_\_ 节点」，标准答案填「文档」。

**45.（判断）DOM 把 HTML 文档描绘成层次化的节点树，树中每个对象都称为节点。**

答案 **对**。节点类型包括文档节点、元素节点、文本节点、属性节点、注释节点。

**46.（判断）JavaScript 由 ECMAScript、DOM、BOM 三部分组成，其中 ECMAScript 是核心。**

答案 **对**。另有同题问「不属于 JavaScript 核心组成部分的是」——选项里的 `JVM` 是 Java 虚拟机，与 JS 无关。

**47.（判断）JavaScript 作为脚本语言，需要提前编译生成可执行文件才能在浏览器中运行。**

答案 **错**。它是解释型脚本语言，由浏览器里的脚本引擎逐句解释执行，跨平台正来自这一点。

## 七、读代码写结果（自测）

动手推一遍，答案在下面。

```javascript
// ①
var a = new Array(3);
a[0] = 'x'; a.push('y');
console.log(a.length);          // ?

// ②
var b = [10, 9, 1];
b.sort();
console.log(b.join('|'));       // ?

// ③
var c = ['a', 'b', 'c'];
var d = c.concat(['d']);
console.log(c.length, d.length);// ?

// ④
var e = [1, 2, 3];
console.log(e.pop(), e.length);// ?
```

答案：① `4`（长度 3 + push 一个）；② `"1|10|9"`（默认按字符编码，`"1" < "10" < "9"`）；③ `3 4`（`concat` 不改原数组）；④ `3 2`（`pop` 返回被删的元素）。

## 八、填空题速记表

三份作业 37 道填空，答案基本都在这一张表里。

| 题干关键词 | 答案 |
| --- | --- |
| 网页 \_\_ 与样式分离 | 内容（结构） |
| CSS 写在标签的 \_\_ 属性 | style |
| 外部样式表扩展名 | .css |
| 导入外部 CSS 的规则 | @import |
| 盒子模型第二层 | 内边距（padding） |
| 三大基本选择器之二 | 类（class） / ID |
| CSS3 的发展趋势 | 模块化 |
| HTML 的中文含义 | 超文本标记语言 |
| 字符编码通用值 | UTF-8 |
| 引入外部 CSS 的标签 | link |
| 最大标题 / 换行 / 文档根元素 | h1 / br / html |
| 注释写法 | `<!-- -->` |
| 表格标题 / 行 / 表头 | caption / tr / th |
| 跨行 / 跨列 | rowspan / colspan |
| 下拉框多选 | multiple |
| 预选：复选框 / 下拉项 | checked / selected |
| HTML5 加密 / 输出 / 选项列表 | keygen / output / datalist |
| BOM 核心 | window |
| 访问 HTML 元素的入口 | document |
| 历史 / URL / 浏览器信息 | history / location / navigator |
| DOM 的结构 | 节点树 |
| 数组长度 / 合并 / 删尾 / 加尾 | length / concat() / pop() / push() |
| 拼字符串 / 排序 / 转字符串 | join() / sort() / toString() |
| JS 跨平台所依赖 | 浏览器 |

> 最后一个提醒：填空一个空只填一个词，多个空分开填；术语带上括号里的英文更保险（`内容（content）`），只写中文可能被判错——这是作业二、作业三实打实丢过分的坑。
