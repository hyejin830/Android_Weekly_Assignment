## 1. RecyclerView에 대해서 조사하고, RecyclerView의 주요 클래스를 모두 구현한 코드를 작성하시오.

- 많은 데이터를 한정된 View를 재사용해서 표시
- 스크롤의 성능이 좋다
- 최소한 Adapter와 ViewHolder를 만들 필요 있음

ViewHolder : 재활용 View에 대한 모든 서브 뷰를 보유
LayoutManager : 아이템의 항목을 배치
ItemDecoration : 아이템 항목에서 서브뷰에 대한 처리
ItemAnimaiton : 아이템 항목이 추가, 제거 되거나 정렬될 때 애니메이션 처리

### RecyclerView.Adapter

View를 만들고, 표시되는 View와 데이터 연결

### RecyclerView.ViewHolder

일반적으로 Adapter 내에서 RecyclerView.ViewHolder를 상속하는 클래스를 만든다

- ViewHolder는 View에 대한 참조를 유지

- Adapter의 `onCreateViewHolder()` 메서드로 ViewHolder의 인스턴스를 생성해서 반환 (뷰 홀더를 생성하고 뷰를 붙여주는 부분)

- `onBindViewHolder()` 메서드로 ViewHolder에 설정한 View에 데이터를 설정
 (`재활용 되는 뷰가 호출하여 실행`되는 메소드, 뷰 홀더를 전달하고 어댑터는 `position`의 데이터를 결합)
- ViewHolder의 멤버변수에 View를 저장해둠으로써 findViewById를 매번 실행할 필요가 없어 성능 향상

### RecyclerView.LayoutManager

- RecyclerView에서 view의 `위치와 크기`를 결정
- View의 재사용 규칙을 관리

3가지의 매니저
1) LinearLayoutManager
2) GridLayoutManager
3) StaggeredGridLayoutManager

- ViewGroup구조로 RecyclerView에서 onLayout() 메서드를 호출해 LayoutManager에 처리를 맡긴다
- LayoutManager는 필요해진 아이템의 ViewHolder를 Adapter로부터 가져오고
- Adapter에서 ViewHolder에 데이터를 설정하게 된다

재사용 될 수 있는 이유는
스크롤해서 필요 없어진 View는 ViewHolder로서 Scrap 리스트에 추가
Adapter를 통해 데이터를 바인딩하여 재사용

출처 : 안드로이드 개발 레벨업 교과서
https://itmining.tistory.com/12
https://github.com/sjthn/RecyclerViewDemo/blob/decorator-and-animator/app/src/main/java/com/example/srijith/recyclerviewdemo/UserListItemDecorator.java