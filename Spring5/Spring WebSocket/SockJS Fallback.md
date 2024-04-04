## SockJS Fallback

public Internet에서 제한적인 프록시가 Upgrade 헤더를 전달할 수 있게 설정되지 않았거나, long-lived connections를 끊기 때문에 WebSocket 통신을 방해할 수 있다.

이 문제의 해결책으로는 WebSocket emulation을 사용하면 된다.
WebSocket emulation이란, 먼저 WebSocket을 사용하는 것을 먼저 시도한 후, 
실패한 경우 WebSocket 통신을 Emulate하고 동일 application level의 API를 노출하는 HTTP-based 기술로 돌아가게하면 된다.

즉, WebSocket 통신에 실패한 경우 Http Streaming이나 Long polling과 같은 Http-based 기술로 전환해 다시 연결하는 기술이다.


Servlet 스택에서 Spring Framework는 서버 및 클라이언트에 대한 SockJS protocol을 모두 지원한다.

조금 더 자세한 설명의 경우는 아래를 참고하자.
[한글](https://velog.io/@yyong3519/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%9B%B9%EC%86%8C%EC%BC%932)
[공식문서](https://docs.spring.io/spring-framework/reference/web/websocket/fallback.html)
