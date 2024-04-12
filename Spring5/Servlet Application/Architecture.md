
이번 섹션에서는 Servlet 기반 어플리케이션 안에서의 Spring Security의 high-level 아키텍처를 논의해본다.
우리는 high-level에서 Authentication, Authorization, Protection Against Exploits를 이해할 수 있다.

## A Review of Filters

Spring Security의 Servlet지원은 Servlet Filter 기반으로 된다.
먼저, filter의 역할을 전반적으로 살펴봐야 한다.
아래 이미지는 단일 HTTP request에 대한 handler의 일반적인 계층을 보여준다.
![](https://i.imgur.com/SFw2Lzs.png)

클라이언트가 application에 요청을 보내면, container는 요청 URL의 경로에 따라 HttpServletRequest를 처리해야하는filter instance와 Servlet을 포함하는 `FilterChain`을 생성한다.

Spring MVC application에서 Servlet은 DispatcherServlet의 instance이다.

하나의 Servlet은 최대 하나의 HttpServletRequest와 HttpServletResponse를 처리할 수 있다. 물론 Servlet은 둘 이상의 filter를 사용할 수 있다.

downstream Filter 인스턴스 또는 Servlet이 호출되는 것을 방지한다. 이 경우 Filter는 일반적으로 HttpServletResponse를 작성한다.

Downstream Filter instance 및 Servlet에서 사용하는 HttpServletRequest, HttpServletResponse를 수정한다.

아래는 FilterChain의 예시 코드이다.


```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
	// do something before the rest of the application
    chain.doFilter(request, response); // invoke the rest of the application
    // do something after the rest of the application
}
```
`Filter`는 downstream Filter instance와 servlet에만 영향을 끼친다. 즉, 각 Filter가 호출되는 순서는 매우 중요하다.

### downstream Filter instance

클라이언트 요청이 controller에 전달되기 전에 적용되는 Filter를 가리킨다.

Downstream의 경우 HTTP 요청 처리과정에서 클라이언트->서버로 향하는 방향을 의미한다.

즉, 클라이언트의 요청이 서버에 도달하기 전에 실행된다.(그래서 보통 전처리에 사용된다.)

이러한 Filter들은 Request에 대한 pre-processing(전처리) 혹은 post-processing(후처리)를 수행하거나 req, res data를 수정할 수 있다.

일반적으로 Spring MVC downstream Filter instance는 Servlet container에서 관리되는 `javax.servlet.Filter`의 인터페이스를 구현한 클래스이다.

이러한 Filter들은 application의 web.xml에 정의되거나 Spring의 Java 구성 클래스에서 Bean으로 등록될 수 있다.

Downstream Filter instance는 Servlet container에 의해 chain으로 관리되며, 여러 개의 Filter가 chain형태로 연결될 수 있다.
Filter chain의 마지막 단계에는 실제 요청을 처리하는 Servlet이 위치한다.

downstream Filter instance는 요청의 전/후처리를 담당하고, Spring MVC application의 보안, 로깅, 인코딩, 캐싱 등과 같은 측면을 제어하는 역할을 한다.

## `DelegatingFilterProxy`

>   Spring provides a Filter implementation named DelegatingFilterProxy that allows bridging between the Servlet container’s lifecycle and Spring’s ApplicationContext.

Spring은 Servlet container의 lifecycle과 Spring의 ApplicationContext간에 bridging을 허용하는 DelegatingFilterProxy라는 Filter 구현체를 제공한다.

여기서 bridging이 정확히 무슨 말인지 이해가 안갔다.

문맥만 파악하자면, bridging은 두 가지 다른 구성요소간에 연결이나 조정 과정을 수행한다고 파악했다.
즉, Servlet container의 lifecycle과 Spring ApplicationContext 사이에 연결을 만들거나 조정하는 것이지 않을까.

위의 문장을 다시 이해하자면, DelegatingFilterProxy는 Servlet Container가 관리하는 Filter의 lifecycle을 Spring ApplicationContext와 연결하는 역할을 한다.
Servlet container가 Filter를 인스턴스화하고 lifecycle을 관리하고, Spring ApplicationContext는 bean의 lifecycle을 관리한다.
즉, DelegatingFilterProxy는 이러한 두 시스템 간의 상호 운용을 가능하게 한다고 설명하는게 아닐까?
이를 통해 Spring ApplicationContext에서 Filter를 구성할 수 있게 하는 것이지 않나 싶다.

Servlet Container는 자체적인 standard를 사용하여 Filter instance를 등록할 수는 있지만, Spring 정의 bean은 인식하지 못한다.
standard Servlet container 메커니즘을 통해 DelegatingFilterProxy를 등록할 수 있지만, 모든 작업은 Filter를 구현하는 Spring Bean에 위임이 가능하다.

다음은 DelegatingFilterProxy가 Filter 인스턴스와 FilterChain에 어떻게 적용되는지 보여준다.
![](https://i.imgur.com/iGNJXFw.png)
DelegatingFilterProxy는 ApplicationContext에서 Bean Filter0를 조회한 다음 Bean Filter0을 호출한다.
다음 목록은 DelegatingFilterProxy의 pseudo code를 보여준다.

```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
	Filter delegate = getFilterBean(someBeanName);
	delegate.doFilter(request, response);
}
```
