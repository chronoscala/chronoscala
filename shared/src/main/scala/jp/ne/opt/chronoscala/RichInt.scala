package jp.ne.opt.chronoscala

import java.time.{Period, Duration}

class RichInt(val underlying: Int) extends AnyVal {

  def nanos: Duration = Duration.ofNanos(underlying)

  def millis: Duration = Duration.ofMillis(underlying)

  def seconds: Duration = Duration.ofSeconds(underlying)

  def minutes: Duration = Duration.ofMinutes(underlying)

  def hours: Duration = Duration.ofHours(underlying)

  def days: Period = Period.ofDays(underlying)

  def weeks: Period = Period.ofWeeks(underlying)

  def months: Period = Period.ofMonths(underlying)

  def years: Period = Period.ofYears(underlying)

  // singular form
  def nano: Duration = nanos
  def milli: Duration = millis
  def second: Duration = seconds
  def minute: Duration = minutes
  def hour: Duration = hours

  def day: Period = days
  def week: Period = weeks
  def month: Period = months
  def year: Period = years
}
