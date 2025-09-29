// 250923tue 자바 레벨테스트
// [Problem3] 단어 수 & 최장 단어
// 목표: 공백이 여러 개여도 단어 개수와 최장 단어 찾기
package com.kt.hw.d250923tue;

public class Problem3 {
	public static void analyze(String s) {
		int count = 0;
		String longest = "";
		String[] arr = s.split(" ");

		// 답안
		// trim() // 앞뒤 공백 제거
		// split("\\s+") // 하나 이상의 공백을 기준으로 분리

		/*
		if (s == null) s = "";
		s = s.trim();
		if (s.length() == 0) {
			System.out.println("count=0");
			System.out.println("longest=");
			return;
		}
		String[] arr = s.split("\\s+");
		int count = arr.length;
		String longest = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].length() > longest.length()) {
				longest = arr[i];
			}
		}
		*/

		for(int i = 0; i < arr.length; i++) {
			if (arr[i].length() > longest.length()) {
				longest = arr[i];
			}
			if (!arr[i].isEmpty()) count++;
		}
		
		System.out.println("count=" + count + ", longest=" + longest);
	}
	public static void main(String[] args) {
		String s1 = " Java is a good language ";
		String s2 = "";
		analyze(s1); // count=5, longest=language
		analyze(s2); // count=0, longest=
	}
}
