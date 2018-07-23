package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, LocalDate}

trait LocalDateForwarder {

  def now(): LocalDate = LocalDate.now(ClockProvider.clock)

  def now(clock: Clock): LocalDate = LocalDate.now(clock)

  def now(zoneId: ZoneId): LocalDate = LocalDate.now(ClockProvider.clock.withZone(zoneId))

  def parse(str: String): LocalDate = LocalDate.parse(str)

  def parse(str: String, formatter: DateTimeFormatter): LocalDate = LocalDate.parse(str, formatter)

}
