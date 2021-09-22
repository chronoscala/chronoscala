import com.typesafe.tools.mima.core.{DirectMissingMethodProblem, ProblemFilters}

val isScala3 = Def.setting(
  CrossVersion.partialVersion(scalaVersion.value).exists(_._1 == 3)
)

Global / onChangedBuildSource := ReloadOnSourceChanges

publish / skip := true

lazy val chronoscala = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(
    name := "chronoscala",
    publishTo := sonatypePublishToBundle.value,
    organization := "io.github.chronoscala",
    licenses += "MIT" -> url("http://opensource.org/licenses/MIT"),
    version := "2.0.1",
    publishMavenStyle := true,
    crossScalaVersions := Seq("2.12.14", "2.13.6", "3.0.2"),
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
      "org.scalacheck" %%% "scalacheck" % "1.15.4" % Test,
      "org.scalatest" %%% "scalatest" % "3.2.9" % Test
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
        // Set(organization.value %% name.value % "2.0.1")
        Set.empty
      },
      Test / test := {
        mimaReportBinaryIssues.value
        (Test / test).value
      }
    )
  })
  .enablePlugins(MimaPlugin)
