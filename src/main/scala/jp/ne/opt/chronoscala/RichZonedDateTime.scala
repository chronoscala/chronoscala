package jp.ne.opt.chronoscala

import java.time.ZonedDateTime
import java.time.temporal.{TemporalAmount, ChronoUnit}

class RichZonedDateTime(val underlying: ZonedDateTime) extends AnyVal with Ordered[ZonedDateTime] {

  def +(millis: Long): ZonedDateTime = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(amount: TemporalAmount): ZonedDateTime = underlying.plus(amount)

  def +(builder: DurationBuilder): ZonedDateTime = underlying.plus(builder.underlying)

  def +(builder: PeriodBuilder): ZonedDateTime = underlying.plus(builder.underlying)

  def -(millis: Long): ZonedDateTime = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(amount: TemporalAmount): ZonedDateTime = underlying.minus(amount)

  def -(builder: DurationBuilder): ZonedDateTime = underlying.minus(builder.underlying)

  def -(builder: PeriodBuilder): ZonedDateTime = underlying.minus(builder.underlying)

  def compare(that: ZonedDateTime): Int = underlying.compareTo(that)

}
