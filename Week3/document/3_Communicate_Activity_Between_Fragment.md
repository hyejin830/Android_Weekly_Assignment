# Activity와 Fragment가 서로 통신하는 방법에 대해서 조사하시오. (Activity <- -> Fragment)

Fragment는 Activity로부터 독립적인 객체로 구현되었고
여러 개의 액티비티 안에서 사용할 수 있는 것이 사실이지만,
프래그먼트의 주어진 인스턴스는 `그것을 포함하고 있는 액티비티에 직접적으로 연결`되어 있다.

프래그먼트는 getActivity()를 사용하여 Activity 인스턴스에 액세스하여 액티비티 레이아웃에서 뷰를 찾는 것과 같은 작업을 손쉽게 수행 가능

```
View listView = getActivity().findViewById(R.id.list);
```

액티비티도 프래그먼트 안의 메서드를 호출할 수 있다.

FragmentManager 로부터의 Fragment에 대한 참조를 가져와야 하며,
이 때 findFragmentById() 또는 findFragmentByTag()를 사용

```
ExampleFragment fragment = (ExampleFragment) getFragmentManager().findFragmentById(R.id.example_fragment);
```

## 액티비티로부터 이벤트 콜백 생성

프래그먼트로 하여금 액티비티와 이벤트를 공유하게 해야 할 수 있다

한 가지 좋은 방법

프래그먼트 내부의 콜백 인터페이스를 정의한 다음 해당 호스트 액티비티가 이를 구현하도록 하는 것

액티비티가 인터페이스를 통해 콜백을 수신하면, 필요에 따라 그 정보를 레이아웃 내의 다른 프래그먼트와 공유 할 수 있음

예)

어떤 뉴스 애플리케이션에서
액티비티 하나에 프래그먼트가 두개 있음
하나는 기사 목록을 표시 (프래그먼트A)
다른 하나는 기사 하나를 표시(프래그먼트B)

목록 항목이 선택되면 프래그먼트A가 액티비티에 알려야 프래그먼트 B에 해당 기사를 표시하라고 알릴 수 있다.

이 경우 , Listener 인터페이스는 프래그먼트A 내부에 선언

```
// Container Activity must implement this interface
    public interface OnArticleSelectedListener {
        public void onArticleSelected(Uri articleUri);
    }
```

프래그먼트를 호스팅하는 액티비티가 Listener 인터페이스를 구현하고 Listener를 재 정의하며 프래그먼트A 로부터 발생한 이벤트를 프래그먼트 B에 알림! 

호스트 액티비티가 이 인터페이스를 구현하도록 하ㄹ면 프래그먼트 A의 onAttach() 콜백 메서드가 Listener의 인스턴스를 생성해야 한다. 

이때, onAttach()로 전달되는 Activity를 형변환하는 방법을 쓴다. 


https://developer.android.com/guide/components/fragments#CommunicatingWithActivity


2가지
https://medium.com/@kimtaesoo188/android-weekly-277-from-fragments-to-activity-the-lambda-way-cac15973721a

8가지 방법
https://hackernoon.com/8-ways-to-communicate-between-fragment-and-activity-in-android-apps-235b60005d04


1) Interface

interface onboardFragmentDelegate 


```
interface OnboardingFragmentDelegate {
    fun onboardingFragmentDidClickStartButton(fragment: OnboardingFragment)
}
class OnboardingFragment: Fragment() {
    var delegate: OnboardingFragmentDelegate? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnboardingFragmentDelegate) {
            delegate = context
        }
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton.onClick {
            delegate?.onboardingFragmentDidClickStartButton(this)
        }
    }
}
class OnboardingActivity: AppCompatActivity(), OnboardingFragmentDelegate {
    override fun onboardingFragmentDidClickStartButton(fragment: OnboardingFragment) {
        onboardingService.hasShown = true
        startActivity<LoginActivity>()
    }
}
```

2) ViewModel : share data between fragments

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