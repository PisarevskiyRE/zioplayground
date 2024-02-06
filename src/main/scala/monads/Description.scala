package monads

import fplibrary.Description

object Description:
  def create[A](a: => A): Description[A] =
    () => a
