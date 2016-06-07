
import SafeTripApp._
import akka.actor.{ActorSystem, PoisonPill, Props}
import com.ibm.safetrip.{KafkaStreaming, SafeTripSettings}
import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import com.ibm.safetrip.SafeTripSettings
import org.apache.spark.Logging

/**
  * Created by qiang on 16-6-7.
  */
object SingleSafeTrip extends App{

  val settings = new SafeTripSettings()
  import settings._

  /** Configures Spark. */
  val conf = new SparkConf()
    .setMaster(SparkMaster)
    .setAppName(AppName)
    .set("spark.cassandra.connection.host", CassandraHosts)
    .set("spark.cassandra.connection.port",CassandraRpcPort.toString)
    //.set("spark.cleaner.ttl", SparkCleanerTtl.toString)
    //.setExecutorEnv("spark.local.ip","127.0.0.1")
    //.setExecutorEnv("spark.driver.ip","127.0.0.1")
  //.set("spark.cleaner.ttl", SparkCleanerTtl.toString)

  val sc = new SparkContext(conf)

  /** Creates the Spark Streaming context. */
  val ssc = new StreamingContext(sc, Seconds(2))
  ssc.checkpoint(SparkCheckpointDir)


  val kfkStreaming = new KafkaStreaming(ssc,settings,"time-raw-data")

  ssc.start()
  ssc.awaitTermination()


}
