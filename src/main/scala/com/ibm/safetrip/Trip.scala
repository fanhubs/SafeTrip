package com.ibm.safetrip

import akka.actor.ActorRef
import org.apache.spark.util.StatCounter
import org.joda.time.DateTime

/**
  * Created by qiang on 16-6-3.
  */
object Trip {

  /** Base marker trait. */
  @SerialVersionUID(1L)
  sealed trait TripModel extends Serializable

  /**
    *
    * @param year Year collected
    * @param month Month collected
    * @param dayofmonth Day collected
    * @param dayofweek
    * @param deptime
    * @param csrdeptime
    * @param arrtime
    * @param uniquecarrier
    * @param flightnum
    * @param tailnum
    * @param actualelapsedeime
    * @param crselapsedtime
    * @param airtime
    * @param arrdelay
    * @param depdelay
    * @param origin
    * @param dest
    * @param distance
    */
  case class RawTripData(
                             year: Int,
                             month: Int,
                             dayofmonth: Int,
                             dayofweek : Int,
                             deptime: Int,
                             csrdeptime: Int,
                             arrtime: Int,
                             uniquecarrier: String,
                             flightnum:String,
                             tailnum: String,
                             actualelapsedtime: Int,
                             crselapsedtime: Int,
                             airtime:Int,
                             arrdelay: Int,
                             depdelay:Int,
                             origin:String,
                             dest: String,
                             distance: Int) extends TripModel

  object RawTripData {

    def apply(array: Array[String]): RawTripData = {

      RawTripData(
        year = doubleCheckNA(array(0)),
        month= doubleCheckNA(array(1)),
        dayofmonth= doubleCheckNA(array(2)),
        dayofweek = doubleCheckNA(array(3)),
        deptime= doubleCheckNA(array(4)),
        csrdeptime= doubleCheckNA(array(5)),
        arrtime= doubleCheckNA(array(6)),
        uniquecarrier = array(8),
        flightnum= array(9),
        tailnum= array(10),
        actualelapsedtime= doubleCheckNA(array(11)),
        crselapsedtime= doubleCheckNA(array(12)),
        airtime= doubleCheckNA(array(13)),
        arrdelay= doubleCheckNA(array(14)),
        depdelay= doubleCheckNA(array(15)),
        origin= array(16),
        dest= array(17),
        distance= doubleCheckNA(array(18))

      )
    }

    /**
      * double check value of input, just in case NA to make exception
      * @param input
      * @return
      */
    def doubleCheckNA(input: String):Int = {
      var value = 0
      if(!input.contains("NA"))
        value = input.toInt

      value

    }
  }

}

object OnTimeEvent {

  import Trip._

  /** Base marker trait. */
  @SerialVersionUID(1L)
  sealed trait AppEvent extends Serializable

  sealed trait LifeCycleEvent extends AppEvent
  case object OutputStreamInitialized extends LifeCycleEvent
  case class NodeInitialized(root: ActorRef) extends LifeCycleEvent
  case object DataFeedStarted extends LifeCycleEvent
  case object Shutdown extends LifeCycleEvent
  case object TaskCompleted extends LifeCycleEvent

  @SerialVersionUID(1L)
  sealed trait OnTimeRequest extends Serializable

}
