// 250924wed 자바 기초
// [Problem2] 점수 score(0~100)를 기준으로 90↑: A, 80↑: B, 70↑: C, 60↑: D, 나머지 F 출력
package com.kt.hw.d250924wed;
public class Problem2 {
    enum Grade {A, B, C, D, F, Invalid}

    // if-else 사용
    /*static String gradeOf(int score) {
        if (score < 0 || score > 100) return "Invalid";
        else if (score >= 90 && score <= 100) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else if (score >= 0) return "F";
        else return "Invalid";
    }*/

    // switch문, enum 사용
    static Grade gradeOf(int score) {
        if (score < 0 || score > 100) return Grade.Invalid;
        Grade grade = switch (score / 10) {
            case 10, 9 -> Grade.A;
            case 8 -> Grade.B;
            case 7 -> Grade.C;
            case 6 -> Grade.D;
            default -> Grade.F;
        };
        return grade;
    }
    public static void main(String[] args) {
        int[] samples = {100, 95, 83, 75, 61, 42, -1, 101};
        for (int s : samples) {
            System.out.printf("score=%3d → grade=%s%n", s, gradeOf(s));
        }
    }
}