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

Client -> HTTP Request -> Tomcat(Servlet Container) -> Dispatcher Servlet -> handler adaptor ->  Controller -> Business Logic -> Controller Return -> handler Adaptor -> HTTP Response -> Client

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

WebSocket handshake를 수행하는 `DefaultHandshakeHandler`을 확장하면, client origin이나 sub-protocol 설정 및 valid 설정을 할 수 있다.

또한 만약 WebSocket 서버 엔진을 지원하지 않는 버전을 사용해서 `RequestUpgradeStrategy`를 커스텀으로 설정해야하는 경우에도 위의 `DefaultHandshakeHandler`를 확장하면 된다.

> Spring은 WebSocketHandler를 데코레이트하는데 사용되는 Base class인 `WebSocketHandlerDecorator` 클래스를 제공한다. logging이나 Exception 처리의 경우 WebSocket config나 XML namespace를 구성할 때 기본적으로 제공되고 추가될 수 있다.
> 
> `ExceptionWebSocketHandlerDecorator`을 통해 WebSocketHandler 메서드에서 발생하는 uncaught Exception을 잡을 수 있다. 그리고, 서버 오류를 나타내는 1011로 WebSocket session을 닫는다.

## Deployment

Spring WebSocket API는 Spring MVC와 쉽게 통합됨.(DispatcherServlet에서 HTTP요청을 서빙하는 것과 WebSocket handshake 둘다 가능)

`WebSocketHttpRequestHandler`를 통해 다른 HTTP 처리에 쉽게 통합된다. 다만, JSR-356과 관련해서 특정한 고려사항이 있다.

Jakarta WebSocket API는 2가지 배포 매커니즘을 가지고 있다.
1. Servlet 컨테이너의 classpath를 스캔해서 시작하는 방법
2. Servlet 컨테이너 초기화시 사용되는 registration API로 시작하는 방법

1번째 방법의 경우 서블릿 컨테이너가 시작될 때 클래스 경로를 스캔하여 WebSocket Endpoint를 등록하는 방법이다.
2번째 방법의 경우 웹 어플리케이션이 시작될 때 Servlet 컨테이너 initialize event가 발생시켜  WebSocket Endpoint를 등록하는 방법이다.

아무튼지간에, 위의 두 가지 모두 DispatcherServlet과 같은 단일 Front Controller를 사용할 수 없다.

[JSR-356](https://www.oracle.com/technical-resources/articles/java/jsr356.html)의 제한사항이라고 한다.

JSR-356을 지원하는 서블릿 컨테이너는 Application initializer의 성능 감소를 시킬 수 있다고 한다.
이 경우 SCI scanning옵션을 활성화/비활성화 해야한다고 함.

## Configuring the Server

input 메세지의 buffer size, idle timeout 등 여러 설정 방법을 설명한다.

Jakarta WebSocket servers에서 `ServletServerContainerFactoryBean`을 통해 설정이 가능하다.

```java
@Bean
public ServletServerContainerFactoryBean createWebSocketContainer() {
    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
    container.setMaxTextMessageBufferSize(8192); // Text Message Buffer Size
    container.setMaxBinaryMessageBufferSize(8192); // Binary Message Buffer Size
    return container;
}
```

XML은 생략.

Jetty에서는 Consumer 콜백을 제공한다.
이를 통해 설정이 가능하다.

```java 
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(echoWebSocketHandler(), "/echo").setHandshakeHandler(handshakeHandler()); // custom handler 추가, handshakeHandler 추가
	}

	@Bean
	public DefaultHandshakeHandler handshakeHandler() {
		JettyRequestUpgradeStrategy strategy = new JettyRequestUpgradeStrategy();
		strategy.addWebSocketConfigurer(configurable -> {
				policy.setInputBufferSize(8192); // BufferSize setting
				policy.setIdleTimeout(600000); // Timeout setting
		});
		return new DefaultHandshakeHandler(strategy);
	}

}
```

## Allow Origins

Spring Framework 4.1.5 기준, WebSocket과 SockJS기본 동작은 무조건 same-origin요청만 된다.(기본동작 한정)

물론 all or 특정 origin리스트로도 설정 가능하다.

세 가지 가능한 동작 방식이 있다.

1. same-origin 요청만 허용(default) : 이 모드에서 SockJS가 활성화 되었을 때 Iframe HTTP 응답 헤더인 X-Frame-Options가 SAMEORIGIN으로 설정되고, 요청의 출저를 확인할 수 없어서 JSONP 전송이 비활성화 된다. 따라서 이 모드의 경우 IE6, IE7은 지원되지 않는다.
2. Specified origins 허용 : 각 origin 출저는 `http://` 또는 `https://`로 시작해야한다. 이 모드에서 SockJS가 활성화되면 IFrame 전송은 비활성화 된다.
3. 모든 출처 허용 : Origin값을 `*`로 설정하면 된다. 모든 전송이 사용 가능하다.

아래는 예제 코드이다.

```java
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(),  "/myHandler").setAllowedOrigins("https://mydomain.com"); // Handler Setting & Origin Setting
	}

	@Bean
	public WebSocketHandler myHandler() { // Custom Handler
		return new MyHandler();
	}

}
```