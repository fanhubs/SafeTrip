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
//import com.datastax.spark.connector._
//import com.datastax.spark.connector.streaming._
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
                            topic: String) extends Logging {

    // removed pram - kafkaParams: Map[String, String]
    import settings._
    import Trip._

    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBrokerList)
    val topics = Set(topic)
    // Create the direct stream with the Kafka parameters and topics
    val kafkaStream = KafkaUtils.createDirectStream[String,
      String,
      StringDecoder,
      StringDecoder](ssc, kafkaParams, topics)


    //kafkaStream.foreachRDD(
    //    rdd=> (rdd.map(_._2.split(",")).map(RawTripData(_))).saveToCassandra(CassandraKeyspace, CassandraTableRaw)

    //      )

      //.map{
       // rdd=> { rdd._2.map{ line => line.toString}.foreach(println) }}
      //case line => line._2.split(",")}.foreachRDD(x=>println(x.toString))

//      foreachRDD{
//      rdd=> {
//        rdd.map(
//          line => line._2.split(",")
//        )
//      }
//
//    }


      //.map(RawTripData(_))

        //.foreach(item=>println(item.toString))

      //map { case (_, line) => line.split(",")}
     // .map(RawTripData(_))


    /** Saves the raw data to Cassandra - raw table. */
    //kafkaStream.saveToCassandra(CassandraKeyspace, CassandraTableRaw)


}
