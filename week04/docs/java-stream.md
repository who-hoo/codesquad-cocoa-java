# 자바 스트림

## 스트림이란?

- 다양한 데이터 소스(컬렉션, 배열 등)를 표준화된 방법으로 다루기 위한 것
- 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의
  - 데이터 소스가 무엇이던 간에 관계없이 같은 방식으로 다룰 수 있음
  - 동일한 연산이 수행되어 일관성 있는 처리 가능
  - 코드의 재사용성이 높아짐
- 스트림의 연산
  ```java
  String[] strArr = { "dd", "aaa", "CC", "cc", "b" } ;
  Stream<String> stream = Stream.of(strArr); // 문자열 배열이 소스인 스트림
  Stream<String> filteredStream = stream.filter(); // 걸러내기(중간연산)
  Stream<String> distinctedStream = stream.distinct(); // 중복제거(중간연산)
  Stream<String> sortedStream = stream.sort(); // 정렬(중간연산)
  Stream<String> limitedStream = stream.limit(5); // 스트림 자르기(중간연산)
  int total = stream.count(); // 요소 개수 세기(최종 연산)
  ```
  - 스트림에 정의된 메서드 중에서 데이터 소스를 다루는 작업을 수행하는 것
  - 중간 연산 : 연산결과가 스트림인 연산. 반복적(0~n번)으로 적용가능
  - 최종 연산 : 연산결과가 스트림이 아닌 연산. 단 한번만 적용가능(스트림의 요소를 소모하면서 연산 수행)
- 스트림은 데이터 소스로부터 데이터를 읽기만할 뿐, 데이터 소스를 변경하지 않는다.
- 스트림은 일회용이다.
  - `Iterator`로 컬렉션의 요소를 모두 읽고 나면 다시 사용할 수 없는 것처럼, 스트림도 한번 사용하면 닫혀서 다시 사용할 수 없다.
  - 필요하다면 스트림을 다시 생성해야 한다.
- 최종 연산 전까지 중간 연간이 수행되지 않는다. - 지연된 연산
- 스트림은 작업을 내부 반복으로 처리해 반복문을 메서드의 내부에 숨길 수 있다.
- 스트림의 작업을 병렬로 처리 - 병렬스트림
  - `parallel()`이라는 메서드를 호출해서 병렬로 연산을 수행하도록 지시하면 내부적으로 자동으로 연산을 병렬로 수행.
  - `parallel()`로 호출한 것을 취소할 때는 `sequential()` 호출
- 기본형 스트림 - `IntStream`, `LongStream`, `DoubleStream`
  - 오토박싱&언박싱의 비효율이 제거됨(`Stream<Integer>` 대신 `IntStream` 사용)
  - 숫자와 관련된 유용한 메서드를 `Steam<T>`보다 더 많이 제공

## 스트림 만들기

- 컬렉션
  - 최고조상인 `Collection` 인터페이스의 `stream()`으로 컬렉션을 스트림으로 변환
    ```java
    Stream<T> Collection.stream();
    ```
- 배열
  - 객체 배열로부터 스트림 생성하기
    ```java
    Stream<T> Stream.of(T... values)
    Stream<T> Stream.of(T[])
    Stream<T> Arrays.stream(T[])
    Stream<T> Arrays.stream(T[] array, int startInclusive, int endExclusive)
    ```
- 특정 범위의 정수(`IntSteam`, `LongStream`)
  ```java
  IntStream IntStream.range(int begin, int end) // end 포함 X
  IntStream IntStream.rangeClosed(int begin, int end) // end 포함 O
  ```
- 임의의 수(난수)

  ```java
  // 해당 타입의 난수들로 이루어진 무한 스트림 반환
  IntStream ints()
  LongStream longs()
  DoubleStream doubles()

  // 크기가 정해지지 않은 무한 스트림이므로 limit()도 같이 사용해서 스트림의 크기 제한
  // 무한 스트림 -> 유한 스트림
  IntStream intStream = new Random().ints();
  intStream.limit(5).forEach(System.out::println);

  // 해당 타입의 난수들로 이루어진 매개변수 사이즈의 유한 스트림 반환
  // limit()를 사용하지 않아도 된다
  IntStream ints(long streamSize)
  LongStream longs(long streamSize)
  DoubleStream doubles(long streamSize)

  // 지정된 범위(begin~end, end 포함X)의 난수들로 이루어진 무한 스트림 반환
  IntStream ints(int begin, int end)
  LongStream longs(long begin, long end)
  DoubleStream doubles(double begin, double end)

  // 지정된 범위(begin~end, end 포함X)의 난수들로 이루어진 유한 스트림 반환
  IntStream ints(long streamSize, int begin, int end)
  LongStream longs(long streamSize, long begin, long end)
  DoubleStream doubles(long streamSize, double begin, double end)
  ```

