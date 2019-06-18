## REST API에 대해서 조사하시오.

- 서버 프로그램은 여러 웹 브라우저는 물론이며, 아이폰 , 안드로이드 애플리케이션과의 통신에 대응해야 한다.
- 매번 서버를 새로 만드는 수고를 들이지 않기 위해선 `범용적인 사용성`을 보장하는 서버 디자인이 필요

## REST란?

- Representational State Trans 약자
- 웹의 장점을 최대한 활용할 수 있는 아키텍쳐

### 구성

자원(Resource) : URI
행위(Verb) : HTTP METHOD
표현(Represetations)

### 특징

1) Uniform (유니폼 인터페이스)
Unifrom Interface는 URI로 지정한 리소스에 대한 조작을 `통일되고 한정적인 인터페이스`로 수행하는 아키텍쳐 스타일

2) Stateless (무상태성)
- REST는 `무상태성 성격`을 갖는다
- `세션 정보나 쿠키 정보를 별도로 저장하고 관리하지 않기 때문에 API 서버는 들어오는 요청만을 단순히 처리하면 된다`
- 서비스의 자유도가 높아지고 서버에서 불필요한 정보를 관리하지 않음으로써 구현이 단순해진다

3) Cahceable (캐시 기능)
- 가장 큰 특징
- HTTP라는 기존 웹표준을 그대로 사용하기 때문에
- 웹에서 사용하는 기존 인프라를 그대로 활용 가능

4) Self-desrciptiveness (자체 표현 구조)
- REST Api 메세지만 보고도 이를 `쉽게 이해 할 수 있는 자체 표현 구조`

5) Client - Server 구조
- REST서버는 API 제공
- 클라이언트는 사용자 인증이나 컨텍스트(세션, 로그인 정보)등을 직접 관리하는 구조
- 각각의 역할이 확실히 구분
- 서로의 의존성이 줄어든다

6) 계층형 구조
- REST 서버는 다중 계층으로 구성 될 수 있다

### 디자인 가이드 (아주 중요)

1. URI는 정보의 차원을 표현해야 한다
2. 자원의 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현

POST : POST를 통해 해당 `URI를 요청`하면 `리소스를 생성`
GET : GET를 통해 `해당 리소스를 조회`, 리소스를 조회하고 `해당 도큐먼트에 대한 자세한 정보`를 가져온다
PUT : PUT을 통해 `해당 리소스를 수정`
DELETE : `해당 리소스를 삭제`


ex) 1번 사용자에 대해 정보를 받아야 할 때
```
GET /user/1
````







-------------

자원은 크게 
collection과 Element로 나누어 표현할 수 있다

Collection URI (such as `http://example.com/resources)

GET : 컬렉션에 속한 자원들의 URI나 그 상세사항의 목록을 보여준다
PUT : 전체 컬렉션은 다른 컬렉션으로 교체한다.
POST : 해당 컬렉션에 속하는 새로운 자원을 생성한다. 자원의 URI는 시스템에 의해 할당된다.
DELETE : 전체 컬렉션을 삭제한다.

Element URI (such as `http://example.com/item17)

GET : 요청한 컬렉션 내 자원을 반환
PUT : 해당 자원을 수정한다
POST : 해당 자원에 귀속되는 새로운 자원을 생성한다.
DELETE : 해당 컬렉션 내 자원을 삭제한다.



