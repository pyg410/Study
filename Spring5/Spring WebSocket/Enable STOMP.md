## Enable STOMP

WebSocket에서의 STOMP는 `spring-messaging`과 `spring-websocket`모듈을 사용할 수 있다.

dependency 설정이 되면, 아래 예제에서 볼 수 있듯이 WebSocket을 통해 Endpoint를 노출시킬 수 있다.

```java
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/portfolio"); (1)
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/app"); (2)
		config.enableSimpleBroker("/topic", "/queue"); (3)
	}
}
```
1. `/portfolio`는 WebSocket 클라이언트가 handshake를 하기 위해 필요한 endpoint인 HTTP URL이다.
2. `destination` header가 `/app`으로 시작하는 STOMP message는 `@Controller`클래스의 `@MessageMapping`메서드로 라우팅 된다.
3. default로 내장되어있는 메세지 브로커를 사용하여 구독과 브로드캐스팅을 하고, destination 헤더가 `/topic` 또는 `/queue`로 시작하는 메세지를 브로커로 라우팅한다.

기본으로 내장된 simple broker의 경우 `/topic`과 `/queue` prefix는 특별한 의미를 가지고 있지 않다.
이는 단지 pub-sub을 point to point 메세지(다수의 구독자와 하나의 consumer)를 구분하기 위한 규칙일 뿐이다.

> 만약 외부 broker를 사용하는 경우 해당 브로커의 STOMP 페이지를 확인해서 어떤 종류의 STOMP destination과 prefix를 지원하는지 파악해야한다.

STOMP를 사용해서 브라우저와 연결하기 위해서는 `stomp-js/stomjs`라이브러리를 사용해야한다.

아래는 해당 라이브러리의 사용 예시이다.

```javascript
const stompClient = new StompJs.Client({
       brokerURL: 'ws://domain.com/portfolio',
       onConnect: () => {
           // ...
       }
   });
```
위의 라이브러리는 SockJS로 대체 가능하다.