package monads

import fplibrary.*

import scala.annotation.tailrec

object PointFreeProgram:

  lazy val createDescription:Array[String] => Description[Unit] = args =>
    Description.create(
    display(
      createMessage(
        round(
          ensureAmountIsPositive(
            convertStringToInt(
              prompt(
                display(
                  question(
                    display(
                      hyphens(
                        args
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )
    )
    )
   


  private def hyphens(input: Any): String =
    "-" * 50

  private def question(input: Any): String =
    "Push cash"

  private def display(input: Any): Unit =
    println(input)

  private def prompt(input: Any): String = "5"
  //io.StdIn.readLine

  private def convertStringToInt(input: String): Int =
    input.toInt

  private def ensureAmountIsPositive(amount: Int): Int =
    if (amount < 1) 1
    else amount

  @tailrec
  private def round(amount: Int): Int =
    if (isDivisibleByHundred(amount)) amount
    else round(amount + 1)

  private def isDivisibleByHundred(amount: Int): Boolean =
    amount % 100 == 0

  private def createMessage(balance: Int): String =
    s"You have balance $balance"


