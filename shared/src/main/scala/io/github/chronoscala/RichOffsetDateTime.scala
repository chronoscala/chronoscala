package io.github.chronoscala

import java.time.format.DateTimeFormatter
import java.time.temporal.{ChronoUnit, TemporalAmount}
import java.time.{Duration, OffsetDateTime, Period}

class RichOffsetDateTime(val underlying: OffsetDateTime)
  extends AnyVal
  with Ordered[OffsetDateTime] {

  def +(millis: Long): OffsetDateTime = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(amount: TemporalAmount): OffsetDateTime = underlying.plus(amount)

  def +(duration: Duration): OffsetDateTime = underlying.plus(duration)

  def +(period: Period): OffsetDateTime = underlying.plus(period)

  def -(millis: Long): OffsetDateTime = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(amount: TemporalAmount): OffsetDateTime = underlying.minus(amount)

  def -(duration: Duration): OffsetDateTime = underlying.minus(duration)

  def -(period: Period): OffsetDateTime = underlying.minus(period)

  def to(end: OffsetDateTime): Interval = Interval(underlying.toInstant, end.toInstant)

  def toEpochMilli: Long = underlying.toInstant.toEpochMilli

  def compare(that: OffsetDateTime): Int = underlying.compareTo(that)

  def format(pattern: String): String = underlying.format(DateTimeFormatter.ofPattern(pattern))

}
