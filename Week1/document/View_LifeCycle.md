
## View LifeCycle

Constructor
onAttachedToWindow()
measure() onMeasure()
layout onLayout()
dispatchDraw()
draw() onDraw()

1. Constructor
 : 생성자에서 초기화를 진행하고, default 값을 설정
 - 초기 설정을 쉽게 설정하기 위해 AttributeSet 인터페이스 사용 가능(attrs.xml)

2. onAttachedToWindow()
 : 부모 뷰가 addView(childView) 함수를 호출하고 나서 자식 뷰는 윈도에 붙게 된다. 이때부터 뷰의 id를 통해 접근 가능

3. onMeasure()
: 뷰의 크기를 측정하는 단계
두 단계의 과정
(1) 뷰가 원하는 사이즈를 계산
(2) MeasureSpec 에 따라 크기와 mode를 가져온다

4. onLayout()
 : 뷰의 크기와 위치를 할당

5. onDraw()
 : 뷰를 실제로 그리는 단계 
 - Canvas와 Paint 객체를 사용하면 필요한 것을 그리게 된다. 
 - Canvas : 뷰의 모양 그리는 객체
 - Paint : 뷰의 색 그리는 객체

6. View Update
뷰를 다시 그리도록 유도하는 invalidate() 와 requestLayout() 함수
--> 런타임에 뷰를 다시 그릴 수 있게 허용
 - invalidate() : 단순히 뷰를 다시 그릴 때 사용
 - requestLayout() : ...