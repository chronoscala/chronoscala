package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, LocalDateTime}

trait LocalDateTimeForwarder {

  def now(): LocalDateTime = LocalDateTime.now(ClockProvider.clock)

  def now(clock: Clock): LocalDateTime = LocalDateTime.now(clock)

  def now(zoneId: ZoneId): LocalDateTime = LocalDateTime.now(ClockProvider.clock.withZone(zoneId))

  def parse(str: String): LocalDateTime = LocalDateTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter): LocalDateTime = LocalDateTime.parse(str, formatter)

}
