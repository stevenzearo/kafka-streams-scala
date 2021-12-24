package common.prototol.customer.purchase


case class CustomerPurchaseSummary(id: String, customerId: String, totalAmount: Double)

object CustomerPurchaseSummary {
  def apply(id: String, customerId: String, totalAmount: Double): CustomerPurchaseSummary
  = new CustomerPurchaseSummary(id, customerId, totalAmount)

  def unapply(purchaseSummary: CustomerPurchaseSummary): Option[(String, String, Double)]
  = if (Option(purchaseSummary).isEmpty) None else Some(purchaseSummary.id, purchaseSummary.customerId, purchaseSummary.totalAmount)
}