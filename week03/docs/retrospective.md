# 3주차 회고

## Daily

### Day11 - 2021/11/15 월요일

#### 한 일

- 미션 5 - 셸 만들기에 도전
  - 간단한 커맨드 몇가지(`pwd`, `ls`, `cd`, `mkdir`)만 구현하였다.
  - 그동안 거의 사용하는게 `ls`, `cd`뿐이였는데 구현을 위해 간단한 명령어들이 무엇이 있는지 찾아보다가 간단한 명령어들을 알게되었다!
- 셸(Shell)에 대해서 간단한 학습
  - 셸이란 무엇인지, 종류에는 어떤 것들이 있는지 정도만 간단히 학습하였다.
  - 터미널을 사용하면서 `bash`, `zsh`가 뭘까 궁금했었는데 드디어 알게되었다ㅋㅅㅋ
- 프로세스와 쓰레드 학습
  - 프로세스와 쓰레드가 무엇인지
  - 프로세스와 쓰레드의 상태변화(Life Cycle)
  - 자바에서 쓰레드를 어떻게 다루는지

#### 소감

- 미션을 처음 보고 너무 막막했다😭
- 그래도 제일 쉬운거부터 하나하나씩 구현해보자 싶어서 하나씩 기능을 구현해나가려는데 파일 경로잡는 것 때문에 한참을 헤맸다.
- 구현만 하다가 이게 과연 이 미션의 의도가 맞나...? 싶어서 운영체제 학습을 시작했다. ...만 🤯🤯🤯🤯🤯 이해가 하나도 가질 않는다...! 일단 읽어만 두고 내일의 수업을 기대해보자.

#### 잘한점

- 회고 양식을 바꿔보았다. 여태까지 감상위주로 주절주절 회고를 했었는데... 확실히 이 방법으로 회고를 하니 무엇을 학습했는지, 어제의 나와 비교해서 오늘 얼마나 성장했는지, 개선할 점은 무엇이 있을지 생각해볼 수 있어서 좋다.

#### 개선점

- 구현한 커맨드들 예외처리 + David 리뷰 내용 반영
- 지난주 가계부 미션때도 파일 입출력때문에 엄청 고생했는데, 그때그때 필요한 것만 찾아서 사용만 하고 나의 언어로 문서화해두지 않아서 똑같은 일이 반복되었다🥲 주말을 이용해서 꼬옥 정리해야지

### Day12 - 2021/11/16 화요일

#### 한 일

