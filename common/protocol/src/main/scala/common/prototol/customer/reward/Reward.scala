package common.prototol.customer.reward

class Reward(val rewardId: String, val rewardName: String, val rewardType: RewardType)

object Reward {
  def apply(rewardId: String, rewardName: String, rewardType: RewardType): Reward = new Reward(rewardId, rewardName, rewardType)

  def unapply(reward: Reward): Option[(String, String, RewardType)] = if (Option(reward).isEmpty) None else Some((reward.rewardId, reward.rewardName, reward.rewardType))
}