package common.prototol.customer.reward

case class AccelerateReward(override val rewardId: String, minValue: Double, unitValue: Double, maxUnit: Int) extends Reward(rewardId, RewardType.ACCELERATE)

object AccelerateReward {
  def apply(id: String, minValue: Double, unitValue: Double, maxUnit: Int): AccelerateReward
  = new AccelerateReward(id, minValue, unitValue, maxUnit)

  def unapply(accelerateReward: AccelerateReward): Option[(String, Double, Double, Int)]
  = if (Option(accelerateReward).isEmpty) None else Some((accelerateReward.rewardId, accelerateReward.minValue, accelerateReward.unitValue, accelerateReward.maxUnit))
}