# Activity와 Fragment가 서로 통신하는 방법에 대해서 조사하시오. (Activity <- -> Fragment)

Fragment는 Activity로부터 `독립적인 객체로 구현`되었고 여러 개의 액티비티 안에서 사용할 수 있는 것이 사실이지만,
프래그먼트의 주어진 인스턴스는 `그것을 포함하고 있는 액티비티에 직접적으로 연결`되어 있다.

## 방법1. 직접 호출

### 1.1 프래그먼트

프래그먼트는 `getActivity()를 사용`하여 Activity 인스턴스에 액세스하여 액티비티 레이아웃에서 뷰를 찾는 것과 같은 작업을 손쉽게 수행 가능

```
View listView = getActivity().findViewById(R.id.list);
```

### 1.2 액티비티

액티비티도 `프래그먼트 안의 메서드를 호출`할 수 있다.

FragmentManager 로부터의 Fragment에 대한 참조를 가져와야 하며,
이 때 findFragmentById() 또는 findFragmentByTag()를 사용

```
ExampleFragment fragment = (ExampleFragment) getFragmentManager().findFragmentById(R.id.example_fragment);
```

## 방법2. 액티비티로부터 이벤트 콜백 생성

- 프래그먼트로 하여금 액티비티와 이벤트를 공유하게 해야 할 수 있다
- 프래그먼트 내부의 콜백 인터페이스를 정의한 다음 해당 호스트 액티비티가 이를 구현하도록 하는 것
- 액티비티가 인터페이스를 통해 콜백을 수신하면, 필요에 따라 그 정보를 레이아웃 내의 다른 프래그먼트와 공유 할 수 있음

### 2.1 프래그먼트

- 인터페이스 작성
- onAttach 함수 
- 프래그먼트 내부 이벤트 

### 2.2 액티비티

- 프래그먼트 인터페이스 implement 
- 콜백 수신

3. ViewModel
   
- 특정 Activity나 Fragment에 데이터를 제공
- 프래그먼트 간 데이터를 공유

4. EventBus
https://github.com/greenrobot/EventBus

5. RxJava

- Observable : 이벤트를 발생시키는 주체
- Subscriber : 이벤트를 전달 받는 객체
- PublishSubject : 구독한 시점으로부터 발생되는 이벤트를 계속 전달 받는다.


출처 : 
https://developer.android.com/guide/components/fragments#CommunicatingWithActivity
https://hackernoon.com/8-ways-to-communicate-between-fragment-and-activity-in-android-apps-235b60005d04
https://tourspace.tistory.com/23