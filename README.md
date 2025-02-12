# android-signup
- ---

## 🚀 2단계 - 회원가입(뷰)
- [x] string 리소스 추가
- [x] TextField Component 추가
- [x] Button Component 추가
- [x] Component 배치
- [x] 테스트 코드 작성

## 🚀 3단계 - 회원가입(유효성 검사)
- [x] 2단계 코드리뷰 항목
  - [x] Modifier를 생성자로 전달하도록 변경하여 UI 구성 개선
  - [x] 중복된 Modifier 속성을 fillMaxSize()로 통합
  - [x] 불필요한 Spacer 제거 및 padding 수정
  - [x] Component 의 기본 동작과 중복되는 wrapContentHeight 제거
  - [x] MaterialTheme.typography 사용하여 텍스트 스타일 적용
  - [x] 테스트코드 메소드의 이름을 직관적인 이름으로 재정의
- [x] 유효성 검사 로직 추가
- [x] 비밀번호 일치 여부 검증 로직 추가
- [x] 에러 UI 추가
- [x] 유효성 검사 로직 테스트 코드 작성

## 🚀 4단계 - 회원가입(리팩터링)
- [ ] SignUp 버튼 디자인 추가 (활성화, 비활성화)
- [ ] 모든 필드가 에러 없이 채워진 경우만 버튼 활성화 되도록 처리
- [ ] 스낵바 컴포넌트 추가
- [ ] 활성화 된 SignUp 버튼 클릭시 스낵바 호출
- [ ] SignupValidator 단위 테스트 추가
- [ ] 3단계 코드리뷰 항목
  - [ ] SignupTextField 관심사 분리
  - [ ] SignupValidator 관심사 분리
  - [ ] 컴포넌트 및 Validator 변경에 따른 UI 테스트 코드 수정