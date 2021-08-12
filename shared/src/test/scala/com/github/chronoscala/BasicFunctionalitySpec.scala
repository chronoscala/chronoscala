package com.github.chronoscala

import Imports._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Prop, Properties}

object BasicFunctionalitySpec extends Properties("ZonedDateTime") with Gens {

  property("LocalDateTime equality") = Prop.secure {
    forAll(localDateTimeGen) { localDateTime =>
      localDateTime == localDateTime
    }
  }

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

  property("localDateTime < (localDateTime + 1.hour)") = Prop.secure {
    forAll(localDateTimeGen) { localDateTime =>
      localDateTime < (localDateTime + 1.hour)
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
