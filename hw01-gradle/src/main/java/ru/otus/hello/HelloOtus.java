package ru.otus.hello;

public class HelloOtus {
    public static void main(String[] args) {
        int[] arr = {9, 4, 9, 6, 7, 4, 5};
        System.out.println(findFirstUniqueInt(arr));
    }

    // Найти первый не повторяющийся элемент в массиве целых чисел
    public static int findFirstUniqueInt(int[] arr) {
        int result = 0;
        for(int i= 0; i < arr.length; i++) {
            boolean flag = false;
            for(int j = 0; j < arr.length; j++) {
                if(i != j && arr[i] == arr[j]) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                result = arr[i];
                break;
            }
        }

        return result;
    }
}
