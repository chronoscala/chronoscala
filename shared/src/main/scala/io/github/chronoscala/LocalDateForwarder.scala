package io.github.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, LocalDate, Month, ZoneId}

trait LocalDateForwarder {

  def now(): LocalDate = LocalDate.now(ClockProvider.clock)

  def now(clock: Clock): LocalDate = LocalDate.now(clock)

  def now(zoneId: ZoneId): LocalDate = LocalDate.now(ClockProvider.clock.withZone(zoneId))

  def of(year: Int, month: Month, dayOfMonth: Int): LocalDate = LocalDate.of(year, month, dayOfMonth)

  def of(year: Int, month: Int, dayOfMonth: Int): LocalDate = LocalDate.of(year, month, dayOfMonth)

  def parse(str: String): LocalDate = LocalDate.parse(str)

  def parse(str: String, formatter: DateTimeFormatter): LocalDate = LocalDate.parse(str, formatter)

}
