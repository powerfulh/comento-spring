# comento-spring
## 일지
### 241011
- 더이상 Xoo 를 볼일이 없다고 판단되어 메뉴 삭제
- 폰트 어섬 활용 추가
- 맵만 쓰다가 Dto 도입 (사실 로그인에서 쓰고 있긴 했음)

## 포함된 컨텐츠
### 자바 `8`
개인적으로 쓸만한 자바 버전 최소 허용은 1.8 버전이라고 생각한다.
`stream` 안쓸건가?
7 (1.7) 이하 쓰는 프로젝트는 반성하자

### 스프링부트
이름만 봐도 알다시피 스프링부트, 설명 굳이 필요한가

#### application.properties
요즘은 YML 쓰는 추세지만 꼰대 프로젝트라 프로퍼티즈 사용한다
- `spring.datasource` 로 디비 접속 정보 관리
- `server.port` 로 포트 번호 관리
- `mybatis` 로 마이바티스 관련 설정
- 그 외 데이타로서 프로퍼티즈 가져오는 예제는 `some-prop` 참고

참고로 환경 프로필 나누는건 안했다.
그딴건 딱히 필요없는 장난감 프로젝트이므로

#### logback-spring.xml
스프링에 내장되어 있는 로깅 설정을 담당하며 이 프로젝트에선 다음과 같은 사항을 관리중
- 로깅 시간 형식 관리
- 로그 파일명
- 과거 로그 최대 용량, 파일명 포맷
- 로그를 콘솔에만 찍을지 파일로도 만들지

이 외에 기능이 많은데 난 이것만 쓴다

#### @Autowired
기동 시 메모리에 떠있는 클라스가 가지고 있는 멤버 변수에 자동으로 새 객체를 주입해주는 어노태이션이다

요즘은 이거 안쓰고 `@RequiredArgsConstructor` 쓰거나 그냥 정직하게 생성자 만들어주는 추세인데 꼰대이기도 하고 Autowired 가 졸라 편하긴하다.
근데 이거 안좋다고 하니 이런 장난감 프로젝트 외엔 쓰지 말자.
뭐가 안좋은진 잘 모른다

### 타임리프
요즘은 스프링부트를 완전한 레스트 API로서 사용하는 추세지만 꼰대 프로젝트라 화면 제공을 타임 리프를 통해 직접 한다

resources/templates 경로가 타임리프를 쓰는 화면 경로이다

### 메이븐
의존성 추가, 빌드 도구 국룰 매이븐

### 마이바티스
요즘은 JPA 쓰는 추세지만 꼰대 프로젝트라 여전히 마이바티스를 사용한다

### 롬복
자바에서 롬복을 안쓰는건 자살 행위

### 폰트 어썸
전혀 디자인에 신경 안쓰는 척 하지만 이모티콘은 못참는다

다음과 같은 방식으로 활용중이며 안쓰는 화면도 있다
```html
<script src="https://kit.fontawesome.com/db65e38582.js" crossorigin="a"></script>
```

### springfox-swagger2
요즘은 fox가 아닌 doc
즉 최신 버전인 스웨거 3을 쓰는 추세지만 왜 2를 고르게 됐는지 경위가 기억나지 않는다
