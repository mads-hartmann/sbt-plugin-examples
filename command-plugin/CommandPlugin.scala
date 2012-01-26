import sbt._
import Keys._

object CommandPlugin extends Plugin {

  override lazy val settings = Seq(commands += myCommand)

  lazy val myCommand = 
    Command.command("hello") { (state: State) =>
      println("Hi PHASE!")
      state
    }
}