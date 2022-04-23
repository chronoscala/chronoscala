import com.typesafe.tools.mima.core.{DirectMissingMethodProblem, ProblemFilters}

Global / onChangedBuildSource := ReloadOnSourceChanges

publish / skip := true

lazy val chronoscala = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(
    name := "chronoscala",
    publishTo := sonatypePublishToBundle.value,
    organization := "io.github.chronoscala",
    licenses += "MIT" -> url("http://opensource.org/licenses/MIT"),
    version := "2.0.6",
    publishMavenStyle := true,
    crossScalaVersions := Seq("2.12.15", "2.13.8", "3.1.2"),
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
      "org.scalacheck" %%% "scalacheck" % "1.16.0" % Test,
      "org.scalatest" %%% "scalatest" % "3.2.11" % Test
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.3.0",
      "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.3.0"
    )
  )
  .settings({
    Seq(
      mimaPreviousArtifacts := {
        // Set(organization.value %% name.value % "2.0.6")
        Set.empty
      },
      Test / test := {
        mimaReportBinaryIssues.value
        (Test / test).value
      }
    )
  })
  .enablePlugins(MimaPlugin)
