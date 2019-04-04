
## 프로젝트 개요

#### CustomDialogPasswordChangeTestActivity.java
 - 다이얼로그를 띄우는 액티비티
 - 보지않기 check 확인 후 : false - 보기 / true - 보지 않기
    - false인 경우, 다이얼로그 띄우기
    - true인 경우, 다이얼로그 띄우지 않기

#### CustomDialogPasswordChange.java
 - `DialogFragment`를 상속
 - `닫기` 버튼 액션에서 체크 되어 있을 시, `Shared Preference 데이터` 저장

#### PasswordChangeActivity.java
 - 비밀번호 변경 버튼 액션 시 이동하는 액티비티

-------------

## 1. DialogFragment란?

- 다이얼로그를 생성하는데 원하는 모든 컨트롤를 제공, 이것의 외관을 관리
<!--
- 정확하게 유저의 back 버튼 또는 스크린을 회전할 때 라이프 사이클로 다룬다는 것을 보장
- 재사용을 허락
-->

### 1.1 Dialog Fragment 생성

- 커스텀 레이아웃을 포함하여 다양한 디자인 적용 가능
- DialogFragment 상속, onCreateDialog() 콜백함수에서 AlertDialog를 생성

AlertDialog : Title, Content Area, Action buttons 
 - Positive/Negatvie/Neutral
 - list
 - multiple-chocie or single-choice list

### 1.2 Custom Layout 생성

- 커스텀 레이아웃을 사용한다고 해도 AlertDialog.Builder 메소드를  사용해서 버튼과 타이틀을 추가 가능
- DialogFragment에 레이아웃을 인플레이트 하기 위해
  getLayoutInflator()와 함게 LayoutInflator를 얻고 , inflate()를 호출해야한다

### 1.3 다이얼로그 보여주기 풀스크린 또는 내장된 프래그먼트

- embeddable Fragment
- AlertDialg.Builder를 사용하지 못함
- 레이아웃 안에 dialog UI를 꼭 정의하고 onCreateView 콜백으로 레이아웃을 로딩

----------------------------------------------------

## 2. 값을 저장하는 방법 (Data and file Storage)

- 구체적인 요구사항에 따라, 데이터 크기에 따라, 데이터 종류에 따라, 비공개 데이터인지 공유데이터인지에 따라 옵션들 중 선택

1. Internal file storage 
   디바이스 파일 시스템에 앱 비밀 파일을 저장
2. External file storage
   외부 파일 시스템에 파일 저장. ex) 사진
3. Shared preferences
   키-값 쌍인 비공개 기본 데이터를 저장
4. Databases
   구조적인 데이터 저장

## 3. Shared Preference 사용

### 3.1 키-값 데이터 저장

- SharedPreferences API 사용
- 이 객체는 키-값 쌍을 포함한 파일을 가르키고, 파일에서 읽고 쓰는 단순한 메소드를 제공
- 프레임워크에 의해 관리되며 개별적 또는 공유할 수 있다

#### Shared preferences 다루는 방법

- getSharedPreferences() : 이름으로 정의된 다중의 정보를 사용할 때
- getPreferences() : 하나의 값을 사용 할 때

- shared preferences file에 스트링 리소스로 접근

### 3.2 shared preferences에 `Write`

- edit()을 호출함으로써 SharedPrefernce.Editor 생성
- 키와 원하는 타입의 값 전달 ex) putInt(), putString()
- apply() or commit() : 저장
  apply() - SharedPreferences 객체 메모리에서 즉시 변경되지만, 비동기적으로 디스크에 업데이트
  commit() - 즉시 변경되고, 동기적으로 디스크에 데이터 반영


### 3.3 shared preferences `Read`

- 값을 불러오기 위해서는 getInt(),getString()와 같은 메소드를 호출
- 원하는 데이터의 키를 제공
- 키가 존재하지 않는다면 기본 값이 제공


출처
https://developer.android.com/guide/topics/ui/dialogs?hl=en
https://developer.android.com/guide/topics/data/data-storage?hl=en
https://developer.android.com/training/data-storage/shared-preferences



