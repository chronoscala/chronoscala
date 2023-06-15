import com.typesafe.tools.mima.core.{DirectMissingMethodProblem, ProblemFilters}

Global / onChangedBuildSource := ReloadOnSourceChanges

publish / skip := true

lazy val chronoscala = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("."))
  .settings(
    name := "chronoscala",
    publishTo := sonatypePublishToBundle.value,
    organization := "io.github.chronoscala",
    licenses += "MIT" -> url("http://opensource.org/licenses/MIT"),
    version := "2.0.8",
    publishMavenStyle := true,
    crossScalaVersions := Seq("2.12.18", "2.13.11", "3.3.0"),
    scalaVersion := crossScalaVersions.value.last,
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    Compile / doc / scalacOptions ++= {
      val tree = sys.process.Process("git rev-parse HEAD").lineStream_!.head
      Seq(
        "-sourcepath",
        (LocalRootProject / baseDirectory).value.getAbsolutePath,
        "-doc-source-url",
        s"https://github.com/chronoscala/chronoscala/tree/$tree€{FILE_PATH}.scala"
      )
    }
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % "1.17.0" % Test,
      "org.scalatest" %%% "scalatest" % "3.2.16" % Test
    )
  )
  .platformsSettings(JSPlatform, NativePlatform)(
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.5.0",
      "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.5.0"
    )
  )
  .settings {
    Seq(
      mimaPreviousArtifacts := {
        // Set(organization.value %% name.value % "2.0.8")
        Set.empty
      },
      Test / test := {
        mimaReportBinaryIssues.value
        (Test / test).value
      }
    )
  }
  .jsSettings(
    scalacOptions += {
      val a = (LocalRootProject / baseDirectory).value.toURI.toString
      val tree = sys.process.Process("git rev-parse HEAD").lineStream_!.head
      val g = "https://raw.githubusercontent.com/chronoscala/chronoscala/" + tree
      val key =
        if (scalaBinaryVersion.value == "3") {
          "-scalajs-mapSourceURI"
        } else {
          "-P:scalajs:mapSourceURI"
        }
      s"${key}:$a->$g/"
    }
  )
  .enablePlugins(MimaPlugin)
