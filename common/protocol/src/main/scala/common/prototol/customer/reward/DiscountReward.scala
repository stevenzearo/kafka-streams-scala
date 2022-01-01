package common.prototol.customer.reward

case class DiscountReward(override val rewardId: String, override val rewardName: String, discountRate: Double) extends Reward(rewardId, rewardName, RewardType.DISCOUNT)

object DiscountReward {
  def apply(id: String, rewardName: String, discountRate: Double): DiscountReward = new DiscountReward(id, rewardName, discountRate)

  def unapply(discountReward: DiscountReward): Option[(String, String, Double)]
  = if (Option(discountReward).isEmpty) None else Some((discountReward.rewardId, discountReward.rewardName, discountReward.discountRate))
}