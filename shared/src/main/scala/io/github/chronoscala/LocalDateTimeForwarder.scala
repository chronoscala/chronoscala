package io.github.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, LocalDate, LocalDateTime, LocalTime, Month, ZoneId}

trait LocalDateTimeForwarder {

  def now(): LocalDateTime = LocalDateTime.now(ClockProvider.clock)

  def now(clock: Clock): LocalDateTime = LocalDateTime.now(clock)

  def now(zoneId: ZoneId): LocalDateTime = LocalDateTime.now(ClockProvider.clock.withZone(zoneId))

  def of(year: Int, month: Month, dayOfMonth: Int, hour: Int, minute: Int): LocalDateTime =
    LocalDateTime.of(year, month, dayOfMonth, hour, minute)

  def of(year: Int, month: Month, dayOfMonth: Int, hour: Int, minute: Int, second: Int): LocalDateTime =
    LocalDateTime.of(year, month, dayOfMonth, hour, minute, second)

  def of(
      year: Int,
      month: Month,
      dayOfMonth: Int,
      hour: Int,
      minute: Int,
      second: Int,
      nanoOfSecond: Int
  ): LocalDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond)

  def of(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int): LocalDateTime =
    LocalDateTime.of(year, month, dayOfMonth, hour, minute)

  def of(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int, second: Int): LocalDateTime =
    LocalDateTime.of(year, month, dayOfMonth, hour, minute, second)

  def of(
      year: Int,
      month: Int,
      dayOfMonth: Int,
      hour: Int,
      minute: Int,
      second: Int,
      nanoOfSecond: Int
  ): LocalDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond)

  def of(date: LocalDate, time: LocalTime): LocalDateTime = LocalDateTime.of(date, time)

  def parse(str: String): LocalDateTime = LocalDateTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter): LocalDateTime = LocalDateTime.parse(str, formatter)

}
