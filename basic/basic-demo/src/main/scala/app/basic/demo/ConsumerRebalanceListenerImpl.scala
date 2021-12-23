package app.basic.demo

import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

import java.util

class ConsumerRebalanceListenerImpl[K, V](consumer: KafkaConsumer[K, V]) extends ConsumerRebalanceListener {
  var offset = 0 // should store in db, like zookeeper
override def onPartitionsRevoked(partitions: util.Collection[TopicPartition]): Unit = {
    println("partitions revoked!")
  }

  override def onPartitionsAssigned(partitions: util.Collection[TopicPartition]): Unit = {
    partitions.forEach(p =>
      consumer.seek(p, offset)
    )
    offset += 1
  }
}
