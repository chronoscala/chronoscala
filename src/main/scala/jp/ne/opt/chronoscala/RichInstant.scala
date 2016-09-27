package jp.ne.opt.chronoscala

import java.time.temporal.{ChronoUnit, TemporalAmount}
import java.time.{Instant, Duration, Period}

class RichInstant(val underlying: Instant) extends AnyVal with Ordered[Instant] {

  def +(millis: Long): Instant = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(amount: TemporalAmount): Instant = underlying.plus(amount)

  def +(duration: Duration): Instant = underlying.plus(duration)

  def +(period: Period): Instant = underlying.plus(period)

  def -(millis: Long): Instant = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(amount: TemporalAmount): Instant = underlying.minus(amount)

  def -(duration: Duration): Instant = underlying.minus(duration)

  def -(period: Period): Instant = underlying.minus(period)

  def to(end: Instant): Interval = Interval(underlying, end)

  def compare(that: Instant): Int = underlying.compareTo(that)

}
