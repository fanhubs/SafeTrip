
package com.ibm.safetrip

import akka.actor.{ActorSystem, PoisonPill, Props}
import com.ibm.safetrip.{KafkaStreaming, SafeTripSettings}
import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import com.ibm.safetrip.SafeTripSettings

/**
  * Created by qiang on 16-6-7.
  */
object SingleSafeTrip extends App{

  println("You can consider this this my approval")


  val settings = new SafeTripSettings()
  import settings._

  /** Configures Spark. */
//  val conf = new SparkConf()
//    .setMaster(SparkMaster)
//    .setAppName(AppName)
//    .set("spark.cassandra.connection.host", CassandraHosts)
//    .set("spark.cassandra.connection.port",CassandraNativePort.toString)
//
//  val sc = new SparkContext(conf)
//
//  /** Creates the Spark Streaming context. */
//  val ssc = new StreamingContext(sc, Seconds(2))
//  //ssc.checkpoint(SparkCheckpointDir)
//
//
//  val kfkStreaming = new KafkaStreaming(ssc,settings,"time-raw-data")
//
//  ssc.start()
//  ssc.awaitTermination()


}
