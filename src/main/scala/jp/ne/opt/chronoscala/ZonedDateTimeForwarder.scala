package jp.ne.opt.chronoscala

import java.time.{LocalDateTime, Clock, LocalDate, ZoneId, ZonedDateTime}
import java.time.format.{DateTimeFormatter, DateTimeParseException}

import scala.util.Try

trait ZonedDateTimeForwarder {

  def now(): ZonedDateTime = ZonedDateTime.now(ClockProvider.clock)

  def now(clock: Clock): ZonedDateTime = ZonedDateTime.now(clock)

  def now(zoneId: ZoneId): ZonedDateTime = ZonedDateTime.now(ClockProvider.clock.withZone(zoneId))

  def parse(str: String): ZonedDateTime = Try {
    ZonedDateTime.parse(str)
  }.recover {
    case e: DateTimeParseException => LocalDateTime.parse(str).atZone(ZoneId.systemDefault)
  }.recover {
    case e: DateTimeParseException => LocalDate.parse(str).atStartOfDay(ZoneId.systemDefault)
  }.get

  def parse(str: String, formatter: DateTimeFormatter): ZonedDateTime = ZonedDateTime.parse(str, formatter)

}
