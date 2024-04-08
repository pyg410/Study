## Sending Messages

만약 우리가 application 어느부분에서 연결된 클라이언트에게 Message를 보내기 위해선 어떻게 해야할까?
어떤 application component던지 message를 brokerChannel로 보낼 수 있다.
가장 쉬운 방법은 `SimpMessagingTemplate`을 삽입하는 것이다. 그리고 삽입한 객체를 통해 Message를 보내면 된다.

일반적으로 type에 의한 주입을 할 수 있다. 아래 예제를 보자.

```java
@Controller 
public class GreetingController { 
	private SimpMessagingTemplate template; 
	
	@Autowired 
	public GreetingController(SimpMessagingTemplate template) { 
		this.template = template; 
	} 
	
	@RequestMapping(path="/greetings", method=POST) 
	public void greet(String greeting) { 
		String text = "[" + getTimestamp() + "]:" + greeting; 
		this.template.convertAndSend("/topic/greetings", text);  // 여기서 inject받은 template를 통해 Message를 보내고 있다.
	} 
}
```

하지만, 동일한 type의 Bean이 존재하는 경우, 해당 빈의 이름(brokerMessagingTemplate)으로 한정할 수도 있다.