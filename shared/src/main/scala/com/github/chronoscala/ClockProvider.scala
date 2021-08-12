package com.github.chronoscala

import java.time.{Clock, Instant, ZoneId}

object ClockProvider {

  @volatile private[this] var _clock = Clock.systemDefaultZone()

  def clock: Clock = _clock

  def setCurrentClock(clock: Clock): Unit = _clock = clock

  def setCurrentClockFixedMillis(epochMilli: Long): Unit =
    _clock = Clock.fixed(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault())

  def setCurrentClockSystem(): Unit = _clock = Clock.systemDefaultZone()
}
