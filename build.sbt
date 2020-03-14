import com.typesafe.tools.mima.core.{DirectMissingMethodProblem, ProblemFilters}

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val chronoscala = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(
    name := "chronoscala",

    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),

    organization := "jp.ne.opt",

    licenses += "MIT" -> url("https://raw.githubusercontent.com/opt-tech/chronoscala/master/LICENSE"),

    version := "0.3.3-SNAPSHOT",

    publishMavenStyle := true,

    scalaVersion := "2.11.12",

    crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1"),

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    scalacOptions in (Compile, doc) ++= {
      val tree = sys.process.Process("git rev-parse HEAD").lineStream_!.head
      Seq(
        "-sourcepath",
        (baseDirectory in LocalRootProject).value.getAbsolutePath,
        "-doc-source-url",
        s"https://github.com/opt-tech/chronoscala/tree/${tree}€{FILE_PATH}.scala"
      )
    },

    TaskKey[Unit]("checkScalariform") := {
      val diff = sys.process.Process("git diff").!!
      if (diff.nonEmpty) {
        sys.error("Working directory is dirty!\n" + diff)
      }
    }
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % "1.14.3" % Test,
      "org.scalatest" %%% "scalatest" % "3.1.1" % Test
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.0.0-RC4",
      "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.0.0-RC4_2019c"
    )
  )
  .settings({
    val previousVersions = (0 to 2).map(patch => s"0.3.$patch").toSet
    Seq(
      mimaPreviousArtifacts := {
        if (scalaBinaryVersion.value == "2.13") {
          Set.empty
        } else {
          previousVersions.map {
            organization.value %% name.value % _
          }
        }
      },
      //chronoscala 0.3.0 is compiled with Scala 2.12.7.
      //the exclusion filter is necessary to avoid false positive of binary compatibility issue for Scala compiler bug (https://github.com/scala/bug/issues/11207).
      mimaBackwardIssueFilters := Map(
        "0.3.0" -> (
          if (scalaBinaryVersion.value == "2.12")
            Seq(ProblemFilters.exclude[DirectMissingMethodProblem]("jp.ne.opt.chronoscala.Interval.apply"))
          else
            Nil)
      ),
      test in Test := {
        mimaReportBinaryIssues.value
        (test in Test).value
      }
    )
  })
  .settings(
    {
      import scalariform.formatter.preferences._
      import SbtScalariform._

      ScalariformKeys.preferences := ScalariformKeys.preferences.value
        .setPreference(DoubleIndentConstructorArguments, false)
        .setPreference(SpacesAroundMultiImports, false)
    }
  ).enablePlugins(MimaPlugin)
