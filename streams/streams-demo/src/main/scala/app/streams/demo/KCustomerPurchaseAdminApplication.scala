package app.streams.demo

import common.prototol.customer.purchase.KafkaTopics
import org.apache.kafka.clients.admin.{AdminClient, AdminClientConfig, NewTopic}

import java.util
import java.util.Properties

object KCustomerPurchaseAdminApplication {
  def main(args: Array[String]): Unit = {
    val propertiesMap: util.HashMap[String, String] = new util.HashMap[String, String]()
    propertiesMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094")
    val properties = new Properties()
    properties.putAll(propertiesMap)

    val topics = List(KafkaTopics.CUSTOMER_PURCHASE_LINE, KafkaTopics.CUSTOMER_PURCHASE_SUMMARY)

    val admin = AdminClient.create(properties)
    try {
      val newTopics: util.Collection[NewTopic] = new util.ArrayList()
      topics.foreach(s => newTopics.add(new NewTopic(s, 3, Short.box(3))))
      val createTopicsResult = admin.createTopics(newTopics)
      createTopicsResult.values().forEach((k, v) => {
        v.whenComplete((v, e) => {
          if (Option(e).nonEmpty) e.printStackTrace()
          else println(s"Successfully create topic:$k")
        })
      })
    } finally {
      admin.close()
    }
  }
}
