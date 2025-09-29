// 250924wed 자바 기초
// [Problem3] 구구단
package com.kt.hw.d250924wed;
public class Problem3 {
    public static void main(String[] args) {
        System.out.println("[3-1] 구구단 2단~9단");
        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 10; j++)
                System.out.println(i + " x " + j + " = " + i * j);
            System.out.println();
        }
        System.out.println("[3-2] 구구단 2단~9단 나란히");
        for (int i = 1; i < 10; i++) {
            for (int j = 2; j < 10; j++)
                System.out.printf("%d x %d = %2d ", j, i, j * i);
            System.out.println();
        }
    }
}
