# 第 2 章 HTML5 基础

> 本章是全书分值最实在的一章：既有概念（语言分类、文档结构、块/行内元素），也有手写代码（表格、表单、超链接）。复习方法：把每个标签的「作用 + 必考属性 + 一个例子」记住。

---

## 一、先分清三种语言

| 类型 | 特点 | 代表 |
| --- | --- | --- |
| 编程语言 | 编译成机器码或中间码再由解释器执行，有完整逻辑与流程控制 | Java、C |
| 脚本语言 | 由解释器直接解释，或借助系统提供的脚本接口 | Python、Shell、JavaScript |
| 标记语言 | 用一套标记标签描述文档结构与内容，本身不能做运算 | HTML、XML |

结论式表达：**HTML 是标记语言，不是编程语言**。下面这些是定义题的原句：

- HTML，Hyper Text Markup Language，超文本标记语言，用来制作网页的标准语言。
- 标记语言是一套标记标签（markup tag），HTML 用标记标签描述网页。
- HTML 文档 = HTML 标签 + 文本内容；用 HTML 写的超文本文件叫 HTML 文件，也叫 Web 文件。
- 当前最新版本是 HTML5。

## 二、发展史（记住 HTML5 是 2014-10-29）

1993-06 超文本标记语言 → 1995-11 HTML 2.0 → 1996-01-14 HTML 3.2 → 1997-12-18 HTML 4.0 → 1999-12-24 HTML 4.01 → 2000-01-26 XHTML 1.0 → 2001-05-31 XHTML 1.1 → 2002-08-05 XHTML 2.0 → **2014-10-29 HTML5**（目前网页中最常用）。

## 三、编写方法与命名规则

三种编写方法：手工直接写（记事本，存为 `.htm` / `.html`）、可视化编辑器（FrontPage、Dreamweaver）、服务器端实时动态生成。

命名规则：扩展名 `.htm` 或 `.html`；**无空格、无特殊符号**（如 `&`），只可有下划线 `_`，只能是英文和数字；区分大小写；首页文件名默认 `index.htm` 或 `index.html`。

## 四、书写规范（选择题常穿插）

1. 标签用尖括号括起来，如 `<html>`、`<head>`、`<body>`。
2. 多数标签成对出现：`<标签>内容</标签>`。
3. 标签可以嵌套，但**不能交叉**：`<h3><i>正确</i></h3>` ✅；`<h3><i>错误</h3></i>` ❌。
4. 标签名和属性名**不区分大小写**（建议全小写）。
5. 源代码里的空格和回车在浏览器中无效（要保留用 `<pre>`）。
6. 标记中不要有多余空格（`< h1>` 是错的）。
7. 属性值用双引号或单引号括起来，写成「属性/值」对，出现在开始标签的 `>` 之前。

标签分类：

- **双标签**：`<element> content </element>`，元素 = 内容 + 标记。
- **单标签（空元素）**：`<标签名/>` 或 `<标签名>`，如 `<br>`、`<hr>`、`<img>`、`<input>`。

注释：`<!-- 注释信息 -->`（`<!--` 内部不能插空格；浏览器渲染时忽略）。

## 五、文档结构与头部元素

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta name="keywords" content="Web,前端,HTML5">
  <meta name="description" content="页面描述">
  <title>我的第一个网页</title>
  <link rel="stylesheet" type="text/css" href="mystyle.css">
  <style>h1 { color: red; }</style>
  <script>document.write("Hello World!")</script>
</head>
<body>
  我的第一个网页
