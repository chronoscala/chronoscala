# chronoscala

A port of [nscala_time](https://github.com/nscala-time/nscala-time) for JSR-310.

## Requirement

- Java 8

## Installation

```scala
libraryDependencies += "jp.ne.opt" %% "chronoscala" % "0.0.1"
```

## Usage

```scala
import jp.ne.opt.chronoscala.Imports._

ZonedDateTime.now + 2.months // returns java.time.ZonedDateTime = 2016-09-12T02:24:22.724+09:00[Asia/Tokyo]

ZonedDateTime.now < ZonedDateTime.now + 1.month // returns true

2.hours + 45.minutes + 10.seconds // returns PT2H45M10S

(2.hours + 45.minutes + 10.seconds).millis // returns 9910000

2.months + 3.days // returns P2M3D
```
