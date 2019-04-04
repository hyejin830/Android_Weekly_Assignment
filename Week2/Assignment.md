## 2주차 과제

### [`1. ScrollView와 NestedScrollView에 대해서 비교하여 조사하시오.`](https://github.com/hyejin830/Android_Weekly_Assignment/blob/master/Week2/document/1_ScrollView_NestedScrollView.md)

목적 : 실제 업무에서 NestedScrollView를 많이 사용하기 때문에 경험해봐야 한다.

#### 참고 자료

[1]https://developer.android.com/reference/android/widget/ScrollView
[2]https://developer.android.com/reference/android/support/v4/widget/NestedScrollView

-------------------------

### [`2. 안드로이드 앱과 웹페이지가 서로 통신하는 방법을 조사하고, 간단한 Sample 앱을 작성하시오.`](https://github.com/hyejin830/Android_Weekly_Assignment/blob/master/Week2/document/2_Communicate_App_WebPage.md)

- WebView
- Java(Android)와 JavaScript(Html)의 통신

```
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Js Interface Sample</title>
</head>
<body>
<input id="search_input" type="text">
<input type="button" value="Search" onclick="doAndroidSearch()"/>
</body>
<script type="text/javascript">
    function doAndroidSearch() {
        var query = document.getElementById("search_input").value;
        // ...
    }
</script>
</html>
```

#### 참고 자료

[1]https://developer.android.com/guide/webapps/webview#BindingJavaScript

--------------------

### [`3. 다음과 같은 디자인을 갖는 나만의 Custom Dialog Class를 작성하시오.`](https://github.com/hyejin830/Android_Weekly_Assignment/blob/master/Week2/document/3_Custom_Dialog_Class.md)

- `비밀번호 변경해주세요` Button : 새로운 화면으로 이동
- `닫기` Button : Dialog를 닫기
- `30일간 다시 보지 않기` Check Box : 앱을 재시작 시, 일정 시간 동안 Dialog를 보여주지 않기
- `동그란` Button (가능하면)

 <img src="https://github.com/hyejin830/Android_Weekly_Assignment/blob/master/Week2/images/dialog.png" width="20%"></img>

#### 참고 자료

[1]https://developer.android.com/guide/topics/ui/dialogs
