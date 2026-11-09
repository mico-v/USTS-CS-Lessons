# 第 5 章 JavaScript 基础

> 这章的考法几乎全是「看代码写结果」。必须掌握：变量与数据类型、`typeof`、`==` 与 `===`、`parseInt`、流程控制、函数与作用域。

---

## 一、JavaScript 是什么

> JavaScript 是一种基于对象和事件驱动的、并具有安全性能的**脚本语言**。

脚本语言的定义：嵌入在网页文件中的一种具有程序逻辑的语言；由集成在浏览器里的**脚本引擎**解释执行；浏览器打开 HTML 文档时从头到尾逐句解释，读到可执行语句就立即执行。

| 对比 | 非脚本语言 | 脚本语言 |
| --- | --- | --- |
| 运行方式 | 需编译、链接，生成可执行文件 | 依赖解释器，被调用时解释执行 |
| 优点 | 执行效率高 | 省掉“编写→编译→链接→运行”，简单易学，语法宽松 |
| 缺点 | 开发迭代慢 | 执行效率不如编译型 |

### 1.1 六大特点

1. 脚本语言（解释型，与 HTML 结合好）。
2. 基于对象。
3. 简单性（开发环境要求不高）。
4. 安全性好。
5. 动态性（客户端、事件驱动）。
6. 跨平台性（依赖浏览器，与操作系统无关）。

### 1.2 三大部分

| 组成 | 作用 |
| --- | --- |
| ECMAScript | 核心，描述语言的语法和基本对象 |
| DOM（文档对象模型） | HTML 和 XML 的应用程序接口（API） |
| BOM（浏览器对象模型） | 对浏览器窗口进行访问和操作 |

### 1.3 JavaScript 与 Java 的区别（简答必背）

| JavaScript | Java |
| --- | --- |
| 不编译，客户端解释执行 | 编译成 class 文件 |
| 基于对象，没有类和继承，有内置对象 | 完全面向对象，程序由继承的类组成 |
| 嵌入 HTML 文件 | 生成 Applet，从 HTML 中调用 |
| 不用定义变量类型（弱类型） | 必须定义变量类型 |
| 动态绑定，运行时检查 | 静态绑定，编译时检查 |

## 二、引入方式

```html
<!-- 1. 内嵌 -->
<script type="text/javascript">
  document.write("Hello World！");  // 向网页输出内容
</script>

<!-- 2. 外部文件 -->
<script src="test2.js" type="text/javascript"></script>

<!-- 3. 行内（事件属性里） -->
<input type="button" value="弹出消息框" onclick="alert('Hello World！')">
```

`<script>` 可以出现在文档任何位置，只要保证代码在被使用前已加载到内存。

执行原理：浏览器输入 URL 发请求 → 服务器返回含 JavaScript 的页面 → 浏览器解析 HTML 标签和 JavaScript 并执行。

## 三、变量

```javascript
var width;            // 先声明
width = 5;            // 再赋值
var catName = "皮皮"; // 声明同时赋值
var x, y, z = 10;     // 只有 z 是 10，x、y 为 undefined
width = 5;            // 不声明直接赋值（不推荐，容易出错且难排错）
```

JavaScript 是**弱类型**语言：定义时不指明类型，同一个变量可以先后赋不同类型的值。

## 四、数据类型与 typeof

| 类型 | 说明 | 例子 |
| --- | --- | --- |
| number | 不区分整型与浮点型，内部都是浮点型 | `var i=23; var j=23.0;` |
| string | 单引号或双引号括起来的 0 个或多个字符 | `var s="abc";` |
| boolean | 只有 true / false | `var r=true;` |
| null | 表示“无值”，**不等于 0，也不等于空字符串** | `var n=null;` |
| undefined | 变量已声明未赋值，或访问了不存在的对象属性 | `var w;` |

`typeof` 返回值（实验题原句）：

| 表达式 | 返回 |
| --- | --- |
| `typeof(width)`（已声明未赋值） | `undefined` |
| `typeof(height)`（数值） | `number` |
| `typeof(name)`（字符串） | `string` |
| `typeof(true)` | `boolean` |
| `typeof(null)` | `object`（历史遗留，必考坑） |
| `typeof(new Date())` | `object` |

> 记住两个反常识点：`typeof null === "object"`；`null` 既不等于 `0` 也不等于 `""`。

## 五、注释与运算符

```javascript
// 单行注释
/* 多行注释
   可以跨行 */
```

| 类型 | 运算符 |
| --- | --- |
| 算术 | `+ - * / % ++ --` |
| 赋值 | `= += -=`（还有 `*= /= %=`） |
| 比较 | `> < >= <= == != === !==` |
| 逻辑 | `&& \|\| !` |

