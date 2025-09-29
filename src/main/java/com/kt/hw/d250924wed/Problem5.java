// 250924wed 자바 기초
// [Problem5] 로또 당첨번호 출력기
package com.kt.hw.d250924wed;
import java.util.Arrays;

public class Problem5 {
    public static void main(String[] args) {
        int[] ticket = draw();
        System.out.println(Arrays.toString(ticket));
    }

    static int[] draw() {
        int[] pool = new int[45];
        for (int i = 0; i < 45; i++) pool[i] = i + 1;

        // 0~5 인덱스만 무작위 위치와 스왑
        for (int i = 0; i < 6; i++) {
            int r = i + (int) (Math.random() * (45 - i));
            int tmp = pool[i];
            pool[i] = pool[r];
            pool[r] = tmp;
        }

        int[] pick = new int[6];
        System.arraycopy(pool,0, pick, 0, 6);
        return pick;
    }
}