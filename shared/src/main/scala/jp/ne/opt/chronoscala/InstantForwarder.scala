package jp.ne.opt.chronoscala

import java.time.{Instant, Clock}

trait InstantForwarder {

  def now(): Instant = Instant.now(ClockProvider.clock)

  def now(clock: Clock): Instant = Instant.now(clock)

  def parse(str: String): Instant = Instant.parse(str)

  def ofEpochMilli(millis: Long): Instant = Instant.ofEpochMilli(millis)

}
