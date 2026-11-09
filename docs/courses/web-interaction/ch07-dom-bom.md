# 第 7 章 JavaScript DOM 与 BOM

> 这章是“用 JS 操作页面”的入口。考点集中在对象层次图、`document` 的三个选取方法、`confirm/alert/prompt` 的区别、`location` 与 `history`、表单对象访问语法。

---

## 一、DOM 是什么

> DOM（Document Object Model，文档对象模型）是 W3C 推荐的处理可扩展标志语言的标准编程接口，是针对 HTML 和 XML 文档的一个 API，允许开发人员添加、移除和修改页面的某一部分。

DOM 把文档描绘成一棵**层次化的节点树**，树中每个对象称为一个节点。

```
document（文档节点，每个文档的根节点）
 └─ <html>（文档元素，文档节点的唯一子节点）
     ├─ <head>
     │   └─ <title> → 文本
     └─ <body>
         ├─ <img>
         ├─ <h1> → 文本
         └─ <p>  → 文本
```

## 二、JavaScript 的三部分与 BOM

| 组成 | 作用 |
| --- | --- |
| ECMAScript | 语法与基本对象 |
| DOM | HTML / XML 的应用程序接口 |
| BOM | 独立于内容、可与浏览器窗口交互的对象结构 |

BOM 的核心是 `window`，它同时也是其他浏览器对象的顶层容器：

| 对象 | 作用 |
| --- | --- |
| `window` | 代表浏览器窗口，层次图顶层；框架集中每个框架各有一个 window |
| `document` | 代表整个 HTML 文档，访问所有 HTML 元素的入口，位于核心地位 |
| `location` | 当前 URL 的信息 |
| `navigator` | 当前浏览器的信息 |
| `history` | 浏览器过去访问的 URL 信息 |

BOM 能实现：弹出新窗口；移动、关闭窗口、调整大小；页面的前进后退。

## 三、window 对象

常用属性：

```javascript
window.location = "http://www.xxxx.cn";   // 跳转
window.history;    // 访问过的 URL 信息
window.location;   // 当前 URL 信息
```

常用方法：

| 方法 | 说明 |
| --- | --- |
| `alert(msg)` | 警示框，一个参数，只显示消息并带“确定”，无返回值 |
| `prompt(msg, default)` | 输入框，两个参数；确定返回输入值，取消返回 `null` |
| `confirm(msg)` | 确认框，一个参数；确定返回 `true`，取消返回 `false`，常配 `if-else` |
| `open(url, name, features)` | 打开新窗口 |
| `close()` | 关闭窗口 |
| `setTimeout()` / `setInterval()` | 定时器 |

三者对比（必考）：

| | 参数个数 | 返回值 | 典型用途 |
| --- | --- | --- | --- |
| `alert` | 1 | 无 | 提示信息 |
| `prompt` | 2 | 输入值 / null | 收集用户输入 |
| `confirm` | 1 | true / false | 二次确认（如删除） |

```javascript
var flag = confirm("确认要删除此条信息吗？");
if (flag == true) alert("删除成功！");
else alert("你取消了删除");
```

`open()` 的窗口特征参数：`height`、`width`、`left`、`top`、`toolbar`、`scrollbars`、`location`、`status`、`menubar`、`resizable`、`titlebar`、`fullscreen`，取值 `yes|no|1|0`（`fullscreen` 默认 no）。

常用事件：`onload`、`onclick`、`onmouseover`、`onkeydown`、`onchange`。

## 四、document 对象

属性：

| 属性 | 说明 |
| --- | --- |
| `document.referrer` | 载入当前文档的 URL（判断是否从链接进入） |
| `document.URL` | 当前文档的 URL |

```javascript
// 防盗链式的判断：不是从授权页面进入就 5 秒后跳登录
var preUrl = document.referrer;
if (preUrl == "") {
  document.write("您不是从授权页面进入，5秒后将自动跳转到登录页面");
  setTimeout("javascript:location.href='login.html'", 5000);
}
```

方法：

