package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, LocalTime}

trait LocalTimeForwarder {

  def now() = LocalTime.now(ClockProvider.clock)

  def now(clock: Clock) = LocalTime.now(clock)

  def now(zoneId: ZoneId) = LocalTime.now(ClockProvider.clock.withZone(zoneId))

  def parse(str: String) = LocalTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter) = LocalTime.parse(str, formatter)

}
