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

  property("OffsetDateTime equality") = Prop.secure {
    forAll(offsetDateTimeGen) { offsetDateTime =>
      offsetDateTime == offsetDateTime
    }
  }

  property("zonedDateTime < (zonedDateTime + 1.hour)") = Prop.secure {
    forAll(zonedDateTimeGen) { zonedDateTime =>
      zonedDateTime < (zonedDateTime + 1.hour)
    }
  }

  property("offsetDateTime < (offsetDateTime + 1.hour)") = Prop.secure {
    forAll(offsetDateTimeGen) { offsetDateTime =>
      offsetDateTime < (offsetDateTime + 1.hour)
    }
  }
}
