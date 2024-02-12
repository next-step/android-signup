package nextstep.signup.domain

import org.junit.Test

class UsernameValidationResultTest {

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_길이가_유효하지_않다() {
        listOf("김", "가", "가나다라마바사").forEach { username ->
            // given & when
            val actual = UsernameValidationResult.match(username)

            // then
            assert(actual == UsernameValidationResult.INVALID_LENGTH)
        }
    }

    @Test
    fun 사용자_이름이_숫자나_기호가_포함되면_유효하지_않다() {
        listOf("컴포즈1", "컴포즈!", "12345").forEach { username ->
            // given & when
            val actual = UsernameValidationResult.match(username)

            // then
            assert(actual == UsernameValidationResult.INVALID_FORMAT)
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자_사이의_한글_과_영어로_구성되어야_한다() {
        listOf("김철수", "abc", "kim").forEach { username ->
            // given & when
            val actual = UsernameValidationResult.match(username)

            // then
            assert(actual == UsernameValidationResult.SUCCESS)
        }
    }
}
