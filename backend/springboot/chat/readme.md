# React , SpringBoot

StompJS 사용 채팅


`CORS-practice로 웹팩,바벨 설정먼저하고 하기`

# 사전 설치  
# webpack-practice 복사해서 사용

- package.json 생성  
`npm init -y`  

- 웹팩 라이브러리,로더, 바벨로더 설정  
 `npm i -D webpack webpack-cli webpack-dev-server babel-loader style-loader css-loader sass-loader node-sass @babel/core @babel/cli @babel/cli @babel/preset-env @babel/preset-react`    
 - react dom 사용  
  `npm i react react-dom`
 - yarn 설치  

`npm install -g yarn`
-  axios 설치  
`yarn add axios`

- stomp 모듈, socketJS 추가  
`yarn add @stomp/stompjs sockjs-client`

# CORS
react port 3000  
spring port 9099

## Server(Back)
<pre>
com.douzone.chat  
    --- ChatApplication.java  
    --- MainController.java  
    --- StompController.java    
    --- config  
        --- FilterConfig.java  
        --- SocketConfig.java  
    --- dto  
        --- ChatMessageDto.java  
        --- ChatRoomDto.java  
    --- filter  
        --- CorsFilter.java  
</pre>
