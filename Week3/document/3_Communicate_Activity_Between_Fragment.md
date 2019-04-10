# Activity와 Fragment가 서로 통신하는 방법에 대해서 조사하시오. (Activity <- -> Fragment)

Fragment는 Activity로부터 `독립적인 객체로 구현`되었고 여러 개의 액티비티 안에서 사용할 수 있는 것이 사실이지만,
프래그먼트의 주어진 인스턴스는 `그것을 포함하고 있는 액티비티에 직접적으로 연결`되어 있다.

방법1. 직접 호출

1.1 프래그먼트

프래그먼트는 `getActivity()를 사용`하여 Activity 인스턴스에 액세스하여 액티비티 레이아웃에서 뷰를 찾는 것과 같은 작업을 손쉽게 수행 가능

```
View listView = getActivity().findViewById(R.id.list);
```

1.2 액티비티

액티비티도 `프래그먼트 안의 메서드를 호출`할 수 있다.

FragmentManager 로부터의 Fragment에 대한 참조를 가져와야 하며,
이 때 findFragmentById() 또는 findFragmentByTag()를 사용

```
ExampleFragment fragment = (ExampleFragment) getFragmentManager().findFragmentById(R.id.example_fragment);
```

방법2. 액티비티로부터 이벤트 콜백 생성

- 프래그먼트로 하여금 액티비티와 이벤트를 공유하게 해야 할 수 있다
- 프래그먼트 내부의 콜백 인터페이스를 정의한 다음 해당 호스트 액티비티가 이를 구현하도록 하는 것
- 액티비티가 인터페이스를 통해 콜백을 수신하면, 필요에 따라 그 정보를 레이아웃 내의 다른 프래그먼트와 공유 할 수 있음

2.1 프래그먼트

- 인터페이스 작성
- onAttach 함수 
- 프래그먼트 내부 이벤트 

2.2 액티비티

- 프래그먼트 인터페이스 implement 
- 콜백 수신

3. ViewModel

4. RxJava

5. EventBus
https://github.com/greenrobot/EventBus




5) ViewModel : share data between fragments

```
class OnboardingSharedViewModel: ViewModel() {
    val finish = MutableLiveData<Unit>()
}
class OnboardingActivity: AppCompatActivity(), OnboardingFragmentDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(OnboardingSharedViewModel::class.java)
        viewModel.finish.observe(this, Observer {
            startActivity<LoginActivity>()
        })
    }
}
```

Call ViewModelProviders.of(activity) to get the same ViewModel with the activity

```
class OnboardingFragment: Fragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity).get(OnboardingSharedViewModel::class.java)
        startButton.onClick({
            viewModel.finish.value = Unit
        })
    }
}
```

3) RxJava : 

```
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
// Use object so we have a singleton instance
object RxBus {
    
    private val publisher = PublishSubject.create<Any>()
    fun publish(event: Any) {
        publisher.onNext(event)
    }
    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}
// OnboardingFragment.kt
startButton.onClick {
    RxBus.publish(OnboardingFinishEvent())
}
// OnboardingActivity.kt
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        
    RxBus.listen(OnboardingFinishEvent::class.java).subscribe({
        // finish
    })
}
```

4) EventBus : https://github.com/greenrobot/EventBus

components are loosely coupled, every component and broadcast can listen to event from a singleton

https://gunhansancar.com/ease-communication-between-activities-fragments-services/

```
data class OnboardingFinishEvent()
class OnboardingActivity: AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }
    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onOnboardingFinishEvent(event: OnboardingFinishEvent) {
        // finish
    }
}
class OnboardingFragment: Fragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton.onClick {
            EventBus.getDefault().post(OnboardingFinishEvent())
        }
    }
}
```

출처 : 
https://developer.android.com/guide/components/fragments#CommunicatingWithActivity
https://hackernoon.com/8-ways-to-communicate-between-fragment-and-activity-in-android-apps-235b60005d04