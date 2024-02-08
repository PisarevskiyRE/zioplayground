import monads.Description.Monad

package object fplibrary:

  private type Thunk[A] = () => A
  type Description[A] = Thunk[A]
  
  private type RegularArrow[A, B] = A => B
  private type KleisliArrow[A, B, C[_]] = A => C[B]
  

  object PointFree {
    def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
      val b = ab(a)
      val c = bc(b)

      c
    }

    def composeKleisli[A, B, C](adb: A => Description[B], bdc: B => Description[C]): A => Description[C] = a => {
      val db = adb(a)
      val b = db.apply()
      val dc = bdc(b)

      dc
    }

    //def helper[A, B ,C[_]](ca: C[A], acb: A => C[B]): C[B] = ???







    def composeKleisli2[A, B, C, D[_]: Monad](adb: A => D[B], bdc: B => D[C]): A => D[C] = a => {
      val db = adb(a)


      val dc = Monad[D].flatMap(db)(bdc)

      dc
    }


    implicit final class InfixNotationForPointFree[A, B](private val ab: A => B) extends AnyVal:
      @inline def `;`[C](bc: B => C): A => C = PointFree.compose(ab,bc)
      @inline def `.`[C](bc: B => C): A => C = PointFree.compose(ab,bc)
      @inline def `-->`[C](bc: B => C): A => C = PointFree.compose(ab,bc)


    implicit final class InfixNotationForPointFreeKleisli[A, B, D[_]](private val adb: A => D[B]) extends AnyVal:
      @inline def `;;`[C](bdc: B => D[C])(implicit  M: Monad[D]): A => D[C] = PointFree.composeKleisli(adb, bdc)

      @inline def `>=>`[C](bdc: B => D[C])(implicit  M: Monad[D]): A => D[C] = PointFree.composeKleisli(adb, bdc)


  }