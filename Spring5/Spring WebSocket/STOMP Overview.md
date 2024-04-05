## Overview

STOMP는 Simple Text oriented Messaging Protocol의 줄임말이다.
STOMP는 enterprise message broker에 연결하기 위한 scripting language를 위해 만들어졌다.(ex,. Ruby, Python, Perl).

STOMP는 일반적으로 사용되는 Messaging Pattern의 최소한의 부분들을 처리하기 위해 설계되었다.

STOMP는 TCP, WebSocket과 같은 신뢰가능한 양방향 streaming network protocol을 통해 사용할 수 있다.

STOMP는 Text 중심 Protocol이지만, Message의 Payload에 Binary도 들어갈 수 있다.

STOMP는 HTTP frame 기반의 protocol이다. 아래의 예시는 STOMP frame이다.

```
COMMAND
header1:value1
header2:value2

Body^@
```
클라이언트는 `SEND` 혹은 `SUBSCRIBE` 커맨드를 이용한 메세지를 통해 전송 및 구독할 수 있다.
`destination` 헤더는 메세지의 받을 곳을 지정한다.

이건 간단한 pub-sub 메커니즘이다. 이러한 메커니즘을 사용해 브로커를 통해 연결된 클라이언트로 메세지를 보내거나 서버에 메세지를 보내 작업을 실행 요청을 보낼 수 있다.

Spring STOMP를 사용하는 경우 Spring WebSocket 어플리케이션은 클라이언트에 대한 STOMP 브로커 역할을 한다.

메세지는 `@Controller` 메세지 핸들링 메서드 또는 구독 추적이나 구독한 사용자에게 메시지를 broadcast하는 간단한 In-memory broker 로 라우된다.

메세지의 broadcast를 위해 전용 STOMP 브로커를 구성할 수도 있다.(ex,. RabbitMQ, ActiveMQ 등)

이 경우 Spring은 브로커에 대한 TCP 연결을 유지하고, 브로커에 메시지를 중계하고 브로커에 있는 메세지를 연결된 WebSocket 클라이언트들에게 전달한다.

HTTP-based Security, 유효성 검사, 등 친숙한 프로그래밍 모델을 사용할 수 있다.

아래 예시는 서버가 주기적으로 `SimpleMessagingTemplate`을 통해 브로커에 메세지를 보내는 작업을 통해 주식 시세를 수신 구독하는 클라이언트를 보여준다.


```
SUBSCRIBE
id:sub-1
destination:/topic/price.stock.*

^@
```

아래 예시는 서버가`@MessageMapping`메서드를 통해 핸들링 할 수 있다는 것과, 클라이언트가 트레이드 request을 보낸 것을 보여준다.


```
SEND
destination:/queue/trade
content-type:application/json
content-length:44

{"action":"BUY","ticker":"MMM","shares",44}^@
```

실행 후에, 서버는 트레이드 확인 메세지와 세부사항을 클라이언트에 broadcast한다.

`destination`의 의미는 STOMP spec에서 의도적으로 가려놨다.

`destination`은 String이 될 수도 있고, 다른 것도 될 수 있다.
지원되는 타입 및 특정한 구문의 의미는 STOMP 서버가 지정한다.

하지만, 경로와 같이 `destination`은 대부분 string인 경우가 일반적이다. 
`/topic/..`의 경우 일대다(pub/sub)임을 의미하고, `/queue/`는 point to point(일대일) 메세지 교환을 의미한다.
STOMP 서버는 `MESSAGE` 커맨드를 사용해서 모든 구독자에게 메세지를 브로드캐스트 할 수 있다.

아래 예시는 구독한 클라이언트에게 주식 시세를 보내는 예제이다.


```
MESSAGE
message-id:nxahklf6-1
subscription:sub-1
destination:/topic/price.stock.MMM

{"ticker":"MMM","price":129.45}^@
```
서버는 요청하지 않은 메세지를 보낼 수 없다.
서버의 모든 메세지는 구체적인 클라이언트의 구독에 의한 응답이여야 한다.
서버 메세지의 구독 header는 클라이언트 구독의 ID와 같아야한다.

## Benefits

하위프로토콜인 STOMP를 사용하면, Spring Framework와 Spring Security가 단순한 raw WebSocket을 사용하는 것 보다 더 풍부한 프로그래밍 모델을 제공할 수 있다. 

HTTP와 Raw TCP를 비교한 것과 같다고 볼 수 있다.
이를 통해 Spring MVC와 다른 web framework들은 더 풍부한 기능을 제공한다.

아래는 장점 목록이다.
1. 커스텀 메세지 프로토콜을 개발할 필요가 없다.
2. Spring Framework의 Java Client를 포함해서 STOMP 클라이언트를 사용할 수 있다.
3. 메세지 브로커(RabbitMQ, ActiveMQ)를 사용하여 구독과 브로드캐스트된 메세지를 관리할 수 있다.
4. 애플리케이션 로직은 여러개의 `@Controller` 인스턴스로 구성할 수 있다. 연결에 대해 단일 `WebSocketHandler`로 raw WebSocket메세지를 처리하는게 아닌, STOMP `destination` header를 기반으로 메세지 라우팅이 가능하다.
5. Spring Security를 이용하여 STOMP `destination` 및 메세지 타입 기반으로 보안설정이 가능하다.

