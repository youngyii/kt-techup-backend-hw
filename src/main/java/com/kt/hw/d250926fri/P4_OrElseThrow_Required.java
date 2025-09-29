// 250926thu 모던자바와 람다식 과제
// [Problem4] orElseThrow 로 필수값 보장
// 필수 입력(토큰 등)이 없으면 조기 실패로 명확하게 처리
package com.kt.hw.d250926fri;
import java.util.Optional;

class P4_OrElseThrow_Required {
	static String externalToken(boolean asNull) {
		return asNull ? null : "token-xyz";
	}

	// (버그 유도) 나중에 터질 수 있는 코드
	static String useToken_BUGGY(String token) {
		// (예상) token이 null이면 어떤 일이? -> NullPointerException(NPE) 발생
		return token.toUpperCase();
	}

	// TODO: SAFE - orElseThrow로 null일 경우 IllegalArgumentException 발생,
	// null 아닐 경우 해당 문자열 반환
	static String useToken_SAFE(String token) {
		// TODO
		return Optional.ofNullable(token)
			.orElseThrow(() -> new IllegalArgumentException("token required"));
	}

	public static void main(String[] args) {
		System.out.println("== P4: orElseThrow ==");
		String tokenNotNull = externalToken(false);
		String tokenNull = externalToken(true);

		System.out.println("tokenNotNull(BUGGY) → " + useToken_BUGGY(tokenNotNull)); // (예상: TOKEN-XYZ)

		// BUGGY test
		try {
			System.out.println("tokenNull(BUGGY) → " + useToken_BUGGY(tokenNull)); // (예상: 실행 안 됨)
		} catch (Exception e) {
			System.out.println("BUGGY caught: " + e.getClass().getSimpleName()); // (예상: NullPointerException)
		}

		// SAFE test
		System.out.println("tokenNotNull(SAFE) → " + useToken_SAFE(tokenNotNull)); // (예상: token-xyz)
		try {
		System.out.println("tokenNull(SAFE) → " + useToken_SAFE(tokenNull)); // (예상: 실행 안 됨)
		} catch (Exception e) {
		System.out.println("SAFE caught: " + e.getMessage()); // (예상: token required)
		}
	}
}
