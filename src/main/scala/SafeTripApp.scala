/**
  * Created by qiang on 16-6-4.
  */

import akka.actor.{Props, ActorSystem, PoisonPill}
import com.ibm.safetrip.{NodeGuardian,  SafeTripSettings}
import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.streaming.{Seconds,Milliseconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import com.ibm.safetrip.SafeTripSettings


object SafeTripApp extends App{

  val settings = new SafeTripSettings()
  import settings._

  /** Configures Spark. */
  lazy val conf = new SparkConf().setAppName(getClass.getSimpleName)
    .setMaster(SparkMaster)
    .setAppName(AppName)
    .set("spark.cassandra.connection.host", CassandraHosts)
    .set("spark.cassandra.connection.port",CassandraRpcPort.toString)
    //.set("spark.cleaner.ttl", SparkCleanerTtl.toString)
    .setExecutorEnv("spark.local.ip","127.0.0.1")
    .setExecutorEnv("spark.driver.ip","127.0.0.1")
    //.set("spark.cleaner.ttl", SparkCleanerTtl.toString)



  val sc = new SparkContext(conf)

  /** Creates the Spark Streaming context. */
  val ssc = new StreamingContext(sc, Seconds(2))
  ssc.checkpoint(SparkCheckpointDir)
  //val kafkaStreamingActor

  /** Creates the ActorSystem. */
  val system = ActorSystem(AppName, rootConfig)

  /* The root supervisor and traffic controller of the app. All inbound messages go through this actor. */
  val guardian = system.actorOf(Props(new NodeGuardian(ssc, settings)), "node-guardian")

  /** Registers the shutdown sequence. */
  system.registerOnTermination {

    ssc.stop(stopSparkContext = true, stopGracefully = true)
    guardian ! PoisonPill
  }




}
