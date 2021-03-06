# Activity와 Fragment의 생명주기에 대해서 조사하시오.

## 액티비티란?

- 중요한 컴포넌트로 기본적으로 한 액티비티가 `하나의 화면`을 표시
- 액티비티에는 윈도우가 있고, 그 윈도우에 텍스트나 이미지를 표시해 사용자 조작(활동)에 반응

## 생명주기

`몇 가지 상태`와 `상태를 변경할 때의 콜백`이 안드로이드 프레임워크에서 호출

- 콜백 메서드를 구현하여 액티비티의 수명 주기를 관리하는 것 -> 강력하고 유연한 애플리케이션 개발에 대단히 중요한 역할
- 액티비티의 수명 주기는 다른 액티비티와의 관계, 액티비티의 작업과 백 스택 등에 직접적으로 영향을 받습니다.

### 세가지 상태

#### `재개됨(Resumed)` : 화면 포그라운드, 사용자 포커스 갖고있음

#### `일시정지됨(Paused)` : 다른 액티비티가 이 액티비티 위에 표시, 해당 액티비티는 부분적으로 투명 또는 전체 화면을 덮지 않는 상태

#### `정지됨(Stopped)` : 다른 액티비티에 의해 완전히 가려진 상태, 백그라운드, 여전히 살아있다

#### Paused와 Stopped 차이

- 공통점 : Activity 객체가 메모리에 보관되어 있고 모든 상태 및 멤버 정보를 유지
- 차이점 : Paused는 창 관리자에 붙어 있는 상태로 유지, Stopped는 창 관리자에 붙어 있지 않음

### 콜백함수

#### onCreate()
액티비티가 처음 `생성`되었을 때 호출, 뷰 생성, 목록에 데이터 바인딩하기 등
-> onStart() 

#### onRestart() : 비표시
액티비티가 중단되었다가 다시 시작되기 직전에 호출
-> onStart()

#### onStart() : 비표시
액티비티가 사용자에게 표시되기 직전에 호출
-> 액티비티가 포그라운드로 나오면 onResume()
-> 액티비티가 숨겨지면 onStop()

#### onResume() : 표시(맨 앞)
액티비티가 시작되고 사용자와 `상호작용하기 직전`에 호출
->onPause()

#### onPause() : 표시(일시정지)
시스템이 다른 액티비티를 `재개하기 직전에` 호출
일반적으로 `데이터를 유지하기 위해 저장되지 않은 변경 사항`을 커밋
애니메이션을 비록하여 CPU를 소모하는 기타 작업을 중단하는 등 여러 가지 용도에 사용
무슨 일을 하든 매우 빨리 끝내야 함
-> 액티비티가 다시 전경으로 돌아오면 onResume()
-> 액티비티가 사용자에게 보이지 않게 되면 onStop()

#### onStop() : 비표시
액티비티가 더 이상 사용자에게 표시되지 않게 되면 호출
액티비티가 소멸되고 있기 때문에 일어날 수도 있고, 
다른 액티비티(기존 것이든 새로운 것이든)가 재개되어 이것을 덮고 있기 때문일 수도 있다
-> 액티비티가 다시 사용자와 상호작용하면 onRestart()
-> 액티비티가 사라지면 onDestroy()

#### onDestroy() : 폐기
액티비티가 `소멸`되기 전에 호출

출처 : https://developer.android.com/guide/components/activities/activity-lifecycle.html#tba


-------------------------------------------

## 프래그먼트란?

- `여러 개의 프래그먼트`를 `하나의 액티비티`에 조합하여 창이 여러 개인 UI구축 가능
- 프래그먼트는 자체 수명 주기를 가지고, 자체 입력 이벤트를 받으며, 액티비티 실행 중에 추가 및 제거가 가능한 액티비티의 모듈식 섹션
- 그러나, 프래그먼트는 `항상 액티비티 내에 포함`되어 있어야 함 - 이유: 프래그먼트의 수명 주기는 `호스트 액티비티의 수명 주기에 직접적으로 영향`을 받음
예)
액티비티가 일시정지 되는 경우, 그 안의 모든 프래그먼트도 일시정지
액티비티가 소멸되면 모든 프래그먼트도 마찬가지로 소멸
그러나,
액티비티가 실행 중인 동안에는 각 프래그먼트를 추가 또는 제거하는 등 개별적으로 조작 가능

