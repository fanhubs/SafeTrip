package com.ibm.safetrip

import com.databricks.spark.csv._
import com.databricks.spark.csv.readers._
import org.apache.hadoop.io.{BytesWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql._
import org.apache.spark.streaming._



/**
  * Created by qiang on 16-6-28.
  */
class AirlineData(sc: SparkConf) extends Serializable {

  //val settings  = new SafeTripSettings()
  //import settings._

  //1. reading data from local data folder (or hdfs)
  val ssc = new StreamingContext(sc, Seconds(5))
  //ssc.checkpoint(SparkCheckpointDir)

    val it = ssc.textFileStream("/usr/data/new").flatMap(_.toString.split(",")).print()

  //2. put every data line (except header) to Kafka Server

  //3. move data file from local data folder to recycle bin ()
  // I guess that to move data file in hdfs will be problem.



  // Start the computation
  ssc.start()
  // Wait for the computation to terminate
  ssc.awaitTermination()
}

object AirlineData{
  def apply(sc: SparkConf): AirlineData = new AirlineData(sc)
}




