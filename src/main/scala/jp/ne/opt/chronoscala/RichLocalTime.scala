package jp.ne.opt.chronoscala

import java.time.LocalTime
import java.time.temporal.ChronoUnit

class RichLocalTime(val underlying: LocalTime) extends AnyVal with Ordered[LocalTime] {

  def +(millis: Long): LocalTime = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(builder: DurationBuilder): LocalTime = underlying.plus(builder.underlying)

  def -(millis: Long): LocalTime = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(builder: DurationBuilder): LocalTime = underlying.minus(builder.underlying)

  def compare(that: LocalTime): Int = underlying.compareTo(that)

}
