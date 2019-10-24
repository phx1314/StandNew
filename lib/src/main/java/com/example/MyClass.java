package com.example;


public class MyClass {

    public static void main(String[] args) {
//        int[] num = {12, 23, 34, 45, 56, 67, 77, 89, 90};
////        kp(num, 0, num.length - 1);
//        System.out.print(erFF(num, 45));
        final int data = 9;
        final StringBuilder stringBuilder;
        stringBuilder = new StringBuilder("aaa");
        System.out.println("与运算后的数据为:" + data);
        try {

        } catch (Exception e) {
        } finally {

        }
    }

    static int erFF(int[] data, int key) {
        if (data == null || data.length <= 0)
            return -1;
        int start = 0;
        int end = data.length;
        while (start < end) {
            int mm = (start + end) / 2;
            if (data[mm] < key) {
                start = mm;
            } else if (data[mm] > key) {
                end = mm;
            } else {
                return mm;
            }
        }
        return -1;


    }

    static void kp(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        int k = data[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (data[j] >= k && i < j) {
                j--;
            }
            while (data[i] <= k && i < j) {
                i++;
            }
            if (i < j) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }

        }
        data[left] = data[i];
        data[i] = k;
        kp(data, left, i - 1);
        kp(data, i + 1, right);

    }


}
