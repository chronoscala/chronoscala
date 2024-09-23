# chronoscala

[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/chronoscala/chronoscala/ci.yml?branch=main&style=for-the-badge)](https://github.com/chronoscala/chronoscala/actions/workflows/ci.yml?query=branch%3Amain)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.chronoscala/chronoscala_3?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.chronoscala/chronoscala_3)

A JSR-310 port of [nscala_time](https://github.com/nscala-time/nscala-time)

## Supported environment

- Scala 2.12.x / 2.13.x / 3.x.x
  * Requries Java 8 or later
- Scala.js
- Scala Native

## Installation

```scala
libraryDependencies += "io.github.chronoscala" %% "chronoscala" % LATEST_VERSION
```

## Usage

```scala
import io.github.chronoscala.Imports._

ZonedDateTime.now() + 2.months // returns java.time.ZonedDateTime = 2016-09-12T02:24:22.724+09:00[Asia/Tokyo]

OffsetDateTime.now() + 2.months // returns java.time.OffsetDateTime = 2016-09-12T02:24:22.724+09:00

ZonedDateTime.now() < ZonedDateTime.now() + 1.month // returns true

ZonedDateTime.now() to (ZonedDateTime.now() + 1.day) // returns Interval(2016-07-11T19:15:42.641Z,2016-07-12T19:15:42.641Z)

(ZonedDateTime.now() to (ZonedDateTime.now() + 1.second)).millis // returns 1000

2.hours + 45.minutes + 10.seconds // returns PT2H45M10S

(2.hours + 45.minutes + 10.seconds).millis // returns 9910000

2.months + 3.days // returns P2M3D

LocalDate.now() to (LocalDate.now() + 7.days) by 2.days // returns DateInterval(2016-09-04, 2016-09-06, 2016-09-08, 2016-09-10)
```

Chronoscala also provides `NamespacedImports` to avoid conflicts between other package such as `scala.concurrent.duration`.

```scala
import io.github.chronoscala.NamespacedImports._

// Methods are namespaced with `cs`.
2.cs.months + 3.cs.days // returns P2M3D

2L.cs.seconds + 3L.cs.seconds // returns PT5S
```

### ClockProvider

`ClockProvider` is useful for unit testing.

```scala
import io.github.chronoscala.Imports._
import io.github.chronoscala.ClockProvider

ClockProvider.setCurrentClockFixedMillis(0L)

Instant.now() // returns 1970-01-01T00:00:00Z
```
