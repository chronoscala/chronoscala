package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, LocalTime, ZoneId}

trait LocalTimeForwarder {

  def now(): LocalTime = LocalTime.now(ClockProvider.clock)

  def now(clock: Clock): LocalTime = LocalTime.now(clock)

  def now(zoneId: ZoneId): LocalTime = LocalTime.now(ClockProvider.clock.withZone(zoneId))

  def parse(str: String): LocalTime = LocalTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter): LocalTime = LocalTime.parse(str, formatter)

}
