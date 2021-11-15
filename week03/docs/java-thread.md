# 자바 Thread 프로그래밍

## 프로세스와 쓰레드(process & thread)

- 프로세스(process) : 실행 중인 프로그램(program). 자원(resources. 메모리, CPU 등)과 쓰레드로 구성 → 공장
- 쓰레드(thread) : 프로세스 내에서 실제 작업을 수행하는 작업의 최소단위. 모든 프로세스는 최소한 하나의 쓰레드를 가지고 있다. → 일꾼
  - 가벼운 프로세스(LWP, light-weight process)라고 부르기도 한다.
  - 병행(concurrent) : 여러 쓰레드가 여러 작업을 동시에 진행
  - 병렬(parallel) : 하나의 작업을 여러 쓰레드가 나눠서 처리
- 멀티쓰레드 프로세스(multi-threaded process) : 둘 이상의 쓰레드를 가진 프로세스
- 하나의 새로운 프로세스를 생성하는 것보다 하나의 새로운 쓰레드를 생성하는 것이 더 적은 비용이 듬
  - 멀티태스킹(multi-tasking, 다중작업) : 여러 개의 프로세스를 동시에 실행
  - 멀티쓰레딩 : 하나의 프로세스 내에서 여러 쓰레드가 동시에 작업을 수행
    - 장점
      - 시스템 자원을 보다 효율적으로 사용할 수 있다.
      - 사용자에 대한 응답성(responseness)이 향상된다.
      - 작업이 분리되어 코드가 간결해진다.
    - 단점
      - 동기화(synchronization)에 주의해야 한다.
      - 교착상태(dead-lock)가 발생하지 않도록 주의해야한다.
        - 두 쓰레드가 자원을 점유한 상태에서 서로 상대편이 점유한 자원을 사용하려고 기다리느라 진행이 멈춰있는 상태
      - 각 쓰레드가 효율적으로 고르게 실행될 수 있게 해야한다.

## 쓰레드의 구현과 실행

- `Thread` 클래스를 상속

  ```java
  class MyThread extends Thread { // Thread 클래스를 상속해서 쓰레드 구현
  	public void run() { // Thread 클래스의 run()을 오버라이딩
  		// 작업내용
  	}
  }

  MyThread t1 = new MyThread(); // 쓰레드 생성
  t1.start(); // 쓰레드 실행
  ```

  - 자손 클래스에서 조상인 `Thread` 클래스의 메서드를 직접 호출 가능

- `Runnable` 인터페이스를 구현 - 좀 더 유연하고 좋은 방법

  ```java
  class MyThread implements Runnable { // Runnable 인터페이스를 구현해서 쓰레드 구현
  	public void run() { // Runnable 인터페이스의 추상메서드 run()을 구현
  		// 작업내용
  	}
  }

  Thread t2 = new Thread(new MyThread()); // Thread(Runnable r)
  t2.start();
  ```

  - `Thread` 클래스의 static 메서드인 `currentThread()`를 후출하여 쓰레드에 대한 참조를 얻어와야 `Thread`의 메서드 사용 가능

- 쓰레드의 실행 - `start()`
  - 쓰레드를 생성한 후에 `start()`를 호출해야 쓰레드가 작업을 시작
  - `start()`가 호출되었다고 바로 실행되는 것이 아니라, 일단 실행대기 상태에 있다가 자신의 차례가 되어야 실행되며 실행 순서는 OS의 스케쥴러가 작성한 스케쥴에 의해 결정된다.
  - 한 번 실행이 종료된 쓰레드는 다시 실행할 수 없음
  - `run()` - 생성된 쓰레드를 실행시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출
  - `start()` - 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택(call stack)을 생성한 다음에 `run()`을 호출해서, 생성된 호출스택에 `run()`이 첫 번째로 올라가게 함.
    - 모든 쓰레드는 독립적인 작업을 수행하기 위해 자신만의 호출스택을 생성, 쓰레드가 종료되면 작업에 사용된 호출스택은 소멸된다.

## 싱글 쓰레드와 멀티 쓰레드

- 쓰레드간의 작업전환(context switching)에 시간이 걸리기 때문에 멀티쓰레드 작업이 싱글쓰레드 작업보다 시간이 더 소요된다.
- 싱글 코어에서 단순히 CPU만을 사용하는 계산작업이라면 오히려 싱글쓰레드로 프로그래밍하는 것이 더 효율적
- 두 쓰레드가 서로 다른 자원을 사용하는 작업이나 쓰레드의 I/O 블락킹(blocking)이 발생하는 작업의 경우에는 싱글쓰레드 프로세스보다 멀티 쓰레드 프로세스가 더 효율적

