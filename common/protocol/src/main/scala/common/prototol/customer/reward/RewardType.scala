package common.prototol.customer.reward

class RewardType(val name: String)

object RewardType {

  final case object ACCELERATE extends RewardType("ACCELERATE")

  final case object DISCOUNT extends RewardType("DISCOUNT")

  def apply(name: String): RewardType = new RewardType(name.toUpperCase)

  def unapply(rewardType: RewardType): Option[String] = if (Option(rewardType).isEmpty) None else Some(rewardType.name)
}