</body>
</html>
```

| 部分 | 说明 |
| --- | --- |
| `<!DOCTYPE html>` | 告诉浏览器按什么规范解析（HTML5 声明） |
| `<html>` | 根元素，整个文档都包在里面 |
| `<head>` | 头部，包含文档描述性信息（不显示在页面上） |
| `<title>` | 必有；作用：提示网页内容、保存时默认文件名、收藏时默认名称 |
| `<meta>` | 元信息。`charset` 设编码；`name`（keywords/description/author/copyright）+ `content` |
| `<link>` | 链接外部样式表，只能放 `<head>` 中，可多次出现 |
| `<style>` | 内部样式表 |
| `<script>` | 客户端脚本，可内嵌或用 `src` 引入外部文件 |
| `<body>` | 主体，浏览器窗口上真正显示的内容 |

编码提醒：推荐 `utf-8`（包含全世界字符），`gb2312` 只含中文字符；**页面声明的编码必须与文件保存时的编码一致**。

## 六、常用基础标签

| 标签 | 作用 | 关键点 |
| --- | --- | --- |
| `<h1>`–`<h6>` | 标题，1 级最大 | 块元素，独占一行 |
| `<p align="left\|center\|right\|justify">` | 段落 | 浏览器自动在前后加空白，可用样式控制 |
| `<br>` | 换行 | 单标签，源代码回车无效时用它 |
| `<hr width size color align>` | 水平分隔线 | 单标签 |
| `<strong>` / `<em>` | 加粗 / 斜体（强调） | 行内元素 |
| `<font size="1-7" color face>` | 字体，size 默认 3，可用 +1/-1 | 已过时，考试仍会出现 |
| `<pre>` | 预格式化，保留空格与换行 | 常用于诗歌排版 |
| `<div>` | 分区，块级容器 | 可嵌套，常配合 id/class/style |
| `<span>` | 行内范围 | 没有固定格式，加样式才有效果 |

字符实体（容易出小填空）：

| 符号 | 实体 |
| --- | --- |
| 空格 | `&nbsp;` |
| `>` | `&gt;` |
| `<` | `&lt;` |
| `"` | `&quot;` |
| 版权 © | `&copy;` |

## 七、列表三兄弟（必考对比）

```html
<ol type="1|A|a|I|i" start="3">
  <li>有序列表项</li>
</ol>

<ul type="disc|circle|square">
  <li>无序列表项</li>
</ul>

<dl>
  <dt>项目名称</dt>
  <dd>项目说明</dd>
</dl>
```

| 类型 | 标签 | 项目符号 | 特性与用途 |
| --- | --- | --- | --- |
| 无序列表 | `ul` + `li` | 默认实心圆点 | 无顺序、平级；导航、侧边栏新闻、图文组合 |
| 有序列表 | `ol` + `li` | 默认数字 | 有顺序；试卷、问卷选项 |
| 自定义（描述）列表 | `dl` + `dt` + `dd` | 默认无标记 | 一个标题 + 一个或多个说明 |

共同点：`li` / `dt` / `dd` 都是**块元素，各自独占一行**。

## 八、表格

```html
<table border="1">
  <caption>学生信息表</caption>
  <thead><tr><th>班级</th><th>姓名</th></tr></thead>
  <tbody><tr><td>SE131</td><td>张三</td></tr></tbody>
  <tfoot><tr><td colspan="2">软件学院</td></tr></tfoot>
</table>
```

| 标签 | 作用 |
| --- | --- |
| `table` | 定义表格整体 |
| `caption` | 表格标题，每个表格只能有一个 |
| `tr` | 行 |
| `td` | 单元格（包含在 `tr` 中） |
| `th` | 表头单元格 |
| `thead` / `tbody` / `tfoot` | 表头 / 主体 / 页脚 |
| `col` | 定义列的属性 |

常用属性：`border`、`cellpadding`（单元格边框与内容间距）、`cellspacing`（单元格之间的空白）、`width`、`height`、`align`、`bgcolor`；合并单元格用 `colspan`（跨列）与 `rowspan`（跨行）。

表格布局的两种典型应用：**图文布局**（图像和文本都当单元格内容，用跨行跨列拼）与**表单布局**（标题一列、输入框一列），还可做表格嵌套。

## 九、图像与图片列表

