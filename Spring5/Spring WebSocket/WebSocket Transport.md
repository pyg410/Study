## WebSocket Transport

이번 섹션은 어떻게 기본 WebSocket 전송 설정에 대해 설명한다.

`Jakarta`의 WebSocket 서버에서, `ServletServerContainerFactoryBean`에 사용자의 configuration을 추가한다.

예시로, [Configuring the Server](https://docs.spring.io/spring-framework/reference/web/websocket/server.html#websocket-server-runtime-configuration) 섹션을 보면 된다.

Jetty WebSocket 서버에서는, `JettyRequestUpgradeStrategy`를 커스터마이징 하면된다.


```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/portfolio").setHandshakeHandler(handshakeHandler());
	}

	@Bean
	public DefaultHandshakeHandler handshakeHandler() {
		JettyRequestUpgradeStrategy strategy = new JettyRequestUpgradeStrategy();
		strategy.addWebSocketConfigurer(configurable -> {
				policy.setInputBufferSize(4 * 8192);
				policy.setIdleTimeout(600000);
		});
		return new DefaultHandshakeHandler(strategy);
	}
}
```

추가적으로 WebSocket서버 properties를 설정하려면 STOMP WebSocket전송 properties를 다음과 같이 설정하면 된다.

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		registry.setMessageSizeLimit(4 * 8192);
		registry.setTimeToFirstMessage(30000);
	}

}
```
Message Size 제한, Timesout 제한을 걸 수 있다.
기존 WebSocket Configuration의 경우는 Buffer Size, Timeout을 설정할 수 있었다.