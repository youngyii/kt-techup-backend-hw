// 250923tue 자바 레벨테스트
// [Problem1] 성적 등급 변환기 (if-else)
// 목표: 점수 구간에 따른 등급 문자열 반환 로직 구현
package com.kt.hw.d250923tue;

public class Problem1 {
	public static String gradeOf(int s) {
		if (s < 0 || s > 100) return "INVALID";
		else if(s >= 90) return "A";
		else if (s >= 80) return "B";
		else if (s >= 70) return "C";
		else if (s >= 60) return "D";
		else if (s >= 0) return "F";
		
		return null;
	}
	public static void main(String[] args) {
		int[] samples = {73, 90, 59, 101, -3, 100, 0, 89, 79, 69};
		for (int i = 0; i < samples.length; i++) {
			int s = samples[i];
			System.out.println(s + " => " + gradeOf(s));
		}
	}
}
