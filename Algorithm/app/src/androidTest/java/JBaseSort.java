import java.util.Arrays;

/**
 * dec: 静态内部类单例 基础排序
 * <p>
 * author : louis
 * email : zhaotian.liu@tcl.com
 * time : 2022/2/26  20：47
 */
public class JBaseSort {

    private JBaseSort(){}

    static class SingleHelp {
        private static JBaseSort INSTANCE = new JBaseSort();
    }

    public static JBaseSort getInstance(){
        return SingleHelp.INSTANCE;
    }

    // 冒泡排序
    public int[] BubbleSort(int[] arr){
        for(int i =0; i <arr.length-1; i++){
            for (int j = i+1; j< arr.length; j++){
                if (arr[i] > arr[j]){
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
    private void swap(int[] arr, int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public void printOddTimesNum1(int[] arr){
        int eor = 0;
        for (int cur:arr){
            eor ^= cur;
        }
        System.out.println(eor);
    }

    public void printOddTimesNum2(int[] arr){
        int eor = 0, onlyOne=0;
        for (int curNum:arr){
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
                if ((cur & rightOne)==0){
                    onlyOne ^= cur;  // a / b
                }
            }
        }
        System.out.println(onlyOne + "_" + (eor ^ onlyOne));
    }

    /**
     * 插入排序
     */
    public void insertPort(int[] arr){
        if(arr == null || arr.length < 2){return;
        }
        // 0~0有序
        // 0~i有序
        for(int i=0; i<arr.length; i++){

            for(int j = i-1; j>=0&&arr[j] > arr[j+1]; j--){
                swap(arr, j, j+1);
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    // for test
    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    /**
     * 用递归方法找到一个数组中的最大值，系统上到底是怎么做的？
     *
     * T(N):母问题的数据量 是N级别的
     * N/b :子问题都是 N/b的规模
     * a :  是指子问题调用了多少次
     * O(N^d) : 除了子问题被调用多少次之外，剩下的过程 时间复杂度 是多少
     *
     * T(N) = a*T(N/b) + O(N^d)
     * log b^a < d    O(N^d)
     * log b^a > d    O(N^log b^a)
     * log b^a == d   O(N^d * logN)
     *
     * T(N) = 2*T(N/2) + O(1)
     */
    public static int process(int[] arr, int L,int R) {
        if(L==R){ // arr[L..R]范围上只有一个数，直接返回， base case
            return arr[L];
        }
        int mid = L + ((R-L) >> 1);// 中点
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid+1, R);
        return Math.max(leftMax, rightMax);
    }

    /**
     * 归并排序
     * 1) 整体就是一个简单递归，左边排好序，右边排好序，让整体有序
     * 2) 让其整体有序的过程里用了排外序方法
     * 3) 利用master公式来求解时间复杂度
     * 4) 归并排序的实质
     * 时间复杂度O(N*logN), 额外空间复杂度 O(N)
     */
    public static void mergeSort(int[] arr){
        if (arr==null || arr.length <2){
            return;
        }
        processMergeSort(arr, 0, arr.length-1);
    }

    private static void processMergeSort(int[] arr, int L, int R) {
        if(L==R){
            return;
        }
        int mid = L + ((R-L) >> 1);
        processMergeSort(arr, L, mid);
        processMergeSort(arr, mid+1, R);
        merge(arr, L , mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r-l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1 ;
        while(p1 <= mid && p2<=r){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid){

        }
    }

}
