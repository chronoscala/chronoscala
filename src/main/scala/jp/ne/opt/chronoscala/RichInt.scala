package jp.ne.opt.chronoscala

import java.time.{ Period, Duration }

class RichInt(val underlying: Int) extends AnyVal {

  def nanos = Duration.ofNanos(underlying)

  def millis = Duration.ofMillis(underlying)

  def seconds = Duration.ofSeconds(underlying)

  def minutes = Duration.ofMinutes(underlying)

  def hours = Duration.ofHours(underlying)

  def days = Period.ofDays(underlying)

  def weeks = Period.ofWeeks(underlying)

  def months = Period.ofMonths(underlying)

  def years = Period.ofYears(underlying)

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
