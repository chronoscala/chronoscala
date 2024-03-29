package io.github.chronoscala

import java.time._
import java.util.TimeZone
import org.scalacheck.Gen
import Gens.timeZoneIds

trait Gens {

  def instantGen: Gen[Instant] = Gen.chooseNum(0L, Long.MaxValue).map(Instant.ofEpochMilli)

  def localDateGen: Gen[LocalDate] = for {
    year <- Gen.chooseNum(Year.MIN_VALUE, Year.MAX_VALUE)
    month <- Gen.chooseNum(1, 12)
    dayOfMonth <- Gen.chooseNum(1, Month.of(month).length(Year.isLeap(year)))
  } yield LocalDate.of(year, month, dayOfMonth)

  def localDateTimeGen: Gen[LocalDateTime] = for {
    instant <- instantGen
    zoneId <- Gen.oneOf(timeZoneIds)
  } yield LocalDateTime.ofInstant(instant, zoneId)

  def localTimeGen: Gen[LocalTime] = for {
    hour <- Gen.chooseNum(0, 23)
    minute <- Gen.chooseNum(0, 59)
    second <- Gen.chooseNum(0, 59)
  } yield LocalTime.of(hour, minute, second)

  def zonedDateTimeGen: Gen[ZonedDateTime] = for {
    instant <- instantGen
    zoneId <- Gen.oneOf(timeZoneIds)
  } yield ZonedDateTime.ofInstant(instant, zoneId)

  def offsetDateTimeGen: Gen[OffsetDateTime] = for {
    instant <- instantGen.map(_.atZone(ZoneOffset.UTC).toLocalDateTime)
    offset <- Gen
      .chooseNum(ZoneOffset.MIN.getTotalSeconds, ZoneOffset.MAX.getTotalSeconds)
      .map(ZoneOffset.ofTotalSeconds)
  } yield OffsetDateTime.of(instant, offset)

  def durationGen: Gen[Duration] = for {
    start <- instantGen
    end <- instantGen
  } yield Duration.between(start, end)
}

object Gens {
  private val timeZoneIds = TimeZone.getAvailableIDs.map(TimeZone.getTimeZone(_).toZoneId).toSeq
}
