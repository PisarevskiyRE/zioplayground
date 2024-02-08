package monads

import fplibrary.Description
import fplibrary.PointFree

object Description:
  def create[A](a: => A): Description[A] =
    () => a
    
  implicit val M: Monad[Description] = new Monad[Description] {
    final override def flatMap[A, B](ca: Description[A])( acb: A => Description[B]): Description[B] = {
      val a = ca.apply()
      val cb = acb(a)

      cb 
    }
  }


  trait Monad[C[_]]:
    def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]

    @inline def bind[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)

    @inline def `>>=`[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)


  object Monad:
    def apply[C[_] : Monad]: Monad[C] = implicitly[Monad[C]]