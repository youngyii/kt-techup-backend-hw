// 250930tue Stream API 복습
// [Problem2] 기존 for/if로 구성된 코드를 Stream API로 변경하기
package com.kt.hw.d250930tue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P2 {
	static List<String> cleanAndSort(List<String> names) {
		// 아래 로직을 Stream으로 수정할 것

		/*
		Set<String> seen = new HashSet<>();
		List<String> result = new ArrayList<>();
		for (String raw : names) {
			String s = raw.trim();
			if (s.length() >= 3) {
				String up = s.toUpperCase();
				if (!seen.contains(up)) {
					seen.add(up); // 중복 제거
					result.add(up);
				}
			}
		}
		Collections.sort(result);
		return result;
		*/

		List<String> streamResult = names.stream()
			.map(n -> n.trim())
			.filter(n -> n.length() >= 3)
			.map(n -> n.toUpperCase())
			.distinct()
			.sorted()
			.toList();

		return streamResult;
	}

	public static void main(String[] args) {
		List<String> in = Arrays.asList(" amy", "BO", " amy ", "alice", "AlIce ", "bob ");
			System.out.println(cleanAndSort(in)); // 결과: [ALICE, AMY, BOB]
	}
}