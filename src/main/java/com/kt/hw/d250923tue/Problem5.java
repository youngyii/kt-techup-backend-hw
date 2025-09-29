// 250923tue 자바 레벨테스트
// [Problem5] 소수 개수 세기 (에라토스테네스의 체)
// 목표: 기본 for/while 만으로 소수 개수 계산
package com.kt.hw.d250923tue;
import java.util.*;

public class Problem5 {
	public static int countPrimes(int N) {
		if(N < 2) return 0;
		
		List<Boolean> arr = new ArrayList<>(Collections.nCopies(N + 1, true));
        arr.set(0, false);
        arr.set(1, false);

		for(int i = 2; i < N; i++){
			if(arr.get(i)){
				int j = 2;
				while(i * j <= N){
					arr.set(i * j, false);
					j++;
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i <= N; i++)
			if(arr.get(i)) cnt++;
		
		return cnt;
	}
	public static void main(String[] args) {
		int[] Ns = {10, 20, 100, 1_000_000};
		for (int i = 0; i < Ns.length; i++) {
			int N = Ns[i];
			System.out.println("N=" + N + " => primes=" + countPrimes(N));
		}
	}
}
