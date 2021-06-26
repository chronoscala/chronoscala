package jp.ne.opt.chronoscala

import jp.ne.opt.chronoscala.Imports._
import org.scalacheck.{Prop, Properties}

object RichLocalDateSpec extends Properties("RichLocalDate") with Gens {
  import Prop.forAll

  property("totally ordered") = forAll(for {
    a <- localDateGen
    b <- localDateGen
    c <- localDateGen
  } yield (a, b, c)) { case (a, b, c) =>
    val antisymmetry = !(a <= b && b <= a) || a == b
    val transitivity = !(a <= b && b <= c) || a <= c
    val totality = a <= b || b <= a

    antisymmetry && transitivity && totality
  }
}
