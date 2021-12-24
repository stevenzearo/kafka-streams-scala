package common.prototol.customer.purchase

class ItemScale(val name: String)

object ItemScale {

  final case object KG extends ItemScale("KG")

  final case object UNIT extends ItemScale("UNIT")

  def apply(name: String): ItemScale = new ItemScale(name.toUpperCase)

  def unapply(itemScale: ItemScale): Option[String] = if (Option(itemScale).isEmpty) None else Some(itemScale.name)
}
