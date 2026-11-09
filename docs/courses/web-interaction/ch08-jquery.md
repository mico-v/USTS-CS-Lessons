# 第 8 章 jQuery 基础

> jQuery 这一章不要求你从零搭框架，只考“会不会用”：`$()` 的语法、`ready()` 与 `onload` 的区别、两套对象的互转、选择器分类与常用过滤选择器。

---

## 一、jQuery 是什么

- 随着 JavaScript、CSS、Ajax 技术发展，开发者把常用功能封装起来供别人调用，这些封装好的组件就是 **JavaScript 库**。
- jQuery 是兼容多浏览器的 JavaScript 库，是对 JavaScript 对象和函数的封装，由美国人 **John Resig 于 2006 年**创建。
- 它简化了 HTML 文档遍历、事件处理、动画和 Ajax 交互。
- 设计思想：**write less, do more**。

jQuery 能做什么：访问和操作 DOM 元素、控制页面样式、处理页面事件、扩展插件、与 Ajax 结合。

> 关键一句：jQuery 能做的 JavaScript 也都能做，但用 jQuery 能大幅提高开发效率。

### 1.1 优势（简答常考，记 6～8 条即可）

轻量级、强大的选择器、出色的 DOM 操作封装、可靠的事件处理机制、完善的 Ajax、不污染顶级变量、出色的浏览器兼容性（兼容到 IE6）、链式操作、行为层与结构层分离、丰富插件、完善文档、开源。

### 1.2 引入

```html
<script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
```

| 文件 | 大小 | 用途 |
| --- | --- | --- |
| `jquery-3.x.js`（开发版） | 约 268KB | 完整无压缩，便于调试、学习 |
| `jquery-3.x.min.js`（发布版） | 约 91KB（3.3.1 min 约 85KB） | 压缩过，用于生产 |

## 二、语法结构与 ready

```javascript
$(selector).action();
```

三段含义：`$()` 是**工厂函数**，把 DOM 对象转成 jQuery 对象；`selector` 是**选择器**，取到要操作的元素；`.action()` 是**方法**。

```javascript
$(document).ready(function() {
  alert("我的第一个 jQuery 程序!");
});

// 简化写法
$(function() {
  /* ... */
});
```

`$(document).ready()` 与 `window.onload` 的三点区别（必考）：

| 对比项 | `window.onload` | `$(document).ready()` |
| --- | --- | --- |
| 执行时机 | 网页中所有内容（图片、flash、视频）加载完才执行 | DOM 结构绘制完就执行，图片等可能还没加载 |
| 编写个数 | 同一页面只能写一个，后面的会覆盖前面的 | 可以写多个，全部执行 |
| 简化写法 | 无 | `$(function(){})` |

## 三、操作页面元素

```javascript
$("p:first").addClass("intro");           // 加类名
$("p").css("color", "white");           // 设置一个样式
$("p").css({ "color":"white", "font-size":"20px" });  // 设置多个
$("p").hide();   $("p").show();          // 隐藏 / 显示
$("p").hide().show();                    // 链式
```

## 四、代码风格

- `$` 等同于 `jQuery`：`$(document).ready()` = `jQuery(document).ready()`。
- **链式操作**：同一对象的多步操作连续写，如 `$("h2").css(...).next().css(...)`。
- **隐式迭代**：`$("li").css(...)` 会自动对集合里每个元素执行一遍，不需要写 for 循环。

## 五、DOM 对象与 jQuery 对象

| | DOM 对象 | jQuery 对象 |
| --- | --- | --- |
| 获取方式 | `document.getElementById("title")` | `$("#title")` |
| 典型属性/方法 | `obj.innerHTML` | `$obj.html()` |
| 说明 | 原生节点对象 | 包装 DOM 对象后的对象，能用 jQuery 方法 |

> 两套方法**不能混用**：`$obj.innerHTML` 无效，`obj.html()` 也无效。

互相转换：

```javascript
// DOM → jQuery：用 $() 包装
var obj = document.getElementById("title");
var $obj = $(obj);
$obj.html("转换成功现在是 jQuery 对象");

// jQuery → DOM：用下标或 get()
var $div = $("div");
var div1 = $div[0];        // 或 $div.get(0)
div1.innerHTML = "转换成功现在是 DOM 对象！";
```

习惯约定：jQuery 对象变量名以 `$` 开头；事件里常用的 `$(this)` 中 `this` 是触发事件的 DOM 对象。

## 六、选择器

jQuery 选择器类似 CSS 选择器，必须放在 `$()` 里，返回 jQuery 对象。

### 6.1 基本选择器

| 名称 | 语法 | 示例 |
| --- | --- | --- |
| 标签 | `element` | `$("h2")` |
| 类 | `.class` | `$(".title")` |
| ID | `#id` | `$("#title")` |
| 并集 | `s1, s2, ...` | `$("div,p,.title")` |
| 全局 | `*` | `$("*")` |

