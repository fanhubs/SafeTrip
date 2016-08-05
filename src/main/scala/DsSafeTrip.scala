import org.apache.spark._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{SQLContext,Dataset}

/**
  * Created by qiang on 16-8-4.
  */
object DsSafeTrip extends App{

  //ini spark session
  val spark = SparkSession.
    builder()
    .appName("safetrip")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  //For implicit conversions like converting RDDs to DataFrames
  import spark.implicits._

  val df  = spark.readStream.csv("/usr/data/production/Fire_Department_Calls_for_Service.csv")
  df.show();





}
