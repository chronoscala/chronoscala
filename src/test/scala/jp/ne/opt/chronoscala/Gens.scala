package jp.ne.opt.chronoscala

import java.time.{ Instant, ZonedDateTime }
import java.util.TimeZone

import org.scalacheck.Gen

trait Gens {
  def instantGen: Gen[Instant] = Gen.choose(0L, Long.MaxValue).map(Instant.ofEpochMilli)

  def zonedDateTimeGen: Gen[ZonedDateTime] = for {
    instant <- instantGen
    zoneId <- Gen.oneOf(TimeZone.getAvailableIDs.map(TimeZone.getTimeZone(_).toZoneId))
  } yield ZonedDateTime.ofInstant(instant, zoneId)
}
