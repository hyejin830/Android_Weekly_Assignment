
# Create `interfaces` between your app code and your JavaScript code

## 1. Enabling JavaScript : 자바스크립트 `이용 가능하게` 설정하기
- JavaScript is disabled in a WebView by default.
   자바스크립트는 기본적으로 웹뷰에서 이용 불가능
- Javscript는 WebView에 붙혀진 `WebSettings`를 통해 이용가능
- retrieve WebSettings with getSettings(), then enable JavaScript with setJavaScriptEnabled.

`WebSettings`
- it provides access to a variety of other settings that you might find useful.
<!--  example)
then you can define a custom user agent string with setUserAgentString(), then query the custom user agent in your web page to verify that the client requesting your web page is actually your Android app. 
-->
- 다양한 셋팅에 접근하는 것을 제공

```
    WebView webView = findViewById(R.id.web_view);
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
```

## 2. html 처리

> asset 파일 추가

1) app - new - Folder - Assets Folder
2) assets - new - file - 파일이름.html

/assets/파일이름.html

> loadUrl(String url)

```
    webView.loadUrl("file:///android_asset/JSInterfaceSample.html");
```

## 3. Binding JavaScript code to Android code
: Interaction 의 의미와 비슷한 개념, 이름을 속성에 연관시키는 과정, 연관성을 만드는 것

- 자바스크립트 코드는 안드로이드 코드의 메소드를 호출 가능
ex) 자바스크립트의 alert() 대신에 Dialog

- JavaScript와 Android 코드간에 새 인터페이스를 바인딩하려면,
자바스크립트에 바인드하기 위한 클래스 인스턴스와 자바스크립트가 클래스에 접근하기 위한 호출을 할 수 있는 인터페이스 이름을 전달하는 `addJavaScriptInterface()`를 호출해야 한다.

- WebAppInterface 클래스는 정의한 메소드를 웹페이지에 허락해준다

WebAppInterface.java
```
    @JavascriptInterface
    public void doSearch(String toast){  //WebPage 액션 함수
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
```

- 인터페이스 이름 : Android
- addJavaScriptInterface() 와 인터페이스 이름(Android)를 통해 WebView에 실행시키어 JavaScript에 바인드 할 수 있다.

실행시키는 액티비티.java
```
    webView.addJavascriptInterface(new WebAppInterface(this),"Android");
```

JavaScript code can call a method in your Android code to display a Dialog,
Instead of using JavaScript's alert() function.
To bind a new interface between your JavaScript and Android code,
call addJavascriptInterface(), passing it a class instance to bind to your JavaScript and an interface name that your JavaScript can call to access the class.

## 4.WebAppInterface

- 생성자(Context)
- web page 액션 함수
- @JavascriptInterface

JSInterfaceSample.html 

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
            Android.doSearch(query);
        }
    </script>
    </html>
```

`Android.doSearch(query)` 
 인터페이스이름.앱 함수() 호출함으로 안드로이드에 액세스하여 함수 호출 

[출처]
asset파일 추가 
https://www.baswijdenes.com/portfolio/add-html-to-an-android-application

loadUrl - local html
https://mainia.tistory.com/5537

loadUrl -
https://stackoverflow.com/questions/7609130/set-the-value-of-an-input-field


