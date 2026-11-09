# 考前速查表与易错点

> 最后一晚只看这一页。前半部分是表格速查，后半部分是历年最容易错的点。

---

## 一、选择器与优先级

CSS 权重：行内 1000 > ID 100 > 类/伪类/属性 10 > 标签/伪元素 1 > 通配 0。同权重后写的赢，`!important` 最大。

引入方式优先级：行内样式 > 内部样式表 > 外部样式表（就近原则）。

| 玩意儿 | 写法 | 一句话 |
| --- | --- | --- |
| 后代 / 子代 | `E F` / `E > F` | 所有后代 / 只管直系儿子 |
| 相邻 / 同辈兄弟 | `E + F` / `E ~ F` | 紧挨着的一个 / 后面所有同辈 |
| 属性开头 / 结尾 / 包含 | `[a^=v]` / `[a$=v]` / `[a*=v]` | ^ 开头、$ 结尾、* 包含 |
| 结构伪类 | `E:nth-child(n)` / `E:nth-of-type(n)` | 不分类型数 / 先分类型再数 |
| 超链接伪类顺序 | `link → visited → hover → active` | LoVe HAte |

## 二、HTML 标签速查

| 需求 | 标签 |
| --- | --- |
| 段落 / 换行 / 水平线 | `p` / `br` / `hr` |
| 标题 | `h1`–`h6` |
| 加粗 / 斜体 | `strong` / `em` |
| 保留空格换行 | `pre` |
| 区块容器 / 行内容器 | `div`（块） / `span`（行内） |
| 无序 / 有序 / 描述列表 | `ul`+`li` / `ol`+`li` / `dl`+`dt`+`dd` |
| 表格 | `table`+`tr`+`td`（`th`、`caption`、`thead/tbody/tfoot`） |
| 合并单元格 | `colspan`（跨列） / `rowspan`（跨行） |
| 图片 / 图片列表 | `img` / `figure`+`figcaption` |
| 超链接 | `a`, `href` + `target` |
| 视频 / 音频 | `video` / `audio`，配 `controls` |
| 框架 | `iframe`, `name` + `target` 联动 |
| 语义结构 | `header`、`footer`、`section`、`article`、`aside`、`nav` |

`input` 的 type：`text`（默认）、`password`、`radio`、`checkbox`、`file`、`hidden`、`submit`、`reset`、`button`、`image`；HTML5 新增 `email`、`url`、`number`、`range`、`date`、`color`。

HTML5 表单验证三件套：`placeholder`、`required`、`pattern`；`autocomplete`、`novalidate`。

## 三、盒子与定位

```
实际宽度 = width + padding×2 + border×2
总占位  = 实际宽度 + margin×2
```

margin / padding 简写按顺时针：上 右 下 左；两值 = 上下、左右；三值 = 上、左右、下。

| 定位 | 基准 | 脱离文档流 | 保留原位 |
| --- | --- | --- | --- |
| static | — | 否 | — |
| relative | 自身原位置 | 否 | 是 |
| absolute | 最近的已定位祖先（无则窗口） | 是 | 否 |
| fixed | 浏览器窗口 | 是 | 否 |

`margin: 0 auto` 居中 = 块元素 + 固定宽度。圆形 = 宽高相等 + `border-radius: 50%`。

## 四、JavaScript 速查

| 知识点 | 结论 |
| --- | --- |
| `typeof` | `undefined`/`string`/`boolean`/`number`/`object`/`function`；`typeof null === "object"` |
| `==` vs `===` | 只比值 / 同时比类型；`"5"==5` 真，`"5"===5` 假 |
| `prompt()` | 返回**字符串**，做加法先 `parseInt` |
| `parseInt` / `parseFloat` / `isNaN` | 转整数 / 转浮点 / 判非数字 |
| `null` | 不等于 0，也不等于空字符串 |
| 作用域 | 函数内 `var` 是局部；不用 `var` 赋值会污染全局 |
| `Math.ceil(-25.5)` | -25（向上）；`floor(-25.5)` = -26；`round(-25.5)` = -26 |

对象方法备忘：

