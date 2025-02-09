# android-signup

## 구현 기능 목록

### 2단계
- [x] 사용자 입력 및 유효성 검사에 대해서는 고려하지 않기. 다만 추가할 수 있도록 확장성 열어두기?
- [x] 컴포저블 함수가 너무 많은 일을 하지 않도록 분리
- [x] Material3 Button, TextField를 활용
- [x] 반복 사용되는 TextField를 Composable로 정의하기(UI 요구 사항에 맞는 TextField)
- [x] Material Theme 정의하기

### 3단계
- 2단계 피드백 반영하기
  - [x] ScreenRoot 대신 screen으로 바꾸고 내부에서만 사용하는 composable은 private 접근자 붙이기
  - [ ] Action 이름 현재형으로 바꾸기
  - [ ] email, password 등 TextField의 state는 TextFieldState로 변경하기
  - [ ] LazyColumn 이용해서 Scrollable하게 바꾸기 + 키보드가 UI 가리지 않도록 설정하기
  - [ ] 텍스트 typography 정의해서 사용하기
  - [ ] TextField for문 대신 4개로 변경하기
  - [ ] State에 action 관련 코드 모두 없애기
- [ ] 유효성 검사 기능을 담당하는 Class를 만든다. Composable에서는 람다로 class의 함수를 넘겨 받는다.
- [ ] 유효성 검사를 통과하지 못하면 하단에 hint를 추가하고, error 처리한다.
- [ ] UI 테스트를 작성한다.