## 쓰레드의 우선순위(priority of thread)

- 작업의 중요도에 따라 쓰레드의 우선순위를 다르게 하여 특정 쓰레드가 더 많은 작업시간을 갖게 할 수 있다. → 확률만 높여줄 뿐 실제 결과는 다를 수 있음
  - `void setPriority(int newPriority)` : 쓰레드의 우선순위를 지정한 값으로 변경
    - 쓰레드를 실행(`start()`)하기 전에만 우선수위를 변경할 수 있음
  - `int getPriority()` : 쓰레드의 우선순위를 반환
- 쓰레드가 가질 수 있는 우선순위의 범위는 1~10이며 숫자가 높을수록 우선순위가 높다.
- 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받음
- 쓰레드에 높은 우선순위를 주면 무조건 더 많은 실행시간과 실행기회를 가지는 것은 아니며, 차라리 작업에 우선순위를 두어 `PriorityQueue`에 저장해 놓고, 우선순위가 높은 작업이 먼저 처리되도록 하는 것이 나을 수 있음

## 쓰레드 그룹(thread group)

- 서로 관련된 쓰레드를 그룹으로 묶으서 다루기 위한 것
- 모든 쓰레드는 반드시 하나의 쓰레드 그룹에 포함되어 있어야 한다.
- 자신을 생성한 쓰레드(부모 쓰레드)의 그룹과 우선순위를 상속받음
- 쓰레드 그룹을 지정하지 않고 생성한 쓰레드는 자신을 생성한 쓰레드와 같은 쓰레드 그룹(main쓰레드 그룹)에 속하게 된다.
- `ThreadGroup getThreadGroup()` : 자신이 속한 쓰레드 그룹 반환
- `void uncaughtException(Thread t, Throwable e)` : 쓰레드 그룹의 쓰레드가 처리되지 않은 예외에 의해 실행이 종료되었을 때, JVM에 의해 이 메서드가 자동적으로 호출됨

## 데몬 쓰레드(daemon thread)

- 일반 쓰레드(non-daemon thread)의 작업을 돕는 보조적인 역할을 수행
- 일반 쓰레드가 모두 종료되면 자동적으로 종료
- 가비지 컬렉터, 자동저장, 화면 자동갱신 등에 사용
- 무한루프와 조건문을 이용해서 실행 수 대기하다가 특정조건이 만족되면 작업을 수행하고 다시 대기하도록 작성
- `boolean isDaemon()` : 쓰레드가 데몬 쓰레드인지 확인
- `void setDaemon(boolean on)` : 쓰레드를 데몬 쓰레드 또는 사용자 쓰레드로 변경
  - 반드시 `start()`를 호출하기 전에 실행되어야 함. 그렇지 않으면 `IllegalThreadStateException` 발생

## 쓰레드의 상태(thread status)

- `NEW` (생성) : 쓰레드가 생성되고 아직 `start()`가 호출되지 않은 상태
- `RUNNABLE` (실행대기) : 실행 중 또는 실행 가능한 상태
  - `start()`를 호출하면 바로 실행되는 것이 아니라 실행대기열에 저장되어 자신의 차례를 기다림
  - 실행대기열은 큐(queue)와 같은 FIFO 구조
  - 주어진 실행시간이 다 되거나 `yield()`를 만나면 다시 실행대기상태가 되고 다음 차례의 쓰레드 실행
- `BLOCKED` (일시정지) : 동기화블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태)  
  `WAITING`, `TIMED_WAITING` : 쓰레드의 작업이 종료되지는 않았지만 실행가능하지않은(unrunnable) 일시정지상태, `TIMED_WAITING`은 일시정지시간이 지정된 경우를 의미
  - `suspend()`, `sleep()`, `wait()`, `join()`, `I/O block`에 의해 일시정지 상태가 될 수 있음
  - 지정된 일시정지 시간이 다 되거나(time-out), `notify()`, `resume()`, `interrupt()`가 호출되면 다시 실행대기열에 저장
- `TERMINATED` (소멸) : 쓰레드의 작업이 종료된 상태
  - 실행을 모두 마치거나 `stop()`이 호출되면 쓰레드는 소멸

## 쓰레드의 실행제어

