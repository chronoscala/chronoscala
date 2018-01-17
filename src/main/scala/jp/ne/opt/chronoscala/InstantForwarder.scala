package jp.ne.opt.chronoscala

import java.time.{Instant, Clock}

trait InstantForwarder {

  def now() = Instant.now(ClockProvider.clock)

  def now(clock: Clock) = Instant.now(clock)

  def parse(str: String) = Instant.parse(str)

  def ofEpochMilli(millis: Long) = Instant.ofEpochMilli(millis)

}
