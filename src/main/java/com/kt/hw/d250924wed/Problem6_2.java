// 250924wed 자바 기초
// [Problem6-2] 영화관 티켓 요금 계산기 2 (추가 조건 - 상영 타입, 좌석등급, 최소 요금 하한 기준)
package com.kt.hw.d250924wed;
public class Problem6_2 {
    enum Age{CHILD, TEEN, ADULT, SENIOR}
    enum Day{WEEKDAY, WEEKEND}
    enum Show{MATINEE, STANDARD, LATE}
    enum Seat{STANDARD, PRIME}

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

    static int baseByShow(Show show) {
        return switch(show) {
            case MATINEE -> -2000;
            case STANDARD -> 0;
            case LATE -> -1000;
        };
    }

    static int baseBySeat(Seat seat) {
        return switch(seat) {
            case STANDARD -> 0;
            case PRIME -> 3000;
        };
    }

    static int price(Age age, Seat seat, Day day, Show show) {
        int price = baseByAge(age) + baseByDay(day) + baseByShow(show) + baseBySeat(seat);

        if (price < 5000) return 5000;
        else return price;
    }

    public static void main(String[] args) {
        System.out.println(price(Age.ADULT, Seat.STANDARD, Day.WEEKDAY, Show.STANDARD)); // 12000
        System.out.println(price(Age.ADULT, Seat.PRIME, Day.WEEKEND, Show.MATINEE)); // 15000 (=12000+3000+2000-2000)
        System.out.println(price(Age.CHILD, Seat.STANDARD, Day.WEEKDAY, Show.MATINEE)); // 5000 (하한 적용: 7000-2000=5000)
        System.out.println(price(Age.SENIOR, Seat.PRIME, Day.WEEKDAY, Show.LATE)); // 10000 (=8000+3000-1000)
    }
}