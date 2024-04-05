## Flow of Messages

STOMP endpoint가 노출되면, Spring app은 연결된 클라이언트에 대한 STOMP broker가 된다.

이 섹션에서는 Server side에서의 message의 flow를 설명한다.

Spring-messaging 모듈은 Messaging application에 대한 기본지원이 된다.

나중에 많은 Spring project와 application에 많이 사용되게 하기 위해서 Spring Framework에 통합되었다.

아래에서는 몇 가지 Messaging abstraction(추상화)에 대해 설명한다.

1. `Message` : Header와 payload를 포함한 메세지의 표현이다.
2. `MessageHandler` : 메세지를 처리하기위한 규약을 의미한다.(수신, 처리, 전송과 관련된 작업 규정)
3. `MessageChannel` : producer과 consumer간 느슨한 커플링(결합)을 가능하게 하는 메세지 전송을 위한 규약
4. `SubscribableChannel` : 메세지 핸들러 구독자가 있는 MessageChannel
5. `ExecutorSubscribableChannel` : 메세지 전달을 위해 Executor를 사용하는 구독가능 채널

`@EnableWebSocketMessageBroker`과 XML namespace 구성은 모두 위의 구성요소를 사용하여 message workflow를 구성한다.
아래의 다이어그램은 간단한 내장 Message broker가 활성화 되었을 때 component를 보여준다.
![](https://i.imgur.com/uUZ4GJX.png)

위의 다이어그램은 3가지 message channel을 보여준다.
1. `clientInboundChannel` : WebSocket 클라이언트로부터 받은 메세지를 전달하기 위한 채널.
2. `clientOutboundChannel` :  WebSocket 클라이언트로 서버 메세지를 보내기 위한 채널.
3. `brokerChannel` : server-side application 코드 내에서 message broker로 메세지를 보내기 위한 채널

아래의 다이어그램은 외부 브로커(RabbitMQ)가 사용되었을 때 컴포넌트가 관리하는 구독과 메세지 broadcast가 설정된 것을 보여준다.
![](https://i.imgur.com/t10P5GC.png)

위의 두 다이어그램의 차이점은 메세지 전달을 위해 TCP를 통해 외부 STOMP broker에 메세지를 전달하고 구독한 클라이언트에게 메세지 전달을 하면서 "broker relay"를 사용하는지 여부이다.

WebSocket connection으로부터 메세지를 받았을 때, STOMP frames로 디코딩되고 Spring Message 표현으로 변환된 후, 추가 처리를 위해 `clientInboundChannel`로 전달된다.

예를 들어 `destination` header가 `/app`으로 시작하는 STOMP message의 경우 `@MessageMapping`어노테이션이 달린 메서드로 라우팅 될 수 있고, `/topic`,`/queue` 메세지는 메세지 브로커로 직접 라우팅 된다.

클라이언트의 STOMP message를 handle하기 위한 `@Controller`어노테이션은 message broker에 메세지를 보내고, broker는 `clientOutboundChannel`을 통해 일치하는 구독자에게 메세지를 broadcast한다.

동일한 Controller가 HTTP 요청에 대한 응답으로도 동일한 작업을 할 수 있다.
그래서 클라이언트가 HTTP POST를 수행한 다음 `@PostMapping`메서드를 통해 Message Broker에 메세지를 전송하여 구독한 클라이언트에게 broadcast할 수 있다.

아래 예시를 통해 flow를 따라갈 수 있다.

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/portfolio");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app"); // Controller mapping prefix
		registry.enableSimpleBroker("/topic"); // subscribe
	}
}

@Controller
public class GreetingController {

	@MessageMapping("/greeting")
	public String handle(String greeting) {
		return "[" + getTimestamp() + ": " + greeting;
	}
}
```
1. 클라이언트가 `localhost:8080/portfolio`에 연결하고 WebSocket connection이 설정되면, STOMP frames이 해당 연결로 연결된다.
2. 클라이언트는 `destination` header가 `/topic/greeting` 인 `SUBSCRIBE` frame을 전송한다. 수신 및 디코딩이 되면, 메세지는 `clientInboundChannel`로 전송되고, 그 다음 클라이언트 구독여부를 저장하는 message broker로 라우팅된다.
3. 클라이언트는 `/app/greeting`으로 `SEND` frame을 보낸다. `/app` prefix는 어노테이션이 달린 Controller로 라우팅되게 해준다. `/app` prefix가 제거된 후 나머지 `/greeting`부분은 `GreetingController`의 `@MessageMapping`메서드에 매핑된다.
4. `GreetingController`에서 반환된 값은 반환 값에 기반한 payload와 기본 `destination` header인 `/topic/greeting`을 포함하는 Spring Message로 변환된다.(input destination인 `/app`이 `/topic`으로 대체됨) 결과메세지는 `brokerChannel`로 전달되어 Message broker에 의해 처리된다.
5. Message broker는 일치하는 모든 구독자를 찾아 `clientOutboundChannel`을 통해`MESSAGE` frame을 보낸다. 여기서 Message는 STOMP frame으로 인코딩되어 WebSocket connection으로 전송된다.
