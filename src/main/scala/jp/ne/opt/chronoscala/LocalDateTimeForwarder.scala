package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, LocalDateTime}

trait LocalDateTimeForwarder {

  def now() = LocalDateTime.now()

  def now(clock: Clock) = LocalDateTime.now(clock)

  def now(zoneId: ZoneId) = LocalDateTime.now(zoneId)

  def parse(str: String) = LocalDateTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter) = LocalDateTime.parse(str, formatter)

}
