package com.algo.util.array.model;

/**
 * 
 * 使用数组实现Stack,性能非常好
 */
public class Stack {

    private int[] arr;
    private int size;

    public Stack(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("The init size is less than 0");
        }
        arr = new int[initSize];
        size = 0;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return arr[size - 1];
    }

    public void push(int obj) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("The queue is full");
        }
        arr[size++] = obj;
    }

    public Integer pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("The queue is empty");
        }
        return arr[--size];
    }
}
