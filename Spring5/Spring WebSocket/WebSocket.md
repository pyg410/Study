## WebSockets
WebSocket Protocol에 대해 알고 싶은 경우 참고할 레퍼런스 : https://datatracker.ietf.org/doc/html/rfc6455

WebSocket Protocol은 full-duplex를 사용하기 위한 가장 기본적인 방법을 제공한다.

단일 TCP 연결어을 통한 클라이언트와 서버 사이의 Two-way 통신채널을 가진다.(양방향)

WebSocket은 Http 요청의 Upgrade헤더를 통해 시작된다.

Upgrade헤더를 담은 요청을 통해 Http -> WebSocket으로 Protocol변경이 일어난다.

아래는 Upgrade 헤더 요청의 예시다.
```
GET /spring-websocket-portfolio/portfolio HTTP/1.1 
Host: localhost:8080 
Upgrade: websocket 
Connection: Upgrade 
Sec-WebSocket-Key: Uc9l9TMkWGbHFD2qnFHltg== 
Sec-WebSocket-Protocol: v10.stomp, v11.stomp 
Sec-WebSocket-Version: 13 
Origin: http://localhost:8080
```

이러한 요청을 받은 서버에서는 200 status 대신, 다른 응답을 보낸다.

```
HTTP/1.1 101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: 1qVdfYHU9hPOl4JYYNXF623Gzn0=
Sec-WebSocket-Protocol: v10.stomp
```
이러한 handshake 이후, HTTP Upgrade 요청을 통한 TCP socket에서는 이제 서버와 클라이언트간 메세지를 주고 받을 수 있는 지속성을 유지할 수 있다.

더 알고 싶은 경우는 RFC 6455를 보면 된다.

### Full-Duplex

full-duplex란 전이중 통신이며, Sender와 Receiver 사이의 통신이 동시에 일어난다는 특징이 있다.
즉, 정보를 동시에 전송 및 수신할 수 있다.
기본적으로 스마트폰 및 이더넷 통신은 송수신이 동시에 가능한 full-duplex의 예시가 되겠다.

전이중 통신의 기법에는 몇 가지가 있다.
1. Time Division Duplex
시간을 나눠 일정 타임 슬롯 주기로 송수신 방향을 변경하는 것이다.
최초 100ms 동안 송신을 하고 그 다음 100ms는 수신을 하는 방식이다.
엄밀히 full-duplex는 아니지만, 사람이 인지하지 못할 정도로 빠르게 송수신 모드 전환이 반복해서 이뤄지므로 동시에 송수신하는 것처럼 느낄 수 있다.
요약하면 빠른 context switching 통신이라고 볼 수 있다.
이러한 기술은 보통 와이파이, 블루투스에 사용된다.

2. Frequency Division Duplex
통신하는 주파수 대역을 분할하여 전송되는 데이터가 충돌하지 않도록 하는 방법이다.
예로는 셀룰러 통신이 있다.

## Http vs WebSocket

WebSocket은 Http Protocol과 호환되게 설계되었다.

물론 WebSocket이 Http 요청에 의해 시작되지만, 두 Protocol은 매우 다른 아키텍처와 어플리케이션 프로그래밍 모델로 이루어져 있다.

1. HTTP
HTTP의 경우 어플리케이션은 많은 URL로 모델링된다. 어플리케이션과 상호작용하기 위해서 클라이언트들은 이러한 URL에 접근하게 된다. (request - response 스타일)

서버는 이러한 요청들을 HTTP URL, method, header를 기반으로 적절한 핸들러에 라우팅 한다.

2. WebSocket
반대로 WebSocket의 경우, 첫 connect를 위한 하나의 URL만 가진다.

이후 모든 Application 메세지는 같은 TCP connection에서 주고 받게 된다.

WebSocket은 low-level transport protocol이다.

>which, unlike HTTP, does not prescribe any semantics to the content of messages. That means that there is no way to route or process a message unless the client and the server agree on message semantics.

또한 웹소켓은 저수준 전송 프로토콜로, HTTP와 달리 메시지 내용에 어떤 의미도 규정하지 않는다. 즉, 클라이언트와 서버가 메시지의 규칙에 동의하지 않으면 메시지를 라우팅하거나 처리할 방법이 없다.

WebSocket 클라이언트와 서버는 HTTP handshake 요청의 Sec-WebSocket-Protocol 헤더를 통해 higher-level의 messaging protocol(ex,. stomp)를 사용할 수 있다. 
이러한 Protocol을 사용하지 않으면 자체 규칙을 만들어야한다.


## When to Use WebSockets

WebSocket은 웹 페이지를 동적 혹은 상호작용하게 만들 수 있다.
하지만, 대부분 AJAX, HTTP streaming, HTTP long pooling이 더 간단하고 효율적인 해결책이 될 수 있다.

예를 들어 뉴스, 메일, SNS는 동적인 업데이트가 필요하다. 하지만 그런 것들은 몇분안에 해도 괜찮다.
하지만, 증권 어플이나 게임, 협업의 경우 실시간에 가까워야 한다.

메세지의 양이 상대적으로 적은 경우 HTTP streaming, HTTP polling이 효율적일 수 있다.

하지만, 짧은 지연율, 높은 빈도, 많은 양의 조합은 WebSocket을 사용하기에 적합하다.

또한 인터넷에서 제한적인 프록시는 Upgrade header을 전달하도록 되어있지 않거나, 아무것도 하지 않은 상태의 장기 연결을 종료하기 때문에 WebSocket과의 연결을 끊을 수 있다.

즉, 방화벽 안의 application에 WebSocket을 사용하는게 public facing application보다 더 좋은 결정이다.


## WebSocketHandler
