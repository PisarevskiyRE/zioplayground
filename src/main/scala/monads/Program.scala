package monads

import scala.annotation.tailrec
import fplibrary.*

object Program:
  

  def createDescription(args: Array[String]): Description[Unit] = Description.create {
    display(hyphens)

    display(question)

    val input: String = promt()
    val integerAmount: Int = convertStringToInt(input)
    val positiveAmount: Int = ensureAmountIsPositive(integerAmount)
    val balance: Int = round(positiveAmount)
    val message: String = createMessage(balance)

    display(message)
    display(hyphens)


  }


  private val hyphens: String =
    "-" * 50

  private val question: String =
    "Push cash"

  private def display(input: Any): Unit =
    println(input)

  private def promt(): String = "5"
    //io.StdIn.readLine

  private def convertStringToInt(input: String): Int =
    input.toInt

  private def ensureAmountIsPositive(amount: Int): Int =
    if (amount < 1) 1
    else amount
    
  @tailrec
  private def round(amount: Int ): Int =
    if (isDivisibleByHundred(amount)) amount
    else round(amount + 1)
  
  private def isDivisibleByHundred(amount: Int): Boolean =
    amount % 100 == 0

  private def createMessage(balance: Int): String =
    s"You have balance $balance"


