package jp.ne.opt.chronoscala

import java.time.{LocalDate, Period}
import jp.ne.opt.chronoscala.Imports._

/**
 * Represents an immutable date interval
 */
case class DateInterval(startDate: LocalDate, endDate: LocalDate, step: Period) extends Seq[LocalDate] {
  def apply(idx: Int) = {
    if (0 <= idx && idx < length) {
      iterator.drop(idx).next
    } else {
      throw new IndexOutOfBoundsException()
    }
  }
  
  def iterator = Iterator.iterate(startDate)(_ + step).takeWhile(_ <= endDate)
  
  def length = iterator.length

  def by(step: Period) = this.copy(step = step)
}
