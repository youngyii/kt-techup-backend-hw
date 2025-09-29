// 250924wed 자바 기초
// [Problem1] 1~n의 합을 for로 구해 출력 (n=10)
package com.kt.hw.d250924wed;
public class Problem1 {
    public static void main(String[] args) {
        int n = 10, sum = 0;

        for(int i = 1; i <= n; i++) sum += i;

        System.out.println("sum: " + sum);
    }
}