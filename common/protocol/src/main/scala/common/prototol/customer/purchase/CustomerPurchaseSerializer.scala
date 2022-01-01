package common.prototol.customer.purchase

import play.api.libs.json.{Json, OWrites, Reads}

object CustomerPurchaseSerializer {
  implicit final val itemScaleReads: Reads[ItemScale] = Json.reads[ItemScale]
  implicit final val customerPurchaseItemReads: Reads[CustomerPurchaseItem] = Json.reads[CustomerPurchaseItem]
  implicit final val customerPurchaseLineReads: Reads[CustomerPurchaseLine] = Json.reads[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryReads: Reads[CustomerPurchaseSummary] = Json.reads[CustomerPurchaseSummary]
  implicit final val customerPurchaseRewardReads: Reads[CustomerPurchaseReward] = Json.reads[CustomerPurchaseReward]

  implicit final val itemScaleWrites: OWrites[ItemScale] = Json.writes[ItemScale]
  implicit final val customerPurchaseItemWrites: OWrites[CustomerPurchaseItem] = Json.writes[CustomerPurchaseItem]
  implicit final val customerPurchaseLineWrites: OWrites[CustomerPurchaseLine] = Json.writes[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryWrites: OWrites[CustomerPurchaseSummary] = Json.writes[CustomerPurchaseSummary]
  implicit final val customerPurchaseRewardWrites: OWrites[CustomerPurchaseReward] = Json.writes[CustomerPurchaseReward]
}
