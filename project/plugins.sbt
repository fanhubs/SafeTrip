

resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
resolvers += Resolver.url("Akka Snapshot Repository", url("http://repo.akka.io/"))(Resolver.ivyStylePatterns)

//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

addSbtPlugin("org.typelevel" % "sbt-typelevel" % "0.3.1")

addSbtPlugin("com.scalapenos" % "sbt-prompt" % "0.2.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.3")

