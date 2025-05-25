# API 명세서



##Lv1 ~ Lv2, Lv4

|기능|메서드|URL|request|response|상태코드|
|---|---|---|---|---|---|
|일정 등록|POST|/schedule|요청 body(제목, 내용, 이름, 비밀번호)|등록 정보|201 CREATED|
|일정 전체 조회|GET|/schedule|요청 body(이름, 수정일)|조회 결과|200 OK|
|일정 단건 조회|GET|/schedule/{id}|X|조회 결과|200 OK|
|일정 페이지 조회|GET|/schedule/page|요청 param(페이지 넘버, 페이즈 크기)|조회 결과|200 OK|
|일정 수정|PATCH|/schedule/{id}|요청 body(제목, 내용, 이름, 비밀번호)|수정 결과|200 OK|
|일정 삭제|DELETE|/schedule/{id}|요청 body(비밀번호)|X|200 OK|



##Lv3

|기능|메서드|URL|request|response|상태코드|
|---|---|---|---|---|---|
|사람 등록|POST|/memberV2|요청 body(이름, 이메일)|등록 완료|201 CREATED|
|일정 등록|POST|/scheduleV2|요청 body(제목, 내용, 비밀번호), 요청 param(유저 ID)|등록 결과|201 CREATED|
|일정 전체 조회|GET|/scheduleV2|요청 param(유저 ID)|조회 결과|200 OK|
|일정 단건 조회|GET|/scheduleV2/{id}|X|조회 결과|200 OK|
|일정 페이지 조회|GET|/scheduleV2/page|요청 param(페이지 넘버, 페이즈 크기)|조회 결과|200 OK|
|일정 수정|PATCH|/scheduleV2/{id}|요청 body(제목, 내용, 비밀번호)|수정 결과|200 OK|
|일정 삭제|DELETE|/scheduleV2/{id}|요청 body(비밀번호)|X|200 OK


##ERD 다이어그램

Lv1, Lv2 단건 테이블

Lv3 schedule이 foriegn key로 user table의 primary key를 가짐

#최초 작성

![erd-first](https://github.com/user-attachments/assets/a60144b0-32eb-4f59-bfba-5204305738f3)


#최종 작성

![erd-complete](https://github.com/user-attachments/assets/d5451707-5161-4447-88df-4e3dc00a21c9)



### 1. 적절한 관심사 분리를 적용하셨나요? (Controller, Service, Repository)

Controller, Service, Repository 분리 완료

### 2. RESTful한 API를 설계하셨나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?

역할 별로 Get, Post, Patch, Delete를 잘 매칭 시켰으나 그 이름에 대해서는 적절하지 않을 수 있다고 판단

### 3. 수정, 삭제 API의 request를 어떤 방식으로 사용 하셨나요? (param, query, body)

수정의 경우 Lv 1, 2 에서는 body에 정보를 담아서 보냈고, 바꾸고자 하는 일정의 id는 query로 받았다

Lv 3에서도 똑같이 구현하였다.

삭제의 경우 query로 id를 받고 body에 비밀번호를 받아 구현하였으며, Lv 2, 3 공통이다.
