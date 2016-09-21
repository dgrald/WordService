package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/31/16.
  */
object MonthAbbreviationCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {

    Constants.mapOfMonthAbbreviations.foldRight(List(input))((map, list) => map match {
      case (key, value) =>
        val regex = s"\\b$key(/)?".r
        val newInput = regex.replaceAllIn(list.head, regexMatch => {
          if(regexMatch.toString().last == '/') {
            if(list.head.length > regexMatch.end + 2 && list.head(regexMatch.end + 1).isDigit) {
              s"$value "
            } else {
              s"$value/"
            }
          } else {
            value
          }
        })
        MultipleSpaceCorrector.correct(newInput) +: list
    }).head
  }
}
