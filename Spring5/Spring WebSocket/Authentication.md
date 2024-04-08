## Overview

모든 WebSocket 위의 STOMP messaging 세션의 경우 HTTP Request로 시작된다.
이는 WebSocket으로  Upgrade요청(handshake)일 수도 있고, SockJS fallback의 경우 일종의 SockJS HTTP 전송 Request일 수도 있다.

많은 web application에서는 이미 HTTP 요청을 보호하기 위한 인증 및 권한 부여 과정이 있다.
일반적으로 사용자는  로그인 페이지, HTTP 기본 인증 또는 다른 방법과 같은 메커니즘이 Spring security을 통해 인증된다.
인증된 사용자의 security context는 HTTP 세션에 저장되면 동일한 cookie-based session의 추후 request와 연결된다.

따라서 WebSocket handsake 혹은 SockJS HTTP 전송 요청의 경우 일반적으로 `HttpServletRequest#getUserPrincipal()`을 통해 접근할 수 있는 인증된 사용자가 이미 존재한다.
Spring은 해당 사용자를 위해 생성된 WebSocket 혹은 SockJS 세션과 자동으로 연결한 후, Usser의 header를 통해 해당 세션에 전송되는 모든 STOMP Message와 연결한다.

요약해서, 일반적인 Web aaplication은 보안을 위해 이미 수행 중인 작업 외에 아무것도 할 필요가 없다.
사용자는 쿠키 기반 HTTP 세션을 통해 유지되는 security context(해당 user를 위해 생성된 WebSocket 혹은 SockJS 세션과 연결됨)를 통해 HTTP request 수준에서 인증되며, application을 통해 흐르는 모든 Message에 User header가 스탬프처럼 찍힌다.

STOMP protocol에는 CONNECT frame에 login 및 password header가 있다.
이러한 header는 원래 TCP를 통한 STOMP를 위해 설계되었다.
하지만, WebSocket을 통한 STOMP의 경우, 기본적으로 Spring은 STOMP protocol level에서 Authentication header를 무시하고, 사용자가 HTTP 전송 수준에서 이미 인증된 것으로 가정한다.

따라서, STOMP는 WebSocket 또는 SockJS 세션에 인증된 사용자가 이미 있을 것으로 예상한다.


