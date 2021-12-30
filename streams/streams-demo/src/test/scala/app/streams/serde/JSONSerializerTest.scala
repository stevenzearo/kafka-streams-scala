package app.streams.serde

import common.prototol.customer.purchase.{CustomerPurchaseItem, CustomerPurchaseLine, CustomerPurchaseReward, CustomerPurchaseSummary, ItemScale}
import common.prototol.customer.reward.{AccelerateReward, DiscountReward, Reward, RewardType}
import org.junit.jupiter.api.{Assertions, Disabled, Test}
import play.api.libs.json.{Json, OWrites, Reads}

@Test
class JSONSerializerTest {
  implicit final val itemScaleReads: Reads[ItemScale] = Json.reads[ItemScale]
  implicit final val customerPurchaseItemReads: Reads[CustomerPurchaseItem] = Json.reads[CustomerPurchaseItem]
  implicit final val customerPurchaseLineReads: Reads[CustomerPurchaseLine] = Json.reads[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryReads: Reads[CustomerPurchaseSummary] = Json.reads[CustomerPurchaseSummary]
  implicit final val rewardTypeReads: Reads[RewardType] = Json.reads[RewardType]
  implicit final val rewardReads: Reads[Reward] = Json.reads[Reward]
  implicit final val accelerateRewardReads: Reads[AccelerateReward] = Json.reads[AccelerateReward]
  implicit final val discountRewardReads: Reads[DiscountReward] = Json.reads[DiscountReward]
  implicit final val customerPurchaseRewardReads: Reads[CustomerPurchaseReward[Reward]] = Json.reads[CustomerPurchaseReward[Reward]]

  implicit final val itemScaleWrites: OWrites[ItemScale] = Json.writes[ItemScale]
  implicit final val customerPurchaseItemWrites: OWrites[CustomerPurchaseItem] = Json.writes[CustomerPurchaseItem]
  implicit final val customerPurchaseLineWrites: OWrites[CustomerPurchaseLine] = Json.writes[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryWrites: OWrites[CustomerPurchaseSummary] = Json.writes[CustomerPurchaseSummary]
  implicit final val rewardTypeWrites: OWrites[RewardType] = Json.writes[RewardType]
  implicit final val rewardWrites: OWrites[Reward] = Json.writes[Reward]
  implicit final val accelerateRewardWrites: OWrites[AccelerateReward] = Json.writes[AccelerateReward]
  implicit final val discountRewardWrites: OWrites[DiscountReward] = Json.writes[DiscountReward]
  implicit final val customerPurchaseRewardWrites: OWrites[CustomerPurchaseReward[Reward]] = Json.writes[CustomerPurchaseReward[Reward]]

  @Test
  def testPurchaseSummary(): Unit = {
    val purchaseSummary = CustomerPurchaseSummary("id-0001", "customer-0001", 12.8)
    val str = Json.toJson(purchaseSummary)(customerPurchaseSummaryWrites).toString()
    val purchaseSummary1 = Json.fromJson(Json.parse(str))(customerPurchaseSummaryReads).get
    Assertions.assertEquals(purchaseSummary.id, purchaseSummary1.id)
    Assertions.assertEquals(purchaseSummary.customerId, purchaseSummary1.customerId)
    Assertions.assertEquals(purchaseSummary.totalAmount, purchaseSummary1.totalAmount)
    Assertions.assertTrue(purchaseSummary == purchaseSummary1)
  }

  @Test
  def testPurchaseLine(): Unit = {
    val purchaseItem1 = CustomerPurchaseItem("id-0001", "item-0001", "item-name-0001", 23.23, 2, ItemScale.KG)
    val purchaseItem2 = CustomerPurchaseItem("id-0002", "item-0002", "item-name-0002", 12.99, 2, ItemScale.UNIT)
    val purchaseLine = CustomerPurchaseLine("id-0003", "customer-0001", List(purchaseItem1, purchaseItem2))
    val str = Json.toJson(purchaseLine)(customerPurchaseLineWrites).toString()
    val purchaseLine1 = Json.fromJson(Json.parse(str))(customerPurchaseLineReads).get
    Assertions.assertEquals(purchaseLine.id, purchaseLine1.id)
    Assertions.assertEquals(purchaseLine.customerId, purchaseLine1.customerId)
    Assertions.assertEquals(purchaseLine.customerPurchaseItems.size, purchaseLine1.customerPurchaseItems.size)
    purchaseLine.customerPurchaseItems.zipWithIndex.foreach((tuple: (CustomerPurchaseItem, Int)) => {
      Assertions.assertEquals(tuple._1, purchaseLine1.customerPurchaseItems(tuple._2))
    })
  }

  // todo child class will serialized and deserialized as super class
  @Disabled
  @Test
  def testCustomerRewards(): Unit = {
    val reward1 = new AccelerateReward("reward-0001", 59.99, 10, 5)
    val reward2 = new DiscountReward("reward-0002", 0.98)
    val customerPurchaseReward = CustomerPurchaseReward[Reward]("id-0001", "customer-0001", List(reward1, reward2))
    val str = Json.toJson(customerPurchaseReward)(customerPurchaseRewardWrites).toString()
    val customerPurchaseReward1 = Json.fromJson(Json.parse(str))(customerPurchaseRewardReads).get
    Assertions.assertEquals(customerPurchaseReward.id, customerPurchaseReward1.id)
    Assertions.assertEquals(customerPurchaseReward.customerId, customerPurchaseReward1.customerId)
    Assertions.assertEquals(customerPurchaseReward.rewards.size, customerPurchaseReward1.rewards.size)
    customerPurchaseReward.rewards.zipWithIndex.foreach((tuple: (Reward, Int)) => {
      Assertions.assertEquals(tuple._1, customerPurchaseReward1.rewards(tuple._2))
    })
  }
}
