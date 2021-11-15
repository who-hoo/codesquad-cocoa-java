# 셸(Shell)

## 셸이란?

- 사용자가 운영체제 기능과 서비스를 조작할 수 있도록 사용자와 하드웨어 또는 운영체제간 인터페이스를 제공하는 프로그램
- 사용자가 입력한 명령어를 해석하여 커널에 전달, 커널과 사용자 간의 대화식 인터페이스를 제공한다
  - 운영체제 구조
    ![UNIX 시스템 구조](https://t1.daumcdn.net/cfile/blog/276283395822B6291F)
  - 별도의 외부 프로그램(시스템 콜)으로 운영체제에서 제공하는 프로그램을 실행
    - 운영체제가 운영체제의 각 기능을 사용할 수 있도록 제공하는 명령 또는 함수
    - 보통 시스템 콜을 직접 사용하기 보다, 해당 시스템콜을 사용해서 만든 각 언어별 라이브러리(API)를 사용한다.
      - JAVA의 경우 - `Runtime.exec()`, `ProcessBuilder`
        - [참고링크](https://www.baeldung.com/run-shell-command-in-java)

## 셸 종류

- 본셸(Bourne shell)
  - Bourne Shell (sh)
  - Korn Shell (ksh)
  - Bourne-Again Shell (bash) : GNU 프로젝트의 일환으로 개발됨, 리눅스 거의 디폴트임
  - z shell (zsh)
- C셸
  - C Shell (csh)
  - tc shell (tcsh)
