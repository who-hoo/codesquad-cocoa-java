# 자바 람다식

## 람다식(Lambda expression)이란?

- 함수(메서드)를 간단한 '식(expression)'으로 표현한 것
- 익명 함수(이름이 없는 함수, anonymous function)
- 자바에서 함수형 프로그래밍(functional programming)을 구현하는 방식
  - 순수 함수(pure function)를 구현하고 호출
  - 매개 변수만을 사용하도록 만든 함수로 외부 자료에 부수적인 영향(side effect)가 발생하지 않도록 함
  - 입력 받은 자료를 기반으로 수행되고 외부에 영향을 미치지 않으므로 병렬처리등에 가능
  - 안정적인 확장성있는 프로그래밍 방식
- JDK1.8부터 추가된 람다식(lambda expression)의 도입으로 인해, 자바는 객체지향언어인 동시에 함수형 언어가 되었다.
- 함수와 메서드의 차이
  - 근본적으로 동일. 함수는 일반적 용어, 메서드는 객체지향개념 용어
  - 함수는 클래스에 독립적, 메서드는 클래스에 종속적(자바에서 모든 메서드는 클래스에 포함되어야 함)
- 클래스를 새로 만들고, 객체도 생성해야 비로소 메서드를 호출할 수 있었던 모든 과정 없이 람다식 자체만으로 메서드의 역할을 대신할 수 있다.
- 메서드의 매개변수로 전달되어지는 것이 가능하고, 메서드의 결과로 반환될 수도 있다. → 메서드를 변수처럼 다루는 것이 가능

## 람다식 작성하기

```java
int max(int a, int b) {
	return a > b ? a : b;
}
```

1. 메서드의 이름과 반환타입을 제거하고 `->` 를 블럭{} 앞에 추가한다.

   ```java
   (int a, int b) -> {
   	return a > b ? a : b;
   {
   ```

2. 반환값이 있는 경우, 식이나 값만 적고 `return`문 생략 가능(끝에 `;` 안 붙임)

   ```java
   (int a, int b) -> a > b ? a : b
   ```

   - 식의 연산결과가 자동적으로 반환값이 된다.
   - 이 때는, '문장(statement)'이 아닌 '식(expression)'이므로 끝에 `;`를 붙이지 않는다.

3. 매개변수의 타입이 추론 가능하면 생략가능(대부분의 경우 생략 가능)

   ```java
   (a, b) -> a > b ? a : b
   ```

   - `(int a, b) -> a > b ? a : b`와 같이 어느 하나의 타입만 생략하는 것은 허용되지 않는다.

- 주의사항

  1. 매개변수가 하나인 경우, 괄호() 생략가능(타입이 없을 때만)

     ```java
     a -> a * a // OK
     int a -> a * a // Error
     ```

  2. 블록 안의 문장이 하나뿐일 때, 괄호{} 생략가능(끝에 `;` 안붙임)

     ```java
     (int i) -> System.out.println(i)
     ```

  3. 하나뿐인 문장이 `return`문이면 괄호{} 생략불가

     ```java
     (int a, int b) -> { return a > b ? a : b; } // OK
     (int a, int b) ->  return a > b ? a : b // Error
     ```

## 함수형 인터페이스(Functional Interface)

- 람다식은 익명 함수가 아니라 익명 객체이다.
- 람다식(익명 객체)를 다루기 위한 참조변수의 타입으로 함수형 인터페이스를 사용한다.
- 함수형 인터페이스 : 람다식을 다루기 위해 사용하는 단 하나의 추상 메서드만 선언된 인터페이스

  ```java
  @FunctionalInterface
  interface MyFunction {
  	public abstract int max(int a, int b);
  }

  MyFunction f = new MyFunction() { // 익명 클래스
  	public int max(int a, int b) {
  		return a > b ? a : b;
  	}
  }

  MyFunction f = (a, b) -> a > b ? a : b; // 익명 객체를 람다식으로 대체

  int value = f.max(3, 5);
  ```

  - 익명함수와 매개변수만으로 구현되므로 두 개 이상의 매서드가 선언되면 어느 메서드의 호출인지 모호해 지므로 단 하나의 메서드만 선언해야 함
  - 람다식도 실제로는 익명 객체이고, 인터페이스를 구현한 익명 객체의 메서드 `max()`와 람다식의 매개변수의 타입과 개수 그리고 반환값이 일치하기 때문에 인터페이스를 구현한 긱명 객체를 람다식으로 대체할 수 있다.

- 람다식은 오직 함수형 인터페이스로만 형변환이 가능하다.

## java.util.function 패키지

