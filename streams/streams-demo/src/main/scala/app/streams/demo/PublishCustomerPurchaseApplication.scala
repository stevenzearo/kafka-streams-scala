package app.streams.demo

import common.prototol.customer.purchase._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer
import play.api.libs.json.{Json, OWrites}

import java.util
import java.util.Properties
import java.util.concurrent.{Future, TimeUnit}


object PublishCustomerPurchaseApplication {
  implicit final val itemScaleWrites: OWrites[ItemScale] = Json.writes[ItemScale]
  implicit final val customerPurchaseItemWrites: OWrites[CustomerPurchaseItem] = Json.writes[CustomerPurchaseItem]
  implicit final val customerPurchaseLineWrites: OWrites[CustomerPurchaseLine] = Json.writes[CustomerPurchaseLine]
  implicit final val customerPurchaseSummaryWrites: OWrites[CustomerPurchaseSummary] = Json.writes[CustomerPurchaseSummary]

  def main(args: Array[String]): Unit = {
    val value: util.HashMap[String, String] = new util.HashMap[String, String]()
    value.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094")
    value.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getCanonicalName)
    value.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getCanonicalName)
    val properties = new Properties()
    properties.putAll(value)

    val purchaseLines: List[CustomerPurchaseLine] = initPurchaseLine()
    val purchaseLineStr = purchaseLines.map(line => Json.toJson(line)(Json.writes[CustomerPurchaseLine]).toString())
    val producer = new KafkaProducer[String, String](properties)
    try {
      for (purchaseLine <- purchaseLineStr) {
        val result: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](KafkaTopics.CUSTOMER_PURCHASE_LINE, purchaseLine))
        val metadata: RecordMetadata = result.get(3, TimeUnit.SECONDS)
        println(metadata.toString)
      }
    } finally {
      producer.close()
    }
  }

  private def initPurchaseLine(): List[CustomerPurchaseLine] = {
    val item1: CustomerPurchaseItem = CustomerPurchaseItem("id-0001", "item-0001", "orange", 23.23, 2, ItemScale.KG)
    val item2: CustomerPurchaseItem = CustomerPurchaseItem("id-0002", "item-0002", "apple", 13.99, 1, ItemScale.KG)
    val item3: CustomerPurchaseItem = CustomerPurchaseItem("id-0003", "item-0002", "apple", 13.99, 3, ItemScale.UNIT)
    val item4: CustomerPurchaseItem = CustomerPurchaseItem("id-0004", "item-0003", "milk", 9.98, 1, ItemScale.UNIT)
    val item5: CustomerPurchaseItem = CustomerPurchaseItem("id-0005", "item-0003", "milk", 9.98, 2, ItemScale.UNIT)
    val item6: CustomerPurchaseItem = CustomerPurchaseItem("id-0006", "item-0003", "milk", 9.98, 3, ItemScale.UNIT)
    val purchaseLine1: CustomerPurchaseLine = CustomerPurchaseLine("id-1001", "customer-0001", List(item1, item2))
    val purchaseLine2: CustomerPurchaseLine = CustomerPurchaseLine("id-1002", "customer-0002", List(item3))
    val purchaseLine3: CustomerPurchaseLine = CustomerPurchaseLine("id-1003", "customer-0003", List(item4, item5, item6))
    List(purchaseLine1, purchaseLine2, purchaseLine3)
  }
}
