# android-signup

## Step2
회원가입 화면을 구현한다.
- `Scaffold` 함수로 `TopBar`와 `Content`를 구분한다.
- Username, Email, Password, Password Confirm은 `TextField` 함수를 이용하여 구성한다.
  - 이 때 각 상태 관리는 `remember` 함수를 이용한다.
- Sign Up 버튼은 `Button` 함수를 이용한다.
  - [추가] 모든 입력이 완료가 안 되었다면 비활성화 한다

## Step3
- 유효성 검사 로직에 대한 테스트 코드를 추가한다.
- 만일 유효성 검사에 적절하지 않은 양식이면 [에러문구 상세]를 표기한다.
> 이름은 2~5자이어야 한다.
> 이름에는 숫자나 기호가 포함될 수 없다.
> 이메일은 이메일 형식인지 판별해야 한다.
> 비밀번호가 8 ~ 16자 이어야 한다.
> 비밀번호는 영문과 숫자를 포함해야 한다.
> Password와 Password Confirm이 일치해야 한다.