각 백 스택 항목이 발생한 프래그먼트 트랜잭션의 기록
-> 백 스택을 사용하면 사용자가 프래그먼트 트랜잭션을 거꾸로 돌릴 수 있다.

### 프래그먼트를 액티비티 레이아웃의 일부로 추가하는 경우

1. 액티비티 레이아웃 파일에서 <fragment> 요소로 선언
2. 에플리케이션 코드에서 이를 기존의 ViewGruop에 추가

### 프래그먼트 생성

- 액티비티와 `비슷한 콜백 메서드`가 들어 있다.

### 프래그먼트 수명 주기 처리

- onAttach()

- onCreate()
    프래그먼트를 `생성`할 때 이것을 호출
    프래그먼트가 `일시정지`되거나 `중단되었다가 재개`되었을 때 유지하고자 하는 것을 초기화 

- onCreateView()
    프래그먼트가 자신의 `사용자 인터페이스를 처음으로 그릴 시간`이 되면 이것을 호출
    프래그먼트에 맞는 UI를 그리려면 메서드에서 View를 반환해야 함
    이 메서드는 `프래그먼트 레이아웃의 루트`
    프래그먼트가 UI를 제공하지 않는 경우 null을 반환하면 된다.

- onActivityCreated()

`Started`
- onStart()
  
`Resumed` : 프래그먼트가 `실행` 중인 액티비티에 표시
- onResume()

`Paused` : 다른 액티비티가 포그라운드에 있고 포커스를 갖고 있지만, 이 프래그먼트가 있는 액티비티도 여전히 표시 

- onPause() 
    사용자가 프래그먼트를 떠난다는 첫번재 신호(다만 이것이 항상 프래그먼트가 소멸 중이라는 뜻은 아님)
    현재 사용자 세션을 넘어서 지속되어야 하는 변경 사항을 커밋하려면 보통 이곳에서 한다.

`Stoped` : 프래그먼트가 표시되지 않는다. 호스트 액티비티가 정지되었거나 프래그먼트가 액티비티에서 제거되었지만 `백 스택`에 추가되었다. 정지된 프래그먼트도 여전히 표시는 된다. 하지만 사용자에게 더 이상 표시되지 않으며 액티비티를 종료하면 이것도 종료

- onStop()

`Destroyed`

- onDestroyView()
- onDestory()
- onDetach()

### 상태 저장
onSaveInsatanceState()

### 상태 복구
OnCreate()
onCreateView()
onActivityCreate()

### 액티비티와 프래그먼트의 생명주기에서 가장 중대한 차이점 
백 스택에 저장되는 방법

> Activity
 기본적으로 중단되었을 때 시스템이 관리하는 액티비티 백 스택안에 배치
 - 사용자가 BACK 버튼을 사용하여 다시 이 액티비티로 돌아갈 수 있다

> Fragment
 호스트 액티비티가 관리하는 백 스택 안에 배치되는 경우,
 트랜잭션 동안 프래그먼트를 제거하는 addToBackStack()를 호출하여 해당 인스턴스를 저장하라고 명시적으로 요청하는 경우뿐

이것만 제외하면, 프래그먼트 수명 주기를 관리하는 것은 액티비티 수명 주기를 관리하는 것과 아주 비슷하다
또 중요한 것은, 액티비티의 수명이 프래그먼트의 수명에 `어떤 영향`을 미치는지 알아두어야 한다.

Fragment내에서 Context객체가 필요한 경우, getActivity()를 호출하면 된다. 
그러나 이 함수를 호출하는 것은 `프래그먼트가 액티비티에 첨부되어 있는 경우` 뿐이다. 
프래그먼트가 아직 첨부되어 있지 않거나 수명 주기가 끝날 무렵 분리된 경우, getActivity()가 null 을 반환

## 액티비티 수명 주기와의 조화

프래그먼트가 있는 액티비티의 수명 주기는 해당 프래그먼트의 수명 주기에 `직접적인 영향`을 미친다.

