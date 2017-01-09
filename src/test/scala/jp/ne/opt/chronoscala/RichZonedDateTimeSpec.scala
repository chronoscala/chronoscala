package jp.ne.opt.chronoscala

import org.scalacheck.{Prop, Properties}
import Imports._

object RichZonedDateTimeSpec extends Properties("RichZonedDateTime") with Gens {
  import Prop.forAll

  property("totally ordered") = forAll { (a: ZonedDateTime, b: ZonedDateTime, c: ZonedDateTime) =>
    val antisymmetry = !(a <= b && b <= a) || a == b
    val transitivity = !(a <= b && b <= c) || a <= c
    val totality = a <= b || b <= a

    antisymmetry && transitivity && totality
  }

  property("max and min") = forAll { (a: ZonedDateTime, b: ZonedDateTime) =>
    (a max b) == (b max a) && (a min b) == (b min a) && (a min b) <= (a max b)
  }
}
