package jp.ne.opt.chronoscala

import java.time.{Period, Duration}

class RichInt(val underlying: Int) extends AnyVal {

  def nanos = DurationBuilder(Duration.ofNanos(underlying))

  def millis = DurationBuilder(Duration.ofMillis(underlying))

  def seconds = DurationBuilder(Duration.ofSeconds(underlying))

  def minutes = DurationBuilder(Duration.ofMinutes(underlying))

  def hours = DurationBuilder(Duration.ofHours(underlying))

  def days = PeriodBuilder(Period.ofDays(underlying))

  def weeks = PeriodBuilder(Period.ofWeeks(underlying))

  def months = PeriodBuilder(Period.ofMonths(underlying))

  def years = PeriodBuilder(Period.ofYears(underlying))

  // singular form
  def nano = nanos
  def milli = millis
  def second = seconds
  def minute = minutes
  def hour = hours

  def day = days
  def week = weeks
  def month = months
  def year = years
}
