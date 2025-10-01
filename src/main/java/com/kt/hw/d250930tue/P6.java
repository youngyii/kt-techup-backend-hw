// 250930tue Stream API 복습
// [Problem6] 단어 빈도 Top-5 출력하기
package com.kt.hw.d250930tue;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class P6 {
	// 주어진 문장들에서 가장 빈도수가 높은 단어 5개를 반환
	// - 대소문자 구분 없음
	// - 공백 무시
	// - 문장 부호 무시
	// Stream API 활용
	static List<String> top5FrequentWords(List<String> sentences) {
		// TODO

		List<String> streamResult = sentences.stream()
			//.flatMap(s -> Arrays.stream(s.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")))
			.flatMap(s -> Arrays.stream(s.split("\\W+"))) // 비-단어 문자를 구분자로 분할
			.map(s -> s.toLowerCase().trim())
			.filter(s -> !s.isEmpty())
			.collect(groupingBy(s -> s, counting()))
			.entrySet().stream()
			.sorted(Comparator
				.comparing(Map.Entry<String, Long>::getValue).reversed() // 값 기준 내림차순
				.thenComparing(Map.Entry::getKey) // 값 같으면 키 기준 오름차순
			)
			.limit(5)
			.map(Map.Entry::getKey)
			.toList();

		return streamResult;
	}
	public static void main(String[] args) {
		List<String> sentences = Arrays.asList(
			"Hello, Stream API!",
			"hello java stream",
			"Stream... HELLO?"
		);
		System.out.println(top5FrequentWords(sentences)); // 결과: [hello, stream, api, java]
	}
}