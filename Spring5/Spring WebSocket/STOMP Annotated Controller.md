## Overview

어플리케이션은 `@Controller` 가 달린 클래스를 통해 클라이언트로부터 받은 메세지를 처리할 수 있다.

이번 섹터에서는 `@MessageMapping`, `@SubscribeMapping`, `@ExceptionHandler` 어노테이션에 대해 설명한다.

## `@MessageMapping`

`destination`기반으로한 메세지를 라우트 할 때 `@MessageMapping` 어노테이션을 사용할 수 있다.
이 어노테이션은 method level, type level을 지원한다.
`@MessageMapping`은 모든 Controller에서 매핑 공유가 된다.

default속성으로 매핑된 Value는 Ant-style pattern(ex,. \*\*, \*, ? 과 같이 사용되는 것이다.
물론 template variable도 지원된다.(ex,. `/thing/{id}`)

value들은 `@DestinationVariable` 어노테이션의 인자로 참조된다.
Application은 점으로 구분된 `destination` 컨벤션으로 전환할 수도 있다.(자세한 문서는 [여기](https://docs.spring.io/spring-framework/reference/web/websocket/stomp/destination-separator.html))

## Supported Method Arguments

아래 표는 method의 argument들을 설명한다.

| Method Argument                                                               | Description                                                                                                                                                                                                                                                                  |
| ----------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Message                                                                       | 완료된 메세지에 접근하기 위한 것                                                                                                                                                                                                                                                           |
| MessageHeaders                                                                | Message 내부의 header에 접근                                                                                                                                                                                                                                                       |
| MessageHeaderAccessor,<br>SimpleMessageHeaderAccessor,<br>StompHeaderAccessor | accessor metohds 타입을 통해 header에 접근하기 위함                                                                                                                                                                                                                                      |
| @Payload                                                                      | Message의 payload에 엑세스하려면 설정된 MessageConverter에 의해(ex,. JSON)변환된 Message를 사용한다.<br>이 어노테이션은 기본적으로 다른 argument는 일치되지 않았다고 가정한다. (즉, 웬만해서 이 어노테이션은 사용하지 않아도 된다.)<br>`@jakarta.validation.Valid` 혹은 Spring의 `@Validated` 어노테이션의 argument로 매핑하여 payload의 argument의 유효성을 체크할 수 있다. |
| @Header                                                                       | 구체적인 header value에 접근할 때 사용하기 위함.<br>필요하다면, `org.springframework.core.convert.converter.Converter`를 사용하여 type 변환을 할 수 있다.                                                                                                                                                    |
| @Headers                                                                      | Message의 모든 header에 접근한다.<br>이 argumnet는 `java.util.Map`에 할당할 수 있어야 한다.                                                                                                                                                                                                      |
| @DestinationVariable                                                          | Message의 `destination`에서 추출한 template 변수에 접근한다.<br>value는 선언된 method 인자 Type으로 변환된다.                                                                                                                                                                                         |
| java.security.Principal                                                       | WebSocket HTTP handshake 시점에 로그인한 사용자를 반영한다.                                                                                                                                                                                                                                 |

## Return Values

default로 `@MessageMapping`의 반환 값은 `MessageConverter`을 통해 payload로 serialize된다. 그리고나서 `brokerChannel`에 Message로 전송되어 subscriber에게 broadcast된다.
outbound Message의 경우 inbound Message의 대상과 동일하다. 하지만, prefix로 앞에 `/topic`이 붙는다.

`@SendTo`와 `@SendToUser` 어노테이션을 사용하여 출력할 Message의 `destination`을 커스터마이징할 수 있다.
`@SendTo`의 경우 특정 `destination` 혹은 구체적인 다수의 `destination`을 커스터마이징할 때 사용된다.
`@SendToUser`어노테이션의 경우 input Message와 연결된 사용자에게만 output Message를 직접적으로 보낼 때 사용된다.

동일한 Method에서 `@SendTo`와 `@SendToUser`를 동시에 사용할 수 있다. 둘 다 같은 class-level에서 지원된다. 이 경우 default로 class method에 대한 default로 동작한다.
하지만, method-level에서 `@SendTo`, `@SendToUser`어노테이션은 class-level에서의 어노테이션 override하는 것을 유의해야 한다.

Message는 async하게 처리할 수 있다. `@MessageMapping`어노테이션은 `ListenableFuture`, `CompletableFuture`, `CompletionStage` 타입을 반환한다.

`@SendTo`, `@SendToUser`는 `SimpMessagingTemplate`를 사용하여 Message를 보내는데 편의를 제공하기 위할 뿐이라는 점을 유의해야한다.
필요한 경우, `@MessageMapping` 어노테이션을 `SimpMessagingTemplate`을 직접 사용하는 것으로 대체가능하다.
이 작업은 추가적으로 값을 반환하는 것과 다른 것을 함께 실행할 수 있다. 이 경우는 [여기](https://docs.spring.io/spring-framework/reference/web/websocket/stomp/handle-send.html)를 참고하면 된다.

## `@SubscribeMapping`

`@SubscribeMapping`의 경우 `@MessageMapping`과 비슷하지만, 매핑 범위에 차이가 있다.
매핑 범위가 subscription message로 한정된다.

`@MessageMapping`과 동일한 메서드 인자를 지원하지만, 반환 값의 경우 기본적으로 message가 broker가 아닌, 클라이언트로 직접 전송된다.(brokerChannel이 아닌 clientOutboundChannel로 전송된다.)
`@SendTo`, `@SendToUser`어노테이션을 추가하면 이러한 동작은 override되고 대신 broker로 전송된다.

언제 사용하면 될까?
broker가 `/topic`, `/queue`에 매핑되어있고 application controller가 `/app`에 매핑되어있다고 가정해보자.
이러한 경우, broker가 구독에 대한 모든 정보를 `/topic`, `/queue`에 저장하므로 application은 특정한 작업을 할 필요가 없다.
클라이언트가 일부 `/app` `destination`에 구독할 수 있고, controller는 broker의 특정 작업 없이 해당 구독에 대한 값을 응답으로 반환할 수 있다.(즉, 일회성 req-res이다.) 
이러한 경우에 대한 예제로 시작시, 초기 데이터로 UI를 채우는 것이 있다.

언제 사용하지 말아야 할까?
특정 이유로 인해 broker와 controller가 구독을 포함한 message를 독립적으로 처리해야하는 경우가 아니라면, broker와 controller가 같은 `destination` prefix에 매핑되게 하면 안된다.

Inbound Message는 병렬로 처리된다. broker와 conroller중 어느쪽이던지 주어진 message를 먼저 처리할지 보장이 되지 않는다.
구독 정보가 저장되어 broadcast할 준비가 되었을 때 알림을 받는 것이 목표라면, 클라이언트는 서버로부터(서버가 지원하는 경우) receipt을 요구하면 된다.

예를 들어, Java STOMP 클라이언트의 경우 receipt를 추가하기 위해 아래와 같이 할 수 있다.

```java
@Autowired private TaskScheduler messageBrokerTaskScheduler; 

// During initialization.. 
stompClient.setTaskScheduler(this.messageBrokerTaskScheduler); 

// When subscribing.. 
StompHeaders headers = new StompHeaders(); headers.setDestination("/topic/..."); 
headers.setReceipt("r1"); FrameHandler handler = ...; 
stompSession
	.subscribe(headers, handler)
	.addReceiptTask(receiptHeaders -> { 
		// Subscription ready... 
	});
```
server side 옵션의 경우 `ExecutorChannelInterceptor`을 `brokerChannel`에 등록하고, 구독 정보를 포함한 메세지가 처리된 후 호출되는 `afterMessageHandled`메서드를 구현하는 방법이 있다.

## `@MessageExceptionHandler`

application은 `@MessageExceptionHandler` 어노테이션을 사용해서 `@MessageMapping`메서드에서 발생하는 exception을 처리할 수 있다.

Exception instance에 직접적으로 접근하고 싶다면 method argument를 통해Exception을 선언하면 된다.
아래의 예제는 Method argument를 통해 Exception을 선언하는 예제이다.

```java
@Controller
public class MyController {

	// ...

	@MessageExceptionHandler
	public ApplicationError handleException(MyException exception) {
		// ...
		return appError;
	}
}
```

`@MessageExceptionHandler` 어노테이션은 유연한 method 시그니처를 지원한다. 그리고, `@MessageMapping`어노테이션과 같은 return value & argument type을 지원한다.

일반적으로 `@MessageExceptionhandler`어노테이션은 `@Controller`클래스 혹은 클래스 계층구조 내에서 적용된다.
이러한 것보다 더욱 글로벌하게 적용하려면 `@ControllerAdvice`로 표시된 클래스에서 선언하면 된다.
이는 Spring MVC에서 제공되는 것과 비슷하다.