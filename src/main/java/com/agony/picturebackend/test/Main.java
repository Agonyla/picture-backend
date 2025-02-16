package com.agony.picturebackend.test;

/**
 * @author: Agony
 * @create: 2025/2/16 14:40
 * @describe:
 */
public class Main {


    /**
     * 返回一个数组的第一个数字
     *
     * @param data 数组
     * @param <T>  泛型类型参数，表示方法 print 可以接受任意类型的数组
     * @return
     */
    public static <T> T print(T[] data) {
        return data[0];
    }


    public static void main(String[] args) {


        Integer[] arr1 = {1, 2, 3};

        String[] arr2 = {"hello", "world", "this", "is"};

        System.out.println(print(arr1));
        System.out.println(print(arr2));

    }
}
