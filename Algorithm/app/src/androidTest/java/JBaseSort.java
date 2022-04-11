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
     * T(N) = a*T(N/b) + O(N^d)
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

}
