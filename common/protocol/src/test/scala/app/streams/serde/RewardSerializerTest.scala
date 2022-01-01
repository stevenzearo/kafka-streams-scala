package app.streams.serde

import common.prototol.customer.reward.RewardSerializer._
import common.prototol.customer.reward.{AccelerateReward, DiscountReward}
import org.junit.jupiter.api.{Assertions, Test}
import play.api.libs.json.Json

@Test
class RewardSerializerTest {
  @Test
  def testAccelerateRewards(): Unit = {
    val reward = new AccelerateReward("reward-0001", "Crease Purchase", 59.99, 10, 5)
    val str = Json.toJson(reward)(accelerateRewardWrites).toString()
    val reward1 = Json.fromJson(Json.parse(str))(accelerateRewardReads).get
    Assertions.assertEquals(reward.rewardId, reward1.rewardId)
    Assertions.assertEquals(reward.rewardType, reward1.rewardType)
    Assertions.assertEquals(reward.minValue, reward1.minValue)
    Assertions.assertEquals(reward.rewardId, reward1.rewardId)
    Assertions.assertEquals(reward.unitValue, reward1.unitValue)
    Assertions.assertEquals(reward.maxUnit, reward1.maxUnit)
  }

  @Test
  def testDiscountRewards(): Unit = {
    val reward = new DiscountReward("reward-0002", "Good Sale", 0.98)
    val str = Json.toJson(reward)(discountRewardWrites).toString()
    val reward1 = Json.fromJson(Json.parse(str))(discountRewardReads).get
    Assertions.assertEquals(reward.rewardId, reward1.rewardId)
    Assertions.assertEquals(reward.rewardType, reward1.rewardType)
    Assertions.assertEquals(reward.discountRate, reward1.discountRate)
  }
}
