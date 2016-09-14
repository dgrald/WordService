package org.dgrald

/**
  * Created by dylangrald on 9/14/16.
  */
object WordToOrdinalCorrector extends Corrector {
  override def correct(input: String): String = {
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

    wordToOrdinalMap.foldRight(List(input))((ordinalPair, outputs) => ordinalPair match {
      case (key: String, value: String) => {
        val regex = s"\\b($key|${StringUtils.capitalize(key)})\\b".r
        regex.replaceAllIn(outputs.head, wordToOrdinalMap(key)) +: outputs
      }
    }).head
  }
}
