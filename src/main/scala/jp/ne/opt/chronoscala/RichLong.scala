package jp.ne.opt.chronoscala

import java.time.Duration

class RichLong(val underlying: Long) extends AnyVal {

  def nanos = Duration.ofNanos(underlying)

  def millis = Duration.ofMillis(underlying)

  def seconds = Duration.ofSeconds(underlying)

  def minutes = Duration.ofMinutes(underlying)

  def hours = Duration.ofHours(underlying)

  // singular form
  def nano = nanos
  def milli = millis
  def second = seconds
  def minute = minutes
  def hour = hours
}
