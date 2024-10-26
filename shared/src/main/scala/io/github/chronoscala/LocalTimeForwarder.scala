package io.github.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, LocalTime, ZoneId}

trait LocalTimeForwarder {

  def now(): LocalTime = LocalTime.now(ClockProvider.clock)

  def now(clock: Clock): LocalTime = LocalTime.now(clock)

  def now(zoneId: ZoneId): LocalTime = LocalTime.now(ClockProvider.clock.withZone(zoneId))

  def of(hour: Int, minute: Int): LocalTime = LocalTime.of(hour, minute)

  def of(hour: Int, minute: Int, second: Int): LocalTime = LocalTime.of(hour, minute, second)

  def of(hour: Int, minute: Int, second: Int, nanoOfSecond: Int): LocalTime =
    LocalTime.of(hour, minute, second, nanoOfSecond)

  def parse(str: String): LocalTime = LocalTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter): LocalTime = LocalTime.parse(str, formatter)

}
