
### View

This class represents the basic building block for user interface components. 
이 클래스는 사용자 인터페이스 구성 요소의 기본 구성 요소를 나타냅니다.

A View occupies a rectangular area on the screen and is responsible for drawing and event handling. 
뷰는 화면의 직사각형 영역을 차지하며 그리기 및 이벤트 처리를 담당합니다.

View is the base class for widgets, which are used to create interactive UI components (buttons, text fields, etc.). 
뷰는 대화형 UI 구성요소(버튼, 텍스트 필드 등)를 만드는 위젯의 기본 클래스이다.

The ViewGroup subclass is the base class for layouts, which are invisible containers that hold other Views (or other ViewGroups) and define their layout properties.
뷰 그룹 서브클래스는 다른 뷰를 보유하고 레이아웃 속성을 정의하는 보이지 않는 컨테이너인 레이아웃으 기본 클래스이다.

View - Widget
ViewGroup - Layout

### Using Views

All of the views in a window are arranged in a single tree.
창의 모든 뷰는 단일 트리로 정렬되어있다.

You can add views either from code or by specifying a tree of views in one or more XML layout files. 
코드에서 뷰를 추가하거나 하나 이상의 XML 레이아웃 파일에서 뷰들의 트리를 지정하여 추가할 수 있다.

There are many specialized subclasses of views that act as controls or are capable of displaying text, images, or other content.
컨트롤로 작동하거나 텍스트, 이미지 또는 기타 내용을 표시할 수 있는 뷰의 특수 하위 클래스가 많이 있다.

* specialized 전문화된, 특수화된

Once you have created a tree of views, there are typically a few types of common operations you may wish to perform:
뷰들의 트리를 만ㄷㄴ 후, 일반적으로 수행할 수 있는 몇가지 일반 작업 유형이 있다.

- Set properties: for example setting the text of a TextView. The available properties and the methods that set them will vary among the different subclasses of views. Note that properties that are known at build time can be set in the XML layout files.

속성 설정 : 예를 들어, TextView의 텍스트 설정.
가능한 속성과 메소드는 뷰들의 다양한 서브클래스에따라 다르게 설정한다. 빌드 시 알려진 속성은 XML 레이아웃 파일에 설정할 수 있다. 


- Set focus: The framework will handle moving focus in response to user input. To force focus to a specific view, call requestFocus().

포커스 설정 : 프레임워크는 유저의 입력에 따라 포커스를 움직이는 것을 다룰 것이다. 특정한 뷰의 포커스를 집중하기 위해, requestFocus()를 호출한다.

- Set up listeners: Views allow clients to set listeners that will be notified when something interesting happens to the view. For example, all views will let you set a listener to be notified when the view gains or loses focus. You can register such a listener using setOnFocusChangeListener(android.view.View.OnFocusChangeListener). Other view subclasses offer more specialized listeners. For example, a Button exposes a listener to notify clients when the button is clicked.

리스너 설정 : 뷰들을 클라이언트들에게 뷰에게 흥미로운 무언가가 발생할 때 알려주는 리스너 설정을 허락한다. 예를 들어, 모든 뷰들은 뷰가 포커스를 얻거나 잃을 때 설정된 리스너에게 알려준다. setOnFocusChangeListener를 이용하여 등록할 수 있다. 다른 뷰 하위클래스들은 구체화된 리스너들을 더 제공한다. 예를 들어, 버튼은 버튼이 클릭될 때 클라이언트에게 알리는 리스너를 노출한다. 

- Set visibility: You can hide or show views using setVisibility(int).

Note: The Android framework is responsible for measuring, laying out and drawing views. You should not call methods that perform these actions on views yourself unless you are actually implementing a ViewGroup.

참고 : 안드로이드 프레임워크는 뷰들을 측정하고, 레이아웃하고 그리기를 담당한다. 실제로 뷰그룹을 구현하지 않는한 너 스스로 이 액션들을 수행시키는 메소드를 호출하면 안된다.

-------------

When an Activity receives focus, it will be requested to draw its layout. 
> 액티비티가 포커스를 받게 될때, 그 액티비티의 레이아웃을 그리도록 요청한다. 

The Android framework will handle the procedure for drawing, but the Activity must provide the root node of its layout hierarchy.
> 안드로이드 프레임워크는 그리는 과정을 다루지만 액티비티는 반드시 상속관관계의 루트노드를 제공해야 한다. 

Drawing begins with the root node of the layout. It is requested to measure and draw the layout tree.
> 레이아웃의 루트노드와 함계 그리기를 시작한다. 레이아웃 트리를 측정하고 그리기가 요청된다.

Drawing is handled by walking the tree and rendering each View that intersects the invalid region. 


In turn, each ViewGroup is responsible for requesting each of its children to be drawn (with the draw() method) and each View is responsible for drawing itself. 


