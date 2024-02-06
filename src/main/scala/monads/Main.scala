package monads

import fplibrary.*

object Interpreter:
  def main(args: Array[String]): Unit =
    //Program.run(args)
    
    val description: Description[Unit] =
      PointFreeProgram.createDescription(args)
    
    def interpret[A](description: Description[A]): A =
      description.apply()
      
    interpret(description)
