package object fplibrary:

  private type Thunk[A] = () => A
  type Description[A] = Thunk[A]
  
  private type RegularArrow[A, B] = A => B
  private type KleisliArrow[A, B, C[_]] = A => C[B]
  

