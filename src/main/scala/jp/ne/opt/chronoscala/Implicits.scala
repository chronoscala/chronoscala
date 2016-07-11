package jp.ne.opt.chronoscala

import java.time.{LocalDateTime, LocalTime, LocalDate, ZonedDateTime}
import scala.language.implicitConversions

trait Implicits extends IntImplicits with TimeImplicits with OrderingImplicits

trait IntImplicits {
  implicit def richInt(n: Int): RichInt = new RichInt(n)
}

trait TimeImplicits {
  implicit def richZonedDateTime(t: ZonedDateTime): RichZonedDateTime = new RichZonedDateTime(t)
  implicit def richLocalDateTime(t: LocalDateTime): RichLocalDateTime = new RichLocalDateTime(t)
  implicit def richLocalTime(t: LocalTime): RichLocalTime = new RichLocalTime(t)
  implicit def richLocalDate(t: LocalDate): RichLocalDate = new RichLocalDate(t)
}

trait OrderingImplicits {
  implicit val zonedDateTimeOrdering: Ordering[ZonedDateTime] = Ordering.fromLessThan(_ isBefore _)
  implicit val localDateTimeOrdering: Ordering[LocalDateTime] = Ordering.fromLessThan(_ isBefore _)
  implicit val localDateOrdering: Ordering[LocalDate] = Ordering.fromLessThan(_ isBefore _)
  implicit val localTimeOrdering: Ordering[LocalTime] = Ordering.fromLessThan(_ isBefore _)
}
