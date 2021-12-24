package app.streams.demo

import common.prototol.customer.purchase._
import org.apache.kafka.streams.scala.kstream.{Consumed, Produced}
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}
import play.api.libs.json.{Json, OWrites, Reads}

import java.util
import java.util.{Properties, UUID}


object KStreamsCustomerPurchaseApplication {
  implicit final val itemScaleReads: Reads[ItemScale] = Json.reads[ItemScale]
  implicit final val customerPurchaseItemReads: Reads[CustomerPurchaseItem] = Json.reads[CustomerPurchaseItem]
  implicit final val customerPurchaseLineReads: Reads[CustomerPurchaseLine] = Json.reads[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryReads: Reads[CustomerPurchaseSummary] = Json.reads[CustomerPurchaseSummary]
  implicit final val itemScaleWrites: OWrites[ItemScale] = Json.writes[ItemScale]
  implicit final val customerPurchaseItemWrites: OWrites[CustomerPurchaseItem] = Json.writes[CustomerPurchaseItem]
  implicit final val customerPurchaseLineWrites: OWrites[CustomerPurchaseLine] = Json.writes[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryWrites: OWrites[CustomerPurchaseSummary] = Json.writes[CustomerPurchaseSummary]

  def main(args: Array[String]): Unit = {
    val map = new util.HashMap[String, String]()
    map.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094")
    map.put(StreamsConfig.APPLICATION_ID_CONFIG, "yelling-application-0001")
    val properties = new Properties()
    properties.putAll(map)

    val streamsBuilder = new StreamsBuilder()
    streamsBuilder.stream(KafkaTopics.CUSTOMER_PURCHASE_LINE, Consumed.`with`(Serdes.stringSerde, Serdes.stringSerde))
      .mapValues(s => transferToSummary(s))
      .to(KafkaTopics.CUSTOMER_PURCHASE_SUMMARY, Produced.`with`(Serdes.stringSerde, Serdes.stringSerde))
    val topology = streamsBuilder.build()

    val kafkaStreams = new KafkaStreams(topology, properties)
    try {
      kafkaStreams.start()
      Thread.sleep(5000)
    } finally {
      kafkaStreams.close()
    }
  }

  private def transferToSummary(s: String): String = {
    val purchaseLine = Json.fromJson(Json.parse(s))(Json.reads[CustomerPurchaseLine]).get
    val purchaseSummary = CustomerPurchaseSummary(UUID.randomUUID().toString, purchaseLine.customerId, purchaseLine.customerPurchaseItems.map(a => a.quantity * a.unitPrice).sum)
    Json.toJson(purchaseSummary)(Json.writes[CustomerPurchaseSummary]).toString()
  }
}