- 쓰레드의 실행을 제어할 수 있는 메서드가 제공
  - `static void sleep(long millis, [int nanos])`
    - 현재 쓰레드를 지정된 시간동안 일시정지 시킴
    - 지정한 시간이 지나고 나면, 자동적으로 다시 실행대기상태가 됨
    - 예외처리를 해야한다. → `InterruptedException`이 발생하면 깨어남
    - 특정 쓰레드를 지정해서 멈추게 하는 것은 불가능 (자기 자신에 대해서만 동작, `static`으로 선언)
  - `void join([long millis, int nanos])`
    - 지정된 시간동안 다른 특정 쓰레드가 작업을 수행하도록 함
    - 지정된 시간이 지나거나 작업이 종료되면 `join()`을 호출한 쓰레드로 다시 돌아와 계속 실행
    - 예외처리를 해야한다. → `InterruptedException`이 발생하면 깨어남
  - `void interrupt()`
    - `sleep()`, `wait()`, `join()`에 의해 대기상태(WAITING)인 쓰레드를 깨워 실행대기상태(RUNNABLE)로 만든다.
    - 해당 쓰레드에서는 `InterruptedException`이 발생함으로써 일시정지상태를 벗어나게 됨
  - `static void interrupted()`
    - 현재 쓰레드의 `interrupted` 상태를 알려주고, `false`로 초기화
  - `void stop()`
    - 쓰레드를 즉시 종료(소멸) 시킴
  - `void suspend()`
    - 쓰레드를 일시정지 시킴
  - `void resume()`
    - `suspend()`에 의해 일시정지상태에 있는 쓰레드를 실행대기상태로 만듬
  - `static void yield()`
    - 실행 중에 자신에게 주어진 실행시간을 다음 쓰레드에게 양보(yield)하고 자신은 실행대기상태가 된다.
    - 자기 자신에 대해서만 동작, `static`으로 선언
    - `yield()`와 `interrupt()`를 적절히 사용하면, 프로그램의 응답성을 높이고 보다 효율적인 실행 가능
  - `resume()`, `stop()`, `suspend()`는 쓰레드를 교착상태(dead-lock)로 만들기 쉽기 때문에 사용을 권장하지 않는다(deprecated).
    - 호환성을 위해 삭제하지 않는 것일 뿐 사용해선 안된다.
  - `volatile` 키워드 - 이 변수는 쉽게 바뀌는 변수니까, 복사본(cache)가 아닌 원본을 읽어올 것을 지정

## 쓰레드의 동기화(synchronization)

- 한 쓰레드가 진행중인 작업을 다른 쓰레드가 간섭하지 못하게 막는 것
  - 멀티 쓰레드 프로세스에서는 자원을 공유하기 때문에 다른 쓰레드의 작업에 영향을 미칠 수 있다.
  - 진행중인 작업이 다른 쓰레드에게 간섭받지 않게 하려면 **'동기화'** 필요
- 동기화하려면 간섭받지 않아야 하는 문장들(공유 데이터를 사용하는 코드 영역)을 '임계 영역(critical section)'으로 설정
- 임계영역은 락(lock)을 얻은 단 하나의 쓰레드만 수행가능(공유데이터-객체 1개에 락 1개)
- `synchronized`를 이용한 동기화

  1. 메서드 전체를 임계 영역으로 지정

     ```java
     public synchronized void calcSum() {
     	...
     }
     ```

     - `synchronized` 메서드가 호출된 시점부터 해당 메서드가 포함된 객체의 lock을 얻어 작업을 수행하다가 메서드가 종료되면 lock을 반환
     - `synchronized` 메서드 내에서 다른 `synchronized` 메서드를 호출 X (deadlock 방지)

  2. 특정한 영역을 임계 영역으로 지정

     ```java
     synchronized(객체의 참조변수) {
     	...
     }
     ```

     - 참조변수는 락을 걸고자하는 객체를 참조하는 것이어야 함
     - 메서드 내의 코드 일부를 블럭(synchronized block)으로 감싸면, 이 블럭의 영역 안으로 들어가면서부터 쓰레드는 지정된 객체의 lock을 얻게 되고 블럭을 벗어나면 lock을 반납

  - 임계 영역은 멀티쓰레드 프로그램의 성능을 좌우하기 때문에 가능하면 메서드 전체에 락을 걸기 보다는 `synchronized block`으로 임계영역을 최소화하는 것이 좋음

