# android-signup

기본적으로 지켜야 할 사항들

> - 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
> - Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다
> - 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.

## step2

- [x] Title 추가
- [x] SignupTextField 구현
- [x] SignupButton 구현
- [x] UI 컴포넌트 조합
- [x] 테스트 코드 확인

## step3

- [x] 이름 / 이메일 / 비밀번호 / 비밀번호 확인의 Validation Check가 가능한 클래스 구현
- [x] Validation Check 테스트 코드 구현
- [x] UserName / Email / Password / Password Confirm 에 Validation Check 추가
- [x] 각 컴포넌트에 테스트 코드 구현
 - [x] 성공 케이스
 - [x] 실페 케이스


### 삽질 기록

```kotlin
@Composable
fun ValidatedTextField(
    field: InputFieldModel,
    modifier: Modifier = Modifier,
) {
    var errorRes by remember { mutableStateOf<Int?>(null) }
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = "lee-ji-hoon",
            label = field.label,
            isError = errorRes != null,
            onValueChange = {
                field.onValueChange(it)
                val result = field.validator.validate(it)
                errorRes = if (result.isValid.not()) result.message else null
            },
            singleLine = true,
            visualTransformation = field.visualTransformation
        )
        errorRes?.let {
            Text(
                text = stringResource(id = it),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

class ValidatedTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val username = mutableStateOf("")
    private val validator = mutableStateOf<Validator>(NameValidator())

    @Before
    fun setup() {
        composeTestRule.setContent {
            ValidatedTextField(field = InputFieldModel(
                value = username.value,
                onValueChange = { username.value = it },
                validator = validator.value,
                label = { Text("사용자 이름") }
            ))
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        validator.value = NameValidator()
        username.value = "lee-ji-hoon"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_name_length_error))
            .assertExists()
    }
}
```

> 위 학습 예시대로면 위 결과는 정상적으로 나와야 한다.

<img width="2432" alt="image" src="https://github.com/user-attachments/assets/35fb3d32-47e3-4a01-b79e-489604345663">

하지만 실제로는 테스트 코드가 실패하는데 lee-ji-hoon을 입력했을 때 Validate로 안잡히는 것인지 UI로 테스트 해보면 아래와 같다.

<img width="572" alt="image" src="https://github.com/user-attachments/assets/7474985f-8892-452f-8853-42573afa96db">

입력 결과 정상적으로 Error로 나오는 모습이라서 테스트 코드에 `assertExists()` 디버그 포인트를 찍고 확인해보니 무엇이 문제인지 확인이 됐다.

<img width="537" alt="image" src="https://github.com/user-attachments/assets/a6e11422-de3d-42ad-ac46-f31c02df3bf1">

`.value` 로 값을 넣는 상황인데 내가 짠 `TextField` 는 초기값은 아예 고려가 되지 않은 상태여서 에러가 나는 것이였다.

그래서 테스트 코드를 `performTextInput` 로 변경하는 방법도 있지만 초기값을 체크하는게 맞다고 생각을 해서 `ValidateTextField` 자체를 수정하는 것으로 변경
