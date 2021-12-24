package common.prototol.customer.purchase


case class CustomerPurchaseLine(id: String, customerId: String, customerPurchaseItems: List[CustomerPurchaseItem])

object CustomerPurchaseLine {
  def apply(id: String, customerId: String, customerPurchaseItems: List[CustomerPurchaseItem]): CustomerPurchaseLine
  = new CustomerPurchaseLine(id, customerId, customerPurchaseItems)

  def unapply(purchaseLine: CustomerPurchaseLine): Option[(String, String, List[CustomerPurchaseItem])]
  = if (Option(purchaseLine).isEmpty) None else Some(purchaseLine.id, purchaseLine.customerId, purchaseLine.customerPurchaseItems)
}