- 셸 리팩토링(리뷰 내용 반영)
  - `io.File` 사용하던 것 `nio.Files`로 변경
    - 사용하는 패키지를 통일하면 어떤 이점이 있어서 언지를 주셨나? 싶었는데 패키지를 통일해서 좋은게 아니라 [`nio(JDK1.4)`가 `io(JDK1.0)`보다 더 향상된 조작을 제공](https://www.baeldung.com/java-io-vs-nio)해서였다.
    - 앞으로도 가능하면 `nio` 패키지를 사용도록 하자!
  - `cd` 기능 추가(`~`, `/`, 절대경로 이동)
- `Map` 학습
  - `Hashtable`은 동기화가 구현되어있는 고대의 유믈, `HashMap`을 사용하자! 정도로만 알고 있었는데 `ConcurrentHashMap`은 또 뭐지. 내가 전부라고 생각했던 것 외에도 많은 구현체들이 있다는 것을 알게되었다.

#### 소감

- `nio` 패키지도 그렇고, `ConcurrentHashMap`도 그렇고 기본서 꼴랑 하나 읽어놓고 그게 전부일거라는 생각을 했는데 그게 아니였다.
- 한번쯤은 자바독 Map 들어가 볼 법도 하잖아... 필요한 것만 찾지 말고 좀 더 호기심을 가져보자.

#### 잘한점

- 고민되는 부분을 다른 사람들과 이야기를 나누고 아이디어를 얻었다.
  - 콘솔에 폴더와 파일을 어떻게 구분할지?
    - [출력 결과에 색을 입힐 수도 있다.](https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println)
    - 간단하게 뒤에 `/`를 붙여주기
- 혼자서 쉽게 답이 안나오는 것들도 같이 고민하면 답을 쉽게 얻을 수 있다. 고맙습니다.

#### 개선점

- `ls`는 `Files.list()`가 스트림을 반환해서 리팩토링을 포기했는데, 다음에 `FunctionalInterface`를 학습해서 다시 도전해보자.

### Day13 - 2021/11/17 수요일

#### 한 일

- 한글 시계 구현
  - 지난 주 가계부 구현(`String`으로 yyyymmdd 관리)과 알고리즘 문제(`Calendar`사용)를 풀고 리뷰를 받다가 `LocalDateTime`을 학습하고 사용해보면 좋을 것 같다는 피드백을 받았는데 이번 미션에 `LocalTime`을 사용해보게 되었다.
  - 확실히 기존의 클래스들이 가지고 있던 단점을 해소하기 위해 추가된 패키지라 그런지 더 편리했다.
  - 이것저것 해보면서 `LocalTime` 클래스에 조금은 익숙해졌다.
- 셸 내장 명령 추가
  - 월요일에 이론만 학습했던 쓰레드 개념을 코드에 적용해볼 수 있었다.

#### 소감

- 오늘 미션에서는 그동안 학습했던 것을 실제로 적용해볼 수 있었다. 확실히 직접 코드를 작성해보니 이론만 학습했던 것들이 정리가 되고 오래 기억에 남는다.
- 앞으로는 학습을 할 때에 시간이 걸리더라도 꼭 나의 코드로 작성해보자!

#### 잘한점

- 이번주에 새로 학습했던 내용을 많이 활용했다.
  - `LocalTime`
  - 콘솔에 색 입혀서 출력하기
  - Thread

#### 개선점

- 캘린더 추가로 구현해보면서 `LocalDate`도 학습해보자.
- 어찌저찌 구현은 하긴 했지만 아직 완전히 스레드 동작을 이해하지 못했는데, 추가로 스터디를 통해 학습해보자.
- 셸 커맨드들을 클래스로 따로 나눌 수 있을 듯
- 나아중에 `ExecutorService`, `ProcessBuilder` 한번 뭔지 찾아보기

### Day14 - 2021/11/18 목요일

#### 한 일

- 피어세션 미션 구현 코드 공유
  - `ProcessBuilder`를 사용해서 셸을 구현한 다른 팀원의 코드를 보고 `ProcessBuilder`가 뭔지 맛을 볼 수 있었다.
- 새벽 신세한탄

#### 소감

- 다른 팀원들과 구현 방법을 공유하면서 다양한 아이디어를 얻을 수 있어서 좋았다.
- 스레드 부분을 새벽에 같이 공부하는 멤버들과 함께 스터디해보기로 했다. 각자 파트를 나눠서 모두에게 발표하며 설명하는 식으로 하게 되었는데 걱정된다.
- 코코아도 벌써 끝나가고, 고민이 많아져서 새벽에 `David` 붙잡고 신세한탄을 했다ㅋㅋㅋ 늦은 시간에 잠도 못자게 괴롭혀서 죄송합니다...

#### 잘한점

- 서툴지만 미션을 구현하면서 어떤게 고민이였는지, 어떻게 해결하려고했는지 말해보려고 노력했다.

#### 개선점

- 한글시계 하드코딩한거 `Set`으로 개선해볼 수 있을듯...?
- 일찍 좀 자라!!!

### Day15 - 2021/11/19 금요일

#### 한 일

- 해커랭크 알고리즘 풀이
- 스레드 스터디 준비
  - 자바의 정석 스레드 부분 읽고정리
  - 예제 생각해보기

#### 소감

- 문제들이 영어로 되어있어서, 문제 해석하는데만도 한세월이 걸렸다. 영어 잘하고싶다...
- 어제 늦게자서 하루를 거의 날렸다. 의욕적인 것도 좋지만 항상 컨디션 조절을 잘하자.

#### 잘한점

- 스터디 발표 준비로 책의 예제가 아닌 나의 코드를 준비

#### 개선점

- 하려했으나 쉽지않다... 코드가 생각한것처럼 안돌아간다
- 개념을 잘 녹이고, 설명할 수 있도록... 그리고 생각한대로 돌아가도록 구현...