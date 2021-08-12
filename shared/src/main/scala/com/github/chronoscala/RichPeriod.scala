package com.github.chronoscala

import java.time.Period

class RichPeriod(val underlying: Period) extends AnyVal {

  def days: Int = underlying.getDays

  def months: Int = underlying.getMonths

  def years: Int = underlying.getYears

  def +(period: Period): Period = underlying.plus(period)

  def -(period: Period): Period = underlying.minus(period)

  def unary_- : Period = underlying.negated

}
