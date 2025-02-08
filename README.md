# 회원가입

## 학습테스트를 통해 회원가입 기능을 구현하여 컴포즈 기초를 학습

[피그마](https://www.figma.com/design/OhrMuSgyoqk6nBty3BBA1u/%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A1%9C-%EB%B0%B0%EC%9A%B0%EB%8A%94-Compose-%EB%AF%B8%EC%85%98-%EB%94%94%EC%9E%90%EC%9D%B8?node-id=69-814&p=f)

## Step 2 : 회원가입 화면 구현

- [x] 제목
- [x] 회원 가입 입력값
    - 사용자명
    - 이메일
    - 비밀번호
    - 비밀번호 확인
- [x] 가입 완료 버튼

## Step 3 : 회원가입 (유효성 검사)

- 입력값에 따른 각 항목 유효성 검사
    - [x] 사용자명
        - length 2 ~ 5
        - 숫자나 기호가 포함될 수 없음
    - [ ] 이메일
        - 정규표현식 사용
    - [ ] 비밀번호
        - length 8 ~16
        - 영문, 숫자 포함
    - [ ] 비밀번호 확인
        - 비밀번호와 일치여부