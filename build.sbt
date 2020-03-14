import com.typesafe.tools.mima.core.{DirectMissingMethodProblem, ProblemFilters}

Global / onChangedBuildSource := ReloadOnSourceChanges

val scala210 = "2.10.7"

lazy val chronoscala = (project in file("."))
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

    scalaVersion := scala210,

    crossScalaVersions := Seq(scala210, "2.11.12", "2.12.10", "2.13.1"),

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

    libraryDependencies ++= Seq(
      "org.scalacheck" %% "scalacheck" % "1.14.3" % Test,
      "org.scalatest" %% "scalatest" % "3.1.1" % Test
    ),

    TaskKey[Unit]("checkScalariform") := {
      val diff = sys.process.Process("git diff").!!
      if (diff.nonEmpty) {
        sys.error("Working directory is dirty!\n" + diff)
      }
    }
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
      mimaBinaryIssueFilters +=
        ProblemFilters.exclude[DirectMissingMethodProblem]("jp.ne.opt.chronoscala.Interval.apply"),
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
