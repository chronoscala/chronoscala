package io.github.chronoscala

import java.time.temporal.ChronoUnit
import java.time.Duration

class RichDuration(val underlying: Duration) extends AnyVal with Ordered[Duration] {

  def millis: Long = underlying.toMillis

  def seconds: Long = underlying.getSeconds

  def minutes: Long = underlying.toMinutes

  def hours: Long = underlying.toHours

  def +(millis: Long): Duration = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(duration: Duration): Duration = underlying.plus(duration)

  def -(millis: Long): Duration = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(duration: Duration): Duration = underlying.minus(duration)

  def unary_- : Duration = underlying.negated

  override def compare(that: Duration): Int = underlying.compareTo(that)
}