| 方法 | 返回 | 说明 |
| --- | --- | --- |
| `getElementById("id")` | 单个元素 | id 唯一，返回第一个匹配 |
| `getElementsByName("name")` | 集合（类数组） | name 可重复，需 `for` 遍历 |
| `getElementsByTagName("tag")` | 集合（类数组） | 按标签名取 |
| `write()` | — | 向文档写文本 / HTML / JS 代码 |

```javascript
// 改内容：innerHTML 是常用手段
document.getElementById("node").innerHTML = "真香";

// 遍历同 name 的元素
var aInput = document.getElementsByName("season");
var sStr = "";
for (var i = 0; i < aInput.length; i++) sStr += aInput[i].value + "<br />";
document.getElementById("s").innerHTML = sStr;
```

> 选择题提醒：`getElementById` 返回**一个**对象，`getElementsByName` / `getElementsByTagName` 返回**集合**（要用 `length` 与下标）。

## 五、history 与 location

| history 方法 | 等价操作 |
| --- | --- |
| `history.back()` | 浏览器“后退”，等价 `history.go(-1)` |
| `history.forward()` | 浏览器“前进”，等价 `history.go(1)` |
| `history.go(n)` | 跳到历史中第 n 个 URL |

| location 属性/方法 | 说明 |
| --- | --- |
| `host` | 主机名 + 端口号 |
| `hostname` | 主机名 |
| `href` | 完整 URL（赋值即跳转） |
| `reload()` | 重新加载当前文档 |
| `replace()` | 用新文档替换当前文档（不产生历史记录） |

```html
<a href="javascript:location='jQuery.html'">jQuery</a>
<a href="javascript:location.reload()">刷新本页</a>
```

## 六、表单对象

表单是 `document` 的子对象，两种访问语法：

```javascript
document.表单名称.属性 / 方法(参数)
document.forms[索引].属性 / 方法(参数)
document.forms[索引].elements[索引].属性
document.表单名称.元素名称.属性
```

| 表单属性 | 说明 |
| --- | --- |
| `action` | 提交目标 |
| `method` | get / post |
| `name` / `id` | 名称 / id |
| `length` | 表单中元素数目 |

| 表单方法 | 说明 |
| --- | --- |
| `submit()` | 提交 |
| `reset()` | 重置 |
| `handleEvent()` | 使事件处理程序生效 |

表单元素的属性：`value`、`defaultValue`、`name`、`type`、`form`（所属表单）。

```javascript
document.forms[0].elements[0].value;
document.login.username.value;
```

## 七、DOM 编程常见套路

### 7.1 显示与隐藏

| 方式 | 效果 |
| --- | --- |
| `visibility: visible / hidden` | 隐藏但**保留占位** |
| `display: block / none` | 不显示且**不占位** |

```javascript
document.getElementById("box").style.display = "none";
```

### 7.2 树形菜单

思路：节点加/去 class 控制展开收起，或用 `display` 切换子列表；展开时修改图片 `src` 做加减号。

### 7.3 复选框全选

思路：取全选框的 `checked` 值，循环给每个子复选框赋同一个值。

```javascript
function checkAll(obj) {
  var items = document.getElementsByName("item");
  for (var i = 0; i < items.length; i++) items[i].checked = obj.checked;
}
```

---

## 考点自测

1. DOM 的英文全称与定义？它把文档描述成什么结构？
2. BOM 中位于层次图顶层的是哪个对象？`document` 处于什么地位？
3. `alert`、`prompt`、`confirm` 的参数与返回值分别是什么？
4. `getElementById` 与 `getElementsByName` 的返回值有何不同？
5. `history.back()` 与 `history.go(-1)` 的关系？
6. `location.href` 与 `location.reload()` 各做什么？
7. `visibility:hidden` 与 `display:none` 的区别？

> 参考：1. Document Object Model，W3C 推荐的处理可扩展标志语言的标准编程接口，把文档描绘成层次化的节点树。2. `window` 在顶层；`document` 位于核心地位，是访问所有 HTML 元素的入口。3. alert 1 个参数无返回值；prompt 2 个参数返回输入值或 null；confirm 1 个参数返回 true / false。4. 前者返回单个元素，后者返回集合（类数组）。5. 完全等价。6. href 设置或返回完整 URL（赋值即跳转），reload 重新加载当前文档。7. visibility 隐藏但保留占位，display:none 不显示也不占位。
