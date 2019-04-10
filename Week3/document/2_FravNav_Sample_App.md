# Support 디자인 라이브러리의 Bottom Navigation을 사용하여 하단 네비게이션 관련 3rd 라이브러리인 FragNav라는 라이브러리를 적용하는 Sample 앱을 작성하시오.



Using attach and detach for opening new fragments on the current stack and using show and hide for switching between tabs - DETACH_ON_NAVIGATE_HIDE_ON_SWITCH

There is also a possibility to automatically add and inflate all the root fragments right after creation (This makes sense only using HIDE and DETACH_ON_NAVIGATE_HIDE_ON_SWITCH modes). To have this you should set "eager" mode to true on the builder (Default is false).
