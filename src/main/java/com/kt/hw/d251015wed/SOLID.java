// 25.10.15. 백엔드 이신영 과제
package com.kt.hw.d251015wed;

// SOLID 원칙 예제 코드

// ---------------------------------------------------------------------------
// 1. SRP (Single Responsibility Principle) - 단일 책임 원칙
// 각 클래스는 하나의 책임만 가져야 한다

// 잘못된 예시 - 하나의 클래스가 여러 책임을 가짐
class BadEmployee {
	private String name;
	private double salary;

	public void calculatePay() { /* 급여 계산 */ }
	public void saveToDatabase() { /* DB 저장 */ }
	public void sendEmail() { /* 이메일 전송 */ }
}

// 올바른 예시 - 각 클래스가 하나의 책임만 담당
class Employee {
	// 직원 정보만 관리
	private String name;
	private double salary;

	public String getName() { return name; }
	public double getSalary() { return salary; }
}

class PayrollCalculator {
	// 급여 계산만 담당
	public double calculatePay(Employee employee) {
		return employee.getSalary() * 1.1; // 예시: 10% 보너스
	}
}

class EmployeeRepository {
	// 데이터베이스 저장만 담당
	public void save(Employee employee) {
		// DB 저장 로직
	}
}

// ---------------------------------------------------------------------------
// 2. OCP (Open-Closed Principle) - 개방-폐쇄 원칙
// 확장에는 열려있고 수정에는 닫혀있어야 한다

// 도형 넓이 계산 예제
interface Shape {
	double calculateArea();
}

class Rectangle implements Shape {
	private double width;
	private double height;

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double calculateArea() {
		return width * height;
	}
}

class Circle implements Shape {
	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public double calculateArea() {
		return Math.PI * radius * radius;
	}
}

// 새로운 도형을 추가해도 기존 코드를 수정할 필요가 없음
class Triangle implements Shape {
	private double base;
	private double height;

	public Triangle(double base, double height) {
		this.base = base;
		this.height = height;
	}

	@Override
	public double calculateArea() {
		return 0.5 * base * height;
	}
}

// ---------------------------------------------------------------------------
// 3. LSP (Liskov Substitution Principle) - 리스코프 치환 원칙
// // 자식 클래스는 부모 클래스를 대체할 수 있어야 한다 -> 부모가 하던 일을 그대로 할 수 있어야 한다

// 잘못된 예시 - 하위 클래스가 부모의 행위를 깨뜨림
class BadBird {
	void fly() {
		System.out.println("새가 하늘을 납니다");
	}
}

class BadPenguin extends BadBird {
	@Override
	void fly() {
		// 펭귄은 날 수 없으므로 예외 발생
		throw new UnsupportedOperationException("펭귄은 날 수 없습니다!");
	}
}

// 올바른 예시
abstract class Bird {
	abstract void eat();
}

// 날 수 있는 새 전용 인터페이스
interface Flyable {
	void fly();
}

class Sparrow extends Bird implements Flyable {
	@Override
	void eat() {
		System.out.println("참새가 벌레를 먹습니다");
	}

	@Override
	public void fly() {
		System.out.println("참새가 하늘을 납니다");
	}
}

class Penguin extends Bird {
	@Override
	void eat() {
		System.out.println("펭귄이 물고기를 먹습니다");
	}
}

// ---------------------------------------------------------------------------
// 4. ISP (Interface Segregation Principle) - 인터페이스 분리 원칙
// 클라이언트는 자신이 사용하지 않는 메서드에 의존하지 않아야 한다

// 잘못된 예시 - 너무 많은 메서드를 가진 인터페이스
interface BadWorker {
	void work();
	void eat();
	void sleep();
}

// 올바른 예시 - 인터페이스를 작게 분리
interface Workable {
	void work();
}

interface Eatable {
	void eat();
}

interface Sleepable {
	void sleep();
}

class Human implements Workable, Eatable, Sleepable {
	@Override
	public void work() {
		System.out.println("일을 합니다");
	}

	@Override
	public void eat() {
		System.out.println("음식을 먹습니다");
	}

	@Override
	public void sleep() {
		System.out.println("잠을 잡니다");
	}
}

class Robot implements Workable {
	@Override
	public void work() {
		System.out.println("일을 합니다");
	}
	// 로봇은 먹거나 자지 않으므로 불필요한 메서드를 구현하지 않음
}

// ---------------------------------------------------------------------------
// 5. DIP (Dependency Inversion Principle) - 의존성 역전 원칙
// 고수준 모듈은 저수준 모듈에 의존하면 안되고, 둘 다 추상화에 의존해야 한다
// 고수준 모듈은 구체적인 구현체가 아니라 인터페이스/추상 클래스에 의존
// 저수준 모듈은 그 추상화를 구현

// 메시지 전송 예제
interface MessageSender {
	void sendMessage(String message);
}

class EmailSender implements MessageSender { // 저수준 모듈(구체적 구현)
	@Override
	public void sendMessage(String message) {
		System.out.println("이메일로 전송: " + message);
	}
}

class SmsSender implements MessageSender { // 저수준 모듈(구체적 구현)
	@Override
	public void sendMessage(String message) {
		System.out.println("SMS로 전송: " + message);
	}
}

class NotificationService { // 고수준 모듈
	private MessageSender messageSender;

	// 새로운 구현체 추가 시 고수준 모듈 수정 불필요
	public NotificationService(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public void notify(String message) {
		messageSender.sendMessage(message);
	}
}

public class SOLID {
	public static void main(String[] args) {
		// ---------------------------------------------------------------------------
		// 3. LSP 예제 실행

		// 잘못된 예시
		BadBird badBird = new BadPenguin(); // 업캐스팅
		try {
			badBird.fly(); // 예외 발생
		} catch (UnsupportedOperationException e) {
			// 예외 메시지 출력
			System.out.println("예외 발생: " + e.getMessage());
		}

		// 올바른 예시
		Bird sparrow = new Sparrow();
		Bird penguin = new Penguin();

		sparrow.eat();
		penguin.eat();

		// 날 수 있는 새만 Flyable로 취급 -> 안전한 대체 가능
		Flyable flyingBird = new Sparrow();
		flyingBird.fly();

		// ---------------------------------------------------------------------------
		// 5. DIP 예제 실행
		NotificationService emailNotification = new NotificationService(new EmailSender());
		emailNotification.notify("안녕하세요!");

		NotificationService smsNotification = new NotificationService(new SmsSender());
		smsNotification.notify("안녕하세요!");
	}
}
