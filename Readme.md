# API 명세서



Lv1 ~ Lv2, Lv4

|기능|메서드|URL|request|response|상태코드|
|---|---|---|---|---|---|
|일정 등록|POST|/schedule|요청 body(제목, 내용, 이름, 비밀번호)|등록 정보|201 CREATED|
|일정 전체 조회|GET|/schedule|요청 body(이름, 수정일)|조회 결과|200 OK|
|일정 단건 조회|GET|/schedule/{id}|X|조회 결과|200 OK|
|일정 페이지 조회|GET|/schedule/page|요청 param(페이지 넘버, 페이즈 크기)|조회 결과|200 OK|
|일정 수정|PATCH|/schedule/{id}|요청 body(제목, 내용, 이름, 비밀번호)|수정 결과|200 OK|
|일정 삭제|DELETE|/schedule/{id}|요청 body(비밀번호)|X|200 OK|



Lv3

기능            메서드      URL              request                                       response        상태코드

사람 등록       POST        /memberV2        요청 body                                      등록 완료        201 CREATED

일정 등록       POST        /scheduleV2      요청 body                                      등록 결과        201 CREATED

일정 전체 조회   GET        /scheduleV2      요청 param                                      조회 결과       200 OK


ERD 다이어그램

Lv1, Lv2 단건 테이블

Lv3 schedule이 foriegn key로 user table의 primary key를 가짐

최초 작성

![erd-first](https://github.com/user-attachments/assets/a60144b0-32eb-4f59-bfba-5204305738f3)


최종 작성

![erd-complete](https://github.com/user-attachments/assets/d5451707-5161-4447-88df-4e3dc00a21c9)
