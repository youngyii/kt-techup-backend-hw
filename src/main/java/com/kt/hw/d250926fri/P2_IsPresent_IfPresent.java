// 250926thu 모던자바와 람다식 과제
// [Problem2] isPresent와 ifPresent의 차이
// 목표: 값 존재 확인 후 처리 방식과 부수효과(출력/발송 등) 처리
package com.kt.hw.d250926fri;
import java.util.Optional;

public class P2_IsPresent_IfPresent {
		// isPresent(): Optional에 값이 존재하는지 확인(true/false), 값이 필요하면 opt.get() 사용
		// ifPresent(): Optional에 값이 존재하는 경우에만 특정 동작 실행

		static String externalName(boolean asNull) {
			return asNull ? null : "Alice";
		}

		// (안티패턴 시연) isPresent + get 조합
		static String greet_BUGGY(Optional<String> nameOpt) {
			if (nameOpt.isPresent()) {
				return "Hello, " + nameOpt.get();
			}
			// (예상) 이 줄은 어떤 문제를 일으키는가?
			// 값이 있으면 "Hello, " + name 반환
			// 값이 없으면 빈 Optional에서 get() 호출 -> NoSuchElementException 발생
			return "Hi, " + nameOpt.get();
		}

		// TODO: ifPresent 또는 ifPresentOrElse로 로그 처리 + 기본값 반환
		static void printGreet_SAFE(Optional<String> nameOpt) {
			// 요구:
			// 1) 값이 있으면 System.out.println("[LOG] greet " + name);
			// 2) 값이 없으면 System.out.println("[LOG] greet null");
			// TODO ...

			// ifPresent 사용
			/*if (nameOpt.isPresent()) { // 값 존재 확인
				nameOpt.ifPresent(name -> System.out.println("[LOG] greet " + name)); // 값 존재하면 동작
			} else {
				System.out.println("[LOG] greet null");
			}*/

			// ifPresentOrElse 사용
			nameOpt.ifPresentOrElse(
				name -> System.out.println("[LOG] greet " + name),
				() -> System.out.println("[LOG] greet null")
			);
		}

		public static void main(String[] args) {
			System.out.println("== P2: isPresent vs ifPresent ==");
			Optional<String> nameNotNull = Optional.ofNullable(externalName(false));
			Optional<String> nameNull = Optional.ofNullable(externalName(true));
			System.out.println("nameNotNull → " + greet_BUGGY(nameNotNull)); // (예상: Hello, Alice)

			// BUGGY test
			try {
				System.out.println("nameNull → " + greet_BUGGY(nameNull)); // (예상: 실행 안 됨)
			} catch (Exception e) {
				System.out.println("caught: " + e.getClass().getSimpleName()); // (예상: NoSuchElementException)
			}

			// SAFE test
			System.out.println("\n-- SAFE --");
			printGreet_SAFE(nameNotNull); // (예상: [LOG] greet Alice)
			System.out.println("\n-- SAFE null --");
			printGreet_SAFE(nameNull); // (예상: [LOG] greet null)
		}
}