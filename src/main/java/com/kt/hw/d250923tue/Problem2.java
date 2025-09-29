// 250923tue 자바 레벨테스트
// [Problem2] 기초 통계 (min/max/sum/avg)
// 목표: 배열 순회로 최소/최대/합/평균 계산
package com.kt.hw.d250923tue;

public class Problem2 {
	public static void printStats(int[] a) {
		int min = a[0];
		int max = a[0];
		int sum = 0;
		double avg;
		
		for(int i = 0; i < a.length; i++) {
			if(a[i] < min) min = a[i];
			if(a[i] > max) max = a[i];
			sum += a[i];
		}
		avg = (double)sum / a.length;
		String formattedAvg = String.format("%.2f", avg);
		System.out.println("min=" + min + " max=" + max + " sum=" + sum + " avg=" + formattedAvg);
	
	}
	public static void main(String[] args) {
		int[] A = {3, 7, -2, 10, 4};
		int[] B = {5};
		printStats(A); // min=-2 max=10 sum=22 avg=4.40
		printStats(B); // min=5 max=5 sum=5 avg=5.00
	}
}
