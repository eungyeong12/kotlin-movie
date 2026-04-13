package movie.domain.payment

import movie.domain.amount.PaymentResult
import movie.domain.amount.Point
import movie.domain.amount.Price
import movie.domain.discount.DiscountPolicy
import movie.domain.reservation.Reservations

class PriceCalculator {
    fun calculate(
        reservations: Reservations,
        discountPolicy: DiscountPolicy,
        point: Point,
        paymentMethod: PaymentMethod,
    ): PaymentResult {
        val discountedPrice = reservations.discountedTotalPrice(discountPolicy)
        val usedPoint = point.usableAmount(discountedPrice)
        val priceAfterPoint = discountedPrice.minus(Price(usedPoint.value))
        val finalPrice = paymentMethod.applyDiscount(priceAfterPoint)

        return PaymentResult(
            totalPrice = finalPrice,
            usedPoint = usedPoint,
        )
    }
}
