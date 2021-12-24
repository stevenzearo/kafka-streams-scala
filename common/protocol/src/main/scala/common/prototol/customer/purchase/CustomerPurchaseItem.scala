
package common.prototol.customer.purchase

case class CustomerPurchaseItem(id: String, itemId: String, itemName: String, unitPrice: Double, quantity: Double, scale: ItemScale)

object CustomerPurchaseItem {
  def apply(id: String, itemId: String, itemName: String, unitPrice: Double, quantity: Double, scale: ItemScale): CustomerPurchaseItem
  = new CustomerPurchaseItem(id, itemId, itemName, unitPrice, quantity, scale)

  def unapply(purchaseItem: CustomerPurchaseItem): Option[(String, String, String, Double, Double, ItemScale)]
  = if (Option(purchaseItem).isEmpty) None else Some(purchaseItem.id, purchaseItem.itemId, purchaseItem.itemName, purchaseItem.unitPrice, purchaseItem.quantity, purchaseItem.scale)
}
