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

    public int[] BubbleSort(int[] arr){
        return arr;
    }

}
