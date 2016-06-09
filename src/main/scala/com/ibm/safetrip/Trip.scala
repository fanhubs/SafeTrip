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
    * @param dayofMonth Day collected
    * @param dayOfWeek
    * @param depTime
    * @param csrDepTime
    * @param arrTime
    * @param uniqueCarrier
    * @param flightNum
    * @param tailNum
    * @param actualElapsedTime
    * @param crsElapsedTime
    * @param airTime
    * @param arrDelay
    * @param depDelay
    * @param origin
    * @param dest
    * @param Distance
    */
  case class RawTripData(
                             year: Int,
                             month: Int,
                             dayofMonth: Int,
                             dayOfWeek : Int,
                             depTime: Int,
                             csrDepTime: Int,
                             arrTime: Int,
                             uniqueCarrier: String,
                             flightNum:Int,
                             tailNum: String,
                             actualElapsedTime: Int,
                             crsElapsedTime: Int,
                             airTime:Int,
                             arrDelay: Int,
                             depDelay:Int,
                             origin:String,
                             dest: String,
                             Distance: Int) extends TripModel

  object RawTripData {

    def apply(array: Array[String]): RawTripData = {

      RawTripData(
        year = array(0).toInt,
        month= array(1).toInt,
        dayofMonth= array(2).toInt,
        dayOfWeek = array(3).toInt,
        depTime= array(4).toInt,
        csrDepTime= array(5).toInt,
        arrTime= array(6).toInt,
        uniqueCarrier = array(7),
        flightNum= array(8).toInt,
        tailNum= array(9),
        actualElapsedTime= array(10).toInt,
        crsElapsedTime= array(11).toInt,
        airTime= array(12).toInt,
        arrDelay= array(13).toInt,
        depDelay= array(14).toInt,
        origin= array(15),
        dest= array(16),
        Distance= array(17).toInt

      )

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
