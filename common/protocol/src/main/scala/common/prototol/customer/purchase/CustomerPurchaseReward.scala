package common.prototol.customer.purchase

import common.prototol.customer.reward.Reward

case class CustomerPurchaseReward[T <: Reward](id: String, customerId: String, rewards: List[T])

object CustomerPurchaseReward {
  def apply[T <: Reward](id: String, customerId: String, rewards: List[T]): CustomerPurchaseReward[T] = new CustomerPurchaseReward(id, customerId, rewards)

  def unapply[T <: Reward](customerPurchaseReward: CustomerPurchaseReward[T]): Option[(String, String, List[T])]
  = if (Option(customerPurchaseReward).isEmpty) None else Some((customerPurchaseReward.id, customerPurchaseReward.customerId, customerPurchaseReward.rewards))
}