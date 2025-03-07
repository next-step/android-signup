# android-signup

## Goals
[ 과제 진행 요구 사항 ]
* 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
* Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다.
    * [AngularJS Git Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)을 참고해 커밋 메시지를 작성한다.

[ 기능 요구 사항 ]
* [디자인 시안](https://www.figma.com/design/OhrMuSgyoqk6nBty3BBA1u/%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A1%9C-%EB%B0%B0%EC%9A%B0%EB%8A%94-Compose-%EB%AF%B8%EC%85%98-%EB%94%94%EC%9E%90%EC%9D%B8?node-id=231-5278&t=pPNZtCD789GTPS5u-0)을 참고하여 회원가입 뷰를 구현한다.

[ 프로그래밍 요구 사항 ]
* Step2
    * ViewModel, Hilt 등은 회원가입 미션에서 활용하지 않는다.
    * 사용자 입력 및 유효성 검사에 대해서는 이 단계에서 고민하지 않아도 된다.
    * 컴포저블 함수가 너무 많은 일을 하지 않도록 분리하기 위해 노력해 본다.
    * Material3 Button, TextField를 활용한다.

* Step3
    * 유효성 검사 로직에 대한 테스트 코드를 추가한다.

* Step4
    * 유효성 검사 로직과 뷰 로직을 나누어 관심사를 분리한다.
    * 모든 로직에 테스트 코드를 추가한다.
      테스트 가능한 부분과 테스트하기 힘든 부분을 분리해 테스트 가능한 부분에 대해서만 테스트를 진행한다.

-----------------------------------------------------------------
## 기능 목록
- [x] Username/Email/Password/Password Confirm input view
- [x] signup button view
- [x] UI 테스트
- [x] 유효성 검사 로직
- [x] 모든 필드가 에러 없이 채워진 경우에만 Sign up 버튼을 활성화
- [x] Sign up 버튼을 클릭하면 회원가입 완료 스낵바가 노출

-----------------------------------------------------------------
## 리뷰 반영 사항
- [x] Username/Email/Password/Password Confirm input view
- [x] Column 블록 안에 Sapcer를 매번 넣는 대신, VerticalArrangement에 Arrangement.spacedBy()를 활용해보기
- [x] Resource 분리하기
- [x] 컴포넌트 분리하기
- [x] padding 관련한 내용을 각각의 파라미터가 아닌 Modifier로 설정하여 전달
- [x] 텍스트 값을 외부에서 받아서 사용할 수 있게 변경해보기 (TextField의 파라미터를 참고)
- [x] 분리된 컴포넌트에 private preview 추가
- [x] 파라미터 순서에 의존하지 않고 named argument를 명시
- [x] 컴포저블 파라미터 컨벤션 반영
- [x] password 인지 나타내는 변수로 Compponent 내부에서 Transformation을 결정하지 않고 사용하는 곳에서 visualTransformation를 파라미터로 전달
- [x] errorMessage 존재 여부(null)로 에러 표시 결정하도록 변경
  -> 컴포즈에서는 값이 있으면 보여주고, 없으면 보여주지 않는 형태로 구성되어있는 경우가 많음