```html
<img src="images/pic.jpg" width="160" height="160" alt="替代文字" title="悬停提示">

<figure>
  <figcaption>黄浦江上的卢浦大桥</figcaption>
  <img src="images/6.jpg" width="350" height="234">
</figure>
```

`img` 属性：`src`（路径，必选）、`alt`（替代文字）、`title`（悬停提示）、`width` / `height`、`align`、`border`、`hspace` / `vspace`。常见图像格式：JPG、GIF、PNG、BMP。

`figure` 是独立流内容（图、图表、照片、代码），被删除不影响文档流；`figcaption` 是它的标题，必须是 figure 的**第一个或最后一个**子元素。

## 十、超链接

```html
<a href="http://www.baidu.com" target="_blank">百度</a>
<a href="a.html" target="_self">首页</a>
<a href="mailto:test@qq.com">联系我们</a>
<a name="marker">乙位置</a>   <!-- 创建跳转标记 -->
<a href="#marker">甲位置</a>  <!-- 创建跳转链接 -->
```

URL 构成：`protocol://machine.name[:port]/directory/filename`，协议有 http、file、ftp、news，端口默认 80。

`target` 取值：`_blank`（新窗口）、`_self`（当前窗口，默认）、`_parent`（上一层框架）、`_top`（顶层框架）。

超链接分三类：**页面间链接**、**锚链接**、**功能性链接**（mailto、QQ 等）。默认外观：未访问蓝色下划线、已访问紫色、活动红色。

路径写法：

- 绝对路径：完整路径，目标清晰，但文件移动后失效，适合引用站外资源。
- 相对路径：相对当前文件，适合站内资源。`.` 当前目录、`..` 上一层目录、`/` 根目录。

## 十一、块元素与行内元素

| 类型 | 特征 | 常见标签 |
| --- | --- | --- |
| 块元素 block | 无论内容多少都独占一行 | `p`、`h1`–`h6`、`div`、`ul`/`ol`/`dl`、`li`、`table`、`form` |
| 行内元素 inline | 宽度由内容撑开，左右可并排 | `a`、`strong`、`em`、`span`、`img`、`br`、`figure`/`figcaption` |

行内元素可以包含在块元素中，反过来不成立。

## 十二、音视频

```html
<video src="video/video.mp4" controls poster="cover.jpg" width="480"></video>
<audio controls autoplay loop>
  <source src="music.mp3" type="audio/mpeg">
  您的浏览器不支持 audio 标签。
</audio>
```

- `video` 支持 Ogg、MPEG 4、WebM；`audio` 支持 wav、mp3、ogg。
- 标签之间的字符串是浏览器不支持时的提示文字。
- 公共属性：`src`、`controls`、`autoplay`、`loop`、`start` / `end`、`loopstart` / `loopend`、`playcount`；`video` 额外有 `width` / `height` / `poster`。

## 十三、表单（本章最重）

```html
<form method="post" action="result.html">
  <p>名字：<input type="text" name="name" placeholder="请输入姓名"></p>
  <p>密码：<input type="password" name="pass" maxlength="20"></p>
  <p>性别：<input type="radio" name="gen" value="男" checked>男
           <input type="radio" name="gen" value="女">女</p>
  <p>兴趣：<input type="checkbox" name="hobby" value="sing" checked>唱歌</p>
  <select name="city"><option value="sz" selected>苏州</option></select>
  <textarea name="remark" cols="30" rows="5">默认文本</textarea>
  <input type="submit" value="提交"><input type="reset" value="重填">
</form>
```

`form` 两个核心属性：`action`（数据提交到哪里）、`method`（`get` / `post`，实际开发常用 post）。文件上传要 `enctype="multipart/form-data"`。

`input` 常用 type：

