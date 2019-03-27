# Android에서 화면 상에 View가 그려지는 원리에 대해서 조사하시오.

## View의 특징 
- 트리 구조 (계층 구조)
- 전위 순회 방식 [루트-왼쪽-오른쪽]
- 탑다운 형식
==> 부모뷰는 자식뷰가 그려지기 전에(즉, 자식뷰 뒤에) 그려지고, 형제 뷰는 전위 방식에 따라 순서대로 그려진다.

setContentView 메소드를 통해 셋팅한 View : root node

1. Activity가 포커스를 갖게 되면, 시스템은 Activity의 root node 요청. root node를 시작으로 트리 구조의 아래로 향하여 뷰를 그리기 시작

순서 : 1 - 2 - 3
 : 자신의 사이즈를 정하고, 자신의 위치를 정한다.

1. Measure(측정) : 뷰의 크기를 정하는 과정
 : 부모노드 - 자식노드 경유하며 실행

Measure() 호출
 : 각 뷰들은 자신의 getMeausredWidth() , getMeasureHeight() 로 리턴할 값들을 셋팅 -> 자신의 사이즈를 정한다.
 자신의 부모로부터 부여된 제약 사항을 따라야 한다.
 이로써 모든 부모들은 자신의 자식들의 사이즈를 알게 된다.
 
==> 부모들은 첫번째 measure()로 자식들이 원하는 크기를 알아내고, 원하는 크기가 비상적이라면 두번째 measure()을 통해 자식들의 사이즈를 정하는데 간섭한다.

 :측정과정에서 부모 뷰와 자식 뷰간의 크기 정보를 전달하기 위해 2가지 클래스 사용

1) ViewGroup.LayoutParams
   : 자식 뷰가 부모 뷰에게 자신이 어떻게 측정되고 위치를 정할지 요청하는데 사용
 - 뷰의 width, height 가 얼마만큼의 크기를 원하는지 설명
 - 구성요소
   - 정확한 수치 : ex) 50dp, 100dp
   - MATCH_PARENT :  자신의 부모만큼 크기
   - WRAP_CONTENT :  자신의 내용물만 담을 수 있을 만큼 크기 

2) MeasureSpec
 - 뷰 트리상 부모가 자식에게 전달하는 requirment(요구사항)을 담고있다.
 - 세가지 모드
   - UNSPECIFIED : 부모가 자식이 얼마만큼의 치수를 원하는지 알기 위해 사용한다. 제약사항이 없다. 자식이 원하는 수치를 가질 수 있다.
   - EXACTLY : 부모가 자식에게 정확한 수치를 부여하며, 자식들은 이 치수를 사용해야 한다.
   - ATMOST : 부모가 자식에게 가질 수 있는 최대 사이즈를 알려준다.
   
2. Layout : 뷰의 위치를 정하는 과정
: 부모 노드에서 자식 노드를 경유해서 실행, 뷰와 자식 뷰들의 크기와 위치를 할당할 때 사용

3. Draw : 최종적으로 뷰를 캔버스 위에 실제로 그리는 과정

# View LifeCycle

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