import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

name := "SafeTrip"

organization := "com.qiang"

version := "1.0"

startYear := Some(2015)


scalaVersion := "2.11.6"

//import AssemblyKeys._

//assemblySettings


//jarName := "SafeTrip.jar"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  ,"-unchecked"
  ,"-encoding", "UTF-8"
  ,"-Xlint"
  ,"-Yclosure-elim"
  ,"-Yinline"
  ,"-Xverify"
  ,"-feature"
  ,"-language:postfixOps"
)


//provided
//libraryDependencies ++= Seq(
//"joda-time" % "joda-time",
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.0"  % "compile",
  "com.databricks" % "spark-csv_2.10" % "1.4.0",
  "org.apache.spark" %% "spark-sql" % "1.6.0"  % "compile",
  "org.apache.spark" %% "spark-streaming" % "1.6.0"  % "compile" ,
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0" ,
  "org.apache.commons" % "commons-pool2" % "2.3",
  "io.jvm.uuid" %% "scala-uuid" % "0.2.1",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-cluster" % "2.3.11",
  "com.typesafe.akka" %% "akka-remote" % "2.3.11",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.11"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

maintainer := "Qiang Xie <qiang@gmail.com>"

dockerEntrypoint in Docker := Seq("sh", "-c", "bin/SingleSafeTrip $*")

dockerRepository := Some("qiang")

dockerBaseImage := "ubuntu"



