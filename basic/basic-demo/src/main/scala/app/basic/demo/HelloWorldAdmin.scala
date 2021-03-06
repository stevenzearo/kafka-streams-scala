package app.basic.demo

import org.apache.kafka.clients.admin.{AdminClient, AdminClientConfig, NewTopic}

import java.util
import java.util.{Collections, Properties}

object HelloWorldAdmin {
  def main(args: Array[String]): Unit = {
    val propertiesMap: util.HashMap[String, String] = new util.HashMap[String, String]()
    propertiesMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094")
    val properties = new Properties()
    properties.putAll(propertiesMap)

    val testTopic = new NewTopic("a-good-test", 3, Short.box(3))

    val admin = AdminClient.create(properties)
    try {
      val createTopicsResult = admin.createTopics(Collections.singletonList(testTopic))
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
