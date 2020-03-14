package jp.ne.opt.chronoscala

import jp.ne.opt.chronoscala.Imports._
import org.scalacheck.{Prop, Properties}

object RichLocalTimeSpec extends Properties("RichLocalTime") with Gens {
  import Prop.forAll

  property("totally ordered") = forAll(for {
    a <- localTimeGen
    b <- localTimeGen
    c <- localTimeGen
  } yield (a, b, c)) {
    case (a, b, c) =>
      val antisymmetry = !(a <= b && b <= a) || a == b
      val transitivity = !(a <= b && b <= c) || a <= c
      val totality = a <= b || b <= a

      antisymmetry && transitivity && totality
  }
}
