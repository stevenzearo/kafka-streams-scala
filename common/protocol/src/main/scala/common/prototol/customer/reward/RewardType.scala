package common.prototol.customer.reward

class RewardType private(val name: String)

object RewardType {

  final case object ACCELERATE extends RewardType("ACCELERATE")

  final case object DISCOUNT extends RewardType("DISCOUNT")

  def apply(name: String): RewardType = {
    if (name == ACCELERATE.name) ACCELERATE
    else if (name == DISCOUNT.name) DISCOUNT
    else throw new Exception("unknown reward type")
  }

  def unapply(rewardType: RewardType): Option[String] = if (Option(rewardType).isEmpty) None else Some(rewardType.name)
}