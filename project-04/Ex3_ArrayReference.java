import java.util.Arrays;

/**
 * 实验四 - 题目 3：数组作为引用类型的参数传递
 *
 * Java 中一切参数传递都是"值传递"，但"引用类型"传递的是"引用的副本"，
 * 该副本与原引用指向堆上的同一块数组对象。因此：
 *   - 在方法中"修改数组元素"    => 调用方看得到变化（同一对象）；
 *   - 在方法中"重新赋值形参"    => 调用方看不到变化（只是改了局部引用）；
 *   - 对于基本类型（int 等）    => 方法内任何修改都不会回传到调用方。
 *
 * 本题通过 A / B / C / D 四组对照实验帮助你理解这一差异。
 *
 * 使用说明：将下方每一处 /* TODO x *&#47; 替换为你自己的代码。完成后运行程序，
 * 并与文件末尾"期望输出"进行对照。
 */
public class Ex3_ArrayReference {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // ===== 实验 A：方法内"修改元素" —— 调用方会看到变化 =====
        System.out.println("A-前：" + Arrays.toString(arr));
        doubleEach(arr);
        System.out.println("A-后：" + Arrays.toString(arr));

        // ===== 实验 B：方法内"重新赋值形参" —— 调用方看不到变化 =====
        System.out.println("B-前：" + Arrays.toString(arr));
        replaceInside(arr);
        System.out.println("B-后：" + Arrays.toString(arr));

        // ===== 实验 C：基本类型参数是"值传递" —— 调用方看不到变化 =====
        int x = 10;
        System.out.println("C-前：x = " + x);
        tryChange(x);
        System.out.println("C-后：x = " + x);

        // ===== 实验 D：用数组"包装"一个 int —— 可以被方法修改 =====
        int[] wrapper = {10};
        System.out.println("D-前：wrapper[0] = " + wrapper[0]);
        tryChangeInArray(wrapper);
        System.out.println("D-后：wrapper[0] = " + wrapper[0]);
    }

    // ========== TODO 1 ==========
    // 把数组 a 中"每个元素"变成原来的 2 倍（直接在原数组上修改）
    // 提示：使用传统 for 循环 + 下标，形如 a[i] = a[i] * 2;
    public static void doubleEach(int[] a) {
        /* TODO 1 */
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] * 2;
        }
    }

    // ========== TODO 2 ==========
    // 尝试把形参 a "整体替换"为一个新数组：{99, 99, 99}
    // 注意：这里只是改变了方法内的局部引用，main 中的 arr 不会受影响！
    // 提示：a = new int[]{...};
    public static void replaceInside(int[] a) {
        /* TODO 2 */
        a = new int[]{99, 99, 99};
    }

    // ========== TODO 3 ==========
    // 尝试把基本类型形参 n 修改为 999
    // （这一改只影响方法内的 n，main 中的 x 仍然是 10 —— 值传递）
    public static void tryChange(int n) {
        /* TODO 3 */
        n = 999;
    }

    // ========== TODO 4 ==========
    // 把数组 a 的第 0 个元素改为 999（修改元素内容，引用相同）
    // 与 TODO 3 对比：通过"引用 + 下标"可以从外部看到修改
    public static void tryChangeInArray(int[] a) {
        /* TODO 4 */
        a[0] = 999;
    }

    /*
     * ========== TODO 5（思考题，写在下方注释里即可） ==========
     * 请用 1-3 句话回答：
     *   为什么"修改数组元素"对调用方可见，而"重新给形参赋值"却不可见？
     *
     * 你的回答：
     *   // TODO 5 在此作答
     *  因为"修改数组元素"是通过形参引用访问堆上的同一块数组对象，所以调用方能看到变化；而"重新给形参赋值"只是改变了方法内的局部引用，使其指向一个新对象，调用方的引用仍然指向原数组，因此看不到变化。
     */

    /*
     * =================== 期望输出 ===================
     * A-前：[1, 2, 3, 4, 5]
     * A-后：[2, 4, 6, 8, 10]
     * B-前：[2, 4, 6, 8, 10]
     * B-后：[2, 4, 6, 8, 10]        <-- 注意：没有变成 [99, 99, 99]
     * C-前：x = 10
     * C-后：x = 10                   <-- 基本类型不受影响
     * D-前：wrapper[0] = 10
     * D-后：wrapper[0] = 999         <-- 通过数组可以"穿透"传递修改
     * ================================================
     */
}
