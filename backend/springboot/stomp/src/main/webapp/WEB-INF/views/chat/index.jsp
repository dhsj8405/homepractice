<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>stomp practice</title>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

<!-- <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<script>
var stompClient;
$(document).on("click", ".conn-btn", function(){
	console.log("버튼클릭");
	//config에서 설정한 핸드쉐이크를 위한 endpoint 값 넣어줌
	var socket = new SockJS("/stomp");
	// stomp 객체를 초기화 하기 위해서 over()를 이용해 SocketJS의 정보를 기반으로 설정하게 된다.
	stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(frame) {
		console.log('Connected:' + frame);
		stompClient.subscribe('/topic/b', function(response){
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
</script>

</head>
<body>
<h1>chat practice</h1>
	<tr>
		<td class="s">&nbsp;</td>
		<td><input class="conn-btn" type="submit" value="소켓 연결 및 구독"></td>
		
		<td><input id="msg" type="text" name="name" required>		
		<td><input class="send-btn" type="submit" value="메시지 보내기"></td>
		
	</tr>
	<!-- 푸쉬테스트 -->
</body>
</html>