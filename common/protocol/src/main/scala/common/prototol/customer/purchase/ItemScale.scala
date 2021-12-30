package common.prototol.customer.purchase

class ItemScale private(val name: String)

object ItemScale {

  final case object KG extends ItemScale("KG")

  final case object UNIT extends ItemScale("UNIT")

  def apply(name: String): ItemScale = {
    if (name == KG.name) KG
    else if (name == UNIT.name) UNIT
    else throw new Exception("unknown item scale")
  }

  def unapply(itemScale: ItemScale): Option[String] = if (Option(itemScale).isEmpty) None else Some(itemScale.name)
}