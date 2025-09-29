// 250924wed 자바 기초
// [Problem4] 아래 코드에서 “예상” 되는 부분을 미리 생각해보고 실행해서 확인하기
package com.kt.hw.d250924wed;
import java.util.Arrays;

public class Problem4 {
    static void plusFive(int v) {
        v += 5; // 기본형
    }

    static void modifyArr(int[] a) {
        a[1] = 77; // 내부 변경
    }

    static void changeRef(int[] a) {
        a = new int[]{9, 9}; // 재할당
    }

    public static void main(String[] args) {
        int x = 1;
        plusFive(x);
        System.out.println("x=" + x); // 예상: x=1
        int[] arr = {10, 20, 30};
        modifyArr(arr);
        System.out.println("arr=" + Arrays.toString(arr)); // 예상: {10, 77, 30}
        changeRef(arr);
        System.out.println("arr=" + Arrays.toString(arr)); // 예상: {10, 77, 30}
    }
}