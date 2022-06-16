Springboot + JSP

# 메시징 시스템
분리된 어플리케이션간 비동기적으로 신뢰성있게 통신

특징
- 메시지 생산자(producer),소비자(consumer)간 약한 결합성
- 동적이고 신뢰성있고 유연함
- 높은 확장성, 서로 다른 네트웤 사이의 쉬운 통합, 안정성

종류
1. Public-Subcribe 방식(Pub-Sub)  
    - 생산자가 토픽에 메시지를 보내면, 해당 토픽을 구독해놓은 모든 사용자들에게 메시지가 전송되는 방식

2. Point-To-Point 방식(PTP)
    - 큐를 통해서 메시지를 전달하고 튜를 통해서 메시지를 받는 방식
    모든 사람이 큐를 하나씩 가지고 보내는 사람이 목적지 큐를정해서 전송

# stomp


프레임 구조
```
COMMAND
header1 : value1
header2 : value2

Body^@
```

## client

메세지를 보내기 위한 명령
send
구독을 위한 명령
subscritbe

- 사용하려면 destination이라는 헤더 필요  
    - destination   
        - 어디에 메시지 전송할지, 어디에서 메세지 구독하는지 알려주는 헤더
        - 값은 서버에서 정하는 규칙에 따라 어떤 문자열이든 가능
        - 라우팅 방향을 결정하는 prefix(일반적으로 쓰이는 destination 값)
            1. 서버측 annotated method방향
                - `/app/.../...`
            2. 브로커 방향
                - `/topic/.../...` 주소는 pub-sub 방식의 one to many일 때 사용  
                - `/queue/.../...` 주소는 point to point 방식의 one to one일 때 사용

- console.log 예시
    clientA가 5번 채팅방을 구독할 때
    ```
    >>>SUBSCRIBE
    destination: /topic/chat/room/5
    id: sub-0

    ```
    cleintB에서 채팅 메시지를 보낼 때
    ```
    >>>SEND
    destination: /pub/chat
    content-type: application/json

    {"chatRoomId": 5, "type": "MESSAGE", "writer": "clientB"}^@
    ```

클라이언트에서 서버로 SEND할 때 흐름
![image](https://user-images.githubusercontent.com/60701130/155841215-9a902eaa-ae5d-4389-a783-28493f22cc4d.png)

 
1.  클라이언트에서 destination에 /app 이라는 prefix를 주었을때 흐름

해당 request는 @messagemapping된 스프링 컨트롤러(simpAnnotationMethod)로 흘러가고 컨트롤러에서 메세지를 수신한 후 여러 작업들을 처리한 후에 /topic이라는 prefix를 통해 브로커에게 전달하면 브로커는  STOMP MESSAGE 메소드를 이용해서 특정 토픽을 구독하는 구독자들에게 reponse를 보낸다.

 

2.  클라이언트에서 destination에 /topic이라는 prefix를 주었을때 흐름

 

매핑된 스프링 컨트롤러를 안거치고 브로커에게 직접 접근하겠다는 뜻인데 주로 클라이언트가 subscribe를 할때 이 prefix를 사용하는 것으로 보인다. 계속 설명하지만  /topic 이라는 prefix를 쓰면 브로커에게 직접 전달되는데 이 경우 브로커가 직접 받아서 subscriber들 관리를 하는 것 같다.

## Server

모든 구독자에게 메시지를 broadcasting 하기 위한 MESSAGE 명령 사용가능
- console.log 예시
```
<<< MESSAGE
destination:/topic/users
content-type: application/json;charset=UTF-8
subscription:sub-0
message-id:ltjhcr5o-0
content-length:18

{"name":"DongHyeonwoo"}
```



# 코드구현

## 소켓 설정

``` java
package com.douzone.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker//Stomp를 사용하기위해 선언하는 어노테이션
public class SocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // handshake가 될 endpoint 지정
		registry.addEndpoint("/stomp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // prefix에 따라 해당되는 메시지 핸들러로 분기
    	
    	//pub
    	// prefix가 /app일 때 SimpAnnotationMethod로 요청이 흘러감
    	// ex) 클라이언트가 메시지 보낼 때
    	// ex) stomp.send("/sub/chat/room/",function(){})
    	registry.setApplicationDestinationPrefixes("/app");
 
    	//sub
    	// prefix가 /topic일 때 브로커에 직접 접근 : 브로커가 직접 받아서 subscriber들을 관리
    	// ex)클라이언트가 메시지 받을 때				
    	// ex)stomp.subscribe("/sub/chat/room/",function(){})
        registry.enableSimpleBroker("/topic");
    }
}

```




## client 코드
``` java
var stompClient;
$(document).on("click", ".conn-btn", function(){
	console.log("버튼클릭");
	var socket = new SockJS("/stomp");
	stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(frame) {
		console.log('Connected:' + frame);
		stompClient.subscribe('/topic/a', function(response){
			console.log(response);
			console.log(JSON.parse(response.body));
		});
	});
	
});

$(document).on("click", ".send-btn", function(){
	console.log("sending");
	message = $("#msg").val();
	console.log(message);
	stompClient.send("/app/msg",{},JSON.stringify({message : message}));
		
});
```

## 서버 측 코드

클라이언트에서 /app/msg로 send 명령어가 왔을 때 메시지 받고
구독자들한테 브로드 캐스팅

```java
@Controller
public class StompController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/msg")
	public void showMessage(String a) {
		System.out.println(a);
		
		String msg[] = a.split(":");
		HashMap<String,Object> map = new HashMap<>();
		map.put("메시지", msg[1]);
		simpMessagingTemplate.convertAndSend("/topic/a",map);
	}
	
}
```

# 결과

![image](https://user-images.githubusercontent.com/60701130/156189968-7d253480-75d1-4c35-bbe2-385ea396adb0.png)
