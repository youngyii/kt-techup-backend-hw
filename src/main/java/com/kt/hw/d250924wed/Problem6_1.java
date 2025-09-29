// 250924wed 자바 기초
// [Problem6-1] 영화관 티켓 요금 계산기 1 (조건 - 나이, 요일)
package com.kt.hw.d250924wed;
public class Problem6_1 {
    enum Age {CHILD, TEEN, ADULT, SENIOR}
    enum Day {WEEKDAY, WEEKEND}

    static int baseByAge(Age age) {
        return switch(age) {
            case CHILD -> 7000;
            case TEEN -> 9000;
            case ADULT -> 12000;
            case SENIOR -> 8000;
        };
    }

    static int baseByDay(Day day) {
        return switch(day) {
            case WEEKDAY -> 0;
            case WEEKEND -> 2000;
        };
    }

    static int price(Age age, Day day) {
        return baseByAge(age) + baseByDay(day);
    }

    public static void main(String[] args) {
        System.out.println(price(Age.ADULT, Day.WEEKDAY)); // 12000 (출력)
        System.out.println(price(Age.ADULT, Day.WEEKEND)); // 14000 (출력)
        System.out.println(price(Age.CHILD, Day.WEEKDAY)); // 7000 (출력)
        System.out.println(price(Age.SENIOR, Day.WEEKEND)); // 10000 (출력)
    }
}
