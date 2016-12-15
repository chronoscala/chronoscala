package jp.ne.opt.chronoscala

import java.time.{Instant, ZonedDateTime}
import java.util.TimeZone
import org.scalacheck.{Arbitrary, Gen}

trait Gens {
  def instantGen: Gen[Instant] = Gen.chooseNum(0L, Long.MaxValue).map(Instant.ofEpochMilli)

  def zonedDateTimeGen: Gen[ZonedDateTime] = for {
    instant <- instantGen
    zoneId <- Gen.oneOf(TimeZone.getAvailableIDs.map(TimeZone.getTimeZone(_).toZoneId))
  } yield ZonedDateTime.ofInstant(instant, zoneId)

  implicit val zonedDateTimeArbitrary = Arbitrary(zonedDateTimeGen)
}
