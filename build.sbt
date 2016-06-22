//import com.typesafe.sbt.packager.archetypes.ServerLoader
//import NativePackagerHelper._
import AssemblyKeys._
//import sbtdocker.DockerKeys._

assemblySettings

name := "safetrip"

//organization := ""

version := "latest"

startYear := Some(2015)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.0"  % "provided",
  "com.databricks" % "spark-csv_2.10" % "1.4.0",
  "org.apache.spark" %% "spark-sql" % "1.6.0"  % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.6.0"  % "provided" ,
  "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0" ,
  "org.apache.commons" % "commons-pool2" % "2.3",
  "io.jvm.uuid" %% "scala-uuid" % "0.2.1",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-cluster" % "2.3.11",
  "com.typesafe.akka" %% "akka-remote" % "2.3.11",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.11"
).map(
  _.excludeAll(ExclusionRule(organization = "org.mortbay.jetty"))
)

mergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties"         => MergeStrategy.first
  case "org/apache/spark/unused/UnusedStubClass.class" => MergeStrategy.first
  case "META-INF/maven/com.google.guava/guava/pom.properties"  => MergeStrategy.first
  case "META-INF/maven/com.google.guava/guava/pom.xml" => MergeStrategy.first
  case "META-INF/maven/org.apache.avro/avro-ipc/pom.properties" => MergeStrategy.last
  case "META-INF/maven/org.slf4j/slf4j-api/pom.properties" => MergeStrategy.first
  case "META-INF/maven/org.slf4j/slf4j-api/pom.xml" => MergeStrategy.first

  case x =>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}

enablePlugins(DockerPlugin)


mainClass in Compile := Some("com.ibm.safetrip.SingleSafeTrip")

jarName := "safetrip.jar"

dockerfile in docker := {
  // The assembly task generates a fat JAR file
  val artifact: File = assembly.value
  val artifactTargetPath = s"/app/${artifact.name}"
  val scriptPath = baseDirectory (_/ "scripts/deploy-app.sh").value
  var scriptTragetPath = "/usr/bin/deploy-app.sh"

  new Dockerfile {
    from("ubuntu")
    add(artifact, artifactTargetPath)
    copy(scriptPath, scriptTragetPath)
    run("chmod", "+x", scriptTragetPath)
    //entryPoint("sh", "-c", "bin/SingleSafeTrip $*")
    expose(2181,9092)
  }
}

buildOptions in docker := BuildOptions(cache = false)