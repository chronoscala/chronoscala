package jp.ne.opt.chronoscala

import java.time.format.{DateTimeFormatter, DateTimeParseException}
import java.time._
import scala.util.Try

trait OffsetDateTimeForwarder {

  def now(): OffsetDateTime = OffsetDateTime.now(ClockProvider.clock)

  def now(clock: Clock): OffsetDateTime = OffsetDateTime.now(clock)

  def now(zoneId: ZoneId): OffsetDateTime = OffsetDateTime.now(ClockProvider.clock.withZone(zoneId))

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
