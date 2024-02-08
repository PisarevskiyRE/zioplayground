package monads


import fplibrary.*
import fplibrary.PointFree.{InfixNotationForPointFree, InfixNotationForPointFreeKleisli}

import scala.annotation.tailrec

object PointFreeProgram:

  private lazy val ignoreArgs: Array[String] => Unit = _ => ()

//  lazy val createDescription:Array[String] => Description[Unit] = args =>
//    Description.create(
//    display(
//      createMessage(
//        round(
//          ensureAmountIsPositive(
//            convertStringToInt(
//              prompt(
//                display(
//                  question(
//                    display(
//                      hyphens(
//                        args
//                      )
//                    )
//                  )
//                )
//              )
//            )
//          )
//        )
//      )
//    )
//    )

  lazy val createDescription: Array[String] => Description[Unit]  =
    ignoreArgs  `-->` hyphens  `-->` displayKleisli                                                         `>=>` 
    question  `-->` displayKleisli                                                                          `>=>`  
    promptKleisli                                                                                           `>=>` 
    convertStringToInt `-->` ensureAmountIsPositive   `-->`round  `-->` createMessage  `-->` displayKleisli `>=>` 
    Description.brokenCreate



  private lazy val hyphens: Any => String = _ =>
    "-" * 50

  private lazy val question: Any => String = _ =>
    "Push cash"

  private lazy val displayKleisli: Any => Description[Unit] = input => Description.create{
    println(input)
  }
  
  private lazy val display: Any => Unit = input =>
    println(input)


  private lazy val promptKleisli: Any => Description[String] = _ => Description.create("5")
  private lazy val prompt: Any => String = _ => "5"
  //io.StdIn.readLine


  private lazy val convertStringToInt: String => Int = input =>
    input.toInt

  private lazy val ensureAmountIsPositive: Int => Int = amount =>
    if (amount < 1) 1
    else amount


  private lazy val round: Int => Int = amount =>
    if (isDivisibleByHundred(amount)) amount
    else round(amount + 1)

  private lazy val isDivisibleByHundred: Int => Boolean = amount =>
    amount % 100 == 0

  private lazy val createMessage: Int => String = balance =>
    s"You have balance $balance"


