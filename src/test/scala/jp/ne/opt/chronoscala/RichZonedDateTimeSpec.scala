package jp.ne.opt.chronoscala

import org.scalacheck.{ Prop, Properties }
import Imports._

object RichZonedDateTimeSpec extends Properties("RichZonedDateTime") with Gens {
  import Prop.forAll

  property("totally ordered") = forAll(for {
    a <- zonedDateTimeGen
    b <- zonedDateTimeGen
    c <- zonedDateTimeGen
  } yield (a, b, c)) {
    case (a, b, c) =>
      val antisymmetry = !(a <= b && b <= a) || a == b
      val transitivity = !(a <= b && b <= c) || a <= c
      val totality = a <= b || b <= a

      antisymmetry && transitivity && totality
  }
}
