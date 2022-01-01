package app.streams.serde

import common.prototol.customer.purchase._
import common.prototol.customer.serialize.Serializer._
import org.junit.jupiter.api.{Assertions, Test}
import play.api.libs.json.Json

@Test
class CustomerPurchaseSerializerTest {
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

  @Test
  def testCustomerRewards(): Unit = {
    val customerPurchaseReward = CustomerPurchaseReward("id-0001", "customer-0001", "purchase-0001", "reward-0001", "New Year Celebration", 23.12)
    val str = Json.toJson(customerPurchaseReward)(customerPurchaseRewardWrites).toString()
    val customerPurchaseReward1 = Json.fromJson(Json.parse(str))(customerPurchaseRewardReads).get
    Assertions.assertEquals(customerPurchaseReward.id, customerPurchaseReward1.id)
    Assertions.assertEquals(customerPurchaseReward.customerId, customerPurchaseReward1.customerId)
    Assertions.assertEquals(customerPurchaseReward.purchaseId, customerPurchaseReward1.purchaseId)
    Assertions.assertEquals(customerPurchaseReward.rewardId, customerPurchaseReward1.rewardId)
    Assertions.assertEquals(customerPurchaseReward.rewardName, customerPurchaseReward1.rewardName)
    Assertions.assertEquals(customerPurchaseReward.rewardAmount, customerPurchaseReward1.rewardAmount)
  }
}
