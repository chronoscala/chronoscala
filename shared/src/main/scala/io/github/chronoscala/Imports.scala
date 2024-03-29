package io.github.chronoscala

object Imports extends Imports

object NamespacedImports extends NamespacedImports

trait Imports extends TypeImports with StaticForwarderImports with Implicits

trait NamespacedImports extends TypeImports with StaticForwarderImports with NamespacedImplicits

trait TypeImports {
  type ZonedDateTime = java.time.ZonedDateTime
  type OffsetDateTime = java.time.OffsetDateTime
  type LocalDateTime = java.time.LocalDateTime
  type LocalDate = java.time.LocalDate
  type LocalTime = java.time.LocalTime
  type Instant = java.time.Instant
  type Duration = java.time.Duration
}

trait StaticForwarderImports {
  object ZonedDateTime extends ZonedDateTimeForwarder
  object OffsetDateTime extends OffsetDateTimeForwarder
  object LocalDateTime extends LocalDateTimeForwarder
  object LocalDate extends LocalDateForwarder
  object LocalTime extends LocalTimeForwarder
  object Instant extends InstantForwarder
}
