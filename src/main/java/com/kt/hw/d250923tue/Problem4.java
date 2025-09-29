// 250923tue 자바 레벨테스트
// [Problem3] 홀짝 분리 + 중복 제거 + 오름차순 정렬
// 목표: 배열의 정수들을 짝수/홀수로 나누고, 중복을 제거한 뒤 오름차순으로 출력
package com.kt.hw.d250923tue;
import java.util.*;

public class Problem4 {
	public static void splitDedupSortAndPrint(int[] arr) {
		List<Integer> even = new ArrayList<>();
		List<Integer> odd = new ArrayList<>();
		
		for(int num : arr) {
			if(num % 2 == 0) {if(!even.contains(num)) even.add(num);}
			else {if(!odd.contains(num)) odd.add(num);}
		}
		
		Collections.sort(even);
		Collections.sort(odd);
			
		System.out.printf("even: ");
		for(int n : even) System.out.printf(n + " ");
		System.out.printf("\nodd: ");
		for(int n : odd) System.out.printf(n + " ");
	}
	public static void main(String[] args) {
		int[] A = {5, 2, 3, 2, 8, 5, 1, 8, 0, 7, 7, 3, 10, 10, 9};
		splitDedupSortAndPrint(A);
		// even: 0 2 8 10
		// odd: 1 3 5 7 9
	}
}
