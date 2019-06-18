
추가 과제

## 1. View

### 1.1 Setting Attribute Custom

#### AttributeSet

>attrs.xml

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="CustomView">
        <attr name="strokeWidth" format="dimension" />
        <attr name="strokeColor" format="color" />
        <attr name="circleColor" format="color" />

    </declare-styleable>
</resources>
```

>CustomView.java

TypedArray란? 공유 리소스 , 반드시 사용후에 recycle()해야 함

```
TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        // 설정된 값 가져오고 없으면 default로
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CustomView_strokeWidth, getContext().getResources().getDimensionPixelSize(R.dimen._10dp));
        strokeColor = typedArray.getColor(R.styleable.CustomView_strokeColor, Color.BLACK);
        circleColor = typedArray.getColor(R.styleable.CustomView_circleColor, Color.RED);

        typedArray.recycle();
```

>activity_main.xml
- 원하는 색으로 변경 가능

```
<com.example.week1_project.CustomView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center"
        android:layout_weight="2"
        app:strokeColor="@android:color/holo_green_dark"
        app:circleColor="@android:color/holo_blue_bright"
        app:strokeWidth="20dp" />
```

### 1.2 onSaveInstanceState/onRestoreInstanceState 
 - 뷰의 변동이 생길 시 처리를 위해 필요 ex) 가로 <-> 세로 모드 변경

색 변경이 그대로 유지된다

### 1.3 requestLayout invalidate 의 차이

invalidate 

- 화면이 유효하지 않으니 다시 그리도록 하라는 것
- 뷰를 다시 그리도록

requestLayout : layout을 갱신

- 사이즈에 영향을 줄때
- 이것은 onMeasure과 onLayout을 작동시킬 것이다. 

If in the course of processing the event, the view's bounds may need to be changed, the view will call requestLayout().
Similarly, if in the course of processing the event the view's appearance may need to be changed, the view will call invalidate().

## 2. Activity - Fragment 통신

### 2.1 ViewModel

- 현대적 접근 방법으로 충돌되는 코드가 적어지고, 연결된 클래스들이 느슨해진다
- `AAC 라이브러리의 ViewModel 클래스`를 생성 -> `observable LiveData`를 저장
- `MutableLiveData 서브클래스`를 사용함으로써, LiveData가 래핑한 객체를 업데이트 할 수 있는 setValue 메소드에 접근할 수 있다
- 백그라운드 스레드에서 postValue를 사용할 수 있다
- LiveData를 업데이트 할 때, 자동적으로 활성화된 모든 Observer들에게 통지하고 그들의 onChanged 콜백을 작동시킨다
- 콜백은 새로운 데이터를 가져오게 하고 UI를 업데이트한다

프로젝트에서..
- 프래그먼트에서, 실시간으로 업데이트하게 하고, 반영하게 할 LiveData를 관찰한다
- ViewModelProviders.of 함수에서 this 대신 getActivity()를 지나감으로써, ViewModel의 라이프사이클을 프래그먼트에서 액티비티로 확장시킬 수 있다
- 그럼으로 다중의 프래그먼트에 데이터를 공유할 수 있게 된다 (액티비티가 살아있는 경우에)

- 프래그먼트에서는 onActivityCreated에서 ViewModel을 할당하는 것이 베스트
- 이 메소드는 액티비티의 onCreate()가 완료되었음을 보장하기 때문에, 액티비티가 null 이 아니다

- getViewLifecycleOwner : LiveData를 프래그먼트의 뷰의 라이프사이클 범위로 하고 , 멀티플 observers가 추가되는 것에 메모리 누수를 피할 수 있음

### 2.2 Activity 버튼 액션 -> Fragment 텍스트 변경

#### 1) ViewModel 할당

```
sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
```

#### 2) Activity onClick 
```
sharedViewModel.setFromActivityText(activityEditText.getText());
```

#### 3) ViewModel의 setFromActivityText 실행

```
private MutableLiveData<CharSequence> fromActivitytext = new MutableLiveData<>();

public void setFromActivityText(CharSequence input){
        fromActivitytext.setValue(input);
    }
