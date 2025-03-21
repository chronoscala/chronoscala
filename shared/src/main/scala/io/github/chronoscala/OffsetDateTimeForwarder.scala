package io.github.chronoscala

import java.time.format.{DateTimeFormatter, DateTimeParseException}
import java.time._
import scala.util.Try

trait OffsetDateTimeForwarder {

  def now(): OffsetDateTime = OffsetDateTime.now(ClockProvider.clock)

  def now(clock: Clock): OffsetDateTime = OffsetDateTime.now(clock)

  def now(zoneId: ZoneId): OffsetDateTime = OffsetDateTime.now(ClockProvider.clock.withZone(zoneId))

  def of(date: LocalDate, time: LocalTime, offset: ZoneOffset): OffsetDateTime = OffsetDateTime.of(date, time, offset)

  def of(dateTime: LocalDateTime, offset: ZoneOffset): OffsetDateTime = OffsetDateTime.of(dateTime, offset)

  def of(
      year: Int,
      month: Int,
      dayOfMonth: Int,
      hour: Int,
      minute: Int,
      second: Int,
      nanoOfSecond: Int,
      offset: ZoneOffset
  ): OffsetDateTime = OffsetDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond, offset)

  def parse(str: String): OffsetDateTime = Try {
    OffsetDateTime.parse(str)
  }.recover { case e: DateTimeParseException =>
    LocalDateTime.parse(str).atZone(ZoneId.systemDefault).toOffsetDateTime
  }.recover { case e: DateTimeParseException =>
    LocalDate.parse(str).atStartOfDay(ZoneId.systemDefault).toOffsetDateTime
  }.get

  def parse(str: String, formatter: DateTimeFormatter): OffsetDateTime =
    OffsetDateTime.parse(str, formatter)

}
