package common.prototol.customer.reward

case class AccelerateReward(override val rewardId: String, override val rewardName: String, minValue: Double, unitValue: Double, maxUnit: Int) extends Reward(rewardId, rewardName, RewardType.ACCELERATE)

object AccelerateReward {
  def apply(id: String, rewardName: String, minValue: Double, unitValue: Double, maxUnit: Int): AccelerateReward
  = new AccelerateReward(id, rewardName, minValue, unitValue, maxUnit)

  def unapply(accelerateReward: AccelerateReward): Option[(String, String, Double, Double, Int)]
  = if (Option(accelerateReward).isEmpty) None else Some((accelerateReward.rewardId, accelerateReward.rewardName, accelerateReward.minValue, accelerateReward.unitValue, accelerateReward.maxUnit))
}