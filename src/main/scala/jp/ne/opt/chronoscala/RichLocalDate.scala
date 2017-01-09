package jp.ne.opt.chronoscala

import java.time.{Period, LocalDate}

class RichLocalDate(val underlying: LocalDate) extends AnyVal with Ordered[LocalDate] {

  def +(period: Period): LocalDate = underlying.plus(period)

  def -(period: Period): LocalDate = underlying.minus(period)

  def compare(that: LocalDate): Int = underlying.compareTo(that)

  def max(that: LocalDate): LocalDate = if (this > that) underlying else that

  def min(that: LocalDate): LocalDate = if (this < that) underlying else that

  def to(end: LocalDate): DateInterval = DateInterval(underlying, end, Period.ofDays(1))
}
