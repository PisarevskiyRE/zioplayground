package playground

import ourzio.*

object Main extends scala.App:


  val trace = s"[${Console.BLUE}TRACE${Console.RESET}]"


  println(Runtime.default.unsafeRunSync(programm))

  lazy val programm = (for
    _ <- console.putStrLn("-" * 100)
    _ <- console.putStrLn("What is your name")
      name <- ZIO.succeed("Roma")
    _ <- console.putStrLn(s"Hello $name")

    _ <- ZIO.effect(throw RuntimeException( "boom"))

    _ <- console.putStrLn("-" * 100)
  yield ())








