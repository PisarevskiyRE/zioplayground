package playground


object ourzio:

  final case class ZIO[A](think: () => A):
    def flatMap[B](azb: A => ZIO[B]): ZIO[B] =
      ZIO.succeed{
        val a = think()
        val zb = azb(a)
        val b = zb.think()
        b
      }

    def map[B](ab: A => B): ZIO[B] =
      ZIO.succeed {
        val a = think()
        val b = ab(a)
        b
      }
  end ZIO

  object ZIO:
    def succeed[A](a: => A): ZIO[A] =
      ZIO(() => a)

  object console:
    def putStrLn(line: => String) =
      ZIO.succeed(println(line))

    val getStrLn =
      ZIO.succeed(scala.io.StdIn.readLine())


  object Runtime:
    object default:
      def unsafeRunSync[A](zio: => ZIO[A]): A =
        zio.think()

