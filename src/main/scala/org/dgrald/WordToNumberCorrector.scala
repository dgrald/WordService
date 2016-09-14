package org.dgrald

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
      "nine" -> 9
    )

    wordsToNumbersMap.foldRight(List(input))((tuple, outputs) => tuple match {
      case (word, number) => {
        val regex = s"($word|${StringUtils.capitalize(word)})".r
        val updated = regex.replaceAllIn(outputs.head, regexMatch => {
          wordsToNumbersMap(regexMatch.toString().toLowerCase).toString
        })
        updated +: outputs
      }
    }).head
  }
}