- Array：`length`（属性）、`concat`、`join`、`sort`、`pop`、`push`、`reverse`、`toString`。
- String：`charAt`、`concat`、`substring(start,stop)`（左闭右开）、`indexOf`/`lastIndexOf`（找不到返回 -1）、`split`、`toUpperCase`/`toLowerCase`、`replace`。
- Date：`getDate` 1～31、`getDay` 0～6、`getMonth` 0～11、`getFullYear`、`getHours/Minutes/Seconds`、`getTime`（毫秒）。
- 定时：`setTimeout` 一次 / `setInterval` 循环 / `clearTimeout` / `clearInterval`。

## 五、BOM 与 DOM 速查

| 对象 | 关键成员 |
| --- | --- |
| `window` | 顶层；`alert`/`prompt`/`confirm`/`open`/`close`/定时器 |
| `document` | `referrer`、`URL`、`getElementById`（单）、`getElementsByName` / `getElementsByTagName`（集合）、`write` |
| `history` | `back()`=`go(-1)`、`forward()`=`go(1)` |
| `location` | `href`（跳转）、`host`、`hostname`、`reload()`、`replace()` |
| 表单 | `document.forms[i].elements[j].value` 或 `document.表单名.元素名.value` |

`alert` 无返回值；`prompt` 返回输入值或 null；`confirm` 返回 true/false。

## 六、jQuery 速查

| 项目 | 结论 |
| --- | --- |
| 语法 | `$(selector).action()`，`$` = `jQuery` |
| ready vs onload | DOM 就绪即执行 / 全部加载完才执行；可写多个 / 只能一个 |
| DOM→jQuery | `$(domObj)` |
| jQuery→DOM | `$obj[0]` 或 `$obj.get(0)` |
| 取内容 | `$obj.html()` ↔ `dom.innerHTML` |
| 链式 / 隐式迭代 | 连续调用方法 / 自动遍历集合 |

## 七、高频易错点清单

1. `padding` 是内容与边框的距离，`margin` 是盒子与盒子之间的距离（别写反）。
2. `display:none` 不占位，`visibility:hidden` 保留占位。
3. `absolute` 的基准是**最近的已定位祖先**，不是父元素。
4. 只设 `position:absolute` 不设偏移量，元素停在原位置。
5. `clear` 只对块级元素有效，且不影响行内元素。
6. `rel="stylesheet"` 不能省，否则 `<link>` 不生效。
7. 超链接伪类顺序写错就有一条不生效。
8. `:nth-child` 索引从 1，`:eq` 从 0；`nth-child` 不分类型，`nth-of-type` 分类型。
9. `null` 既不等于 0 也不等于空串；`typeof null` 是 `object`。
10. `prompt` 拿到的是字符串，`"3"+4` 得 `"34"`。
11. `getMonth()` 从 0 开始，`getDay()` 0 是周日。
12. `substring(start, stop)` 左闭右开，`start > stop` 会自动交换。
13. `indexOf` 找不到返回 `-1`，不是 `0` 也不是 false。
14. `getElementById` 返回单个对象，`getElementsByName/TagName` 返回集合。
15. jQuery 对象与 DOM 对象的方法不能混用。
16. `$(document).ready()` 可以写多个，`window.onload` 后面的会覆盖前面的。
17. `:hidden` 包括 `display:none`、`visibility:hidden` 和 `type="hidden"` 的控件。
18. 选择器里多一个空格就变意思：`.a :hidden` ≠ `.a:hidden`。
19. HTML 文件命名不能有空格、`&` 等特殊符号，首页默认 `index.html`。
20. HTML 标签和属性名不区分大小写，但**属性值**（如 ID、文件名）通常区分。

## 八、简答题作答模板

- **Web 定义**：基于超文本和 HTTP 的、全球性的、动态交互的、跨平台的分布式图形信息系统。
- **结构/样式/行为**：HTML 描述结构（语义），CSS 描述样式（表现），JavaScript 描述行为（交互）。
- **W3C 标准**：结构化标准（XHTML、XML）+ 表现标准（CSS）+ 行为标准（DOM、ECMAScript）。
- **盒子模型**：内容 + 内边距（padding）+ 边框（border）+ 外边距（margin），总尺寸 = 内容宽高 + padding + border + margin。
- **开发流程**：需求分析 → 设计 → 制作（前台页面 / 代码书写 / 后台开发）→ 测试（功能/性能/可用性/安全性）→ 维护更新。
