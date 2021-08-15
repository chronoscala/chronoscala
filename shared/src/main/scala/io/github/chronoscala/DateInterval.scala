package io.github.chronoscala

import java.time.{LocalDate, Period}
import Imports._

/**
 * Represents an immutable date interval
 */
case class DateInterval(startDate: LocalDate, endDate: LocalDate, step: Period)
  extends Seq[LocalDate] {
  def apply(idx: Int): LocalDate =
    if (0 <= idx && idx < length) {
      iterator.drop(idx).next()
    } else {
      throw new IndexOutOfBoundsException(idx.toString)
    }

  def iterator: Iterator[LocalDate] = Iterator.iterate(startDate)(_ + step).takeWhile(_ <= endDate)

  def length: Int = iterator.length

  def by(step: Period): DateInterval = this.copy(step = step)
}
