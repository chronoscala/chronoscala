package jp.ne.opt.chronoscala

object Imports extends Imports
object NamespacedImports extends Imports with NamespacedImplicits

trait Imports extends TypeImports with StaticForwarderImports with Implicits

trait TypeImports {
  type ZonedDateTime = java.time.ZonedDateTime
  type LocalDateTime = java.time.LocalDateTime
  type LocalDate = java.time.LocalDate
  type LocalTime = java.time.LocalTime
}

trait StaticForwarderImports {
  object ZonedDateTime extends ZonedDateTimeForwarder
  object LocalDateTime extends LocalDateTimeForwarder
  object LocalDate extends LocalDateForwarder
  object LocalTime extends LocalTimeForwarder
}
