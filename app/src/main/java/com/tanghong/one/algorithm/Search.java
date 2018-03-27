package com.tanghong.one.algorithm;

/**
 * Created by tanghong on 2018/3/27.
 */
public class Search {

    /**
     * 顺序查找：依次遍历
     *
     * @param args
     * @param key
     */
    public static int sequentialSearch(int[] args, int key) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 折半查找：每次查找都对半分，但要求数组是有序的
     *
     * @param args
     * @param key
     * @return
     */
    public static int binarySearch(int[] args, int key) {
        int low = 0;
        int high = args.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (args[middle] == key) {
                return middle;
            } else if (args[middle] > key) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