### `==` 与 `===` 的区别

- `==` 只比较值，会做类型转换：`"5" == 5` 为 `true`。
- `===` 同时比较值与类型：`"5" === 5` 为 `false`。
- `!=` / `!==` 同理。

拼接时的坑：`prompt()` 返回的是**字符串**，所以 `"3" + 4` 得 `"34"`，要做加法必须 `parseInt`。

## 六、常用输入输出

```javascript
alert("提示信息");                            // 警示框，无返回值
document.write("<h1>Hello</h1>");             // 向页面写内容
var j = prompt("请输入次数：", "");           // 输入框，返回字符串；取消返回 null
```

## 七、流程控制

```javascript
// 分支
if (条件) { ... } else { ... }
switch (表达式) {
  case 常量1: 语句1; break;
  case 常量2: 语句2; break;
  default:    语句3;
}

// 循环
while (条件) { ... }
do { ... } while (条件);          // 至少执行一次
for (初始化; 条件; 增量) { ... }
for (var property in object) { ... }  // 遍历对象属性或数组下标
```

`for...in` 的 `property` 每轮被赋成对象的下一个属性名；对象是数组时，`property` 就是下标。

转移语句：

- `break`：终止 `switch` 的 case 序列或跳出整个循环。
- `continue`：跳过本次循环剩余语句，进入下一次迭代。

读代码示例（经典）：`for(i=0;i<=5;i++){ if(i==3) continue; document.write(i); }` 输出 `0 1 2 4 5`；把 `continue` 换成 `break` 则只输出 `0 1 2`。

## 八、函数

分类：**系统函数**与**自定义函数**。函数类似 Java 的方法，但不用定义在类里，直接用。

常用系统函数：

| 函数 | 作用 |
| --- | --- |
| `parseInt("86")` | 字符串转整数 → 86 |
| `parseFloat("34.45")` | 字符串转浮点 → 34.45 |
| `isNaN(x)` | 判断参数是否为非数字（`isNaN("abc")` 为 true） |

```javascript
// 定义
function study(count) {          // 括号里是形参，可有可无
  for (var i = 0; i < count; i++) {
    document.write("<h3>Welcome JavaScript</h3>");
  }
}

// 调用：一般和表单元素的事件一起用
// <input type="button" value="显示" onclick="study(3)">
```

返回值用 `return`，可选。

## 九、变量的作用域（读代码必考）

- 在函数外用 `var` 声明的是**全局变量**。
- 在函数内用 `var` 声明的是**局部变量**，函数外访问不到。
- 函数内部不使用 `var` 赋值，会创建（或覆盖）全局变量。

```javascript
var i = 20;                  // 全局
document.body.onload = second();

function first() {
  var i = 5;                 // 局部，只在函数内覆盖全局
  for (var j = 0; j < i; j++) document.write("&nbsp;&nbsp;&nbsp;&nbsp;" + j);
}

function second() {
  var t = prompt("输入一个数", "");   // 输入 67
  if (t > i) document.write(t);      // 67 > 20 → 输出 67
  else document.write(i);
  first();                           // 输出 0 1 2 3 4
}
```

结果：先输出 `67`，再输出 `0 1 2 3 4`（每个前面 4 个 `&nbsp;`）。

---

## 考点自测

1. JavaScript 由哪三部分组成？各负责什么？
2. 脚本语言与非脚本语言的区别？脚本语言的优缺点？
3. 说出 `typeof` 的六个可能返回值，`typeof null` 返回什么？
4. `==` 与 `===` 的区别，`"5" == 5` 和 `"5" === 5` 分别是什么？
5. `prompt("","")` 输入的 `"3"` 与 `4` 相加得多少？怎么改才对？
6. `break` 与 `continue` 的区别？
7. 全局变量与局部变量如何区分？

> 参考：1. ECMAScript（语法与基本对象）、DOM（HTML/XML 的 API）、BOM（操作浏览器窗口）。2. 非脚本语言要编译链接成可执行文件，脚本语言由解释器解释执行；优点是省掉编译链接、简单易学，缺点是执行效率低。3. undefined / string / boolean / number / object / function；`typeof null` 是 `"object"`。4. `==` 只比值（会转换类型），`===` 同时比类型；前者 true，后者 false。5. 得 `"34"`（字符串拼接）；先用 `parseInt(op)` 转成数字。6. break 跳出整个循环或 switch，continue 只跳过本次迭代。7. 函数外用 var 声明的是全局，函数内用 var 声明的是局部，内部不用 var 赋值会污染全局。
