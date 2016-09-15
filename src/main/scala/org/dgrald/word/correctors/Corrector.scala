package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
trait Corrector {
  def correct(input: String, otherInstructions: List[Any] = List()): String
}

object WordServiceCorrector {
  def correct(input: String, newLines: Boolean, otherInstructions: List[Any]): String = {
    val correctors: List[Corrector] = getCorrectors(newLines)

    correctors.foldRight(List(input))((corrector, resultList) => {
      corrector.correct(resultList.head, otherInstructions) +: resultList
    }).head
  }

  def getCorrectors(newLines: Boolean): List[Corrector] = {
    val beforeNewLines = List(PreWordCorrector, FollowUpCorrector, HyphenCorrector, VersusCorrector, ComparisonSymbolCorrector, CopyErrorCorrector, MultipleSpaceCorrector, RemoveNewLineAndTabCorrector)
    val afterNewLines = List(WordToOrdinalCorrector, WordToNumberCorrector, PlusAndMinusCorrector, PercentageSpaceCorrector, MonthAbbreviationCorrector)
    if (newLines) {
      afterNewLines ++ List(NewLineCorrector) ++ beforeNewLines
    } else {
      afterNewLines ++ beforeNewLines
    }
  }
}
