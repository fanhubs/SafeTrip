package com.ibm.safetrip

import com.databricks.spark.csv.util.ParseModes
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql._
/**
  * Created by qiang on 16-6-28.
  */
class AirlineData(sc:SparkContext) {

  val settings  = new SafeTripSettings()
  import settings._

  //1. reading data from local data folder (or hdfs)
  val sqlContext = new SQLContext(sc)


  //2. put every data line (except header) to Kafka Server

  //3. move data file from local data folder to recycle bin ()
  // I guess that to move data file in hdfs will be problem.

  def ReadAirlineData(){
  }

}

object AirlineData{
  def apply(sc: SparkContext): AirlineData = new AirlineData(sc)
}




