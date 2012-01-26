import sbt._
import Keys._

object Playground extends Build {
  
  val commandPlugin = RootProject(file("../../command-plugin"))
  val settingsPlugin = RootProject(file("../../settings-plugin"))
  val parserPlugin = RootProject(file("../../parser-plugin"))
  
  lazy val root = Project(id = "playground", base = file(".")).dependsOn(commandPlugin, settingsPlugin, parserPlugin)

}