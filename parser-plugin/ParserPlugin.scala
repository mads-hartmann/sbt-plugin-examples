import sbt._
import Keys._
import Defaults._
import complete.DefaultParsers._
import complete.DefaultParsers.{ NotSpace, Space, repsep }
import complete.{ Parser }

object ParserPlugin extends Plugin {
  
  // Check out the API "documentation" for the default parsers here: 
  // http://harrah.github.com/xsbt/latest/api/index.html#sbt.complete.DefaultParsers$

  override lazy val settings = Seq(commands += cmd)

  // apply [T] (name: String, help: Help = Help.empty)(parser: (State) ⇒ Parser[T])(effect: (State, T) ⇒ State): Command
  lazy val cmd = Command("ask-phase")(_ => parser)(action _)
    
  def action(st: State, parsed: (String,String)): State = {
    val (name, question) = parsed
    println("hi " + name + ", " + question)
    st
  }

  val names = List("brian","jamie", "nik", "richard", "yuvi", "jay", "jeff", "drew", "chirag", "dustin", "george")  
  val questions = List("would you like to buy mads a beer?","nice weather today don't you think?")
  
  lazy val parser: Parser[(String, String)] = 
    Space ~> nameParser flatMap { name => Space ~> questionParser map { (name,_) } }
  
  val nameParser: Parser[String] = 
    token(NotSpace.examples(names:_*)).filter(names.contains(_), _ + " isn't here tonight") 
  
  def questionParser: Parser[String] = 
    token(sentence.examples(questions:_*))
  
  val sentence: Parser[String] = repsep(NotSpace, Space) map ( _.mkString(" "))

}