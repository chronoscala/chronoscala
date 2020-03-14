package jp.ne.opt.chronoscala

import java.time.{Period, LocalDate}
import java.time.format.DateTimeFormatter

class RichLocalDate(val underlying: LocalDate) extends AnyVal with Ordered[LocalDate] {

  def +(period: Period): LocalDate = underlying.plus(period)

  def -(period: Period): LocalDate = underlying.minus(period)

  def compare(that: LocalDate): Int = underlying.compareTo(that)

  def to(end: LocalDate): DateInterval = DateInterval(underlying, end, Period.ofDays(1))

  def format(pattern: String): String = underlying.format(DateTimeFormatter.ofPattern(pattern))

}