- 람다식
  ```java
  // 무한 스트림 반환
  static <T> Stream<T> iterate(T seed, UnaryOperator<T> f) // 이전 요소에 종속적
  static <T> Stream<T> generate(Supplier<T> s) // 이전 요소에 독립적
  ```
  - `iterate()`는 이전 요소를 seed로 해서 다음 요소를 계산한다.
    - ex) `Stream<Integer> evenStream = Stream.iterate(0, n->n+2);`
  - `generate()`는 seed를 사용하지 않는다.
    - ex) `Stream<Double> randomStream = Stream.generate(Math::random);`
    - 정의된 매개변수의 타입이 `Supplier<T>`이므로 매개변수가 없는 람다식만 허용된다.
  - `iterate()`와 `generate()`에 의해 생성된 스트림은 기본형 스트림 타입의 참조변수로 다룰 수 없다.
    - 굳이 필요하다면, `mapToInt()`와 같은 메서드로 변환
- 파일
  ```java
  Stream<Path> Files.list(Path dir)
  ```
  - 지정된 디렉토리에 있는 파일의 목록을 소스로 하는 스트림을 생성하여 반환
- 빈 스트림(empty stream)
  ```java
  Stream emptyStream = Stream.empty();
  ```
  - 요소가 하나도 없는 비어있는 스트림
  - 스트림에 연산을 수행한 결과가 하나도 없을 때, `null`보다 빈 스트림을 반환하는 것이 낫다.

## 스트림의 중간연산

- `Stream<T> distinct()` : 중복을 제거
- `Stream<T> filter(Predicate<T> predicate)` : 조건에 안 맞는 요소 제외
- `Stream<T> limit(long maxSize)` : 스트림의 일부를 잘라낸다
- `Stream<T> skip(long n)` : 스트림의 일부를 건너뛴다
- `Stream<T> peek(Consumer<T> action)` : 스트림의 요소에 작업수행
  - `forEach()`와 달리 스트림의 요소를 소모하지 않으므로 중간 작업 결과를 확인(디버깅)할 때 사용
- `Stream<T> sorted()` : 스트림 요소의 기본정렬(Comparable)로 정렬  
  `Stream<T> sorted(Comparator<T> comparator)` : 지정된 Comparator로 정렬

  - JDK1.8부터 Comparator인터페이스에 추가된 static메서드와 디폴트 메서드들을 이용하면 정렬이 쉬워진다.
  - `Comparator`의 `comparing()`으로 정렬 기준을 제공

    ```java
    studentStream.sorted(Comparator.comparing(Student::getBan)
    	.thenComparing(Student::getTotalScore)
    	.thenComparing(Student::getName))
    	.forEach(System.out::println);
    ```

- `Stream<R> map(Function<T, R> mapper)`  
  `DoubleStream mapToDouble(ToDoubleFunction<T> mapper)`  
  `IntStream mapToInt(ToIntFunction<T> mapper)`  
  `LongStream mapToLong(ToLongFunction<T> mapper)`
  - 스트림의 요소 변환(Stream<T> → Stream<R>)
- `Stream<R> flatMap(Function<T, Stream<R>> mapper)`  
  `DoubleStream flatMapToDouble(Function<T, DoubleStream> m)`  
  `IntStream flatMapToInt(Function<T, IntStream> m)`  
  `LongStream flatMapToLong(Function<T, LongStream> m)`
  - 스트림의 스트림을 스트림으로 변환(Stream<T[]> → Stream<T>)

## 스트림의 최종연산

- `void forEach(Consumer<? super T> action)` : 각 요소에 지정된 작업 수행  
  `void forEachOrdered(Consumer<? super T> action)` : 병렬스트림인 경우에도 순서보장
- `long count()` : 스트림의 요소의 개수 반환
- `Optional<T> max(Comparator<? super T> comparator)` : 스트림의 최대값 반환  
  `Optional<T> min(Comparator<? super T> comparator)` : 스트림의 최소값 반환
- `Optional<T> findAny()` : 스트림의 요소를 아무거나 하나 반환. 병렬 스트림에 사용  
  `Optional<T> findFirst()` : 스트림의 첫 번째 요소를 반환. 순차 스트림에 사용
- `boolean allMatch(Predicate<T> p)` : 주어진 조건을 모든 요소가 만족시키는지 반환  
  `boolean anyMatch(Predicate<T> p)` : 주어진 조건을 한 요소라도 만족시키는지 반환  
  `boolean noneMatch(Predicate<T> p)` : 주어진 조건을 모든 요소가 만족시키지않는지 반환
- `Object[] toArray()` : 모든 요소를 배열로 반환  
  `A[] toArray(IntFunction<A[]> generator)`
