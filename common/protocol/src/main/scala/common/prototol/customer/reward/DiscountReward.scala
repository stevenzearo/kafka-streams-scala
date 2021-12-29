package common.prototol.customer.reward

case class DiscountReward(override val rewardId: String, discountRate: Double) extends Reward(rewardId, RewardType.DISCOUNT)

object DiscountReward {
  def apply(id: String, discountRate: Double): DiscountReward = new DiscountReward(id, discountRate)

  def unapply(discountReward: DiscountReward): Option[(String, Double)]
  = if (Option(discountReward).isEmpty) None else Some((discountReward.rewardId, discountReward.discountRate))
}