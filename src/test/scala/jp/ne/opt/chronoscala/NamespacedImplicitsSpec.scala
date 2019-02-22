package jp.ne.opt.chronoscala

import org.scalacheck.{Prop, Properties}

object NamespacedImplicitsSpec extends Properties("NamespacedImplicits") {
  property("Work with `import scala.concurrent.duration._`") = Prop.secure {
    import NamespacedImports._
    import scala.concurrent.duration._

    1.second.getClass == classOf[FiniteDuration] &&
      1L.second.getClass == classOf[FiniteDuration] &&
      1.cs.second.getClass == classOf[java.time.Duration] &&
      1L.cs.second.getClass == classOf[java.time.Duration]
  }
}
