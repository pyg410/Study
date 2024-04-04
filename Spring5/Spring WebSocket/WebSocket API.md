## WebSocketHandler

웹소켓 서버를 생성하는 방법은 WebSocketHandler 구현, TextWebSocketHandler, BinaryWebSocketHandler 확장하는 것만큼 쉽다.
다음 예제는 TextWebSocketHandler를 사용한다.


```java
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;

// WebSocket 연결위한 핸들러
public class MyHandler extends TextWebSocketHandler {

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// ...
	}

}
```
WebSocket handler를 특정 URL에 매핑하기위한 전용 WebSocket Java Configuration과 XML namespace가 지원된다. 

```java
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/myHandler"); // WebSocket Handler과 URL 매핑
	}

	@Bean // Custom Handler Bean 등록
	public WebSocketHandler myHandler() {
		return new MyHandler();
	}

}
```

아래 예시는 XML configuration이다.
위의 예시와 동일한 XML 예제이다.
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:websocket="http://www.springframework.org/schema/websocket"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/websocket
		https://www.springframework.org/schema/websocket/spring-websocket.xsd">

	<websocket:handlers>
		<websocket:mapping path="/myHandler" handler="myHandler"/>
	</websocket:handlers>

	<bean id="myHandler" class="org.springframework.samples.MyHandler"/>

</beans>
```

이전 예시는 Sping MVC 어플리케이션에서 사용하기 위한 예제이다.
그리고 DispatcherServlet의 configuration이 포함되어야 한다.
(Spring의 WebSocket은 Spring MVC와 의존관계는 아니다.)

WebSocketHandler를 다른 HTTP를 서빙 환경에 통합하는 것은 WebSocketHttpRequestHandler를 사용하면 간단해진다.

WebSocketHandler를 직접 혹은 간접적으로 사용할 때, (eg,. STOMP Messaging), 어플리케이션은 메세지 전송하는 것을 동기화 해야한다. 
기본적인 WebSocket session(JSR-356)은 동시 전송을 허용하지 않기 때문이다.

### Dispatcher Servlet

간단하게 HTTP Protocol로 들어오는 모든 요청을 가장 앞단에서 먼저 받아 적합한 Controller로 매핑해주는 Front Controller 역할을 한다.

Client -> HTTP Request -> Tomcat(Servlet Container) -> Dispatcher Servlet -> handler adaptor ->  Controller -> Business Logig -> Controller Return -> handler Adaptor -> HTTP Response -> Client

물론 Dispatcher Servlet 앞에 Filter가 올 수도 있고, 뒤에 Interceptor가 올 수도 있다.

## WebSocket HandShake

첫 HTTP WebSocket HandShake 요청을 가장 쉽게하는 방법은 HandShakeInterceptor을 사용하는 방법이다.
HandShake의 이전과 이후 method를 설정할 수 있다.

```java
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyHandler(), "/myHandler")
			.addInterceptors(new HttpSessionHandshakeInterceptor()); // Interceptor추가
	}

}
```

아래의 예시는 XML configuration 예제이다. 위의 예제와 동일하다.

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:websocket="http://www.springframework.org/schema/websocket"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/websocket
		https://www.springframework.org/schema/websocket/spring-websocket.xsd">

	<websocket:handlers>
		<websocket:mapping path="/myHandler" handler="myHandler"/>
		<websocket:handshake-interceptors>
			<bean class="org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor"/>
		</websocket:handshake-interceptors>
	</websocket:handlers>

	<bean id="myHandler" class="org.springframework.samples.MyHandler"/>

</beans>
```

`DefaultHandshakeHandler