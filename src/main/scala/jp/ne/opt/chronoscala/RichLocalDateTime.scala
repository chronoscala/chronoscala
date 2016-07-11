package jp.ne.opt.chronoscala

import java.time.LocalDateTime
import java.time.temporal.{ChronoUnit, TemporalAmount}

class RichLocalDateTime(val underlying: LocalDateTime) extends AnyVal with Ordered[LocalDateTime] {

  def +(millis: Long): LocalDateTime = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(amount: TemporalAmount): LocalDateTime = underlying.plus(amount)

  def +(builder: DurationBuilder): LocalDateTime = underlying.plus(builder.underlying)

  def +(builder: PeriodBuilder): LocalDateTime = underlying.plus(builder.underlying)

  def -(millis: Long): LocalDateTime = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(amount: TemporalAmount): LocalDateTime = underlying.minus(amount)

  def -(builder: DurationBuilder): LocalDateTime = underlying.minus(builder.underlying)

  def -(builder: PeriodBuilder): LocalDateTime = underlying.minus(builder.underlying)

  def compare(that: LocalDateTime): Int = underlying.compareTo(that)

}
