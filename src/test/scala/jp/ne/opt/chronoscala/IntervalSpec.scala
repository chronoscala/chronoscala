package jp.ne.opt.chronoscala

import java.time.{Duration, Instant}

import org.scalacheck.{Gen, Prop, Properties}

object IntervalSpec extends Properties("Interval") {
  import Prop.forAll

  val instantGen: Gen[Instant] = Gen.choose(0L, Long.MaxValue).map(Instant.ofEpochMilli)

  val startEndGen: Gen[(Instant, Instant)] = for {
    startEpochMillis <- Gen.choose(0L, Long.MaxValue)
    endEpochMillis <- Gen.choose(startEpochMillis, Long.MaxValue)
  } yield {
    val start = Instant.ofEpochMilli(startEpochMillis)
    val end = Instant.ofEpochMilli(endEpochMillis)

    (start, end)
  }

  property("empty interval") = forAll(instantGen) { instant =>
    Interval(instant, instant).duration == Duration.ZERO
  }

  property("contains itself") = forAll(startEndGen) { case (start, end) =>
    val interval = Interval(start, end)
    interval.contains(interval)
  }

  property("contains start and end") = forAll(startEndGen) { case (start, end) =>
    val interval = Interval(start, end)

    interval.contains(start) && interval.contains(end)
  }

  property("contains instant between start and end") = forAll(for {
    (start, end) <- startEndGen
    middleMillis <- Gen.choose(start.toEpochMilli, end.toEpochMilli)
  } yield (start, Instant.ofEpochMilli(middleMillis), end)) { case (start, middle, end) =>
    val interval = Interval(start, end)

    interval.contains(middle)
  }
}
