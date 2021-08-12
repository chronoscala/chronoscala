package com.github.chronoscala

import java.time.{Clock, ZoneId}

import Imports._
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

class ClockProviderSpec extends AnyFlatSpec with BeforeAndAfter {

  after {
    ClockProvider.setCurrentClockSystem()
  }

  it should "set current clock" in {

    ClockProvider.setCurrentClock(Clock.fixed(Instant.ofEpochMilli(0L), ZoneId.of("UTC")))

    assert(Instant.now() == Instant.ofEpochMilli(0L))
    assert(LocalDate.now() == LocalDate.parse("1970-01-01"))
    assert(LocalDateTime.now() == LocalDateTime.parse("1970-01-01T00:00:00.000"))
    assert(LocalTime.now() == LocalTime.parse("00:00:00.000"))
    assert(ZonedDateTime.now() == ZonedDateTime.parse("1970-01-01T00:00:00.000+00:00[UTC]"))
    assert(OffsetDateTime.now() == OffsetDateTime.parse("1970-01-01T00:00:00.000+00:00"))
  }
}
