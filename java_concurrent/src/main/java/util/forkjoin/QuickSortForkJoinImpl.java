package util.forkjoin;

import alth.QuickSort;
import sun.java2d.pipe.SpanIterator;
import util.BuildIntegerArray;
import util.PatternMethod;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class QuickSortForkJoinImpl {

    static class SafeQuickSorkAction extends RecursiveAction {
        private int start;
        private int end;
        private AtomicIntegerArray atomicIntegerArray;

        SafeQuickSorkAction(AtomicIntegerArray atomicIntegerArray, int start, int end) {
            this.atomicIntegerArray = atomicIntegerArray;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (start < end) {
                int pivot = QuickSort.doublePointerSwap(atomicIntegerArray, start, end);
                SafeQuickSorkAction left = new SafeQuickSorkAction(atomicIntegerArray, start, pivot - 1);
                SafeQuickSorkAction right = new SafeQuickSorkAction(atomicIntegerArray, pivot + 1, end);
                left.fork();
                right.fork();
                left.join();
                right.join();
            }
        }
    }


    static class NoSafeQuickSorkAction extends RecursiveAction {
        private int start;
        private int end;
        private int[] arr;

        NoSafeQuickSorkAction(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (start < end) {
                int pivot = QuickSort.doublePointerSwap(arr, start, end);
                NoSafeQuickSorkAction left = new NoSafeQuickSorkAction(arr, start, pivot - 1);
                NoSafeQuickSorkAction right = new NoSafeQuickSorkAction(arr, pivot + 1, end);
                left.fork();
                right.fork();
                left.join();
                right.join();
            }
        }
    }


    private static void testSafe(int arrSize) {
        int[] arr = BuildIntegerArray.buildIntegerArray(arrSize);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
        PatternMethod.arrPrint(atomicIntegerArray);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SafeQuickSorkAction quickSorkAction = new SafeQuickSorkAction(atomicIntegerArray, 0, arr.length - 1);
        forkJoinPool.invoke(quickSorkAction);
        PatternMethod.arrPrint(atomicIntegerArray);
    }

    private static void testNoSafe(int arrSize) {
        int[] arr = BuildIntegerArray.buildIntegerArray(arrSize);
        PatternMethod.arrPrint(arr);

        /*int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);*/

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        NoSafeQuickSorkAction quickSorkAction = new NoSafeQuickSorkAction(arr, 0, arr.length - 1);
        forkJoinPool.invoke(quickSorkAction);
        PatternMethod.arrPrint(arr);

       /* List arrList = Arrays.asList(arr);
        List arr2List = Arrays.asList(arr2);
        Iterator iterator = arrList.iterator();
        while (iterator.hasNext()){
            Object value =  iterator.next();
            if(!arr2List.contains(value)){
                System.out.println(value);
            }
        }*/

    }


    public static void main(String[] args) {
        /**
         * 这里使用forkJoin都是对arr进行操作会存在线程安全的问题么？？？
         */
        //testSafe();
        testNoSafe(2000);
    }
}
