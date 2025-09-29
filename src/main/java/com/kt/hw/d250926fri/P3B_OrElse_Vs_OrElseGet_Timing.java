// 250926thu 모던자바와 람다식 과제
// [Problem3-2] orElse, orElseGet 호출 시점(평가 시점) 확인
// 목표: 둘 간의 차이 및 활용성에 대해여 생각해보기
package com.kt.hw.d250926fri;
import java.util.Optional;
import java.util.function.Supplier;

class P3B_OrElse_Vs_OrElseGet_Timing {
	public static void main(String[] args) {
		System.out.println("== orElse vs orElseGet: 평가 시점 비교 ==");

		Optional<String> present = Optional.of("VALUE");
		Optional<String> empty = Optional.empty();

		// 기본값 생성기: 호출될 때만 로그 출력
		Supplier<String> makeDefault = () -> {
			System.out.println("makeDefault.get() 호출됨");
			return "DEFAULT";
		};

		// A. orElse: 인자를 '먼저' 만들어서 넘김
		System.out.println("-- orElse --");
		System.out.println("present → " + present.orElse(makeDefault.get()));
		// (예상: makeDefault.get() 호출됨 present → VALUE)
		System.out.println("empty → " + empty.orElse(makeDefault.get()));
		// (예상: makeDefault.get() 호출됨 empty → DEFAULT)

		// B. orElseGet: 필요할 때만 Supplier 실행(Optional이 비어있을 때)
		System.out.println("-- orElseGet --");
		System.out.println("present → " + present.orElseGet(makeDefault));
		// (예상: present → VALUE)
		System.out.println("empty → " + empty.orElseGet(makeDefault));
		// (예상: makeDefault.get() 호출됨 empty → DEFAULT)
	}
}
