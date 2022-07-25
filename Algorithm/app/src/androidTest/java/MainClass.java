/**
 * dec:
 * <p>
 * author : louis
 * email : zhaotian.liu@tcl.com
 * time : 2022/2/19  20：30
 */
public class MainClass {

    public static void main(String[] args) {

        int[] aar = new int[]{1, 5, 8, 2, 3, 9, 4};
        int[] aar1 = new int[]{9, 8, 7, 6, 5, 4, 3};
//        JBaseSort.getInstance().quickSort(aar1);
//        JBaseSort.getInstance().BubbleSort(aar1);
//        JBaseSort.getInstance().smallSum(new int[]{1,3,4,2,5});
//        ByteTest.INSTANCE.abc();
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            JBaseSort.getInstance().insertPort(arr1);
//            JBaseSort.getInstance().comparator(arr2);
//        }
//        System.out.println("---" + fib(6));
    }

    // for test
    private static int[] copyArray(int[] arr1) {
        if (arr1 == null) return null;
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i];
        }
        return res;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random()*N) -> [0,N-1) 所有的整数，等概率返回一个

        int[] arr = new int[(int) (Math.random() * (maxSize + 1))]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    // 反转单向和双向链表
    // 题目： 分别实现反转单向链表和反转双向链表的函数
    // 要求： 如果链表长度为N，时间复杂度要求为O(N), 额外空间复杂度要求为O(1)
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = next;
            head = next;
        }
        return head;
    }

    public static Node reverseLinkedList2(Node head) {
        if(head == null || head.next == null) return head;
        Node newHead = reverseLinkedList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode next = null;
        DoubleNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 斐波那契数列
    public static Integer fib(int n) {
        int x = 0;
        int y = 1;
        while (n != 0) {
            y = x + y;
            x = y - x;
            n--;
        }
        return x % 1000000007;
    }

}
