package jp.ne.opt.chronoscala

import java.time.Period

object PeriodBuilder {
  def apply(underlying: Period): PeriodBuilder =
    new PeriodBuilder(underlying)
}

class PeriodBuilder(val underlying: Period) extends AnyVal {

  def +(that: PeriodBuilder): PeriodBuilder =
    PeriodBuilder(underlying.plus(that.underlying))

  def -(that: PeriodBuilder): PeriodBuilder =
    PeriodBuilder(underlying.minus(that.underlying))

  def days: Int = underlying.getDays

  def months: Int = underlying.getMonths

  def years: Int = underlying.getYears

}
