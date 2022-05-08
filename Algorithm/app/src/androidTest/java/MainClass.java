/**
 * dec:
 * <p>
 * author : louis
 * email : zhaotian.liu@tcl.com
 * time : 2022/2/19  20：30
 */
public class MainClass {

    public static void main(String[] args) {

        int[] aar = new int[]{1,5,8,2,3,9,4};
        int[] aar1 = new int[]{9,8,7,6,5,4,3};
//        JBaseSort.getInstance().BubbleSort(aar1);
        JBaseSort.getInstance().smallSum(new int[]{1,3,4,2,5});
//        ByteTest.INSTANCE.abc();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for(int i=0;i<testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            JBaseSort.getInstance().insertPort(arr1);
            JBaseSort.getInstance().comparator(arr2);
        }
    }

    // for test
    private static int[] copyArray(int[] arr1) {
        if (arr1 == null) return null;
        int[] res = new int[arr1.length];
        for(int i=0; i< arr1.length; i++){
            res[i] = arr1[i];
        }
        return res;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random()*N) -> [0,N-1) 所有的整数，等概率返回一个

        int[] arr = new int[(int)(Math.random()*(maxSize+1))]; // 长度随机
        for (int i=0;i<arr.length; i++){
            arr[i] = (int)((maxValue+1)*Math.random()) - (int)(maxValue*Math.random());
        }
        return arr;
    }

}
