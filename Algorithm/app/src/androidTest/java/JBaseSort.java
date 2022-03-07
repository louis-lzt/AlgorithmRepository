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

    private void swap(int[] arr, int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}