package org.dgrald.word.correctors

import scala.annotation.tailrec

/**
  * Created by dylangrald on 10/30/16.
  */
object RepeatAcryonymCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {

    @tailrec
    def fixRec(total: String, remaining: Seq[String]): String = remaining match {
      case first +: second +: rest =>
        val secondLetters = second.filter(c => c.isLetter)
        val newTotal = if(total.length == 0) {
          first
        } else {
          s"$total $first"
        }
        val newRemaining = if(first.equals(secondLetters)) {
          rest
        } else {
          second +: rest
        }
        fixRec(newTotal, newRemaining)
      case Seq(first) => s"$total $first"
      case Seq() => total
    }

    val lines = input.split("\n")
    (for {
      line <- lines
    } yield fixRec("", line.split(" "))).mkString("\n")
  }
}
