# 프로세스(Process)와 쓰레드(Thread)

## 프로세스란?

- 메모리에 올려져서, 실행 중인 프로그램
- 자원(resources. 메모리, CPU 등)과 쓰레드로 구성
- 작업, task, job 이라는 용어와 혼용
- 응용프로그램 ≠ 프로세스
  - 하나의 응용 프로그램은 여러 개의 프로세스가 상호 작용을 하면서 실행될 수도 있음

## 프로세스의 상태

![프로세스의 상태](https://zitoc.com/wp-content/uploads/2019/02/process-state.png)

- running state : 현재 CPU에서 실행 상태
- ready state : CPU에서 실행 가능 상태
- waiting state : 특정 이벤트 발생 대기 상태(ex. 프린팅이 다 되었다!)

## 쓰레드란?

- 프로세스 내에서 실제 작업을 수행하는 작업의 최소단위
- 모든 프로세스는 최소한 하나의 쓰레드를 가지고 있다
- Light Weight Process 라고도 함
- 스레드들은 동시에 실행 가능
- 프로세스 안에 있으므로, 프로세스의 데이터를 모두 접근 가능
- 각기 실행이 가능한 stack 존재

## 쓰레드의 장단점

- 장점
  1. 사용자에 대한 응답성 향상
  2. 자원 공유 효율
     - IPC 기법과 같이 프로세스간 자원 공유를 위해 번거로운 작업이 필요 없음
     - 프로세스 안에 있으므로, 프로세스의 데이터를 모두 접근 가능
  3. 작업이 분리되어 코드가 간결(작성하기 나름)
- 단점
  1. 스레드 중 한 스레드만 문제가 있어도, 전체 프로세스가 영향을 받음
  2. 스레드를 많이 생성하면, Context Switching이 많이 일어나, 성능 저하(리눅스 OS에서는 Thread를 Process와 같이 다룸)

## Deadlock(교착상태) & Starvation(기아상태)

- Deadlock
  - 무한 대기 상태 : 두 개 이상의 작업이 서로 상대방의 작업이 끝나기만을 기다리고 있기 때문에, 다음 단계로 진행하지 못하는 상태(배치 처리 시스템에서는 일어나지 않는 문제로 프로세스, 스레드 둘다 이와 같은 상태가 일어날 수 있음)
  - 발생 조건(다음 네 가지 조건이 모두 성립될 때, 교착상태 발생 가능성 있음)
    1. 상호배제(Mutual exclusion) : 프로세스들이 필요로 하는 자원에 대해 배타적인 통제권을 요구한다.
    2. 점유대기(Hold and wait) : 프로세스가 할당된 자원을 가진 상태에서 다른 자원을 기다린다.
    3. 비선점(No preemption) : 프로세스가 어떤 자원의 사용을 끝낼 때까지 그 자원을 뺏을 수 없다.
    4. 순환대기(Circular wait) : 각 프로세스는 순환적으로 다음 프로세스가 요구하는 자원을 가지고 있다.
- Starvation
  - 특정 프로세스의 우선순위가 낮아서 원하는 자원을 계속 할당 받지 못하는 상태
  - 교착상태와 기아상태
    - 교착상태는 여러 프로세스가 동일 자원 점유를 요청할 때 발생
    - 기아상태는 여러 프로세스가 부족한 자원을 점유하기 위해 경쟁할 때, 특정 프로세스는 영원히 자원 할당이 안되는 경우를 주로 의미함
  - 해결방안
    - 우선순위 변경
      - 우선순위를 수시로 변경해서, 각 프로세스가 높은 우선순위를 가질 기회주기
      - 오래 기다린 프로세스의 우선순위를 높여주기
      - 우선순위가 아닌, 요청 순서대로 처리하는 FIFO 기반 요청큐 사용