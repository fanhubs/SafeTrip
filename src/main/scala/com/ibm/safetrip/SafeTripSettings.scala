package com.ibm.safetrip

import java.io.{File => JFile}
import scala.util.Try
import com.datastax.driver.core.ConsistencyLevel
import com.datastax.spark.connector.cql.{NoAuthConf, PasswordAuthConf, AuthConf}
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.Logging

/**
  * Created by qiang on 16-6-4.
  */
final class SafeTripSettings(conf: Option[Config] = None) extends Serializable with Logging  {

  //create root config object
  val rootConfig = conf match {
    case Some(c) => c.withFallback(ConfigFactory.load)
    case _ => ConfigFactory.load
  }

  //spark config items
  protected val spark = rootConfig.getConfig("spark")

  val SparkMaster = withFallback[String](Try(spark.getString("master")),
    "spark.master") getOrElse "local[*]"


  val SparkCleanerTtl = withFallback[Int](Try(spark.getInt("cleaner.ttl")),
    "spark.cleaner.ttl") getOrElse (3600*2)

  val SparkStreamingBatchInterval = withFallback[Int](Try(spark.getInt("streaming.batch.interval")),
    "spark.streaming.batch.interval") getOrElse 1

  //cassandra config items
  protected val cassandra = rootConfig.getConfig("cassandra")

  val CassandraHosts = withFallback[String](Try(cassandra.getString("connection.host")),
    "spark.cassandra.connection.host") getOrElse "127.0.0.1"

  logInfo(s"Starting up with spark master '$SparkMaster' cassandra hosts '$CassandraHosts'")

  val CassandraRpcPort = withFallback[Int](Try(cassandra.getInt("connection.rpc.port")),
    "spark.cassandra.connection.rpc.port") getOrElse 9160

  val CassandraNativePort = withFallback[Int](Try(cassandra.getInt("connection.native.port")),
    "spark.cassandra.connection.native.port") getOrElse 9042


  //kafka configuration items
  protected val kafka = ConfigFactory.load.getConfig("kafka")
  val KafkaEncoderFqcn = kafka.getString("encoder.fqcn")
  val KafkaDecoderFqcn = kafka.getString("decoder.fqcn")
  val KafkaPartitioner = kafka.getString("partitioner.fqcn")
  val KafkaBatchSendSize = kafka.getInt("batch.send.size")
  val KafkaGroupId = kafka.getString("group.id")
  val KafkaTopicRaw = kafka.getString("topic.raw")

  /** Attempts to acquire from environment, then java system properties. */
  private def withFallback[T](env: Try[T], key: String): Option[T] = env match {
    case null  => None
    case value => value.toOption
  }


}
