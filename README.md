# chronoscala

[![Build Status](https://travis-ci.org/opt-tech/chronoscala.svg?branch=master)](https://travis-ci.org/opt-tech/chronoscala)

A port of [nscala_time](https://github.com/nscala-time/nscala-time) to JSR-310.

## Requirements

- Java 8
- Scala 2.10.x / 2.11.x / 2.12.x

## Installation

```scala
libraryDependencies += "jp.ne.opt" %% "chronoscala" % "0.1.1"
```

## Usage

```scala
import jp.ne.opt.chronoscala.Imports._

ZonedDateTime.now() + 2.months // returns java.time.ZonedDateTime = 2016-09-12T02:24:22.724+09:00[Asia/Tokyo]

ZonedDateTime.now() < ZonedDateTime.now() + 1.month // returns true

ZonedDateTime.now() to (ZonedDateTime.now() + 1.day) // returns Interval(2016-07-11T19:15:42.641Z,2016-07-12T19:15:42.641Z)

(ZonedDateTime.now() to (ZonedDateTime.now() + 1.second)).millis // returns 1000

2.hours + 45.minutes + 10.seconds // returns PT2H45M10S

(2.hours + 45.minutes + 10.seconds).millis // returns 9910000

2.months + 3.days // returns P2M3D

LocalDate.now() to (LocalDate.now() + 7.days) by 2.days // returns DateInterval(2016-09-04, 2016-09-06, 2016-09-08, 2016-09-10)
```

Chronoscala also provides `NamespacedImports` to avoid conflicts between other library. (Currently, only methods in Int implicits are namespaced.)

```scala
import jp.ne.opt.chronoscala.NamespacedImports._

// Methods are namespaced with `cs`.
2.cs.months + 3.cs.days // returns P2M3D
```
