# Spring Boot TDD

## 목차
1. [서비스단 테스트](#1-서비스단-테스트)
2. [API 컨트롤러 테스트](#2-api-컨트롤러-테스트)
3. [예외 처리 테스트](#3-예외-처리-테스트)
4. [VIEW 컨트롤러 테스트](#4-view-컨트롤러-테스트)
5. [Util 컨트롤러 테스트](#5-util-컨트롤러-테스트)
6. [시큐리티 추가시 테스트 방법](#6-시큐리티-추가시-테스트-방법)
7. [통합 테스트](#7-통합-테스트)

---

## TDD란?

- 설계 목표를 분석해 테스트 시나리오인 테스트 메소드를 작성하고, 그것을 만족하기 위한 실제 행위 메소드를 반복 구현하여 메소드의 안정성을 높이는 것이 주 목적
- 추가적으로 이미 구현한 메소드에 대해 값을 검증하는 역할도 수행

---

## 1. 서비스단 테스트

- 테스트 메소드를 작성하고 그것을 만족하기 위한 실제 행위 메소드를 반복 구현
- `@ExtendWith(MockitoExtension.class)`  
  Mockito 기반 단위 테스트용 확장 등록. JUnit 5 (`@Test`)와 함께 사용
- `@InjectMocks`  
  테스트 대상 객체 생성 + 내부 필드에 `@Mock` 객체 자동 주입
- `@Mock`  
  Mockito 가짜(mock) 객체 생성. 단위 테스트에서 의존성 주입 용도

---

## 2. API 컨트롤러 테스트

- `@WebMvcTest(controllers = Xxx.class)`  
  Spring MVC 구성만 로드. 컨트롤러 단위 테스트에 적합. Service, Repository는 Mock 처리 필요
- `@MockitoBean`  
  Mockito 전용 mock 객체를 Spring Context에 주입. (✅ Spring Boot 3.4부터 표준)
- `@MockBean`  
  Spring Boot 3.4 이상에서는 Deprecated (❌ 사용 지양)
- `MockMvc`  
  HTTP 요청을 시뮬레이션하여 Spring MVC 컨트롤러를 테스트. 실제 서버 구동 없이 API 호출 가능

---

## 3. 예외 처리 테스트

- API 요청 시 빈 값이나 잘못된 값으로 발생하는 예외는 보통 `@ControllerAdvice`에서 처리
- `@RestControllerAdvice`  
  모든 컨트롤러에서 발생하는 예외를 공통으로 처리. 반복 코드 제거에 유용  
  예시: `GlobalExceptionHandler.class`

---

## 4. VIEW 컨트롤러 테스트

- 뷰 템플릿(JSP, Thymeleaf 등)을 반환하는 컨트롤러 테스트

예시 (`PageControllerTest` 참고):

```java
.andExpect(view().name("page"))
.andExpect(model().attributeExists("POSTLIST"));

.andExpect(status().is3xxRedirection())
.andExpect(redirectedUrl("/page"));
```
---

## 5. Util 컨트롤러 테스트
	•	Util 클래스는 보통 static 메소드로 구성됨
	•	이 경우 Mock 없이 직접 호출해서 테스트 가능
---

## 6. 시큐리티 추가시 테스트 방법
	•	Spring Security 의존성을 추가하면 MockMvc 기반 컨트롤러 테스트가 실패할 수 있음

인증 사용자 설정
```java
@WithMockUser(username = "admin", roles = {"ADMIN"})
```

CSRF 설정 추가
```java
mockMvc.perform(post("/post")
        .with(csrf()))
```
테스트 전용 시큐리티 설정 클래스 사용
- 테스트 환경에서 인가 검증을 우회하는 TestSecurityConfig 작성
- 적용:
```java
@Import(TestSecurityConfig.class)
```

---

## 7. 통합 테스트

단위 테스트의 한계
1.	“단위” 테스트는 각 계층별 동작만 검증하고 전체 흐름 테스트가 어려움
2.	Mock 객체는 실제 동작과 다를 수 있음
3.	전역 설정 또는 보안 설정이 누락될 수 있음

통합 테스트 예시 흐름
1.	각 레이어에 대한 단위 테스트를 먼저 작성
2.	실제 엔드포인트를 통해 전체 흐름을 검증

---
### 출처: https://www.devyummi.com/page?id=681f5ce4efe11dc60378a412