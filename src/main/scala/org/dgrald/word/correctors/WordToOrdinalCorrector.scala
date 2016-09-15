package org.dgrald.word.correctors

import org.dgrald.StringUtils

import scala.util.matching.Regex.Match

/**
  * Created by dylangrald on 9/14/16.
  */
object WordToOrdinalCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val wordToOrdinalMap = Map(
      "first" -> "1st",
      "second" -> "2nd",
      "third" -> "3rd",
      "fourth" -> "4th",
      "fifth" -> "5th",
      "sixth" -> "6th",
      "seventh" -> "7th",
      "eighth" -> "8th",
      "ninth" -> "9th",
      "tenth" -> "10th"
    )

    def isFollowedBySpaceAndThenNumber(regexMatch: Match): Boolean = {
      regexMatch.end < (input.length + 2) && input.charAt(regexMatch.end).isSpaceChar && input.charAt(regexMatch.end + 1).isDigit
    }

    wordToOrdinalMap.foldRight(List(input))((ordinalPair, outputs) => ordinalPair match {
      case (key: String, value: String) => {
        val ordinalWordRegex = s"\\b($key|${StringUtils.capitalize(key)})\\b"
        val regex = s"$ordinalWordRegex".r
        regex.replaceAllIn(outputs.head, regexMatch => {
            if(isFollowedBySpaceAndThenNumber(regexMatch)) {
              regexMatch.toString()
            } else {
              wordToOrdinalMap(regexMatch.toString().toLowerCase)
            }
        }) +: outputs
      }
    }).head
  }
}
