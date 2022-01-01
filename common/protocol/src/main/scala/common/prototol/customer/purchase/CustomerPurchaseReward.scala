package common.prototol.customer.purchase

case class CustomerPurchaseReward(id: String, customerId: String, purchaseId: String, rewardId: String, rewardName: String, rewardAmount: Double)

object CustomerPurchaseReward {
  def apply(id: String, customerId: String, purchaseId: String, rewardId: String, rewardName: String, rewardAmount: Double): CustomerPurchaseReward = new CustomerPurchaseReward(id, customerId, purchaseId, rewardId, rewardName, rewardAmount)

  def unapply(customerPurchaseReward: CustomerPurchaseReward): Option[(String, String, String, String, String, Double)]
  = if (Option(customerPurchaseReward).isEmpty) None else Some((customerPurchaseReward.id, customerPurchaseReward.customerId, customerPurchaseReward.purchaseId, customerPurchaseReward.rewardId, customerPurchaseReward.rewardName, customerPurchaseReward.rewardAmount))
}