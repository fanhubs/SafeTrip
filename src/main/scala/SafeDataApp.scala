package com.ibm.safetrip

import com.ibm.safetrip.SafeTripApp._
import com.ibm.safetrip._
import org.apache.spark.{SparkConf, SparkContext}

/**
  * the travel data formatted to csv, we have to read this file from
  * csv file and update this file to KAFKA since we have some network limited..
  *
  * Created by Qiang on 16-6-28.
  */
object SafeDataApp extends App with Serializable {

  //val settings = new SafeTripSettings()
  //import settings._

  /** Configures Spark. */
  val conf = new SparkConf().setAppName(getClass.getSimpleName)
    .setMaster("local[2]")
    .setAppName("myapp")

  val airlineData = AirlineData(conf)

}
