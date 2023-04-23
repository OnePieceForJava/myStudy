package alth;

import util.PatternMethod;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class QuickSort {

    /**
     * 双边指针（挖坑法）
     * 思路：
     * 创建左右指针。
     * 记录左指针数据为分界值 pivot，
     * 此时左指针视为"坑"，里面的数据可以被覆盖。
     * <p>
     * 首先从右向左找出比pivot小的数据，
     * 找到后立即放入左边坑中，当前位置变为新的"坑"，
     * 然后从左向右找出比pivot大的数据，
     * 找到后立即放入右边坑中，当前位置变为新的"坑"，
     * <p>
     * 结束循环后将最开始存储的分界值放入当前的"坑"中，
     * 返回当前"坑"下标（即分界值下标）
     */
    public static int doublePointerHole(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {
            // 从右向左找出比pivot小的数据，
            while (leftPoint < rightPoint
                    && arr[rightPoint] > pivot) {
                rightPoint--;
            }
            // 找到后立即放入左边坑中，当前位置变为新的"坑"
            if (leftPoint < rightPoint) {
                arr[leftPoint] = arr[rightPoint];
                leftPoint++;
            }
            // 从左向右找出比pivot大的数据
            while (leftPoint < rightPoint
                    && arr[leftPoint] <= pivot) {
                leftPoint++;
            }
            // 找到后立即放入右边坑中，当前位置变为新的"坑"
            if (leftPoint < rightPoint) {
                arr[rightPoint] = arr[leftPoint];
                rightPoint--;
            }
        }
        // 将最开始存储的分界值放入当前的"坑"中
        arr[rightPoint] = pivot;
        return rightPoint;  //注意思考为什么这里返回rightPoint而不是leftPoint
    }




    /**
     * 单边指针
     * 思路：
     * 记录首元素为分界值 pivot, 创建标记 mark 指针。
     * 循环遍历与分界值对比。
     * 比分界值小，则 mark++ 后与之互换。
     * 结束循环后，将首元素分界值与当前mark互换。
     * 返回 mark 下标为分界值下标。
     */
    public static int singlePointer(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int markPoint = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            // 如果比分界值小，则 mark++ 后互换。
            if (arr[i] < pivot) {
                markPoint++;
                int temp = arr[markPoint];
                arr[markPoint] = arr[i];
                arr[i] = temp;
            }
        }
        // 将首元素分界值与当前mark互换
        arr[startIndex] = arr[markPoint];
        arr[markPoint] = pivot;
        return markPoint;
    }


    public static int doublePointerSwap(AtomicIntegerArray atomicIntegerArray, int startIndex, int endIndex) {
        int pivot = atomicIntegerArray.get(startIndex);
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {
            // 从右向左找出比pivot小的数据
            while (leftPoint < rightPoint && atomicIntegerArray.get(rightPoint) > pivot) {
                rightPoint--;
            }
            // 从左向右找出比pivot大的数据
            while (leftPoint < rightPoint && atomicIntegerArray.get(leftPoint) <= pivot) {
                leftPoint++;
            }
            // 没有过界则交换
            if (leftPoint < rightPoint) {
                int left = atomicIntegerArray.get(leftPoint);
                int right = atomicIntegerArray.get(rightPoint);
                atomicIntegerArray.set(leftPoint, right);
                atomicIntegerArray.set(rightPoint, left);

            }
        }
        // 最终将分界值与当前指针数据交换
        atomicIntegerArray.set(startIndex, atomicIntegerArray.get(rightPoint));
        atomicIntegerArray.set(rightPoint, pivot);
        // 返回分界值所在下标
        return rightPoint;
    }




    public static void main(String[] args) {
        testSafe();
        //testNoSafe();
    }


    public static void testNoSafe(){
        int[] arr = {5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8};
        PatternMethod.arrPrint(arr);
        quickSortRecursive(0, arr.length-1,arr);
        PatternMethod.arrPrint(arr);
    }


    public static void testSafe(){
        int[] arr = {5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
        PatternMethod.arrPrint(atomicIntegerArray);
        quickSortRecursive(0, arr.length-1,atomicIntegerArray);
        PatternMethod.arrPrint(atomicIntegerArray);
    }

    private static void quickSortRecursive(int start, int end, int[] arr) {
        if (start < end) {
            /*双边指针（挖坑法）*/
            //int pivot = doublePointerHole(arr,start,end);
            /*双边指针（交换法）*/
            //int pivot = doublePointerSwap(arr,start,end);
            /*单边指针*/
            int pivot = singlePointer(arr,start,end);
            quickSortRecursive(start, pivot-1, arr);
            quickSortRecursive(pivot + 1, end, arr);
        }
    }

    private static void quickSortRecursive(int start, int end, AtomicIntegerArray atomicIntegerArray) {
        if (start < end) {
            int pivot = doublePointerSwap(atomicIntegerArray,start,end);
            quickSortRecursive(start, pivot-1, atomicIntegerArray);
            quickSortRecursive(pivot + 1, end, atomicIntegerArray);
        }
    }

    /**
     * 双边指针（交换法）
     * 思路：
     * 记录分界值 pivot，创建左右指针（记录下标）。
     * （分界值选择方式有：首元素，随机选取，三数取中法）
     * <p>
     * 首先从右向左找出比pivot小的数据，
     * 然后从左向右找出比pivot大的数据，
     * 左右指针数据交换，进入下次循环。
     * <p>
     * 结束循环后将当前指针数据与分界值互换，
     * 返回当前指针下标（即分界值下标）
     * <p>
     * <p>
     * pivot
     * n.
     * 支点;枢轴;中心点;最重要的人(或事物);中心;核心
     * v.
     * (使)在枢轴上旋转(或转动)
     */
    public static int doublePointerSwap(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {
            // 从右向左找出比pivot小的数据
            while (leftPoint < rightPoint && arr[rightPoint] > pivot) {
                rightPoint--;
            }
            // 从左向右找出比pivot大的数据
            while (leftPoint < rightPoint && arr[leftPoint] <= pivot) {
                leftPoint++;
            }
            // 没有过界则交换
            if (leftPoint < rightPoint) {
                int temp = arr[leftPoint];
                arr[leftPoint] = arr[rightPoint];
                arr[rightPoint] = temp;
            }
        }
        // 最终将分界值与当前指针数据交换
        arr[startIndex] = arr[rightPoint];
        arr[rightPoint] = pivot;
        // 返回分界值所在下标
        return rightPoint;
    }

}