| type | 说明 | 备注 |
| --- | --- | --- |
| `text` | 单行文本框（默认值） | `size` 以字符为单位，`maxlength` 限长 |
| `password` | 密码框 | 输入内容显示为圆点 |
| `radio` | 单选按钮 | 同组 name 必须相同，必须给 value，`checked` 设默认 |
| `checkbox` | 复选框 | 可多选 |
| `file` | 文件域 | 需 form 设 multipart/form-data |
| `hidden` | 隐藏域 | 随表单提交但不可见 |
| `submit` / `reset` / `button` | 提交 / 重置 / 普通按钮 | `value` 是按钮文字 |
| `image` | 图片提交按钮 | 虽不是 submit，仍具备提交功能 |

其他表单元素：`select` + `option`（列表框，`selected` 默认选中）、`textarea`（多行文本，`cols` / `rows`）、`label`（`for` 关联）。

只读与禁用：`readonly`（可提交，不可改）、`disabled`（不可用且不提交）。HTML5 规定布尔属性可省略属性值。

### HTML5 新增

- 属性：`autocomplete="on|off"`（适用 form 及 text/search/url/email/password/range/color 等）、`novalidate`（提交时跳过校验）。
- 表单控件：`datalist`（配合 `input list` 做下拉候选）、`keygen`（密钥对生成，提交时产生私钥/公钥）、`email`、`url`、`number`（`min`/`max`/`step`）、`range`（滑块）。
- 初级验证：`placeholder`（提示，输入后消失）、`required`（必填）、`pattern`（正则，如 `^1[358]\d{9}`）。

> 表单验证的好处：**减轻服务器压力** + **保证数据的可行性与安全性**。

## 十四、iframe 与 HTML5 结构元素

```html
<iframe name="mainFrame" src="subframe/the_second.html"></iframe>
<a href="subframe/the_second.html" target="mainFrame">下边显示第二页</a>
```

实现框架内跳转的两个条件：被打开的框架加 `name`；超链接的 `target` 指向该框架名。`src` 也可以直接指向线上网页（如 baidu.com）。

| 结构元素 | 含义 |
| --- | --- |
| `header` | 头部区域（页面或某一块区域） |
| `footer` | 脚部区域 |
| `section` | 页面中一块独立区域 |
| `article` | 独立的文章内容 |
| `aside` | 相关内容或应用，常用于侧边栏 |
| `nav` | 导航类辅助内容 |

## 十五、W3C 与四种经典块状结构

W3C 标准：结构化标准（XHTML、XML）、表现标准（CSS）、行为标准（DOM、ECMAScript）。提倡内容与结构分离、结构语义化。

实际开发常用的四种块状结构，常以填空或简答出现：

| 结构 | 典型场合 |
| --- | --- |
| `div` → `ul`(ol) → `li` | 分类导航、菜单 |
| `div` → `dl` → `dt` → `dd` | 图文混编 |
| `table` → `tr` → `td` | 规整数据的显示 |
| `form` → `table` → `tr` → `td` | 表单布局 |

---

## 考点自测

1. `<!DOCTYPE html>` 的作用是什么？
2. `<title>` 有哪三个作用？
3. 有序列表、无序列表、自定义列表分别用什么标签？各自项目符号是什么？
4. 合并单元格的 `colspan` 与 `rowspan` 分别是什么含义？
5. `readonly` 与 `disabled` 的区别？
6. 表单验证的三个 HTML5 属性是什么？验证有什么好处？
7. 行内元素和块元素各举三个例子。

> 参考：1. 告诉浏览器用 HTML5 规范解析文档。2. 提示网页内容、保存时默认文件名、收藏时默认名称。3. `ol`+`li` 数字；`ul`+`li` 实心圆点；`dl`+`dt`+`dd` 无标记。4. `colspan` 跨列（水平合并），`rowspan` 跨行（垂直合并）。5. `readonly` 只读但会提交，`disabled` 禁用且不提交。6. `placeholder`、`required`、`pattern`；减轻服务器压力、保证数据可行性与安全性。7. 行内：`a`、`span`、`img`、`strong`；块：`div`、`p`、`h1`、`li`。
