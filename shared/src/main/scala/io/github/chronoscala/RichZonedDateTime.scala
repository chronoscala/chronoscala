package io.github.chronoscala

import java.time.{Duration, Period, ZonedDateTime}
import java.time.format.DateTimeFormatter
import java.time.temporal.{ChronoUnit, TemporalAmount}

class RichZonedDateTime(val underlying: ZonedDateTime) extends AnyVal with Ordered[ZonedDateTime] {

  def +(millis: Long): ZonedDateTime = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(amount: TemporalAmount): ZonedDateTime = underlying.plus(amount)

  def +(duration: Duration): ZonedDateTime = underlying.plus(duration)

  def +(period: Period): ZonedDateTime = underlying.plus(period)

  def -(millis: Long): ZonedDateTime = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(amount: TemporalAmount): ZonedDateTime = underlying.minus(amount)

  def -(duration: Duration): ZonedDateTime = underlying.minus(duration)

  def -(period: Period): ZonedDateTime = underlying.minus(period)

  def to(end: ZonedDateTime): Interval = Interval(underlying.toInstant, end.toInstant)

  def toEpochMilli: Long = underlying.toInstant.toEpochMilli

  def compare(that: ZonedDateTime): Int = underlying.compareTo(that)

  def format(pattern: String): String = underlying.format(DateTimeFormatter.ofPattern(pattern))

}
