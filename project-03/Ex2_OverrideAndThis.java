/**
 * 实验二：方法重写与 this / super 关键字
 *
 * 【学习目标】
 *   - 掌握方法重写（Override）的语法和规则
 *   - 理解 @Override 注解的作用
 *   - 区分 this 和 super 关键字的用法
 *   - 理解 final 关键字对方法重写的限制
 *
 * 【任务说明】
 *   下面定义了"形状"继承体系：Shape → Circle
 *   请根据 TODO 提示补全代码，使程序输出：
 *
 *   === 测试方法重写 ===
 *   这是一个圆形，半径为 5.0
 *   圆形的面积为：78.54
 *   === 测试 this 和 super ===
 *   Shape 的 describe(): 我是一个通用形状，颜色：蓝色
 *   Circle 的 describe(): 我是一个圆形，半径：5.0
 *   --- 调用 showBoth() ---
 *   [super] 我是一个通用形状，颜色：蓝色
 *   [this]  我是一个圆形，半径：5.0
 *   === 测试 final 方法 === （可选）
 *   形状类型：Circle
 */

// ======================== 父类：Shape ========================
class Shape {
    String color;

    Shape(String color) {
        this.color = color;
    }

    // 父类的 getInfo() 方法
    String getInfo() {
        return "这是一个通用形状";
    }

    // 父类的 getArea() 方法，返回 0
    double getArea() {
        return 0;
    }

    // 父类的 describe() 方法
    void describe() {
        System.out.println("Shape 的 describe(): 我是一个通用形状，颜色：" + color);
    }

    // 父类的 final 方法 —— 子类不能重写！
    final String getType() {
        return this.getClass().getSimpleName();
    }
}

// ======================== 子类：Circle ========================
class Circle extends Shape {
    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    // TODO 1: 重写父类的 getInfo() 方法
    //   - 返回 "这是一个圆形，半径为 " + radius
    @Override
    String getInfo() {
        return "这是一个圆形，半径为 " + radius;
    }

    // TODO 2: 重写父类的 getArea() 方法
    //   - 返回圆的面积（Math.PI * radius * radius）
    //   - 结果保留两位小数：Math.round(面积 * 100.0) / 100.0
    @Override
    double getArea() {
        double area = Math.PI * radius * radius;
        return Math.round(area * 100.0) / 100.0;
    }

    // TODO 3: 重写父类的 describe() 方法
    //   - 打印 "Circle 的 describe(): 我是一个圆形，半径：" + radius
    @Override
    void describe() {
        System.out.println("Circle 的 describe(): 我是一个圆形，半径：" + radius);
    }

    // TODO 4: 编写 showBoth() 方法，展示 super 和 this 的区别
    //   - 第一行：用 super.describe() 调用父类版本的 describe
    //     打印前先输出 "[super] "（不换行，用 System.out.print）
    //   - 第二行：用 this.describe() 调用当前类版本的 describe
    //     打印前先输出 "[this]  "（不换行，用 System.out.print）
    //
    // 提示：super.describe() 会调用 Shape 的 describe()
    //       this.describe()  会调用 Circle 的 describe()
    void showBoth() {
        System.out.print("[super] ");
        super.describe();
        System.out.print("[this]  ");
        this.describe();
    }

    // TODO 5: 体验 final 关键字的限制（可选！）
    //   请尝试取消下面代码的注释，编译并观察报错信息。
    //   请重新注释掉这段代码，保证程序能正常编译运行。
    //
    // @Override
    // String getType() {
    //     return "圆形";
    // }

}

// ======================== 测试类 ========================
public class Ex2_OverrideAndThis {
    public static void main(String[] args) {
        Circle c = new Circle("蓝色", 5.0);

        System.out.println("=== 测试方法重写 ===");
        System.out.println(c.getInfo());
        System.out.println("圆形的面积为：" + c.getArea());

        System.out.println("=== 测试 this 和 super ===");
        c.describe();

        // 为了对比，也调用一下父类的 describe
        Shape s = new Shape("红色");
        s.describe();

        System.out.println("--- 调用 showBoth() ---");
        c.showBoth();

        System.out.println("=== 测试 final 方法 ===");
        // getType() 是父类的 final 方法，子类可以调用但不能重写
        System.out.println("形状类型：" + c.getType());
    }
}
