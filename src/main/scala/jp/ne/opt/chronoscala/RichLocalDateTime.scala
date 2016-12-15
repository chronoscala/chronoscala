package jp.ne.opt.chronoscala

import java.time.{Period, Duration, LocalDateTime}
import java.time.temporal.{ChronoUnit, TemporalAmount}

class RichLocalDateTime(val underlying: LocalDateTime) extends AnyVal with Ordered[LocalDateTime] {

  def +(millis: Long): LocalDateTime = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(amount: TemporalAmount): LocalDateTime = underlying.plus(amount)

  def +(duration: Duration): LocalDateTime = underlying.plus(duration)

  def +(period: Period): LocalDateTime = underlying.plus(period)

  def -(millis: Long): LocalDateTime = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(amount: TemporalAmount): LocalDateTime = underlying.minus(amount)

  def -(duration: Duration): LocalDateTime = underlying.minus(duration)

  def -(period: Period): LocalDateTime = underlying.minus(period)

  def compare(that: LocalDateTime): Int = underlying.compareTo(that)

  def max(that: LocalDateTime): LocalDateTime = if (this > that) underlying else that

  def min(that: LocalDateTime): LocalDateTime = if (this < that) underlying else that
}
