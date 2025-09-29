// 250926thu 모던자바와 람다식 과제
// [Problem1] of와 ofNullable의 관계
// 목표: null 여부에 따른 동작 차이 이해 및 개선
package com.kt.hw.d250926fri;
import java.util.Optional;

public class P1_Of_OfNullable {
	// 외부에서 들어온 값(널 가능)
	static String externalEmail(boolean asNull) {
		return asNull ? null : "user@example.com"; // asNull이 true면 null 반환, 아니면 "user@example.com" 반환
	}

	// (버그 유도) null일 때 of 사용 → NPE
	static Optional<String> wrapEmail_BUGGY(String email) {
		// (예상) email이 null이면 어떤 일이 발생? -> NPE(NullPointerException) 발생
		// Optional.of는 null 허용 X
		return Optional.of(email);
	}

	// TODO: null safe 버전 작성
	// ( null 발생 시, null이 아닌 다른 문자열로 변경하여 반환 )
	static Optional<String> wrapEmail_SAFE(String email) {
		// TODO: email이 null이면 "safe@example.com"으로 대체하여 Optional로 감싸 반환
		// hint : orElse 활용.

		// Optional.ofNullable은 null 허용
		// email이 null -> Optional.empty() 반환 -> orElse 사용해 "safe@example.com" 반환
		// email이 null이 아님 -> Optional[email] 반환
		// 다시 Optional.of로 감싸 반환

		return Optional.of(Optional.ofNullable(email).orElse("safe@example.com"));
	}

	public static void main(String[] args) {
		System.out.println("== P1: of vs ofNullable ==");
		String notNullValue = externalEmail(false);
		String nullValue = externalEmail(true);
		System.out.println("notNullValue = " + notNullValue); // (예상: user@example.com)
		System.out.println("wrap notNullValue (BUGGY) = " + wrapEmail_BUGGY(notNullValue)); // (예상: Optional[user@example.com])

		// BUGGY test
		try {
			System.out.println("wrap nullValue (BUGGY) = " + wrapEmail_BUGGY(nullValue)); // (예상: 실행 안 됨)
		} catch (Exception e) {
			System.out.println("caught: " + e.getClass().getSimpleName()); // (예상: NullPointerException)
		}

		// SAFE test
		// TODO: NULL SAFE 버전으로 아래 출력이 기대대로 나오도록 구현
		System.out.println("wrap notNullValue (SAFE) = " + wrapEmail_SAFE(notNullValue)); // (예상: Optional[user@example.com])
		System.out.println("wrap nullValue (SAFE) = " + wrapEmail_SAFE(nullValue)); // (예상: Optional[safe@example.com])
	}
}