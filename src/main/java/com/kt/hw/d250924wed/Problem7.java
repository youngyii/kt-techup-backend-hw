// 250924wed 자바 기초
// [Problem7] 가위바위보 연승 횟수 출력기
package com.kt.hw.d250924wed;
import java.util.Arrays;
import java.util.Random;

public class Problem7 {
    public static void main(String[] args) {
        char[] me = {'R', 'S', 'S', 'P', 'P', 'R'};
        String[] results = resultsByRound(me); // 라운드별 결과: Win/Draw/Lose
        //System.out.println(Arrays.toString(results));
        System.out.println("Max consecutive wins: " + maxConsecutiveWins(results));
    }

    // 라운드별 결과 계산
    static String[] resultsByRound(char[] me) {
        char[] choices = {'R', 'P', 'S'};
        Random rand = new Random();
        String[] result = new String[me.length];

        for (int i = 0; i < me.length; i++) {
            char opp = choices[rand.nextInt(3)]; // 0~2 랜덤
            if (me[i] == opp) {
                result[i] = "Draw";        // 무승부
            } else if (isWin(me[i], opp)) {
                result[i] = "Win";         // 승리
            } else {
                result[i] = "Lose";        // 패배
            }
            System.out.printf("Round %d: me=%c, opp=%c → %s%n", i+1, me[i], opp, result[i]);
        }
        return result;
    }

    // 승리 판단
    static boolean isWin(char me, char opp) {
        return (me == 'R' && opp == 'S') ||
                (me == 'S' && opp == 'P') ||
                (me == 'P' && opp == 'R');
    }

    // 최대 연속 승 계산
    static int maxConsecutiveWins(String[] results) {
        int max = 0, current = 0;
        for (String r : results) {
            if (r.equals("Win")) {
                current++;
                if (current > max) max = current;
            } else {
                current = 0; // 무승부/패배 시 연속 승 초기화
            }
        }
        return max;
    }
}
