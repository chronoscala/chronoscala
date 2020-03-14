package jp.ne.opt.chronoscala

import java.time.{Duration, LocalTime}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class RichLocalTime(val underlying: LocalTime) extends AnyVal with Ordered[LocalTime] {

  def +(millis: Long): LocalTime = underlying.plus(millis, ChronoUnit.MILLIS)

  def +(duration: Duration): LocalTime = underlying.plus(duration)

  def -(millis: Long): LocalTime = underlying.minus(millis, ChronoUnit.MILLIS)

  def -(duration: Duration): LocalTime = underlying.minus(duration)

  def compare(that: LocalTime): Int = underlying.compareTo(that)

  def format(pattern: String): String = underlying.format(DateTimeFormatter.ofPattern(pattern))

}
