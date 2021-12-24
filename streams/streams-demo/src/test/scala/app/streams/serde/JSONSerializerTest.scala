package app.streams.serde

import common.prototol.customer.purchase.CustomerPurchaseSummary
import org.junit.jupiter.api.{Assertions, Test}
import play.api.libs.json.Json

@Test
class JSONSerializerTest {

  @Test
  def test(): Unit = {
    val purchaseSummary = CustomerPurchaseSummary("id-0001", "customer-0001", 12.8)
    val str = Json.toJson(purchaseSummary)(Json.writes[CustomerPurchaseSummary]).toString()
    val purchaseSummary1 = Json.fromJson(Json.parse(str))(Json.reads[CustomerPurchaseSummary]).get
    Assertions.assertEquals(purchaseSummary.id, purchaseSummary1.id)
    Assertions.assertEquals(purchaseSummary.customerId, purchaseSummary1.customerId)
    Assertions.assertEquals(purchaseSummary.totalAmount, purchaseSummary1.totalAmount)
    Assertions.assertTrue(purchaseSummary == purchaseSummary1)
  }
}
