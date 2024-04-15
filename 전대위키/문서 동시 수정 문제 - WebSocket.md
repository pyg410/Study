## 개요

전의 동시수정 문제에 대한 초기 대응 방법으로 WebSocket 위에서 동작하는 STOMP 프로토콜을 사용하기로 결정했다.

특정 사용자가 문서에 수정 요청시, 사용자는 HTTP 요청을 통해 서버에 인증인가를 받고, 해당 문서가 수정중인지 아닌지 상태를 response받는다.

다른 사용자가 수정중인 문서가 아니라면, WebSocket(편의상 WebSocket이라고 부르자) 프로토콜을 통해 서버와 connect하기 위한 HTTP요청을 보낸다.
HTTP를 통해 handshake 과정을 거치면, 서버와 연결된다.

## 고민
여기서 InboundChannelInterceptor을 통해 JWT를 실시간 통신에서 계속해서 주고 받을 것인가
아니면 handshake과정에서만 주고받고, 그 후는 사용자에 대한 인증을 따로 하지 않을 것인가에 대해 고민이 많았다.

후자로 진행하기로 함.

## 작업사항

### WebSocketConfig
- AllowOriginPatterns 모두 허용
- sockJS 허용
- endpoint 설정
- prehandler 적용(inbound interceptor)

### EventHandler

- 커넥트 종료시 DocsStatus에 있는 해당 인스턴스 삭제(email)
- 

### Business logic

처음 접속할 때 message받아야 하는 데이터
- MemberId
- DocsId
나중에 캐시 db로 변경해야할듯

- DocsStatusEntity 작성
- DocsStatusRepository 작성