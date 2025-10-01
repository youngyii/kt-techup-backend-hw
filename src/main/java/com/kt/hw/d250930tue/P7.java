// 250930tue Stream API 복습
// [Problem7] 기존 for/if로 구성된 코드를 Stream API로 변경하기
package com.kt.hw.d250930tue;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.summingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P7 {
	enum Status {NEW, PROCESSING, COMPLETED, CANCELED}
	public static class Order {
		final String product; // 상품명
		final int quantity; // 수량
		final Status status; // 상태
		Order(String product, int quantity, Status status) {
			this.product = product;
			this.quantity = quantity;
			this.status = status;
		}
	}
	// 판매량 기준 Top3
	// 아래 로직을 Stream API로 변경해보세요.
	static List<String> top3ProductsByQuantity(List<Order> orders) {
		/*
		Map<String, Integer> qty = new HashMap<>();
		for (Order o : orders) {
			if (o.status == Status.COMPLETED) {
				qty.put(o.product, qty.getOrDefault(o.product, 0) + o.quantity);
			}
		}
		List<Map.Entry<String, Integer>> entries = new ArrayList<>(qty.entrySet());
		entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
		List<String> result = new ArrayList<>();
		for (int i = 0; i < entries.size() && i < 3; i++) {
			result.add(entries.get(i).getKey());
		}
		return result;
		*/

		List<String> streamResult = orders.stream()
			.filter(o -> o.status == Status.COMPLETED)
			.collect(groupingBy(o -> o.product, summingInt(o -> o.quantity)))
			.entrySet().stream()
			.sorted(Comparator
				.comparing(Map.Entry<String, Integer>::getValue).reversed() // 값 기준 내림차순
				.thenComparing(Map.Entry::getKey) // 값 같으면 키 기준 오름차순
			)
			.limit(3)
			.map(Map.Entry::getKey)
			.toList();

		return streamResult;
	}
	public static void main(String[] args) {
		List<Order> orders = Arrays.asList(
			new Order("Apple", 10, Status.COMPLETED), // 10
			new Order("Banana", 20, Status.COMPLETED), // 20
			new Order("Apple", 5, Status.COMPLETED), // +5 = 15
			new Order("Kiwi", 5, Status.PROCESSING), // 제외
			new Order("Kiwi", 7, Status.COMPLETED) // 7
		);
		System.out.println(top3ProductsByQuantity(orders)); // 기대: [Banana, Apple, Kiwi]
	}
}