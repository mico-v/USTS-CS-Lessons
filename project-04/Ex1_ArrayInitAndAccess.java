/**
 * 实验四 - 题目 1：数组的初始化与访问
 *
 * 本题练习 Java 一维数组的两种初始化方式（静态 / 动态）、通过下标访问元素、
 * 使用传统 for 循环与增强 for 循环（for-each）遍历数组，以及 length 属性的使用。
 *
 * 使用说明：将下方每一处 /* TODO x *&#47; 替换为你自己的代码。完成后运行程序，
 * 并与文件末尾"期望输出"进行对照。
 */
public class Ex1_ArrayInitAndAccess {

    public static void main(String[] args) {

        // ========== TODO 1：静态初始化 ==========
        // 使用"数组字面量"的方式创建一个 int 类型数组 scores，
        // 元素依次为：90, 85, 77, 92, 68
        int[] scores = {90, 85, 77, 92, 68} /* TODO 1 */ ;

        // ========== TODO 2：动态初始化 ==========
        // 2a：使用 new 关键字创建一个长度为 5 的 int 数组 squares
        // 2b：在下方的 for 循环里，把 squares[i] 赋值为 (i + 1) * (i + 1)
        int[] squares = new int[5] /* TODO 2a */ ;
        for (int i = 0; i < squares.length; i++) {
            squares[i] = (i + 1) * (i + 1)  /* TODO 2b */ ;
        }

        // ========== TODO 3：传统 for 循环遍历 scores ==========
        // 按格式打印（每行一个元素）：scores[0] = 90
        // 提示：需要用到 scores.length 和下标 i
        System.out.println("--- scores（传统 for 循环）---");
        /* TODO 3 */
        for (int i = 0; i < scores.length; i++) {
            System.out.println("scores[" + i + "] = " + scores[i]);
        }

        // ========== TODO 4：增强 for 循环遍历 squares ==========
        // 按格式打印（每行一个元素）：squares 元素 = 1
        // 提示：for (int x : squares) { ... }
        System.out.println("--- squares（增强 for 循环）---");
        /* TODO 4 */
        for (int x : squares) {
            System.out.println("squares 元素 = " + x);
        }

        // ========== TODO 5：求 scores 的最大值 ==========
        // 先把 max 初始化为 scores 的第一个元素，然后遍历数组更新 max
        int max = scores[0] /* TODO 5a */ ;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        System.out.println("scores 的最大值 = " + max);
    }

    /*
     * =================== 期望输出 ===================
     * --- scores（传统 for 循环）---
     * scores[0] = 90
     * scores[1] = 85
     * scores[2] = 77
     * scores[3] = 92
     * scores[4] = 68
     * --- squares（增强 for 循环）---
     * squares 元素 = 1
     * squares 元素 = 4
     * squares 元素 = 9
     * squares 元素 = 16
     * squares 元素 = 25
     * scores 的最大值 = 92
     * ================================================
     */
}
