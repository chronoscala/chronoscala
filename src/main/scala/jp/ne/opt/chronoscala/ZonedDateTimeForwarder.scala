package jp.ne.opt.chronoscala

import java.time.format.DateTimeFormatter
import java.time.{Clock, ZoneId, ZonedDateTime}

trait ZonedDateTimeForwarder {

  def now = ZonedDateTime.now()

  def now(clock: Clock) = ZonedDateTime.now(clock)

  def now(zoneId: ZoneId) = ZonedDateTime.now(zoneId)

  def parse(str: String) = ZonedDateTime.parse(str)

  def parse(str: String, formatter: DateTimeFormatter) = ZonedDateTime.parse(str, formatter)

}
