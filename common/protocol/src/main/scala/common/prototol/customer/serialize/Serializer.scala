package common.prototol.customer.serialize

import common.prototol.customer.purchase._
import common.prototol.customer.reward.{AccelerateReward, DiscountReward, Reward, RewardType}
import play.api.libs.json.{Json, OWrites, Reads}

object Serializer {
  implicit final val itemScaleReads: Reads[ItemScale] = Json.reads[ItemScale]
  implicit final val customerPurchaseItemReads: Reads[CustomerPurchaseItem] = Json.reads[CustomerPurchaseItem]
  implicit final val customerPurchaseLineReads: Reads[CustomerPurchaseLine] = Json.reads[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryReads: Reads[CustomerPurchaseSummary] = Json.reads[CustomerPurchaseSummary]
  implicit final val rewardTypeReads: Reads[RewardType] = Json.reads[RewardType]
  implicit final val rewardReads: Reads[Reward] = Json.reads[Reward]

  implicit final val accelerateRewardReads: Reads[AccelerateReward] = Json.reads[AccelerateReward]
  implicit final val discountRewardReads: Reads[DiscountReward] = Json.reads[DiscountReward]
  implicit final val customerPurchaseRewardReads: Reads[CustomerPurchaseReward] = Json.reads[CustomerPurchaseReward]

  implicit final val itemScaleWrites: OWrites[ItemScale] = Json.writes[ItemScale]
  implicit final val customerPurchaseItemWrites: OWrites[CustomerPurchaseItem] = Json.writes[CustomerPurchaseItem]
  implicit final val customerPurchaseLineWrites: OWrites[CustomerPurchaseLine] = Json.writes[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryWrites: OWrites[CustomerPurchaseSummary] = Json.writes[CustomerPurchaseSummary]
  implicit final val rewardTypeWrites: OWrites[RewardType] = Json.writes[RewardType]
  implicit final val rewardWrites: OWrites[Reward] = Json.writes[Reward]
  implicit final val accelerateRewardWrites: OWrites[AccelerateReward] = Json.writes[AccelerateReward]
  implicit final val discountRewardWrites: OWrites[DiscountReward] = Json.writes[DiscountReward]
  implicit final val customerPurchaseRewardWrites: OWrites[CustomerPurchaseReward] = Json.writes[CustomerPurchaseReward]
}
