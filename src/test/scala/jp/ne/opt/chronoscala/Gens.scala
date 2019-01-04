package jp.ne.opt.chronoscala

import java.time._
import java.util.TimeZone
import org.scalacheck.Gen

trait Gens {
  def instantGen: Gen[Instant] = Gen.chooseNum(0L, Long.MaxValue).map(Instant.ofEpochMilli)

  def zonedDateTimeGen: Gen[ZonedDateTime] = for {
    instant <- instantGen
    zoneId <- Gen.oneOf(TimeZone.getAvailableIDs.map(TimeZone.getTimeZone(_).toZoneId))
  } yield ZonedDateTime.ofInstant(instant, zoneId)

  def offsetDateTimeGen: Gen[OffsetDateTime] = for {
    instant <- instantGen.map(_.atZone(ZoneOffset.UTC).toLocalDateTime)
    offset <- Gen.chooseNum(ZoneOffset.MIN.getTotalSeconds, ZoneOffset.MAX.getTotalSeconds)
      .map(ZoneOffset.ofTotalSeconds)
  } yield OffsetDateTime.of(instant, offset)

  def durationGen: Gen[Duration] = for {
    start <- instantGen
    end <- instantGen
  } yield Duration.between(start, end)
}
