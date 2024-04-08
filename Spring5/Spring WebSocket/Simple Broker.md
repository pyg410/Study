## Simple Broker

내장된 simple broker는 클라이언트의 구독 요청을 처리하고, memory에 저장하고, message를 연결된 클라이언트에 broadcast 한다.

broker는 `Ant-style` destination pattern에 대한 구독정보를 포함하여, path-like destination을 지원한다.(Ant-style의 경우 우리가 controller에서 url매핑시 사용하는 패턴이다.)

> Application에서 dot-separated destination을 사용할 수도 있다.

만약 task scheduler로 구성되어있다면, simple broker는 STOMP heartbeats를 지원한다.
Scheduler를 설정하려면, 자체적인 `TaskScheduler` Bean을 등록하고, `MessageBrokerRegistry`를 통해 설정이 가능하다.

이미 선언되어있는 내장된 WebSocket 설정을 사용할 수 있지만, `@Lazy` 어노테이션을 통해 내장된 WebSocket 설정과 본인의 `WebSocketMessageBrokerConfigurer` 간 cycle을 조절해야한다.


```java
@Configuration 
@EnableWebSocketMessageBroker 
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { 
	private TaskScheduler messageBrokerTaskScheduler; 
	
	@Autowired 
	public void setMessageBrokerTaskScheduler(@Lazy TaskScheduler taskScheduler){ // Lazy어노테이션을 통해 cycle 조절
		this.messageBrokerTaskScheduler = taskScheduler; 
	} 
	
	@Override 
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue/", "/topic/") 
			.setHeartbeatValue(new long[] {10000, 20000}) 
			.setTaskScheduler(this.messageBrokerTaskScheduler); 
		// ... 
	} 
}
```

### `@Lazy`

Lazy어노테이션은 초기화를 지연시킨다.
Spring의 경우 기본적으로 즉시 초기화를 default로 가지고 있다.(EAGER)
Lazy어노테이션을 적용하게 되면 해당 클래스 객체를 사용하려고 하는 시점에 초기화가 진행된다.