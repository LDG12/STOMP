## STOMP (In Memory Broker) 기반 Real Time Chatting 

![bandicam2024-05-0600-12-50-462-ezgif com-video-to-gif-converter (1)](https://github.com/LDG12/STOMP/assets/99185099/aff27cbc-82c8-4c64-9bba-f9a459cb62dc)


## 기능 ( 2024-05-05 )
1. SimpMessageBrocker 기반 pub/sub을 통해 SockJS에 연결된 사용자들에게 실시간 채팅 기능 구현
2. 채팅방 입장시 입장메시지, 퇴장시 퇴장 메시지를 다른 인원들에게 전송
3. 채팅방에 새로운 유저 입장시 기존 30분간의 대화 내역을 입장한 사용자에게만 보여줌

## 사용 도구
1. Spring Boot
2. STS
3. MySQL
4. MyBatis
5. BootStrap
6. SockJS
7. STOMP


## 보완 사항
- In Memory Brocker의 약점으로 알려진 사용자의 메시지 텍스트를 Memory에 저장하는 방식을 외부 브로커 사용하는 방식으로 구현 필요
- 추후 사용할 외부 브로커는 Kafka 예정
- 사용자가 입력한 채팅은 우측, 다른 구독자로부터 받은 메시지는 왼쪽에 표기하도록 HTML, CSS, JS 수정 필요
