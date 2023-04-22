# 교환 일기장 DailyNote(데일리 노트) - Android
2022.06.08 ~

## 개발 목적
- 내 일기, 메모 등을 기록하고 짝을 지정하여 공유가 가능한 시스템 개발

## 개발 팀원
* 문지홍(앱)

## 개발 환경
* Android Studio , Kotlin

## 개발 화면
![KakaoTalk_20230420_013533074](https://user-images.githubusercontent.com/62639477/233760710-6dd56a7a-03b5-4b89-8a53-ec93790166c9.jpg)
![KakaoTalk_20230420_013533074_01](https://user-images.githubusercontent.com/62639477/233760714-6279f8b2-9716-4331-be8a-d8a39a2e6a77.jpg)


## 개발 내용

* 하단 내비게이션

* 홈 
  * 캘린더
  * 해당 날짜 메모리스트 조회

* 메모리스트
  * 메모 작성
  * 메모 수정
  * 메모 삭제
  * 메모 조회
  * 메모 검색

* 로그인(미구현)
  * SNS로그인 - kakao, naver, google 로그인 api 연동 

* 짝 지정(미구현)
  * ID로 짝 설정
  * 일기장 업로드
  * 짝 일기장 조회
  * 댓글

### Memo
* 규모를 작게하기 위해 도메인 레이어는 제외하고 UI레이어와 데이터 레이어로 구성
* 구글 플레이 6월 중 출시예정
