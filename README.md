# android-signup

## Step3
- 디자인 시안을 참고하여 회원가입 뷰에 유효성 검사 로직을 추가한다.
  - 유효성 검사 로직에 대한 테스트 코드를 추가한다.
    - 이름은 2~5자여야 합니다.
    - 이름에는 숫자나 기호가 포함될 수 없습니다.
    - 이메일 형식이 올바르지 않습니다.
    - 비밀번호는 8~16자여야 합니다.
    - 비밀번호는 영문과 숫자를 포함해야 합니다.
    - 비밀번호가 일치하지 않습니다.
  - Design : https://www.figma.com/file/OhrMuSgyoqk6nBty3BBA1u/%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A1%9C-%EB%B0%B0%EC%9A%B0%EB%8A%94-Compose-%EB%AF%B8%EC%85%98-%EB%94%94%EC%9E%90%EC%9D%B8?type=design&node-id=69:814&mode=design&t=yOerW8yTqbT0MTRu-1
  - UI Test 작성한다.

## Step2
- 회원가입 뷰를 구현한다.
  - ViewModel, Hilt 등은 활용하지 않는다.
  - 컴포저블 함수가 너무 많은 일을 하지 않도록 분리한다.
  - Material3 Button, TextField를 활용한다.
 
- 아래 항목을 구현
  - 이름 입력영역
  - Email 입력 영역
  - Password 입력 영역
  - Password 확인 입력영역
  - Sign Up 버튼
