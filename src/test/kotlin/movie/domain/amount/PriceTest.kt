package movie.domain.amount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PriceTest {
    @Test
    fun `금액끼리 더할 수 있다`() {
        // given
        val price = Price(10000)

        // when
        val result = price.plus(Price(20000))

        // then
        assertThat(result).isEqualTo(Price(30000))
    }

    @Test
    fun `금액끼리 뺄 수 있다`() {
        // given
        val price = Price(20000)

        // when
        val result = price.minus(Price(10000))

        // then
        assertThat(result).isEqualTo(Price(10000))
    }

    @Test
    fun `비율 계산을 할 수 있다`() {
        // given
        val price = Price(10000)

        // when
        val result = price.percentOf(10)

        // then
        assertThat(result).isEqualTo(Price(1000))
    }

    @Test
    fun `금액이 0 미만이 되면 0원으로 보장된다`() {
        // given
        val price = Price(20000)

        // when
        val result = price.minus(Price(30000))

        // then
        assertThat(result).isEqualTo(Price(0))
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 101])
    fun `퍼센트가 범위를 벗어나면 예외가 발생한다`(percent: Int) {
        val price = Price(10000)
        val exception =
            assertThrows<IllegalArgumentException> {
                price.percentOf(percent)
            }
        assertThat(exception.message).isEqualTo("퍼센트는 0~100 사이여야 합니다.")
    }

    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "100, 10000",
    )
    fun `퍼센트가 경계값이면 정상적으로 계산된다`(
        percent: Int,
        expected: Int,
    ) {
        val price = Price(10000)

        val result = price.percentOf(percent)

        assertThat(result).isEqualTo(Price(expected))
    }
}
