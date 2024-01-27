package playground

import zio.*

object ourConsole:
  def putStrLn(line: => String) =
    ZIO.succeed(println(line))

  val getStrLn =
    ZIO.succeed(scala.io.StdIn.readLine())



object Main extends scala.App:
  val trace = s"[${Console.BLUE}TRACE${Console.RESET}]"

  val programm = (for
    _ <- ourConsole.putStrLn("-" * 100)
    _ <- ourConsole.putStrLn("What is your name")
    name <- ZIO.succeed("Roma")
    _ <- ourConsole.putStrLn(s"Hello $name")

    _ <- ZIO.effect(sys.error("BOOM"))

    _ <- ourConsole.putStrLn("-" * 100)
  yield ())

  Runtime.default.unsafeRunSync(programm)







