name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJpa,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.jsoup" % "jsoup" % "1.9.2",
  "com.machinepublishers" % "jbrowserdriver" % "0.14.7",
  "org.seleniumhq.selenium" % "selenium-java" % "2.53.0"

)


fork in run := false