package app.basic.demo

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer

import java.util
import java.util.Properties
import java.util.concurrent.{Future, TimeUnit}

object HelloWorldProducer {
  def main(args: Array[String]): Unit = {
    val value: util.HashMap[String, String] = new util.HashMap[String, String]()
    value.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094")
    value.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getCanonicalName)
    value.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getCanonicalName)
    val properties = new Properties()
    properties.putAll(value)
    val producer = new KafkaProducer[String, String](properties)
    for (i <- 0 until 10) {
      val msg = s"hello, world! $i"
      val result: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String]("test", msg))
      val metadata: RecordMetadata = result.get(3, TimeUnit.SECONDS)
      println(metadata.toString)
    }
    producer.close()
  }
}
