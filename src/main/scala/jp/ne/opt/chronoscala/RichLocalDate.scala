package jp.ne.opt.chronoscala

import java.time.LocalDate

class RichLocalDate(val underlying: LocalDate) extends AnyVal with Ordered[LocalDate] {

  def +(builder: PeriodBuilder): LocalDate = underlying.plus(builder.underlying)

  def -(builder: PeriodBuilder): LocalDate = underlying.minus(builder.underlying)

  def compare(that: LocalDate): Int = underlying.compareTo(that)

}
