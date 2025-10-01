// 250930tue Stream API 복습
// [Problem5] 기존 for/if로 구성된 코드를 Stream API로 변경하기
package com.kt.hw.d250930tue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P5 {
	static class Person {
		final String name;
		final int age;
		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}
	// 아래 로직을 Stream으로 수정할 것
	static List<String> top3AdultNames(List<Person> people) {
		/*
		List<Person> adults = new ArrayList<>();
		for (Person p : people) {
			if (p.age >= 20)
				adults.add(p);
		}
		adults.sort((a, b) -> Integer.compare(b.age, a.age));
		List<String> result = new ArrayList<>();
		for (int i = 0; i < adults.size() && i < 3; i++) {
			result.add(adults.get(i).name);
		}
		return result;
		*/

		List<String> streamResult = people.stream()
			.filter(p -> p.age >= 20)
			.sorted((p1, p2) -> Integer.compare(p2.age, p1.age))
			.limit(3)
			.map(p -> p.name)
			.toList();

		return streamResult;
	}
	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
			new Person("Amy", 19),
			new Person("Bob", 22),
			new Person("Cody", 31),
			new Person("Daisy", 44),
			new Person("Evan", 18)
		);
		System.out.println(top3AdultNames(people)); // 결과: [Daisy, Cody, Bob]
	}
}