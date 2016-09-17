package org.dgrald.word.correctors

import scala.annotation.tailrec

/**
  * Created by dylangrald on 9/15/16.
  */
object FindAndReplaceCorrector extends Corrector{
  override def correct(input: String, otherInstructions: List[Any]): String = {

    def replaceFirstInstance(alreadyParsed: String, toParse: String, toReplace: String, replacement: String, alreadySeen: Boolean): String = toParse match {
      case "" => alreadyParsed
      case someString => {
        if(alreadySeen) {
          alreadyParsed ++ toParse.replace(toReplace, replacement)
        } else {
          val start = toParse.indexOf(toReplace)
          val end = start + toReplace.length
          replaceFirstInstance(toParse.substring(0, end), toParse.substring(end, toParse.length), toReplace, replacement, alreadySeen = true)
        }
      }
    }


    @tailrec
    def correctTailRec(latestInput: String, remainingInstructions: List[Any]): String = remainingInstructions match {
      case List() => latestInput
      case first +: rest => first match {
        case (toReplace: String, replacement: String, replaceAllButFirst: Boolean) =>
          val newInput = if(replaceAllButFirst) {
            replaceFirstInstance("", latestInput, toReplace, replacement, alreadySeen = false)
          } else {
            latestInput.replace(toReplace, replacement)
          }
          correctTailRec(newInput, rest)
        case _ => correctTailRec(latestInput, rest)
      }

    }

    correctTailRec(input, otherInstructions)
  }
}
