/**
 * 实验三：方法重写（Override）与方法重载（Overload）辨析
 *
 * 【学习目标】
 *   - 区分方法重写和方法重载
 *   - 重写：父子类之间，方法名和参数列表都相同
 *   - 重载：同一个类中，方法名相同但参数列表不同
 *   - 进一步巩固 super 关键字调用父类方法的用法
 *
 * 【任务说明】
 *   下面定义了一个"计算器"继承体系：Calculator → ScientificCalculator
 *   请根据 TODO 提示补全代码，使程序输出：
 *
 *   === 基础计算器 ===
 *   3 + 5 = 8.0
 *   === 科学计算器 ===
 *   3 + 5 = 8.0 (ScientificCalculator)
 *   3.14 + 2.72 = 5.86
 *   1 + 2 + 3 = 6.0
 *   5 的平方 = 25.0
 *   === 验证重写：super 调用 ===
 *   父类的 add: 8.0
 *   子类的 add: 8.0 (ScientificCalculator)
 */

// ======================== 父类：Calculator ========================
class Calculator {
    // 基础加法：两个 int 参数
    double add(int a, int b) {
        return a + b;
    }
}

// ======================== 子类：ScientificCalculator ========================
class ScientificCalculator extends Calculator {

    // TODO 1【方法重写 Override】
    //   重写父类的 add(int, int) 方法：
    //   - 计算结果与父类相同（a + b）
    //   - 但返回前打印：a + " + " + b + " = " + 结果 + " (ScientificCalculator)"
    //   - 返回结果
    @Override
    double add(int a, int b) {
        double result = a + b;
        System.out.println(a + " + " + b + " = " + result + " (ScientificCalculator)");
        return result;
    }

    // TODO 2【方法重载 Overload —— 两个 double 参数】
    //   注意：这不是重写，因为参数类型不同！
    //   返回 a + b
    double add(double a, double b) {
        return a + b;
    }

    // TODO 3【方法重载 Overload —— 三个 int 参数】
    //   返回 a + b + c
    double add(int a, int b, int c) {
        return a + b + c;
    }

    // TODO 4【子类独有方法】
    //   返回 x * x
    double square(double x) {
        return x * x;
    }

    // TODO 5【用 super 调用父类被重写的方法】
    //   - 用 super.add(a, b) 调用父类原始版本，打印 "父类的 add: " + 结果
    //   - 用 this.add(a, b) 调用子类重写版本（会打印带标记的结果）
    //   - 打印 "子类的 add: " + 结果
    void compareAdd(int a, int b) {
        double parentResult = super.add(a, b);
        System.out.println("父类的 add: " + parentResult);
        double childResult = this.add(a, b);
        System.out.println("子类的 add: " + childResult + " (ScientificCalculator)");
    }


}

// ======================== 测试类 ========================
public class Ex3_OverrideVsOverload {
    public static void main(String[] args) {
        Calculator basic = new Calculator();
        ScientificCalculator sci = new ScientificCalculator();

        System.out.println("=== 基础计算器 ===");
        System.out.println("3 + 5 = " + basic.add(3, 5));

        System.out.println("=== 科学计算器 ===");
        sci.add(3, 5);                // 重写：同名同参，覆盖父类行为
        System.out.println("3.14 + 2.72 = " + sci.add(3.14, 2.72));  // 重载：double 参数
        System.out.println("1 + 2 + 3 = " + sci.add(1, 2, 3));      // 重载：三个参数
        System.out.println("5 的平方 = " + sci.square(5));

        System.out.println("=== 验证重写：super 调用 ===");
        sci.compareAdd(3, 5);
    }
}
