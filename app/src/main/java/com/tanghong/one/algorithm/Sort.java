package com.tanghong.one.algorithm;

import android.util.Log;

/**
 * Created by tanghong on 2018/3/27.
 */
public class Sort {

    /**
     * 选择排序：将待排序集合(0...n)看成两部分，在起始状态中，一部分为(k..n)的待排序unsorted集合，
     * 另一部分为(0...k)的已排序sorted集合,在待排序集合中挑选出最小元素并且记录下标i，若该下标不等于k，
     * 那么 unsorted[i] 与 sorted[k]交换 ，一直重复这个过程，直到unsorted集合中元素为空为止。
     *
     * @param args
     */
    public static void selectionSort(int[] args) {
        if (args == null || args.length == 0) return;

        int len = args.length;
        for (int i = 0, k = 0; i < len; i++, k = i) {
            for (int j = i + 1; j < len; j++) {
                if (args[k] > args[j]) k = j;
            }
            if (i != k) {
                int tmp = args[i];
                args[i] = args[k];
                args[k] = tmp;
            }
        }
    }

    /**
     * 冒泡排序：由于算法每次都将一个最大的元素往上冒，我们可以将待排序集合(0...n)看成两部分，
     * 一部分为(k..n)的待排序unsorted集合，另一部分为(0...k)的已排序sorted集合，
     * 每一次都在unsorted集合从前往后遍历，选出一个数，如果这个数比其后面的数大，则进行交换。完成一轮之后，
     * 就肯定能将这一轮unsorted集合中最大的数移动到集合的最后，并且将这个数从unsorted中删除，移入sorted中。
     *
     * @param args
     */
    public static void bubbleSort(int[] args) {
        if (args == null || args.length == 0) return;

        int len = args.length;
        for (int i = len - 1; i > 0; --i) {
            for (int j = 0; j < i; j++) {
                if (args[j] > args[j + 1]) {
                    int tmp = args[j];
                    args[j] = args[j + 1];
                    args[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                if (args[j] < args[j - 1]) {
                    int tmp = args[j];
                    args[j] = args[j - 1];
                    args[j - 1] = tmp;
                }
            }
        }
    }

    /**
     * 归并排序：像快速排序一样，由于归并排序也是分治算法，因此可使用分治思想：
     * 1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * 2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * 3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * 4.重复步骤3直到某一指针到达序列尾
     * 5.将另一序列剩下的所有元素直接复制到合并序列尾
     *
     * @param ints
     * @param merge
     * @param start
     * @param end
     */
    public static void mergeSort(int[] ints, int[] merge, int start, int end) {
        if (start >= end) return;

        int mid = (end + start) >> 1;

        mergeSort(ints, merge, start, mid);
        mergeSort(ints, merge, mid + 1, end);

        merge(ints, merge, start, end, mid);
    }

    private static void merge(int[] a, int[] merge, int start, int end, int mid) {
        int i = start;
        int j = mid + 1;
        int pos = start;
        while (i <= mid || j <= end) {
            if (i > mid) {
                while (j <= end) merge[pos++] = a[j++];
                break;
            }

            if (j > end) {
                while (i <= mid) merge[pos++] = a[i++];
                break;
            }

            merge[pos++] = a[i] >= a[j] ? a[j++] : a[i++];
        }

        for (pos = start; pos <= end; pos++)
            a[pos] = merge[pos];
    }

    /**
     * 快速排序（交互换排序）：本质来说，快速排序的过程就是不断地将无序元素集递归分割，一直到所有的分区只包含一个元素为止。
     * 由于快速排序是一种分治算法，我们可以用分治思想将快排分为三个步骤：
     * 1.分：设定一个分割值，并根据它将数据分为两部分
     * 2.治：分别在两部分用递归的方式，继续使用快速排序法
     * 3.合：对分割的部分排序直到完成
     *
     * @param args
     * @param start
     * @param end
     */
    public static void exchangeSort(int[] args, int start, int end) {
        if (args == null || args.length == 0) return;
        if (end - start > 1) {
            int mid = dividerAndChange(args, start, end);
            // 对左部分排序
            exchangeSort(args, start, mid);
            // 对右部分排序
            exchangeSort(args, mid + 1, end);
        }
    }

    private static int dividerAndChange(int[] args, int start, int end) {
        //标准值
        int pivot = args[start];
        while (start < end) {
            // 从右向左寻找，一直找到比参照值还小的数值，进行替换
            // 这里要注意，循环条件必须是 当后面的数 小于 参照值的时候
            // 我们才跳出这一层循环
            while (start < end && args[end] >= pivot)
                end--;

            if (start < end) {
                swap(args, start, end);
                start++;
            }

            // 从左向右寻找，一直找到比参照值还大的数组，进行替换
            while (start < end && args[start] < pivot)
                start++;

            if (start < end) {
                swap(args, end, start);
                end--;
            }
        }

        args[start] = pivot;
        return start;
    }

    private static void swap(int[] args, int fromIndex, int toIndex) {
        args[fromIndex] = args[toIndex];
    }

    public static void logSort() {
        int[] arr = {4, 8, 9, 2, 11, 5, 6};
        exchangeSort(arr, 0, arr.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append("value = " + i + "\n");
        }
        Log.i("sort", "value = " + sb.toString());
    }
}
