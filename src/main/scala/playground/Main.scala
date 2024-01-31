package playground

import ourzio.*

//object Main extends scala.App:
//
//  val trace = s"[${Console.BLUE}TRACE${Console.RESET}]"
//
//  println(Runtime.default.unsafeRunSync(programm))
//
//  lazy val programm = (for
//    _ <- console.putStrLn("-" * 100)
//    _ <- console.putStrLn("What is your name")
//      name <- ZIO.succeed("Roma")
//    _ <- console.putStrLn(s"Hello $name")
//
//    _ <- ZIO
//      .effect(throw RuntimeException( "boom"))
//      .catchAll( _ => ZIO.succeed("Шучу"))
//
//    _ <- console.putStrLn("-" * 100)
//  yield ())


trait BusinessLogic:
  def foo(topic: String): Boolean

object BusinessLogic:
  lazy val live: Google => BusinessLogic = google => make(google)

  def make(google: Google):BusinessLogic =
    new:
      override def foo(topic: String): Boolean =
        google.countPicturesOf(topic) % 2 == 0


trait Google:
  def countPicturesOf(topic: String): Int

object GoogleImp:
  lazy val live: Any => Google =
    _ => make


  lazy val make: Google =
    new:
      override def countPicturesOf(topic: String): Int =
        if topic == "cats" then 1337 else 1338


object DependencyGraph:
  lazy val live: Any => BusinessLogic = _ =>
    val google = GoogleImp.live.apply(())
    val businessLogic = BusinessLogic.live.apply(google)
    businessLogic


  lazy val make: BusinessLogic =
    val google = GoogleImp.make
    val businessLogic = BusinessLogic.make(google)
    businessLogic

object Main extends App:
  lazy val businessLogic = DependencyGraph.live.apply(())

  println("-"*100)
  println(businessLogic.foo("dogs"))
  println(businessLogic.foo("cats"))
  println("-"*100)









