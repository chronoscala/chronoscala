package jp.ne.opt.chronoscala

import jp.ne.opt.chronoscala.Imports._
import org.scalacheck.{Prop, Properties}

object RichLocalDateTimeSpec extends Properties("RichLocalDateTime") with Gens {
  import Prop.forAll

  property("totally ordered") = forAll(for {
    a <- localDateTimeGen
    b <- localDateTimeGen
    c <- localDateTimeGen
  } yield (a, b, c)) {
    case (a, b, c) =>
      val antisymmetry = !(a <= b && b <= a) || a == b
      val transitivity = !(a <= b && b <= c) || a <= c
      val totality = a <= b || b <= a

      antisymmetry && transitivity && totality
  }
}
