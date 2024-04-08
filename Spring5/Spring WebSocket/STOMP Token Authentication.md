## Token Authentication

Spring Security OAuth는 JWT를 포함한 토큰 기반 security를 지원한다.
Authentication 섹션에서 설명한대로 STOMP는 Web application의 인증 메커니즘으로 사용할 수 있다(쿠키 기반 세션을 통해 인증 유지를 위해서).

동시에 쿠키 기반 세션이 항상 best fit이진 않다.
예를 들어, server side 세션을 유지하지 않는 application이나, 인증에 header를 사용하는게 일반적인 모바일 application이 있다.

WebSocket protocol인 RFC 6455에서는 다음과 같이 설명한다.
> WebSocket handshake 중에, 서버가 클라이언트를 인증하는 특별한 방법을 규정하고 있지 않다.

하지만, 실제 브라우저 클라이언트는 표준 authentication header 또는 cookie만 사용할 수 있으며, 커스텀 header를 사용할 수 없다.

마찬가지로 SockJS 자바스크립트 클라이언트도 SockJS 전송 요청과 함께 HTTP 헤더를 전송하는 방법을 제공하지 않는다.(sockjs-client의 196 issue 참고)

대신 토큰을 보내는 데 사용할 수 있는 Query parameter를 보낼 수 있지만, 여기에는 단점이 있다.(예를 들어 토큰이 실수로 서버 로그에 URL과 함께 기록될 수 있다.)

**위의 제한 사항은 브라우저 기반 클라이언트에 대한 것이며, WebSocket 및 SockJS 요청을 모두 포함하는 헤더 전송을 지원하는 Spring Java STOMP 클라이언트에는 적용되지 않는다.**

따라서 쿠키를 사용하지 않으려는 application은 HTTP protocol Level에서 인증할 수 있는 다른 방법이 없을 수 있다.
쿠키를 사용하는 대신, STOMP 메세지 프로토콜 level에서 헤더로 인증할 수도 있다.
이렇게 하려면 두 가지 단계를 따라야 한다.

1. STOMP 클라이언트를 사용하여 연결 시 authentication header를 전송한다.
2. `ChannelInterceptor`로 authentication header를 처리한다.

아래 예시는 server side configuration을 사용하여, 커스터 atuhentication interceptor를 작성하는 방법이다.
Interceptor는 CONNECT message에서 header를 인증하고 설정하기만 하면 된다.
Spring은 인증된 사용자를 저장하고 동일한 세션의 후속 STOMP Message와 연결한다.

아래 예시는 커스텀 authentication interceptor를 만드는 방법을 보여준다.

```java
@Configuration 
@EnableWebSocketMessageBroker 
public class MyConfig implements WebSocketMessageBrokerConfigurer { 
	@Override 
	public void configureClientInboundChannel(ChannelRegistration registration) { 
		registration.interceptors(new ChannelInterceptor() { 
			@Override 
			public Message<?> preSend(Message<?> message, MessageChannel channel) { 
				StompHeaderAccessor accessor = 
					MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class); 
				if (StompCommand.CONNECT.equals(accessor.getCommand())) { 
					Authentication user = ... ; // access authentication header(s) 
					accessor.setUser(user); 
				} 
				return message; 
			} 
		}); 
	} 
}
```
또한 Message에 대해 Spring Security의 권한 부여를 사용하는 경우, 현재 authentication `ChannelInterceptor` 설정이 Spring Security보다 우선순위가 높은지 확인해야 한다.

이러한 경우 `@Order(Ordered.HIGH_PRECEDENCE + 99`로 표시된 `WebSocketMessageBrokerConfigurer`의 구현에서 커스텀 interceptor를 선언하면 가장 잘 수행된다.

