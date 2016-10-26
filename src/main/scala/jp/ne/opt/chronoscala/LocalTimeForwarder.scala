package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, LocalTime}

trait LocalTimeForwarder {

  def now() = LocalTime.now()

  def now(clock: Clock) = LocalTime.now(clock)

  def now(zoneId: ZoneId) = LocalTime.now(zoneId)

  def parse(str: String) = LocalTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter) = LocalTime.parse(str, formatter)

}