```

#### 4) Fragment UI

Observe 하고 있다가 변경 사항 콜백해서 적용

```
 viewModel.getFromActivityText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {                fragmentTextView.setText(charSequence);
            }
        });
```

## 3. Rest Api 추가, 보기

### 3.1 Retrofit이란?

HTTP API를 자바 인터페이스 형태로 사용할 수 있게한다

- 인터페이스를 구현해서 생성
- 각각의 Call은 인터페이스를 통해 동기 또는 비동기하는 HTTP 요청을 원격 웹서버에 보낼 수 있다
- HTTP 요청 
    - 어노테이션을 사용하여 명시
    - URL 파라미터 치환과 쿼리 파라미터 지원
    - 객체를 요청 body로 변환 (예: JSON, protocol buffers)
    
API 정의


>요청 메소드

- 모든 메소드들 반드시 상대 URL과 요청 메소드를 명시하는 어노테이션을 가지고 있어야 한다
- 요청 메소드 어노테이션 종류 : GET, POST, PUT, DELETE, HEAD

>URL 다루기

- 요청 URL은 동적으로 부분 치환 가능
- 부분 치환은 영문/숫자로 이루엉진 문자열과 {}로 감싸 정의
- 대응하는 @Path 를 메소드 매개변수에 명시

```
@GET("/group/{id}/users")
Call<List<User>> groupList(@Path("id") int groupId);
```

```
@POST("/users/new")
Call<User> createUser(@Body User user);
```

>컨버터

- Retrofit은 HTTP 요청 본문과 OKHttp의 ResponseBody형식과 @Body 에 이용하는 RequestBody 타입만 역직렬화 할 수 있다.
- 이외의 형식을 변환해주는 역할을 컨버터가 한다
- 종류
    - Gson
    - Jackson
    - Moshi
    - Protobuf
    - Wire
    - Simple XML

```
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

GitHubService service = retrofit.create(GitHubService.class);
```

Retrofit은 파라미터, 쿼리, 헤더 등의 매핑작업, 결과 처리작업 등 반복되는 작업들을 편리하게 처리할 수 있게끔 구현되어있는 라이브러리,
이 작업들을 통신을 위해 okhttp 라이브러리 이용
그래서 기본적으로 retrofit 라이브러리는 기본적으로 okhttp 라이브러리 포함

> 필요한 3요소

1) POJO 또는 Model 클래스
 - 서버에서 JSON 형식으로 통신하기 위함

2)interface
 - 가능한 HTTP 동작을 정의해 놓은 인터페이스

3)Retrofit.Builder클래스
 - 인터페이스를 사용하는 인스턴스
 - Builder는 BASE_URL을 설정

>RESTful

일반적으로 REST라는 아키텍쳐를 구현하는 웹 서비스를 나타내기 위해 사용되는 용어
- 'REST API'를 제공하는 웹 서비스는 'RESTful'하다고 할 수 있다
- 'RESTful'은 REST를 REST답게 쓰기 위한 방법
- REST 원리를 따르는 시스템은 RESTful이란 용어로 지징

### 3.2 GET, POST 

#### 1) 데이터 클래스 생성

원하는 항목의 필드 생성 및 getter , setter

#### 2) Retrofit 객체 싱글톤으로 생성해서 어디서든 사용하게 하기

NetRetrofit.class

#### 3) RetrofitExService

>GET 특정 id 레스토랑

```
@GET("restful/restaurant/{id}")
Call<Restaurant> getRestaurantInfo(@Path("id") int id);
```

>GET 전체 레스토랑

```
@GET("restful/restaurant")
Call<List<Restaurant>> getAllRestaurant();
```

>POST 레스토랑 추가

```
@POST("restful/restaurant/")
Call<Restaurant> postRestaurant(@Body Restaurant restaurant);
```

참고
[1]https://tausiq.wordpress.com/2014/03/20/android-custom-circle-view-with-stroke/
[2]https://www.youtube.com/watch?v=ACK67xU1Y3s
[3]https://square.github.io/retrofit/
[4]https://newland435.tistory.com/25
[5]https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html