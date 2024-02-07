//package monads

//import scala.annotation.tailrec
//import fplibrary.*

//object Program:
//  
//
//  def createDescription(args: Array[String]): Description[Unit] = Description.create {
//    display(hyphens)
//
//    display(question)
//
//    val input: String = promt()
//    val integerAmount: Int = convertStringToInt(input)
//    val positiveAmount: Int = ensureAmountIsPositive(integerAmount)
//    val balance: Int = round(positiveAmount)
//    val message: String = createMessage(balance)
//
//    display(message)
//    display(hyphens)
//
//
//  }
//
//  private lazy val hyphens: Any => String = _ =>
//    "-" * 50
//
//  private lazy val question: Any => String = _ =>
//    "Push cash"
//
//  private lazy val display: Any => Unit = input =>
//    println(input)
//
//  private lazy val promt: Any => String = _ => "5"
//
//  private lazy val convertStringToInt: String => Int = input => 
//    input.toInt
//
//  private lazy val ensureAmountIsPositive: Int => Int = amount =>
//    if (amount < 1) 1
//    else amount
//
//  private lazy val round: Int => Int = amount =>
//    if (isDivisibleByHundred(amount)) amount
//    else round(amount + 1)
//
//  private lazy val isDivisibleByHundred: Int => Boolean = amount =>
//    amount % 100 == 0
//
//  private lazy val createMessage: Int => String = balance =>
//    s"You have balance $balance"
//

