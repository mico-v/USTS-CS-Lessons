# 第 6 章 JavaScript 对象

> JavaScript 是“基于对象”的语言，**万物皆对象**：对象拥有属性和方法。本章四个内置对象（Array / String / Date / Math）+ 自定义对象 + 事件，考法多为“给一段代码问输出”。

---

## 一、数组对象 Array

```javascript
// 三种创建方式
var fruit = new Array("apple", "orange", "peach", "banana");
var fruit = new Array(4);          // 指定长度
var fruit = ["apple", "banana"];  // 字面量（最常用）

// 访问与赋值
fruit[0] = "apple";
```

常用属性与方法：

| 名称 | 作用 | 备注 |
| --- | --- | --- |
| `length` | 元素的数目 | 属性，不是方法 |
| `concat()` | 连接两个或多个数组 | 返回**新数组**，原数组不变 |
| `join(sep)` | 用分隔符把元素拼成字符串 | `join("-")` → `a-b` |
| `sort()` | 排序 | 默认按字符串比较 |
| `pop()` | 删除并返回最后一个元素 | 改变原数组 |
| `push(x)` | 末尾添加，返回新长度 | 改变原数组 |
| `reverse()` | 颠倒元素顺序 | 改变原数组 |
| `toString()` | 转成字符串 | 默认用逗号分隔 |

```javascript
var ar1 = new Array("hello"), ar2 = new Array("world");
var ar3 = ar1.concat(ar2);        // hello,world
ar3.join(" ");                    // "hello world"
var len = ar3.push("!");          // len = 3
ar3.pop();                        // "!"
ar3.reverse();                    // world,hello
```

> 注意：`concat` 与 `join` 返回新值，`push` / `pop` / `reverse` 改原数组。选择题常拿这点做手脚。

## 二、字符串对象 String

两种创建：

```javascript
var str = "this is JavaScript";              // 直接赋值（基本类型，方法可用）
var strObj = new String("this is JavaScript"); // 对象方式
```

常用方法：

| 方法 | 作用 | 示例 |
| --- | --- | --- |
| `charAt(index)` | 返回指定位置的字符，索引从 0 开始 | `"abc".charAt(1)` → `b` |
| `concat(s1, s2)` | 连接字符串 | `"a".concat("b")` → `ab` |
| `substring(start, stop)` | 截取 `[start, stop)`，省略 stop 到末尾 | `"Made In China".substring(2,4)` → `de` |
| `indexOf(s, i)` | 从 i 开始找，返回**第一次出现**位置，找不到返回 -1 | `"made in china".indexOf("a")` → 1 |
| `lastIndexOf(s)` | 返回**最后一次出现**位置 | `"made in china".lastIndexOf("a")` → 12 |
| `split(sep, howmany)` | 按分隔符切成数组 | `"a,b".split(",")` → `["a","b"]` |
| `toUpperCase()` / `toLowerCase()` | 全部转大 / 小写 | `"Ab".toLowerCase()` → `ab` |
| `replace(old, new)` | 替换（默认只换第一个） | `"i am fine".replace("i","I")` |

`substring` 的两个细节：

- `stop` 是“最后一个字符的位置 + 1”，即**左闭右开**。
- 如果 `start > stop`，会自动交换两个参数：`"Made In Korea".substring(4,2)` 等于 `substring(2,4)` → `de`。
- `split("")` 用空串当分隔符，会把每个字符切开。

## 三、日期对象 Date

```javascript
var today = new Date();                            // 当前日期时间
var tdate = new Date("september 1,2013,14:58:12"); // 参数格式：MM DD,YYYY,hh:mm:ss
var d2    = new Date("2024/1/1 0:0:0");            // 斜杠格式也常见
```

| 方法 | 返回范围 |
| --- | --- |
| `getDate()` | 一个月中的第几天，1～31 |
| `getDay()` | 星期几，**0（周日）～6** |
| `getHours()` | 小时，0～23 |
| `getMinutes()` | 分钟，0～59 |
| `getSeconds()` | 秒，0～59 |
| `getMonth()` | 月份，**0～11**（用的时候记得 +1） |
| `getFullYear()` | 四位年份 |
| `getTime()` | 自 1970-01-01 起的毫秒数 |

时钟特效的核心三行：

```javascript
var today = new Date();
var hh = today.getHours(), mm = today.getMinutes(), ss = today.getSeconds();
document.getElementById("myclock").innerHTML = "现在是:" + hh + ":" + mm + ":" + ss;
```

倒计时思路（期末可能考）：两个日期 `getTime()` 相减得毫秒差 → `/1000` 得秒 → 用 `Math.floor` 逐级换算天、时、分、秒，取余用 `%`。

## 四、定时器与清除

