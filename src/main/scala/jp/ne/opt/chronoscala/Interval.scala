package jp.ne.opt.chronoscala

import java.time.{ZonedDateTime, Duration, ZoneId, Instant}

/**
 * Represents an immutable time interval.
 */
case class Interval(startInstant: Instant, endInstant: Instant) {
  require(!endInstant.isBefore(startInstant), "The end instant must not be before the start")

  val start: ZonedDateTime = startInstant.atZone(ZoneId.systemDefault())

  val end: ZonedDateTime = endInstant.atZone(ZoneId.systemDefault())

  val millis: Long = endInstant.toEpochMilli - startInstant.toEpochMilli

  val duration: Duration = Duration.ofMillis(millis)

  def contains(millisInstant: Long): Boolean =
    millisInstant >= startInstant.toEpochMilli && millisInstant <= endInstant.toEpochMilli

  def contains(instant: Instant): Boolean =
    contains(instant.toEpochMilli)

  def contains(dateTime: ZonedDateTime): Boolean =
    contains(dateTime.toInstant)

  def contains(other: Interval): Boolean = {
    val otherStart = other.startInstant.toEpochMilli
    val otherEnd = other.endInstant.toEpochMilli
    val thisStart = startInstant.toEpochMilli
    val thisEnd = endInstant.toEpochMilli

    thisStart <= otherStart && otherStart < thisEnd && otherEnd <= thisEnd
  }

}
