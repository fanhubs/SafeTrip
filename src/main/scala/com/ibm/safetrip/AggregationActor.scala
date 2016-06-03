package com.ibm.safetrip
import java.util.concurrent.TimeoutException

import akka.actor.SupervisorStrategy._
import akka.actor._
import akka.util.Timeout
import org.apache.spark.SparkException
import org.joda.time.{DateTime, DateTimeZone}

import scala.concurrent.duration._

/** A base actor for weather data computation. */
trait AggregationActor extends Actor {

  implicit val timeout = Timeout(5.seconds)

  implicit val ctx = context.dispatcher

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: SparkException           => Stop
      case _: ActorInitializationException => Stop
      case _: IllegalArgumentException => Stop
      case _: IllegalStateException    => Restart
      case _: TimeoutException         => Escalate
      case _: Exception                => Escalate
    }

  /** Creates a timestamp for the current date time in UTC. */
  def timestamp: DateTime = new DateTime(DateTimeZone.UTC)

}
