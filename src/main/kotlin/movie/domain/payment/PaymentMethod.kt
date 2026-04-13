package movie.domain.payment

import movie.domain.amount.Price

sealed interface PaymentMethod {
    fun applyDiscount(price: Price): Price

    class CreditCard : PaymentMethod {
        override fun applyDiscount(price: Price): Price = price.percentOf(95)
    }

    class Cash : PaymentMethod {
        override fun applyDiscount(price: Price): Price = price.percentOf(98)
    }

    companion object {
        fun from(input: Int): PaymentMethod {
            validate(input)

            return when (input) {
                1 -> CreditCard()
                2 -> Cash()
                else -> throw IllegalArgumentException("유효하지 않은 결제 수단입니다.")
            }
        }

        private fun validate(input: Int) {
            require(input == 1 || input == 2) { "유효하지 않은 결제 수단입니다." }
        }
    }
}
