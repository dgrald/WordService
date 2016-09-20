package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/19/16.
  */
object DateCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val mapOfMonths = Map(
      "1" -> "Jan.",
      "2" -> "Feb.",
      "3" -> "March",
      "4" -> "April",
      "5" -> "May",
      "6" -> "June",
      "7" -> "July",
      "8" -> "Aug.",
      "9" -> "Sept.",
      "10" -> "Oct.",
      "11" -> "Nov.",
      "12" -> "Dec."
    )

    mapOfMonths.toList.foldRight(List(input))((pair, outputs) => pair match {
      case (toReplace: String, replacement: String) => {
        val regex = s"\\b0?$toReplace/(\\s)*[0-9]+".r
        val output = regex.replaceAllIn(outputs.head, regexMatch => {
          val split = regexMatch.toString().split("/")
          replacement ++ " " ++ split.last.trim
        })
        output +: outputs
      }
    }).head
  }
}