- `Optional<T> reduce(BinaryOperator<T> accumulator)`  
  `T reduce(T identity, BinaryOperator<T> accumulator)`  
  `U reduce(U identity, BiFunction<U, T, U> accumulator, BinaryOperator<U> combiner)`
  - 정의된 연산이 아닌 프로그래머가 직접 지정하는 연산을 적용
  - 스트림의 요소를 하나씩 줄여가며 누적연산 수행(accumulate: 누적하다)
  - identity : 초기값
  - accumulator : 이전 연산결과와 스트림의 요소에 수행할 연산
  - combiner : 병렬처리된 결과를 합치는데 사용할 연산(병렬 스트림)
- `R collect(Collector<T, A, B> collector)`  
  `R collect(Supplier<R> supplier, BiConsumer<R, T> accumulator, BiConSumer<R, R> combiner)`

  - `Collector`를 매개변수로 하는 스트림의 최종연산. (그룹별 연산 가능)
  - `Collector` : 수집(collect)에 필요한 메서드를 정의해 놓은 인터페이스. 컬렉터는 이 인터페이스를 구현해야 한다.

    ```java
    public interface Collector<T, A, R> {
      Supplier<A> supplier();
      BiConsumer<A, T> accumalator();
      BinaryOperator<A> combiner();
      Function<A, R> finisher();

      Set<Characteristics> characteristics();
      ...
    }
    ```

    - `supplier()` : 작업 결과를 저장할 공간을 제공
    - `accumulator()` : 스트림의 요소를 수집(collect)할 방법을 제공. 어떻게 `supplier()`가 제공한 공간에 누적할 것인지
    - `combiner()` : 두 저장공간을 병합할 방법을 제공(병렬 스트림). 병렬 스트림인 경우, 여러 쓰레드에 의해 처리된 결과를 어떻게 합칠 것인가
    - `finisher()` : 결과를 최종적으로 변환할 방법을 제공. 변환이 필요 없다면, 항등 함수인 `Function.identity()`를 반환
    - `characteristics()` : 컬렉터가 수행하는 작업의 속성에 대한 정보를 제공

      - `Characteristics.CONCURRENT` : 병렬로 처리할 수 있는 작업
      - `Characteristics.UNORDERED` : 스트림의 요소의 순서가 유지될 필요가 없는 작업
      - `Characteristics.IDENTITY_FINISH` : `finisher()`가 항등 함수인 작업

    - `Collectors` : 다양한 기능의 컬렉터(`Collector`를 구현한 클래스)를 제공하는 클래스. static메서드로 미리 작성된 컬렉터를 제공한다.

      - 스트림을 컬렉션과 배열로 변환 - `toList()`, `toSet()`, `toMap()`, `toCollection()`, `toArray()`
      - 통계 - `counting()`, `summingInt()`, `averageInt()`, `maxBy()`, `minBy()`
      - 리듀싱 - `reducing()`
      - 문자열 결합 - `joining()`
      - 그룹화와 분할 - `groupingBy()`, `partitioningBy()`

        - `partitioningBy()` : 스트림을 2분할한다.

          ```java
          Map<Boolean, List<Student>> stuBySex = stuStream
          	.collect(partitioningBy(Student::isMale)); // 학생을 성별로 분할
          List<Student> maleStudent = stuBySex.get(true);
          List<Student> femaleStudent = stuBySex.get(false);
          ```

        - `groupingBy()` : 스트림을 n분할한다.

          ```java
          Map<Integer, List<Student>> stuByBan = stuStream
          	.collect(groupingBy(Student::getBan, toList())); // 학생을 반별로 분할
          ```

## Optional

```java
public final class Optional<T> {
	private final T value;
}
```

- `T` 타입 객체의 래퍼클래스
- 널 체크를 위한 `if`문 없이도 `NullPointerException`이 발생하지 않는 보다 간결하고 안전한 코드를 작성하는 것이 가능해짐
- `Optional<T>` 객체 생성하기
  - `Optional.of()`
  - `Optional.ofNullable()` : 참조변수의 값이 `null`일 가능성이 있을 때 사용
  - `Optional.empty()` : 기본값으로 초기화
- `Optional<T>` 객체의 값 가져오기
  - `get()` : `null`이면 예외발생
  - `orElse()` : `null` 이면 `""` 반환
  - `orElseGet()` : 람다식 사용 가능
  - `orElseThrow()` : `null`이면 예외 지정하여 반환 가능
  - `isPresent()` : 값이 `null`이면 `false`, 아니면 `true` 반환
- `OptionalInt`, `OptionalLong`, `OptionalDouble`
  - 기본형 값을 감싸는 래퍼클래스
  - `getAsInt()`, `getAsLong()`, `getAsDouble()`
  - `isPresent`라는 인스턴스 변수로 기본값이 저장된 객체와 빈 객체를 구분

---

## Reference

남궁성, 『Java의 정석 3판』, 도우출판(2016), p814-865(Chapter 14 - 람다와 스트림 中 2.스트림).
