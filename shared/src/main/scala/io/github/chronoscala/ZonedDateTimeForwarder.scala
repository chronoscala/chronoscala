package io.github.chronoscala

import java.time.{Clock, LocalDate, LocalDateTime, LocalTime, ZoneId, ZonedDateTime}
import java.time.format.{DateTimeFormatter, DateTimeParseException}
import scala.util.Try

trait ZonedDateTimeForwarder {

  def now(): ZonedDateTime = ZonedDateTime.now(ClockProvider.clock)

  def now(clock: Clock): ZonedDateTime = ZonedDateTime.now(clock)

  def now(zoneId: ZoneId): ZonedDateTime = ZonedDateTime.now(ClockProvider.clock.withZone(zoneId))

  def of(date: LocalDate, time: LocalTime, zone: ZoneId): ZonedDateTime = ZonedDateTime.of(date, time, zone)

  def of(localDateTime: LocalDateTime, zone: ZoneId): ZonedDateTime = ZonedDateTime.of(localDateTime, zone)

  def of(
      year: Int,
      month: Int,
      dayOfMonth: Int,
      hour: Int,
      minute: Int,
      second: Int,
      nanoOfSecond: Int,
      zone: ZoneId
  ): ZonedDateTime = ZonedDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond, zone)

  def parse(str: String): ZonedDateTime = Try {
    ZonedDateTime.parse(str)
  }.recover { case e: DateTimeParseException =>
    LocalDateTime.parse(str).atZone(ZoneId.systemDefault)
  }.recover { case e: DateTimeParseException =>
    LocalDate.parse(str).atStartOfDay(ZoneId.systemDefault)
  }.get

  def parse(str: String, formatter: DateTimeFormatter): ZonedDateTime =
    ZonedDateTime.parse(str, formatter)

}