| 函数 | 作用 |
| --- | --- |
| `setTimeout("fun()", ms)` | 等待 ms 毫秒后执行**一次** |
| `setInterval("fun()", ms)` | 每隔 ms 毫秒执行**一次**（循环） |
| `clearTimeout(id)` | 取消 setTimeout，传入其返回的 ID |
| `clearInterval(id)` | 取消 setInterval，传入其返回的 ID |

```javascript
var myTime = setTimeout("disptime()", 1000);   // 1 秒后执行一次
clearTimeout(myTime);

var timer = setInterval(disptime, 1000);       // 每秒执行
clearInterval(timer);
```

要实现多次调用，用 `setInterval`，或者在 `setTimeout` 调用的函数体内再调用一次 `setTimeout`。

## 五、数学对象 Math

属性（数学常数）：`Math.E`、`Math.LN2`、`Math.LN10`、`Math.PI`、`Math.SQRT2`、`Math.SQRT1_2`。

方法：

| 方法 | 作用 | 例 |
| --- | --- | --- |
| `ceil(x)` | 向上取整 | `Math.ceil(25.5)` → 26；`Math.ceil(-25.5)` → -25 |
| `floor(x)` | 向下取整 | `Math.floor(25.5)` → 25；`Math.floor(-25.5)` → -26 |
| `round(x)` | 四舍五入 | `Math.round(25.5)` → 26；`Math.round(-25.5)` → -26 |
| `random()` | 0～1 随机数 | `0.6273...` |
| `max(a,b)` / `min(a,b)` | 最大 / 最小 | `Math.max(10,20)` → 20 |
| `abs(x)` | 绝对值 | `Math.abs(-7)` → 7 |

随机取数组元素的标准写法（负数侧要小心）：

```javascript
var colors = ["红","黄","蓝","绿"];
var num = Math.floor(Math.random() * colors.length);
```

## 六、自定义对象

JavaScript 通过**函数 + this** 生成对象，用 `new` 实例化：

```javascript
function Student(name, number, eng, mat) {
  this.school = "苏州科技大学";   // 属性
  this.name = name;
  this.number = number;
  this.eng = eng;
  this.mat = mat;
}

var s1 = new Student("Jack.Ma", 8003117701, 80, 90);
document.write(s1.name + s1.eng);
```

在对象内设定方法：定义一个独立函数，再把它赋给 `this.某属性`：

```javascript
function display() {
  document.write(this.name + "的英语成绩：" + this.eng);
}

function Student(name, eng) {
  this.name = name;
  this.eng = eng;
  this.dsp = display;      // 方法就是“属性 = 函数”
}

new Student("张三", 90).dsp();
```

对象还可以作为另一个对象的属性（嵌套对象），这是后面学 JSON 的雏形。

## 七、事件

> 事件是指特定动作发生时产生的信号，比如敲键盘、点鼠标。

| 事件 | 触发时机 |
| --- | --- |
| `onload` / `onunload` | 页面或图像完成加载 / 关闭页面（写在 `<body>` 上） |
| `onclick` | 鼠标单击 |
| `onmouseover` / `onmouseout` | 鼠标移入 / 移出 |
| `onchange` | 域的内容改变（常用于 `select`、`text`） |
| `onkeydown` | 键盘按键按下 |

图片换图效果的标准写法：

```html
<a href="#" onclick="btn_click()"
   onmouseover="mouseOver()" onmouseout="mouseOut()">
  <img src="images/0.jpg" id="b1" width="600">
</a>

<script>
function mouseOver(){ document.getElementById('b1').src = "images/1.jpg"; }
function mouseOut(){ document.getElementById('b1').src = "images/0.jpg"; }
function btn_click(){ alert("我变强了!"); }
</script>
```

---

## 考点自测

1. `length` 是属性还是方法？`concat` 会改变原数组吗？
2. `"Made In China".substring(2,4)` 的输出？它的参数是左闭右开吗？
3. `"made in china".indexOf("a")` 与 `lastIndexOf("a")` 分别返回什么？找不到时返回什么？
4. `getMonth()` 与 `getDay()` 的取值范围？
5. `setTimeout` 与 `setInterval` 的区别？如何取消？
6. `Math.ceil(-25.5)`、`Math.floor(-25.5)`、`Math.round(-25.5)` 分别是多少？
7. 用构造函数创建对象时，`this` 指什么？

> 参考：1. 属性；不会，返回新数组（`push`/`pop`/`reverse` 才会改）。2. `de`；是，`stop` 是结束位置 +1，且 start>stop 时自动交换。3. 1 和 12；找不到返回 -1。4. `getMonth()` 0～11，`getDay()` 0～6（0 为周日）。5. 前者延迟执行一次，后者按周期反复执行；用 `clearTimeout(id)` / `clearInterval(id)` 取消。6. -25、-26、-26。7. 指向用 `new` 创建出来的那个新对象实例。
