// 250930tue Stream API 복습
// [Problem1] 기존 for/if로 구성된 코드를 Stream API로 변경하기
package com.kt.hw.d250930tue;

import java.util.*;
import java.util.Arrays;
import java.util.List;

public class P1 {
	static int sumOfSquaresOfEven(List<Integer> nums) {
		// 아래 로직을 Stream으로 수정할 것

		/*
		int sum = 0;
		for (int n : nums) {
			if (n % 2 == 0) {
				int sq = n * n;
				sum += sq;
			}
		return sum;
		}*/

		int streamSum = nums.stream()
			.filter(n -> n % 2 == 0)
			.map(n -> n * n)
			.reduce(0, Integer::sum);
			//.mapToInt(Integer::intValue)
			//.sum();

		return streamSum;
	}
	public static void main(String[] args) {
		System.out.println(sumOfSquaresOfEven(Arrays.asList(1,2,3,4,5,6))); // 결과: 4^2 + 2^2 + 6^2 = 56
	}
}