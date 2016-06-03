/**
  * Created by qiang on 16-6-2.
  */
package com.ibm.safetrip



/** The KafkaStreamActor creates a streaming pipeline from Kafka to Cassandra via Spark.
  * It creates the Kafka stream which streams the raw data, transforms it, to
  * a column entry for a specific airport
  * and saves the new data to the cassandra table as it arrives.
  *
  * @author Qiang
  * @version 2016 Jun 2nd
  */
class KafkaStreamingActor {

}
