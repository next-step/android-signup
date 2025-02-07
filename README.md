# android-signup

## 기능 요구 사항

### Step 1 - 컴포즈 기초
- 모든 테스트가 성공하도록 만든다.
- Preview를 노출시킨다.
- Preview의 interactive 모드를 활용하여 버튼을 클릭해본다.

### Step 2 - 회원가입(뷰)
- 디자인 시안을 참고하여 회원가입 뷰를 구현한다.
- Text(title) 컴포넌트를 구현한다.
- TextField 컴포넌트를 구현한다. 
- Button 컴포넌트를 구현한다.

#### Step 2 - 개선 사항
- SignUpScreen Preview 구현
- Spacer 컴포넌트를 이용하여 구현한 여백을 SignUpScreen 각 컴포넌트 내에 modifier를 통해 구현
- TitleComponent 명칭을 해당 컴포넌트가 어떤 것인지 알 수 있도록 변경
- 각 컴포넌트에 하드코딩된 문구를 strings.xml에 정의하여 적용
- 테스트 코드 내에 작성된 컴포넌트를 SignUpScreen에 구현된 컴포넌트로 대체