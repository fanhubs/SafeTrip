/**
  * Created by qiang on 16-6-2.
  */
package com.ibm.safetrip

import akka.actor.{Actor, ActorRef}
import akka.cluster.Cluster
import kafka.producer.ProducerConfig
import kafka.serializer.StringDecoder
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import com.datastax.spark.connector.streaming._
import kafka.utils.Logging
import org.apache.spark.util.logging

  /** The KafkaStreamActor creates a streaming pipeline from Kafka to Cassandra via Spark.
    * It creates the Kafka stream which streams the raw data, transforms it, to
    * a column entry for a specific airport
    * and saves the new data to the cassandra table as it arrives.
    *
    * @author Qiang
    * @version 2016 Jun 2nd
    */

  class KafkaStreaming(ssc: StreamingContext,
                            settings: SafeTripSettings,
                            topic: String) extends Logging{

    // removed pram - kafkaParams: Map[String, String]
    import settings._
    import OnTime._






    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBrokerList)
    val topics = Set(topic)
    // Create the direct stream with the Kafka parameters and topics
    val kafkaStream = KafkaUtils.createDirectStream[String,
      String,
      StringDecoder,
      StringDecoder](ssc, kafkaParams, topics)

    kafkaStream.foreachRDD(rdd => {
      rdd.map (a=> a._2.split(","))

    }

    )




}