- `wait()`, `notify()`
  - 동기화의 효율을 높이기 위해 사용 → 한 쓰레드가 lock을 오래 가지는 일이 없어져 효율적
  - `Object` 클래스에 정의되어 있으며, 동기화 블록 내에서만 사용할 수 있다.
  - `wait()` : 객체의 lock을 풀고 쓰레드를 해당 객체의 waiting pool에 넣는다.
  - `notify()` : waiting pool에서 대기중인 쓰레드 중의 하나를 깨운다.
  - `notifyAll()` : waiting pool에서 대기중인 모든 쓰레드를 깨운다.
    - `notify()`보다 `notifyAll()`을 사용하기를 권장
    - 특정 thread가 통지를 받도록 제어할 수 없으므로 모두 깨운 후 스케쥴러에 CPU를 점유하는 것이 좀 더 공평
- `Lock`과 `Condition`을 이용한 동기화
  - JDK1.5부터 `java.util.concurrent.locks` 패키지 추가
  - `synchronized`블럭의 같은 메서드 내에서만 lock을 걸 수 있다는 제약이 불편할 때 사용
  - 자동적으로 잠금과 해제가 관리되는 `synchronized`와 달리 수동으로 lock을 잠그고 해제해야 함
  - lock 클래스의 종류
    - `ReentrantLock` : 재진입이 가능한 lock. 가장 일반적인 배타 lock
      - reentrant - 재진입할 수 있는
      - 특정 조건에서 lock을 풀고 나중에 다시 lock을 얻고 임계영역으로 들어와 이후 작업을 수행
      - 배타적 lock - 무조건 lock이 있어야만 임계영역의 코드 수행 가능
      - `ReentrantLock(boolean fair)`
        - 생성자의 매개변수를 `true`로 주면 가장 오래 기다린 쓰레드가 lock을 획득할 수 있게 공정(fair)하게 처리 → 성능은 떨어짐
      - `Condition` : 이미 생성된 lock으로부터 `newCondition()`을 호출해서 생성
        - `wait()` → `await()`
        - `notify()` → `signal()`
    - `ReentrantReadWriteLock` : 읽기에는 공유적이고, 쓰기에는 배타적인 lock
      - 읽기를 위한 lock과 쓰기를 위한 lock 제공
      - 읽기 lock이 걸려있으면, 다른 쓰레드가 읽기 lock을 중복해서 걸고 읽기 수행 가능
      - 읽기 lock이 걸린 상태에서 쓰기 lock을 걸거나 쓰기 lock이 걸린 상태에서 읽기 lock을 거는 것은 허용 X
    - `StampedLock` : `ReentrantReadWriteLock`에 낙관적인 lock의 기능 추가
      - lock을 걸거나 해지할 때 스탬프(`long` 타입의 정수값)를 사용
      - 읽기와 쓰기를 위한 lock 외에 낙관적 읽기 lock(optimistic reading lock)이 추가
        - 낙관적 읽기 lock은 쓰기 lock에 의해 바로 풀린다.
        - 무조건 읽기 lock을 걸지 않고, 쓰기와 읽기가 충돌할 때만 쓰기가 끝난 후에 읽기 lock을 거는 것

## fork & join

- JDK1.7 - fork & join 프레임웍 추가
- 하나의 작업을 작은 단위로 나눠서 여러 쓰레드가 동시에 처리하는 것을 쉽게 만들어 준다.
- 수행할 작업에 따라 클래스를 상속받아 구현
  - `RecursiveAction` : 반환값이 없는 작업을 구현할 때 사용
  - `RecursiveTask` : 반환값이 있는 작업을 구현할 때 사용
- `compute()` : 상속을 통해 구현할 추상 메서드 (`run()`과 같은 역할)
- `invoke()` : 작업 시작 (`start()`와 같은 역할)
- `fork()` : 해당 작업을 쓰레드 풀의 작업 큐에 넣음.
  - 작업 큐에 들어간 작업은 더 이상 나눌 수 없을 때까지 나뉜다.
  - 비동기 메서드(asynchronous method) : 일반적인 메서드와 달리 메서드를 호출만 할 뿐, 결과를 기다리지 않음.
- `join()` : 해당 작업의 수행이 끝날 때까지 기다렸다가, 수행이 끝나면 그 결과를 반환.
  - 동기 메서드(synchronous method)

---

## Reference

남궁성, 『Java의 정석 3판』, 도우출판(2016), p722-792(Chapter 13 - 쓰레드 thread).
