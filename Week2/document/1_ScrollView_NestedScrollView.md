# ScrollView와 NestedScrollView 비교

## 1. ScrollView

### extends FrameLayout
java.lang.Object
&nbsp;&nbsp;&nbsp;&nbsp;android.view.View
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android.view.ViewGroup
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android.widget.FrameLayout
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android.widget.ScrollView

Scrollview may have `only one direct child` placed within it.

- 오직 하나의 자식만 가질 수 있다
- view Group을 자식으로 추가하면 그 안에 복수 뷰들을 추가 가능
- 오직 수직 스크롤만 지원
- 수평 스크롤은 HorizontalScrollView
- 스크롤뷰에 RecyclerView 또는 ListView 안된다
- 낮은 유저 인터페이스 퍼포먼스의 결과

단 하나의 위젯만 넣을 수 있다.

<!-- 
- widget을 2개 이상 넣은 경우 Error
A scroll view can have only one child  ScrollViews can only have one child widget. If you want more children, wrap them in a container layout.
-->


## 2. NestedScrollView

### extends FrameLayout
java.lang.Object
&nbsp;&nbsp;&nbsp;&nbsp;android.view.View
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android.view.ViewGroup
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android.widget.FrameLayout
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;android.support.v4.widget.NestedScrollView

### interface 
1) NestedScrollingParent
2) NestedScrollingChild2
3) ScrollView

- 새 버전과 구 버전 모두에서 `중첩된 스크롤 부모와 스크롤 자식`을 지원
- 중첩되는 스크롤링이 사용 가능
- NestedScrollView는 좋은 유저인터페이스를 유연하게 제공
- 머터리얼 디자인 스크롤링 패턴을 지원


<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

------ 영문 정리 ---

ScrollView

To add multiple views within the scroll view, make the direct child you add a `view group`,
for example LinearLayout, and place additional views within that LinearLayout.

ScrollView supports vertical scrolling only.
For horizontal scrolling, use HorizontalScrollView instead.

```
Never add a RecyclerView or ListView to a scrollview. 
Doing so results in poor user interface performance and a poor user interface.
```

NestedScrollView

It supports acting as both a nested scrolling parent and child on both new and old versions of Android.
Nested scrolling is enabled by default.

NestedScrollView offers greater user interface fiexibility and support for the material design scrolling patterns.

-------------------

### support 라이브러리

v4 core-ui library

### Special-purpose layout containers
These support classes provide compatible implementations of specific layout patterns,
such as drawer views that can be pulled from the edge of the screen , sliding panels, and nesting lists within lists.

호환가능한!

NestedScrollView :

A scrolling layout that supports nesting of other scrolling views, allowing you to create lists, with items containing an additional, child lists. 

스크롤링 레이아웃은 다른 스크롤링의 중첩을 지원한다. 추가적인 아이템이 있는 리스트를 만들수 있고, 자식 리스트도.

These nested lists can contain items that scroll horizontally or vertically, separately from the parent list.

이 중첩되는 리스트는 수평 또는 수직으로 스크롤할 수 있는 아이템들을 포함시킬 수 있으며, 부모 리스트로부터 개별적으로....

[출처]
https://developer.android.com/topic/libraries/support-library/features?hl=en#special-purpose-layout-containers

++
한글로 다시 한번 정리. 차이점 위주로 서술하기. 
예제가 있으면 더 설득력 있을 것