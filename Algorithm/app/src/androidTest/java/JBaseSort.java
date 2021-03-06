import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * dec: 静态内部类单例 基础排序
 * <p>
 * author : louis
 * email : zhaotian.liu@tcl.com
 * time : 2022/2/26  20：47
 */
public class JBaseSort {

    // 堆排序扩展题目
    // 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，并且k相对于
    //数组来说比较小。请选一个合适的排序算法针对这个数据进行排序
    public void sortedArrDistanceLessK(int[] arr, int k) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while(!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }

    // 堆排序
    public static void heapsort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) { // O(N)
            heapInsert(arr, i);   // O(logN)
        }
        int heapSize = arr.length;
        swap1(arr, 0, --heapSize);
        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap1(arr, 0, --heapSize);
        }
    }

    // 某个数现在处在index位置，往上继续移动
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1)] / 2) {
            swap1(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候

            // 两个孩子中，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和孩子之间，谁的值大，就把下标 给 largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap1(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }

    }

    public static void swap1(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap1(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1); // < 区
            quickSort(arr, p[1] + 1, r);   // > 区
        }
    }

    // 这是一个处理arr[l..r]的函数
    // 默认以arr[r]做划分， arr[r] -> p <p ==p >p
    // 返回等于区域(左边界， 右边界)， 所以返回一个长度为2的数组， res, res[0] res[1]
    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap1(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap1(arr, --more, less++);
            } else {
                l++;
            }
        }
        swap1(arr, --more, r);
        return arr;
    }

    /**
     * 归并排序
     * 1) 整体就是一个简单递归，左边排好序，右边排好序，让整体有序
     * 2) 让其整体有序的过程里用了排外序方法
     * 3) 利用master公式来求解时间复杂度
     * 4) 归并排序的实质
     * 时间复杂度O(N*logN), 额外空间复杂度 O(N)
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processMergeSort(arr, 0, arr.length - 1);
    }

    private static void processMergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        processMergeSort(arr, L, mid);
        processMergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;  // 专门给help使用的
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    /**
     * 归并排序的扩展
     * 小和问题
     * des： 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数据的小和。求一个数组的小和。
     * 例子： [1,3,4,2,5] 1左边比1小的数，没有；3左边比三小的数，1；。。。。5左边比5小的数，1、3、4、2；
     * 所以小和为1+1+3+1+1+3+4+2 = 16
     * <p>
     * 逆序对问题： 在一个数组中，左边的数如果比右边的大，则两个数构成一个逆序对，请打印所有逆序对
     *
     * @param arr
     * @return
     */
    public int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processSmallSum(arr, 0, arr.length - 1);
    }

    // 既要排好序, 也要求小和
    private static int processSmallSum(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // 0000 1010 -》 0000 0101 -》 0000 0010
        int mid = l + ((r - l) >> 1);
        return processSmallSum(arr, l, mid) +
                processSmallSum(arr, mid + 1, r) +
                mergeSmallSum(arr, l, mid, r);
    }

    private static int mergeSmallSum(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            System.out.println("1::" + i);
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            System.out.println("2::" + i);
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            System.out.println("3::" + help[i]);
            arr[l + i] = help[i];
        }
        return res;
    }

    // 冒泡排序
    public int[] BubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                    System.out.println(Arrays.toString(arr));
                }
            }
            System.out.println("----------------------");
        }
        return arr;
    }

    // 前提是数组中的数 不重复才可以，否则将出现0的情况
    // 异或运算可用于：找出一个数组中，出现过奇数次的数。把数组异或一遍，偶数全消掉，留下的就是奇数
    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    public void printOddTimesNum2(int[] arr) {
        int eor = 0, onlyOne = 0;
        for (int curNum : arr) {
            eor ^= curNum;
            // eor = a ^ b
            // eor !=0
            // eor 必然有一个位置上是1
            /**
             * 提取出最右的1
             * eor:               0101 0100
             * ~eor:              1010 1011
             * ~eor+1:            1010 1100
             * eor & (~eor + 1) : 0000 0100
             */
            int rightOne = eor & (~eor + 1);
            for (int cur : arr) {
                if ((cur & rightOne) == 0) {
                    onlyOne ^= cur;  // a / b
                }
            }
        }
        System.out.println(onlyOne + "_" + (eor ^ onlyOne));
    }

    /**
     * 插入排序
     */
    public void insertPort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~0有序
        // 0~i有序
        for (int i = 0; i < arr.length; i++) {

            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    // for test
    public void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 用递归方法找到一个数组中的最大值，系统上到底是怎么做的？
     * <p>
     * T(N):母问题的数据量 是N级别的
     * N/b :子问题都是 N/b的规模
     * a :  是指子问题调用了多少次
     * O(N^d) : 除了子问题被调用多少次之外，剩下的过程 时间复杂度 是多少
     * <p>
     * T(N) = a*T(N/b) + O(N^d)
     * log b^a < d    O(N^d)
     * log b^a > d    O(N^log b^a)
     * log b^a == d   O(N^d * logN)
     * <p>
     * T(N) = 2*T(N/2) + O(1)
     */
    public static int process(int[] arr, int L, int R) {
        if (L == R) { // arr[L..R]范围上只有一个数，直接返回， base case
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);// 中点
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    private JBaseSort() {
    }

    static class SingleHelp {
        private static JBaseSort INSTANCE = new JBaseSort();
    }

    public static JBaseSort getInstance() {
        return SingleHelp.INSTANCE;
    }

}
