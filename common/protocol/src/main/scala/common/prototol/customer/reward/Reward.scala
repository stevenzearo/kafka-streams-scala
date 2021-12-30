package common.prototol.customer.reward

class Reward(val rewardId: String, val rewardType: RewardType)

object Reward {
  def apply(rewardId: String, rewardType: RewardType): Reward = new Reward(rewardId, rewardType)

  def unapply(reward: Reward): Option[(String, RewardType)] = if (Option(reward).isEmpty) None else Some((reward.rewardId, reward.rewardType))
}