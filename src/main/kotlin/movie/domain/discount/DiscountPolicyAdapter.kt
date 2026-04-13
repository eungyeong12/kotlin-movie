package movie.domain.discount

import movie.domain.amount.Price
import java.time.LocalDateTime

class DiscountPolicyAdapter(
    private val percentagePolicies: List<PercentageDiscountPolicy>,
    private val fixedPolicies: List<FixedAmountDiscountPolicy>,
) : DiscountPolicy {
    override fun applyDiscount(
        price: Price,
        localDateTime: LocalDateTime,
    ): Price {
        var result = price
        percentagePolicies.forEach {
            result = it.applyDiscount(result, localDateTime)
        }
        fixedPolicies.forEach {
            result = it.applyDiscount(result, localDateTime)
        }
        return result
    }
}
