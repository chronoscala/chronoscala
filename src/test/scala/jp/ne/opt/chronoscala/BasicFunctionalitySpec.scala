package jp.ne.opt.chronoscala

import jp.ne.opt.chronoscala.Imports._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Prop, Properties}

object BasicFunctionalitySpec extends Properties("ZonedDateTime") with Gens {

  property("ZonedDateTime equality") = Prop.secure {
    forAll(zonedDateTimeGen) { zonedDateTime =>
      zonedDateTime == zonedDateTime
    }
  }

  property("zonedDateTime < (zonedDateTime + 1.hour)") = Prop.secure {
    forAll(zonedDateTimeGen) { zonedDateTime =>
      zonedDateTime < (zonedDateTime + 1.hour)
    }
  }
}
