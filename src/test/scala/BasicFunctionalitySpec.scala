package jp.ne.opt.chronoscala

import jp.ne.opt.chronoscala.Imports._
import org.scalacheck.{Prop, Properties}

object BasicFunctionalitySpec extends Properties("ZonedDateTime") {

  property("ZonedDateTime.now == ZonedDateTime.now") = Prop.secure {
    val now: ZonedDateTime = ZonedDateTime.now
    now == now
  }

  property("ZonedDateTime.now < (ZonedDateTime.now + 1.hours)") = Prop.secure {
    val now: ZonedDateTime = ZonedDateTime.now
    (now plusHours 1) isAfter now
  }
}
