// 250926thu 모던자바와 람다식 과제
// [Problem3-1] get VS orElse VS orElseGet
// 목표: get() , orElse , orElseGet 사용 실습
package com.kt.hw.d250926fri;
import java.util.Optional;

public class P3A_PickValue_Assignment {
	// (버그 유도) get 사용: 빈 Optional이면 예외가 난다.
	static String chooseByGet_BUGGY(Optional<String> opt) {
		// (예상) opt가 비어 있을 때 어떤 일이 일어나는가?
		// get() 호출 -> NoSuchElementException 발생
		return opt.get();
	}

	// orElse 연습: opt가 비어 있으면 "DEFAULT" 반환, 값이 있으면 그 값을 반환
	static String chooseByGet_SAFE(Optional<String> opt) {
		return opt.orElse("DEFAULT");
	}

	// orElse 연습: null 일 경우, fallback 값을 반환
	static String chooseByOrElse(Optional<String> opt, String fallback) {
		return opt.orElse(fallback);
	}

	// orElseGet 연습
	// null 일 경우, fallback 값을 반환
	static String chooseByOrElseGet(Optional<String> opt, String fallback) {
		return opt.orElseGet(() -> fallback);
	}

	public static void main(String[] args) {
		System.out.println("== P3A ASSIGNMENT: 값 넣기 실습 ==");

		Optional<String> valuePresent = Optional.of("VALUE");
		Optional<String> valueEmpty = Optional.empty();

		// 1) BUGGY: get()
		System.out.println("-- chooseByGet_BUGGY --");
		System.out.println("present → " + chooseByGet_BUGGY(valuePresent)); // (예상: VALUE)
		try {
			System.out.println("empty → " + chooseByGet_BUGGY(valueEmpty)); // (예상: 실행 안 됨)
		} catch (Exception e) {
			System.out.println("BUGGY caught: " + e.getClass().getSimpleName()); // (예상: NoSuchElementException)
		}

		// 2) TODO: SAFE (get 대체)
		System.out.println("-- chooseByGet_SAFE --");
		System.out.println("present → " + chooseByGet_SAFE(valuePresent)); // (예상: VALUE)
		System.out.println("empty → " + chooseByGet_SAFE(valueEmpty)); // (예상: DEFAULT)

		// 3) orElse
		System.out.println("-- chooseByOrElse --");
		System.out.println("present → " + chooseByOrElse(valuePresent, "DEFAULT")); // (예상: VALUE)
		System.out.println("empty → " + chooseByOrElse(valueEmpty, "DEFAULT")); // (예상: DEFAULT)

		// 4) TODO: orElseGet
		System.out.println("-- chooseByOrElseGet --");
		System.out.println("present → " + chooseByOrElseGet(valuePresent, "DEFAULT")); // (예상: VALUE)
		System.out.println("empty → " + chooseByOrElseGet(valueEmpty, "DEFAULT")); // (예상: DEFAULT)
	}
}