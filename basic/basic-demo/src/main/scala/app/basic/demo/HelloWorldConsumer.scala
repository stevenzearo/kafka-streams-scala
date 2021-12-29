package app.basic.demo

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer, OffsetAndMetadata}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util
import java.util.{Collections, Properties}

object HelloWorldConsumer {
  def main(args: Array[String]): Unit = {
    val value: util.HashMap[String, String] = new util.HashMap[String, String]()
    value.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094")
    value.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getCanonicalName)
    value.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getCanonicalName)
    value.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
    value.put(ConsumerConfig.GROUP_ID_CONFIG, "test-1")
    val properties = new Properties()
    properties.putAll(value)

    val consumer = new KafkaConsumer[String, String](properties)
    val reblanceListener = new ConsumerRebalanceListenerImpl[String, String](consumer)
    try {
      consumer.subscribe(Collections.singletonList("test"), reblanceListener)
      while (true) {
        val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(3000))
        records.forEach(r => {
          println(s"fetched record{ topic:${r.topic()}, partition:${r.partition()},key:${r.key()}, value:{${r.value()}, offset:${r.offset()}, timestamp:${r.timestamp()}}")
        })
        consumer.commitAsync((offsets: util.Map[TopicPartition, OffsetAndMetadata], exception: Exception) => {
          if (Option(exception).nonEmpty)
            exception.printStackTrace()
          else
            offsets.forEach((k, v) => println(s"topic:${k.topic()}, offset:${v.offset()}"))
        })
      }
    } finally {
      consumer.close()
    }
  }
}