### 6.2 层次选择器

| 名称 | 语法 | 含义 | 示例 |
| --- | --- | --- | --- |
| 后代 | `ancestor descendant` | 所有后代（子子孙孙） | `$("#menu span")` |
| 子 | `parent>child` | 直属子元素（儿子） | `$("#menu>span")` |
| 相邻 | `prev+next` | 紧邻的下一个同辈 | `$("h2+dl")` |
| 同辈 | `prev~siblings` | 之后所有同辈 | `$("h2~dl")` |

### 6.3 基本过滤选择器

| 语法 | 说明 | 示例 |
| --- | --- | --- |
| `:first` / `:last` | 第一个 / 最后一个 | `$("li:first")` |
| `:not(sel)` | 排除匹配的元素 | `$("li:not(.three)")` |
| `:even` / `:odd` | 索引为偶数 / 奇数（**索引从 0 开始**） | `$("li:odd")` |
| `:eq(i)` / `:gt(i)` / `:lt(i)` | 索引等于 / 大于 / 小于 i | `$("li:eq(1)")` |
| `:header` | 所有标题元素 h1～h6 | `$(":header")` |
| `:focus` | 当前获取焦点的元素 | `$(":focus")` |
| `:animated` | 正在执行动画的元素 | `$(":animated")` |

### 6.4 可见性过滤

| 语法 | 说明 |
| --- | --- |
| `:visible` | 所有可见元素 |
| `:hidden` | 所有隐藏元素 |

注意：`:hidden` 不只指 `display:none`，还包括文本隐藏域（`<input type="hidden">`）和 `visibility:hidden` 的元素。

### 6.5 属性过滤

| 语法 | 说明 |
| --- | --- |
| `[attr]` | 拥有该属性 |
| `[attr=value]` | 属性值等于 value |
| `[attr!=value]` | 属性值不等于 value（**没有该属性的元素也会被选中**） |
| `[attr^=value]` | 以 value 开头 |
| `[attr$=value]` | 以 value 结尾 |
| `[attr*=value]` | 包含 value |
| `[attr1][attr2]` | 复合条件，逐步缩小范围 |

### 6.6 子元素过滤与表单选择器

| 语法 | 说明 |
| --- | --- |
| `:nth-child(index/even/odd)` | 每个父元素下第 index 个子元素（**index 从 1 开始**，与 `eq` 不同） |
| `:first-child` / `:last-child` | 每个父元素的第一个 / 最后一个子元素 |
| `:only-child` | 作为父元素唯一子元素的元素 |

| 表单选择器 | 说明 |
| --- | --- |
| `:input` | 所有 input、textarea、select、button |
| `:text` / `:password` | 单行文本框 / 密码框 |
| `:radio` / `:checkbox` | 单选框 / 复选框 |
| `:submit` / `:reset` / `:button` / `:image` / `:file` | 对应类型按钮与文件域 |
| `:hidden` | 所有不可见元素 |

```javascript
// 统计表单内控件数量、给提交按钮换色
$("#form :input").length;
$("#form :submit").css("background-color", "red");
```

## 七、两个坑

1. **特殊字符要转义**：`id="id#a"` 写成 `$("#id\\#a")`，`id="id[2]"` 写成 `$("#id\\[2\\]")`。
2. **选择器里的空格有语义**：
   - `$(".test :hidden")` → class 为 test 的**元素内部的**隐藏元素。
   - `$(".test:hidden")` → 隐藏的、class 为 test 的**元素本身**。

---

## 考点自测

1. jQuery 的创始人、创建年份和设计思想？
2. `$(document).ready()` 与 `window.onload` 的三点区别？
3. DOM 对象与 jQuery 对象如何互相转换？各自用什么方式取值？
4. `:eq(1)` 与 `:nth-child(2)` 的索引基准有何不同？
5. `$(".test :hidden")` 与 `$(".test:hidden")` 选中的元素有何不同？
6. 开发版与发布版 jQuery 文件的大小与用途？
7. 什么是链式操作和隐式迭代？

> 参考：1. John Resig，2006 年，write less, do more。2. 执行时机（全部加载完 vs DOM 结构就绪）、可写个数（一个 vs 多个）、简化写法（无 vs `$(function(){})`）。3. DOM→jQuery 用 `$()` 包装，$(dom)；jQuery→DOM 用 `$obj[0]` 或 `$obj.get(0)`；前者用 `innerHTML`，后者用 `.html()`。4. `:eq()` 索引从 0 开始且全局只匹配一个；`:nth-child()` 从 1 开始且对每个父元素分别匹配。5. 前者是 test 元素内部的隐藏元素，后者是自身隐藏的 test 元素。6. 开发版约 268KB 用于调试学习，发布版约 91KB（3.3.1 约 85KB）压缩后用于生产。7. 链式操作是对同一对象连续调用方法；隐式迭代是 jQuery 自动遍历集合中的每个元素执行同一操作。