- 자주 사용되는 다양한 함수형 인터페이스를 제공
- 가장 기본적인 함수형 인터페이스
  - `java.lang.Runnable`
    - `void run()` : 매개변수도 없고, 반환값도 없음
  - `Supplier<T>`
    - `T get()` : 매개변수는 없고, 반환값만 있음
  - `Consumer<T>`
    - `void accept(T t)` : 매개변수만 있고, 반환값은 없음
  - `Function<T, R>`
    - `R apply(T t)` : 일반적인 함수. 하나의 매개변수를 받아서 결과를 반환
  - `Predicate<T>`
    - `boolean test(T t)` : 조건식을 표현하는데 사용. 매개변수는 하나, 반환 타입은 `boolean`
- 매개변수가 2개인 함수형 인터페이스 - 이름 앞에 접두사 `Bi`가 붙는다.
  - `BiConsumer<T, U>`
    - `void accept(T t, U u)` : 두 개의 매개변수만 있고, 반환값은 없음
  - `BiPredicate<T, U>`
    - `boolean test(T t, U u)` : 조건식을 표현하는데 사용. 두개의 매개변수
  - `BiFunction<T, U, R>`
    - `R apply(T t, U u)` : 두 개의 매개변수를 받아서 결과를 반환
- 두 개 이상의 매개변수를 갖는 함수형 인터페이스가 필요하다면 직접 만들어서 써야 한다.
- 매개변수의 타입과 반환타입이 일치하는 함수형 인터페이스
  - `UnaryOperator<T>`
    - `T apply(T t)` : `Function`의 자손, `Function`과 달리 매개변수와 결과의 타입이 같다.
  - `BinaryOperator<T>`
    - `T apply(T t, T t)` : `BiFunction`의 자손, `BiFunction`과 달리 매개변수와 결과의 타입이 같다.
- 기본형을 사용하는 함수형 인터페이스
  - `DoubleToIntFunction`
    - `int applyAsInt(double d)` : **A**To**B**Function은 입력이 A타입, 출력이 B타입
  - `ToIntFunction<T>`
    - `int applyAsInt(T value)` : to**B**Function은 입력은 제네릭 타입, 출력이 B타입
  - `IntFunction<R>`
    - `R apply(T t, U u)` : **A**Function은 입력이 A타입, 출력이 제네릭 타입
  - `ObjIntConsumer<T>`
    - `void accept(T t, U u)` : Obj**A**Function은 입력이 T, A타입, 출력은 없다.

## Function의 합성과 Predicate의 결합

- `Function`의 합성
  - 두 람다식을 합성해서 새로운 람다식을 만들 수 있다.
  - `f.andThen(g)` : 함수 f를 먼저 적용하고, 함수 g를 적용
  - `f.compose(g)` : 함수 g를 먼저 적용하고, 함수 f를 적용
- `Predicate`의 결합
  - `and()`, `or()`, `negate()`로 연결해서 `Predicate`를 하나로 결합(default 메서드)
  - 등가비교를 위한 `Predicate`의 작성에는 `isEqual()`를 사용(static 메서드)

## 메서드 참조(method reference)

- 하나의 메서드만 호출하는 람다식은 '메서드 참조'로 간단히 할 수 있다.
  - static 메서드 참조
    - 람다 : `(x) -> ClassName.method(x)`
    - 메서드 참조 : `ClassName::method`
  - 인스턴스 메서드 참조
    - 람다 : `(obj, x) -> obj.method(x)`
    - 메서드 참조 : `ClassName::method`
  - 특정 객체 인스턴스 메서드 참조
    - 람다 : `(x) -> obj.method(x)`
    - 메서드 참조 : `obj::method`
- 생성자를 호출하는 람다식도 메서드 참조로 변환할 수 있다.

  ```java
  Supplier<MyClass> s = () -> new MyClass(); // 람다식
  Supplier<MyClass> s = MyClass::new;        // 메서드 참조

  Function<Integer, MyClass> f = (i) -> new MyClass(i);
  Function<Integer, MyClass> f = MyClass::new;

  BiFunction<Integer, String, MyClass> bf = (i, s) -> new MyClass(i, s);
  BiFunction<Integer, String, MyClass> bf = MyClass::new;

  Function<Integer, int[]> f = x -> new int[x];
  Function<Integer, int[]> f = int[]::new;
  ```

- 메서드 참조는 람다식을 마치 static변수처럼 다룰 수 있게 해준다.

---

## Reference

남궁성, 『Java의 정석 3판』, 도우출판(2016), p794-813(Chapter 14 - 람다와 스트림 中 1.람다식).
