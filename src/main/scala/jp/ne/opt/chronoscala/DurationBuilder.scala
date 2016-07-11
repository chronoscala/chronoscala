package jp.ne.opt.chronoscala

import java.time.Duration

object DurationBuilder {
  def apply(underlying: Duration): DurationBuilder =
    new DurationBuilder(underlying)
}

class DurationBuilder(val underlying: Duration) extends AnyVal {

  def +(that: DurationBuilder): DurationBuilder =
    DurationBuilder(underlying.plus(that.underlying))

  def -(that: DurationBuilder): DurationBuilder =
    DurationBuilder(underlying.minus(that.underlying))

  def millis: Long = underlying.toMillis

  def seconds: Long = underlying.getSeconds

}
