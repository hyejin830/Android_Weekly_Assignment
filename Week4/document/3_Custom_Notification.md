## 3. 다음과 같은 디자인을 갖는 나만의 Custom Notification Class를 작성하시오.

- Activity의 어떠한 상태가 변경되면 해당 알림에도 상태가 변경되게 할 것 
1) 테스트 환경 'DEV' -> 테스트 환경 'Staging'
2) 해당 알림을 클릭하거나 드래그했을 때 알림이 사라지지 않게 할 것
3) 해당 알림을 강제로 사라지게 만드는 버튼이 있을 것
4) Notification channel이 있을 것

출처 :
https://nicopatch.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9CCustom-notification-view-%EB%A7%8C%EB%93%A4%EA%B8%B0
http://www.masterqna.com/android/8478/notification-custom%EC%9C%BC%EB%A1%9C-%EA%B5%AC%ED%98%84%EC%9D%84-%ED%96%88%EB%8A%94%EB%8D%B0-textview%EC%9D%98-%EA%B0%92%EC%9D%84-%EC%A7%80%EC%86%8D%EC%A0%81%EC%9C%BC%EB%A1%9C-%EB%B3%80%EA%B2%BD%ED%95%98%EA%B3%A0-%EC%8B%B6%EC%9D%80%EB%8D%B0
https://stackoverflow.com/questions/15568754/how-to-close-the-status-bar-notification-panel-after-notification-button-click

-----------------------

NotificationCompat.Builder 개체에서 알림에 대한 UI 정보와 작업을 지정합니다.
`알림 자체를 생성`하려면 NotificationCompat.Builder.build()를 호출합니다. 

이는 사양이 포함된 Notification 객체를 반환합니다. 
`알림을 발행`하려면 NotificationManager.notify()를 호출해서 시스템에 Notification 객체를 전달합니다.

필수 알림 콘텐츠
Notification 객체는 다음을 `반드시 포함`해야 합니다.
setSmallIcon()이 설정한 작은 아이콘
setContentTitle()이 설정한 제목
setContentText()이 설정한 세부 텍스트