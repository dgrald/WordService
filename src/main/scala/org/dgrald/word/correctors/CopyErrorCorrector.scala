package org.dgrald.word.correctors

import scala.annotation.tailrec

/**
  * Created by dylangrald on 9/15/16.
  */
object CopyErrorCorrector extends Corrector{
  override def correct(input: String, otherInstructions: List[Any]): String = {

    @tailrec
    def correctTailRec(latestInput: String, remainingInstructions: List[Any]): String = remainingInstructions match {
      case List() => latestInput
      case first +: rest => first match {
        case (toReplace: String, replacement: String) =>
          correctTailRec(latestInput.replace(toReplace, replacement), rest)
        case _ => correctTailRec(latestInput, rest)
      }

    }

    correctTailRec(input, otherInstructions)
  }
}
