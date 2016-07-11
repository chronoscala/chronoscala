package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, LocalDate}

trait LocalDateForwarder {
  def now = LocalDate.now()
  def now(clock: Clock) = LocalDate.now(clock)
  def now(zoneId: ZoneId) = LocalDate.now(zoneId)
  def parse(str: String) = LocalDate.parse(str)
  def parse(str: String, formatter: DateTimeFormatter) = LocalDate.parse(str, formatter)
}