액티비티에 대한 각 수명 주기 콜백이 각 프래그먼트에 대한 비슷한 콜백을 유발

예)
액티비티가 onPause()를 받으면, 해당 액티비티 내의 각 프래그먼트가 onPause()를 받는다.

하지만, 프래그먼트는 프래그먼트의 UI를 구축하고 소멸하는 것과 같은 작업을 하기 위해 `액티비티와의 고유한 상호작용`을 처리하는 몇 가지 수명 주기 콜백이 더 있다.

`onAttach()` : 프래그먼트가 액티비티와 연관되어 있었던 경우 호출 , 여기서 Actiivty가 전달
`onCreateView()` : 프래그먼트와 연관된 뷰 계층을 생성하기 위해 호출
`onActivityCreated()` : 액티비티의 onCreate() 메서드가 반환되면 호출
`onDestroyView()` : 프래그먼트와 연관된 뷰 계층이 제거되는 중일 때 호출
`onDetach()` : 프래그먼트가 액티비티와 연결이 끊어지는 중일 때 호출

----------

## 액티비티 LOG TEST

### 액티비티 시작

onCreate() - onStart() - onResume()

### 액티비티 시작 후 rotate

onPause() - onStop() - onDestroy() - onCreate() - onStart() - onResume()

### 액티비티 시작 후, 액티비티 backgroud 또는 다른 어플리케이션 실행

onPause() - onStop()

### 액티비티 종료

onPause() - onStop() - onDestroy()

### 백그라운드에서 앱에 재진입 또는 새로운 액티비티에서 돌아온 경우

onRestart() - onStart() - onResume()

### 백그라운드에서 종료

onDestroy() 

## 프래그먼트 LOG TEST

### 프래그먼트 시작

onAttach() - onCreate() - onCreateView() - onStart() - onResume()

### 프래그먼트 시작 후 rotate

onPause() - onStop() - onDestroyView() - onDestroy() - onDeteach() - onAttach() - onCreate() - onCreateView() - onAttach() - onCreate() - onCreateView() - onStart() - onStart() - onResume() - onResume()

### 새로운 액티비티로 이동
onPause() - onStop()

### 새로운 액티비티에서 돌아옴

onStart() - onResume()

### 프래그먼트 종료

onPause() - onStop() - onDestroyView() - onDestroy() - onDetach()

## 액티비티와 프래그먼트 LOG TEST

### 시작

FragmentLifeCycle: onAttach()
FragmentLifeCycle: onCreate()
FragmentLifeCycle: onCreateView()
FragmentLifeCycle: onStart()

ActivityLifeCycle: onStart()
ActivityLifeCycle: onResume()

FragmentLifeCycle: onResume()

### 새로운 액티비티로 이동

FragmentLifeCycle: onPause()

ActivityLifeCycle: onPause()

FragmentLifeCycle: onStop()

ActivityLifeCycle: onStop()

### 새로운 액티비티에서 back

ActivityLifeCycle: onRestart()

FragmentLifeCycle: onStart()

ActivityLifeCycle: onStart()
ActivityLifeCycle: onResume()

FragmentLifeCycle: onResume()

### 종료(back)

FragmentLifeCycle: onPause()

ActivityLifeCycle: onPause()

FragmentLifeCycle: onStop()

ActivityLifeCycle: onStop()

FragmentLifeCycle: onDestroyView()
FragmentLifeCycle: onDestroy()
FragmentLifeCycle: onDetach()

ActivityLifeCycle: onDestroy()

### background로 이동

FragmentLifeCycle: onPause()

ActivityLifeCycle: onPause()

FragmentLifeCycle: onStop()

ActivityLifeCycle: onStop()

### background 에서 다시 시작

ActivityLifeCycle: onRestart()

FragmentLifeCycle: onStart()

ActivityLifeCycle: onStart()
ActivityLifeCycle: onResume()

FragmentLifeCycle: onResume()


## app이 죽을 때 시나리오
https://developer.android.com/guide/components/activities/activity-lifecycle.html#asem

1. setContentVeiw 가 없는 경우
onCreate() 


