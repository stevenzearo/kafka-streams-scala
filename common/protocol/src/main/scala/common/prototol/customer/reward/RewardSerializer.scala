package common.prototol.customer.reward

import play.api.libs.json.{Json, OWrites, Reads}

object RewardSerializer {
  implicit final val rewardTypeReads: Reads[RewardType] = Json.reads[RewardType]
  implicit final val rewardReads: Reads[Reward] = Json.reads[Reward]
  implicit final val accelerateRewardReads: Reads[AccelerateReward] = Json.reads[AccelerateReward]
  implicit final val discountRewardReads: Reads[DiscountReward] = Json.reads[DiscountReward]

  implicit final val rewardTypeWrites: OWrites[RewardType] = Json.writes[RewardType]
  implicit final val rewardWrites: OWrites[Reward] = Json.writes[Reward]
  implicit final val accelerateRewardWrites: OWrites[AccelerateReward] = Json.writes[AccelerateReward]
  implicit final val discountRewardWrites: OWrites[DiscountReward] = Json.writes[DiscountReward]
}
