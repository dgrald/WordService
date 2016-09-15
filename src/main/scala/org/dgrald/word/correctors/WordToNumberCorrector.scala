package org.dgrald.word.correctors

import org.dgrald.StringUtils

import scala.util.matching.Regex.Match

/**
  * Created by dylangrald on 9/14/16.
  */
object WordToNumberCorrector extends Corrector {
  override def correct(input: String): String = {
    val wordsToNumbersMap = Map(
      "zero" -> 0,
      "one" -> 1,
      "two" -> 2,
      "three" -> 3,
      "four" -> 4,
      "five" -> 5,
      "six" -> 6,
      "seven" -> 7,
      "eight" -> 8,
      "nine" -> 9,
      "ten" -> 10
    )

    def isPrecededByHyphen(regexMatch: Match): Boolean = {
      regexMatch.start > 2 && regexMatch.source.toString().charAt(regexMatch.start - 1) == '-'
    }

    wordsToNumbersMap.foldRight(List(input))((tuple, outputs) => tuple match {
      case (word, number) => {
        val regex = s"\\b($word|${StringUtils.capitalize(word)})\\b".r
        val updated = regex.replaceAllIn(outputs.head, regexMatch => {
          if(isPrecededByHyphen(regexMatch)) {
            regexMatch.toString
          } else {
            wordsToNumbersMap(regexMatch.toString().toLowerCase).toString
          }
        })
        updated +: outputs
      }
    }).head
  }
}
