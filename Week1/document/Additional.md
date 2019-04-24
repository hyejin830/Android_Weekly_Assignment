

1. Canvas Save/Restore 
 - 뷰의 변동이 생길 시 처리를 위해 필요

2. Setting Attribute Custom

2.1 values - attr.xml 파일 추가


```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="CustomView">
        <attr name="bottom_color" format="color"/>
    </declare-styleable>
</resources>
```



1. Processing Event


2. Using onMeasue/onLayout/onDraw

괜찮은 블로그 정리
https://onecellboy.tistory.com/344

onMeasure

View의 크기를 결정할 때 불리는 함수

1. requestLayout  

레이아웃을 갱신
