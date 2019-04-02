
## Creating Interfaces

Create interfaces between your app code and your JavaScript code

1. Enabling JavaScript : 자바스크립트 이용 가능하게 하다
- JavaScript is disabled in a WebView by default. : 자바스크립는 기본적으로 웹뷰에서 이용 불가능
- WebSettings with getSettings enable JavaScript with setJavaScriptEnabled. : 

WebSettings
- it provides access to a variety of other settings that you might find useful. 
- Developing a web application for the WebView in your Android app
- setUserAgentString() : ...이해 안됨

```
    WebView webView = findViewById(R.id.web_view);
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
```

## Binding JavaScript code to Android code

JavaScript code can call a method in your Android code to display a Dialog,
Instead of using JavaScript's alert() function.

To bind a new interface between your JavaScript and Android code,
call addJavascriptInterface(), passing it a class instance to bind to your JavaScript
and an interface name that your JavaScript can call to access the class.

JavaScript와 Android 코드간에 새 인터페이스를 바인딩하려면,
addJavascriptInterface ()를 호출한다 이것을 통해 클래스 인스턴스를 전달하여 JavaScript에 바인딩하고 JavaScript가 클래스에 액세스하기 위해 호출 할 수있는 인터페이스 이름을 전달합니다.

자바스크립트에 바인드하기 위한 클래스 인스턴스와 자바스크립트가 클래스에 접근하기 위한 호출을 할 수 있는 인터페이스 이름을 전달한다.
JavaScript는 클래스에 액세스하여 호출할 수 있다.

```
    webView.addJavascriptInterface(new WebAppInterface(this),"Android");
```

### WebAppInterface

- 생성자(Context)
- web page 액션 함수
- @JavascriptInterface

```
    @JavascriptInterface
    public void doSearch(String toast){
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
```

> asset 파일 추가

1) app - new - Folder - Assets Folder
2) assets - new - file - 파일이름.html

/assets/파일이름.html

> loadUrl(String url)

```
    webView.loadUrl("file:///android_asset/JSInterfaceSample.html");
```

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

`Android.doSearch(query)` : 


http://sumi3360.blogspot.com/2014/12/android_52.html

[출처]
asset파일 추가 
https://www.baswijdenes.com/portfolio/add-html-to-an-android-application

loadUrl - local html
https://mainia.tistory.com/5537