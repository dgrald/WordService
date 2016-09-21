package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/19/16.
  */
object DateCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {

    Constants.mapOfMonthAbbreviations.map(e => e._2).zipWithIndex.foldRight(List(input))((pair, outputs) => pair match {
      case (replacement: String, index: Int) => {
        val regex = s"\\b0?${(index + 1).toString}/(\\s)*[0-9]+".r
        val output = regex.replaceAllIn(outputs.head, regexMatch => {
          val split = regexMatch.toString().split("/")
          replacement ++ " " ++ split.last.trim
        })
        output +: outputs
      }
    }).head
  }
}
