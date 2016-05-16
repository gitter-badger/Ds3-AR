scalaVersion in ThisBuild := "2.11.8"

lazy val root = project.in(file(".")).
  aggregate(ds3arJS, ds3arJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val ds3ar = crossProject.in(file("."))
  .settings(
    organization := "ds3ar",
    name := "ds3ar",
    autoCompilerPlugins := true,

    resolvers += Resolver.sonatypeRepo("releases"),
    resolvers += Resolver.sonatypeRepo("snapshots"),

    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-target:jvm-1.6",
      "-feature",
      "-deprecation",
      "-Xlint",
      "-Yinline-warnings",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      // "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Xfuture",
      "-language:higherKinds"
    ),

    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalatags" % "0.5.5",
      "com.lihaoyi" %%% "sourcecode" % "0.1.0",
      "org.typelevel" %%% "cats" % "0.5.0",
      "org.scalatest" %% "scalatest" % "3.0.0-M10" % "test"
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.github.tototoshi" %% "scala-csv" % "1.3.1",
      "com.github.melrief" %% "purecsv" % "0.0.6",
      "org.scodec" %% "scodec-bits" % "1.1.0",
      "com.nrinaudo" %% "kantan.csv-generic" % "0.1.10"
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq()
  )

// Needed, so sbt finds the projects
lazy val ds3arJVM = ds3ar.jvm
lazy val ds3arJS = ds3ar.js
