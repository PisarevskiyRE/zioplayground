package playground

import ourzio.*

object Main extends scala.App:


  val trace = s"[${Console.BLUE}TRACE${Console.RESET}]"
  Runtime.default.unsafeRunSync(programm)

  lazy val programm = (for
    _ <- console.putStrLn("-" * 100)
    _ <- console.putStrLn("What is your name")
      name <- ZIO.succeed("Roma")
    _ <- console.putStrLn(s"Hello $name")
    _ <- console.putStrLn("-" * 100)
  yield ())








