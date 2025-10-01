// 250930tue Stream API 복습
// [Problem4] 기존 for/if로 구성된 코드를 Stream API로 변경하기
package com.kt.hw.d250930tue;

import static java.util.stream.Collectors.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P4 {
	// 아래 로직을 Stream으로 수정할 것
	static Set<String> wordSet(List<String> sentences) {
		/*
		Set<String> result = new HashSet<>();
		for (String line : sentences) {
			for (String w : line.split("\\s+")) {
				String s = w.toLowerCase().trim();
				if (!s.isEmpty())
					result.add(s);
			}
		}
		return result;
		*/

		Set<String> streamResult = sentences.stream()
			.flatMap(s -> Arrays.stream(s.split("\\s+")))
			.map(s -> s.toLowerCase().trim())
			.filter(s -> !s.isEmpty())
			.collect(toSet());

		return streamResult;
	}
	public static void main(String[] args) {
		List<String> sentences = Arrays.asList(
			"Hello Stream API",
			"hello Java stream",
			" "
		);
		System.out.println(wordSet(sentences)); // 결과: [hello, stream, api, java]	(순서는 Set 특성상 달라질 수 있음)
	}
}