Because the tree is traversed pre-order, this means that parents will be drawn before (i.e., behind) their children, with siblings drawn in the order they appear in the tree.
> 암튼 부모가 먼저 그려지고 그 다음 자식 

## Layout

Layout is a two pass process: a measure pass and a layout pass. 
>레이아웃은 두 단계의 프로세스가 있다 : 측정 단계와 레이아웃 단계 

[measuring pass]
The measuring pass is implemented in measure(int, int) and is a top-down traversal of the view tree. 
>측정 단계는 measure(int,int)로 구현되고 뷰 트리의 탑다운 형태로 진행한다.

Each view pushes dimension specifications down the tree during the recursion. 
>각 뷰는 재귀하는 동안 넓이 사양을 트리 아래로 푸시한다.

At the end of the measure pass, every view has stored its measurements. 
>측정 단계가 끝날 때마다, 모든 뷰는 측정 값이 저장된다.

The second pass happens in layout(int, int, int, int) and is also top-down. 
>layout(int,int,int,int)로 두번째 단계가 발생하고 이것 또한 탑다운이다.

During this pass each parent is responsible for positioning all of its children using the sizes computed in the measure pass.
>이 단계 동안 각 부모는 측정 단계에서 계산된 사이즈로 모든 하위 항목을 배치해야한다.


When a view's measure() method returns, its getMeasuredWidth() and getMeasuredHeight() values must be set, along with those for all of that view's descendants. 

A view's measured width and measured height values must respect the constraints imposed by the view's parents. 

This guarantees that at the end of the measure pass, all parents accept all of their children's measurements. 

A parent view may call measure() more than once on its children. For example, the parent may measure each child once with unspecified dimensions to find out how big they want to be, then call measure() on them again with actual numbers if the sum of all the children's unconstrained sizes is too big or too small.

The measure pass uses two classes to communicate dimensions. 

The MeasureSpec class is used by views to tell their parents how they want to be measured and positioned. 

The base LayoutParams class just describes how big the view wants to be for both width and height. For each dimension, it can specify one of:

an exact number
MATCH_PARENT, which means the view wants to be as big as its parent (minus padding)
WRAP_CONTENT, which means that the view wants to be just big enough to enclose its content (plus padding).


There are subclasses of LayoutParams for different subclasses of ViewGroup. For example, AbsoluteLayout has its own subclass of LayoutParams which adds an X and Y value.
MeasureSpecs are used to push requirements down the tree from parent to child. A MeasureSpec can be in one of three modes:

UNSPECIFIED: This is used by a parent to determine the desired dimension of a child view. For example, a LinearLayout may call measure() on its child with the height set to UNSPECIFIED and a width of EXACTLY 240 to find out how tall the child view wants to be given a width of 240 pixels.
EXACTLY: This is used by the parent to impose an exact size on the child. The child must use this size, and guarantee that all of its descendants will fit within this size.
AT_MOST: This is used by the parent to impose a maximum size on the child. The child must guarantee that it and all of its descendants will fit within this size.


To initiate a layout, call requestLayout(). This method is typically called by a view on itself when it believes that is can no longer fit within its current bounds.

#### Drawing

Drawing is handled by walking the tree and recording the drawing commands of any View that needs to update.
> 트
 
 After this, the drawing commands of the entire tree are issued to screen, clipped to the newly damaged area.
> 이후, 전체 트리의 그리기 명령들은 스크린 위에 발행되고, ...

The tree is largely recorded and drawn in order, with parents drawn before (i.e., behind) their children, with siblings drawn in the order they appear in the tree. 
> 트리는 순서대로 기록되고 그려진다. 그들의 자신 전에 부모가 그려지고, 순서대로 트리가 그려진다.
 
If you set a background drawable for a View, then the View will draw it before calling back to its onDraw() method. 
> 뷰를 위해 배경을 설정하면, 뷰는 onDraw() 메소드를 부르기 전에 그릴 것이다. 

The child drawing order can be overridden with ViewGroup#setChildrenDrawingOrderEnabled(boolean) in a ViewGroup, and with setZ(float) custom Z values} set on Views.
자식을 그리는 거슨 ......

To force a view to draw, call invalidate().


========



Measure() 호출
 : 각 뷰들은 자신의 getMeausredWidth() , getMeasureHeight() 로 리턴할 값들을 셋팅 -> 자신의 사이즈를 정한다.
 자신의 부모로부터 부여된 제약 사항을 따라야 한다.
 이로써 모든 부모들은 자신의 자식들의 사이즈를 알게 된다.
 
==> 부모들은 첫번째 measure()로 자식들이 원하는 크기를 알아내고, 원하는 크기가 비상적이라면 두번째 measure()을 통해 자식들의 사이즈를 정하는데 간섭한다.

이 measure를 겪는 동안 2가지 클래스를 사용
1) ViewGroup.LayoutParams
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

출처 : https://developer.android.com/guide/topics/ui/how-android-draws.html


이제 정리하자