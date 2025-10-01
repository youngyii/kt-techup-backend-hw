// 250930tue Stream API 복습
// [Problem3] 기존 for/if로 구성된 코드를 Stream API로 변경하기
package com.kt.hw.d250930tue;

import static java.util.stream.Collectors.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P3 {
	enum Status {NEW, PROCESSING, COMPLETED, CANCELED}

	static class Order {
		final Status status;
		final int quantity;

		Order(Status status, int quantity) {
			this.status = status;
			this.quantity = quantity;
		}
	}

	// 아래 메서드 로직을 Stream으로 수정할 것
	static Map<Status, Integer> quantityByStatus(List<Order> orders) {
		/*
		Map<Status, Integer> acc = new HashMap<>();
		for (Order o : orders) {
			acc.put(o.status, acc.getOrDefault(o.status, 0) + o.quantity);
		}
		return acc;
		*/

		Map<Status, Integer> streamAcc = orders.stream()
			.collect(groupingBy(o -> o.status, summingInt(o -> o.quantity)));
		return streamAcc;
	}

	public static void main(String[] args) {
		List<Order> orders = Arrays.asList(
			new Order(Status.NEW, 3),
			new Order(Status.COMPLETED, 5),
			new Order(Status.NEW, 2),
			new Order(Status.CANCELED, 1),
			new Order(Status.COMPLETED, 7)
		);
		System.out.println(quantityByStatus(orders)); // 결과: {NEW=5, COMPLETED=12, CANCELED=1, PROCESSING=0(없으면 키 없음)}
	}
}