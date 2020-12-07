package jp.ne.opt.chronoscala

import java.time.Duration

class RichLong(val underlying: Long) extends AnyVal {

  def nanos: Duration = Duration.ofNanos(underlying)

  def millis: Duration = Duration.ofMillis(underlying)

  def seconds: Duration = Duration.ofSeconds(underlying)

  def minutes: Duration = Duration.ofMinutes(underlying)

  def hours: Duration = Duration.ofHours(underlying)

  // singular form
  def nano: Duration = nanos
  def milli: Duration = millis
  def second: Duration = seconds
  def minute: Duration = minutes
  def hour: Duration = hours
}
