package movie.domain.amount

@JvmInline
value class Point(
    val value: Int,
) {
    init {
        require(value >= 0) { "포인트는 0원 이상이어야 합니다." }
    }

    fun use(amount: Int): Point {
        require(amount <= value) { "보유 포인트를 초과할 수 없습니다." }
        return Point(amount)
    }

    fun usableAmount(price: Price): Point {
        if (value >= price.value) {
            return Point(price.value)
        }
        return Point(value)
    }
}
