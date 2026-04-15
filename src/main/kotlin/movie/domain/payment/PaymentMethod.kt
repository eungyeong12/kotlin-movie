package movie.domain.payment

sealed interface PaymentMethod {
    data object CreditCard : PaymentMethod

    data object Cash : PaymentMethod

    companion object {
        fun from(input: Int): PaymentMethod {
            validate(input)

            return when (input) {
                1 -> CreditCard
                2 -> Cash
                else -> throw IllegalArgumentException("유효하지 않은 결제 수단입니다.")
            }
        }

        private fun validate(input: Int) {
            require(input == 1 || input == 2) { "유효하지 않은 결제 수단입니다." }
        }
    }
}
