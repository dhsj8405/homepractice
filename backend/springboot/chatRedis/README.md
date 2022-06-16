
# Redis 설치
[redis 설치](https://github.com/microsoftarchive/redis/releases)

- redis 서버 실행  
 redis-server.exe 실행
- cli로 확인하기  
 redis-cli.exe 실행  
 ![image](https://user-images.githubusercontent.com/60701130/166664129-9669b46d-3143-4fc1-862f-e37aa9c37a15.png)

# spring boot 연동하기

1. 의존성 추가하기
    - pom.xml
        ```
        <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-data-redis</artifactId>
            </dependency>
        ```
2. yml 환경설정
    ```
    spring:
       redis:
          lettuce:
          pool:
             min-idle: 0 # pool에 할당될 수 있는 커넥션 최대수 (음수로 하면 무제한)
             max-idle: 8 # pool의 "idle" 커넥션 최대수 (음수로 하면 무제한)
             max-active: 8 # 풀에서 관리하는 idle 커넥션의 최소 수 대상
          port: 6379
          host: localhost
    ```
 3. [redisConfig](https://github.com/dhsj8405/homepractice_BE/blob/main/chatRedis/src/main/java/com/douzone/chatRedis/config/RedisConfig.java) 클래스 작성
 4. 메세지 컨트롤러 ([StompController.java](https://github.com/dhsj8405/homepractice_BE/blob/main/chatRedis/src/main/java/com/douzone/chatRedis/StompController.java))에서 메시지 리스너추가, 기본 브로커로 보내지않고 레디스 publish로 메시지 발행추가
 5. service패키지에 레디스 [pub](https://github.com/dhsj8405/homepractice_BE/blob/main/chatRedis/src/main/java/com/douzone/chatRedis/service/RedisPublisher.java),[sub](https://github.com/dhsj8405/homepractice_BE/blob/main/chatRedis/src/main/java/com/douzone/chatRedis/service/RedisSubscriber.java) 클래스 만들기
