# 자바 날짜와 시간

## Calendar와 Date

- `java.util.Date` (JDK1.0) : deprecated
- `java.util.Calendar` (JDK1.1)
  - 추상클래스이기 때문에 직접 객체를 생성할 수 없고, 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.
    ```java
    Calendar cal = Calendar.getInstance();
    ```
  - `Calendar`를 상속받아 완전히 구현한 클래스 - `GregorianCalendar`, `BuddhistCalendar`
    - `getInstance()`가 시스템의 국가와 지역설정을 확인해서 태국인 경우에만 `BuddhistCalendar` 반환
  - `int get(int field)` : 원하는 필드의 값을 얻어옴
    - 매개변수로 사용되는 int값들은 `Calendar`에 정의된 `static` 상수들 → Java Doc을 보자
    - `Calendar.MONTH` : 0~11 (0부터 시작)
    - `Calendar.DAY_OF_WEEK` : 1(일요일)~7(토요일)
- `Date`와 `Calendar`간 변환
  - `Calendar` → `Date`
    ```java
    Calendar cal = Calendar.getInstance();
    Date d = new Date(cal.getTimeInMillis());
    ```
  - `Date` → `Calendar`
    ```java
    Date d = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    ```
- 두 날짜간의 차이
  - 두 날짜를 최소단위인 초단위로 변경한 다음 차이를 구한다.
  - `getTimeInMillis()` : 1/1000초
    - 초단위 : `getTimeInMillis()` / 1000
    - 일단위 : `getTimeInMillis()` / (24(시간) \* 60(분) \* 60(초) \* 1000)

## java.time 패키지

- `Date`와 `Calendar`가 가지고 있던 단점들을 해소하기 위해 JDK1.8부터 `java.time`패키지가 추가
- 불변(immutable) - 날짜나 시간을 변경하는 메서드들은 기존의 객체를 변경하는 대신 항상 변경된 새로운 객체를 반환
  - 기존 `Calendar` 클래스는 변경이 가능하므로, 멀티 쓰레드 환경에서 안전(thread-safe)하지 못함
- 날짜와 시간을 하나로 표현하는 `Calendar` 클래스와 달리 날짜와 시간을 별도의 클래스로 분리
  - `LocalTime` : 시간을 표현할 때 사용
  - `LocalDate` : 날짜를 표현할 때 사용
  - `LocalDateTime` : 날짜와 시간이 모두 필요할 때 사용
  - `ZonedDateTime` : 시간대(time-zone)까지 다뤄야 할 때 사용
  - `Period` : 두 날짜간의 차이를 표현
  - `Duration` : 시간의 차이를 표현
- 객체 생성 - `now()`, `of()`, `parse()`
- 특정 필드의 값 얻기 - `get()`, `getXXX()`
  - 값의 범위가 `Calendar`와 다름
  - 월 : 1~12(1부터 시작)
  - 요일 : 1(월요일)~7(일요일)
- 필드의 값 변경하기 - `with()`, `plus()`, `minus()`
  - `with()` - 원하는 필드를 직접 지정
  - `plus()`, `minus()` - 열거형 `ChronoUnit` 함께 사용
  - 필드를 변경하는 메서드들은 항상 새로운 객체를 생성해서 반환하므로 대입연산자를 같이 사용
- 날짜와 시간의 비교 - `isAfter()`, `isBefore()`, `isEqual()`
- `TemporalAdjusters` - 자주 쓰일만한 날짜 계산들을 정의해놓은 클래스
- 형식화(formatting)와 관련된 클래스 - `java.time.format.DateTimeFormatter`
  - `ofLocalizedXXX()` : 로케일에 종속적인 포멧터 생성
  - `ofPattern()` : 원하는 출력형식 직접 작성
- 문자열을 날짜와 시간으로 파싱 - `parse()`

---

## Reference

남궁성, 『Java의 정석 3판』, 도우출판(2016), p528-576(Chapter 10 - 날짜와 시간 & 형식화 date, time and formatting).
